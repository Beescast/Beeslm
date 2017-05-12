from __future__ import unicode_literals

from django.db import models
#
# # Create your models here.
# class Pay(models.Model):
#

def getDto():
	dto = {}
	dto['service'] = "create_direct_pay_by_user"
	dto['partner'] = "2088521044317405"
	dto['seller_id'] = 'partner'
	dto['_input_charset'] = "utf-8"
	dto['payment_type'] = "1"
	dto['notify_url'] = GlobalConfigure.URL_BACKEND+"pay/aliPayResNotify"
	dto['return_url'] = GlobalConfigure.URL_BACKEND+"pay/aliPayRes"
	dto['anti_phishing_key'] = ""
	dto['exter_invoke_ip'] = ""
	dto['out_trade_no'] = None
	dto['subject'] = None
	dto['total_fee'] = None
	dto['extra_common_param'] = None
	return dto

