package locke.dustin.minilink.dto;

public record MiniLinkResponse(
        Long id,
        String originalUrl,
        String miniCode,
        String shortUrl
) {}
