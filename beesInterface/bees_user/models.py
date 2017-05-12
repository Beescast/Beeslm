#coding:utf-8
from __future__ import unicode_literals
import time
from django.db import models
from utils.mobileCode import MobileCode
from utils.const import Const

# Create your models here.
class User(models.Model):
	mobile = models.CharField(max_length=16)
	passwd = models.CharField(max_length=32)
	pay_passwd = models.CharField(max_length=32)
	nickname = models.CharField(max_length=128)
	avatar = models.CharField(max_length=128)
	balance = models.CharField(max_length=32)
	reg_time = models.DateTimeField()
	truename = models.CharField(max_length=128)
	id_card = models.CharField(max_length=32)
	front_pic = models.CharField(max_length=128)
	back_pic = models.CharField(max_length=128)
	hand_pic = models.CharField(max_length=128)
	plat = models.CharField(max_length=16)
	live_room = models.CharField(max_length=32)
	plat_pic = models.CharField(max_length=128)
	auth_status = models.IntegerField()
	auth_time = models.DateTimeField()
	auth_opid = models.IntegerField()
	email = models.CharField(max_length=128)
	contact_mobile = models.CharField(max_length=32)
	contact_address = models.CharField(max_length=1024)
	agency_name = models.CharField(max_length=128)
	agency_mobile = models.CharField(max_length=32)
	account_name = models.CharField(max_length=128)
	bank_name = models.CharField(max_length=1024)
	sub_bank_name = models.CharField(max_length=1024)
	bank_card = models.CharField(max_length=128)
	task_cat = models.CharField(max_length=256)
	qq_id = models.CharField(max_length=64)
	weixin_id = models.CharField(max_length=64)
	description = models.CharField(max_length=2048)
	note = models.CharField(max_length=2048)
	cat_num = models.IntegerField()
	sign_num = models.IntegerField()
	finish_num = models.IntegerField()
	balance_num = models.CharField(max_length=32)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'user'

	def getUserDto(self):
		dto = {}
		dto['id'] = self.id
		dto['mobile'] = self.mobile
		dto['passwd'] = self.passwd
		dto['payPasswd'] = self.pay_passwd
		dto['nickname'] = self.nickname
		dto['avatar'] = self.avatar
		dto['balance'] = self.balance
		if self.reg_time:
			dto['regTime'] = int(time.mktime(self.reg_time.timetuple())*1000)
		else:
			dto['regTime'] = None
		dto['truename'] = self.truename
		dto['idCard'] = self.id_card
		dto['frontPic'] = self.front_pic
		dto['backPic'] = self.back_pic
		dto['handPic'] = self.hand_pic
		dto['plat'] = self.plat
		dto['liveRoom'] = self.live_room
		dto['platPic'] = self.plat_pic
		dto['authStatus'] = self.auth_status
		if self.auth_time:
			dto['authTime'] = int(time.mktime(self.auth_time.timetuple())*1000)
		else:
			dto['authTime'] = None
		dto['authOpid'] = self.auth_opid
		dto['email'] = self.email
		dto['contactMobile'] = self.contact_mobile
		dto['contactAddress'] = self.contact_address
		dto['agencyName'] = self.agency_name
		dto['agencyMobile'] = self.agency_mobile
		dto['accountName'] = self.account_name
		dto['bankName'] = self.bank_name
		dto['subBankName'] = self.sub_bank_name
		dto['bankCard'] = self.bank_card
		dto['taskCat'] = self.task_cat
		dto['qqId'] = self.qq_id
		dto['weixinId'] = self.weixin_id
		dto['description'] = self.description
		dto['note'] = self.note
		dto['catNum'] = self.cat_num
		dto['signNum'] = self.sign_num
		dto['finishNum'] = self.finish_num
		dto['balanceNum'] = self.balance_num
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		if self.update_time:
			dto['updateTime'] = int(time.mktime(self.update_time.timetuple())*1000)
		else:
			dto['updateTime'] = None
		dto['delFlag'] = self.del_flag
		return dto

	def getUserInfoDto(self):
		dto = {}
		dto['id'] = self.id
		dto['mobile'] = self.mobile
		dto['nickname'] = self.nickname
		dto['avatar'] = self.avatar
		dto['truename'] = self.truename
		if self.pay_passwd != '':
			dto['payPass'] = 1
		else:
			dto['payPass'] = None
		if self.task_cat != '':
			dto['tasks'] = self.task_cat.split(',')
		else:
			dto['tasks'] = None
		dto['idCard'] = self.id_card
		dto['frontPic'] = self.front_pic
		dto['backPic'] = self.back_pic
		dto['handPic'] = self.hand_pic
		dto['plat'] = self.plat
		dto['liveRoom'] = self.live_room
		dto['platPic'] = self.plat_pic
		if self.auth_time:
			dto['authTime'] = int(time.mktime(self.auth_time.timetuple())*1000)
		else:
			dto['authTime'] = None
		dto['authStatus'] = self.auth_status
		dto['email'] = self.email
		dto['contactMobile'] = self.contact_mobile
		dto['contactAddress'] = self.contact_address
		dto['agencyName'] = self.agency_name
		dto['agencyMobile'] = self.agency_mobile
		dto['accountName'] = self.account_name
		dto['bankName'] = self.bank_name
		dto['subBankName'] = self.sub_bank_name
		dto['bankCard'] = self.bank_card
		dto['description'] = self.description
		dto['note'] = self.note
		dto['balance'] = self.balance
		dto['qqId'] = self.qq_id
		dto['weixinId'] = self.weixin_id
		return dto

