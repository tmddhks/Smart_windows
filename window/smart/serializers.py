#-*- coding:utf-8 -*-
from smart.models import Windowmodel, Weathermodel, finedust_siheung
from rest_framework import serializers, viewsets
from django.contrib.auth.models import User

class WindowSerializer(serializers.ModelSerializer):
    # ModelSerializer
    class Meta:
        model = Windowmodel
        fields = ('temperature', 'humidity', 'finedust')

    # Window 모델 생성
    def create(self, validated_data):
        return Windowmodel.objects.create(**validated_data)

    #  window model instance 유효한 데이터값을 얻었을 시 업데이트
    def update(self, instance, validated_data):
        instance.content = validated_data.get('temperature', instance.content)
        instance.content = validated_data.get('humidity', instance.content)
        instance.content = validated_data.get('finedust', instance.content)
        instance.save()
        return instance

class WindowViewSet(viewsets.ModelViewSet):
    queryset = Windowmodel.objects.all()
    serializer_class = WindowSerializer
    
# 유저 목록 클래스
class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'username', 'email', 'is_staff')

# ViewSets define the view behavior.
class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

class WeatherSerializer(serializers.ModelSerializer):
    class Meta:
        model = Weathermodel
        fields = ('time', 'weather', 'temp', 'mintemp', 'maxtemp', 'hudmity')
    def update(self, instance, validated_data):
        instance.content = validated_data.get('time', instance.content)
        instance.content = validated_data.get('weather', instance.content)
        instance.content = validated_data.get('temp', instance.content)
        instance.content = validated_data.get('mintemp', instance.content)
        instance.content = validated_data.get('maxtemp', instance.content)
        instance.content = validated_data.get('humity', instance.content)
        instance.save()
        return instance

# ViewSets define the view behavior.
class WeatherViewSet(viewsets.ModelViewSet):
    queryset = Weathermodel.objects.all()
    serializer_class = WeatherSerializer
    
class finedust_siheungSerializer(serializers.ModelSerializer):
    class Meta:
        model = finedust_siheung
        fields = ('time', 'pm10', 'pm25', 'pm10value', 'pm25value')
    def update(self, instance, validated_data):
        instance.content = validated_data.get('time', instance.content)
        instance.content = validated_data.get('pm10', instance.content)
        instance.content = validated_data.get('pm25', instance.content)
        instance.content = validated_data.get('pm10value', instance.content)
        instance.content = validated_data.get('pm25value', instance.content)
        instance.save()
        return instance

# ViewSets define the view behavior.
class finedust_siheungViewSet(viewsets.ModelViewSet):
    queryset = finedust_siheung.objects.all()
    serializer_class = finedust_siheungSerializer
    