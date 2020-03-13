package com.tubecentric.webapplication.keyword.entity;

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
@Table(name="keyword_search_term_entry")
public class KeywordSearchTermEntry extends AbstractEntity {

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST}, fetch= FetchType.LAZY)
    @JoinColumn(name="keyword_search_term_id")
    public KeywordSearchTerm keywordSearchTerm;
}
