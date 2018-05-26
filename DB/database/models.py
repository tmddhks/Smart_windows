from django.db import models

# Create your models here.
class wanymodel(models.Model):
    temperature = models.TextField()
    hudmity = models.TextField()
    realtemp = models.TextField()
    outdust = models.TextField()
    indust = models.TextField()
    
    def __str__(self):
        return self.name