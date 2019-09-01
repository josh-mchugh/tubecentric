package com.tubecentric.webapplication.register.service.model;

import com.tubecentric.webapplication.register.FeatureSet;
import com.tubecentric.webapplication.register.ReportingInterval;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(builderClassName="Builder")
public class QuestionnairePersistRequest {

    private final String id;
    private final List<FeatureSet> featureSet;
    private final List<ReportingInterval> reportingIntervals;
    private final String frustrations;
    private final boolean allowFutureQuestionnaires;
}
