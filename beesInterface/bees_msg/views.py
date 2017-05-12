#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from msgService import LeaveMsgService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def list(request):
	start = request.POST.get('start')
	limit = request.POST.get('limit')
	uid = request.POST.get('uid')
	name = request.POST.get('name')
	content = request.POST.get('content')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		leaveMsgs = LeaveMsgService.getList(uid, name, content, start, limit)
		dtos = []
		for msg in leaveMsgs:
			dtos.append(msg.getDict())
		total = LeaveMsgService.getListCount(uid, name, content)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	content = request.POST.get('content')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(sessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		result = LeaveMsgService.add(adminId, content)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)



