package locke.dustin.minilink.service;

import locke.dustin.minilink.dto.*;
import locke.dustin.minilink.repository.LinkEventRepository;
import locke.dustin.minilink.type.EventType;
import locke.dustin.minilink.util.LinkEventMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final LinkEventRepository linkEventRepository;

    public AnalyticsService(LinkEventRepository linkEventRepository) {
        this.linkEventRepository = linkEventRepository;
    }


    public AnalyticsResponse getAnalytics ( ) {

        long totalLinksCreated =
                linkEventRepository.countByEventType( EventType.CREATED );

        long totalClicks =
                linkEventRepository.countByEventType(EventType.ACCESSED);


        List< LinkEventResponse > recentEvents =
                linkEventRepository
                        .findAllByOrderByEventDateDesc( PageRequest.of( 0, 5 ) )
                        .stream()
                        .map( LinkEventMapper::toResponse )
                        .toList();


        List< PopularLinkResponse > popularLinks =
                linkEventRepository.findMostAccessedLinks()
                                   .stream()
                                   .map(row -> new PopularLinkResponse(
                                           (String) row[0],
                                           ((Number) row[1]).longValue()
                                   ))
                                   .toList();


        return new AnalyticsResponse(
                totalLinksCreated,
                totalClicks,
                popularLinks,
                recentEvents
        );
    }
}
