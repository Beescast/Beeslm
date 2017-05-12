
#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_admin.adminService import AdminService
from utils.validateUtils import ValidateUtils
from packagesService import PackagesService
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def info(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res =  Res()
	try:
		packages = PackagesService.getInfoById(id)
		prices = PackagesService.getPriceByPid(id)
		res.addResponse("result", packages.getDict())
		res.addResponse("prices", prices)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def price(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		prices = PackagesService.getPriceByPid(id)
		res.addResponse("resultList", prices)
	except ServiceException, e:
		res.addError(e.getMessage())

	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def singlePrice(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res =  Res()
	try:
		price = PackagesService.getPriceByid(id)
		res.addResponse("result", price)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def list(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	order = request.POST.get('order')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		prices = PackagesService.getPackagesList(start, limit, order)
		res.addResponse("resultList", prices)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	name = request.POST.get('name')
	content = request.POST.get('content')
	pic = request.POST.get('pic')
	titleOne = request.POST.get('titleOne')
	titleTwo = request.POST.get('titleTwo')
	titleThree = request.POST.get('titleThree')
	prices = request.POST.get('prices')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res =  Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 5
		AdminService.hasOp(adminId, op)

		result = PackagesService.add(name,content,titleOne,titleTwo,titleThree,pic,prices)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def delete(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res =  Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)

		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 5
		AdminService.hasOp(adminId, op)
		result = PackagesService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def delPrice(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 5
		AdminService.hasOp(adminId, op)
		result = PackagesService.delPrice(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id = request.POST.get('id')
	name = request.POST.get('name')
	content = request.POST.get('content')
	pic = request.POST.get('pic')
	titleOne = request.POST.get('titleOne')
	titleTwo = request.POST.get('titleTwo')
	titleThree = request.POST.get('titleThree')
	prices = request.POST.get('prices')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	res = Res()
	response = None
	try:
		adminId = AdminService.getUserId(adminSessionKey)

		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op = 5
		AdminService.hasOp(adminId, op)
		result = PackagesService.edit(id, name, content, titleOne, titleTwo, titleThree, pic, prices)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
