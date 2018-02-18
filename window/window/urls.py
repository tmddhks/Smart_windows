from django.contrib import admin
from django.urls import include, path
from rest_framework import routers
from smart import views

urlpatterns = [
    path('smart/', include('smart.urls')),
    path('admin/', admin.site.urls),
   
    #path('Window/', include('smart.views')),
] 
