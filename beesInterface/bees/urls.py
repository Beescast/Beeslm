"""bees URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
	https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
	1. Add an import:  from my_app import views
	2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
	1. Add an import:  from other_app.views import Home
	2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
	1. Import the include() function: from django.conf.urls import url, include
	2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin

urlpatterns = [
	# url(r'^admin/', admin.site.urls),
	url(r'^bees/admin/', include('bees_admin.urls', namespace='admin')),
	url(r'^bees/auth/', include('bees_auth.urls', namespace='auth')),
	url(r'^bees/banner/', include('bees_banner.urls', namespace='banner')),
	url(r'^bees/comment/', include('bees_comment.urls', namespace='comment')),
	url(r'^bees/config/', include('bees_config.urls', namespace='config')),
	url(r'^bees/file/', include('bees_file.urls', namespace='file')),
	url(r'^bees/link/', include('bees_link.urls', namespace='link')),
	url(r'^bees/live/', include('bees_live.urls', namespace='live')),
	url(r'^bees/msg/', include('bees_msg.urls', namespace='msg')),
	url(r'^bees/notice/', include('bees_notice.urls', namespace='notice')),
	url(r'^bees/order/', include('bees_order.urls', namespace='order')),
	url(r'^bees/orderLog/', include('bees_orderLog.urls', namespace='orderLog')),
	url(r'^bees/package/', include('bees_package.urls', namespace='package')),
	url(r'^bees/pay/', include('bees_pay.urls', namespace='pay')),
	url(r'^bees/product/', include('bees_product.urls', namespace='product')),
	url(r'^bees/task/', include('bees_task.urls', namespace='task')),
	url(r'^bees/user/', include('bees_user.urls', namespace='user')),
	url(r'^bees/wallet/', include('bees_wallet.urls', namespace='wallet')),
]
