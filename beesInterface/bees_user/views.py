#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from bees_user.userService import UserService
from bees_package.models import PackagesOrder
from bees_user.models import User, UserAuth
from utils.validateUtils import ValidateUtils
from utils.cacheService import CacheService
from django.views.decorators.csrf import csrf_exempt

import sys
sys.path.append('..')

@csrf_exempt
def info(request):
	uid = request.POST.get('uid')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response= HttpResponse()
	try:
		user= None
		if not ValidateUtils.isNull(sessionKey):
			uids = UserService.getUserId(sessionKey)
			if not ValidateUtils.isNull(uids):
				user = UserService.getUser(uids)
			else:
				raise ServiceException("登陆失效，请重新登陆")
		if not ValidateUtils.isNull(adminSessionKey):
			ops = AdminService.getUserId(adminSessionKey)
			if not ValidateUtils.isNull(ops):
				dto = UserService.getUser(uid)
			else:
				raise ServiceException("登陆失效，请重新登陆")
		res.addResponse("result", dto)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def list(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	order = request.POST.get('order')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		dtos = UserService.getUserSubInfo(start, limit, order)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def setPassword(request):
	newPassword = request.POST.get('newPassword')
	oldPassword = request.POST.get('oldPassword')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		flag = UserService.modifyPassword(sessionKey, oldPassword, newPassword)
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def setPayPassword(request):
	id = request.POST.get('id')
	newPass = request.POST.get('newPass')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		if ValidateUtils.isNull(id) or ValidateUtils.isNull(sessionKey) or ValidateUtils.isNull(newPass):
			raise ServiceException("缺少参数")
		else:
			userId = UserService.getUserId(sessionKey)
			if cacheService.getAttribute("SESSION_ID_" + userId ) == id :
				flag = UserService.setPayPassword(userId, newPass)
			else:
				raise ServiceException("参数不正确")
			res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	nickname = request.POST.get('nickname')
	avatar = request.POST.get('avatar')
	email = request.POST.get('email')
	contactMobile = request.POST.get('contactMobile')
	contactAddress = request.POST.get('contactAddress')
	agencyName = request.POST.get('agencyName')
	agencyMobile = request.POST.get('agencyMobile')
	description = request.POST.get('description')
	taskCat = request.POST.get('taskCat')
	bankName = request.POST.get('bankName')
	subBankName = request.POST.get('subBankName')
	bankCard = request.POST.get('bankCard')
	accountName = request.POST.get('accountName')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		uid =UserService.getUserId(sessionKey)
		if not ValidateUtils.isNull(uid):
			flag = UserService.editById(uid, email, contactMobile, contactAddress, agencyName, agencyMobile,
											 description, taskCat, bankName, subBankName, bankCard, accountName, avatar,
											 nickname)
		else:
			flag=False
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def editAvatar(request):
	dataY = request.POST.get('dataY')
	dataHeight = request.POST.get('dataHeight')
	dataWidth = request.POST.get('dataWidth')
	dataX = request.POST.get('dataX')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		uid = UserService.getUserId(sessionKey)
		if not ValidateUtils.isNull(uid):
			filepath = FilesUtil.uploads(request, uid, dataX, dataY, dataWidth, dataHeight)
			flag =UserService.editById(uid, filepath)
			res.addResponse("avatar", filepath)
		else:
			flag = False
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def changeMobile(request):
	id = request.POST.get('id')
	newMobile = request.POST.get('newMobile')
	mobileCode = request.POST.get('mobileCode')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		if ValidateUtils.isNull(id)or ValidateUtils.isNull(sessionKey) or ValidateUtils.isNull(newMobile):
			raise ServiceException ("参数错误")
		else:
			userId = UserService.getUserId(sessionKey)
			if cacheService.getAttribute("SESSION_ID_" + userId)== id :
				flag = UserService.changeMobile(userId, mobileCode, newMobile)
			else:
				raise ServiceException("参数不正确")
			res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)


#用户验证
# @param userId
# * @param authStatus
# * @param adminSessionKey
# * @param formatType
# * @param callback
# * @param request
# * @param response
# * @param httpSession
# * @return
@csrf_exempt
def auth(request):
	id = request.POST.get('id')
	authStatus = request.POST.get('authStatus')
	reson = request.POST.get('reson')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		if ValidateUtils.isNull(id) or ValidateUtils.isNull(authStatus) or ValidateUtils.isNull(adminSessionKey):
			raise ServiceException("缺少参数")
		else:
			adminId = AdminService.getUserId(adminSessionKey)
			op = 3
			AdminService.hasOp(adminId, op)
			if not ValidateUtils.isNull(adminId):
				flag = UserService.auth(adminId, id, authStatus, reson)
			else:
				raise ServiceException("登陆失效")
			res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	# except Exception, e:
	#	 e.printStackTrace()
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def authList(request):
	mobile = request.POST.get('mobile')
	truename = request.POST.get('truename')
	plat = request.POST.get('plat')
	liveRoom = request.POST.get('liveRoom')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	authStatus = request.POST.get('authStatus')
	id = request.POST.get('id')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		if ValidateUtils.isNull(adminSessionKey):
			raise  ServiceException("缺少参数")
		adminId =AdminService.getUserId(adminSessionKey)
		op = 2
		AdminService.hasOp(adminId, op)
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		dtos = UserService.authList(mobile, truename, plat, liveRoom, startDate, endDate, authStatus, id, start, limit)
		total =UserService.authListCount(mobile, truename, plat, liveRoom, startDate, authStatus, endDate, id)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def adminList(request):
	mobile = request.POST.get('mobile')
	nickName = request.POST.get('nickName')
	plat = request.POST.get('plat')
	liveRoom = request.POST.get('liveRoom')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	authStatus = request.POST.get('authStatus')
	id = request.POST.get('id')
	balance = request.POST.get('balance')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		if ValidateUtils.isNull(adminSessionKey):
			raise ServiceException("缺少参数")
		adminId = AdminService.getUserId(adminSessionKey)
		op = 2
		AdminService.hasOp(adminId, op)
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		dtos = UserService.adminList(mobile, nickName, plat, liveRoom, startDate, endDate, authStatus, id, balance,
									  start, limit)
		total = UserService.adminListCount(mobile, nickName, plat, liveRoom, startDate, authStatus, endDate, id,
											balance)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def authInfo(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		user = None
		if not ValidateUtils.isNull(adminSessionKey):
			ops = AdminService.getUserId(adminSessionKey)
			if not ValidateUtils.isNull(ops):
				user = UserService.getUserAuth(id)
			else:
				raise ServiceException("登陆失效，请重新登陆")
		res.addResponse("result", user.getDict())
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def unbindBank(request):
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = HttpResponse()
	try:
		uid = UserService.getUserId(sessionKey)
		if not ValidateUtils.isNull(uid):
			flag =UserService.unbindBank(uid)
		else:
			flag = False
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
