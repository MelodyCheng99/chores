from django.urls import path
from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^all/(?P<user_id>\d+)/$', views.all_tasks, name='all_tasks'),
]
