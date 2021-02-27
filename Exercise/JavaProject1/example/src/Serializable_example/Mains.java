package Serializable_example;

import java.io.*;
//Java反序列化漏洞原理解析https://xz.aliyun.com/t/6787

public class Mains {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setName("leixiao");

        byte[] serializeData = serialize(user);
        FileOutputStream fout = new FileOutputStream("user.bin");
        fout.write(serializeData);
        fout.close();
        User user2 = (User) unserialize(serializeData);
        System.out.println(user2.getName());
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
