package yang.yu.lang;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface DistributedLock {

    <T> T callInLock(String key, Supplier<T> supplier, long timeout, TimeUnit timeUnit);

    <T> T callInLock(String key, Supplier<T> supplier);


    <T> void runInLock(String key, Consumer<T> supplier, long timeout, TimeUnit timeUnit);

    <T> void runInLock(String key, Consumer<T> supplier);

}
