package locke.dustin.minilink.service;

import locke.dustin.minilink.util.MiniLinkMapper;
import locke.dustin.minilink.dto.MiniLinkRequest;
import locke.dustin.minilink.dto.MiniLinkResponse;
import locke.dustin.minilink.entity.MiniLink;
import locke.dustin.minilink.repository.MiniLinkRepository;
import locke.dustin.minilink.util.exception.ExistingAliasException;
import locke.dustin.minilink.util.exception.ExistingUrlException;
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

    public List<MiniLinkResponse> getAllLinks() {
        List<MiniLink> miniLinks = repo.findAll();
        return miniLinks
                .stream()
                .map( MiniLinkMapper::toResponse )
                .collect( Collectors.toList( ) );
    }

    public String generateMiniCode() {
        return UUID.randomUUID( )
                .toString()
                .substring( 0, 8);
    }

    public MiniLinkResponse createMiniLink(MiniLinkRequest request) {

        String normalizedUrl = normalizeUrl( request.originalUrl() );
        String code;

        if ( repo.existsByOriginalUrl( request.originalUrl( ) ) ||
             repo.existsByOriginalUrl( normalizedUrl )) {
            throw new ExistingUrlException( normalizedUrl );
        }

        if (request.customAlias() != null && !request.customAlias().isBlank()) {

            if (repo.existsByMiniCode(request.customAlias())) {
                throw new ExistingAliasException(
                        request.customAlias()
                );
            }

            code = request.customAlias();

        } else {
            code = generateMiniCode();

            while (repo.existsByMiniCode(code)) {
                code = generateMiniCode();
            }
        }



        MiniLink miniLink = new MiniLink();
        miniLink.setOriginalUrl(normalizedUrl);
        miniLink.setMiniCode(code);

        repo.save(miniLink);

        return MiniLinkMapper.toResponse(miniLink);
    }

    private String normalizeUrl(String url) {

        if (!url.startsWith("http://")
            && !url.startsWith("https://")) {

            url = "https://" + url;
        }

        return url;
    }

    public void deleteById ( Long id ) {
        System.out.println( "MiniLink id: " + id );
        if (!repo.existsById(id)) {
            System.out.println( "Cannot find MiniLink with id: " + id );
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Link not found"
            );
        }

        repo.deleteById(id);
    }
}
