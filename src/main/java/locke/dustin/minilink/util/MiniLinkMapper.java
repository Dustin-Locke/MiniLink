package locke.dustin.minilink.util;

import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MiniLinkMapper {

    private final String baseUrl;

    public MiniLinkMapper(
            @Value("${minilink.base-url}") String baseUrl
                         ) {
        this.baseUrl = baseUrl;
    }

    public MiniLinkResponse toResponse(MiniLink entity) {
        String miniLink = baseUrl + entity.getMiniCode();

        return new MiniLinkResponse(
                entity.getId(),
                entity.getOriginalUrl(),
                entity.getMiniCode(),
                miniLink
        );
    }
}
