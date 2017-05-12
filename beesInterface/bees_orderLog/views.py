
#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from bees_orderLog.orderLogService import OrderLogService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

@csrf_exempt
def adminList(request):
	uid = request.POST.get('uid')
	type = request.POST.get('type')
	incomeType = request.POST.get('incomeType')
	mobile = request.POST.get('mobile')
	nickname = request.POST.get('nickname')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	startMoney = request.POST.get('startMoney')
	endMoney = request.POST.get('endMoney')
	orderId = request.POST.get('orderId')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 14
		AdminService.hasOp(adminId, op)
		startDate=None
		endDate=None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		OrderLogs = OrderLogService.getList(uid, type, incomeType, mobile, nickname, startDate, endDate, startMoney,
											 endMoney, orderId, start, limit)
		total = OrderLogService.getListCount(uid, type, incomeType, mobile, nickname, startDate, endDate, startMoney,
											  endMoney, orderId)
		res.addResponse("total", total)
		res.addResponse("resultList", OrderLogs)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)



