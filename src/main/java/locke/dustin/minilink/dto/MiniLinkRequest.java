package locke.dustin.minilink.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record MiniLinkRequest(
        @NotBlank String originalUrl,
        String customAlias) { }
