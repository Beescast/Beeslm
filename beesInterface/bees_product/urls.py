from django.conf.urls import url
import views

urlpatterns = [
	url(r'^info$', view=views.info, name='info'),
	url(r'^list$', view=views.list, name='list'),
	url(r'^add$', view=views.add, name='add'),
	url(r'^del$', view=views.delete, name='delete'),
	url(r'^edit$', view=views.edit, name='edit'),
]