package yang.yu.lang.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yang.yu.lang.DistributedLock;
import yang.yu.lang.LockAcquireTimeoutException;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

//@Named
public class RedissonLock implements DistributedLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLock.class);

    private final RedissonClient redissonClient;

    public RedissonLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 使用指定超时时间配合Redis锁实现业务操作（带出参）
     *
     * @param key 键值
     * @param time 超时时间
     * @param timeUnit 时间类型
     * @param supplier 带出参的业务操作
     * @return 业务响应
     * @param <T> 出参数据类型
     */
    public <T> T callInLock(
            String key,
            long time,
            TimeUnit timeUnit,
            Supplier<T> supplier)  {

        RLock lock = redissonClient.getLock(key);
        try {
            //加锁，防止并发问题
            if (lock.tryLock(time, timeUnit)) {
                return supplier.get();
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException interruptedException) {
            LOGGER.warn("获取锁失败【{}】", key, interruptedException);
            Thread.currentThread().interrupt();
            throw new LockAcquireTimeoutException();
        } finally {
            try {
                // 持有锁的场合，释放锁
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            } catch (Exception exception) {
                // 释放锁异常时不向上抛出异常，保证业务正常提交
                LOGGER.warn("释放锁异常【{}】", key, exception);
            }
        }
    }

    /**
     * 使用指定超时时间配合Redis锁实现业务操作（不带出参）
     *
     * @param key 键值
     * @param time 超时时间
     * @param timeUnit 时间类型
     * @param consumer 不带出参的业务操作
     */
    public void runInLock(
            String key,
            long time,
            TimeUnit timeUnit,
            Consumer<Void> consumer) {

        RLock lock = redissonClient.getLock(key);
        try {
            //加锁，防止并发问题
            if (lock.tryLock(time, timeUnit)) {
                consumer.accept(null);
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException interruptedException) {
            LOGGER.warn("获取锁失败【{}】", key, interruptedException);
            Thread.currentThread().interrupt();
            throw new LockAcquireTimeoutException();
        } finally {
            try {
                // 持有锁的场合，释放锁
                if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            } catch (Exception exception) {
                // 释放锁异常时不向上抛出异常，保证业务正常提交
                LOGGER.warn("释放锁异常【{}】", key, exception);
            }
        }
    }

}
