package locke.dustin.minilink.util;

import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;

public class MiniLinkMapper {

    private static final String BASE_URL = "http://localhost:8080/api/";

    public static MiniLinkResponse toResponse(MiniLink entity) {
        String miniLink = BASE_URL + entity.getMiniCode();

        return new MiniLinkResponse(
                entity.getId(),
                entity.getOriginalUrl(),
                entity.getMiniCode(),
                miniLink
        );
    }
}
