// SynonymRepository.java
package com.SynonymApplication.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SynonymApplication.entity.Synonym;

@Repository
public interface SynonymRepository extends JpaRepository<Synonym, Long> {
    Synonym findByWord(String word);
}
