package yang.yu.lang;

/**
 * 当试图获取在IoC容器中不存在的Bean实例时抛出此异常。
 */
public class IocInstanceNotFoundException extends IocException {
    public IocInstanceNotFoundException() {
    }

    public IocInstanceNotFoundException(String message) {
        super(message);
    }

    public IocInstanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public IocInstanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public IocInstanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
