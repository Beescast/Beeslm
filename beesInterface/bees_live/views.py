#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from liveService import LiveService
from bees_admin.adminService import AdminService
from bees_user.userService import UserService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

import sys
sys.path.append('..')

# Create your views here.
@csrf_exempt
def market(request):
	id = request.POST.get('id')
	uid = request.POST.get('uid')
	name = request.POST.get('name')
	startDay = request.POST.get('startDay')
	endDay = request.POST.get('endDay')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	adminSessionKey = request.POST.get('adminSessionKey')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		dtos=[]
		total=0
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
			if ValidateUtils.isNull(adminId):
				raise ServiceException("登陆失效")
			op=11
			AdminService.hasOp(adminId, op)
			startDate=None
			endDate=None
			if not ValidateUtils.isNull(startDay):
				startDate = startDay
			if not ValidateUtils.isNull(endDay):
				endDate = endDay
			dtos = LiveService.market(uid, id, name, startDate, endDate, startTime, endTime, start, limit)
			total = LiveService.marketCount(uid, adminId, name, startDate, endDate, startTime, endTime)
		userId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
			dtos = LiveService.market(userId, None, None, None, None, start, limit)
			total = LiveService.marketCount(userId, None, None, None, None)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def people(request):
	id = request.POST.get('id')
	uid = request.POST.get('uid')
	name = request.POST.get('name')
	startDay = request.POST.get('startDay')
	endDay = request.POST.get('endDay')
	adminSessionKey = request.POST.get('adminSessionKey')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		dtos=[]
		total=0
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
			if ValidateUtils.isNull(adminId):
				raise ServiceException("登陆失效")
			op=11
			AdminService.hasOp(adminId, op)
			startDate=None
			endDate=None
			if not ValidateUtils.isNull(startDay):
				startDate = startDay
			if not ValidateUtils.isNull(endDay):
				endDate = endDay
			dtos = LiveService.people(uid, id, name, startDate, endDate, start, limit)
			total = LiveService.giftCount(uid, adminId, name, startDate, endDate)
		userId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
			dtos = LiveService.people(userId, None, None, None, None, start, limit)
			total = LiveService.peopleCount(userId, None, None, None, None)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def gift(request):
	id = request.POST.get('id')
	startDay = request.POST.get('startDay')
	endDay = request.POST.get('endDay')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	sessionKey = request.POST.get('sessionKey')
	adminSessionKey = request.POST.get('adminSessionKey')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		dtos=[]
		total=0
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
			if ValidateUtils.isNull(adminId):
				raise ServiceException("登陆失效")
			op=11
			AdminService.hasOp(adminId, op)
			startDate=None
			endDate=None
			if not ValidateUtils.isNull(startDay):
				startDate = startDay
			if not ValidateUtils.isNull(endDay):
				endDate = endDay
			dtos = LiveService.gift(id, startDate, endDate, startTime, endTime, start, limit)
			total = LiveService.giftCount(id, startDate, endDate, startTime, endTime)
		# userId = None
		# if not ValidateUtils.isNull(sessionKey):
		# 	userId = UserService.getUserId(sessionKey)
		# 	dtos =LiveService.gift(userId, None, None, None, None, start, limit)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def live(request):
	id = request.POST.get('id')
	uid = request.POST.get('uid')
	name = request.POST.get('name')
	startDay = request.POST.get('startDay')
	endDay = request.POST.get('endDay')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	adminSessionKey = request.POST.get('adminSessionKey')
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', '')
	response = None
	res = Res()
	try:
		dtos=[]
		total=0
		if not ValidateUtils.isNull(adminSessionKey):
			adminId = AdminService.getUserId(adminSessionKey)
			if ValidateUtils.isNull(adminId):
				raise ServiceException("登陆失效")
			op=11
			AdminService.hasOp(adminId, op)
			startDate=None
			endDate=None
			if not ValidateUtils.isNull(startDay):
				startDate = startDay
			if not ValidateUtils.isNull(endDay):
				endDate = endDay
			dtos = LiveService.live(uid, id, name, startDate, endDate, startTime, endTime, start, limit)
			total = LiveService.liveCount(uid, adminId, name, startDate, endDate, startTime, endTime)
		userId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
			dtos = LiveService.live(userId, None, None, None, None, start, limit)
			total = LiveService.liveCount(userId, None, None, None, None)
		res.addResponse("total", total)
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)