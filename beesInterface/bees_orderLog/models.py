from __future__ import unicode_literals

from django.db import models
from bees_user.models import User
import time
from utils.const import Const

# Create your models here.
class OrderLog(models.Model):
	uid = models.IntegerField()
	type = models.IntegerField()
	pay_type = models.IntegerField()
	income_type = models.IntegerField()
	money = models.CharField(max_length=64)
	business_id = models.CharField(max_length=64)
	order_id = models.CharField(max_length=64)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'order_log'

	def getOrderLogDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		dto['type'] = self.type
		try:
			user = User.objects.get(id=self.uid)
			dto['mobile'] = user.mobile
			dto['nickname'] = user.nickname
			dto['payType'] = self.pay_type
			dto['incomeType'] = self.income_type
			if self.add_time:
				dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
			else:
				dto['addTime'] = None
			dto['businessId'] = self.business_id
			dto['orderId'] = self.order_id
		except Exception, e:
			dto['mobile'] = None
			dto['nickname'] = None
			dto['payType'] = None
			dto['incomeType'] = None
			dto['addTime'] = None
			dto['businessId'] = None
			dto['orderId'] = None
		dto['money'] = self.money
		return dto