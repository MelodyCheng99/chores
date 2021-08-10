from django.contrib.auth import get_user_model
from django.contrib.auth.hashers import check_password
from django.http import HttpResponse
from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

@csrf_exempt
@api_view(["POST"])
def validate_user(request):
    if ('username' in request.data) and ('password' in request.data):
        User = get_user_model()

        username = request.data['username']
        password = request.data['password']

        user = User.objects.get(username=username)
        if user.check_password(password):
            return Response({}, status=status.HTTP_200_OK)
    return Response({}, status=status.HTTP_401_UNAUTHORIZED)
    
