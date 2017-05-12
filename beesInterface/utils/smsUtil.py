#coding=utf-8
from utils.globalConfigure import GlobalConfigure
from utils.validateUtils import ValidateUtils
import requests

class SMSUtil:

	# 发送短信
	#
	# @ param name			用户名
	# @ param pwd			密码
	# @ param mobileString	电话号码字符串，中间用英文逗号间隔
	# @ param contextString	内容字符串
	# @ param sign			签名
	# @ param stime			追加发送时间，可为空，为空为及时发送
	# @ param extno			扩展码，必须为数字 可为空
	# @ return
	# @ throws Exception
	@staticmethod
	def doPost(mobileString, type, contextString, stime):
		params = {}
		params['name'] = GlobalConfigure.SMS_NAME
		params['pwd'] = GlobalConfigure.SMS_PASSWORD
		params['mobile'] = mobileString
		params['content'] = SMSUtil.getContext(type, contextString)
		if not ValidateUtils.isNull(stime):
			params['stime'] = stime
		params['sign'] = '晴蜂科技'
		params['type'] = 'pt'
		# params['extno'] = extno
		url = 'http://web.wasun.cn/asmx/smsservice.aspx'
		try:
			r = requests.post(url, data=params)
			if r.status_code >= 300:
				raise Exception("HTTP Request is not success, Response code is " + r.status_code)
			return r.text
		except Exception, e:
			return None

	@staticmethod
	def getContext(type, contextString):
		context = ''
		if type == 1:
			context = "您本次操作的验证码为：" + contextString
		elif type == 2:
			con = contextString.split(",")
			context = "您已通过Bees主播认证，您的账号为：" + con[0] + "，密码为：" + con[1] + "，登录后请更改密码，官网www.beeslm.com。"
		elif type == 3:
			context = "您因【" + contextString + "】未通过Bees主播认证，请到官网www.beeslm.com重新申请。"
		elif type == 4:
			context = "您购买的广告套餐已启动，结束时间为：" + contextString + "。"
		elif type == 5:
			context="您的提现申请已经处理，请到银行查收。"
		elif type == 6:
			context = "您的提现申请未通过，请到官网www.beeslm.com查看。"
		elif type == 7:
			context = "您已中标一笔赏金任务，赏金：" + contextString + "，速去官网www.beeslm.com完成任务。"
		elif type == 8:
			context = "您的任务已完成，赏金（" + contextString + "）已发放，请到官网www.beeslm.com查看个人钱包。"
		elif type == 9:
			context = "您的任务因【" + contextString + "】未完成，请到官网www.beeslm.com查看。"
		elif type == 10:
			context = "有一笔赏金任务适合您，赏金：" + contextString + "元，请到官网www.beeslm.com查看。"
		elif type == 11:
			context = "您已修改网站密码，如非本人操作，请联系客服400-900-9090。"
		return context
