package com.tubecentric.old.app.api.videos;

import com.tubecentric.old.external.api.youtube.params.search.SearchFields;
import com.tubecentric.old.external.api.youtube.params.search.SearchParameters;
import com.tubecentric.old.external.api.youtube.params.video.VideoFields;
import com.tubecentric.old.external.api.youtube.params.video.VideoParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class VideoControllerService implements IVideoControllerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoControllerService.class);

    @Value("${app.youtube.api.key}")
    private String apiKey;

    @Override
    public Map<String, String> getSearchParams(String query, String pageToken) {

        return new SearchParameters.Builder()
                .key(apiKey)
                .part("id,snippet")
                .maxResults(10)
                .query(query)
                .pageToken(pageToken)
                .fields(getSearchFields())
                .build()
                .toMap();
    }

    private SearchFields getSearchFields() {

        return new SearchFields.Builder()
                    .nextPageToken()
                    .prevPageToken()
                    .pageInfo()
                        .all()
                        .build()
                    .regionCode()
                    .items()
                        .id()
                        .videoId()
                        .build()
                    .build()
                .build();
    }

    @Override
    public Map<String, String> getVideoParams(Collection<String> ids, String language) {

        return new VideoParameters.Builder()
                .key(apiKey)
                .ids(ids)
                .hl(language)
                .part("id, snippet, contentDetails, statistics")
                .fields(getVideoFields())
                .build()
                .toMap();

    }

    public VideoFields getVideoFields() {

        return new VideoFields.Builder()
                    .items()
                    .etag()
                    .kind()
                    .id()
                    .snippet()
                    .publishedAt()
                    .channelTitle()
                    .tags()
                    .localized()
                        .all()
                        .build()
                    .thumbnails()
                        .medium()
                            .url()
                            .build()
                        .build()
                    .build()
                    .statistics()
                        .viewCount()
                        .build()
                    .build()
                .build();
    }
}
