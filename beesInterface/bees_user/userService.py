#coding:utf-8

from utils.res import Res
import time
from utils.const import Const
from exception.serviceException import ServiceException
from django.http import HttpResponse,JsonResponse,HttpRequest
from bees_user.models import User,UserAuth,UserPic
from utils.validateUtils import ValidateUtils
from utils.cacheService import CacheService
from utils.globalKeys import GlobalKeys
import django.utils.timezone as timezone
from utils.randomStringUtils import RandomStringUtils
from utils.md5Utils import MD5Utils
from bees_task.models import Task
from utils.mobileCode import MobileCode
#from utils.smsUtil import SMSUtil
from utils.validateUtils import ValidateUtils
import django.utils.timezone as timezone
from utils.stringUtil import StringUtil

class UserService:
	request = HttpRequest()
	response=HttpResponse()

	# 注册
	@staticmethod
	def register(mobile,mobileCode,truename,idCard,frontPic,backPic,handPic,plat,liveRoom,
				 platPic,code,request,response):
		res= Res()

		try:
			if ValidateUtils.isNull(mobile):
				raise ServiceException("请输入手机号码")
			elif not ValidateUtils.isMobile(mobile):
				raise ServiceException("请输入正确手机号码")
			userAgent = request.META.get("HTTP_USER_AGENT")############
			if userAgent != None:
				if userAgent.index("tiantian")>0:
					raise ServiceException("您的所有访问记录已经被记录在档，有关伪造数据后果将追究法律责任!")
			if ValidateUtils.isNull(mobileCode):
				raise ServiceException("请输入短信验证码")
			user = User.objects.get(mobile=mobile)
			if not ValidateUtils.isNull(user):
				raise ServiceException("该手机已存在")
			user = User.objects.get(id_card=idCard)
			if not ValidateUtils.isNull(user):
				raise ServiceException("该身份证号已存在")
			auth = UserAuth.objects.get(mobile)
			if not ValidateUtils.isNull(auth) and auth.auth_status()!=Const.AUTH_STATUS_TWO:
				raise ServiceException("　该手机号尚未审核，请耐心等待")
			auth = UserAuth.objects.get(id_card=idCard)
			if not ValidateUtils.isNull(auth) and auth.auth_status()!=Const.AUTH_STATUS_TWO:
				raise ServiceException("　该手机号尚未审核，请耐心等待")

		#手机验证
			mCode = UserPic.getMobileCode()
			mCode = CacheService.getAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile,UserPic.objects.all())#Mobile没写
			if mCode != None:
				if mCode.getExpires().getTime() <= time.time()*1000:
					# 已经过期
					raise ServiceException("短信验证码已过期,请重新获取")
				else:
					if mCode.getMobile() != mobile or mCode.getCode != mobileCode:
						raise ServiceException("短信验证码不正确")
			else:
				raise ServiceException("短信验证码已过期,请重新获取")

			entity = UserAuth()
			entity.mobile = mobile
			entity.truename = truename
			entity.id_card = idCard
			entity.front_pic = frontPic
			entity.back_pic = backPic
			entity.hand_pic = handPic
			entity.plat = plat
			entity.live_room = liveRoom
			entity.plat_pic = plat
			# String password = StringUtil.code(6)
			# entity.setPasswd(this.getSafePass(password))
			# entity.add_time = timezone.now()
			# entity.reg_time = timezone.now()
			entity.save()

			sessionId = RandomStringUtils.randomAlphanumeric(32).upper() + "_" + entity.getId()
			CacheService.setAttribute(
			GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, entity)

			res.addResponse("sessionKey", sessionId)
			res.addResponse("flag", True)
			user = {'UserId':entity.id, 'UserMobile':entity.mobile}
			res.addResponse("user", user)

			response.set_cookie(GlobalKeys.PASSPORT_LOGIN_KEY, sessionId, max_age=-1, path='/')

			CacheService.removeAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY
										 + "__" + mobile)
			return res
		except Exception, e:
			raise e
		return

	# 登陆验证
	@staticmethod
	def login(mobile, password, mobileCode, request, response, httpSession):
		res = Res()
		try:
			if (ValidateUtils.isNull(mobile)):
				raise ServiceException("请输入手机号码")

			user = None
			try:
				user = User.objects.get(mobile=mobile)
			except:
				pass
			if(ValidateUtils.isNull(user)):
				auth = None
				try:
					auth = UserAuth.objects.get(mobile=mobile)
				except:
					pass
				if (not ValidateUtils.isNull(auth) and auth.auth_status != 1):
					raise ServiceException("审核中")
				else:
					raise ServiceException("请输入正确的手机号码")
			errorNum = CacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + mobile)
			if not errorNum:
				errorNum = 0
			if (errorNum >= 500):
				raise ServiceException("登录失败超过500次，请15分钟后重试")
			
			if(not ValidateUtils.isNull(password)):
				if (user.passwd != UserService.getSafePass(password)):
					CacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
							+ mobile, errorNum + 1)
					CacheService.setExpire(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY
							+ mobile, 900)# 15分钟过期
					raise ServiceException("用户名或密码错误，请重新输入")
			else:
				mCode = CacheService.getAttribute(
						GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile)
				if not mCode:
					if (mCode.getExpires().getTime() <= time.time()*1000):
						# 已经过期
						raise ServiceException("短信验证码已过期,请重新获取")
					else:
						if mCode.getMobile() != mobile or mCode.getCode() != mobileCode:
							raise ServiceException("短信验证码不正确")
				else:
					raise ServiceException("短信验证码已过期,请重新获取")
				CacheService.setAttribute("SESSION_ID_"+mobile, httpSession.getId())

			user.setPasswd("")# 清除密码

			sessionId = RandomStringUtils.randomAlphanumeric(32).upper() + "_" + user.id
			CacheService.setAttribute(
					GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, user)

			res.addResponse("sessionKey", sessionId)
			res.addResponse("flag", True)
			res.addResponse("userid", user.id)
			res.addResponse("nickname", user.nickname)
			res.addResponse("avatar", user.avatar)
			userDict = {}
			userDict['UserId'] = user.id
			userDict['UserMobile'] = user.mobile
			userDict['avatar'] = user.avatar
			res.addResponse("user", userDict())
			response.set_cookie(GlobalKeys.PASSPORT_LOGIN_KEY, sessionId, max_age=-1, path='/')
			#cookie.setDomain(GlobalConfigure.AUTH_DOMAIN)
			# res.setRedirect("index.html")
			return res
		except Exception, e:
			raise e

	@staticmethod
	def getSafePass(password):
		try:
			return MD5Utils.getMD5String(MD5Utils.getMD5String(password) + GlobalKeys.MD5KEY)
		except Exception,e:
			pass
		return password

	@staticmethod
	def getUserId(sessionKey):
		if not ValidateUtils.isNull(sessionKey):
			user = CacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionKey)
			if user:
				return user.id
		return None

	@staticmethod
	def getUserSubInfo(start, limit, order):
		users = User.objects.all()
		if not ValidateUtils.isNull(order):
			users = users.order_by(order)
		if ValidateUtils.isNull(start):
			start = 0
		if ValidateUtils.isNull(limit):
			users = users[start:]
		else:
			users = users[start: start+limit]
		dtos = []
		for user in users:
			dto = {}
			dto['description'] = user.description
			dto['headImg'] = user.avatar
			dto['id'] = user.id
			dto['title'] = user.nickname
			dto['description'] = user.description
			userPics = UserPic.objects.filter(uid=user.id)
			pics = []
			for userPic in userPics:
				pics.append(userPic.pic)
			dto['mgList'] = pics
			dtos.append(dto)
		return dtos

	@staticmethod
	def authCode(mobile, mobileCode, request, response, httpSession):
		res = Res()
		mCode = CacheService.getAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + mobile)
		if mCode:
			if (mCode.getExpires().getTime() <= time.time()*1000):
			# 已经过期
				raise ServiceException("短信验证码已过期,请重新获取")
			else:
				if (mCode.getMobile() != mobile or mCode.getCode() != mobileCode):
					raise ServiceException("短信验证码不正确")
		else:
			raise ServiceException("短信验证码已过期,请重新获取")

		try:
			user = User.objects.get(mobile=mobile)
			sessionId = RandomStringUtils.randomAlphanumeric(32).upper() + "_" + user.id
			CacheService.setAttribute("SESSION_ID_" + user.id, httpSession['id'])
			CacheService.setAttribute("SESSION_ID_" + sessionId, httpSession['id'])
			CacheService.setAttribute("FIND_PASS_" + httpSession['id'], user.id)
		except Exception, e:
			raise e
		res.addResponse("sessionKey", sessionId)
		res.addResponse("id", httpSession['id'])
		res.addResponse("flag", True)
		return res

	@staticmethod
	def modifyPassword(sessionKey, oldPassword, newPassword):
		uid = UserService.getUserId(sessionKey)
		if (not ValidateUtils.isNull(uid)):
			user = User.objects.get(id=uid)
			if (user.passwd != UserService.getSafePass(oldPassword)):
				raise ServiceException("用户名老密码错误，请重新输入")
			user.passwd = UserService.getSafePass(newPassword)
			user.save()
			#SMSUtil.doPost(user.mobile, 11, "", None) # shq
			CacheService.removeAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionKey)
			return True
		return False

	@staticmethod
	def setPayPassword(uid, password):
		try:
			user = User.objects.get(id=uid)
			user.pay_passwd = password
			user.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def editById(uid, email, contactMobile, contactAddress, agencyName, agencyMobile, description, taskCat, bankName, subBankName, bankCard, accountName, avatar, nickname):
		user = None
		try:
			user = User.objects.get(id=uid)
		except Exception, e:
			return False
		user.email = email
		user.contact_mobile = contactMobile
		user.contact_address = contactAddress
		user.agency_name = agencyName
		user.agency_mobile = agencyMobile
		user.description = description
		user.task_cat = taskCat
		user.bank_name = bankName
		user.sub_bank_name = subBankName
		user.bank_card = bankCard
		user.account_name = accountName
		user.avatar = avatar
		user.nickname = nickname
		if not ValidateUtils.isNull(taskCat):
			taskStrings = taskCat.split(",")
			count = 0
			for i in range(0, taskStrings.length):
				count += len(Task.objects.filter(cat_id =int(taskStrings[i])))
			user.tonji = count
			user.save()
			return True
		else:
			return False

	@staticmethod
	def authPayPassword(uid, password):
		user = User.objects.get(id=uid)
		if (UserService.getSafePass(password) == user.pay_passwd):
			return True
		else:
			return False

	@staticmethod
	def getUser(uid):
		try:
			user = User.objects.get(id=uid)
			if (user.pay_passwd != ""):
				user.payPass = 1
			if (user.task_cat != ""):
				user.tasks = user.taskCat.split(",")
		except Exception, e:
			print e
		return user.getUserInfoDto()

	@staticmethod
	def isAdmin(sessionKey):
		keys = sessionKey.split("_")
		if (keys[keys.length - 1] == ("ADMIN")):
			return True
		return False

	@staticmethod
	def setPassword(id, newPass):
		try:
			user = User.object.get(id=id)
			user.passwd = UserService.getSafePass(newPass)
			user.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def getMobileCode(mobile, action, userId):
		res = Res()
		try:
			if action.lower() == "changemobile":
				user = User.objects.get(id=userId)
				mobile=user.mobile
			if (not ValidateUtils.isMobile(mobile)):
				raise ServiceException("请输入正确手机号码")
			if (action.lower() == "login" and ValidateUtils.isNull(user.objects.filter(mobile=mobile))):
				raise ServiceException("该手机号码不存在")
			if (action.lower() == "register" and not ValidateUtils.isNull(user.objects.filter(mobile=mobile))):
				raise ServiceException("该手机号码已注册")
			code = RandomStringUtils.randomNumeric(4)
			mCode = MobileCode.MobileCode()
			mCode.setMobile(mobile)
			mCode.setCode(code)
			mCode.setExpires(time.localtime(time.time()+1800*1000)) # setExpires.getTime() ->time.mktime(time.localtime(time.time()))
			CacheService.setAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY + "__"+ mobile, mCode)
			# SMSUtil.doPost(mobile, 1, code, None)
			res.addResponse("flag", True)

			return res
		except Exception, e:
			raise e
		return False

	@staticmethod
	def changeMobile(id, mobileCode, newMobile):
		mCode = CacheService.getAttribute(GlobalKeys.PASSPORT_REG_MCODE_KEY + "__" + newMobile)
		if mCode:
			if (mCode.getExpires().getTime() <= time.time()*1000):
				# 已经过期
				raise ServiceException("短信验证码已过期,请重新获取")
			else:
				if (mCode.getMobile() != newMobile or mCode.getCode() != mobileCode):
					raise ServiceException("短信验证码不正确")
		else:
			raise ServiceException("短信验证码已过期,请重新获取")
		user = User.objects.get(mobile=newMobile)
		if (ValidateUtils.isNull(user) and ValidateUtils.isMobile(newMobile)):
			oldUser = User.objects.get(id=id)
			oldUser.mobile = newMobile
			oldUser.save()
			return True
		else:
			raise ServiceException("请输入正确手机号码")
		return False

	@staticmethod
	def auth(adminId, id, authStatus, reson):
		try:
			userAuth = UserAuth.objects.get(id=id)
			userAuth.auth_opid = adminId
			userAuth.auth_status = authStatus
			# userAuth.auth_time = datetime.date.today()
			userAuth.save()
		except Exception, e:
			res = False
		if (res > 0):
			if authStatus == '1':
				user = User()
				#PropertyUtils.copyProperties(user, userAuth)
				password = StringUtil.code(6)
				user.passwd = UserService.getSafePass(password)
				user.nickname = user.live_room()
				# user.add_time = datetime.date.today()
				user.save()
				# SMSUtil.doPost(userAuth.getMobile(), 2, userAuth.getMobile()+","+password, None)
			else:
				# SMSUtil.doPost(userAuth.getMobile(), 3, reson, None)
				pass
		else:
			return False
		return True

	@staticmethod
	def getByMobile(mobile):
		try:
			return User.objects.get(mobile=mobile)
		except Exception, e:
			return None

	@staticmethod
	def adminList(mobile, nickName, plat, liveRoom, startDate, endDate, authStatus, id, balance, start, limit):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(authStatus):
			users = users.filter(auth_status=authStatus)
		if not ValidateUtils.isNull(nickName):
			users = users.filter(nickname__contains=nickName)
		if not ValidateUtils.isNull(plat):
			users = users.filter(plat__contains=plat)
		if not ValidateUtils.isNull(liveRoom):
			users = users.filter(live_room__contains=liveRoom)
		if not ValidateUtils.isNull(startDate):
			users = users.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			users = users.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(balance):
			if balance == '1':
				users = users.filter(balance=0.0).order_by('-id')
			else:
				users = users.exclude(balance=0.0).order_by('-balance')
		else:
			users = users.order_by('-id')
		if not ValidateUtils.isNull(id):
			users = users.filter(id=id)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			users = users[start:]
		else:
			limit = int(limit)
			users = users[start: start+limit]
		dtos = []
		for user in users:
			dto = user.getUserDto()
			dto['passwd'] = ""
			dto['payPasswd'] = ""
			dtos.append(dto)
		return dtos

	@staticmethod
	def adminListCount(mobile, nickName, plat, liveRoom, startDate, authStatus, endDate, id, balance):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(authStatus):
			users = users.filter(auth_status=authStatus)
		if not ValidateUtils.isNull(nickName):
			users = users.filter(nickname__contains=nickName)
		if not ValidateUtils.isNull(plat):
			users = users.filter(plat__contains=plat)
		if not ValidateUtils.isNull(liveRoom):
			users = users.filter(live_room__contains=liveRoom)
		if not ValidateUtils.isNull(startDate):
			users = users.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			users = users.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(id):
			users = users.filter(id=id)
		if not ValidateUtils.isNull(balance):
			if balance == '1':
				users = users.filter(balance='0.0')
			else:
				users = users.exclude(balance='0.0')
		return users.count()

	@staticmethod
	def unionLogin(openid, type):
		res = Res()
		user = None
		if type == Const.LOGIN_QQ:
			user = User.objects.get(qq_id=openid)
		elif type == Const.LOGIN_WEIXIN:
			user = User.objects.get(weixin_id=openid)
		if (not ValidateUtils.isNull(user)):
			sessionId = RandomStringUtils.randomAlphanumeric(32).upper() + "_" + user.id
			CacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, user)

			res.addResponse("sessionKey", sessionId)
			res.addResponse("flag", True)
			res.addResponse("userid", user.id)
			res.addResponse("nickname", user.nickname)
			res.addResponse("avatar", user.avatar)
			UserMap = {}
			UserMap["UserId"] = user.id
			UserMap["UserMobile"] = user.mobile
			UserMap["avatar"] = user.avatar
			res.addResponse("user", UserMap)
		else:
			res.addResponse("result", -1)
		return res

	@staticmethod
	def bindUnion(uid, type, openid):
		user = User()
		user.id = uid
		if type == Const.LOGIN_QQ:
			user.qq_id=openid
		elif type == Const.LOGIN_WEIXIN:
			user.weixin_id=openid
		user.save()
		return True

	@staticmethod
	def getUserById(userId):
		try:
			return User.objects.get(id=userId)
		except Exception, e:
			return None

	@staticmethod
	def authList(mobile, truename, plat, liveRoom, startDate, endDate, authStatus, id, start, limit):
			users = UserAuth.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(mobile):
				users = users.filter(mobile=mobile)
			if not ValidateUtils.isNull(authStatus):
				users = users.filter(auth_status=authStatus)
			if not ValidateUtils.isNull(truename):
				users = users.filter(nick_name__contains=truename)
			if not ValidateUtils.isNull(plat):
				users = users.filter(plat__contains=plat)
			if not ValidateUtils.isNull(liveRoom):
				users = users.filter(live_room__contains=liveRoom)
			if not ValidateUtils.isNull(startDate):
				users = users.filter(add_time__gte=startDate)
			if not ValidateUtils.isNull(endDate):
				users = users.filter(add_time__lte=endDate)
			if not ValidateUtils.isNull(id):
				users = users.filter(id=id)
			if ValidateUtils.isNull(start):
				start = 0
			else:
				start = int(start)
			if ValidateUtils.isNull(limit):
				users = users[start:]
			else:
				limit = int(limit)
				users = users[start: start+limit]
			dtos = []
			for user in users:
				dtos.append(user.getDict())
			return dtos

	@staticmethod
	def authListCount(mobile, truename, plat, liveRoom, startDate, authStatus, endDate, id):
		#return this.userAuthManager.authListCount(mobile, truename, plat, liveRoom, startDate, endDate, authStatus, id)
		users = UserAuth.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(authStatus):
			users = users.filter(auth_status=authStatus)
		if not ValidateUtils.isNull(truename):
			users = users.filter(nickname__contains=truename)
		if not ValidateUtils.isNull(plat):
			users = users.filter(plat__contains=plat)
		if not ValidateUtils.isNull(liveRoom):
			users = users.filter(live_room__contains=liveRoom)
		if not ValidateUtils.isNull(startDate):
			users = users.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			users = users.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(id):
			users = users.filter(id=id)
		return users.count()

	@staticmethod
	def getUserAuth(id):
		try:
			user = UserAuth.objects.get(id=id)
			return user
		except Exception, e:
			raise e

	@staticmethod
	def unbindBank(uid):
		try:
			user = User.objects.get(id=uid)
			user.bank_name = ''
			user.sub_bank_name = ''
			user.account_name = ''
			user.bank_card = ''
			user.save()
			return True
		except Exception, e:
			return False