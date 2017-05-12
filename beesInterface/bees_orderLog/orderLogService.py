#coding:utf-8
from __future__ import unicode_literals
from models import OrderLog
from utils.validateUtils import ValidateUtils
from bees_user.models import User
from utils.const import Const

class OrderLogService(object):

	@staticmethod
	def getInfoById(id):
		try:
			return OrderLog.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None

	@staticmethod
	def getList(uid, type, incomeType, mobile,nickname, startDate, endDate, startMoney, endMoney, orderId, start, limit):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(nickname):
			users = users.filter(nick_name__contains=nickname)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		orderLogs = OrderLog.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(uids):
			orderLogs = orderLogs.filter(uid__in=uids)
		if not ValidateUtils.isNull(type):
			orderLogs = orderLogs.filter(type=type)
		if not ValidateUtils.isNull(incomeType):
			orderLogs = orderLogs.filter(income_type=incomeType)
		if not ValidateUtils.isNull(startDate):
			orderLogs = orderLogs.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			orderLogs = orderLogs.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(startMoney):
			orderLogs = orderLogs.filter(money__gte=startMoney)
		if not ValidateUtils.isNull(endMoney):
			orderLogs = orderLogs.filter(money__lte=endMoney)
		if not ValidateUtils.isNull(orderId):
			orderLogs = orderLogs.filter(order_id=orderId)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			orderLogs = orderLogs[start:]
		else:
			limit = int(limit)
			orderLogs = orderLogs[start: start+limit]
		dtos=[]
		for orderLog in orderLogs:
			dtos.append(orderLog.getOrderLogDto())
		return dtos

	@staticmethod
	def delete(id):
		try:
			orderlog = OrderLog.objects.get(id=id)
			orderlog.delete()
		except:
			return False
		return True

	@staticmethod
	def getListCount(uid, type, incomeType, mobile, nickname, startDate, endDate, startMoney, endMoney, orderId):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(nickname):
			users = users.filter(nick_name__contains=nickname)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		orderLogs = OrderLog.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(uids):
			orderLogs = orderLogs.filter(uid__in=uids)
		if not ValidateUtils.isNull(type):
			orderLogs = orderLogs.filter(type=type)
		if not ValidateUtils.isNull(incomeType):
			orderLogs = orderLogs.filter(income_type=incomeType)
		if not ValidateUtils.isNull(startDate):
			orderLogs = orderLogs.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			orderLogs = orderLogs.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(startMoney):
			orderLogs = orderLogs.filter(money__gte=startMoney)
		if not ValidateUtils.isNull(endMoney):
			orderLogs = orderLogs.filter(money__lte=endMoney)
		if not ValidateUtils.isNull(orderId):
			orderLogs = orderLogs.filter(order_id=orderId)
		return orderLogs.count()