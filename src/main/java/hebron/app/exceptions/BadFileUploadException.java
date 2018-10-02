package hebron.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "File was unable to upload")
public class BadFileUploadException extends RuntimeException{
    public BadFileUploadException(Throwable cause) {
        super(cause);
    }

    public BadFileUploadException(String msg){

    }

    public BadFileUploadException() {

    }
}

