package yang.yu.lang.eventbus;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import yang.yu.lang.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RxEventBus implements EventBus {

    private final Subject<Object> bus = PublishSubject.create().toSerialized();

    private final Map<String, List<Disposable>> disposables = new HashMap<>();

    @Override
    public void post(@NonNull Object event) {
        bus.onNext(event);
    }

    @Override
    public <T> void subscribe(@NonNull Class<T> eventClass, @NonNull Consumer<T> consumer) {
        Disposable disposable = bus.ofType(eventClass).subscribe(consumer::accept);
        disposables.computeIfAbsent(eventClass.getName(), it -> new ArrayList<>()).add(disposable);
    }

    @Override
    public <T> void unsubscribe(@NonNull Class<T> eventClass) {
        disposables.get(eventClass.getName()).forEach(Disposable::dispose);
    }

}
