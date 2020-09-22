package test7;


/**
 * Title:  接口和抽象函数
 * Description: 演示继承抽象函数和实现接口
 * Filename: newPlay.java
 */


//接口

/**
 * 接口interface
 * 接口不是类,而是对希望符合这个接口的类的一组需求
 * 接口用来描述类应该做什么,而不指定它们具体怎么做
 * 一个类可以实现(implement)一个或多个接口
 * 接口中的所有方法都自动是public方法,因此不用提供关键词public
 * <p>
 * 为了让类实现一个接口，通常需要完成2个步骤:
 * 1.将类声明为实现给定的接口
 * 2.对接口中的所有方法提供定义
 */

interface player {
    int flag = 1;

    void play();//播放

    void pause();//暂停

    void stop();//停止

}//end

//抽象类

/**
 * 抽象类表示通用属性存在一个严重的问题：每个类只能拓展一个类
 */
abstract class playing {
    public void display(Object oPara) {
        System.out.println(oPara);
    }

    abstract void winRun();
} // end

//继承了playing抽象类和实现类player接口
public class newPlay extends playing implements player {
    public void play() {
        display("newPlay.play()");//这里只是演示，去掉了代码。
    }

    public void pause() {
        display("newPlay.pause()");//这里只是演示，去掉了代码。

    }

    public void stop() {
        display("newPlay.stop()");//这里只是演示，去掉了代码。

    }

    void winRun() {
        display("newPlay.winRun()");//这里只是演示，去掉了代码。

    }

    public static void main(String[] args) {
        newPlay p = new newPlay();
        p.play();
        p.pause();
        p.stop();
        p.winRun();
    }
}
