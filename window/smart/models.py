from django.db import models 
# Create your models here. 
class Windowmodel(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    temperature = models.TextField() #온도 값 저장 필드
    humidity = models.TextField() # 습도 값 저장 필드
    finedust = models.TextField() # 미세먼지 값 저장 필드
   
    def __str__(self):
        return self.name
    
    class Meta : 
        ordering = ('created',)
    
        
class Weathermodel(models.Model):
    time = models.TextField()
    weather = models.TextField() #날씨 값 저장 필드
    temp = models.TextField()
    mintemp = models.TextField()
    maxtemp = models.TextField() # 최저,최고기온
    hudmity = models.TextField()# 신뢰도
    
    def __str__(self):
        return self.name
    
class finedust_siheung(models.Model):
    city = models.TextField()
    time = models.TextField()
    pm10 = models.TextField() #날씨 값 저장 필드
    pm25 = models.TextField()
    pm10value = models.TextField()
    pm25value = models.TextField()
       
    def __str__(self):
        return self.name
        