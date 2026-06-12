package locke.dustin.minilink.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record MiniLinkRequest( @NotBlank @URL String originalUrl, String customAlias) { }
