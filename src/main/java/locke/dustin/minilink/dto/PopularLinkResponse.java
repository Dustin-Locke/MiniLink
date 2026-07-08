package locke.dustin.minilink.dto;

public record PopularLinkResponse(
        String miniCode,
        long clicks
) {}