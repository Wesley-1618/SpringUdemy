package com.br.ent.restspringbootudemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ent.restspringbootudemy.data.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
