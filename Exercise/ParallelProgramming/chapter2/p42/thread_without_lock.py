# -*- coding:utf-8 -*-

# @Time    : 2018/12/15 4:44 PM
# @Author  : jerry

import threading

shared_resource_with_lock = 0
shared_resource_with_no_lock = 0
COUNT = 100000
shared_resource_lock = threading.Lock()


#### NO LOCK MANAGEMENT
def increment_without_lock():
    global shared_resource_with_no_lock
    for i in range(COUNT):
        shared_resource_with_no_lock += 1


def decrement_without_lock():
    global shared_resource_with_no_lock
    for i in range(COUNT):
        shared_resource_with_no_lock -= 1


if __name__ == '__main__':
    t3 = threading.Thread(target=increment_without_lock)
    t4 = threading.Thread(target=decrement_without_lock)

    t3.start()
    t4.start()

    t3.join()
    t4.join()

    print("the value of shared variable with race condition is %s" % shared_resource_with_no_lock)
