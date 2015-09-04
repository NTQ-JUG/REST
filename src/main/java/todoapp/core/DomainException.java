package todoapp.core;

public class DomainException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private String detailedMessage;

    public DomainException() {
    }

    public DomainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(Throwable cause) {
        super(cause);
    }

    public DomainException(int code, String message, String detailedMessage) {
        this.code = code;
        this.message = message;
        this.detailedMessage = detailedMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

}
