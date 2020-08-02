package test2;


/**
 * Title: Java语言流程演示
 * Description: 演示Java中几种常用的流程控制操作
 * Filename: flowDome.java
 */

public class flowDemo {
    public static void main(String[] args) {
        int iPara1, iPara2, iEnd;
        if (args.length != 3) {
            System.out.println("Use: Java flowDemo parameter1 parameter2 circle");
            System.out.println("parameter1 : 比较条件1, 数字类型");
            System.out.println("parameter2 : 比较条件2, 数字类型");
            System.out.println("circle : 循环次数");
            System.out.println("ego:java flowDome 1 2 5");
            return;
        } else {
            iPara1 = Integer.parseInt(args[0]);
            iPara2 = Integer.parseInt(args[1]);
            iEnd = Integer.parseInt(args[2]);
        }

        // if语句
        if (iPara2 > iPara1) {
            System.out.println("if 条件满足!");
            System.out.println("第2个数比第1个数大");
        } else {
            System.out.println("if 条件不满足!");
            System.out.println("第2个数比第1个数小");
        }

        // for循环操作
        for (int i = 0; i < iEnd; i++) {
            System.out.println("这是for 第" + i + "次循环");
        }

        // while循环操作
        int i = 0;
        while (i < iEnd) {
            System.out.println("这是while 第" + i + "次循环");
            i++;
        }

        // do-while循环操作
        int j = 0;
        do {
            System.out.println("这是do-while 第" + j + "次循环");
            j++;
        } while (j < iEnd);

    }
}
