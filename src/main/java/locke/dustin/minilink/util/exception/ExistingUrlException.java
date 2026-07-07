package locke.dustin.minilink.util.exception;

public class ExistingUrlException extends RuntimeException  {

    public ExistingUrlException( String url ) {
        super("The url '" + url + "' is already in use.");    }
}
