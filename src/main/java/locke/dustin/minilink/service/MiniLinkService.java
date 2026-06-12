package locke.dustin.minilink.service;

import locke.dustin.minilink.dto.MiniLinkRequest;
import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.MiniLinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MiniLinkService {

    private final MiniLinkRepository repo;

    public String generateMiniCode() {
        return UUID.randomUUID( )
                .toString()
                .substring( 0, 8);
    }

    public MiniLinkResponse createMiniLink( MiniLinkRequest request ) {

        String code = generateMiniCode();

        if (request.customAlias() != null &&
            !request.customAlias().isBlank()) {

            if (repo.existsByMiniCode(request.customAlias())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Alias already exists"
                );
            }

        } else {
            code = generateMiniCode();

            while ( repo.existsByMiniCode( code ) ) {
                code = generateMiniCode( );
            }
        }
        code = request.customAlias( );

        MiniLink miniLink = new MiniLink();
        miniLink.setOriginalUrl( request.originalUrl() );
        miniLink.setMiniCode( code );

        repo.save( miniLink );

        return new MiniLinkResponse( "http://localhost:8080/api/" + code );
    }
}
