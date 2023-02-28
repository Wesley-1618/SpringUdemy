package com.br.ent.restspringbootudemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.ent.restspringbootudemy.Services.PersonServices;
import com.br.ent.restspringbootudemy.models.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonServices personServ;
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findById(@PathVariable("id") String id) {
		return personServ.findById(id);
	}
}
