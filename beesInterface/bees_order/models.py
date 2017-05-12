from __future__ import unicode_literals

from django.db import models

# Create your models here.
# class Order(models.Model):
# 	id = models.BigIntegerField(primary_key=True)
# 	uid = models.IntegerField()
# 	package_id = models.IntegerField()
# 	package_name = models.CharField(max_length=256)
# 	package_price_id = models.IntegerField()
# 	package_pic = models.CharField(max_length=512)
# 	price = models.CharField(max_length=64)
# 	status = models.IntegerField()
# 	pay_type = models.IntegerField()
# 	submit_time = models.DateTimeField()
# 	pay_time = models.DateTimeField()
# 	start_time = models.DateTimeField()
# 	end_time = models.DateTimeField()
# 	comment = models.CharField(max_length=1024)
# 	comment_time = models.DateTimeField()
# 	add_time = models.DateTimeField(auto_now_add=True)
# 	update_time = models.DateTimeField(auto_now=True)
# 	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)
#
# 	class Meta:
# 		db_table = 'packages_order'
#
# 	def getOrderDto(self):
# 		dto = {}
# 		dto['id'] = self.id
# 		dto['title'] = self.title
# 		dto['url'] = self.pic_href
# 		dto['imgUrl'] = self.pic_url
# 		dto['content'] = self.content
# 		dto['page'] = self.page
# 		dto['position'] = self.position
# 		dto['picOrder'] = self.pic_order
# 		return dto
