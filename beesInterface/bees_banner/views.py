#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bannerService import BannerService
from bees_admin.adminService import AdminService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

import sys
sys.path.append('..')
# Create your views here.

@csrf_exempt
def list(request):
	page = request.POST.get('page')
	position = request.POST.get('position')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	order = request.POST.get('order')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		dtos = BannerService.getList(page, position, start, limit)
		total = BannerService.getListCount(page, position)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def info(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		banner = BannerService.getInfoById(id)
		res.addResponse("result", banner.getDict())
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	title = request.POST.get('title')
	content = request.POST.get('content')
	page = request.POST.get('page')
	position = request.POST.get('position')
	picOrder = request.POST.get('picOrder')
	picHref = request.POST.get('picHref')
	picUrl = request.POST.get('picUrl')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if (ValidateUtils.isNull(adminId)):
			raise ServiceException("登陆失效")
		op=4
		AdminService.hasOp(adminId, op)
		result = BannerService.add(adminId,title,content,page,position,picOrder,picHref,picUrl)
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
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if (ValidateUtils.isNull(adminId)):
			raise ServiceException("登陆失效")
		op = 4
		AdminService.hasOp(adminId, op)
		result = BannerService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id = request.POST.get('id')
	title = request.POST.get('title')
	content = request.POST.get('content')
	page = request.POST.get('page')
	position = request.POST.get('position')
	picOrder = request.POST.get('picOrder')
	picHref = request.POST.get('picHref')
	picUrl = request.POST.get('picUrl')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if (ValidateUtils.isNull(adminId)):
			raise ServiceException("登陆失效")
		op = 4
		AdminService.hasOp(adminId, op)
		result = BannerService.edit(id, title, content, page, position, picOrder, picHref, picUrl)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
