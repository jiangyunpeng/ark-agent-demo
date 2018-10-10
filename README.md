## Demo 描述
测试 BizClassLoader 是否会将 Java Agent 放入 classpath 中，测试结果为：**会**。

## 测试步骤
+ git clone git@github.com:QilongZhang/ark-agent-demo.git
+ cd ark-agent-demo
+ mvn clean package
+ 通过 eclipse IDE 启动，在run config 中加入-javaagent:sample-agent/target/sample-agent-1.0.0.jar 

可以看到如下打印结果：
```text
objc[80483]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java (0x10fda34c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/libinstrument.dylib (0x10fdf24e0). One of the two will be used. Which one is undefined.
Hi, I'm a simple agent
Sofa-Middleware-Log SLF4J : Actual binding is of type [ com.alipay.sofa.ark Log4j ]
log4j:WARN No appenders could be found for logger (com.alipay.sofa.common.log).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
Sofa-Middleware-Log SLF4J : com.alipay.sofa.ark log configuration: log-conf.xml.dev
2018-10-10 12:59:04,243 INFO  main                             - Begin to start ArkServiceContainer
2018-10-10 12:59:04,773 INFO  main                             - Init Service: com.alipay.sofa.ark.container.session.StandardTelnetServerImpl
2018-10-10 12:59:04,795 INFO  main                             - Listening on port: 1234
2018-10-10 12:59:04,798 INFO  main                             - Init Service: com.alipay.sofa.ark.container.service.plugin.PluginDeployServiceImpl
2018-10-10 12:59:04,798 INFO  main                             - Init Service: com.alipay.sofa.ark.container.service.biz.BizDeployServiceImpl
2018-10-10 12:59:04,798 INFO  main                             - Init Service: com.alipay.sofa.ark.container.service.classloader.ClassloaderServiceImpl
2018-10-10 12:59:04,804 INFO  main                             - Finish to start ArkServiceContainer
2018-10-10 12:59:04,817 INFO  main                             - Start to process pipeline stage: com.alipay.sofa.ark.container.pipeline.HandleArchiveStage
2018-10-10 12:59:04,829 INFO  main                             - Finish to process pipeline stage: com.alipay.sofa.ark.container.pipeline.HandleArchiveStage
2018-10-10 12:59:04,829 INFO  main                             - Start to process pipeline stage: com.alipay.sofa.ark.container.pipeline.SystemPropertiesSettingStage
2018-10-10 12:59:04,830 INFO  main                             - Finish to process pipeline stage: com.alipay.sofa.ark.container.pipeline.SystemPropertiesSettingStage
2018-10-10 12:59:04,830 INFO  main                             - Start to process pipeline stage: com.alipay.sofa.ark.container.pipeline.RegisterServiceStage
2018-10-10 12:59:04,838 INFO  main                             - Inject {field='bizManagerService'} of {service='ServiceMetadata{service='com.alipay.sofa.ark.spi.service.biz.BizDeployer', provider='ServiceProvider{provider='Ark Container', order=100}'}'} success!
2018-10-10 12:59:04,838 INFO  main                             - Service: com.alipay.sofa.ark.spi.service.biz.BizDeployer publish by: ServiceProvider{provider='Ark Container', order=100} succeed
2018-10-10 12:59:04,838 INFO  main                             - Finish to process pipeline stage: com.alipay.sofa.ark.container.pipeline.RegisterServiceStage
2018-10-10 12:59:04,838 INFO  main                             - Start to process pipeline stage: com.alipay.sofa.ark.container.pipeline.DeployPluginStage
2018-10-10 12:59:04,838 INFO  main                             - Finish to process pipeline stage: com.alipay.sofa.ark.container.pipeline.DeployPluginStage
2018-10-10 12:59:04,839 INFO  main                             - Start to process pipeline stage: com.alipay.sofa.ark.container.pipeline.DeployBizStage
2018-10-10 12:59:04,840 INFO  main                             - BizDeployer='{name='DefaultBizDeployer', provider='Ark Container'}' is starting.
2018-10-10 12:59:04,840 INFO  main                             - Begin to start biz: Startup In IDE
Hi, I'm a simple ark project.
"me.qlong.tech.SampleArk is loaded by "com.alipay.sofa.ark.container.service.classloader.BizClassLoader@5b441c86
"me.qlong.tech.SampleAgent is loaded by "com.alipay.sofa.ark.container.service.classloader.BizClassLoader@5b441c86
2018-10-10 12:59:04,868 INFO  main                             - Finish to start biz: Startup In IDE
2018-10-10 12:59:04,868 INFO  main                             - Finish to process pipeline stage: com.alipay.sofa.ark.container.pipeline.DeployBizStage
Ark container started in 789 ms.
2018-10-10 12:59:04,869 INFO  Thread-0                         - Begin to stop ArkServiceContainer
2018-10-10 12:59:04,869 INFO  Thread-0                         - Dispose service: com.alipay.sofa.ark.container.service.classloader.ClassloaderServiceImpl
2018-10-10 12:59:04,869 INFO  Thread-0                         - Dispose service: com.alipay.sofa.ark.container.service.biz.BizDeployServiceImpl
2018-10-10 12:59:04,869 INFO  Thread-0                         - BizDeployer='{name='DefaultBizDeployer', provider='Ark Container'}' is stopping.
2018-10-10 12:59:04,869 INFO  Thread-0                         - Begin to stop biz: Startup In IDE
2018-10-10 12:59:04,870 INFO  Thread-0                         - Finish to stop biz: Startup In IDE
2018-10-10 12:59:04,870 INFO  Thread-0                         - Dispose service: com.alipay.sofa.ark.container.service.plugin.PluginDeployServiceImpl
2018-10-10 12:59:04,870 INFO  Thread-0                         - Dispose service: com.alipay.sofa.ark.container.session.StandardTelnetServerImpl
2018-10-10 12:59:04,875 INFO  Thread-0                         - Finish to stop ArkServiceContainer



```

查看测试类 `me.qlong.tech.SampleArk` 代码：
```java
public class SampleArk {

    public static void main(String[] args) {
        SofaArkBootstrap.launch(args);
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

结合代码逻辑和测试输出结果可以看到，`me.qlong.tech.SampleAgent` 最终是被 BizClassLoader(URLClassLoader类型) 加载。
