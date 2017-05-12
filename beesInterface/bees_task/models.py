#coding=utf-8
from __future__ import unicode_literals

import time
from django.db import models
from utils.const import Const
from bees_user.models import User
from bees_admin.models import Admin
from utils.const import Const

# Create your models here.
class Task(models.Model):
	cat_id = models.IntegerField()
	type = models.IntegerField()
	title = models.CharField(max_length=64)
	price = models.CharField(max_length=32)
	num = models.IntegerField()
	illustration = models.CharField(max_length=2048)
	status = models.IntegerField(default=Const.TASK_STATUS_ZERO)
	sign_num = models.IntegerField(default=0)
	bid_num = models.IntegerField(default=0)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'task'

	def getTaskDto(self):
		dto = {}
		dto['id'] = self.id
		dto['title'] = self.title
		dto['price'] = self.price
		dto['num'] = self.num
		dto['illustration'] = self.illustration
		dto['startTime'] = self.startTime
		if self.payStatus == 0:
			dto['payText'] = '待完成'
		if self.payStatus == 1:
			dto['payText'] = '待付款'
		if self.payStatus == 2:
			dto['payText'] = '已完成'
		if self.payStatus == 3:
			dto['payText'] = '未完成'
		if self.status == Const.TASK_STATUS_ZERO:
			dto['statusText'] = '招募中'
		if self.status == Const.TASK_STATUS_ONE:
			dto['statusText'] = '招募完成'
		dto['payStatus'] = self.payStatus
		dto['type'] = self.type
		if self.type == Const.TASK_TYPE_NOMAL:
			dto['typeText'] = '普通任务'
		if self.type == Const.TASK_TYPE_SENIOR:
			dto['typeText'] = '高级任务'
		dto['sign'] = self.sign
		dto['catId'] = self.catId
		dto['catName'] = self.catName
		return dto

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['catId'] = self.cat_id
		dict['type'] = self.type
		dict['title'] = self.title
		dict['price'] = self.price
		dict['num'] = self.num
		dict['illustration'] = self.illustration
		dict['status'] = self.status
		dict['signNum'] = self.sign_num
		dict['bidNum'] = self.bid_num
		if self.add_time:
			dict['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dict['addTime'] = None
		if self.update_time:
			dict['updateTime'] = int(time.mktime(self.update_time.timetuple())*1000)
		else:
			dict['updateTime'] = None
		dict['delFlag'] = self.del_flag
		return dict

class TaskCategory(models.Model):
	name = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'task_category'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['name'] = self.name
		if self.add_time:
			dict['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dict['addTime'] = None
		if self.update_time:
			dict['updateTime'] = int(time.mktime(self.update_time.timetuple())*1000)
		else:
			dict['updateTime'] = None
		dict['delFlag'] = self.del_flag
		return dict

class TaskPic(models.Model):
	task_id = models.CharField(max_length=64)
	uid = models.CharField(max_length=64)
	pic = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'task_pic'

class TaskSign(models.Model):
	task_id = models.IntegerField()
	uid = models.IntegerField()
	bid_status = models.IntegerField()
	bid_time = models.DateTimeField()
	single_price = models.CharField(max_length=64)
	pay_status = models.IntegerField()
	pay_time = models.DateTimeField()
	op_id = models.IntegerField()
	op_time = models.DateTimeField()
	reason = models.CharField(max_length=512)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'task_sign'

	def getTaskSignDto(self):
		user = None
		try:
			user = User.objects.get(id=self.uid)
		except Exception, e:
			pass
		task = None
		try:
			task = Task.objects.get(id=self.task_id)
		except Exception, e:
			pass
		admin = None
		try:
			admin = Admin.objects.get(id=self.op_id)
		except Exception, e:
			pass
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		if user:
			dto['nickname'] = user.nickname
			dto['truename'] = user.truename
			dto['mobile'] = user.mobile
			dto['plat'] = user.plat
			dto['liveRoom'] = user.live_room
		else:
			dto['nickname'] = None
			dto['truename'] = None
			dto['mobile'] = None
			dto['plat'] = None
			dto['liveRoom'] = None
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		dto['singlePrice'] = self.single_price
		dto['bidTime'] = self.bid_time
		if self.bid_time:
			dto['bidTime'] = int(time.mktime(self.bid_time.timetuple())*1000)
		else:
			dto['bidTime'] = None
		dto['bidStatus'] = self.bid_status
		dto['payStatus'] = self.pay_status
		dto['taskId'] = self.task_id
		if self.pay_time:
			dto['payTime'] = int(time.mktime(self.pay_time.timetuple())*1000)
		else:
			dto['payTime'] = None
		if task:
			dto['catId'] = task.cat_id
			dto['type'] = task.type
			dto['title'] = task.title
		else:
			dto['catId'] = None
			dto['type'] = None
			dto['title'] = None
		dto['opId'] = self.op_id
		if self.op_time:
			dto['opTime'] = int(time.mktime(self.op_time.timetuple())*1000)
		else:
			dto['opTime'] = None
		if self.op_id !=0 and admin:
			dto['opName'] = admin.name
		else:
			dto['opName'] = None
		return dto

class TaskSignPic(models.Model):
	task_id = models.IntegerField()
	user_id = models.IntegerField()
	pic_url = models.CharField(max_length=512)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'task_sign_pic'
