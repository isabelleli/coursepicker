# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import os,time
from pprint import pprint
import json

#CityName_RestaurantName

driver = webdriver.Chrome(executable_path='/Users/Regina/Desktop/webdriver/chromedriver.exe')
driver.get("https://courses.wellesley.edu")
courses = driver.find_elements_by_xpath("//div[@class='coursecode']")
names = driver.find_elements_by_xpath("//div[@class='coursename_small']")
professors = driver.find_elements_by_xpath("//div[@class='professorname']")


def parse1(info):
    checker = info.split()[1]
    start = 0
    if checker.isdigit():
        start = info.index(checker) 
    for i in range(start,len(info)-2):
        if info[i].isupper() and info[i+1].isupper():
            if info[i:i+2] == "II":
                return (info[:i+2],info[i+2:])
            elif (info[i] == "I" and info[i:i+4] != "IGOR" and 
            info[i:i+5] != "ISMAR" and info[i:i+5] != "IRENE"
            and info[i:i+6] != "ISABEL" and info[i:i+7] != "IFEANYI"
            and info[i:i+5] != "INELA" and info[i:i+3] != "INA"):
                return (info[:i+1],info[i+1:])
            return (info[:i],info[i:])
    return (info,'N/A')

def parse2(info):
    l = info.split()
    res = (l[0],l[1],l[3],l[4][1:6])
    if l[5] != '-':
        start = info.index(")")+2
        end = info.index("CURRENT")-2
        res += ((info[start:end],))
    else:
        res += (('N/A',))   
    return res
            
parsed1 = [parse1(name.text) for name in names]
parsed2 = [parse2(course.text) for course in courses]

parsed = [(parsed1[i]+parsed2[i]) for i in range(749)]

infoList = [{"Subject":c[2],"Number":c[3],"Name":c[0],"Session":c[4],"Professor":c[1],"CRN":c[5],"Time":c[6]} for c in parsed]

def writeJSON(filename,content):
   with open(filename, 'w') as fw:
       json.dump(content, fw, sort_keys= True, indent=2)

writeJSON("CourseInfo.json",infoList)
