# -*- coding: utf-8 -*- 
from bs4 import BeautifulSoup
import urllib.request as MyURL
import requests
import pymysql
def clear_data(num):
    db = pymysql.connect(host='localhost', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "delete from smart_finedust_siheung where id=%s"
#         print(sql)
        cursor.execute(sql, (str(num)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
def save_record(num, city, time, pm10, pm25, pm10value, pm25value):
    db = pymysql.connect(host='localhost', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "INSERT INTO smart_finedust_siheung(id, city, time, pm10, pm25, pm10value, pm25value) VALUES(%s, %s, %s, %s, %s, %s, %s)"
#         print(sql)
        cursor.execute(sql, (str(num), str(city), str(time), str(pm10), str(pm25), str(pm10value), str(pm25value)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
# 서울/경기 RSS
japi = 'http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey=55w9tSENPOgMO86XUs6zyqEoolT7WwnabV0MWbGVD9bcrAFCPEQf6tPnn4IAi899CQyGPvVEutKfdxW6HuEONg%3D%3D&numOfRows=15&pageSize=15&pageNo=1&startPage=1&sidoName=%EA%B2%BD%EA%B8%B0&searchCondition=HOUR' # 서울/경기

response = MyURL.urlopen(japi)

weathers = BeautifulSoup(response, "html.parser")
cities = "시흥시"

forecastdic = dict()
count=1
for i in range(100):
    clear_data(i)
for items in weathers.findAll('items'): # location데이터 정리
    for item in items.findAll('item'):
#         if item.cityname.string == cities: #RSS안에서 지역 검색
#             print(item.cityname.string)
#             print("="*20)         
            city = item.cityname.string    
            time = item.datatime.string
            PM10 = item.pm10value.string #미세먼지 농도
            if PM10 == '-':
                PM10value = "nodata"
            elif int(PM10) >=0 and int(PM10)<=30:
                PM10value = "good"
            elif int(PM10) >30 and int(PM10)<=80:
                PM10value = "normal"
            elif int(PM10) >80 and int(PM10)<=150:
                PM10value = "bad"
            elif int(PM10) >150:
                PM10value = "verybad"
            PM25 = item.pm25value.string #초미세먼지 농도
            if PM25 == '-':
                PM25value = "nodata"
            elif int(PM25) >=0 and int(PM25)<=15:
                PM25value = "good"
            elif int(PM25) >15 and int(PM25)<=50:
                PM25value = "normal"
            elif int(PM25) >50 and int(PM25)<=100:
                PM25value = "bad"
            elif int(PM25) >100:
                PM25value = "verybad"
#             print("미세먼지 : ", PM10, "초미세먼지 : ", PM25)
            save_record(count, city, time, PM10, PM25, PM10value, PM25value)
            count = count +1
japi = 'http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey=55w9tSENPOgMO86XUs6zyqEoolT7WwnabV0MWbGVD9bcrAFCPEQf6tPnn4IAi899CQyGPvVEutKfdxW6HuEONg%3D%3D&numOfRows=15&pageSize=15&pageNo=2&startPage=1&sidoName=%EA%B2%BD%EA%B8%B0&searchCondition=HOUR' # 서울/경기

response = MyURL.urlopen(japi)

weathers = BeautifulSoup(response, "html.parser")        
for items in weathers.findAll('items'): # location데이터 정리
    for item in items.findAll('item'):
#         if item.cityname.string == cities: #RSS안에서 지역 검색
#             print(item.cityname.string)
#             print("="*20)         
            city = item.cityname.string    
            time = item.datatime.string
            PM10 = item.pm10value.string #미세먼지 농도
            if PM10 == '-':
                PM10value = "nodata"
            elif int(PM10) >=0 and int(PM10)<=30:
                PM10value = "good"
            elif int(PM10) >30 and int(PM10)<=80:
                PM10value = "normal"
            elif int(PM10) >80 and int(PM10)<=150:
                PM10value = "bad"
            elif int(PM10) >150:
                PM10value = "verybad"
            PM25 = item.pm25value.string #초미세먼지 농도
            if PM25 == '-':
                PM25value = "nodata"
            elif int(PM25) >=0 and int(PM25)<=15:
                PM25value = "good"
            elif int(PM25) >15 and int(PM25)<=50:
                PM25value = "normal"
            elif int(PM25) >50 and int(PM25)<=100:
                PM25value = "bad"
            elif int(PM25) >100:
                PM25value = "verybad"
#             print("미세먼지 : ", PM10, "초미세먼지 : ", PM25)
            save_record(count, city, time, PM10, PM25, PM10value, PM25value)
            count = count +1

japi = 'http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey=55w9tSENPOgMO86XUs6zyqEoolT7WwnabV0MWbGVD9bcrAFCPEQf6tPnn4IAi899CQyGPvVEutKfdxW6HuEONg%3D%3D&numOfRows=15&pageSize=15&pageNo=3&startPage=1&sidoName=%EA%B2%BD%EA%B8%B0&searchCondition=HOUR' # 서울/경기

response = MyURL.urlopen(japi)

weathers = BeautifulSoup(response, "html.parser")        
for items in weathers.findAll('items'): # location데이터 정리
    for item in items.findAll('item'):
#         if item.cityname.string == cities: #RSS안에서 지역 검색
#             print(item.cityname.string)
#             print("="*20)         
            city = item.cityname.string    
            time = item.datatime.string
            PM10 = item.pm10value.string #미세먼지 농도
            if PM10 == '-':
                PM10value = "Nodata"
            elif int(PM10) >=0 and int(PM10)<=30:
                PM10value = "good"
            elif int(PM10) >30 and int(PM10)<=80:
                PM10value = "normal"
            elif int(PM10) >80 and int(PM10)<=150:
                PM10value = "bad"
            elif int(PM10) >150:
                PM10value = "verybad"
            PM25 = item.pm25value.string #초미세먼지 농도
            if PM25 == '-':
                PM25value = "Nodata"
            elif int(PM25) >=0 and int(PM25)<=15:
                PM25value = "good"
            elif int(PM25) >15 and int(PM25)<=50:
                PM25value = "normal"
            elif int(PM25) >50 and int(PM25)<=100:
                PM25value = "bad"
            elif int(PM25) >100:
                PM25value = "verybad"
#             print("미세먼지 : ", PM10, "초미세먼지 : ", PM25)
            save_record(count, city, time, PM10, PM25, PM10value, PM25value)
            count = count +1

