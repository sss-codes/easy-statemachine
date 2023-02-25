# easy-statemachine

#### 介绍

Easy Statemachine是一个轻量级的、基于事件驱动的状态机框架，可以便捷的嵌入到Spring项目中，统一控制状态流转，避免随处编写状态更改语句造成后续难以修改扩展。
本项目将每个操作定义为一个事件，每个事件有对应的Handler，方便扩展。
比如"审核"事件，有对应的审核Handler，在此Handler中，开发人员只需关注：
**1.当前事件是什么？**
**2.对应哪些状态？**
**3.目标状态是什么？**
**4.需要执行什么操作？**
清楚这4个问题后，即可完成一个操作事件Handler的编写，剩下的就交给Easy Statemachine来处理吧~~~

#### 设计理念

基于事件驱动思想，每次操作触发都是一个事件。事件的执行将由当前状态转为目标状态，并伴随一系列业务逻辑的处理。

#### QuickStart

##### 1.引入依赖

```xml
<dependency>
    <groupId>fans.java</groupId>
    <artifactId>easy-statemachine-core</artifactId>
    <version>${statemachine.version}</version>
</dependency>
```

##### 2.包扫描

启动类添加配置：scanBasePackages = {"fans.java.esm"}

##### 3.状态枚举（示例）

```java
public enum StateEnum {
    INIT("ESM000", "任务初始化"),
    WAIT_PAY("ESM001", "待支付"),
    PAYED("ESM002", "已支付"),
    CANCELED("ESM003", "取消"),
    ;
    private final String stateCode;
    private final String stateName;
}
```

##### 4.事件枚举（示例）

```java
public enum EventEnum {
    CREATE_ORDER("CREATE_ORDER", "下单"),
    PAY("PAY", "支付"),
    MODIFY_INFO("MODIFY_INFO", "修改资料"),
    ;
    private final String eventCode;
    private final String eventName;
}
```

##### 5.事件Handler编写

事件分为两类：
①事件执行后，状态会切换，sourceState → targetSource
②事件执行后，状态不变

###### 5.1 事件执行，状态切换

需要继承AbstractExternalEsmHandler<S, E, C, R>抽象类，然后实现fromStates、toState、onEvent、action方法。其中AbstractExternalEsmHandler有四种泛型，分别是状态枚举、事件枚举、状态机自定义上下文、状态机执行后返回结果类型。
fromStates(): 源状态，状态枚举数组，当前事件从哪些状态执行
toState(): 目标状态
onEvent(): 基于什么事件
action(): 具体执行的业务逻辑
示例如下：

```java
@Component
@Slf4j
public class PayHandler extends AbstractExternalEsmHandler<StateEnum, EventEnum, EsmContext, StateEnum> {
    @Override
    public StateEnum[] fromStates() {
        return new StateEnum[]{StateEnum.WAIT_PAY};
    }
    @Override
    public StateEnum toState() {
        return StateEnum.PAYED;
    }
    @Override
    public EventEnum onEvent() {
        return EventEnum.PAY;
    }
    @Override
    public Action<StateEnum, EventEnum, EsmContext, StateEnum> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}付款{}", eventUser.getOperatorName(), indexId);
            return to;
        };
    }
}
```

###### 5.2事件执行，状态不切换

需要继承AbstractInternalEsmHandler<S, E, C, R>抽象类，然后实现 withinStates、onEvent、action方法。
withinStates(): 在哪些状态中执行这个Handler，执行完后，状态不变。
AbstractInternalEsmHandler中的四种泛型同5.1。
示例如下：

```java
@Component
@Slf4j
public class ModifyInfoHandler extends AbstractInternalEsmHandler<StateEnum, EventEnum, EsmContext, String> {
    @Override
    public StateEnum[] withinStates() {
        return new StateEnum[]{StateEnum.WAIT_PAY, StateEnum.PAYED};
    }
    @Override
    public EventEnum onEvent() {
        return EventEnum.MODIFY_INFO;
    }
    @Override
    public Action<StateEnum, EventEnum, EsmContext, String> action() {
        return (from, to, event, context) -> {
            EventUser eventUser = context.getEventUser();
            String indexId = context.getIndexId();
            log.info("{}修改资料{}", eventUser.getOperatorName(), indexId);
            return "修改资料成功";
        };
    }
}
```

##### 6.实现持久化接口

实现TransitionPersistInterface<S, E, C>接口，beforeTransit方法将在上述action方法之前执行，afterTransit将在action方法之后执行。
可以在beforeTransit和afterTransit方法中统一编写对于状态的update操作、事件的日志记录等等。
示例如下：

```java
@Component
@Slf4j
public class TransitionPersistImpl implements TransitionPersistInterface<StateEnum, EventEnum, EsmContext> {
    @Override
    public void beforeTransit(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("TransitionPersistImpl-beforeTransit,{},{},{},{}", sourceState, targetState, event, context);
    }
    @Override
    public void afterTransit(StateEnum sourceState, StateEnum targetState, EventEnum event, EsmContext context) {
        log.info("TransitionPersistImpl-afterTransit,{},{},{},{}", sourceState, targetState, event, context);
    }
}
```

##### 7.调用状态机

调用EsmUtil.processEvent()方法即可。
示例如下：

```java
EventUser eventUser = new EventUser();
eventUser.setOperatorCode("333L");
eventUser.setOperatorName("张三");
eventUser.setTargetUserCode("444L");
eventUser.setTargetUserName("李四");
EsmContext esmContext = new EsmContext(eventUser, "123", "备注");
String o1 = EsmUtil.processEvent(StateEnum.INIT, EventEnum.CREATE_ORDER, esmContext);
StateEnum o2 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.PAY, esmContext);
String o3 = EsmUtil.processEvent(StateEnum.WAIT_PAY, EventEnum.MODIFY_INFO, esmContext);
```

具体请参考easy-statemachine-demo

#### 参与贡献


1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 参考学习

[spring-statemachine](https://github.com/spring-projects/spring-statemachine)
[COLA](https://github.com/alibaba/COLA)


#### 特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5. Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)

