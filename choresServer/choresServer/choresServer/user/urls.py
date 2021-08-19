from django.urls import path

from . import views

urlpatterns = [
    path('validate_user', views.validate_user, name='validate_user'),
    path('create_user', views.create_user, name='create_user')
]
