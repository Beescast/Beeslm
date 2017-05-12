
from __future__ import unicode_literals
from models import Link
from utils.const import Const
from utils.validateUtils import ValidateUtils

from django.db import models

class LinkService(object):
	@staticmethod
	def getInfoById(id):
		link = None
		try:
			link = Link.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None
		return link

	@staticmethod
	def getList(start, limit):
		links = Link.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			links = links[start:]
		else:
			limit = int(limit)
			links = links[start:start+limit]
		dtos = []
		for link in links:
			dtos.append(link.getLinkDto())
		return dtos

	@staticmethod
	def add(title, href, orders):
		link = Link()
		link.title = title
		link.href = href
		link.orders = orders
		link.save()
		return True

	@staticmethod
	def delete(id):
		link = None
		try:
			link = Link.objects.get(id=id)
		except:
			return False
		link.delete()
		return True

	@staticmethod
	def edit(id, title, href, orders):
		link = None
		try:
			link = Link.objects.get(id=id)
		except:
			return False
		link.title = title
		link.href = href
		link.orders = orders
		link.save()
		return True

	@staticmethod
	def getListCount():
		return Link.objects.count()
