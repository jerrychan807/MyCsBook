# -*- coding:utf-8 -*-

# @Time    : 2018/12/20 11:12 AM
# @Author  : jerry

import multiprocessing
import time

def foo():
    name = multiprocessing.current_process().name
    print("Starting %s " % name)
    time.sleep(10)
    print("Exiting %s " % name)

def foo1():
    name = multiprocessing.current_process().name
    print("Starting %s " % name)
    time.sleep(5)
    print("Exiting %s " % name)

if __name__ == '__main__':
    background_process = multiprocessing.Process(name='background_process', target=foo1)
    background_process.daemon = True

    NO_background_process = multiprocessing.Process(name='NO_background_process', target=foo)
    NO_background_process.daemon = False
    background_process.start()
    NO_background_process.start()