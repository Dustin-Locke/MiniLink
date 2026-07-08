package locke.dustin.minilink.util;


import locke.dustin.minilink.dto.LinkEventRequest;
import locke.dustin.minilink.dto.LinkEventResponse;
import locke.dustin.minilink.entity.LinkEvent;
import locke.dustin.minilink.service.LinkEventService;

import java.util.List;
import java.util.stream.Collectors;

public class LinkEventMapper {

    public static LinkEvent toEntity ( LinkEventRequest request ) {

        LinkEvent linkEvent = new LinkEvent();
        linkEvent.setMiniLinkId( request.miniLinkId( ) );
        linkEvent.setOriginalUrl( request.originalUrl( ) );
        linkEvent.setMiniCode( request.miniCode( ) );
        linkEvent.setEventDate(request.eventDate( ));
        linkEvent.setEventType( request.eventType( ) );
        linkEvent.setIpAddress( request.ipAddress( ) );
        linkEvent.setUserAgent( request.userAgent( ) );

        return linkEvent;
    }

    public static LinkEventResponse toResponse ( LinkEvent linkEvent ) {

        return new LinkEventResponse(
                linkEvent.getId( ),
                linkEvent.getMiniLinkId( ),
                linkEvent.getOriginalUrl( ),
                linkEvent.getMiniCode( ),
                linkEvent.getEventDate( ),
                linkEvent.getEventType( ),
                linkEvent.getIpAddress( ),
                linkEvent.getUserAgent( ) );
    }

    public static List<LinkEventResponse> toResponseList (List<LinkEvent> eventList) {
        return eventList.stream( )
                .map( LinkEventMapper::toResponse )
                .toList();
    }
}
