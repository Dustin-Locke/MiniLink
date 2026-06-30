package locke.dustin.minilink.util.exception;

import org.springframework.http.HttpStatus;

public class MiniLinkNotFoundException extends RuntimeException {

    public MiniLinkNotFoundException ( String miniLink ) {
        super("The link '" + miniLink + "' is already in use.");
    }}
