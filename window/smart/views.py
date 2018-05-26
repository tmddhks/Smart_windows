# -*- coding: utf-8 -*- 
from django.http import HttpResponse 
from bs4 import BeautifulSoup
import urllib.request as MyURL
from django.shortcuts import render
from django.db.models import Q

"""
def index(request) : 
    return HttpResponse("server is running")
    """
from rest_framework.renderers import JSONRenderer
from smart.serializers import WindowSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework import status
from smart.models import *

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
def capturing_data(request):
    weather_text = Windowmodel.objects.all()
    context = {'weather_text' : weather_text}

    return render(request, 'weather.html', context)

def weather_forecast(request): 
    forecast_text = Weathermodel.objects.all()
    context = {'forecast_text' : forecast_text}
    return render(request, 'forecast.html', context)

def siheung(request):
    siheung_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : siheung_dust}
    return render(request, 'finedust_siheung.html', context)
def goyang(request):
    goyang_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : goyang_dust}
    return render(request, 'finedust_goyang.html', context)
def gapyeong(request):
    gapyeong_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gapyeong_dust}
    return render(request, 'finedust_gapyeong.html', context)
def gwacheon(request):
    gwacheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gwacheon_dust}
    return render(request, 'finedust_gwacheon.html', context)
def gwangmyung(request):
    gwangmyung_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gwangmyung_dust}
    return render(request, 'finedust_gwangmyung.html', context)
def gwangju(request):
    gwangju_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gwangju_dust}
    return render(request, 'finedust_gwangju.html', context)
def guri(request):
    guri_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : guri_dust}
    return render(request, 'finedust_guri.html', context)
def gunpo(request):
    gunpo_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gunpo_dust}
    return render(request, 'finedust_gunpo.html', context)
def gimpo(request):
    gimpo_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : gimpo_dust}
    return render(request, 'finedust_gimpo.html', context)
def namyangju(request):
    namyangju_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : namyangju_dust}
    return render(request, 'finedust_namyangju.html', context)
def dongducheon(request):
    dongducheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : dongducheon_dust}
    return render(request, 'finedust_dongducheon.html', context)
def bucheon(request):
    bucheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : bucheon_dust}
    return render(request, 'finedust_bucheon.html', context)
def sungnam(request):
    sungnam_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : sungnam_dust}
    return render(request, 'finedust_sungnam.html', context)
def suwon(request):
    suwon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : suwon_dust}
    return render(request, 'finedust_suwon.html', context)
def ansan(request):
    ansan_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : ansan_dust}
    return render(request, 'finedust_ansan.html', context)
def ansung(request):
    ansung_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : ansung_dust}
    return render(request, 'finedust_ansung.html', context)
def anyang(request):
    anyang_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : anyang_dust}
    return render(request, 'finedust_anyang.html', context)
def yangju(request):
    yangju_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : yangju_dust}
    return render(request, 'finedust_yangju.html', context)
def yangpyeong(request):
    yangpyeong_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : yangpyeong_dust}
    return render(request, 'finedust_yangpyeong.html', context)
def yeoju(request):
    yeoju_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : yeoju_dust}
    return render(request, 'finedust_yeoju.html', context)
def yeoncheon(request):
    yeoncheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : yeoncheon_dust}
    return render(request, 'finedust_yeoncheon.html', context)
def osan(request):
    osan_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : osan_dust}
    return render(request, 'finedust_osan.html', context)
def yongin(request):
    yongin_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : yongin_dust}
    return render(request, 'finedust_yongin.html', context)
def hewang(request):
    hewang_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : hewang_dust}
    return render(request, 'finedust_hewang.html', context)
def hejungbu(request):
    hejungbu_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : hejungbu_dust}
    return render(request, 'finedust_hejungbu.html', context)
def icheon(request):
    icheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : icheon_dust}
    return render(request, 'finedust_icheon.html', context)
def paju(request):
    paju_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : paju_dust}
    return render(request, 'finedust_paju.html', context)
def pyeongtack(request):
    pyeongtack_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : pyeongtack_dust}
    return render(request, 'finedust_pyeongtack.html', context)
def pocheon(request):
    pocheon_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : pocheon_dust}
    return render(request, 'finedust_pocheon.html', context)
def hanam(request):
    hanam_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : hanam_dust}
    return render(request, 'finedust_hanam.html', context)
def hwasung(request):
    hwasung_dust = finedust_siheung.objects.all()
    context = {'finedust_siheung' : hwasung_dust}
    return render(request, 'finedust_hwasung.html', context)