from django.conf.urls import url
import views

urlpatterns = [
	url(r'^info$', view=views.info, name='info'),
	url(r'^list$', view=views.list, name='list'),
	url(r'^setPassword$', view=views.setPassword, name='setPassword'),
	url(r'^setPayPassword$', view=views.setPayPassword, name='setPayPassword'),
	url(r'^edit$', view=views.edit, name='edit'),
	url(r'^editAvatar$', view=views.editAvatar, name='editAvatar'),
	url(r'^changeMobile$', view=views.changeMobile, name='changeMobile'),
	url(r'^auth$', view=views.auth, name='auth'),
	url(r'^authList$', view=views.authList, name='authList'),
	url(r'^adminList$', view=views.adminList, name='adminList'),
	url(r'^authInfo$', view=views.authInfo, name='authInfo'),
	url(r'^unbindBank$', view=views.unbindBank, name='unbindBank'),
]