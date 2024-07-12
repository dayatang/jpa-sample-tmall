package yang.yu.lang;

import java.util.function.Consumer;

/**
 * 事件总线
 */
public interface EventBus {

    /**
     * 发布事件到事件总线
     * @param event 要发布的事件
     */
    void post(Object event);

    /**
     * 订阅事件
     * @param eventClass 事件类
     * @param consumer 事件消费者
     * @param <T> 事件类型
     */
    <T> void subscribe(Class<T> eventClass, Consumer<T> consumer);

    /**
     * 取消订阅
     * @param eventClass 事件类
     * @param <T> 事件类型
     */
    <T> void unsubscribe(Class<T> eventClass);

}
