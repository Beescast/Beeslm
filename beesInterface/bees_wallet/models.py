#coding=utf-8
from __future__ import unicode_literals

from django.db import models
from utils.const import Const
import time
from bees_user.models import User
from bees_admin.models import Admin
from utils.const import Const

# Create your models here.
class WalletCash(models.Model):
	uid = models.IntegerField()
	money = models.CharField(max_length=64)
	status = models.IntegerField()
	apply_time = models.DateTimeField()
	op_id = models.IntegerField()
	op_time = models.DateTimeField()
	third_order_id = models.CharField(max_length=128)
	reseaon = models.CharField(max_length=512)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'wallet_cash'

	def getWalletDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		dto['money'] = self.money
		if self.apply_time:
			dto['applyTime'] = int(time.mktime(self.apply_time.timetuple())*1000)
		else:
			dto['applyTime'] = None
		dto['status'] = self.status
		if self.op_id != 0:
			try:
				admin = Admin.objects.get(id=self.op_id)
				dto['opName'] = admin.op_name
			except Exception, e:
				dto['opName'] = None
		else:
			dto['opName'] = None
		if self.op_time:
			dto['opTime'] = int(time.mktime(self.op_time.timetuple())*1000)
		else:
			dto['opTime'] = None
		try:
			user = User.objects.get(id=self.uid)
			dto['mobile'] = user.mobile
			dto['nickname'] = user.nickname
			dto['accountName'] = user.account_name
			dto['bankName'] = user.bank_name
			dto['subBankName'] = user.sub_bank_name
		except Exception, e:
			dto['mobile'] = None
			dto['nickname'] = None
			dto['accountName'] = None
			dto['bankName'] = None
			dto['subBankName'] = None
		return dto

class WalletRecharge(models.Model):
	id = models.BigAutoField(primary_key=True)
	uid = models.IntegerField()
	money = models.CharField(max_length=32)
	status = models.IntegerField()
	order_id = models.CharField(max_length=64)
	pay_type = models.IntegerField()
	pay_time = models.DateTimeField()
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)

	class Meta:
		db_table = 'wallet_recharge'

class WalletTurnover(models.Model):
	uid = models.IntegerField()
	business_type = models.IntegerField()
	pay_type = models.IntegerField()
	money = models.CharField(max_length=64)
	current_balance = models.CharField(max_length=64)
	business_no = models.CharField(max_length=64)
	third_order_id = models.CharField(max_length=128)
	reseaon = models.CharField(max_length=512)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'wallet_turnover'

	def getWalletTurnoverDto(self):
		dto = {}
		dto['id'] = self.id
		dto['uid'] = self.uid
		if self.business_type == Const.BUSINESS_TYPE_ONE:
			dto['businessTypeText'] = '充值'
		if self.business_type == Const.BUSINESS_TYPE_TWO:
			dto['businessTypeText'] = '提现'
		if self.business_type == Const.BUSINESS_TYPE_THREE:
			dto['businessTypeText'] = '购买套餐'
		if self.business_type == Const.BUSINESS_TYPE_FOUR:
			dto['businessTypeText'] = '任务收益'
		if self.business_type == Const.BUSINESS_TYPE_FIVE:
			dto['businessTypeText'] = '提现不通过'
		dto['businessType'] = self.business_type
		dto['money'] = self.money
		if self.pay_type == Const.PAY_TYPE_ONE:
			dto['payTypeText'] = '+'
		if self.pay_type == Const.PAY_TYPE_TWO:
			dto['payTypeText'] = '-'
		dto['payType'] = self.pay_type
		dto['currentBalance'] = self.current_balance
		dto['businessNo'] = self.business_no
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		try:
			user = User.objects.get(id=self.uid)
			dto['mobile'] = user.mobile
			dto['nickname'] = user.nickname
		except Exception, e:
			dto['mobile'] = None
			dto['nickname'] = None
		return dto