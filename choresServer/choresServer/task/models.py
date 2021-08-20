from django.db import models
from django.contrib.auth.models import User

class Task(models.Model):
    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        verbose_name="related user"
    )
    room = models.CharField(max_length=20)
    task = models.CharField(max_length=60)
