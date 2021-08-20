from django.views.decorators.csrf import csrf_exempt
from django.contrib.auth import get_user_model

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

from datetime import datetime, timedelta

from .models import Task

@csrf_exempt
@api_view(["GET"])
def all_tasks(request, user_id):
    User = get_user_model()
    try:
        user = User.objects.get(id=user_id)
        due_date = request.GET.get('due_date')
        due_date_object = datetime.strptime(due_date, '%Y-%m-%d')
        due_date_object += timedelta(days=1)

        if due_date:
            tasksObject = Task.objects.filter(
                user=user,
                due_date__lte=due_date_object
            )
        else:
            tasksObject = Task.objects.filter(user=user)
        tasksList = []
        for task in tasksObject:
            tasksList.append({
                'room': getattr(task, 'room'),
                'task': getattr(task, 'task'),
                'due_date': getattr(task, 'due_date')
            })

        return Response(
            tasksList,
            status=status.HTTP_200_OK
        )
    except User.DoesNotExist:
        return Response({}, status=status.HTTP_401_UNAUTHORIZED)
