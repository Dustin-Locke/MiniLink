package locke.dustin.minilink.repository;

import jakarta.validation.constraints.Min;
import locke.dustin.minilink.entity.MiniLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiniLinkRepository extends JpaRepository< MiniLink, Long > {
    Optional< MiniLink > findByMiniCode ( String MiniCode );
    MiniLink findByOriginalUrl ( String originalUrl );
    Boolean existsByOriginalUrl ( String originalUrl );
    Boolean existsByMiniCode ( String miniCode);
}
