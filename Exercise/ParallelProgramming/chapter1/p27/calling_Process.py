# -*- coding:utf-8 -*-

# @Time    : 2018/12/15 2:09 PM
# @Author  : jerry

'''
使用os.execvp开启一个新的process
'''
import os
import sys

program = "python"
print("Process calling")
arguments = ["called_Process.py"]

os.execvp(program, (program, ) + tuple(arguments))
print("Good Bye")