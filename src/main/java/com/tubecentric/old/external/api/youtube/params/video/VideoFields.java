package com.tubecentric.old.external.api.youtube.params.video;

import com.google.common.base.Joiner;
import com.tubecentric.old.external.api.youtube.params.util.FieldsUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * SearchVideo Fields are the properties that appear in the API Call.
 *
 * Using the builder we are able to filter the response properties to shorten API call responses and time
 */
public class VideoFields {

    private static final String PARAM_KEY = "fields";

    private final Map<String, String> fields;

    private VideoFields(Map<String, String> fields) {

        this.fields = fields;
    }

    public Map<String, String> getFields() {

        return fields;
    }

    @Override
    public String toString() {

        return this.fields.toString();
    }

    /**
     * Builder builds the highest level properties from the API response such as:
     *
     * Kind - Identifies the API resource's type. The value will be youtube#video.
     * Etag - The Etag of this resource.
     * EventId - Unsure (Needs Future Documentation)
     * NextPageToken - Token to retrieve the next page
     * PrevPageToken - Token to Retrieve the previous page
     * TokenPagination - Unsure(Needs Future Documentation)
     * VisitorId - Unsure (Needs Future Documentation)
     *
     */
    public static class Builder {

        private StringBuilder stringBuilder = new StringBuilder();
        private Set<String> fields = new LinkedHashSet<>();

        private static final String ETAG = "etag";
        private static final String EVENT_ID = "eventId";
        private static final String KIND = "kind";
        private static final String NEXT_PAGE_TOKEN = "nextPageToken";
        private static final String PREV_PAGE_TOKEN = "prevPageToken";
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

        public VideoFields build() {

            this.stringBuilder.append(Joiner.on(",").join(fields));

            Map<String, String> fields = new HashMap<>();
            fields.put(VideoFields.PARAM_KEY, this.toString());

            return new VideoFields(fields);
        }

        @Override
        public String toString() {

            return this.stringBuilder.toString();
        }

        /**
         * Page Info
         *
         * Returns the statistics of the paging information.
         *
         * TotalResults - Number of total results
         * ResultsPerPage - Number of results per page
         *
         * Can only have self 'pageInfo' or one of two children 'totalResults' or 'resultsPerPage'
         */
        public class PageInfo {

            private StringBuilder stringBuilder = new StringBuilder();
            private Set<String> fields = new LinkedHashSet<>();

            private static final String FIELD_NAME = "pageInfo";

            private static final String TOTAL_RESULTS = "totalResults";
            private static final String RESULTS_PER_PAGE = "resultsPerPage";

            public PageInfo all() {

                this.fields.add(FIELD_NAME);

                return this;
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

        }//End PageInfo

        /**
         * Items is an object used to filter the properties of the video in it's response for the API call.
         *
         * Kind - Identifies the API resource's type. The value will be youtube#video.
         * Etag - The Etag of this resource.
         * Id - The ID that YouTube uses to uniquely identify the video.
         *
         * #Public Items
         * ContentDetails - The contentDetails object contains information about the video content, including the length of the video and an indication of whether captions are available for the video.
         * LiveStreamingDetails - The liveStreamingDetails object contains metadata about a live video broadcast. The object will only be present in a video resource if the video is an upcoming, live, or completed live broadcast.
         * Localizations - The localizations object contains translations of the video's metadata.
         * Player - The player object contains information that you would use to play the video in an embedded player.
         * RecordingDetails - The recordingDetails object encapsulates information about the location, date and address where the video was recorded.
         * Snippet - The snippet object contains basic details about the video, such as its title, description, and category.
         * Statistics - The statistics object contains statistics about the video.
         * Status - The status object contains information about the video's uploading, processing, and privacy statuses.
         * TopicDetails - The topicDetails object encapsulates information about topics associated with the video.
         *
         * #Authorized Items
         * FileDetails - The fileDetails object encapsulates information about the video file that was uploaded to YouTube, including the file's resolution, duration, audio and video codecs, stream bitrates, and more. This data can only be retrieved by the video owner.
         * ProcessingDetails - The processingDetails object encapsulates information about YouTube's progress in processing the uploaded video file. The properties in the object identify the current processing status and an estimate of the time remaining until YouTube finishes processing the video. This part also indicates whether different types of data or content, such as file details or thumbnail images, are available for the video.
         * Suggestions - The suggestions object encapsulates suggestions that identify opportunities to improve the video quality or the metadata for the uploaded video. This data can only be retrieved by the video owner.
         */
        public class Items {

