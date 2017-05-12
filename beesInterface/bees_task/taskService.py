#coding=utf-8

from models import Task, TaskCategory, TaskSign, TaskSignPic
from utils.validateUtils import ValidateUtils
from utils.const import Const
from bees_notice.models import Notice
from bees_user.models import User
from bees_user.userService import UserService
from bees_wallet.models import WalletTurnover
from exception.serviceException import ServiceException
import django.utils.timezone as timezone

class TaskService:
	@staticmethod
	def getInfoById(id):
		try:
			return Task.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except Exception, e:
			return None

	@staticmethod
	def add(userId, title, catId, type, price, num, illustration, notice, msg):
		task = Task()
		task.title = title
		if ValidateUtils.isNull(catId):
			task.cat_id = 0
		else:
			task.cat_id = catId
		if ValidateUtils.isNull(type):
			task.type = 0
		else:
			task.type = type
		task.price = price
		task.num = num
		task.illustration = illustration
		task.save()
		if not ValidateUtils.isNull(notice) and notice == '1':
			notice = Notice()
			notice.create_id = userId
			notice.title = title+u'任务已发布，请速速报名！'
			notice.content = ''
			notice.status = 1
			notice.save()
		users = User.objects.filter(task_cat=catId)
		if not ValidateUtils.isNull(users) and len(users)>0:
			for user in users:
				user.cat_num += 1
				user.save()
				# if not ValidateUtils.isNull(msg) and msg==1:
				# 	SMSUtil.doPost(user.mobile, 10, String.valueOf(Double.valueOf(price)/Double.valueOf(num)), None)
		return True

	@staticmethod
	def delete(id):
		task  = TaskService.getInfoById(id)
		users = User.objects.filter(task_cat=task.cat_td)
		if not ValidateUtils.isNull(users) and users.size()>0:
			for user in users:
				user.cat_num -= 1
				user.save()
		try:
			Task.objects.get(id=id).delete()
			return True
		except Exception, e:
			return False

	@staticmethod
	def edit(id, title, catId, type, price, num, illustration):
		try:
			task = Task.objects.get(id=id)
			task.title = title
			task.cat_id = catId
			task.type = type
			task.price = price
			task.num = num
			task.illustration = illustration
			task.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def getList(userId, status, sign, catId, bidStatus,  start,  limit):
		# 用户已报名的task
		taskIds = []
		signs = None
		if not ValidateUtils.isNull(userId) and userId>0:
			signs = TaskSign.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, uid=userId)
			if not ValidateUtils.isNull(bidStatus) and bidStatus == '1':
				signs = signs.exclude(bid_status=0)
			for taskSign in signs:
				taskIds.append(taskSign.task_id)
		filterTasks = None
		if not ValidateUtils.isNull(sign):
			filterTasks=taskIds
		catIds = []
		if not ValidateUtils.isNull(catId) and catId==-1 and not ValidateUtils.isNull(userId):
			user = UserService.getInfoById(userId)
			taskCats = user.task_cat.split(",")
			for cat in taskCats:
				catIds.append(int(cat))
		type = Const.TASK_TYPE_NOMAL
		if not ValidateUtils.isNull(bidStatus):
			type = None
		tasks = Task.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(sign):
			if len(taskIds) > 0:
				if sign == 0:
					tasks = tasks.exclude(id__in=taskIds)
				else:
					tasks = tasks.filter(id__in=taskIds)
			else:
				if sign == 1:
					tasks = tasks.filter(id=-1)
		# if not ValidateUtils.isNull(title):
		# 	tasks = tasks.filter(title__contains=title)
		if not ValidateUtils.isNull(type):
			tasks = tasks.filter(type=type)
		if not ValidateUtils.isNull(status):
			tasks = tasks.filter(status=status)
		if not ValidateUtils.isNull(catId):
			if catId == -1:
				tasks = tasks.filter(cat_id__in=catIds)
			else:
				tasks = tasks.filter(cat_id=catId)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			tasks = tasks[start:]
		else:
			limit = int(limit)
			tasks = tasks[start: start+limit]
		categories = TaskCategory.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, id__in=[-1,0]).order_by('-id')
		dtos = []
		for task in tasks:
			dto = task.getTaskDto()
			dto['startTime'] = DateUtils.toDateFormat(task.add_time, "yyyy-MM-dd HH:ss")
			dto['status'] = task.status
			dto['typeText'] = task.type
			if task.id in taskIds:
				dto['sign'] = 1
			for cat in categories:
				if cat.id == task.cat_id:
					dto['catName'] = cat.name
			if not ValidateUtils.isNull(bidStatus) and bidStatus.intValue()==1:
				for taskSign in signs:
					if taskSign.task_id == task.id:
						dto['payStatusText'] = taskSign.payStatus
			dtos.append(dto)
		return dtos

	@staticmethod
	def getCatList(start, limit, gt):
		taskCategorys = TaskCategory.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, id__in=[-1,0]).order_by('-id')
		if not ValidateUtils.isNull(gt):
			taskCategorys = taskCategorys.filter(id__gte=0)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			taskCategorys = taskCategorys[start:]
		else:
			limit = int(limit)
			taskCategorys = taskCategorys[start: start+limit]
		return taskCategorys

	@staticmethod
	def addCat(userId, name):
		taskCategory = TaskCategory()
		# taskCategory.add_time = datetime.datetime.now()
		taskCategory.name = name
		taskCategory.save()
		return True

	@staticmethod
	def delCat(id):
		try:
			TaskCategory.objects.get(id=id).delete()
			return True
		except Exception, e:
			return False

	@staticmethod
	def editCat(id, name):
		try:
			taskCategory = TaskCategory.objects.get(id=id)
			taskCategory.name = name
			taskCategory.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def adminList(id, title, status, type, catId, startDate, endDate, start, limit):
		tasks = Task.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			tasks = tasks.filter(id=id)
		if not ValidateUtils.isNull(title):
			tasks = tasks.filter(title__contains=title)
		if not ValidateUtils.isNull(type):
			tasks = tasks.filter(type=type)
		if not ValidateUtils.isNull(status):
			tasks = tasks.filter(status=status)
		if not ValidateUtils.isNull(catId):
			tasks = tasks.filter(cat_id=catId)
		if not ValidateUtils.isNull(startDate):
			tasks = tasks.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			tasks = tasks.filter(add_time__lte=endDate)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			tasks = tasks[start:]
		else:
			limit = int(limit)
			tasks = tasks[start: start+limit]
		return tasks

	@staticmethod
	def getSignList(id, title, catId, taskId, bidStatus, payStatus, startDate, endDate, timeType, start, limit):
		taskIds = []
		if not ValidateUtils.isNull(catId) or not ValidateUtils.isNull(title):
			tasks = Task.objects.filter(cat_id=catId, title__contains=title, del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(tasks):
				for task in tasks:
					taskIds.append(task.id)
		if not ValidateUtils.isNull(taskId):
			taskIds.append(taskId)
		else:
			if len(taskIds) == 0:
				taskIds = None
		taskSigns = TaskSign.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			taskSigns = taskSigns.filter(id=id)
		if not ValidateUtils.isNull(bidStatus):
			taskSigns = taskSigns.exclude(bid_status=0)
		if not ValidateUtils.isNull(payStatus):
			taskSigns = taskSigns.filter(pay_status=payStatus)
		if not ValidateUtils.isNull(taskIds):
			taskSigns = taskSigns.filter(task_id__in=taskIds)
		if not ValidateUtils.isNull(catId):
			taskSigns = taskSigns.filter(cat_id=catId)
		if not ValidateUtils.isNull(startDate) or not ValidateUtils.isNull(endDate):
			if timeType == 'bid':
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(bid_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(bid_time__lte=endDate)
			elif timeType == 'pay':
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(pay_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(pay_time__lte=endDate)
			elif timeType == 'op':
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(op_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(op_time__lte=endDate)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			taskSigns = taskSigns[start:]
		else:
			limit = int(limit)
			taskSigns = taskSigns[start: start+limit]
		dtos = []
		for taskSign in taskSigns:
			dto = taskSign.getTaskSignDto()
			dtos.append(dto)
		return dtos

	@staticmethod
	def sign(taskId, uids):
		uidsString = uids.split(",")
		task = TaskService.getInfoById(taskId)
		if task.num-task.bid_num<len(uidsString):
			raise ServiceException("中标人数超过总人数")
		price = str(float(task.price)/task.num)
		uid = []
		for string in uidsString:
			uid.append(int(string))
		if task.getType().intValue() == Const.TASK_TYPE_NOMAL:
			taskSigns = TaskSign.objects.filter(uid__in=uid, task_id=taskId)
			for taskSign in taskSigns:
				taskSign.single_price = price
				taskSign.bid_status = Const.BID_STATUS_BID
				taskSign.bid_time = datetime.datetime.now()
				taskSign.save()
			# 统计
			for integer in uid:
				user = UserService.getInfoById(integer)
				user.sign_num += 1
				user.save()
				# SMSUtil.doPost(user.mobile, 7, price, None)
		else:
			signs = TaskSign.objects.filter(task_id=taskId, uid__in=uid, del_flag=Const.DEL_FLAG_NOT_DEL)
			if ValidateUtils.isNull(signs) or len(signs.size) == 0:
				for uidInt in uid:
					taskSign = TaskSign()
					taskSign.task_id = taskId
					taskSign.uid = uidInt
					taskSign.single_price = price
					taskSign.bid_status = Const.TASK_BID_YES
					taskSign.bid_time = datetime.datetime.now()
					# taskSign.add_time = datetime.datetime.now()
					taskSign.save()
					user = UserService.getInfoById(uidInt)
					user.sign_num += 1
					user.save()
					# SMSUtil.doPost(user.mobile, 7, price, None)
			else:
				raise ServiceException("该用户已报名："+signs[0].uid)
		TaskService.updateBidNum(taskId)
		return True

	@staticmethod
	def updateBidNum(taskId):
		count = TaskSign.objects.filter(task_id=taskId, bid_status=Const.BID_STATUS_BID).count()
		task = TaskService.getInfoById(taskId)
		task.bid_num = count
		if task.num == count:
			task.status = Const.TASK_STATUS_ONE
		else:
			task.status = Const.TASK_STATUS_ZERO
		task.save()

	@staticmethod
	def updateSignNum(taskId):
		count = TaskSign.objects.filter(task_id=taskId).count()
		task = Task()
		task.id = taskId
		task.sign_num = count
		task.save()

	@staticmethod
	def addSign(userId, taskId):
		task = TaskService.getInfoById(taskId)
		if not ValidateUtils.isNull(task) and task.status == Const.TASK_STATUS_ZERO:
			tasksign = TaskSign.objects.filter(task_id=taskId, uid=userId, del_flag=Const.DEL_FLAG_NOT_DEL)
			if ValidateUtils.isNull(tasksign):
				try:
					taskSign = TaskSign()
					tasksign.task_id = taskId
					taskSign.uid = userId
					taskSign.single_price = str(float(task.price)/task.num)
					TaskService.updateSignNum(taskId)
					return True
				except Exception, e:
					return False
			else:
				raise ServiceException("您已报名")
		return False

	@staticmethod
	def auth(adminId, id, status, reson):
		sign = None
		try:
			sign = TaskSign.objects.get(id=id)
		except Exception, e:
			raise False
		if not ValidateUtils.isNull(sign):
			if sign.bid_status == Const.BID_STATUS_BID:
				sign.op_id = adminId
				sign.op_time = datetime.datetime.now()
				try:
					user = UserService.getInfoById(sign.uid)
					if status == Const.TASK_AUTH_YES:
						# 通过按钮将把交付状态改为已结束，
						# 该用户的钱包余额加上本次赏金金额，
						# 在该用户流水中加入一条赏金任务收益的记录，
						# 发送一条任务完成的短信通知给用户，检查该任务中标的人数是否达到招募的人数要求，如果达到则该任务的状态为招募完成
						# 用户总金额，完成任务总数
						sign.setPayStatus(Const.TASK_PAY_TWO)
						currentBalence = str(float(user.balance)+float(sign.singlePrice))
						user.money = currentBalence
						user.finish_num += 1
						user.balance_num = str(float(sign.single_price())+float(user.balance_num))
						user.save()
						walletTurnover = WalletTurnover()
						walletTurnover.uid = sign.uid
						walletTurnover.business_no = sign.task_id
						walletTurnover.business_type = 4
						walletTurnover.pay_type = 1
						walletTurnover.money = sign.single_price
						walletTurnover.current_balance = currentBalence
						walletTurnover.third_order_id = ''
						walletTurnover.reseaon = ''
						# walletTurnover.add_time = datetime.datetime.now()
						walletTurnover.save()
						# SMSUtil.doPost(user.mobile, 8, sign.getSinglePrice(), None)
					else:
						# 不通过按钮，必须添加不通过的理由，
						# 把中标者的状态改为取消中标，交付状态改为未完成，发送一条任务未完成的通知给用户（含未通过的原因），
						# 同时，如果当前任务的状态改为招募中
						sign.bid_status = Const.BID_STATUS_UNBID
						sign.pay_status = Const.TASK_PAY_THREE
						sign.reason = reson
						# SMSUtil.doPost(user.mobile, 9, reson, None)
						task = TaskService.getInfoById(sign.task_id)
						user.sign_num -= 1
						if task.status == Const.TASK_STATUS_ONE:
							task.status = Const.TASK_STATUS_ZERO
							task.save()
				except Exception, e:
					print e
				sign.save()
				TaskService.updateBidNum(sign.getTaskId())
				return True
			else:
				raise ServiceException("该用户未中标")
		else:
			raise ServiceException("错误的id")

	@staticmethod
	def getListCount(userId, status, sign, catId, bidStatus):
		# 用户已报名的task
		taskIds = []
		if not ValidateUtils.isNull(userId) and userId>0:
			signs = TaskSign.objects.filter(uid=userId, del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if bidStatus == 1:
				signs = signs.exclude(bid_status=0)
			for taskSign in signs:
				taskIds.append(taskSign.task_id)
		catIds = []
		if not ValidateUtils.isNull(catId) and catId==-1 and not ValidateUtils.isNull(userId):
			user = UserService.getInfoById(userId)
			taskCats = user.task_cat().split(",")
			for cat in taskCats:
				catIds.add(int(cat))
		tasks = Task.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, type=Const.TASK_TYPE_NOMAL)
		if not ValidateUtils.isNull(sign):
			if len(taskIds) > 0:
				if sign == 0:
					tasks = tasks.exclude(id__in=taskIds)
				elif sign == 1:
					tasks = tasks.filter(id__in=taskIds)
			else:
				if sign == 1:
					tasks = tasks.filter(id=-1)
		if not ValidateUtils.isNull(status):
			tasks = tasks.filter(status=status)
		if not ValidateUtils.isNull(catId):
			if catId == -1:
				tasks = tasks.filter(cat_id__in=catIds)
			else:
				tasks = tasks.filter(cat_id=catId)
		return tasks.count()

	@staticmethod
	def adminListCount(id, title, status, type, catId, startDate, endDate):
		tasks = Task.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(id):
			tasks = tasks.filter(id=id)
		if not ValidateUtils.isNull(title):
			tasks = tasks.filter(title__contains=title)
		if not ValidateUtils.isNull(type):
			tasks = tasks.filter(type=type)
		if not ValidateUtils.isNull(status):
			tasks = tasks.filter(status=status)
		if not ValidateUtils.isNull(catId):
			tasks = tasks.filter(cat_id=catId)
		if not ValidateUtils.isNull(startDate):
			tasks = tasks.filter(start_date__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			tasks = tasks.filter(end_date__lte=endDate)
		return tasks.count()

	@staticmethod
	def getSignListCount(id, title, catId, taskId, bidStatus, payStatus, startDate, endDate, timeType):
		taskIds = []
		if not ValidateUtils.isNull(catId) or not ValidateUtils.isNull(title):
			tasks = Task.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, title__contains=title, cat_id=catId)
			if not ValidateUtils.isNull(tasks):
				for task in tasks:
					taskIds.append(task.id)
		if not ValidateUtils.isNull(taskId):
			taskIds.append(taskId)
		else:
			if len(taskIds) == 0:
				taskIds=None
		taskSigns = TaskSign.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(id):
			taskSigns = taskSigns.filter(id=id)
		if not ValidateUtils.isNull(taskIds):
			taskSigns = taskSigns.filter(task_id__in=taskIds)
		if not ValidateUtils.isNull(bidStatus):
			taskSigns = taskSigns.filter(bid_status=bidStatus)
		if not ValidateUtils.isNull(payStatus):
			taskSigns = taskSigns.filter(pay_status=payStatus)
		if not ValidateUtils.isNull(startDate) or not ValidateUtils.isNull(endDate):
			if timeType == "bid":
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(bid_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(bid_time__lte=endDate)
			elif timeType == "pay":
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(pay_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(pay_time__lte=endDate)
			elif timeType == "op":
				if not ValidateUtils.isNull(startDate):
					taskSigns = taskSigns.filter(op_time__gte=startDate)
				if not ValidateUtils.isNull(endDate):
					taskSigns = taskSigns.filter(op_time__lte=endDate)
		return taskSigns.count()

	@staticmethod
	def unsign(id):
		try:
			taskSign = TaskSign.objects.get(id=id)
			taskSign.bid_status = Const.BID_STATUS_UNBID
			taskSign.pay_status = Const.TASK_PAY_THREE
			taskSign.save()
			TaskService.updateBidNum(taskSign.task_id)
			return True
		except Exception, e:
			return False

	@staticmethod
	def finish(id):
		try:
			task = Task.objects.get(id=id)
			task.status = Const.TASK_STATUS_ONE
			task.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def finishTask(userId, taskId, pics):
		sign = None
		try:
			sign = TaskSign.objects.get(task_id=taskId, uid=userId)
		except Exception, e:
			pass
		if ValidateUtils.isNull(sign):
			raise ServiceException("您未中标些任务")
		if pics.length < 3:
			raise ServiceException("请至少上传3张图片")
		sign.pay_status = 1
		sign.save()
		for pic in pics:
			signPic = TaskSignPic()
			signPic.pic_url(pic)
			# signPic.add_time = datetime.datetime.now()
			signPic.task_id(taskId)
			signPic.user_id(userId)
			signPic.save()
		return True

	@staticmethod
	def getSignPicList(taskId, uid):
		signPics = SignPic.objects.filter(task_id=taskId, user_id=uid)
		return signPics
