package test8.com;


/**
 * Title: 标识符
 * Description: 演示标识符对类的访问控制
 * Filename:
 */


/**
 * 私有方法 private
 * 公共数据非常危险
 * <p>
 * 重点在于，只要方法是私有的，类的设计者就可以确信它不会在别处使用，
 * 所以可以将其删去。如果一个方法是公共的，就不能简单地将其删除，
 * 因为可能会有其他代码依赖这个方法
 */
public class classDemo1 {

    // 公有方法
    public void method1() {
        System.out.println("这是一个公有的方法！任何类都可以访问。");

    }

    // 受保护的方法
    protected void method2() {
        System.out.println("这是一个受到保护的方法！只有子类可以访问。");

    }

    // 私有方法
    private void method3() {
        System.out.println("这是一个私有的方法！只有类本身才可以访问。");

    }

    public static void main(String[] args) {
        classDemo1 c1 = new classDemo1();
        c1.method1();
        c1.method2();
        c1.method3();
    }

}
