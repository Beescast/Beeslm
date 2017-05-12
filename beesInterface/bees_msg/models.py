from __future__ import unicode_literals

from django.db import models
import time
from bees_user.models import User
from utils.const import Const

# Create your models here.
class LeaveMsg(models.Model):
	uid = models.IntegerField()
	name = models.CharField(max_length=128)
	content = models.CharField(max_length=2048)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'leave_msg'

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['uid'] = self.uid
		try:
			user = User.objects.get(id=self.uid)
			dict['name'] = user.nickname
		except Exception, e:
			dict['name'] = self.name
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