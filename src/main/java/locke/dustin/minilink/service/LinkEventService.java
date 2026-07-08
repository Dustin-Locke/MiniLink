package locke.dustin.minilink.service;

import jakarta.servlet.http.HttpServletRequest;
import locke.dustin.minilink.dto.*;
import locke.dustin.minilink.entity.LinkEvent;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.LinkEventRepository;
import locke.dustin.minilink.type.EventType;
import locke.dustin.minilink.util.LinkEventMapper;
import locke.dustin.minilink.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LinkEventService {

    private final LinkEventRepository linkEventRepository;

    public LinkEventResponse create ( MiniLink miniLink,
                                      EventType eventType,
                                      HttpServletRequest httpRequest ) {

        String ipAddress = RequestUtils.getClientIp( httpRequest );
        String userAgent = RequestUtils.getUserAgent( httpRequest );

        LinkEvent linkEvent = new LinkEvent();
        linkEvent.setMiniLinkId( miniLink.getId( ) );
        linkEvent.setOriginalUrl( miniLink.getOriginalUrl( ) );
        linkEvent.setMiniCode( miniLink.getMiniCode( ) );
        linkEvent.setEventDate( LocalDateTime.now() );
        linkEvent.setEventType( eventType );
        linkEvent.setIpAddress( ipAddress );
        linkEvent.setUserAgent( userAgent );

        linkEventRepository.save( linkEvent );

        return LinkEventMapper.toResponse( linkEvent );
    }

    public LinkEventResponse create ( MiniLinkResponse miniLinkResponse,
                                      EventType eventType,
                                      HttpServletRequest httpRequest) {

        String ipAddress = RequestUtils.getClientIp( httpRequest );
        String userAgent = RequestUtils.getUserAgent( httpRequest );

        LinkEvent linkEvent = new LinkEvent();
        linkEvent.setMiniLinkId( miniLinkResponse.id( ) );
        linkEvent.setOriginalUrl( miniLinkResponse.originalUrl( ) );
        linkEvent.setMiniCode( miniLinkResponse.miniCode( ) );
        linkEvent.setEventDate( LocalDateTime.now() );
        linkEvent.setEventType( eventType );
        linkEvent.setIpAddress( ipAddress );
        linkEvent.setUserAgent( userAgent );

        linkEventRepository.save( linkEvent );

        return LinkEventMapper.toResponse( linkEvent );
    }

    public List< LinkEventResponse > getEventsForDate ( LocalDate date ) {

        LocalDateTime start = date.atStartOfDay( );
        LocalDateTime end   = date.plusDays( 1 ).atStartOfDay( );

        List< LinkEvent >
                events =
                linkEventRepository.findByEventDateBetween(
                        start,
                        end );

        return LinkEventMapper.toResponseList( events );
    }

    public List< LinkEventResponse > getEventsByType ( EventType eventType ) {

        List< LinkEvent >
                events =
                linkEventRepository.findByEventType( eventType );

        return events
                .stream( )
                .map( LinkEventMapper::toResponse )
                .collect( Collectors.toList( ) );
    }

    public List< LinkEventResponse > getEventsByTypeForDate (
            EventType eventType,
            LocalDate date ) {

        LocalDateTime start = date.atStartOfDay( );
        LocalDateTime end   = date.plusDays( 1 ).atStartOfDay( );

        List< LinkEvent >
                events =
                linkEventRepository.findByEventTypeAndEventDateBetween( eventType,
                                                                        start,
                                                                        end );

        return LinkEventMapper.toResponseList( events );
    }

    public List<LinkEventResponse> getMostRecentEvents(int startingEvent, int numberOfEvents) {
        Pageable topFive = PageRequest.of(startingEvent, numberOfEvents);

        List<LinkEvent> events =
                linkEventRepository.findAllByOrderByEventDateDesc(topFive);

        return LinkEventMapper.toResponseList( events );
    }
}
