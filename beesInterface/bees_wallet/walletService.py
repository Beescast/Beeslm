#coding:utf-8
from models import WalletCash,WalletRecharge,WalletTurnover
from bees_user.models import User,UserPic,UserAuth
from utils.const import Const
from bees_admin.models import Admin
from bees_orderLog.models import OrderLog
from exception.serviceException import ServiceException
from utils.validateUtils import ValidateUtils

class WalletService:

	@staticmethod
	def getList(uid, start, limit):
		wallets = WalletTurnover.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(uid):
			wallets = wallets.filter(uid=uid)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			wallets = wallets[start:]
		else:
			limit = int(limit)
			wallets = wallets[start: start+limit]
		dtos=[]
		for wallet in wallets:
			dtos.append(wallet.getWalletTurnoverDto())
		return dtos

	@staticmethod
	def cash(uid,  money):
		user = None
		try:
			user = User.objects.get(id=id)
		except Exception, e:
			return False
		if float(user.balance) < float(money):
			raise ServiceException("余额不足")
		else:
			cash = WalletCash()
			# cash.add_time = datetime.datetime.now()
			# cash.apply_time = datetime.datetime.now()
			cash.uid=uid
			cash.money=money
			cash.save()
			if not ValidateUtils.isNull(cash):
				balance=str(float(user.balance) - float(money))
				user.money = balance
				user.save()
				walletTurnover = WalletTurnover()
				walletTurnover.uid = user.id
				walletTurnover.business_no = cash.id
				walletTurnover.pay_type = 2
				walletTurnover.money = money
				walletTurnover.current_balance = balance
				walletTurnover.third_order_id = ''
				walletTurnover.reseaon = ''
				# walletTurnover.add_time = datetime.datetime.now()
				walletTurnover.save()
				if ValidateUtils.isNull(cash.id):
					walletTurnover.business_no = walletTurnover.id
					walletTurnover.save()
		return True

	@staticmethod
	def adminList(id, uid, status, accountName, mobile, nickname, startDate,endDate,startMoney,endMoney,start,limit):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(nickname):
			users = users.filter(nick_name__contains=nickname)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		if not ValidateUtils.isNull(accountName):
			users = users.filter(account_name=accountName)
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		wallets = WalletCash.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			wallets = wallets.filter(id=id)
		if not ValidateUtils.isNull(uids):
			wallets = wallets.filter(uid__in=uids)
		if not ValidateUtils.isNull(status):
			wallets = wallets.filter(status=status)
		if not ValidateUtils.isNull(startDate):
			wallets = wallets.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			wallets = wallets.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(startMoney):
			wallets = wallets.filter(money__gte=startMoney)
		if not ValidateUtils.isNull(endMoney):
			wallets = wallets.filter(money__lte=endMoney)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			wallets = wallets[start:]
		else:
			limit = int(limit)
			wallets = wallets[start: start+limit]
		dtos = []
		for walletCash in wallets:
			dtos.append(walletCash.getWalletDto())
		return dtos

	@staticmethod
	def cashOp(adminId, id, status, orderId, reseaon):
		cash = WalletCash()
		# cash.op_time = datetime.datetime.now()
		cash.id = id
		cash.op_id = adminId
		cash.status = status
		if not ValidateUtils.isNull(orderId):
			cash.third_order_id = orderId
		if not ValidateUtils.isNull(reseaon):
			cash.reseaon = reseaon
		cash.save()
		user = None
		try:
			user = User.objects.get(id=cash.uid)
		except Exception, e:
			return False
		if status == '1':
			orderLog = OrderLog()
			orderLog.uid=user.id
			orderLog.type = 3
			orderLog.pay_type = 3
			orderLog.income_type = 2
			orderLog.money = cash.money
			orderLog.business_id = ''
			orderLog.order_id = orderId
			# orderLog.add_time = datetime.datetime.now()
			orderLog.save()
			# SMSUtil.doPost(user.mobile, 5, "", None)
		else:
			balance = str(float(user.balance) + float(cash.money))
			user.money = balance
			user.save()
			walletTurnover = WalletTurnover()
			walletTurnover.uid = user.id
			walletTurnover.business_no = id
			walletTurnover.business_type = 5
			walletTurnover.pay_type = 1
			walletTurnover.money = cash.money
			walletTurnover.current_balance = balance
			walletTurnover.third_order_id = ''
			walletTurnover.reseaon = reseaon
			# walletTurnover.add_time = datetime.datetime.now()
			walletTurnover.save()
			# SMSUtil.doPost(user.mobile, 6, "", None)
		return True

	@staticmethod
	def adminListCount(id, uid, status, accountName, mobile, nickname, startDate,endDate,startMoney,endMoney):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(nickname):
			users = users.filter(nick_name__contains=nickname)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		if not ValidateUtils.isNull(accountName):
			users = users.filter(account_name=accountName)
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		wallets = WalletCash.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(id):
			wallets = wallets.filter(id=id)
		if not ValidateUtils.isNull(uids):
			wallets = wallets.filter(uid__in=uids)
		if not ValidateUtils.isNull(status):
			wallets = wallets.filter(status=status)
		if not ValidateUtils.isNull(startDate):
			wallets = wallets.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			wallets = wallets.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(startMoney):
			wallets = wallets.filter(money__gte=startMoney)
		if not ValidateUtils.isNull(endMoney):
			wallets = wallets.filter(money__lte=endMoney)
		return wallets.count()

	@staticmethod
	def getListCount(uid):
		return WalletTurnover.objects.filter(del_flag = Const.DEL_FLAG_NOT_DEL, uid=uid).count()

	@staticmethod
	def adminTurnOverList(id, uid,payType, mobile, businessNo, startDate, endDate, businessType, start, limit):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		turnovers = WalletTurnover.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			turnovers = turnovers.filter(id=id)
		if not ValidateUtils.isNull(uids):
			turnovers = turnovers.filter(uid__in=uids)
		if not ValidateUtils.isNull(payType):
			turnovers = turnovers.filter(pay_type=payType)
		if not ValidateUtils.isNull(businessNo):
			turnovers = turnovers.filter(business_no=businessNo)
		if not ValidateUtils.isNull(startDate):
			turnovers = turnovers.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			turnovers = turnovers.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(businessType):
			turnovers = turnovers.filter(business_type=businessType)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			turnovers = turnovers[start:]
		else:
			limit = int(limit)
			turnovers = turnovers[start: start+limit]
		dtos=[]
		for walletTurnover in turnovers:
			dtos.append(walletTurnover.getWalletTurnoverDto())
		return dtos

	@staticmethod
	def adminTurnOverCount(id, uid, payType, mobile, businessNo,startDate,endDate,businessType):
		users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(mobile):
			users = users.filter(mobile=mobile)
		if not ValidateUtils.isNull(uid):
			users = users.filter(id=int(uid))
		uids = []
		if not ValidateUtils.isNull(users):
			for user in users:
				uids.append(user.id)
		turnovers = WalletTurnover.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(id):
			turnovers = turnovers.filter(id=id)
		if not ValidateUtils.isNull(uids):
			turnovers = turnovers.filter(uid__in=uids)
		if not ValidateUtils.isNull(payType):
			turnovers = turnovers.filter(pay_type=payType)
		if not ValidateUtils.isNull(businessNo):
			turnovers = turnovers.filter(business_no=businessNo)
		if not ValidateUtils.isNull(startDate):
			turnovers = turnovers.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			turnovers = turnovers.filter(add_time__lte=endDate)
		if not ValidateUtils.isNull(businessType):
			turnovers = turnovers.filter(business_type=businessType)
		return turnovers.count()

	@staticmethod
	def pay(orderId,payType,uid,balance,money,thirdOrderId):
		try:
			recharge = WalletRecharge.objects.get(id=orderId)
			recharge.pay_time = datetime.datetime.now()
			recharge.pay_type = payType
			recharge.save()
		except Exception, e:
			pass
		try:
			user = User.objects.get(id=uid)
			user.balance = balance
			user.save()
		except Exception, e:
			pass
		orderLog = OrderLog()
		orderLog.uid = uid
		orderLog.type = 1
		orderLog.pay_type = payType
		orderLog.income_type = 1
		orderLog.money = money
		orderLog.business_id = orderId
		orderLog.order_id = thirdOrderId
		# orderLog.add_time = datetime.datetime.now()
		orderLog.save()
		over = WalletTurnover()
		over.uid = uid
		over.business_no = ''
		over.business_type = 1
		over.pay_type = payType
		over.money = money
		over.current_balance = balance
		over.third_order_id = thirdOrderId
		over.reseaon = ''
		# over.add_time = datetime.datetime.now()
		over.save()














