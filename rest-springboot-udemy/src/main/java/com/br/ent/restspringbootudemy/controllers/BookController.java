package com.br.ent.restspringbootudemy.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.data.dtos.BookDTO;
import com.br.ent.restspringbootudemy.services.BookServices;

import io.swagger.annotations.Api;

@Api(value = "Book Routes", description = "Description for Books", tags = {"Book Routes"})
@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	private BookServices bookServ;

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookDTO> findAll() {
		List<BookDTO> persons = bookServ.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookDTO findById(@PathVariable("id") Long id) {
		BookDTO personDTO = bookServ.findById(id);
		personDTO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookDTO create(@RequestBody BookDTO book) {
		BookDTO personDTO = bookServ.create(book);
		personDTO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return personDTO;
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookDTO update(@RequestBody BookDTO book) {
		BookDTO personDTO = bookServ.update(book);
		personDTO.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return personDTO;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		bookServ.delete(id);
		return ResponseEntity.ok().build();
	}
}
