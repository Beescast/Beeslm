#coding:utf-8
from models import Admin
from utils.res import Res
from utils.globalKeys import GlobalKeys
from exception.serviceException import ServiceException
from utils.validateUtils import ValidateUtils
from utils.cacheService import CacheService
from utils.randomStringUtils import RandomStringUtils
from utils.md5Utils import MD5Utils
from utils.const import Const

class AdminService:

	@staticmethod
	def getInfoById(id):
		try:
			return Admin.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except Exception, e:
			return None

	@staticmethod
	def getList(start, limit):
		admins = Admin.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			admins = admins[start:]
		else:
			limit = int(limit)
			admins = admins[start: start+limit]
		return admins

	@staticmethod
	def login(name, password, request, response):
		res = Res()
		try:
			errorNum = CacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + name)
			if errorNum == None:
				errorNum = 0
			if errorNum >= 10:
				raise ServiceException("登录失败超过10次,请15分钟后重试")
			admin = None
			try:
				admin = Admin.objects.get(name=name)
			except Exception, e:
				pass
			if ValidateUtils.isNull(admin):
				raise ServiceException("用户名或密码错误，请重新输入")
			if admin.passwd != AdminService.getSafePass(password):
				CacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + name, errorNum + 1)
				CacheService.setExpire(GlobalKeys.PASSPORT_LOGIN_ERROR_KEY + name, 900)  # 15分钟过期
				raise ServiceException("用户名或密码错误，请重新输入")
			admin.passwd = "" # 清除密码
			sessionId = RandomStringUtils.randomAlphanumeric(32).upper() + "_" + str(admin.id) + "_ADMIN"
			CacheService.setAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionId, admin)
			res.addResponse("ops", admin.ops)
			res.addResponse("sessionKey", sessionId)
			res.addResponse("flag", True)
			response.set_cookie(GlobalKeys.PASSPORT_LOGIN_KEY, sessionId, max_age=-1, path='/')
			return res
		except ServiceException, e:
			raise e
		except Exception, e:
			raise ServiceException(e)

	@staticmethod
	def getSafePass(password):
		try:
			return MD5Utils.getMD5String(MD5Utils.getMD5String(password)+ GlobalKeys.MD5KEY)
		except ServiceException, e:
			raise Exception(e)
		return password

	@staticmethod
	def getUserId(sessionKey):
		if not ValidateUtils.isNull(sessionKey):
			admin = CacheService.getAttribute(GlobalKeys.PASSPORT_LOGIN_KEY + sessionKey)
			if admin and AdminService.isAdmin(sessionKey):
				return admin.id
		return None

	@staticmethod
	def isAdmin(sessionkey):
		keys = sessionkey.split("_")
		if keys[-1] == "ADMIN":
			return True
		return False

	@staticmethod
	def hasOp(adminId, op):
		admin = AdminService.getInfoById(adminId)
		if ValidateUtils.isNull(admin):
			raise ServiceException("用户错误")
		hasop = False
		ops = admin.ops.split(",")
		for string in ops:
			if string == str(op):
				hasop = True
				break
		if not hasop:
			raise ServiceException("无权限")

	@staticmethod
	def getListCount():
		return Admin.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).count()

	@staticmethod
	def setPassword(adminId, oldPassword, newPassword):
		admin = AdminService.getInfoById(adminId)
		if not ValidateUtils.isNull(admin) and admin.passwd == AdminService.getSafePass(oldPassword):
			admin.passwd = AdminService.getSafePass(newPassword)
			admin.save()
			return True
		else:
			return False

	@staticmethod
	def add(name, password, ops):
		try:
			Admin.objects.get(name=name) # 如果存在则返回
			return False
		except Exception, e:
			password = AdminService.getSafePass(password)
			admin = Admin(name=name, passwd=password, ops=ops)
			admin.name = name
			admin.passwd = password
			admin.ops = ops
			# admin.add_time = datetime.now()
			admin.save()
			return True

	@staticmethod
	def eidt(id, name, password, ops):
		try:
			admin = Admin.objects.get(id=id)
			admin.name = name
			if not ValidateUtils.isNull(password):
				admin.passwd = AdminService.getSafePass(password)
			admin.ops = ops
			admin.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def delete(id):
		try:
			admin = Admin.objects.get(id=id)
			admin.del_flag = Const.DEL_FLAG_DEL
			admin.save()
			return True
		except Exception, e:
			return  False

