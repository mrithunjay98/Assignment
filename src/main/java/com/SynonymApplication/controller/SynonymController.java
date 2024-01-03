package com.SynonymApplication.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.SynonymApplication.service.SynonymService;

import java.util.Set;

@RestController

public class SynonymController {

    private final SynonymService synonymService;

    @Autowired
    public SynonymController(SynonymService synonymService) {
        this.synonymService = synonymService;
    }
    @PreAuthorize("hasRole('WRITER')")
    @PostMapping("/add/{word}/{synonym}")
    public void addSynonym(@PathVariable String word, @PathVariable String synonym) {
        synonymService.addSynonym(word, synonym);
        
       
    }
    
    
    @PreAuthorize("hasRole('READER')")
    @GetMapping("/{word}")
    public Set<String> getSynonyms(@PathVariable String word) {
        return synonymService.getSynonyms(word);
    }

    @DeleteMapping("/delete/{word}/{synonym}")
    public void deleteSynonym(@PathVariable String word, @PathVariable String synonym) {
        synonymService.deleteSynonym(word, synonym);
    }

}

