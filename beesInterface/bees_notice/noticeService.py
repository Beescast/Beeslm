from models import Notice
from utils.const import Const
from utils.validateUtils import ValidateUtils

class NoticeService:
	def __init__(self):
		pass
	
	@staticmethod
	def getInfoById(id):
		try:
			return Notice.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except Exception, e:
			return None
		
	@staticmethod
	def getList(title, status, startDate, endDate, start, limit):
		notices = Notice.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(title):
			notices = notices.filter(title__contains=title)
		if not ValidateUtils.isNull(status):
			notices = notices.filter(status=int(status))
		if not ValidateUtils.isNull(startDate):
			notices = notices.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			notices = notices.filter(add_time__lte=endDate)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			notices = notices[start:]
		else:
			limit = int(limit)
			notices = notices[start: start+limit]
		dtos = []
		for notice in notices:
			dtos.append(notice.getNoticeDto())
		return dtos
	
	@staticmethod
	def add(userId, title, content, status):
		notice = Notice()
		notice.create_id = userId
		notice.title = title
		notice.content = content
		notice.status = int(status)
		notice.save()
		return True
	
	@staticmethod
	def delete(id):
		try:
			notice = Notice.objects.get(id=id)
			notice.del_flag = Const.DEL_FLAG_DEL
			notice.save()
			return True
		except Exception, e:
			return False
	
	@staticmethod
	def edit(id, title, content, status):
		try:
			notice = Notice.objects.get(id=id)
			notice.content = content
			notice.title = title
			notice.status = status
			notice.save()
			return True
		except Exception, e:
			return False
	
	@staticmethod
	def getListCount(title, status, startDate, endDate):
		notices = Notice.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(title):
			notices = notices.filter(title__contains=title)
		if not ValidateUtils.isNull(status):
			notices = notices.filter(status=status)
		if not ValidateUtils.isNull(startDate):
			notices = notices.filter(add_time__gte=startDate)
		if not ValidateUtils.isNull(endDate):
			notices = notices.filter(add_time__lte=endDate)
		return notices.count()
