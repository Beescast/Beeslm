#coding=utf-8
# Session中的手机验证码信息

class MobileCode:
	def __init__(self):
		self.mobile = None#手机号码
		self.code = None#验证码信息
		self.expires = None#过期时间

	def getMobile(self):
		return self.mobile

	def setMobile(self, mobile):
		self.mobile = mobile

	def getCode(self):
		return self.code

	def setCode(self, code):
		self.code = code

	def getExpires(self):
		return self.expires

	def setExpires(self, expires):
		self.expires = expires
