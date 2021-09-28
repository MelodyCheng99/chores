from django.contrib.auth import get_user_model
from django.contrib.auth.hashers import check_password
from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

from .models import ChoresUser

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
@api_view(["PATCH"])
def update_user(request):
    if 'username' in request.data:
        User = get_user_model()
        username = request.data['username']

        try:
            user = User.objects.get(username=username)

            if 'first_name' in request.data:
                user.first_name = request.data['first_name']

            if 'last_name' in request.data:
                user.last_name = request.data['last_name']

            if 'email' in request.data:
                user.email = request.data['email']

            if 'profile_picture' in request.data:
                choresUserObject = ChoresUser.objects.filter(user=user)
                print(choresUserObject)

            user.save()
            return Response({
                'id': getattr(user, 'id'),
                'username': getattr(user, 'username'),
                'first_name': getattr(user, 'first_name'),
                'last_name': getattr(user, 'last_name'),
                'email': getattr(user, 'email')
            }, status=status.HTTP_200_OK)
        
        except User.DoesNotExist:
            return Response({}, status=status.HTTP_404_NOT_FOUND)
            
    return Response({}, status=status.HTTP_404_NOT_FOUND)
    
