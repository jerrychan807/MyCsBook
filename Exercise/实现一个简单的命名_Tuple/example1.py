# -*- coding:utf-8 -*-

# @Time    : 2018/11/8 2:19 PM
# @Author  : jerry


class inch(float):
    "Convert from inch to meter"
    def __new__(cls, arg=0.0):
        return float.__new__(cls, arg*0.0254)


if __name__ == '__main__':
    print(inch(12))