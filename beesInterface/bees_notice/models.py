from __future__ import unicode_literals

from django.db import models
import time
from utils.const import Const

# Create your models here.
class Notice(models.Model):
	create_id = models.IntegerField()
	title = models.CharField(max_length=32)
	status = models.IntegerField()
	content = models.CharField(max_length=2048)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'notice'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['createId'] = self.create_id
		dict['title'] = self.title
		dict['status'] = self.status
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

	def getNoticeDto(self):
		dto = {}
		dto['id'] = self.id
		dto['title'] = self.title
		dto['status'] = self.status
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		return dto
