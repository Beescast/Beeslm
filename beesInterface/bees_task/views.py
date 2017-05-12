#coding=utf-8
from django.shortcuts import render
from django.http import HttpResponse
from utils.res import Res
from exception.serviceException import ServiceException
from taskService import TaskService
from utils.validateUtils import ValidateUtils
from bees_user.userService import UserService
from bees_admin.adminService import AdminService
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
		task = TaskService.getInfoById(id)
		res.addResponse("result", task.getDict())
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def list(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	status = request.POST.get('status')
	sign = request.POST.get('sign')
	catId = request.POST.get('catId')
	bidStatus = request.POST.get('bidStatus')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
			Tasks = TaskService.getList(userId,status,sign,catId,bidStatus,start,limit)
			total = TaskService.getListCount(userId,status,sign,catId,bidStatus)
			res.addResponse("total", total)
			res.addResponse("resultList", Tasks)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def addSign(request):
	taskId = request.POST.get('taskId')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		result = TaskService.addSign(userId, taskId)
		res.addResponse("result", result)

	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def add(request):
	catId = request.POST.get('catId')
	type = request.POST.get('type')
	title = request.POST.get('title')
	price = request.POST.get('price')
	num = request.POST.get('num')
	notice = request.POST.get('notice')
	msg = request.POST.get('msg')
	illustration = request.POST.get('illustration')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=8
		AdminService.hasOp(adminId, op)
		result = TaskService.add(adminId, title, catId, type, price, num, illustration, notice, msg)
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
		op=8
		AdminService.hasOp(adminId, op)
		result = TaskService.delete(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def edit(request):
	id = request.POST.get('id')
	catId = request.POST.get('catId')
	type = request.POST.get('type')
	title = request.POST.get('title')
	price = request.POST.get('price')
	num = request.POST.get('num')
	illustration = request.POST.get('illustration')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=8
		AdminService.hasOp(adminId, op)
		result = TaskService.edit(id, title, catId, type, price, num, illustration)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def catList(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	gt = request.POST.get('gt')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		tasks = TaskService.getCatList(start, limit, gt)
		dtos = []
		for task in tasks:
			dtos.append(task.getDict())
		res.addResponse("resultList", dtos)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def selectList(request):
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId=None
		if not ValidateUtils.isNull(sessionKey):
			userId = UserService.getUserId(sessionKey)
		tasks = TaskService.getcatList(None, None, 1)
		typeLists = []
		type = {}
		for taskCategory in tasks:
			type1 = {}
			type1["title"] = taskCategory.name
			type1["id"] = taskCategory.id
			typeLists.append(type1)
		type["title"] = "任务类型"
		type["list"] = typeLists
		results = []
		status = {}
		status["title"] = "任务状态"
		statusLists = []
		statusList1 = {}
		statusList2 = {}
		statusList3 = {}
		statusList1["title"] = "全部任务"
		statusList2["title"] = "招募中"
		statusList2["id"] = 0
		statusList3["title"] = "招募完成"
		statusList3["id"] = 1
		statusLists.append(statusList1)
		statusLists.append(statusList2)
		statusLists.append(statusList3)
		status["list"] = statusLists
		results.append(status)
		if not ValidateUtils.isNull(userId):
			bind = {}
			status["title"] = "报名状态"
			bindLists = []
			bindList1 = []
			bindList2 = []
			bindList3 = []
			bindList1["title"] = "全部状态"
			bindList2["title"] = "已报名"
			bindList2["id"] = 1
			bindList3["title"] = "未报名"
			bindList3["id"] = 0
			bindLists.append(bindList1)
			bindLists.append(bindList2)
			bindLists.append(bindList3)
			bind["list"] = bindLists
			results.append(bind)
		results.append(type)
		res.addResponse("resultList", results)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def addCat(request):
	name = request.POST.get('name')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		if not UserService.isAdmin(adminSessionKey):
			raise ServiceException("没有权限")
		result = TaskService.addCat(userId, name)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def delCat(request):
	id = request.POST.get('id')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		if not UserService.isAdmin(adminSessionKey):
			raise ServiceException("没有权限")
		result = TaskService.delCat(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def editCat(request):
	id = request.POST.get('id')
	name = request.POST.get('name')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		if not UserService.isAdmin(adminSessionKey):
			raise ServiceException("没有权限")
		result = TaskService.editCat(id, name)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def adminList(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	id = request.POST.get('id')
	status = request.POST.get('status')
	type = request.POST.get('type')
	catId = request.POST.get('catId')
	title = request.POST.get('title')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		tasks = TaskService.adminList(id, title, status, type, catId, startDate, endDate, start, limit)
		total = TaskService.adminListCount(id, title, status, type, catId, startDate, endDate)
		dtos = []
		for task in tasks:
			dtos.append(task.getDict())
		res.addResponse("resultList", dtos)
		res.addResponse("total", total)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def getSignList(request):
	limit = request.POST.get('limit')
	start = request.POST.get('start')
	id = request.POST.get('id')
	taskId = request.POST.get('taskId')
	catId = request.POST.get('catId')
	title = request.POST.get('title')
	bidStatus = request.POST.get('bidStatus')
	payStatus = request.POST.get('payStatus')
	startTime = request.POST.get('startTime')
	endTime = request.POST.get('endTime')
	timeType = request.POST.get('timeType')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		startDate = None
		endDate = None
		if not ValidateUtils.isNull(startTime):
			startDate = startTime
		if not ValidateUtils.isNull(endTime):
			endDate = endTime
		tasks = TaskService.getSignList(id, title, catId, taskId, bidStatus, payStatus, startDate, endDate, timeType, start, limit)
		res.addResponse("total", TaskService.getSignListCount(id, title, catId, taskId, bidStatus, payStatus, startDate, endDate, timeType))
		res.addResponse("resultList", tasks)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def getSignPicList(request):
	taskId = request.POST.get('taskId')
	uid = request.POST.get('uid')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		pics = TaskService.getSignPicList(taskId, uid)
		res.addResponse("resultList", pics)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def finishTask(request):
	taskId = request.POST.get('taskId')
	pics = request.POST.get('pics[]')
	sessionKey = request.POST.get('sessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		userId = UserService.getUserId(sessionKey)
		if ValidateUtils.isNull(userId):
			raise ServiceException("登陆失效")
		result = TaskService.finishTask(userId, taskId, pics)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def sign(request):
	taskId = request.POST.get('taskId')
	uids = request.POST.get('uids')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=9
		AdminService.hasOp(adminId, op)
		result = TaskService.sign(taskId, uids)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def unsign(request):
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
		op=9
		AdminService.hasOp(adminId, op)
		result = TaskService.unsign(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def auth(request):
	id = request.POST.get('id')
	status = request.POST.get('status')
	reson = request.POST.get('reson')
	adminSessionKey = request.POST.get('adminSessionKey')
	formatType = request.POST.get('formatType', 'json')
	callback = request.POST.get('callback', None)
	response = HttpResponse()
	res = Res()
	try:
		adminId = AdminService.getUserId(adminSessionKey)
		if ValidateUtils.isNull(adminId):
			raise ServiceException("登陆失效")
		op=10
		AdminService.hasOp(adminId, op)
		result = TaskService.auth(adminId, id, status, reson)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)

@csrf_exempt
def finish(request):
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
		op=10
		AdminService.hasOp(adminId, op)
		result = TaskService.finish(id)
		res.addResponse("result", result)
	except ServiceException, e:
		res.addError(e.getMessage())
	return res.toView(formatType, "index", callback, response)
