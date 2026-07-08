package locke.dustin.minilink.controller;

import locke.dustin.minilink.dto.AnalyticsResponse;
import locke.dustin.minilink.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics" )
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping
    public ResponseEntity< AnalyticsResponse > getAnalytics ( ) {

        return ResponseEntity.ok(
                analyticsService.getAnalytics( )
                                );
    }
}

