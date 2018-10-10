## Demo 描述
测试 BizClassLoader 是否会将 Java Agent 放入 classpath 中，测试结果为：**不会**。

## 测试步骤
+ git clone git@github.com:QilongZhang/ark-agent-demo.git
+ cd ark-agent-demo
+ mvn clean package
+ java -javaagent:sample-agent/target/sample-agent-1.0.0.jar  -jar sample-ark/target/sample-ark-1.0.0.jar

可以看到如下打印结果：
```text
Hi, I'm a simple agent
Sofa-Middleware-Log SLF4J : Actual binding is of type [ com.alipay.sofa.ark Log4j ]
log4j:WARN No appenders could be found for logger (com.alipay.sofa.common.log).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Hi, I'm a simple ark project.
"me.qlong.tech.SampleArk is loaded by "com.alipay.sofa.ark.container.service.classloader.BizClassLoader@759ebb3d
"me.qlong.tech.SampleAgent is loaded by "java.net.URLClassLoader@7e774085
Ark container started in 560 ms.
```

查看测试类 `me.qlong.tech.SampleArk` 代码：
```java
public class SampleArk {

    public static void main(String[] args) {
        System.out.println("Hi, I'm a simple ark project.");
        try {
            System.out.println("\"me.qlong.tech.SampleArk is loaded by \"" + SampleArk.class.getClassLoader());
            ClassLoader classLoader = SampleArk.class.getClassLoader().loadClass("me.qlong.tech.SampleAgent").getClassLoader();
            System.out.println("\"me.qlong.tech.SampleAgent is loaded by \"" + classLoader);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class me.qlong.tech.SampleAgent not found!!!");
        }
    }

}
```

结合代码逻辑和测试输出结果可以看到，`me.qlong.tech.SampleAgent` 最终是被 AgentClassLoader(URLClassLoader类型) 加载。
