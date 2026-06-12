package locke.dustin.minilink.repository;

import locke.dustin.minilink.entity.MiniLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MiniLinkRepository extends JpaRepository< MiniLink, Long > {
    Optional< MiniLink > findByMiniCode ( String MiniCode );

    Boolean existsByMiniCode ( String miniCode);
}
