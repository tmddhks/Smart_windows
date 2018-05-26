# -*- coding: utf-8 -*- 
import pymysql
def clear_data(num):
    db = pymysql.connect(host='192.168.0.12', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "delete from smart_wanymodel where id=%s"
#         print(sql)
        cursor.execute(sql, (str(num)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
def save_record(num, temperature, hudmity, realtemp, outdust, indust):
    db = pymysql.connect(host='192.168.0.12', port=3307, user='root', passwd='yoon8675!!', db='windowsdb', charset='utf8')
    try:
        cursor = db.cursor()
        sql = "INSERT INTO smart_wanymodel(id, temperature, hudmity, realtemp, outdust, indust) VALUES(%s, %s, %s, %s, %s, %s)"
#         print(sql)
        cursor.execute(sql, (str(num), str(temperature), str(hudmity), str(realtemp), str(outdust), str(indust)))
            
        db.commit()
#         print(cursor.lastrowid)
    finally:
        db.close()
count = 0
for i in range(100):
    clear_data(i)
for k in range(100):
    temperature = 1
    hudmity = 1
    realtemp = 1
    outdust = 1
    indust = 1
    save_record(count, temperature, hudmity, realtemp, outdust, indust)
    count = count + 1
