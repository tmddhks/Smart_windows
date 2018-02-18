#-*- coding:utf-8 -*-
from smart.models import Windowmodel
from rest_framework import serializers, viewsets
from django.contrib.auth.models import User
class WindowSerializer(serializers.ModelSerializer):
    # ModelSerializer
    class Meta:
        model = Windowmodel
        fields = ('temparature', 'moisture', 'finedust')

    # Window 모델 생성
    def create(self, validated_data):
        return Windowmodel.objects.create(**validated_data)

    #  window model instance 유효한 데이터값을 얻었을 시 업데이트
    def update(self, instance, validated_data):
        instance.content = validated_data.get('temparature', instance.content)
        instance.content = validated_data.get('moisture', instance.content)
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