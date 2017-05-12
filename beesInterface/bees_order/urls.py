from django.conf.urls import url
import views

urlpatterns = [
	url(r'^list$', view=views.list, name='list'),
	url(r'^info$', view=views.info, name='info'),
	url(r'^add$', view=views.add, name='add'),
	url(r'^del$', view=views.delete, name='delete'),
	url(r'^realDel$', view=views.realDel, name='realDel'),
	url(r'^startPackage$', view=views.startPackage, name='startPackage'),
]