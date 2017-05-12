#coding:utf-8
from django.shortcuts import render

# Create your views here.
from django.http import HttpResponse,JsonResponse
from utils.res import Res
from exception.serviceException import ServiceException
from utils.cacheService import CacheService
from bees_user.userService import UserService
from utils.validateUtils import ValidateUtils
from django.views.decorators.csrf import csrf_exempt
import urllib2
import json

@csrf_exempt
def login(request):
	mobile = request.POST.get('mobile')
	password = request.POST.get('password')
	code = request.POST.get('code')
	sk = request.POST.get('sk')
	mobileCode = request.POST.get('mobileCode')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		response.setdefault("Access-Control-Allow-Origin", "*")
		if ValidateUtils.isNull(password) and ValidateUtils.isNull(mobileCode):
			raise ServiceException("密码不能为空")
		else:
			print ("auth_CODE_"+sk+":::"+code)
			print ("auth_CODE::"+CacheService.getAttribute("CODE_"+sk).lower())
			print (code.lower())
			if code.lower() == CacheService.getAttribute("CODE_"+sk).lower():
				res = UserService.login(mobile, password, mobileCode, request, response)
			else:
				raise ServiceException("图形验证码不正确")
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def unionLogin(request):
	openid = request.POST.get('openid')
	type = request.POST.get('type')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		res = UserService.unionLogin(openid,type)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def bindUnion(request):
	openid = request.POST.get('openid')
	type = request.POST.get('type')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		uid = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(uid):
			raise ServiceException("用户登陆失效")
		res.addResponse("result", UserService.bindUnion(uid, type, openid))
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def reg(request):
	mobile = request.POST.get('mobile')
	mobileCode = request.POST.get('mobileCode')
	truename = request.POST.get('truename')
	idCard = request.POST.get('idCard')
	frontPic = request.POST.get('frontPic')
	backPic = request.POST.get('backPic')
	handPic = request.POST.get('handPic')
	plat = request.POST.get('plat')
	liveRoom = request.POST.get('liveRoom')
	platPic = request.POST.get('platPic')
	code = request.POST.get('code')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	sessionKey = request.POST.get('sessionKey')
	res = Res()
	response = HttpResponse()
	try:
		res = UserService.register(mobile, mobileCode, truename, idCard, frontPic, backPic, handPic, plat, liveRoom, platPic,code, request, response)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def code(request):
	mobile = request.POST.get('mobile')
	sk = request.POST.get('sk')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	width = 172
	height = 60
	codeCount = 4
	xx = 30
	fontHeight = 55
	codeY = 50
	#图像处理没写
	return HttpResponse('图像处理没写')

@csrf_exempt
def authCode(request):
	mobile = request.POST.get('mobile')
	sk = request.POST.get('sk')
	mobileCode = request.POST.get('mobileCode')
	code = request.POST.get('code')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		if ValidateUtils.isNull(mobile) and ValidateUtils.isNull(mobileCode) and ValidateUtils.isNull(code):
			raise ServiceException("验证码不能为空")
		else:
			if code.lower() == CacheService.getAttribute("CODE_"+sk).lower():
				res = UserService.authCode(mobile, mobileCode, request, response, request.session)
			else:
				raise ServiceException("图形验证码不正确")
	except ServiceException,e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def authMobileCode(request):
	mobile = request.POST.get('mobile')
	sk = request.POST.get('sk')
	mobileCode = request.POST.get('mobileCode')
	code = request.POST.get('code')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		if ValidateUtils.isNull(mobile) and ValidateUtils.isNull(mobileCode):
			raise ServiceException("验证码不能为空")
		else:
			if not ValidateUtils.isNull(code):
				if code.lower() == CacheService.getAttribute("CODE_"+sk).lower():
					raise ServiceException("图形验证码不正确")
		res = UserService.authCode(mobile, mobileCode, request, response, request.session)
	except ServiceException,e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def findPass(request):
	id = request.POST.get('id')
	newPass = request.POST.get('newPass')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		#response.setHeader("Access-Control-Allow-Origin", "*")
		if ValidateUtils.isNull(id) or ValidateUtils.isNull(sessionKey) or ValidateUtils.isNull(newPass):
			raise ServiceException("缺少参数")
		else:
			if CacheService.getAttribute("SESSION_ID_"+sessionKey) == id:
				userId = CacheService.getAttribute("FIND_PASS_" + id)
				if not ValidateUtils.isNull(userId):
					flag = UserService.setPassword(userId, newPass)
				else:
					flag = False
				CacheService.removeAttribute("SESSION_ID_" + sessionKey)
				CacheService.removeAttribute("FIND_PASS_"+id)
			else:
				raise  ServiceException("参数不正确")
			res.addResponse("result", flag)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def getMobileCode(request):
	mobile = request.POST.get('mobile')
	action = request.POST.get('action')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		if ValidateUtils.isNull(mobile) and ValidateUtils.isNull(sessionKey):
			raise ServiceException("缺少参数")
		else:
			userId = 0
			if not ValidateUtils.isNull(sessionKey):
				userId = UserService.getUserId(sessionKey)
			res = UserService.getMobileCode(mobile, action, userId)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def wxlogin(request):
	code = request.POST.get('code')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	res = Res()
	response = None
	try:
		print("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + GlobalConfigure.WEIXIN_APPID + "&secret=" + GlobalConfigure.WEIXIN_SEC + "&code=" + code + "&grant_type=authorization_code")
		resp = urllib2.urlopen("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + GlobalConfigure.WEIXIN_APPID + "&secret=" + GlobalConfigure.WEIXIN_SEC + "&code=" + code + "&grant_type=authorization_code")
		content = resp.read()
		print(content)
		result = json.loads(content)
		openid = result.get("openid")
		res = UserService.unionLogin(openid, "weixin")
	except ServiceException, e:
		print e
	return res.toView(formatType, "index", callback, response)
