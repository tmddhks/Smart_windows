from django.contrib import admin
from django.urls import include, path
from django.conf.urls import url, include
from rest_framework import routers
from smart import views

urlpatterns = [
    path('smart/', include('smart.urls')),
    path('admin/', admin.site.urls),
    url(r'^weather/', include('smart.url2')),
   
    #path('Window/', include('smart.views')),
] 
