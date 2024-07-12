package yang.yu.lang;

public class LockAcquireTimeoutException extends RuntimeException {
    public LockAcquireTimeoutException() {
    }

    public LockAcquireTimeoutException(String message) {
        super(message);
    }

    public LockAcquireTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockAcquireTimeoutException(Throwable cause) {
        super(cause);
    }

    public LockAcquireTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
