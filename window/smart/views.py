# -*- coding: utf-8 -*- 
from django.http import HttpResponse 
from bs4 import BeautifulSoup
import urllib.request as MyURL
from django.shortcuts import render

"""
def index(request) : 
    return HttpResponse("server is running")
    """
from rest_framework.renderers import JSONRenderer
from smart.serializers import WindowSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from smart.models import Windowmodel, Weathermodel

class JSONResponse(HttpResponse):
    def __init__(self, data, **kwargs):

        content = JSONRenderer().render(data, 'application/json; indent=4')

        kwargs['content_type'] = 'application/json'

        super(JSONResponse, self).__init__(content, **kwargs)
@api_view(['GET','POST','DELETE'])
def Windowlist(request, format=None):

    if request.method == 'GET':

        packages = Windowmodel.objects.all()

        serializer = WindowSerializer(packages, many=True)

        return JSONResponse(serializer.data)
    
# def Weatherlist(request):
#     weathers = data.objects.all()
#     
#     for weather in weathers:
#         str +="<p>시간 : {}<br>".format(data.time)
#         str +="<p>날씨 : {}<br>".format(data.weather)
#         str +="<p>최저 : {}℃<br>".format(data.min)
#         str +="<p>최고 : {}℃<br>".format(data.max)
#     return HttpResponse(str)
def capturing_data(request):
    weather_text = Windowmodel.objects.all()
    context = {'weather_text' : weather_text}

    return render(request, 'weather.html', context)

def weather_forecast(request): 
    forecast_text = Weathermodel.objects.all()
    context = {'forecast_text' : forecast_text}
    return render(request, 'forecast.html', context)
