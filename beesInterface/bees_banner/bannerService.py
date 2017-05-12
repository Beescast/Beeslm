# conding=utf-8
from models import Banner
from utils.validateUtils import ValidateUtils
from utils.const import Const

class BannerService:
	@staticmethod
	def getInfoById(id):
		banner = None
		try:
			banner = Banner.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except:
			return None
		return banner

	@staticmethod
	def getList(page, position, start, limit):
		banners = Banner.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(page):
			banners = banners.filter(page=page)
		if not ValidateUtils.isNull(position):
			banners = banners.filter(position=position)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			banners = banners[start:]
		else:
			limit = int(limit)
			banners = banners[start:start+limit]
		dtos = []
		for banner in banners:
			dtos.append(banner.getBannerDto())
		return dtos

	@staticmethod
	def add(userId, title, content, page, position, picOrder, picHref, picUrl):
		banner = Banner()
		banner.id = userId
		banner.title = title
		banner.content = content
		banner.page = page
		banner.position = position
		banner.pic_order = picOrder
		banner.pic_href = picHref
		banner.pic_url = picUrl
		banner.save()
		return True

	@staticmethod
	def delete(id):
		banner = None
		try:
			banner = Banner.objects.get(id=id)
		except:
			return False
		banner.delete()
		return True

	@staticmethod
	def edit(id, title, content, page, position, picOrder, picHref, picUrl):
		banner = None
		try:
			banner = Banner.objects.get(id=id)
		except:
			return False
		banner.title = title
		banner.content = content
		banner.page = page
		banner.position = position
		banner.pic_order = picOrder
		banner.pic_href = picHref
		banner.pic_url = picUrl
		banner.save()
		return True

	@staticmethod
	def getListCount(page, position):
		return Banner.objects.filter(page=page, position=position).count()