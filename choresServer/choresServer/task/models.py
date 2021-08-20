from django.db import models
from django.contrib.auth.models import User

from django.utils import timezone
import datetime

class Task(models.Model):
    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        verbose_name="related user",
        default=1
    )
    room = models.CharField(max_length=20)
    task = models.CharField(max_length=60)
    due_date = models.DateTimeField(default=timezone.now)
