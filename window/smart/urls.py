from django.conf.urls import url, include
from rest_framework import routers #안드로이드 통신을 위한 rest_framework
from smart.serializers import WindowViewSet, UserViewSet ,WeatherViewSet, finedust_siheungViewSet
from . import views


# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()
router.register(r'window', WindowViewSet)
router.register(r'forecast', WeatherViewSet)
router.register(r'user', UserViewSet)
router.register(r'finedust_siheung', finedust_siheungViewSet)
#rest_framework 라우팅
# Wire up our API using automatic URL routing.
# Additionally, we include login URLs for the browsable API.
urlpatterns = [
    url(r'^', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]