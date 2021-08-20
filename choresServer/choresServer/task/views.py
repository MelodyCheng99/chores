from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import get_user_model

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

from .models import Task

@csrf_exempt
@api_view(["GET"])
def all_tasks(request, user_id):
    User = get_user_model()
    try:
        user = User.objects.get(id=user_id)

        tasksObject = Task.objects.filter(user=user)
        tasksList = []
        for task in tasksObject:
            tasksList.append({
                'room': getattr(task, 'room'),
                'task': getattr(task, 'task')
            })

        return Response(
            tasksList,
            status=status.HTTP_200_OK
        )
    except User.DoesNotExist:
        return Response({}, status=status.HTTP_401_UNAUTHORIZED)
