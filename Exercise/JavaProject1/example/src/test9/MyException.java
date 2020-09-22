package test9;


/**
 * Title: 捕获异常和实现自己的异常
 * Description: 通过继承Exception类来实现自己的异常类。并使用try－catch来捕获这个异常。
 * Filename:
 */

public class MyException extends Exception {
    private static final long serialVersionUID = 1L;

    public MyException() {

    }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String msg, int x) {
        super(msg);
        i = x;
    }

    public int val() {
        return i;
    }

    private int i;
}

class DemoException {
    /**
     * 方法说明：使用MyException类中默认的构造器
     */

    public static void a() throws MyException {
        System.out.println("Throwing MyException from a()");
        throw new MyException();

    }

    /**
     * 方法说明：使用MyException类中带信息的构造器
     */
    public static void b() throws MyException {
        System.out.println("Throwing MyException from b()");
        throw new MyException("Originated in b()");
    }

    /**
     * 方法说明：使用了MyException中有编码的构造器
     */
    public static void c() throws MyException {
        System.out.println("Throwing MyException from c()");
        throw new MyException("Originated in c()", 47);
    }


    public static void main(String[] args) {
        try {
            a();
        } catch (MyException e) {
            e.getMessage();
        }

        try {
            b();
        } catch (MyException e) {
            e.toString();
        }

        try {
            c();
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("error code: " + e.val());
        }

    }


} // end
