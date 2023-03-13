package com.br.ent.restspringbootudemy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ent.restspringbootudemy.converter.DozerConverter;
import com.br.ent.restspringbootudemy.converter.custom.PersonConverter;
import com.br.ent.restspringbootudemy.data.dtos.PersonDTO;
import com.br.ent.restspringbootudemy.data.dtos2.PersonDTOV2;
import com.br.ent.restspringbootudemy.data.models.Person;
import com.br.ent.restspringbootudemy.repository.PersonRepository;

@Service
public class PersonServices {
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;
	
	public PersonDTO create(PersonDTO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(repository.save(entity), PersonDTO.class);
		return vo;
	}
	
	public PersonDTOV2 createV2(PersonDTOV2 person) {
		var entity = converter.convertDTOToEntity(person);
		var dto = converter.convertEntityToDTO(repository.save(entity));
		return dto;
	}
	
	public PersonDTO update(PersonDTO person) {
		PersonDTO entity = new PersonDTO();
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
	
	public PersonDTO findById(Long id) {
		var dto = DozerConverter.parseObject(repository.findById(id).orElseThrow(), PersonDTO.class);
		return  dto;
	}
	
	public List<PersonDTO> findAll() {
		var dtoList = DozerConverter.parseListObjects(repository.findAll(), PersonDTO.class);
		return dtoList;
	}
}
