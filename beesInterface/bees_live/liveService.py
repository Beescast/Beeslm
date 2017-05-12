# coding=utf-8
from bees_user.models import User
from exception.serviceException import ServiceException
from utils.validateUtils import ValidateUtils
from models import Market, LivePeople, Live, LiveGift
from utils.const import Const

class LiveService:
	@staticmethod
	def market(uid,id,name,startDate,endDate,startTime,endTime,start,limit):
		uids=[]
		if not ValidateUtils.isNull(name) or (not ValidateUtils.isNull(uid)):
			users = User.objects.all()
			if not ValidateUtils.isNull(name):
				users = users.filter(truename__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=uid)
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		markets = Market.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(uids):
			markets = markets.filter(uid__in=uids)
		if not ValidateUtils.isNull(id):
			markets = markets.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			markets = markets.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			markets = markets.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			markets = markets.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			markets = markets.filter(times__lte=endTime)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			markets = markets[start:]
		else:
			limit = int(limit)
			markets = markets[start: start+limit]
		dtos=[]
		for market in markets:
			dtos.append(market.getMarketDto())
		return dtos

	@staticmethod
	def people(id,uid,name,startDate,endDate,startTime,endTime,start,limit):
		uids=[]
		if not ValidateUtils.isNull(name) or not ValidateUtils.isNull(uid):
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(truename__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=uid)
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		peoples = LivePeople.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(uids):
			peoples = peoples.filter(uid__in=uids)
		if not ValidateUtils.isNull(id):
			peoples = peoples.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			peoples = peoples.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			peoples = peoples.filter(dates__lte=endDate)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			peoples = peoples[start:]
		else:
			limit = int(limit)
			peoples = peoples[start: start+limit]
		dtos=[]
		for people in peoples:
			dtos.append(people.getLivePeopleDto())
		return dtos

	@staticmethod
	def live(id,uid,name,startDate,endDate,startTime,endTime,start,limit):
		uids=[]
		if not ValidateUtils.isNull(name) or not ValidateUtils.isNull(uid):
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(truename__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=uid)
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		lives = Live.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			lives = lives.filter(id=id)
		if not ValidateUtils.isNull(uids):
			lives = lives.filter(uid__in=uids)
		if not ValidateUtils.isNull(startDate):
			lives = lives.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			lives = lives.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			lives = lives.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			lives = lives.filter(times__lte=endTime)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			lives = lives[start:]
		else:
			limit = int(limit)
			lives = lives[start: start+limit]
		dtos=[]
		for live in lives:
			dtos.append(live.getLiveDict())
		return dtos

	@staticmethod
	def marketCount(id,uid,name,startDate,endDate,startTime,endTime):
		uids=[]
		if not ValidateUtils.isNull(name) or (not ValidateUtils.isNull(uid)):
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(truename__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=uid)
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		markets = Market.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(uids):
			markets = markets.filter(uid__in=uids)
		if not ValidateUtils.isNull(id):
			markets = markets.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			markets = markets.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			markets = markets.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			markets = markets.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			markets = markets.filter(times__lte=endTime)
		return markets.count()

	@staticmethod
	def liveCount(id,uid,name,startDate,endDate,startTime,endTime):
		uids=[]
		if not ValidateUtils.isNull(name) or (not ValidateUtils.isNull(uid)):
			users = User.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
			if not ValidateUtils.isNull(name):
				users = users.filter(truename__contains=name)
			if not ValidateUtils.isNull(uid):
				users = users.filter(id=uid)
			if not ValidateUtils.isNull(users):
				for user in users:
					uids.append(user.id)
			else:
				uids.append(-1)
		peoples = LivePeople.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(uids):
			peoples = peoples.filter(uid__in=uids)
		if not ValidateUtils.isNull(id):
			peoples = peoples.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			peoples = peoples.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			peoples = peoples.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			peoples = peoples.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			peoples = peoples.filter(times__lte=endTime)
		return peoples.count()

	@staticmethod
	def gift(id, startDate, endDate, startTime, endTime, start, limit):
		gifts = LiveGift.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(id):
			gifts = gifts.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			gifts = gifts.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			gifts = gifts.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			gifts = gifts.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			gifts = gifts.filter(times__lte=endTime)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			gifts = gifts[start:]
		else:
			limit = int(limit)
			gifts = gifts[start: start+limit]
		dtos=[]
		for gift in gifts:
			dtos.append(gift.getLiveGiftDto())
		return dtos

	@staticmethod
	def giftCount(id, startDate, endDate, startTime, endTime):
		gifts = LiveGift.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(id):
			gifts = gifts.filter(id=id)
		if not ValidateUtils.isNull(startDate):
			gifts = gifts.filter(dates__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			gifts = gifts.filter(dates__lte=endDate)
		if not ValidateUtils.isNull(startTime):
			gifts = gifts.filter(times__gte=startTime)
		if not ValidateUtils.isNull(endTime):
			gifts = gifts.filter(times__lte=endTime)
		return gifts.count()