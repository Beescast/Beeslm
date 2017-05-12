
from __future__ import unicode_literals
from models import LeaveMsg
from bees_user.models import User
from utils.validateUtils import ValidateUtils
from utils.const import Const

from django.db import models
class LeaveMsgService(object):

	@staticmethod
	def getInfoById(id):
		try:
			return LeaveMsg.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None

	@staticmethod
	def getList(uid, name, content, start, limit):
		uids = None
		if not ValidateUtils.isNull(uid) or not ValidateUtils.isNull(name):
			uids = []
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(nick_name__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=int(uid))
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		msgs = LeaveMsg.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(uids):
			msgs = msgs.filter(uid__in=uids)
		if not ValidateUtils.isNull(content):
			msgs = msgs.filter(content__contains=content)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			msgs = msgs[start:]
		else:
			limit = int(limit)
			msgs = msgs[start:start+limit]
		return msgs

	@staticmethod
	def getListCount(uid, name, content):
		uids = None
		if not ValidateUtils.isNull(uid) or not ValidateUtils.isNull(name):
			uids = []
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(nick_name__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=int(uid))
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		msgs = LeaveMsg.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(uids):
			msgs = msgs.filter(uid__in=uids)
		if not ValidateUtils.isNull(content):
			msgs = msgs.filter(content__contains=content)
		return msgs.count()

