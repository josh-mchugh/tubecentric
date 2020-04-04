package com.tubecentric.webapplication.metric.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@Table(name = "metric_related_tag_query_entry")
public class MetricRelatedTagsQueryEntryEntity extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_related_tag_query_id")
    public MetricRelatedTagsQueryEntity relatedTagsQuery;
}
