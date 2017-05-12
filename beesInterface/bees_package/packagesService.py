#coding:utf-8
from models import *
import json
import sys
from utils.const import Const
from bees_package.models import Packages, PackagesOrder, PackagesPrice
from exception.serviceException import ServiceException
from bees_user.userService import UserService
from utils.validateUtils import ValidateUtils
import django.utils.timezone as timezone

sys.path.append('..')

class PackagesService:
	@staticmethod
	def getInfoById(id):
		try:
			return Packages.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None

	@staticmethod
	def getPriceByPid(id):
		packagesPrices = PackagesPrice.objects.filter(package_id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		dtos = []
		for packagesPrice in packagesPrices:
			dtos.append(packagesPrice.getDict())
		return dtos

	@staticmethod
	def getCommentsByPid(pid):
		orders = PackagesOrder.objects.filter(id=0, package_id=pid)
		dtos = []
		for order in orders:
			dto = order.getOrderDto()
			user = UserService.getInfoById(order.uid)
			dto['name'] = user.nickname
			dto['avatar'] = user.avatar
			dtos.append(dto)
		return dtos

	@staticmethod
	def getCommentList(start, limit, order): # order
		orders = PackagesOrder.objects.filter(status=4)
		if order:
			orders = orders.order_by(order)
		if start:
			pass
		else:
			start = 0
		if limit:
			orders = orders[start: start+limit]
		else:
			orders = orders[start:]
		dtos = []
		for tempOrder in orders:
			dto = tempOrder.getOrderDto()
			user = UserService.getInfoById(tempOrder.uid)
			dto['name'] = user.nickname
			dto['imgUrl'] = user.avatar
			dto['roomNo'] = user.liveRoom
			dto['desc'] = user.description
			dto['uid'] = user.uid
			dto['type'] = PackagesOrder.__name__
			dtos.append(dto)
		return dtos

	@staticmethod
	def getPackagesList(start, limit, order):
		if ValidateUtils.isNull(start):
			start = 0
		return ['未实现']
		# if ValidateUtils.isNull(limit):
		# 	return Packages.objects.all()[start:]
		# else:
		# 	return Packages.objects.all()[start: start+limit]
		return dtos

	@staticmethod
	def  add(name, content, titleOne,
			titleTwo, titleThree, pic, prices):
		packages = Packages()
		packages.name = name
		packages.content = content
		packages.titleOne = titleOne
		packages.titleTwo = titleTwo
		packages.titleThree = titleThree
		packages.pic = pic
		packages.save()
		id = packages.id
		priecLists = json.loads(prices)
		for map in priecLists:
			try:
				packagesPrice = PackagesPrice(package_id=id, name=map.name, price=map.price)
				packagesPrice.save()
			except:
				return False
		return True

	@staticmethod
	def delete(id):
		try:
			packages = Packages.objects.get(id=id)
			packages.delete()
		except:
			return False
		return True

	@staticmethod
	def edit(id, name, content, titleOne, titleTwo, titleThree, pic, prices):
		try:
			packages = Packages.objects.get(id=id)
			# packages.add_time = datetime.now()
			packages.name = name
			packages.content = content
			packages.title_one = titleOne
			packages.title_two = titleTwo
			packages.title_three = titleThree
			packages.pic = pic
			packages.save()
		except Exception, e:
			return False
		oldPrices = PackagesPrice.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL, package_id=id)
		ids = []
		if not ValidateUtils.isNull(oldPrices):
			for packagesPrice in oldPrices:
				ids.append(packagesPrice.id)
		# print 'prices:', prices
		priecLists = json.loads(prices)
		for map in priecLists:
			if ValidateUtils.isNull(map.get('id')):
				packagesPrice = PackagesPrice()
				packagesPrice.package_id = id
				# packagesPrice.add_time = datetime.datetime.now()
				packagesPrice.name = map.get('name')
				packagesPrice.price = map.get('price')
				packagesPrice.save()
			else:
				packagesPrice = PackagesPrice.objects.get(id=map.get('id'))
				packagesPrice.name = map.get('name')
				packagesPrice.price = map.get('price')
				packagesPrice.save()
				ids.remove(int(map.get('id')))
		if len(ids) > 0:
			PackagesPrice.objects.filter(id__in=ids).update(del_flag=Const.DEL_FLAG_DEL)
		return True

	@staticmethod
	def getPriceByid(id):
		try:
			return PackagesPrice.objects.get(id=id)
		except:
			return None

	@staticmethod
	def getCommentCount(pid):
		packagesOrders = PackagesOrder.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(pid):
			packagesOrders = packagesOrders.filter(package_id=pid)
		return packagesOrders.count()

	@staticmethod
	def delPrice(id):
		try:
			packagesPrice = PackagesPrice.objects.get(id=id)
			packagesPrice.del_flag = Const.DEL_FLAG_DEL
			packagesPrice.save()
			return True
		except:
			return False

	@staticmethod
	def addComment(userId, orderId, comment):
		try:
			order = PackagesOrder.objects.get(id=orderId)
			if order.uid != userId:
				raise ServiceException("评论失效")
			else:
				order.status = Const.ORDER_STATUS_FOUR
				order.comment = comment
				order.comment_time = timezone.now()
				order.save()
				return True
		except:
			return False