            private static final String FIELD_NAME = "items";

            private StringBuilder stringBuilder = new StringBuilder();
            private Set<String> fields = new LinkedHashSet<>();

            private static final String ETAG = "etag";
            private static final String KIND = "kind";
            private static final String ID = "id";

            private ContentDetails contentDetails;
            private Snippet snippet;
            private Statistics statistics;

            //TODO: LiveStreamingDetails
            //TODO: Localizations
            //TODO: Player
            //TODO: RecordingDetails
            //TODO: Status
            //TODO: TopicDetails

            //TODO: FileDetails
            //TODO: ProcessingDetails
            //TODO: Suggestions

            public Items all() {

                this.stringBuilder.append(FIELD_NAME);

                return this;
            }

            public Items etag() {

                this.fields.add(ETAG);

                return this;
            }

            public Items kind() {

                this.fields.add(KIND);

                return this;
            }

            public Items id() {

                this.fields.add(ID);

                return this;
            }

            public Snippet snippet() {

                this.snippet = new Snippet();

                return this.snippet;
            }

            public ContentDetails contentDetails() {

                this.contentDetails = new ContentDetails();

                return this.contentDetails;
            }

            public Statistics statistics() {

                this.statistics = new Statistics();

                return this.statistics;
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
             * Snippet contains basic details about the video, such as its title, description, and category.
             *
             * PublishedAt - The date and time that the video was published. Note that this time might be different than the time that the video was uploaded. For example, if a video is uploaded as a private video and then made public at a later time, this property will specify the time that the video was made public.
             * ChannelId - The ID that YouTube uses to uniquely identify the channel that the video was uploaded to.
             * Title - The video's title. The property value has a maximum length of 100 characters and may contain all valid UTF-8 characters except < and >.
             * Description - The video's description. The property value has a maximum length of 5000 bytes and may contain all valid UTF-8 characters except < and >.
             * Thumbnails - A map of thumbnail images associated with the video. For each object in the map, the key is the name of the thumbnail image, and the value is an object that contains other information about the thumbnail.
             * ChannelTitle - Channel title for the channel that the video belongs to.
             * Tags - A list of keyword tags associated with the video. Tags may contain spaces. The property value has a maximum length of 500 characters.
             * CategoryId - The YouTube video category associated with the video.
             * LiveBroadCastContent - Indicates if the video is an upcoming/active live broadcast. Or it's "none" if the video is not an upcoming/active live broadcast.
             * DefaultLanguage - The language of the text in the video resource's snippet.title and snippet.description properties.
             * Localized - The language of the text in the video resource's snippet.title and snippet.description properties.
             * DefaultAudioLanguage - The default_audio_language property specifies the language spoken in the video's default audio track.
             *
             */
            public class Snippet {

                private static final String FIELD_NAME = "snippet";

                private StringBuilder stringBuilder = new StringBuilder();
                private Set<String> fields = new LinkedHashSet<>();

                private static final String PUBLISHED_AT = "publishedAt";
                private static final String CHANNEL_ID = "channelId";
                private static final String TITLE = "title";
                private static final String DESCRIPTION = "description";
                private static final String CHANNEL_TITLE = "channelTitle";
                private static final String TAGS = "tags";
                private static final String CATEGORY_ID = "categoryId";
                private static final String LIVE_BROADCAST_CONTENT = "liveBroadcastContent";
                private static final String DEFAULT_LANGUAGE = "defaultLanguage";
                private static final String DEFAULT_AUDIO_LANGUAGE = "defaultAudioLanguage";

                private ThumbnailFieldsBuilder thumbnails;
                private Localized localized;

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

                public Snippet tags() {

                    this.fields.add(TAGS);

                    return this;
                }

                public Snippet categoryId() {

                    this.fields.add(CATEGORY_ID);

                    return this;
                }

                public Snippet liveBroadcastContent() {

                    this.fields.add(LIVE_BROADCAST_CONTENT);

                    return this;
                }

                public Snippet defaultLanguage() {

                    this.fields.add(DEFAULT_LANGUAGE);

                    return this;
                }

                public Snippet defaultAudioLanguage() {

                    this.fields.add(DEFAULT_AUDIO_LANGUAGE);

                    return this;
                }

                public Localized localized() {

                    this.localized = new Localized();

                    return this.localized;
                }

                public ThumbnailFieldsBuilder thumbnails() {

                    this.thumbnails = new ThumbnailFieldsBuilder();

                    return this.thumbnails;
                }

                public Items build() {

                    if(CollectionUtils.isNotEmpty(this.fields)) {

                        if(FieldsUtil.hasSingleField(this.fields)) {

                            this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                        }else {

                            this.stringBuilder.append(String.format("%s(%s)", FIELD_NAME, Joiner.on(",").join(this.fields)));
                        }
                    }

                    Items.this.fields.add(this.toString());

                    return Items.this;
                }

                @Override
                public String toString() {

                    return stringBuilder.toString();
                }

                /**
                 * The snippet.localized object contains either a localized title and description for the video or the title in the default language for the video's metadata.
                 *
                 * Localized text is returned in the resource snippet if the videos.list request used the hl parameter to specify a language for which localized text should be returned and localized text is available in that language.
                 *
                 * Metadata for the default language is returned if an hl parameter value is not specified or a value is specified but localized metadata is not available for the specified language.
                 *
                 * Title - The localized video title.
                 * Description - The localized video description.
                 *
                 */
                public class Localized {

                    private static final String FIELD_NAME = "localized";

                    private StringBuilder stringBuilder = new StringBuilder();
                    private Set<String> fields = new LinkedHashSet<>();

                    private static final String TITLE = "title";
                    private static final String DESCRIPTION = "description";

                    public Localized all() {

                        this.stringBuilder.append(FIELD_NAME);

                        return this;
                    }

                    public Localized title() {

                        this.fields.add(TITLE);

                        return this;
                    }

                    public Localized description() {

                        this.fields.add(DESCRIPTION);

                        return this;
                    }

                    public Snippet build() {

                        if(CollectionUtils.isNotEmpty(this.fields)) {

                            if(FieldsUtil.hasSingleField(this.fields)) {

                                this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                            }else {

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

                }//End Localized

                /**
                 * ThumbnailFieldsBuilder builds the different types of thumbnail sizes that can be retrieved from an API response.
                 *
                 * Default – The default thumbnail image. The default thumbnail for a video – or a resource that refers to a video, such as a playlist item or search result – is 120px wide and 90px tall. The default thumbnail for a channel is 88px wide and 88px tall.
                 * Medium – A higher resolution version of the thumbnail image. For a video (or a resource that refers to a video), this image is 320px wide and 180px tall. For a channel, this image is 240px wide and 240px tall.
                 * High – A high resolution version of the thumbnail image. For a video (or a resource that refers to a video), this image is 480px wide and 360px tall. For a channel, this image is 800px wide and 800px tall.
                 * Standard – An even higher resolution version of the thumbnail image than the high resolution image. This image is available for some videos and other resources that refer to videos, like playlist items or search results. This image is 640px wide and 480px tall.
                 * Maxres – The highest resolution version of the thumbnail image. This image size is available for some videos and other resources that refer to videos, like playlist items or search results. This image is 1280px wide and 720px tall.
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
                     * Default – The default thumbnail image. The default thumbnail for a video – or a resource that refers to a video, such as a playlist item or search result – is 120px wide and 90px tall. The default thumbnail for a channel is 88px wide and 88px tall.
                     *
                     * URL - The image's URL.
                     * Width - The image's width.
                     * Height - The image's height.
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

                    }//End Default

                    /**
                     * Medium – A higher resolution version of the thumbnail image. For a video (or a resource that refers to a video), this image is 320px wide and 180px tall. For a channel, this image is 240px wide and 240px tall.
                     *
                     * URL - The image's URL.
                     * Width - The image's width.
                     * Height - The image's height.
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

                    }//End Medium

                    /**
                     * Standard – An even higher resolution version of the thumbnail image than the high resolution image. This image is available for some videos and other resources that refer to videos, like playlist items or search results. This image is 640px wide and 480px tall.
                     *
                     * URL - The image's URL.
                     * Width - The image's width.
                     * Height - The image's height.
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

                    }//Standard

                    /**
                     * High - A high resolution version of the thumbnail image. For a video (or a resource that refers to a video), this image is 480px wide and 360px tall. For a channel, this image is 800px wide and 800px tall.
                     *
                     * URL - The image's URL.
                     * Width - The image's width.
                     * Height - The image's height.
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

                    }//End High

                    /**
                     * Maxres – The highest resolution version of the thumbnail image. This image size is available for some videos and other resources that refer to videos, like playlist items or search results. This image is 1280px wide and 720px tall.
                     *
                     * URL - The image's URL.
                     * Width - The image's width.
                     * Height - The image's height.
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

                    }//End MaxRes

                }//ThumbnailBuilder End

            }//End Snippet

            /**
             * The contentDetails object contains information about the video content, including the length of the video and an indication of whether captions are available for the video.
             *
             * Duration - The length of the video. The property value is an ISO 8601 duration.
             * Dimension - Indicates whether the video is available in 3D or in 2D.
             * Definition - Indicates whether the video is available in high definition (HD) or only in standard definition.
             * Caption - Indicates whether captions are available for the video.
             * LicensedContent - Indicates whether the video represents licensed content, which means that the content was uploaded to a channel linked to a YouTube content partner and then claimed by that partner.
             * Projection - Specifies the projection format of the video.
             *
             * #Authorized
             * HasCustomThumbnail - Indicates whether the video uploader has provided a custom thumbnail image for the video.
             */
            public class ContentDetails {

                private static final String FIELD_NAME = "contentDetails";

                private StringBuilder stringBuilder = new StringBuilder();
                private Set<String> fields = new LinkedHashSet<>();

                private static final String DURATION = "duration";
                private static final String DIMENSION = "dimension";
                private static final String DEFINITION = "definition";
                private static final String CAPTION = "caption";
                private static final String LICENSED_CONTENT = "licensedContent";
                private static final String PROJECTION = "projection";
                private static final String HAS_CUSTOM_THUMBNAIL = "hasCustomThumbnail";

                //TODO: Region Restriction
                //TODO: Content Rating

                public ContentDetails all() {

                    this.stringBuilder.append(FIELD_NAME);

                    return this;
                }

                public ContentDetails duration() {

                    this.fields.add(DURATION);

                    return this;
                }

                public ContentDetails dimension() {

                    this.fields.add(DIMENSION);

                    return this;
                }

                public ContentDetails definition() {

                    this.fields.add(DEFINITION);

                    return this;
                }

                public ContentDetails caption() {

                    this.fields.add(CAPTION);

                    return this;
                }

                public ContentDetails licensedContent() {

                    this.fields.add(LICENSED_CONTENT);

                    return this;
                }

                public ContentDetails projection() {

                    this.fields.add(PROJECTION);

                    return this;
                }

                public ContentDetails hasCustomThumbnail() {

                    this.fields.add(HAS_CUSTOM_THUMBNAIL);

                    return this;
                }

                public Items build() {

                    if(CollectionUtils.isNotEmpty(this.fields)) {

                        if(FieldsUtil.hasSingleField(this.fields)) {

                            this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                        }else {

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

            }//End ContentDetails

            public class Statistics {

                private static final String FIELD_NAME = "statistics";

                private StringBuilder stringBuilder = new StringBuilder();
                private Set<String> fields = new LinkedHashSet<>();

                private static final String VIEW_COUNT = "viewCount";
                private static final String LIKE_COUNT = "likeCount";
                private static final String DISLIKE_COUNT = "dislikeCount";
                private static final String COMMENT_COUNT = "commentCount";

                public Statistics all() {

                    this.stringBuilder.append(FIELD_NAME);

                    return this;
                }

                public Statistics viewCount() {

                    this.fields.add(VIEW_COUNT);

                    return this;
                }

                public Statistics likeCount() {

                    this.fields.add(LIKE_COUNT);

                    return this;
                }

                public Statistics dislikeCount() {

                    this.fields.add(DISLIKE_COUNT);

                    return this;
                }

                public Statistics commentCount() {

                    this.fields.add(COMMENT_COUNT);

                    return this;
                }

                public Items build() {

                    if(CollectionUtils.isNotEmpty(this.fields)) {

                        if(FieldsUtil.hasSingleField(this.fields)) {

                            this.stringBuilder.append(String.format("%s/%s", FIELD_NAME, Joiner.on(",").join(this.fields)));

                        }else {

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

            }//End Statistics

        }//End Items

    }//End Builder

}//End SearchVideo Fields
