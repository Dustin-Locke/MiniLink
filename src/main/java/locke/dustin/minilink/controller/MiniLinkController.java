package locke.dustin.minilink.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import locke.dustin.minilink.dto.*;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.MiniLinkRepository;
import locke.dustin.minilink.service.LinkEventService;
import locke.dustin.minilink.service.MiniLinkService;
import locke.dustin.minilink.type.EventType;
import locke.dustin.minilink.util.MiniLinkMapper;
import locke.dustin.minilink.util.exception.MiniLinkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api" )
@RequiredArgsConstructor
public class MiniLinkController {


    private final MiniLinkRepository miniLinkRepository;
    private final MiniLinkService    miniLinkService;
    private final LinkEventService   linkEventService;

    @GetMapping("/{miniCode}" )
    public ResponseEntity< Void > redirect (
            @PathVariable String miniCode,
            HttpServletRequest httpRequest ) {

        MiniLink miniUrl = miniLinkRepository.findByMiniCode( miniCode )
                                             .orElseThrow(
                                                     ( ) -> new MiniLinkNotFoundException(
                                                             miniCode
                                                     ) );

        linkEventService.create( miniUrl, EventType.ACCESSED, httpRequest );

        return ResponseEntity.status( HttpStatus.FOUND )
                             .location( URI.create( miniUrl.getOriginalUrl( ) ) )
                             .build( );
    }

    @PostMapping
    public ResponseEntity< MiniLinkResponse > createShortLink (
            @Valid
            @RequestBody
            MiniLinkRequest miniLinkRequest,
            HttpServletRequest httpRequest) {

        MiniLinkResponse response =
                miniLinkService.createMiniLink( miniLinkRequest, httpRequest );

        return ResponseEntity.status( HttpStatus.CREATED )
                             .body( response );
    }

    @GetMapping("/links" )
    public List< MiniLinkResponse > getAllLinks ( ) {

        return miniLinkService.getAllLinks( );
    }

    @DeleteMapping("/links/{id}" )
    @ResponseStatus(HttpStatus.NO_CONTENT )
    public void delete (
            @PathVariable Long id,
            HttpServletRequest httpRequest) {

        miniLinkService.deleteById( id, httpRequest );
    }

}
