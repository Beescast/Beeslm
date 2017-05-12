from __future__ import unicode_literals

from django.db import models
import time
from utils.const import Const

# Create your models here.
class Admin(models.Model):
	name = models.CharField(max_length=64)
	passwd = models.CharField(max_length=64)
	nickname = models.CharField(max_length=64)
	ops = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'admin'

	def getAdminDto(self):
		dto = {}
		dto['id'] = self.id
		dto['title'] = None
		dto['name'] = self.name
		dto['ops'] = self.ops
		dto['url'] = None
		dto['imgUrl'] = None
		dto['content'] = None
		return dto

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['name'] = self.name
		dict['passwd'] = self.passwd
		dict['nickname'] = self.nickname
		dict['ops'] = self.ops
		if self.add_time:
			dict['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dict['addTime'] = None
		dict['updateTime'] = int(time.mktime(self.update_time.timetuple())*1000)
		dict['delFlag'] = self.del_flag
		return dict
