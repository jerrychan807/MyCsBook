package Serializable_example;

//Java反序列化漏洞原理解析https://xz.aliyun.com/t/6787

import java.io.*;

public class EvilMain {
    public static void main(String[] args) throws Exception {
        Evil evil = new Evil();
        evil.cmd = "calc";

        byte[] serializeData = serialize(evil);
        System.out.println(serializeData);
        unserialize(serializeData);
    }

    public static byte[] serialize(final Object obj) throws Exception {
        ByteArrayOutputStream btout = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(btout);
        objOut.writeObject(obj);
        return btout.toByteArray();
    }

    public static Object unserialize(final byte[] serialized) throws Exception {
        ByteArrayInputStream btin = new ByteArrayInputStream(serialized);
        ObjectInputStream objIn = new ObjectInputStream(btin);
        return objIn.readObject();
    }
}