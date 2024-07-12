package yang.yu.lang;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.function.Consumer;

/**
 * 事件总线
 */
public interface EventBus {

    /**
     * 发布事件到事件总线
     * @param event 要发布的事件
     */
    void post(@NonNull Object event);


    <T> void subscribe(@NonNull Class<T> eventClass, @NonNull Consumer<T> consumer);

    <T> void unsubscribe(@NonNull Class<T> eventClass);

}
