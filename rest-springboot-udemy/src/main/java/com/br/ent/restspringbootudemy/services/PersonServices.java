package com.br.ent.restspringbootudemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ent.restspringbootudemy.models.Person;
import com.br.ent.restspringbootudemy.repository.PersonRepository;

@Service
public class PersonServices {
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		return repository.save(person);
	}
	
	public Person update(Person person) {
		Person entity = new Person();
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		return entity;
	}
	
	public void delete(Long id) {
		Person person = repository.findById(id).orElseThrow();
		repository.delete(person);
	}
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow() ;
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
}
