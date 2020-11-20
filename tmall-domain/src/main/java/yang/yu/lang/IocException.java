package yang.yu.lang;

/**
 * IoC容器异常。当访问IoC容器（Spring，Guice等）发生异常时抛出本异常或其子类实例。
 */
public class IocException extends RuntimeException {
	public IocException() {
	}

	public IocException(String message) {
		super(message);
	}

	public IocException(String message, Throwable cause) {
		super(message, cause);
	}

	public IocException(Throwable cause) {
		super(cause);
	}

	public IocException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
