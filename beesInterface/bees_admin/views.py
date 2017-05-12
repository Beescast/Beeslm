#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt
import sys
sys.path.append('..')

# Create your views here.
@csrf_exempt
def list(request):
	start = request.POST.get('start')
	limit = request.POST.get('limit')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 17
		AdminService.hasOp(adminId, op)
		admins = AdminService.getList(start, limit)
		dtos = []
		for admin in admins:
			dtos.append(admin.getAdminDto())
		total = AdminService.getListCount()
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def info(request):
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
		op = 17
		AdminService.hasOp(adminId, op)
		admin = AdminService.getInfoById(id)
		if admin:
			res.addResponse("result", admin.getDict())
		else:
			res.addResponse("result", None)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	ops = request.POST.get('ops')
	name = request.POST.get('name')
	password = request.POST.get('password')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId =AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 17
		AdminService.hasOp(adminId, op)
		result = AdminService.add(name, password, ops)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id = request.POST.get('id')
	ops = request.POST.get('ops')
	name = request.POST.get('name')
	password = request.POST.get('password')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 17
		AdminService.hasOp(adminId, op)
		result = AdminService.eidt(id, name, password, ops)
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
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 17
		AdminService.hasOp(adminId, op)
		result = AdminService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def login(request):
	name = request.POST.get('name')
	password = request.POST.get('password')
	code = request.POST.get('code')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		if ValidateUtils.isNull(password):
			raise ServiceException("请输入账号密码")
		if ValidateUtils.isNull(name):
			raise ServiceException("请输入账号")
		res = AdminService.login(name, password, request, response)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def setPassword(request):
	oldPassword = request.POST.get('oldPassword')
	newPassword = request.POST.get('newPassword')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		flag = AdminService.setPassword(adminId, oldPassword, newPassword)
		res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
