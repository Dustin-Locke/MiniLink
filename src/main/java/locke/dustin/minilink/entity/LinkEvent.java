package locke.dustin.minilink.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import locke.dustin.minilink.type.EventType;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "link_event")
public class LinkEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long miniLinkId;

    @NotBlank
    private String originalUrl;

    @NotBlank
    private String miniCode;

    @NonNull
    private LocalDateTime eventDate;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String ipAddress;

    private String userAgent;

    public LinkEvent ( Long miniLinkId,
                       String originalUrl,
                       String miniCode,
                       LocalDateTime eventDate,
                       EventType eventType,
                       String ipAddress,
                       String userAgent) {

        this.miniLinkId = miniLinkId;
        this.originalUrl = originalUrl;
        this.miniCode = miniCode;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

}
