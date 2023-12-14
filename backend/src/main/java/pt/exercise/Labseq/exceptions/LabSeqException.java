package pt.exercise.Labseq.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LabSeqException extends RuntimeException {
    public LabSeqException() {
        super();
    }

    public LabSeqException(String message, Throwable cause) {
        super(message, cause);
    }

    public LabSeqException(String message) {
        super(message);
    }

    public LabSeqException(Throwable cause) {
        super(cause);
    }
}