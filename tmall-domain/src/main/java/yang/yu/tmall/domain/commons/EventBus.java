package yang.yu.tmall.domain.commons;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

/**
 * 事件总线
 */
public class EventBus {

    private static final @NonNull Subject<Object> bus = PublishSubject.create().toSerialized();

    /**
     * 发布事件到事件总线
     * @param event 要发布的事件
     */
    public static final void post(Object event) {
        bus.onNext(event);
    }

    /**
     * 获取特定类型事件的Observable，供各种客户端监听。
     * @param eventType 要监听的事件的类
     * @param <T> 事件类型
     * @return 一个元素类型为T的Observable
     */
    public static final <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

}
