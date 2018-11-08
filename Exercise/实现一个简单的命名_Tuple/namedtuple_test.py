# -*- coding:utf-8 -*-

# @Time    : 2018/11/8 1:58 PM
# @Author  : jerry


import unittest

from namedtuple import NamedTuple


class TestNamedTuple(unittest.TestCase):
    def test_features(self):
        fields = ['x', 'y']
        values = [1, 2]
        nt = NamedTuple(values, fields)

        self.assertEqual(nt[0], 1)
        self.assertEqual(nt[1], 2)

        self.assertEqual(nt.x, 1)
        self.assertEqual(nt.y, 2)
        #
        # self.assertEqual(repr(nt), 'NamedTuple(x=1, y=2)')