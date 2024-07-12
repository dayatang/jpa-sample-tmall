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


    <T> void subscribe(Class<T> eventClass, Consumer<T> consumer);

    <T> void unsubscribe(Class<T> eventClass);

}
