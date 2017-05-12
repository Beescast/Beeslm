#coding:utf-8

from django.shortcuts import render
from django.http import HttpResponse,JsonResponse

import sys
sys.path.append('..')
from utils.res import Res
from exception.serviceException import ServiceException
from noticeService import NoticeService
from bees_notice.models import Notice
from bees_admin.adminService import AdminService
from django.views.decorators.csrf import csrf_exempt
from utils.validateUtils import ValidateUtils

# Create your views here.
# 公告详情
# /notice/info
@csrf_exempt
def info(request):
	id = request.POST.get('id')
	if not id:
		id = request.GET.get('id')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		notice = NoticeService.getInfoById(id)
		if notice:
			res.addResponse('result', notice.getDict())
		else:
			res.addResponse('result', None)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, 'index', callback, response)

@csrf_exempt
def list(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	status = request.POST.get('status')
	title = request.POST.get('title')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
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
			op = 6
			AdminService.hasOp(adminId, op)
		else:
			status = '1'
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		notices = NoticeService.getList(title, status, startDate, endDate, start, limit)
		count = NoticeService.getListCount(title, status, startDate, endDate)
		res.addResponse("total", count)
		res.addResponse("resultList", notices)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	title = request.POST.get('title')
	content = request.POST.get('content')
	status = request.POST.get('status')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		op = 7
		AdminService.hasOp(adminId, op)
		result = NoticeService.add(adminId, title, content, status)
		res.addResponse('result', result)
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
			raise ServiceException('登陆失败')
		op = 7
		AdminService.hasOp(adminId, op)
		result = NoticeService.delete(id)
		res.addResponse("result", result)
	except Exception, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id=request.POST.get('id')
	title = request.POST.get('title')
	status = request.POST.get('status')
	content = request.POST.get('content')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException('登陆失败')
		op = 7
		AdminService.hasOp(adminId, op)
		result = NoticeService.edit(id,title,content,status)
		res.addResponse('result', result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, 'index', callback, response)









