
#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from bees_order.orderService import OrderService
from utils.validateUtils import ValidateUtils
from bees_user.userService import UserService
from django.views.decorators.csrf import csrf_exempt

import sys
sys.path.append('..')

@csrf_exempt
def list(request):
	uid = request.POST.get('uid')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	mobile = request.POST.get('mobile')
	id = request.POST.get('id')
	payType = request.POST.get('payType')
	status = request.POST.get('status')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = None
		adminId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
			op = 1
			AdminService.hasOp(adminId, op)
			if not ValidateUtils.isNull(uid):
				userId = uid
		if ValidateUtils.isNull(userId) and ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		if not ValidateUtils.isNull(mobile):
			user = UserService.getByMobile(mobile)
			if ValidateUtils.isNull(user) or not ValidateUtils.isNull(userId) and userId != user.id:
				raise ServiceException("无该手机号或手机号和uid不匹配")
			else:
				userId = user.id
		orders = OrderService.getList(userId, id, payType, status, startDate, endDate, None, start, limit)
		total = OrderService.getListCount(userId, id, payType, status, startDate, endDate, None)
		res.addResponse("total", total)
		res.addResponse("resultList", orders)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def info(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = None
		adminId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId) and ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		order = OrderService.getInfoById(id)
		if order:
			res.addResponse("result", order.getDict())
		else:
			res.addResponse("result", None)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	packageId = request.POST.get('packageId')
	packagePriceId = request.POST.get('packagePriceId')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		res = OrderService.add(userId, packageId, packagePriceId)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def delete(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		result = OrderService.delete(id, userId)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def realDel(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		result = OrderService.realDel(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def startPackage(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	endDate = request.POST.get('endDate')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		if ValidateUtils.isNull(adminSessionKey) or ValidateUtils.isNull(id) or ValidateUtils.isNull(endDate):
			raise ServiceException("登陆失效")
		flag=None
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		# endDates = endDate
		flag = OrderService.startPackage(id, endDate)
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)




