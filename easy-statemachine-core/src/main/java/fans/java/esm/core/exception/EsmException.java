package fans.java.esm.core.exception;

/**
 * 状态机自定义异常
 *
 * @author sss
 */
public class EsmException extends RuntimeException {

    public EsmException() {
        super();
    }

    public EsmException(String message) {
        super(message);
    }

    public EsmException(String message, Throwable cause) {
        super(message, cause);
    }

    public EsmException(Throwable cause) {
        super(cause);
    }
}
