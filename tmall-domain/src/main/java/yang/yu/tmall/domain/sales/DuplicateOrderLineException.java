package yang.yu.tmall.domain.sales;

public class DuplicateOrderLineException extends RuntimeException {
    public DuplicateOrderLineException() {
    }

    public DuplicateOrderLineException(String message) {
        super(message);
    }

    public DuplicateOrderLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateOrderLineException(Throwable cause) {
        super(cause);
    }

    public DuplicateOrderLineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
