package com.tubecentric.old.external.api.youtube.params.video;

import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class VideoParameters {

    private Map<String, String> params = new LinkedHashMap<>();

    private VideoParameters(Map<String, String> params) {

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

        private final String KEY = "key";
        private final String PART = "part";
        private final String CHART = "chart";
        private final String HL = "hl";
        private final String ID = "id";
        private final String LOCALE = "locale";
        private final String MAX_HEIGHT = "maxHeight";
        private final String MAX_RESULTS = "maxResults";
        private final String MAX_WIDTH = "maxWidth";
        private final String MY_RATING = "myRating";
        private final String ON_BEHALF_OF_CONTENT_OWNER = "onBehalfOfContentOwner";
        private final String PAGE_TOKEN = "pageToken";
        private final String REGION_CODE = "regionCode";
        private final String VIDEO_CATEGORY_ID = "videoCategoryId";

        public Builder key(String key) {

            this.params.put(this.KEY, key);

            return this;
        }

        public Builder part(String part) {

            this.params.put(this.PART, part);

            return this;
        }

        public Builder part(Collection<String> part) {

            this.params.put(this.PART, Joiner.on(",").join(part));

            return this;
        }

        public Builder chart(String chart) {

            this.params.put(this.CHART, chart);

            return this;
        }

        public Builder hl(String hl) {

            this.params.put(this.HL, hl);

            return this;
        }

        public Builder id(String id) {

            this.params.put(this.ID, id);

            return this;
        }

        public Builder ids(Collection<String> ids) {

            this.params.put(this.ID, Joiner.on(",").join(ids));

            return this;
        }

        @Deprecated
        public Builder locale(String locale) {

            this.params.put(this.LOCALE, locale);

            return this;
        }

        public Builder maxHeight(Integer maxHeight) {

            this.params.put(this.MAX_HEIGHT, maxHeight.toString());

            return this;
        }

        public Builder maxResults(Integer maxResults) {

            this.params.put(this.MAX_RESULTS, maxResults.toString());

            return this;
        }

        public Builder maxWidth(Integer maxWidth) {

            this.params.put(this.MAX_WIDTH, maxWidth.toString());

            return this;
        }

        public Builder myRating(String myRating) {

            this.params.put(this.MY_RATING, myRating);

            return this;
        }

        public Builder onBehalfOfContentOwner(Boolean onBehalfOfContentOwner) {

            this.params.put(this.ON_BEHALF_OF_CONTENT_OWNER, onBehalfOfContentOwner.toString());

            return this;
        }

        public Builder pageToken(String pageToken) {

            this.params.put(this.PAGE_TOKEN, pageToken);

            return this;
        }

        public Builder regionCode(String regionCode) {

            this.params.put(this.REGION_CODE, regionCode);

            return this;
        }

        public Builder videoCategoryId(String videoCategoryId) {

            this.params.put(this.VIDEO_CATEGORY_ID, videoCategoryId);

            return this;
        }

        public Builder fields(VideoFields fields) {

            this.params.putAll(fields.getFields());

            return this;
        }

        public VideoParameters build() {

            return new VideoParameters(this.params);
        }
    }
}
