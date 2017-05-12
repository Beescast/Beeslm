from django.conf.urls import url
import views

urlpatterns = [
	url(r'^login$', view=views.login, name='login'),
	url(r'^unionLogin$', view=views.unionLogin, name='unionLogin'),
	url(r'^bindUnion$', view=views.bindUnion, name='bindUnion'),
	url(r'^reg$', view=views.reg, name='reg'),
	url(r'^code$', view=views.code, name='code'),
	url(r'^authCode$', view=views.authCode, name='authCode'),
	url(r'^authMobileCode$', view=views.authMobileCode, name='authMobileCode'),
	url(r'^findPass$', view=views.findPass, name='findPass'),
	url(r'^getMobileCode$', view=views.getMobileCode, name='getMobileCode'),
	url(r'^wxlogin$', view=views.wxlogin, name='wxlogin'),
]