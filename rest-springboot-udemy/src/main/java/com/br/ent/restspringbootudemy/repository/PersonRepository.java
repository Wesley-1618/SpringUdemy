package com.br.ent.restspringbootudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ent.restspringbootudemy.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
