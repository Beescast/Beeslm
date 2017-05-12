#coding:utf-8
from __future__ import unicode_literals
import django.utils.timezone as timezone
from utils.res import Res
from exception.serviceException import ServiceException
from bees_package.models import Packages, PackagesOrder, PackagesPrice
from utils.validateUtils import ValidateUtils
from bees_user.userService import UserService
from utils.const import Const
from bees_orderLog.models import OrderLog
from bees_wallet.models import WalletTurnover
from bees_user.models import User
import random

class OrderService(object):

	@staticmethod
	def getInfoById(id):
		try:
			return PackagesOrder.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None

	@staticmethod
	def getList(uid, id, payType, status, startDate, endDate, pid, start, limit):
		packagesOrders = PackagesOrder.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-add_time')
		if not ValidateUtils.isNull(uid):
			packagesOrders = packagesOrders.filter(uid=uid)
		if not ValidateUtils.isNull(pid):
			packagesOrders = packagesOrders.filter(package_id=pid)
		if not ValidateUtils.isNull(id):
			packagesOrders = packagesOrders.filter(id=id)
		if not ValidateUtils.isNull(payType):
			packagesOrders = packagesOrders.filter(pay_type=payType)
		if not ValidateUtils.isNull(status):
			packagesOrders = packagesOrders.filter(status=status)
		if not ValidateUtils.isNull(startDate):
			packagesOrders = packagesOrders.filter(submit_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			packagesOrders = packagesOrders.filter(submit_time__lte=endDate)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			packagesOrders = packagesOrders[start:]
		else:
			limit = int(limit)
			packagesOrders = packagesOrders[start: start+limit]
		dtos = []
		for packagesOrder in packagesOrders:
			dto = packagesOrder.getPackagesOrderDto()
			dtos.append(dto)
		return dtos

	@staticmethod
	def add(userId, packageId, packagePriceId):
		packages = Packages.objects.get(packageId=packageId)
		price = PackagesPrice.objects.get(packagePriceId=packagePriceId)
		order = PackagesOrder()
		order.id = OrderService.createOrderId()
		# order.add_time = datetime
		order.uid = userId
		order.package_id=packageId
		order.package_price_id=packagePriceId
		order.package_name = packages.name+"/"+price.name
		order.price=price.price
		order.submit_time = timezone.now()
		result = PackagesOrder(order)
		res = Res()
		res.addResponse("result", result)
		res.addResponse("orderId", order.id)
		return res

	@staticmethod
	def delete(id, userId):
		order = PackagesOrder.objects.get(id=id)
		try:
			if order.id != userId:
				raise ServiceException("您无权操作该订单")
			else:
				order.delete(id)
		except:
			return False
		return True

	@staticmethod
	def realDel(id):
		try:
			order = PackagesOrder.objects.get(id=id)
			if order.status != Const.ORDER_STATUS_ZERO:
				raise ServiceException("订单状态不正确")
			else:
				order.delete()
			return True
		except:
			return False

	@staticmethod
	def createOrderId(self):
		dateString = datetime.time() #写法错误，暂时搁置
		rnd = random
		randString = rnd.random(89999) + 10000 + ""
		return dateString + randString

	@staticmethod
	def startPackage(id, endDates):
		order = None
		try:
			order = PackagesOrder.objects.get(id=id)
		except Exception, e:
			return False
		if order.status != Const.ORDER_STATUS_ONE:
			raise ServiceException("订单状态不正确")
		order.status = Const.ORDER_STATUS_TWO
		order.start_time = timezone.now()
		order.end_time = endDates
		if not order.comment_time:
			order.comment_time = timezone.now()
		order.save()
		try:
			user = User.objects.get(id=order.uid)
			# SMSUtil.doPost(user.mobile, 4, DateUtils.toDateFormat(endDates, "yyyy-MM-dd"), None)
			return True
		except Exception, e:
			return False

	@staticmethod
	def getListCount(uid, id, payType, status, startDate, endDate, pid):
		packagesOrders = PackagesOrder.objects.all()
		if not ValidateUtils.isNull(uid):
			packagesOrders = packagesOrders.filter(uid=uid)
		if not ValidateUtils.isNull(pid):
			packagesOrders = packagesOrders.filter(package_id=pid)
		if not ValidateUtils.isNull(id):
			packagesOrders = packagesOrders.filter(id=id)
		if not ValidateUtils.isNull(payType):
			packagesOrders = packagesOrders.filter(pay_type=payType)
		if not ValidateUtils.isNull(status):
			packagesOrders = packagesOrders.filter(status=status)
		if not ValidateUtils.isNull(startDate):
			packagesOrders = packagesOrders.filter(submit_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			packagesOrders = packagesOrders.filter(submit_time__lte=endDate)
		return packagesOrders.count()

	@staticmethod
	def pay(orderId,payType,userId,balance,money,thirdOrderId):
		res = PackagesOrder.pay(orderId, payType)
		if res and payType == 0:
			try:
				user = UserService.getUserById(userId)
				user.money = balance
				user.save()
			except Exception, e:
				return False
		orderLog = OrderLog()
		orderLog.uid = userId
		orderLog.type = 1
		orderLog.pay_type = payType
		orderLog.income_type = 1
		orderLog.money = money
		orderLog.order_id = thirdOrderId
		orderLog.business_id = str(orderId)
		orderLog.save()
		if payType ==0:
			try:
				walletTurnover = WalletTurnover()
				walletTurnover.uid = userId
				walletTurnover.business_type = 3
				walletTurnover.pay_type = 2
				walletTurnover.money = money
				walletTurnover.current_balance = balance
				walletTurnover.business_no = str(orderId)
				walletTurnover.third_order_id = thirdOrderId
				walletTurnover.reseaon = ''
				walletTurnover.save()
			except Exception, e:
				return False
		return True





