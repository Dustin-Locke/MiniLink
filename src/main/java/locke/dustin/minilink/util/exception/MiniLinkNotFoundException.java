package locke.dustin.minilink.util.exception;

import org.springframework.http.HttpStatus;

public class MiniLinkNotFoundException extends RuntimeException {

    public MiniLinkNotFoundException ( String miniLink ) {
        super("The link '" + miniLink + "' cannot be found.");
    }

    public MiniLinkNotFoundException ( Long id ) {
        super("MiniLink id " + id + " cannot be found.");
    }
}
