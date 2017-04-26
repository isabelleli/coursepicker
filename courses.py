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


def parse1(info):
    checker = info.split()[1]
    start = 0
    if checker.isdigit():
        start = info.index(checker) 
    for i in range(start,len(info)-2):
        if info[i].isupper() and info[i+1].isupper() :
            return (info[:i],info[i:])
    return (info,'N/A')

def parse2(info):
    l = info.split()
    res = (l[0],l[1],l[3],l[4][1:6])
    if l[5] != '-':
        res += (l[5],l[7]+l[8],l[10]+l[11][:-1])
    else:
        res += ('N/A','N/A','N/A')
    return res
            
parsed1 = [parse1(name.text) for name in names]
parsed2 = [parse2(course.text) for course in courses]

parsed = [(parsed1[i]+parsed2[i]) for i in range(750)]

infoList = [{"Subject":c[2],"Number":c[3],"Name":c[0],"Session":c[4],"Professor":c[1],"CRN":c[5],"Day":c[6],"Start Time":c[7],"End Time":c[8]} for c in parsed]

def writeJSON(filename,content):
   with open(filename, 'w') as fw:
       json.dump(content, fw, sort_keys= True, indent=2)

writeJSON("CourseInfo.json",infoList)
