package com.tubecentric.old.app.api.videos;

import java.util.Collection;
import java.util.Map;

public interface IVideoControllerService {

    Map<String, String> getSearchParams(String query, String pageToken);

    Map<String, String> getVideoParams(Collection<String> ids, String language);
}
