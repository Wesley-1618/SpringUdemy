package com.br.ent.restspringbootudemy.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.data.dtos.PersonDTO;
import com.br.ent.restspringbootudemy.data.dtos2.PersonDTOV2;
import com.br.ent.restspringbootudemy.services.PersonServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin
@Api(value = "Person Routes", description = "Description for Persons", tags = {"Person Routes"})
@RestController
@RequestMapping("/api/person")
public class PersonController {
	@Autowired
	private PersonServices personServ;
	
	
//	@CrossOrigin(origins="htpp://localhost:8080")
	@ApiOperation(value="Find all peoples recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Page<PersonDTO>> findAll(@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDiretions = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDiretions,"firstName"));
		Page<PersonDTO> persons = personServ.findAll(pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	
	@ApiOperation(value="Find person by name")
	@GetMapping(value="/findPersonByName/{firstName}" ,produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<Page<PersonDTO>> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		var sortDiretions = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit,Sort.by(sortDiretions,"firstName"));
		Page<PersonDTO> persons = personServ.findPersonByName(firstName,pageable);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	
//	@CrossOrigin(origins={"htpp://localhost:8080","localhost"})
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
	
	@ApiOperation(value = "Disable a specific person by your ID")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonDTO disablePerson(@PathVariable("id") Long id) {
		PersonDTO personDTO = personServ.disablePerson(id);
		personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personDTO;
	}
	
	@ApiOperation(value = "Delete One People Passing If For Reference Search the Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		personServ.delete(id);
		return ResponseEntity.ok().build();
	}
}
