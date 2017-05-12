from django.conf.urls import url
import views

urlpatterns = [
	url(r'^uploadFiles$', view=views.uploadFiles, name='uploadFiles'),
]