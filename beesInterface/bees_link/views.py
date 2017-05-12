
#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from linkService import LinkService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

@csrf_exempt
def list(request):
	start = request.POST.get('start')
	limit = request.POST.get('limit')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		links = LinkService.getList(start, limit)
		total = LinkService.getListCount()
		res.addResponse("total", total)
		res.addResponse("resultList", links)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	title = request.POST.get('title')
	href = request.POST.get('href')
	orders = request.POST.get('orders')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 18
		AdminService.hasOp(adminId, op)
		result = LinkService.add(title, href, orders)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def delete(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId =AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException ("登陆失效")
		op = 18
		AdminService.hasOp(id, op)
		result = LinkService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id=request.POST.get('id')
	title = request.POST.get('title')
	href = request.POST.get('href')
	orders = request.POST.get('orders')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId =AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException ("登陆失效")
		op = 18
		AdminService.hasOp(id, op)
		result = LinkService.edit(id, title, href, orders)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)









