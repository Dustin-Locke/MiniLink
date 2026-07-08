package locke.dustin.minilink.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import locke.dustin.minilink.type.EventType;
import lombok.NonNull;

import java.time.LocalDateTime;

public record LinkEventRequest(
        @Positive(message = "Mini Link Id must be a positive number.")
        Long miniLinkId,
        String originalUrl,
        String miniCode,
        @NonNull
        LocalDateTime eventDate,
        EventType eventType,
        String ipAddress,
        String userAgent
) {
}
