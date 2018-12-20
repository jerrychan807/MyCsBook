# -*- coding:utf-8 -*-

# @Time    : 2018/12/15 9:13 PM
# @Author  : jerry

import threading
import time

class Box(object):
    lock = threading.RLock()

    def __init__(self, item):
        self.total_items = item

    def execute(self, n):
        Box.lock.acquire()
        self.total_items += n
        print("[*] %s items still remain in the box " % self.total_items)
        Box.lock.release()


    def add(self):
        Box.lock.acquire()
        self.execute(1)
        Box.lock.release()

    def remove(self):
        Box.lock.acquire()
        self.execute(-1)
        Box.lock.release()

## These two functions run n in separate
## threads and call the Box's methods
def adder(box):
    while box.total_items > 0:
        print("adding 1 item in the box")
        box.add()
        time.sleep(1)
        box.total_items -= 1

def remover(box):
    while box.total_items > 0:
        print("removing 1 item in the box")
        box.remove()
        time.sleep(1)
        box.total_items -= 1

## the main program build some
## threads and make sure it works
if __name__ == "__main__":
    items = 3
    print("putting %s items in the box " % items)
    box = Box(items)
    t1 = threading.Thread(target=adder, args=(box, ))
    t2 = threading.Thread(target=remover, args=(box, ))
    t1.start()
    t2.start()

    t1.join()
    t2.join()
    print("%s items still remain in the box " % box.total_items)