package locke.dustin.minilink.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import locke.dustin.minilink.type.EventType;
import locke.dustin.minilink.util.MiniLinkMapper;
import locke.dustin.minilink.dto.MiniLinkRequest;
import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.MiniLinkRepository;
import locke.dustin.minilink.util.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MiniLinkService {

    private final MiniLinkRepository repo;
    private final MiniLinkMapper     miniLinkMapper;
    private final LinkEventService   linkEventService;

    public List< MiniLinkResponse > getAllLinks ( ) {

        List< MiniLink > miniLinks = repo.findAll( );
        return miniLinks
                .stream( )
                .map( miniLinkMapper::toResponse )
                .collect( Collectors.toList( ) );
    }

    public String generateMiniCode ( ) {

        return UUID.randomUUID( )
                   .toString( )
                   .substring(
                           0,
                           8 );
    }

    public MiniLinkResponse createMiniLink (
            MiniLinkRequest request,
            HttpServletRequest httpRequest ) {

        String normalizedUrl = normalizeUrl( request.originalUrl( ) );
        String code;

        if ( repo.existsByOriginalUrl( request.originalUrl( ) ) ||
             repo.existsByOriginalUrl( normalizedUrl ) ) {
            throw new ExistingUrlException( normalizedUrl );
        }

        if ( request.customAlias( ) != null &&
             !request.customAlias( ).isBlank( ) ) {

            if ( repo.existsByMiniCode( request.customAlias( ) ) ) {
                throw new ExistingAliasException(
                        request.customAlias( )
                );
            }

            code = request.customAlias( );

        } else {
            code = generateMiniCode( );

            while ( repo.existsByMiniCode( code ) ) {
                code = generateMiniCode( );
            }
        }


        MiniLink miniLink = new MiniLink( );
        miniLink.setOriginalUrl( normalizedUrl );
        miniLink.setMiniCode( code );

        MiniLink savedMiniLink = repo.save( miniLink );

        linkEventService.create(
                savedMiniLink,
                EventType.CREATED,
                httpRequest );

        return miniLinkMapper.toResponse( savedMiniLink );
    }

    private String normalizeUrl ( String url ) {

        if ( !url.startsWith( "http://" )
             && !url.startsWith( "https://" ) ) {

            url = "https://" + url;
        }

        return url;
    }

    public void deleteById (
            Long id,
            HttpServletRequest httpRequest ) {

        MiniLink miniLink = repo.findById( id )
                                .orElseThrow( ( ) -> new MiniLinkNotFoundException( id ) );

        linkEventService.create(
                miniLink,
                EventType.DELETED,
                httpRequest );

        repo.delete( miniLink );
    }
}
