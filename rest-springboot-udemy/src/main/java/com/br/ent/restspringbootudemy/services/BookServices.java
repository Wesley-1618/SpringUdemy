package com.br.ent.restspringbootudemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ent.restspringbootudemy.converter.DozerConverter;
import com.br.ent.restspringbootudemy.converter.custom.PersonConverter;
import com.br.ent.restspringbootudemy.data.dtos.BookDTO;
import com.br.ent.restspringbootudemy.data.dtos2.PersonDTOV2;
import com.br.ent.restspringbootudemy.data.models.Person;
import com.br.ent.restspringbootudemy.repository.PersonRepository;

@Service
public class BookServices {
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;
	
	public BookDTO create(BookDTO book) {
		var entity = DozerConverter.parseObject(book, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookDTO.class);
		return vo;
	}
	
	public BookDTO update(BookDTO book) {
		BookDTO entity = new BookDTO();
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		return entity;
	}
	
	public void delete(Long id) {
		Person book = repository.findById(id).orElseThrow();
		repository.delete(book);
	}
	
	public BookDTO findById(Long id) {
		var dto = DozerConverter.parseObject(repository.findById(id).orElseThrow(), BookDTO.class);
		return  dto;
	}
	
	public List<BookDTO> findAll() {
		var dtoList = DozerConverter.parseListObjects(repository.findAll(), BookDTO.class);
		return dtoList;
	}
}
