// SynonymService.java
package com.SynonymApplication.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SynonymApplication.Repository.SynonymRepository;
import com.SynonymApplication.entity.Synonym;
import com.SynonymApplication.entity.SynonymRelation;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SynonymService {

    
@Autowired
   private  SynonymRepository  synonymRepository;

    public void addSynonym(String word, String synonym) {
        Synonym wordEntity = getOrCreateSynonymEntity(word);
        SynonymRelation relation = new SynonymRelation();
        relation.setWord(wordEntity);
        relation.setSynonym(synonym);
        wordEntity.getSynonyms().add(relation);
        Synonym save = synonymRepository.save(wordEntity);
       
    }

    public Set<String> getSynonyms(String word) {
        Set<String> synonyms = new HashSet<>();
        findSynonyms(word, synonyms);
        return synonyms;
    }

    private void findSynonyms(String word, Set<String> synonyms) {
        Synonym wordEntity = synonymRepository.findByWord(word);
        if (wordEntity != null) {
            wordEntity.getSynonyms().forEach(relation -> {
                synonyms.add(relation.getSynonym());
                findSynonyms(relation.getSynonym(), synonyms);
            });
        }
    }

    private Synonym getOrCreateSynonymEntity(String word) {
        Synonym wordEntity = synonymRepository.findByWord(word);
        if (wordEntity == null) {
            wordEntity = new Synonym();
            wordEntity.setWord(word);
        }
        return wordEntity;
    }

    public void deleteSynonym(String word, String synonym) {
        Synonym wordEntity = synonymRepository.findByWord(word);
        if (wordEntity != null) {
            Optional<SynonymRelation> relationToRemove = wordEntity.getSynonyms().stream()
                    .filter(relation -> relation.getSynonym().equals(synonym))
                    .findFirst();

            relationToRemove.ifPresent(relation -> {
                wordEntity.getSynonyms().remove(relation);
                synonymRepository.save(wordEntity);
            });
        }
    }
}
