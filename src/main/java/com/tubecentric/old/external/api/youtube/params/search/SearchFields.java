package com.tubecentric.old.external.api.youtube.params.search;

import com.google.common.base.Joiner;
import com.tubecentric.old.external.api.youtube.params.util.FieldsUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class SearchFields {

    private static final String PARAM_KEY = "fields";

    private final Map<String, String> fields;

    private SearchFields(Map<String, String> fields) {

        this.fields = fields;
    }

    public Map<String, String> getFields() {

        return fields;
    }

    @Override
    public String toString() {

        return this.fields.toString();
    }

    public static class Builder {

        private StringBuilder stringBuilder = new StringBuilder();
        private Set<String> fields = new LinkedHashSet<>();

        private static final String ETAG = "etag";
        private static final String EVENT_ID = "eventId";
        private static final String KIND = "kind";
        private static final String NEXT_PAGE_TOKEN = "nextPageToken";
        private static final String PREV_PAGE_TOKEN = "prevPageToken";
        private static final String REGION_CODE = "regionCode";
        private static final String TOKEN_PAGINATION = "tokenPagination";
        private static final String VISITOR_ID = "visitorId";

        private PageInfo pageInfo;

        private Items items;

        public Builder etag() {

            this.fields.add(ETAG);

            return this;
        }

        public Builder eventId() {

            this.fields.add(EVENT_ID);

            return this;
        }

        public Builder kind() {

            this.fields.add(KIND);

            return this;
        }

        public Builder nextPageToken() {

            this.fields.add(NEXT_PAGE_TOKEN);

            return this;
        }

        public Builder prevPageToken() {

            this.fields.add(PREV_PAGE_TOKEN);

            return this;
        }

        public Builder regionCode() {

            this.fields.add(REGION_CODE);

            return this;
        }

        public Builder tokenPagination() {

            this.fields.add(TOKEN_PAGINATION);

            return this;
        }

        public Builder visitorId() {

            this.fields.add(VISITOR_ID);

            return this;
        }

        public PageInfo pageInfo() {

            this.pageInfo = new PageInfo();

            return pageInfo;
        }

        public Items items() {

            this.items = new Items();

            return items;
        }

        public SearchFields build() {

            this.stringBuilder.append(Joiner.on(",").join(fields));

            Map<String, String> fields = new HashMap<>();
            fields.put(SearchFields.PARAM_KEY, this.toString());

            return new SearchFields(fields);
        }

        @Override
        public String toString() {

            return this.stringBuilder.toString();
        }

        /**
         *
         */
        public class PageInfo {

            private StringBuilder stringBuilder = new StringBuilder();
            private Set<String> fields = new LinkedHashSet<>();

            private static final String FIELD_NAME = "pageInfo";

            private static final String TOTAL_RESULTS = "totalResults";
            private static final String RESULTS_PER_PAGE = "resultsPerPage";

            public PageInfo all() {

                this.fields.add(FIELD_NAME);

                return PageInfo.this;
            }

            public PageInfo totalResults() {

                this.fields.add(TOTAL_RESULTS);

                return this;
            }

            public PageInfo resultsPerPage() {

                this.fields.add(RESULTS_PER_PAGE);

                return this;
            }

            public Builder build() {

                this.stringBuilder.append(Joiner.on(",").join(this.fields));

                Builder.this.fields.add(this.toString());

                return Builder.this;
            }

            @Override
            public String toString() {

                return this.stringBuilder.toString();
            }
        }

        public class Items {

            private static final String FIELD_NAME = "items";

            private StringBuilder stringBuilder = new StringBuilder();
            private Set<String> fields = new LinkedHashSet<>();

            private static final String ETAG = "etag";
            private static final String KIND = "kind";


            private Id id;
            private Snippet snippet;

            public Items all() {

                this.stringBuilder.append(FIELD_NAME);

                return this;
            }

            public Items etag() {

                this.fields.add(ETAG);

                return this;
            }

            public Items kind(){

                this.fields.add(KIND);

                return this;
            }

            public Id id() {

                this.id = new Id();

                return id;
            }

            public Snippet snippet() {

                this.snippet = new Snippet();

                return snippet;
            }

            public Builder build() {

                if(CollectionUtils.isNotEmpty(this.fields)) {

                    if(FieldsUtil.hasSingleField(this.fields)) {

                        this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                    }else {

                        this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                    }
                }

                Builder.this.fields.add(this.toString());

                return Builder.this;
            }

            @Override
            public String toString() {

                return this.stringBuilder.toString();
            }

            /**
             *
             */
            public class Id {

                private StringBuilder stringBuilder = new StringBuilder();
                private Set<String> fields = new LinkedHashSet<>();

                private static final String FIELD_NAME = "id";

                private static final String KIND = "kind";
                private static final String VIDEO_ID = "videoId";
                private static final String CHANNEL_ID = "channelId";
                private static final String PLAYLIST_ID = "playlistId";

                public Id all() {

                    this.stringBuilder.append(FIELD_NAME);

                    return this;
                }

                public Id kind() {

                    this.fields.add(KIND);

                    return this;
                }

                public Id videoId() {

                    this.fields.add(VIDEO_ID);

                    return this;
                }

                public Id channelId() {

                   this.fields.add(CHANNEL_ID);

                    return this;
                }

                public Id playlistId() {

                    this.fields.add(PLAYLIST_ID);

                    return this;
                }

                public Items build() {

                    if(CollectionUtils.isNotEmpty(this.fields)) {

                        this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                    }

                    Items.this.fields.add(this.toString());

                    return Items.this;
                }

                @Override
                public String toString() {

                    return this.stringBuilder.toString();
                }
            }

            public class Snippet {

                private StringBuilder stringBuilder = new StringBuilder();
                private Set<String> fields = new LinkedHashSet<>();

                private static final String FIELD_NAME = "snippet";

                private static final String PUBLISHED_AT = "publishedAt";
                private static final String CHANNEL_ID = "channelId";
                private static final String TITLE = "title";
                private static final String DESCRIPTION = "description";
                private static final String CHANNEL_TITLE = "channelTitle";
                private static final String LIVE_BROADCAST_CONTENT = "liveBroadcastContent";
                private ThumbnailFieldsBuilder thumbnailBuilder;

                public Snippet all() {

                    this.stringBuilder.append(FIELD_NAME);

                    return this;
                }

                public Snippet publishedAt() {

                    this.fields.add(PUBLISHED_AT);

                    return this;
                }

                public Snippet channelId() {

                    this.fields.add(CHANNEL_ID);

                    return this;
                }

                public Snippet title() {

                    this.fields.add(TITLE);

                    return this;
                }

                public Snippet description() {

                    this.fields.add(DESCRIPTION);

                    return this;
                }

                public Snippet channelTitle() {

                    this.fields.add(CHANNEL_TITLE);

                    return this;
                }

                public Snippet liveBroadcastContent() {

                    this.fields.add(LIVE_BROADCAST_CONTENT);

                    return this;
                }

                public ThumbnailFieldsBuilder thumbnails() {

                    this.thumbnailBuilder = new ThumbnailFieldsBuilder();

                    return thumbnailBuilder;
                }

                public Items build() {

                    if(CollectionUtils.isNotEmpty(this.fields)) {

                        if(FieldsUtil.hasSingleField(this.fields)) {

                            this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                        } else {

                            this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                        }
                    }

                    Items.this.fields.add(this.toString());

                    return Items.this;
                }

                @Override
                public String toString() {

                    return this.stringBuilder.toString();
                }

                /**
                 *
                 */
                public class ThumbnailFieldsBuilder {

                    private static final String FIELD_NAME = "thumbnails";

                    private StringBuilder stringBuilder = new StringBuilder();
                    private Set<String> fields = new LinkedHashSet<>();

                    private Default Default;
                    private Medium medium;
                    private Standard standard;
                    private High high;
                    private MaxRes maxRes;

                    public ThumbnailFieldsBuilder all() {

                        this.stringBuilder.append(FIELD_NAME);

                        return this;
                    }

                    public Default Default() {

                        this.Default = new Default();

                        return Default;
                    }

                    public Medium medium() {

                        this.medium = new Medium();

                        return medium;
                    }

                    public Standard standard() {

                        this.standard = new Standard();

                        return standard;
                    }

                    public High high() {

                        this.high = new High();

                        return high;
                    }

                    public MaxRes maxRes() {

                        this.maxRes = new MaxRes();

                        return maxRes;
                    }

                    public Snippet build() {

                        if(CollectionUtils.isNotEmpty(this.fields)) {

                            if(FieldsUtil.hasSingleField(this.fields)) {

                                this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                            } else {

                                this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                            }
                        }

                        Snippet.this.fields.add(this.toString());

                        return Snippet.this;
                    }

                    @Override
                    public String toString() {

                        return this.stringBuilder.toString();
                    }

                    /**
                     *
                     */
                    public class Default {

                        private StringBuilder stringBuilder = new StringBuilder();
                        private Set<String> fields = new LinkedHashSet<>();

                        private static final String FIELD_NAME = "default";

                        private static final String HEIGHT = "height";
                        private static final String WIDTH = "width";
                        private static final String URL = "url";

                        public Default all() {

                            this.stringBuilder.append(FIELD_NAME);

                            return this;
                        }

                        public Default height() {

                            this.fields.add(HEIGHT);

                            return this;
                        }

                        public Default width() {

                            this.fields.add(WIDTH);

                            return this;
                        }

                        public Default url() {

                            this.fields.add(URL);

                            return this;
                        }

                        public ThumbnailFieldsBuilder build() {

                            if(CollectionUtils.isNotEmpty(this.fields)) {

                                if(FieldsUtil.hasSingleField(this.fields)) {

                                    this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                                } else {

                                    this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                                }
                            }

                            ThumbnailFieldsBuilder.this.fields.add(this.toString());

                            return ThumbnailFieldsBuilder.this;
                        }

                        @Override
                        public String toString() {

                            return this.stringBuilder.toString();
                        }
                    }

                    /**
                     *
                     */
                    public class Medium {

                        private StringBuilder stringBuilder = new StringBuilder();
                        private Set<String> fields = new LinkedHashSet<>();

                        private static final String FIELD_NAME = "medium";

                        private static final String HEIGHT = "height";
                        private static final String WIDTH = "width";
                        private static final String URL = "url";

                        public Medium all() {

                            this.stringBuilder.append(FIELD_NAME);

                            return this;
                        }

                        public Medium height() {

                            this.fields.add(HEIGHT);

                            return this;
                        }

                        public Medium width() {

                            this.fields.add(WIDTH);

                            return this;
                        }

                        public Medium url() {

                            this.fields.add(URL);

                            return this;
                        }

                        public ThumbnailFieldsBuilder build() {

                            if(CollectionUtils.isNotEmpty(this.fields)) {

                                if(FieldsUtil.hasSingleField(this.fields)) {

                                    this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                                } else {

                                    this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                                }
                            }

                            ThumbnailFieldsBuilder.this.fields.add(this.toString());

                            return ThumbnailFieldsBuilder.this;
                        }

                        @Override
                        public String toString() {

                            return this.stringBuilder.toString();
                        }
                    }

                    /**
                     *
                     */
                    public class Standard {

                        private StringBuilder stringBuilder = new StringBuilder();
                        private Set<String> fields = new LinkedHashSet<>();

                        private static final String FIELD_NAME = "standard";

                        private static final String HEIGHT = "height";
                        private static final String WIDTH = "width";
                        private static final String URL = "url";

                        public Standard all() {

                            this.stringBuilder.append(FIELD_NAME);

                            return this;
                        }

                        public Standard height() {

                            this.fields.add(HEIGHT);

                            return this;
                        }

                        public Standard width() {

                            this.fields.add(WIDTH);

                            return this;
                        }

                        public Standard url() {

                            this.fields.add(URL);

                            return this;
                        }

                        public ThumbnailFieldsBuilder build() {

                            if(CollectionUtils.isNotEmpty(this.fields)) {

                                if(FieldsUtil.hasSingleField(this.fields)) {

                                    this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                                } else {

                                    this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                                }
                            }

                            ThumbnailFieldsBuilder.this.fields.add(this.toString());

                            return ThumbnailFieldsBuilder.this;
                        }

                        @Override
                        public String toString() {

                            return this.stringBuilder.toString();
                        }
                    }

                    /**
                     *
                     */
                    public class High {

                        private StringBuilder stringBuilder = new StringBuilder();
                        private Set<String> fields = new LinkedHashSet<>();

                        private static final String FIELD_NAME = "high";

                        private static final String HEIGHT = "height";
                        private static final String WIDTH = "width";
                        private static final String URL = "url";

                        public High all() {

                            this.stringBuilder.append(FIELD_NAME);

                            return this;
                        }

                        public High height() {

                            this.fields.add(HEIGHT);

                            return this;
                        }

                        public High width() {

                            this.fields.add(WIDTH);

                            return this;
                        }

                        public High url() {

                            this.fields.add(URL);

                            return this;
                        }

                        public ThumbnailFieldsBuilder build() {

                            if(CollectionUtils.isNotEmpty(this.fields)) {

                                if(FieldsUtil.hasSingleField(this.fields)) {

                                    this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                                } else {

                                    this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                                }
                            }

                            ThumbnailFieldsBuilder.this.fields.add(this.toString());

                            return ThumbnailFieldsBuilder.this;
                        }

                        @Override
                        public String toString() {

                            return this.stringBuilder.toString();
                        }
                    }

                    /**
                     *
                     */
                    public class MaxRes {

                        private StringBuilder stringBuilder = new StringBuilder();
                        private Set<String> fields = new LinkedHashSet<>();

                        private static final String FIELD_NAME = "maxres";

                        private static final String HEIGHT = "height";
                        private static final String WIDTH = "width";
                        private static final String URL = "url";

                        public MaxRes all() {

                            this.stringBuilder.append(FIELD_NAME);

                            return this;
                        }

                        public MaxRes height() {

                            this.fields.add(HEIGHT);

                            return this;
                        }

                        public MaxRes width() {

                            this.fields.add(WIDTH);

                            return this;
                        }

                        public MaxRes url() {

                            this.fields.add(URL);

                            return this;
                        }

                        public ThumbnailFieldsBuilder build() {

                            if(CollectionUtils.isNotEmpty(this.fields)) {

                                if(FieldsUtil.hasSingleField(this.fields)) {

                                    this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                                } else {

                                    this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                                }
                            }

                            ThumbnailFieldsBuilder.this.fields.add(this.toString());

                            return ThumbnailFieldsBuilder.this;
                        }

                        @Override
                        public String toString() {

                            return this.stringBuilder.toString();
                        }
                    }
                }
            }
        }
    }
}
