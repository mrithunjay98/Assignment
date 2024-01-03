package com.SynonymApplication.entity;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Synonym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String word;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    private Set<SynonymRelation> synonyms = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Set<SynonymRelation> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(Set<SynonymRelation> synonyms) {
		this.synonyms = synonyms;
	}

    // Constructors, getters, setters
}
