# -*- coding:utf-8 -*-

# @Time    : 2018/11/8 1:54 PM
# @Author  : jerry


class NamedTuple(tuple):
    def __new__(cls, x, y):
        return tuple.__new__(cls, (x, y))

    def __init__(self, values, fields):
        self.values = values
        self.fields = fields

    def __getitem__(self, index):
        return self.values[index]

    def __getattr__(self, attr):
        '''
        找到attr对应的位置
        '''
        return self.values[self.fields.index(attr)]

    def __repr__(self):
        tem_list = []
        for index in range(len(self.values)):
            tem_str = "{0}={1}".format(self.fields[index], self.values[index])
            tem_list.append(tem_str)

        last_str =  ", ".join(tem_list)
        return "NamedTuple({0})".format(last_str)



if __name__ == '__main__':
    fields = ['x', 'y']
    values = [1, 2]
    nt = NamedTuple(values, fields)

    print(nt[0])
    print(nt[1])
    print(nt.x)
    print(nt.y)
    print(nt)
    # print(nt.iterable)
    # print(nt.fields)