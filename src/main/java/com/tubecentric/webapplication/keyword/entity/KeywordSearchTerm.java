package com.tubecentric.webapplication.keyword.entity;

import com.tubecentric.webapplication.framework.database.AbstractEntity;
import lombok.Getter;
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
@Table(name="keyword_search_term")
public class KeywordSearchTerm extends AbstractEntity {

    @Basic
    @Column(name="query", unique=true)
    private String query;

    @OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY, orphanRemoval=true, mappedBy="keywordSearchTerm")
    private List<KeywordSearchTermEntry> entries;
}
