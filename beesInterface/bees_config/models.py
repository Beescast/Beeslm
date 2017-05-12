from __future__ import unicode_literals

from django.db import models
from utils.const import Const

# Create your models here.
class Config(models.Model):
	type = models.CharField(max_length=64)
	value = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'config'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['type'] = self.type
		dict['value'] = self.value
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