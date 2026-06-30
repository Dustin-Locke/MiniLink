package locke.dustin.minilink.util;

import locke.dustin.minilink.dto.ErrorResponse;
import locke.dustin.minilink.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger( GlobalExceptionHandler.class );

    @ExceptionHandler(MiniLinkNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMiniLinkNotFound(MiniLinkNotFoundException exception,
                                                         String miniCode) {
        logger.warn( "Code {} not found: {}", miniCode, exception.getMessage() );

        ErrorResponse error = new ErrorResponse(
                exception.getMessage(),
                LocalDateTime.now( )
        );

        return ResponseEntity
                .status( HttpStatus.NOT_FOUND)
                                              .body(error);
    }

    @ExceptionHandler(ExistingAliasException.class)
    public ResponseEntity< ErrorResponse > handleMiniCodeExists (
            ExistingAliasException ex ) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                LocalDateTime.now( )
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(ExistingUrlException.class)
    public ResponseEntity< ErrorResponse > handleUrlExists (
            ExistingUrlException ex ) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                LocalDateTime.now( )
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
}
