# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
import time
from utils.const import Const

# Create your models here.
class Product(models.Model):
	name = models.CharField(max_length=128)
	pic = models.CharField(max_length=256)
	href = models.CharField(max_length=512)
	position = models.IntegerField()
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'product'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['name'] = self.name
		dict['pic'] = self.pic
		dict['href'] = self.href
		dict['position'] = self.position
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