from django.conf.urls import url
import views

urlpatterns = [
	url(r'^info$', view=views.info, name='info'),
	url(r'^list$', view=views.list, name='list'),
	url(r'^addSign$', view=views.addSign, name='addSign'),
	url(r'^add$', view=views.add, name='add'),
	url(r'^del$', view=views.delete, name='delete'),
	url(r'^edit$', view=views.edit, name='edit'),
	url(r'^catList$', view=views.catList, name='catList'),
	url(r'^selectList$', view=views.selectList, name='selectList'),
	url(r'^addCat$', view=views.addCat, name='addCat'),
	url(r'^delCat$', view=views.delCat, name='delCat'),
	url(r'^editCat$', view=views.editCat, name='editCat'),
	url(r'^adminList$', view=views.adminList, name='adminList'),
	url(r'^getSignList$', view=views.getSignList, name='getSignList'),
	url(r'^getSignPicList$', view=views.getSignPicList, name='getSignPicList'),
	url(r'^finishTask$', view=views.finishTask, name='finishTask'),
	url(r'^sign$', view=views.sign, name='sign'),
	url(r'^unsign$', view=views.unsign, name='unsign'),
	url(r'^auth$', view=views.auth, name='auth'),
	url(r'^finish$', view=views.finish, name='finish'),
]