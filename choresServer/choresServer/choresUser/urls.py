from django.urls import path

from . import views

urlpatterns = [
    path('validate_user', views.validate_user, name='validate_user'),
    path('update_user', views.update_user, name='update_user')
]