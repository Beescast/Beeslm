
from utils.validateUtils import ValidateUtils
from models import Product
from utils.const import Const

class ProductService:

	@staticmethod
	def getInfoById(id):
		try:
			return Product.objects.get(id=id, del_flag=Const.DEL_FLAG_NOT_DEL)
		except Exception, e:
			return None

	@staticmethod
	def getList(position, start, limit):
		products = Product.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL).order_by('-id')
		if not ValidateUtils.isNull(position):
			products = products.filter(position=position)
		if ValidateUtils.isNull(start):
			start = 0
		else:
			start = int(start)
		if ValidateUtils.isNull(limit):
			products = products[start:]
		else:
			limit = int(limit)
			products = products[start:start+limit]
		return products

	@staticmethod
	def add(name, pic, href, position):
		product = Product()
		# product.add_time = datetime.datetime.now()
		product.href = href
		product.pic = pic
		product.position = position
		product.name = name
		product.save()
		return True

	@staticmethod
	def delete(id):
		try:
			product = Product.objects.get(id=id)
			product.del_flag = Const.DEL_FLAG_DEL
			product.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def edit(id, name, pic, href, position):
		try:
			product = Product.objects.get(id=id)
			product.href = href
			product.pic = pic
			product.position = position
			product.name = name
			product.save()
			return True
		except Exception, e:
			return False

	@staticmethod
	def getListCount(position):
		products = Product.objects.filter(del_flag=Const.DEL_FLAG_NOT_DEL)
		if not ValidateUtils.isNull(position):
			products = products.filter(position=position)
		return products.count()
