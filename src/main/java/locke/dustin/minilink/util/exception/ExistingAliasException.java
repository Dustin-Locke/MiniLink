package locke.dustin.minilink.util.exception;

public class ExistingAliasException extends RuntimeException  {

    public ExistingAliasException( String miniCode ) {
        super("The alias '" + miniCode + "' is already in use.");    }
}
