package todoapp.task;

import todoapp.core.DomainException;

public class TaskException extends DomainException {

    private static final long serialVersionUID = -8193619668110180987L;

    public TaskException() {
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    public TaskException(int code, String message, String detailedMessage) {
        super(code, message, detailedMessage);
    }

}
