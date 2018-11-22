# -*- coding:utf-8 -*-

# @Time    : 2018/11/8 2:20 PM
# @Author  : jerry

class inch(float):
    "THIS DOESN'T WORK!!!"
    def __init__(self, arg=0.0):
        float.__init__(self, arg*0.0254)


if __name__ == '__main__':

    print (inch(12))