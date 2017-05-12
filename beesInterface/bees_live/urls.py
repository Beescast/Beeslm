from django.conf.urls import url
import views

urlpatterns = [
	url(r'^market$', view=views.market, name='market'),
	url(r'^people$', view=views.people, name='people'),
	url(r'^gift$', view=views.gift, name='gift'),
	url(r'^live$', view=views.live, name='live'),
]