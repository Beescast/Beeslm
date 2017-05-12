from django.conf.urls import url
import views

urlpatterns = [
	url(r'^list$', view=views.list, name='list'),
	url(r'^cash$', view=views.cash, name='cash'),
	url(r'^adminList$', view=views.adminList, name='adminList'),
	url(r'^adminCashList$', view=views.adminCashList, name='adminCashList'),
	url(r'^cashOp$', view=views.cashOp, name='cashOp'),
]