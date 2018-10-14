# -*- coding: utf-8 -*- 
from bs4 import BeautifulSoup
import urllib.request as MyURL
import requests
import pymysql
def clear_data(num):
    db = pymysql.connect(host='localhost', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "delete from smart_weathermodel where id=%s"
#         print(sql)
        cursor.execute(sql, (str(num)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
def save_record(num, time, weather, temp, mintemp, maxtemp, hudmity):
    db = pymysql.connect(host='localhost', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "INSERT INTO smart_weathermodel(id, time, weather, temp, mintemp, maxtemp, hudmity) VALUES(%s, %s, %s, %s, %s, %s, %s)"
#         print(sql)
        cursor.execute(sql, (str(num), str(time), str(weather), str(temp), str(mintemp), str(maxtemp), str(hudmity)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
# 서울/경기 RSS
japi = 'http://www.weather.go.kr/wid/queryDFSRSS.jsp?zone=4139059100' # 서울/경기

response = MyURL.urlopen(japi)

weathers = BeautifulSoup(response, "html.parser")

for date in weathers.findAll('header'):
    today = date.tm.string
    year = today[0:4]
    month = today[4:6]
    day = today[6:8]
#     print(year)
#     print(month)
#     print(day)
forecastdic = dict()
count=1
for i in range(24):
    clear_data(i)
for data in weathers.findAll('data'): # location데이터 정리
# 
#     if location.city.string == cities: #RSS안에서 지역 검색
#         print(location.city.string)
#         print("="*20)
#         for data in location.findAll('data'):
    if len(data.hour.string) == 1:
        data.hour.string = "0"+data.hour.string
            
    hour = str(str(year) + "-" + str(month) + "-" + str(day) + " " + data.hour.string + ":" + "00")
#     print(day)
    if data.hour.string == "24":
#         print("24")
        if int(year)%400 == 0: #400으로 나누어떨어질 시 윤년
            if month == "01" or month == "03" or month == "05" or month == "07" or month == "08" or month == "10" or month == "12":
                if int(day) == 31:
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
            elif month == "02":
                if int(day) == 29: #윤년의 2월은 29일까지
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
            elif month == "04" or month == "06" or month == "09" or month == "11":
                if int(day) == 30:
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
#             print("윤년")
        if int(year)%4 == 0 and int(year)%100 == 0: #4로 나누어떨어지면서 100으로 나누어떨어질 시 윤년
            if month == "01" or month == "03" or month == "05" or month == "07" or month == "08" or month == "10" or month == "12":
                if int(day) == 31:
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
            elif int(day) == 31:
                if day == "29": #윤년의 2월은 29일까지
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
            elif month == "04" or month == "06" or month == "09" or month == "11":
                if int(day) == 30:
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
#             print("윤년")
        else: #나머진 윤년아님
            if month == "01" or month == "03" or month == "05" or month == "07" or month == "08" or month == "10" or month == "12":
#                 print("month")
                if int(day) == 31:
#                     print("31초기화")
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
                    
            elif month == "02":
                if int(day) == 28: #윤년아닌 년도의 2월은 28일까지
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
#                     print("28초기화")
            elif month == "04" or month == "06" or month == "09" or month == "11":
                if int(day) == 30:
                    day = "00"
                    if month == "12":
                        month = "00"
                        year = int(year) + 1
                    month = "0" + str(int(month) + 1)
#                     print("30초기화")
#             print("윤년아님")
        if len(day) == 1:
            day ="0" + str(int(day) + 1)
        else:
            day = str(int(day) + 1)
        
#     print(hour)
#             if data.wf.string == "흐리고 비":
#                 weather = "cloudy and rainy"
#             elif data.wf.string == "구름많고 비":
#                 weather = "full cloudy and rainy"
#             elif data.wf.string == "구름많음":
#                 weather = "full cloudy"
#             elif data.wf.string == "구름조금":
#                 weather = "little cloudy"
#             elif data.wf.string == "맑음":
#                 weather = "sunny"
#             else:
#                 weather = ""
#             if data.reliability.string == "높음":
#                 reliability = "high"
#             elif data.wf.string == "보통":
#                 reliability = "normal"
#             else:
#                 reliability = "low"#   
    weather = data.wfkor.string
    mintemp = data.tmn.string
    maxtemp = data.tmx.string
    temp = data.temp.string
    if mintemp == "-999.0":
        mintemp = "당일 최저 표기 불가"
    else:
        mintemp = mintemp
    if maxtemp == "-999.0":
        maxtemp = "당일 최고 표기 불가"
    else:
        maxtemp = maxtemp
    hudmity = data.reh.string        

    forecastdic["time"] = hour
     
    forecastdic["weather"] = weather
    forecastdic["temp"] = temp
    if mintemp == "-999.0":
        forecastdic["mintemp"] = "당일 최저 표기 불가"
    else:
        forecastdic["mintemp"] = mintemp
    if mintemp == "-999.0":
        forecastdic["maxtemp"] = "당일 최고 표기 불가"
    else:
        forecastdic["maxtemp"] = maxtemp    
    
    forecastdic["hudmity"] = hudmity
#     print(forecastdic)
    save_record(count, hour, weather, temp, mintemp, maxtemp, hudmity)
    count = count + 1

