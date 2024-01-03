package com.SynonymApplication.entity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(unique = true, nullable = false)
 private String username;

 @Column(nullable = false)
 private String password;

 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
 @Column(name = "role")
 private Set<String> roles;

 // Constructors, getters, setters
}
