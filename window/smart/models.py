from django.db import models 
# Create your models here. 
class Windowmodel(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    temparature = models.TextField() #�µ� �� ���� �ʵ�
    moisture = models.TextField() # ���� �� ���� �ʵ�
    finedust = models.TextField() # �̼����� �� ���� �ʵ�
    
    class Meta : 
        ordering = ('created',)