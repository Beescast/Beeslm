from django.conf.urls import url
import views

urlpatterns = [
	url(r'^adminList$', view=views.adminList, name='adminList'),
]