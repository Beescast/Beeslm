#coding=utf-8
from __future__ import unicode_literals

import time
from django.db import models
from bees_user.models import User
from utils.const import Const

# Create your models here.
class Packages(models.Model):
	name = models.CharField(max_length=256)
	title_one = models.CharField(max_length=256)
	title_two = models.CharField(max_length=256)
	title_three = models.CharField(max_length=256)
	pic = models.CharField(max_length=256)
	content = models.TextField(blank=True, null=True)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'packages'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['name'] = self.name
		dict['titleOne'] = self.title_one
		dict['titleTwo'] = self.title_two
		dict['titleThree'] = self.title_three
		dict['pic'] = self.pic
		dict['content'] = self.content
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

class PackagesOrder(models.Model):
	id = models.BigIntegerField(primary_key=True)
	uid = models.IntegerField()
	package_id = models.IntegerField()
	package_name = models.CharField(max_length=256)
	package_price_id = models.IntegerField()
	package_pic = models.CharField(max_length=512)
	price = models.CharField(max_length=64)
	status = models.IntegerField()
	pay_type = models.IntegerField()
	submit_time = models.DateTimeField()
	pay_time = models.DateTimeField()
	start_time = models.DateTimeField()
	end_time = models.DateTimeField()
	comment = models.CharField(max_length=1024)
	comment_time = models.DateTimeField(blank=True, null=True)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'packages_order'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['uid'] = self.uid
		dict['packageId'] = self.package_id
		dict['packageName'] = self.package_name
		dict['packagePriceId'] = self.package_price_id
		try:
			packages = Packages.objects.get(id=self.package_id)
			dict['packagePic'] = packages.pic
		except Exception, e:
			dict['packagePic'] = None
		dict['price'] = self.price
		dict['status'] = self.status
		dict['payType'] = self.pay_type
		if self.submit_time:
			dict['submitTime'] = int(time.mktime(self.submit_time.timetuple())*1000)
		else:
			dict['submitTime'] = None
		if self.pay_time:
			dict['payTime'] = int(time.mktime(self.pay_time.timetuple())*1000)
		else:
			dict['payTime'] = None
		if self.start_time:
			dict['startTime'] = int(time.mktime(self.start_time.timetuple())*1000)
		else:
			dict['startTime'] = None
		if self.end_time:
			dict['endTime'] = int(time.mktime(self.end_time.timetuple())*1000)
		else:
			dict['endTime'] = None
		dict['comment'] = self.comment
		if self.comment_time:
			dict['commentTime'] = int(time.mktime(self.comment_time.timetuple())*1000)
		else:
			dict['commentTime'] = None
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

	def getPackagesOrderDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		try:
			user = User.objects.get(id=self.uid)
			dto['name'] = user.nickname
			dto['mobile'] = user.mobile
		except Exception, e:
			dto['name'] = None
			dto['mobile'] = None

		dto['packageName'] = self.package_name
		dto['price'] = self.price
		if self.pay_type == 0:
			dto['payTypeString'] = '钱包'
		if self.pay_type == 1:
			dto['payTypeString'] = '支付宝'
		if self.pay_type == 2:
			dto['payTypeString'] = '微信'
		if self.pay_type == -1:
			dto['payTypeString'] = '未支付'
		dto['payType'] = self.pay_type
		dto['type'] = None
		dto['status'] = self.status
		if self.status == 0:
			dto['statusString'] = '待付款'
		if self.status == 1:
			dto['statusString'] = '待启动'
		if self.status == 2:
			dto['statusString'] = '上线中'
		if self.status == 3:
			dto['statusString'] = '已结束'
		if self.status == 4:
			dto['statusString'] = '已评价'
		if self.status == 9:
			dto['statusString'] = '已取消'
		dto['comment'] = self.comment
		if self.comment_time:
			dto['commentTime'] = int(time.mktime(self.comment_time.timetuple())*1000)
		else:
			dto['commentTime'] = None
		if self.submit_time:
			dto['submitTime'] = int(time.mktime(self.submit_time.timetuple())*1000)
		else:
			dto['submitTime'] = None
		if self.pay_time:
			dto['payTime'] = int(time.mktime(self.pay_time.timetuple())*1000)
		else:
			dto['payTime'] = None
		if self.start_time:
			dto['startTime'] = int(time.mktime(self.start_time.timetuple())*1000)
		else:
			dto['startTime'] = None
		if self.end_time:
			dto['endTime'] = int(time.mktime(self.end_time.timetuple())*1000)
		else:
			dto['endTime'] = None
		return dto

class PackagesPrice(models.Model):
	package_id = models.IntegerField()
	name = models.CharField(max_length=256)
	price = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'packages_price'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['packageId'] = self.package_id
		dict['name'] = self.name
		dict['price'] = self.price
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