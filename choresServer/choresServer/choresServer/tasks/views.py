from django.views.decorators.csrf import csrf_exempt

from rest_framework.decorators import api_view
from rest_framework import status
from rest_framework.response import Response

@csrf_exempt
@api_view(["GET"])
def all_tasks(request):
    return Response(
        [
            {
                'room': 'Living Room',
                'task': 'Sweep floor'
            },
            {
                'room': 'Kitchen',
                'task': 'Wash dishes'
            },
            {
                'room': 'Bathroom',
                'task': 'Clean toilet'
            },
            {
                'room': 'Bedroom',
                'task': 'Make bed'
            },
            {
                'room': 'Backyard',
                'task': 'Water plants'
            },
            {
                'room': 'Backyard',
                'task': 'Mow lawn'
            }
        ],
        status=status.HTTP_200_OK
    )
