package iuliia.questionsservice.error;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@RestControllerAdvice
public class QuestionsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IOException.class, InterruptedException.class})
    public ResponseEntity<?> handleIOException(Exception exception, WebRequest request) {
        String body = "Error getting questions from StackOverflow";
        return handleExceptionInternal(exception, body,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
