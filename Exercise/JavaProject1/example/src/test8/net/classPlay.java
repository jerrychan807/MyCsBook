package test8.net;

import test8.com.classDemo1;

/**
 * Title: 标识符
 * Description: 演示标识符对类的访问控制
 * Filename:
 */
public class classPlay {
    public static void main(String[] args) {
        classDemo1 d = new classDemo1();
        d.method1();
//        d.method2();  //java: method2() has protected access in test8.com.classDemo1
//        d.method3();  //java: method3() has private access in test8.com.classDemo1
    }
}