package yang.yu.tmall.domain.pricing;

public class PricingException extends RuntimeException {
    public PricingException() {
        super();
    }

    public PricingException(String message) {
        super(message);
    }

    public PricingException(String message, Throwable cause) {
        super(message, cause);
    }

    public PricingException(Throwable cause) {
        super(cause);
    }

    protected PricingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
