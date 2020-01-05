package com.tubecentric.webapplication.metrics.search.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name="metric_search")
public class SearchMetricEntity extends AbstractEntity {

    @Basic
    @Column(name="query", length=500, nullable=false, unique=true)
    private String query;

    @Basic
    @Column(name="count", nullable=false)
    private Long count;
}
