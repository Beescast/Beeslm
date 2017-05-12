from __future__ import unicode_literals

from django.db import models
import time
from bees_user.models import User
from utils.const import Const

# Create your models here.
class Market(models.Model):
	uid = models.IntegerField()
	dates = models.DateField()
	times = models.IntegerField()
	shows = models.CharField(max_length=64)
	click = models.CharField(max_length=64)
	visit_area = models.CharField(max_length=64)
	new_visit = models.CharField(max_length=64)
	old_visit = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'market'

	def getMarketDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		try:
			user = User.objects.get(id=self.uid)
			dto['name'] = user.truename
		except Exception, e:
			dto['name'] = None
		if self.dates:
			dto['dates'] = int(time.mktime(self.dates.timetuple())*1000)
		else:
			dto['dates'] = None
		dto['times'] = self.times
		dto['shows'] = self.shows
		dto['click'] = self.click
		dto['visitArea'] = self.visit_area
		dto['newVisit'] = self.new_visit
		dto['oldVisit'] = self.old_visit
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		return dto

class Live(models.Model):
	uid = models.IntegerField()
	dates = models.DateField()
	times = models.IntegerField()
	focus = models.IntegerField()
	popular = models.IntegerField()
	gift_num = models.IntegerField()
	gift_total = models.CharField(max_length=32)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'live'

	def getLiveDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		try:
			user = User.objects.get(id=self.uid)
			dto['name'] = user.truename
		except Exception, e:
			dto['name'] = None
		if self.dates:
			dto['dates'] = int(time.mktime(self.dates.timetuple())*1000)
		else:
			dto['dates'] = None
		dto['times'] = self.times
		dto['focus'] = self.focus
		dto['popular'] = self.popular
		dto['giftNum'] = self.gift_num
		dto['giftTotal'] = self.gift_total
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		return dto

class LiveGift(models.Model):
	live_id = models.IntegerField()
	name = models.CharField(max_length=64)
	num = models.IntegerField()
	money = models.CharField(max_length=64)
	dates = models.DateField()
	times = models.IntegerField()
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'live_gift'

	def getLiveGiftDto(self):
		dto = {}
		dto['id'] = self.id
		try:
			live = Live.objects.get(id=self.live_id)
			user = User.objects.get(id=live.uid)
			dto['username'] = user.username
			dto['uid'] = user.id
		except Exception, e:
			dto['username'] = None
			dto['uid'] = None
		dto['name'] = self.name
		if self.dates:
			dto['dates'] = int(time.mktime(self.dates.timetuple())*1000)
		else:
			dto['dates'] = None
		dto['times'] = self.times
		dto['num'] = self.num
		dto['money'] = self.money
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		return dto

class LivePeople(models.Model):
	uid = models.IntegerField()
	dates = models.DateField()
	visit_type = models.CharField(max_length=64)
	age = models.IntegerField()
	sex = models.IntegerField()
	focus = models.CharField(max_length=64)
	most_type = models.IntegerField()
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'live_people'

	def getLivePeopleDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		try:
			user = User.objects.get(id=self.uid)
			dto['name'] = user.truename
		except Exception, e:
			dto['name'] = None
		if self.dates:
			dto['dates'] = int(time.mktime(self.dates.timetuple())*1000)
		else:
			dto['dates'] = None
		dto['visitType'] = self.visit_type
		dto['age'] = self.age
		dto['sex'] = self.sex
		dto['focus'] = self.focus
		dto['mostType'] = self.most_type
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		return dto