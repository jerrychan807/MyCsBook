
## 使用maven手工创建第一个项目:

前提：必须遵循maven约定

clazz_web
    /src/main/java    用来存放运行时java代码
    /src/main/resources 用来存放运行时配置文件
    /src/test/java      用来存放测试时java代码
    /src/test/resources 用来存放测试时配置文件
    pom.xml             maven项目配置文件



按照目录规范编写测试项目

```
.                                                                                                           
├── pom.xml                                                                                                 
└── src                                                                                                     
    ├── main                                                                                                
    │   ├── java                                                                                            
    │   │   └── Hello.java                                                                                  
    │   └── resources                                                                                       
    └── test                                                                                                
        ├── java                                                                                            
        └── resources   
```



清空本地目录
![20200922233652](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200922233652.png)

执行`mvn compile`
![20200922234743](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200922234743.png)

build success 代表构建成功

![20200922234706](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200922234706.png)

---

通过项目构建过程得知如下信息：


![20200923000045](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200923000045.png)



![20200922235953](https://raw.githubusercontent.com/jerrychan807/imggg/master/image/20200922235953.png)



----

## maven clean

把上一次构建的产物给清除掉，清除掉target目录

