"""shopping_mall_frontend URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.urls import path

import main.views
import mall.views
import admin.views
import member.views
urlpatterns = [
    # path('admin/', admin.site.urls),
    path('admin/members', admin.views.member_list),
    path('admin/nonMembers', admin.views.non_member_list),
    # path('admin/mall/list', admin.views.mall_list),
    path('', main.views.index),
    path('mall/list', main.views.mall_list),

    path('<str:mall_link>/', mall.views.index),
    path('member/login', member.views.login),
    path('member/join', member.views.join),
    path('member/logout', member.views.logout),

]