class UserAuth(models.Model):
	mobile = models.CharField(max_length=16)
	reg_time = models.DateTimeField()
	truename = models.CharField(max_length=128)
	id_card = models.CharField(max_length=32)
	front_pic = models.CharField(max_length=128)
	back_pic = models.CharField(max_length=128)
	hand_pic = models.CharField(max_length=128)
	plat = models.CharField(max_length=16)
	live_room = models.CharField(max_length=32)
	plat_pic = models.CharField(max_length=128)
	auth_status = models.IntegerField()
	auth_time = models.DateTimeField()
	auth_opid = models.IntegerField()
	email = models.CharField(max_length=128)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'user_auth'

	def getDict(self):
		dto = {}
		dto['id'] = self.id
		dto['mobile'] = self.mobile
		if self.reg_time:
			dto['regTime'] = int(time.mktime(self.reg_time.timetuple())*1000)
		else:
			dto['regTime'] = None
		dto['truename'] = self.truename
		dto['idCard'] = self.id_card
		dto['frontPic'] = self.front_pic
		dto['backPic'] = self.back_pic
		dto['handPic'] = self.hand_pic
		dto['plat'] = self.plat
		dto['liveRoom'] = self.live_room
		dto['platPic'] = self.plat_pic
		dto['authStatus'] = self.auth_status
		if self.auth_time:
			dto['authTime'] = int(time.mktime(self.auth_time.timetuple())*1000)
		else:
			dto['authTime'] = None
		dto['authOpid'] = self.auth_opid
		dto['email'] = self.email
		if self.add_time:
			dto['addTime'] = int(time.mktime(self.add_time.timetuple())*1000)
		else:
			dto['addTime'] = None
		if self.update_time:
			dto['updateTime'] = int(time.mktime(self.update_time.timetuple())*1000)
		else:
			dto['updateTime'] = None
		dto['delFlag'] = self.del_flag
		return dto

class UserPic(models.Model):
	uid = models.IntegerField()
	pic = models.CharField(max_length=128)
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'user_pic'

	def getUserPicDto(self):
		dto = {}
		dto['id'] = self.id
		dto['mobile'] = self.mobile
		dto['nickname'] = self.nickname
		dto['avatar'] = self.avatar
		dto['truename'] = self.truename
		dto['payPass'] = self.pay_pass
		dto['tasks'] = self.tasks
		dto['idCard'] = self.idCard
		dto['frontPic'] = self.frontPic
		dto['backPic'] = self.backPic
		dto['handPic'] = self.handPic
		dto['plat'] = self.plat
		dto['liveRoom'] = self.liveRoom
		dto['platPic'] = self.platPic
		dto['authTime'] = self.authTime
		dto['authStatus'] = self.authStatus
		dto['email'] = self.email
		dto['contactMobile'] = self.contactMobile
		dto['contactAddress'] = self.contactAddress
		dto['agencyName'] = self.agencyName
		dto['agencyMobile'] = self.agencyMobile
		dto['accountName'] = self.accountName
		dto['bankName'] = self.bankName
		dto['subBankName'] = self.subBankName
		dto['bankCard'] = self.bankCard
		dto['description'] = self.description
		dto['note'] = self.note
		dto['balance'] = self.balance
		dto['qqId'] = self.qqId
		dto['weixinId'] = self.weixinId
		return dto

	def getMobileCode(self):
		mobileCode= MobileCode.MobileCode()
		dto = {}
		dto['mobile']=self.mobile   #手机号码
		dto['code']=self.code	   #验证码信息
		dto['expires']=self.expires #过期时间
		return mobileCode

	# def getUserAuthdto(ua):
	#	 dto = {}
	#	 dto['id'] = ua.id
	#	 dto['mobile'] = ua.mobile
	#	 dto['regTime'] = ua.regTime
	#	 dto['truename'] = ua.truename
	#	 dto['idCard'] = ua.idCard
	#	 dto['frontPic'] = ua.frontPic
	#	 dto['backPic'] = ua.backPic
	#	 dto['handPic'] = ua.handPic
	#	 dto['plat'] = ua.plat
	#	 dto['liveRoom'] = ua.liveRoom
	#	 dto['platPic'] = ua.platPic
	#	 dto['authTime'] = ua.authTime
	#	 dto['authStatus'] = ua.authStatus
	#	 dto['email'] = ua.email
	#	 dto['authOpid'] = ua.authOpid
	#	 dto['addTime'] = ua.addTime
	#	 dto['updateTime'] = ua.updateTime
	#	 dto['delFlag'] = ua.delFlag
	#	 dto['serialVersionUID'] = 1L
	#	 return dto


