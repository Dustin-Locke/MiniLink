package locke.dustin.minilink.controller;

import jakarta.validation.Valid;
import locke.dustin.minilink.dto.MiniLinkRequest;
import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.MiniLinkRepository;
import locke.dustin.minilink.service.MiniLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MiniLinkController {

    @Autowired
    MiniLinkRepository repo;
    @Autowired
    MiniLinkService    service;

    @GetMapping("/{miniCode}")
    public ResponseEntity<Void> redirect ( @PathVariable String miniCode ) {
        MiniLink miniUrl = repo.findByMiniCode( miniCode )
                               .orElseThrow(
                                       () -> new ResponseStatusException(
                                               HttpStatus.NOT_FOUND
                                       ) );

        return ResponseEntity.status( HttpStatus.FOUND )
                .location( URI.create( miniUrl.getOriginalUrl()) )
                .build();
    }

    @PostMapping
    public ResponseEntity<MiniLinkResponse> createShortLink (
            @Valid
            @RequestBody MiniLinkRequest request ) {

        MiniLinkResponse response =
                service.createMiniLink(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }

}
