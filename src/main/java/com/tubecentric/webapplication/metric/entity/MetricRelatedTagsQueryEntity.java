package com.tubecentric.webapplication.metric.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "metric_related_tag_query")
public class MetricRelatedTagsQueryEntity extends AbstractEntity {

    @Basic
    @Column(name = "query", unique = true)
    private String query;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "relatedTagsQuery")
    private List<MetricRelatedTagsQueryEntryEntity> entries;

    public MetricRelatedTagsQueryEntity(String query) {

        this.query = query;
    }
}
