from __future__ import unicode_literals

from django.db import models
import time
from utils.const import Const

# Create your models here.
class Banner(models.Model):
	page = models.CharField(max_length=32)
	position = models.CharField(max_length=64)
	pic_order = models.IntegerField()
	title = models.CharField(max_length=512, blank=True, null=True)
	content = models.CharField(max_length=2048, blank=True, null=True)
	pic_href = models.CharField(max_length=64)
	pic_url = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'banner'

	def getBannerDto(self):
		dto = {}
		dto['id'] = self.id
		dto['title'] = self.title
		dto['url'] = self.pic_href
		dto['imgUrl'] = self.pic_url
		dto['content'] = self.content
		dto['page'] = self.page
		dto['position'] = self.position
		dto['picOrder'] = self.pic_order
		return dto

	def getDict(self):
		dict = {}
		dict['id'] = self.id
		dict['page'] = self.page
		dict['position'] = self.position
		dict['picOrder'] = self.pic_order
		dict['title'] = self.title
		dict['content'] = self.content
		dict['picHref'] = self.pic_href
		dict['picUrl'] = self.pic_url
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