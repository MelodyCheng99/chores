from django.contrib.auth import get_user_model
from django.contrib.auth.models import User
from django.contrib.auth.hashers import check_password
from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

@csrf_exempt
@api_view(["POST"])
def validate_user(request): 
    if ('username_or_email' in request.data) and ('password' in request.data):
        User = get_user_model()

        username_or_email = request.data['username_or_email']
        password = request.data['password']

        try:
            user = User.objects.get(username=username_or_email)
            if user.check_password(password):
                return Response({
                    'id': getattr(user, 'id'),
                    'first_name': getattr(user, 'first_name'),
                    'last_name': getattr(user, 'last_name')
                }, status=status.HTTP_200_OK)
        except User.DoesNotExist:
            try:
                user = User.objects.get(email=username_or_email)
                if user.check_password(password):
                    return Response({
                        'id': getattr(user, 'id'),
                        'first_name': getattr(user, 'first_name'),
                        'last_name': getattr(user, 'last_name')
                    }, status=status.HTTP_200_OK)
            except User.DoesNotExist:
                return Response({}, status=status.HTTP_401_UNAUTHORIZED)

    return Response({}, status=status.HTTP_401_UNAUTHORIZED)

@csrf_exempt
@api_view(["POST"])
def create_user(request):
    if ('username' not in request.data):
        return Response({}, status=status.HTTP_422_UNPROCESSABLE_ENTITY)
        #username/email already taken, try again
    else:
        #store username/email, FName, LName, Password
        username = request.data['username']
        new_user = User.objects.create_user(username=username)
        #set email
        return Response({}, status=status.HTTP_201_CREATED)
