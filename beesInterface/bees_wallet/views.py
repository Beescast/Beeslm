#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_banner import bannerService
from bees_admin.adminService import AdminService
from bees_user.userService import UserService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt
from bees_wallet.walletService import WalletService

# Create your views here.
 # * 钱包流水
@csrf_exempt
def list(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = HttpResponse()
	res = Res()
	try:
		uid = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(uid):
			raise ServiceException("登陆失效，请重新登陆")
		wallets = WalletService.getList(uid, start, limit)
		total =WalletService.getListCount(uid)
		res.addResponse("total", total)
		res.addResponse("resultList", wallets)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

# *提现
@csrf_exempt
def cash(request):
	password = request.POST.get('password')
	money = request.POST.get('money')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = HttpResponse()
	res = Res()
	try:
		uid = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(uid):
			raise ServiceException("登陆失效，请重新登陆")
		if not UserService.authPayPassword(uid,password):
			raise ServiceException("密码错误")
		result = WalletService.cash(uid, money)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

# *钱包流水
@csrf_exempt
def adminList(request):
	id = request.POST.get('id')
	uid = request.POST.get('uid')
	payType = request.POST.get('payType')
	mobile = request.POST.get('mobile')
	businessNo = request.POST.get('businessNo')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	businessType = request.POST.get('businessType')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = HttpResponse()
	res = Res()
	try:
		adminId = UserService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=15
		AdminService.hasOp(adminId, op)
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		OrderLogs = WalletService.adminTurnOverList(id, uid, payType, mobile, businessNo, startDate, endDate,
														 businessType, start, limit)
		total = WalletService.adminTurnOverCount(id, uid, payType, mobile, businessNo, startDate, endDate,
													  businessType)
		res.addResponse("resultList", OrderLogs)
		res.addResponse("total", total)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

# *提现列表
@csrf_exempt
def adminCashList(request):
	id = request.POST.get('id')
	uid = request.POST.get('uid')
	accountName = request.POST.get('accountName')
	status = request.POST.get('status')
	mobile = request.POST.get('mobile')
	nickname = request.POST.get('nickname')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	startMoney = request.POST.get('startMoney')
	endMoney = request.POST.get('endMoney')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = HttpResponse()
	res = Res()
	try:
		adminId = UserService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=15
		AdminService.hasOp(adminId, op)
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		OrderLogs = WalletService.adminList(id, status, accountName, mobile, nickname, startDate, endDate, uid,
												 startMoney, endMoney, start, limit)
		total = WalletService.adminListCount(id, status, accountName, mobile, nickname, startDate, endDate, uid,
												  startMoney, endMoney)
		res.addResponse("resultList", OrderLogs)
		res.addResponse("total", total)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

 # * 提现审核
@csrf_exempt
def cashOp(request):
	id = request.POST.get('id')
	status = request.POST.get('status')
	orderId = request.POST.get('thirdOrderId')
	reseaon = request.POST.get('reseaon')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = HttpResponse()
	res = Res()
	try:
		adminId = UserService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=15
		AdminService.hasOp(adminId, op)
		result = WalletService.cashOp(adminId, id, status, orderId, reseaon)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)








