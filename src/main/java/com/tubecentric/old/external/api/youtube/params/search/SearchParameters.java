package com.tubecentric.old.external.api.youtube.params.search;

import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchParameters {

    private Map<String, String> params = new LinkedHashMap<>();

    private SearchParameters(Map<String, String> params) {

        this.params = params;
    }

    public Map<String, String> toMap() {

        return this.params;
    }

    @Override
    public String toString() {

        return this.params.toString();
    }

    public static class Builder {

        private Map<String, String> params = new LinkedHashMap<>();

        private final String API_KEY = "key";
        private final String PART = "part";
        private final String CHANNEL_ID = "channelId";
        private final String CHANNEL_TYPE = "channelType";
        private final String EVENT_TYPE = "eventType";
        private final String FOR_CONTENT_OWNER = "forContentOwner";
        private final String FOR_DEVELOPER = "forDeveloper";
        private final String FOR_MINE = "forMine";
        private final String LOCATION = "location";
        private final String LOCATION_RADIUS = "locationRadius";
        private final String MAX_RESULTS = "maxResults";
        private final String ON_BEHALF_OF_CONTENT_OWNER = "onBehalfOfContentOwner";
        private final String ORDER = "order";
        private final String PAGE_TOKEN = "pageToken";
        private final String PUBLISHED_AFTER = "publishedAfter";
        private final String PUBLISHED_BEFORE = "publishedBefore";
        private final String SEARCH_QUERY = "q";
        private final String REGION_CODE = "regionCode";
        private final String RELATED_TO_VIDEO_ID = "relatedToVideoId";
        private final String RELEVANCE_LANGUAGE = "relevanceLanguage";
        private final String SAFE_SEARCH = "safeSearch";
        private final String TOPIC_ID = "topicId";
        private final String TYPE = "type";
        private final String VIDEO_CAPTION = "videoCaption";
        private final String VIDEO_CATEGORY_ID = "videoCategoryId";
        private final String VIDEO_DEFINITION = "videoDefinition";
        private final String VIDEO_DIMENSION = "videoDimension";
        private final String VIDEO_DURATION = "videoDuration";
        private final String VIDEO_EMBEDDABLE = "videoEmbeddable";
        private final String VIDEO_LICENSE = "videoLicense";
        private final String VIDEO_SYNDICATED = "videoSyndicated";
        private final String VIDEO_TYPE = "videoType";

        public Builder key(String key) {

            this.params.put(this.API_KEY, key);

            return this;
        }

        public Builder part(String part){

            this.params.put(this.PART, part);

            return this;
        }

        public Builder part(Collection<String> part) {

            this.params.put(this.PART, Joiner.on(",").join(part));

            return this;
        }

        public Builder channelId(String channelId) {

            this.params.put(this.CHANNEL_ID, channelId);

            return this;
        }

        public Builder channelType(String channelType) {

            this.params.put(this.CHANNEL_TYPE, channelType);

            return this;
        }

        public Builder eventType(String eventType) {

            this.params.put(this.EVENT_TYPE, eventType);

            return this;
        }

        public Builder forContentOwner(Boolean forContentOwner) {

            this.params.put(this.FOR_CONTENT_OWNER, forContentOwner.toString());

            return this;
        }

        public Builder forDeveloper(Boolean forDeveloper) {

            this.params.put(this.FOR_DEVELOPER, forDeveloper.toString());

            return this;
        }

        public Builder forMine(Boolean forMine) {

            this.params.put(this.FOR_MINE, forMine.toString());

            return this;
        }

        public Builder location(String location) {

            this.params.put(this.LOCATION, location);

            return this;
        }

        public Builder locationRadius(String locationRadius) {

            this.params.put(this.LOCATION_RADIUS, locationRadius);

            return this;
        }

        public Builder maxResults(Integer maxResults) {

            this.params.put(this.MAX_RESULTS, maxResults.toString());

            return this;
        }

        public Builder onBehalfOfContentOwner(String onBehalfOfContentOwner) {

            this.params.put(this.ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner);

            return this;
        }

        public Builder order(String order) {

            this.params.put(this.ORDER, order);

            return this;
        }

        public Builder pageToken(String pageToken) {

            this.params.put(this.PAGE_TOKEN, pageToken);

            return this;
        }

        public Builder publishedAfter(String publishedAfter) {

            this.params.put(this.PUBLISHED_AFTER, publishedAfter);

            return this;
        }

        public Builder publishedBefore(String publishedBefore) {

            this.params.put(this.PUBLISHED_BEFORE, publishedBefore);

            return this;
        }

        public Builder query(String query) {

            this.params.put(this.SEARCH_QUERY, query);

            return this;
        }

        public Builder regionCode(String regionCode) {

            this.params.put(this.REGION_CODE, regionCode);

            return this;
        }

        public Builder relatedToVideoId(String relatedToVideoId) {

            this.params.put(this.RELATED_TO_VIDEO_ID, relatedToVideoId);

            return this;
        }

        public Builder relevanceLanguage(String relevanceLanguage) {

            this.params.put(this.RELEVANCE_LANGUAGE, relevanceLanguage);

            return this;
        }

        public Builder safeSearch(String safeSearch) {

            this.params.put(this.SAFE_SEARCH, safeSearch);

            return this;
        }

        public Builder topicId(String topicId) {

            this.params.put(this.TOPIC_ID, topicId);

            return this;
        }

        public Builder type(String type) {

            this.params.put(this.TYPE, type);

            return this;
        }

        public Builder videoCaption(String videoCaption) {

            this.params.put(this.VIDEO_CAPTION, videoCaption);

            return this;
        }

        public Builder videoCategoryId(String videoCategoryId) {

            this.params.put(this.VIDEO_CATEGORY_ID, videoCategoryId);

            return this;
        }

        public Builder videoDefinition(String videoDefinition) {

            this.params.put(this.VIDEO_DEFINITION, videoDefinition);

            return this;
        }

        public Builder videoDimension(String videoDimension) {

            this.params.put(this.VIDEO_DIMENSION, videoDimension);

            return this;
        }

        public Builder videoDuration(String videoDuration) {

            this.params.put(this.VIDEO_DURATION, videoDuration);

            return this;
        }

        public Builder videoEmbeddable(String videoEmbeddable) {

            this.params.put(this.VIDEO_EMBEDDABLE, videoEmbeddable);

            return this;
        }

        public Builder videoLicense(String videoLicense) {

            this.params.put(this.VIDEO_LICENSE, videoLicense);

            return this;
        }

        public Builder videoSyndicated(String videoSyndicated) {

            this.params.put(this.VIDEO_SYNDICATED, videoSyndicated);

            return this;
        }

        public Builder videoType(String videoType) {

            this.params.put(this.VIDEO_TYPE, videoType);

            return this;
        }

        public Builder fields(SearchFields fields) {

            this.params.putAll(fields.getFields());

            return this;
        }

        public SearchParameters build() {

            return new SearchParameters(this.params);
        }
    }
}
