# -*- coding:utf-8 -*-

# @Time    : 2018/12/15 4:44 PM
# @Author  : jerry

import threading

shared_resource_with_no_lock = 0
COUNT = 100000
import time


#### NO LOCK MANAGEMENT
def increment_without_lock():
    start = time.time()
    global shared_resource_with_no_lock
    for i in range(COUNT):
        shared_resource_with_no_lock += 1
    end = time.time()
    print("increment func use time:{0}".format(str(end - start)))
    print('\n')


def decrement_without_lock():
    start = time.time()
    global shared_resource_with_no_lock
    for i in range(COUNT):
        shared_resource_with_no_lock -= 1
    end = time.time()
    print("decrement func use time:{0}".format(str(end - start)))


if __name__ == '__main__':
    t3 = threading.Thread(name='t3', target=increment_without_lock)
    t4 = threading.Thread(name='t4', target=decrement_without_lock)

    t3.start()
    t4.start()

    t3.join()
    t4.join()

    print("the value of shared variable with race 9_condition is %s" % shared_resource_with_no_lock)
