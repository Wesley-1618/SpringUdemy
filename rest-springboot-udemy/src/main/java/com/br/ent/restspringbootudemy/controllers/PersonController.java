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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Routes", description = "Description for Persons", tags = {"Person Routes"})
@RestController
@RequestMapping("/api/person")
public class PersonController {
	@Autowired
	private PersonServices personServ;
	
	@ApiOperation(value="Find all peoples recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonDTO> findAll() {
		List<PersonDTO> persons = personServ.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}
	
	@ApiOperation(value = "Find People With id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO findById(@PathVariable("id") Long id) {
		PersonDTO personDTO = personServ.findById(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}

	@ApiOperation(value = "Create One New People")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTO create(@RequestBody PersonDTO person) {
		PersonDTO personDTO = personServ.create(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personDTO;
	}
	
	@ApiOperation(value = "Create People on New Version of Endpoint for Peoples")
	@PostMapping(value = "/v2", produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {
		return personServ.createV2(person);
	}
	
	@ApiOperation(value = "Update One People of Database")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonDTO update(@RequestBody PersonDTO person) {
		PersonDTO personDTO = personServ.update(person);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return personDTO;
	}
	
	@ApiOperation(value = "Delete One People Passing If For Reference Search the Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personServ.delete(id);
		return ResponseEntity.ok().build();
	}
}
