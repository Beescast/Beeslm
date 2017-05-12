from django.conf.urls import url
import views

urlpatterns = [
	url(r'^alipay.html$', view=views.alipay, name='alipay'),
	url(r'^aliPayRes$', view=views.aliPayRes, name='aliPayRes'),
	url(r'^aliPayResNotify$', view=views.aliPayResNotify, name='aliPayResNotify'),
	url(r'^weixinPayRes$', view=views.weixinPayRes, name='weixinPayRes'),
	url(r'^wallet$', view=views.wallet, name='wallet'),
]