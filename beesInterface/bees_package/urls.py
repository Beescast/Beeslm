from django.conf.urls import url
import views

urlpatterns = [
	url(r'^list$', view=views.list, name='list'),
	url(r'^info$', view=views.info, name='info'),
	url(r'^price$', view=views.price, name='price'),
	url(r'^singlePrice$', view=views.singlePrice, name='singlePrice'),
	url(r'^delPrice$', view=views.delPrice, name='delPrice'),
	url(r'^add$', view=views.add, name='add'),
	url(r'^del$', view=views.delete, name='delete'),
	url(r'^edit$', view=views.edit, name='edit'),
]
	# url(r'^login$', view=views.login, name='login'),