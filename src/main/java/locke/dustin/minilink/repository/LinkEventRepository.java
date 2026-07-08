package locke.dustin.minilink.repository;

import locke.dustin.minilink.entity.LinkEvent;
import locke.dustin.minilink.type.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LinkEventRepository extends JpaRepository< LinkEvent, Long > {


    List<LinkEvent> findByEventDateBetween(LocalDateTime start, LocalDateTime end);

    List<LinkEvent> findByEventType ( EventType eventType );

    List<LinkEvent> findByEventTypeAndEventDateBetween( EventType eventType,
                                                        LocalDateTime start,
                                                        LocalDateTime end);

    List<LinkEvent> findAllByOrderByEventDateDesc ( Pageable pageable );

    long countByEventType(EventType eventType);

    @Query("""
       SELECT e.miniCode, COUNT(e)
       FROM LinkEvent e
       WHERE e.eventType = 'ACCESSED'
       GROUP BY e.miniCode
       ORDER BY COUNT(e) DESC
       LIMIT 5
       """)
    List<Object[]> findMostAccessedLinks();
}
