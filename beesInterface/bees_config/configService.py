from models import Config
import django.utils.timezone as timezone
from utils.const import Const
from utils.validateUtils import ValidateUtils

class ConfigService:
	@staticmethod
	def getInfoById(id):
		try:
			return Config.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except Exception, e:
			return None

	@staticmethod
	def getList(start, limit):
		configs = Config.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			configs = configs[start:]
		else:
			limit = int(limit)
			configs = configs[start:start+limit]
		return configs

	@staticmethod
	def add(userId, title, content, status):
		config = Config()
		# config.add_time = timezone.now()
		config.save()
		return True

	@staticmethod
	def delete(id):
		try:
			config = Config.objects.get(id=id)
			config.del_flag = Const.DEL_FLAG_DEL
			config.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def edit(id, value):
		try:
			config = Config.objects.get(id=id)
			config.value = value
			config.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def getListCount():
		return Config.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).count()
