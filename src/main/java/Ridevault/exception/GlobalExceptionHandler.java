package Ridevault.exception;

import Ridevault.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BikeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBikeNotFound(
            BikeNotFoundException exception) {

        return new ErrorResponse(
                404,
                exception.getMessage()
        );
    }
}
