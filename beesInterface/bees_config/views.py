#coding:utf-8
# Create your views here.

from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from configService import ConfigService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

@csrf_exempt
def info(request):
	id = request.POST.get('id')
	value = request.POST.get('value')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		config = ConfigService.getInfoById(id)
		res.addResponse("result", config)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, 'index', callback, response)

@csrf_exempt
def list(request):
	start = request.POST.get('start')
	limit = request.POST.get('limit')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失败")
		op = 19
		AdminService.hasOp(adminId, op)
		configs = ConfigService.getList(start, limit)
		dtos = []
		for config in configs:
			dtos.append(config.getDict())
		count = ConfigService.getListCount()
		res.addResponse("total", count)
		res.addResponse("resultList", dtos)
	except ServiceException , e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		op = 19
		AdminService.hasOp(adminId, op)
		result = ConfigService.add(adminId, title, content, status);
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, 'index', callback, response)

@csrf_exempt
def delete(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 19
		AdminService.hasOp(adminId, op)
		result = ConfigService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id=request.POST.get('id')
	value = request.POST.get('value')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 7
		AdminService.hasOp(adminId, op)
		result = ConfigService.edit(id,value)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)









