#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from utils.validateUtils import ValidateUtils
from bees_user.userService import UserService
from bees_package.packagesService import PackagesService
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def list(request):
	start = request.POST.get('start', '0')
	limit = request.POST.get('limit', '10')
	order = request.POST.get('order')
	pid = request.POST.get('pid')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		if ValidateUtils.isNull(pid):
			notices = PackagesService.getCommentList(start, limit, order)
			res.addResponse("resultList", notices)
		else:
			comments = PackagesService.getCommentsByPid(pid)
			res.addResponse("resultList", comments)
		total = PackagesService.getCommentCount(pid)
		res.addResponse("total", total)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def addComment(request):
	orderId = request.POST.get('orderId')
	intro = request.POST.get('intro')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		result = PackagesService.addComment(userId, orderId, comment)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
