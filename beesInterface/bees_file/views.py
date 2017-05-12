#coding:utf-8
from django.shortcuts import render
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from bees_user.userService import UserService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt

# Create your views here.
@csrf_exempt
def uploadFiles(request):
	""" generated source for method uploadFiles """
	dataX = request.POST.get('dataX')
	dataY = request.POST.get('dataY')
	dataHeight = request.POST.get('dataHeight')
	dataWidth = request.POST.get('dataWidth')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = 0
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
		#raise ServiceException(u'未实现')
		#filepath = FilesUtil.uploads(request, userId, dataX, dataY, dataWidth, dataHeight) # shq
		filepath = '未实现'
		res.addResponse("urls", filepath)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)