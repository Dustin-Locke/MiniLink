package locke.dustin.minilink.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "minilink")
public class MiniLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "mini_code", unique = true)
    private String miniCode;
}
