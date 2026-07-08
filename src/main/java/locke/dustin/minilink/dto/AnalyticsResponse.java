package locke.dustin.minilink.dto;

import java.util.List;

public record AnalyticsResponse(
        long totalLinksCreated,
        long totalClicks,
        List<PopularLinkResponse> popularLinks,
        List<LinkEventResponse> recentEvents
) {}
