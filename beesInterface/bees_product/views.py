# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from productService import ProductService
from bees_admin.adminService import AdminService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def info(request):
	id = request.POST.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		product = ProductService.getInfoById(id)
		res.addResponse("result", product.getDict())
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def list(request):
	position = request.POST.get('position')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		products = ProductService.getList(position, start, limit)
		dtos = []
		for product in products:
			dtos.append(product.getDict())
		count = ProductService.getListCount(position)
		res.addResponse("total", count)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	name = request.POST.get('name')
	pic = request.POST.get('pic')
	href = request.POST.get('href')
	position = request.POST.get('position')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		result = ProductService.add(name, pic, href, position)
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
		adminId=AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=19
		AdminService.hasOp(adminId, op)
		result = ProductService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id = request.POST.get('id')
	name = request.POST.get('name')
	pic = request.POST.get('pic')
	href = request.POST.get('href')
	position = request.POST.get('position')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		result = ProductService.edit(id, name, pic, href, position)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
