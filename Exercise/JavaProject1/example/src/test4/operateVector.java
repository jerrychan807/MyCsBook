package test4;

import java.util.*;

/**
 * Title: 矢量操作<
 * Description: 演示一个矢量（Vector）的基本操作
 * Filename: operateVector.java
 */

public class operateVector {

    /*
     *<br>方法说明：生成一个4*4的二维Vector，供使用。
     *<br>输入参数：
     *<br>输出变量：Vector
     *<br>其它说明：
     */
    public Vector<Object> buildVector() {
        Vector<Object> vTemps = new Vector<Object>();
        for (int i = 0; i < 4; i++) {
            Vector<Object> vTemp = new Vector<Object>();
            for (int j = 0; j < 4; j++) {
                vTemp.addElement("Vector(" + i + ")(" + j + ")");
            }
            vTemp.addElement(vTemp);
        }
        return vTemps;
    }

    /*
     *<br>方法说明：插入数据
     *<br>输入参数：Vector vTemp 待插入的数据对象
     *<br>输入参数：int iTemp 插入数据的位置
     *<br>输入参数：Object oTemp 插入数据值
     *<br>输出变量：Vector 结果
     *<br>其它说明：如果插入位置超出实例实际的位置将返回null
     */
    public Vector<Object> insert(Vector<Object> vTemp, int iTemp, Object oTemp) {
        if (iTemp > vTemp.size()) {
            System.out.println("数据超界");
            return null;
        } else {
            vTemp.insertElementAt(oTemp, iTemp);
        }
        return vTemp;

    }


    /*
    *<br>方法说明：移除数据
    *<br>输入参数：Vector vTemp 待删除矢量对象
    *<br>输入参数：int iTemp 删除数据的位置
    *<br>输出变量：Vector
    *<br>其它说明：如果删除超界的数据，将返回null
    */
    public Vector<Object> delete(Vector<Object> vTemp, int iTemp) {
        if (iTemp > vTemp.size()) {
            System.out.println("数据超界");
            return null;
        } else {
            vTemp.removeElementAt(iTemp);
        }
        return vTemp;

    }

    /*
    *<br>方法说明：输出信息
    *<br>输入参数：String sTemp 输出信息名称
    *<br>输入参数：Object oTemp 输出信息值
    *<br>返回变量：无
    */
    public void print(String sTemp,)
}
