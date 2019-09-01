package com.tubecentric.webapplication.register.controller.model;

import com.tubecentric.webapplication.register.FeatureSet;
import com.tubecentric.webapplication.register.ReportingInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireForm {

    private List<FeatureSet> featureSets;
    private List<ReportingInterval> reportingIntervals;
    private String frustrations;

    @Builder.Default
    private boolean allowFutureQuestionnaires = true;
}
