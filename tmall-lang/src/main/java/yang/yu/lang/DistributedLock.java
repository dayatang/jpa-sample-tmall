package yang.yu.lang;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 分布式锁接口
 */
public interface DistributedLock {

    long DEFAULT_WAIT_TIME = 10;

    /**
     * 使用指定超时时间配合Redis锁实现业务操作（带出参）
     *
     * @param key 键值
     * @param timeout 超时时间
     * @param timeUnit 时间类型
     * @param supplier 带出参的业务操作
     * @return 业务响应
     * @param <T> 出参数据类型
     */
    <T> T callInLock(String key, long timeout, TimeUnit timeUnit, Supplier<T> supplier);

    /**
     * 使用默认超时时间配合Redis锁实现业务操作（带出参）
     *
     * @param key 键值
     * @param supplier 带出参的业务操作
     * @return 业务响应
     * @param <T> 出参数据类型
     */
    default <T> T callInLock(String key, Supplier<T> supplier) {
        return callInLock(key, DEFAULT_WAIT_TIME, TimeUnit.SECONDS, supplier);
    }

    /**
     * 使用指定超时时间配合Redis锁实现业务操作（不带出参）
     *
     * @param key 键值
     * @param timeout 超时时间
     * @param timeUnit 时间类型
     * @param consumer 不带出参的业务操作
     */
    void runInLock(String key, long timeout, TimeUnit timeUnit, Consumer<Void> consumer);

    /**
     * 使用默认超时时间配合Redis锁实现业务操作（不带出参）
     *
     * @param key 键值
     * @param consumer 业务操作（不带出参）
     */
    default void runInLock(String key, Consumer<Void> consumer) {
        runInLock(key, DEFAULT_WAIT_TIME, TimeUnit.SECONDS, consumer);
    }
}
