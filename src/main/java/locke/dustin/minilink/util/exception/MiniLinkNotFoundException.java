package locke.dustin.minilink.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MiniLinkNotFoundException extends ResponseStatusException {

    public MiniLinkNotFoundException ( HttpStatus status, String message ) {
        super(status);
    }}
