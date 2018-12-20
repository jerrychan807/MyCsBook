# -*- coding:utf-8 -*-

# @Time    : 2018/12/20 11:25 AM
# @Author  : jerry

# 自定义子类进程
import multiprocessing
import time

class MyProcess(multiprocessing.Process):
        def run(self):
                print ('called run method in process: %s' % self.name)
                time.sleep(5)
                return

if __name__ == '__main__':
        jobs = []
        for i in range(5):
                p = MyProcess()
                jobs.append(p)
                p.start()
        for p in jobs:
            p.join()
        # print(jobs)