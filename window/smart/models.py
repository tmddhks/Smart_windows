from django.db import models 
# Create your models here. 
class Windowmodel(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    temparature = models.TextField() #온도 값 저장 필드
    moisture = models.TextField() # 습도 값 저장 필드
    finedust = models.TextField() # 미세먼지 값 저장 필드
    
    class Meta : 
        ordering = ('created',)