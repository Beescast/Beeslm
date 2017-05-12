from __future__ import unicode_literals

from django.db import models
from utils.const import Const

# Create your models here.
class Link(models.Model):
	title = models.CharField(max_length=128)
	href = models.CharField(max_length=256)
	orders = models.IntegerField()
	add_time = models.DateTimeField(auto_now_add=True)
	update_time = models.DateTimeField(auto_now=True)
	del_flag = models.IntegerField(default=Const.DEL_FLAG_NOT_DEL)

	class Meta:
		db_table = 'friend_link'
