package com.br.ent.restspringbootudemy.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.data.dtos.PersonDTO;
import com.br.ent.restspringbootudemy.data.dtos2.PersonDTOV2;
import com.br.ent.restspringbootudemy.services.PersonServices;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	@Autowired
	private PersonServices personServ;

	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonDTO> findAll() {
		List<PersonDTO> persons = personServ.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO findById(@PathVariable("id") Long id) {
		PersonDTO personDTO = personServ.findById(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTO create(@RequestBody PersonDTO person) {
		PersonDTO personDTO = personServ.create(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personDTO;
	}

	@PostMapping(value = "/v2", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
		return personServ.createV2(person);
	}

	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTO update(@RequestBody PersonDTO person) {
		PersonDTO personDTO = personServ.update(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personDTO;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personServ.delete(id);
		return ResponseEntity.ok().build();
	}
}
