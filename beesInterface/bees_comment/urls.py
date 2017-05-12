from django.conf.urls import url
import views

urlpatterns = [
	url(r'^list$', view=views.list, name='list'),
	url(r'^addComment$', view=views.addComment, name='addComment'),
]