package locke.dustin.minilink.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import locke.dustin.minilink.type.EventType;
import lombok.NonNull;

import java.time.LocalDateTime;

public record LinkEventResponse(
        Long linkEventId,
        Long miniLinkId,
        String originalUrl,
        String miniCode,
        LocalDateTime eventDate,
        EventType eventType,
        String ipAddress,
        String userAgent
        ) {
}
