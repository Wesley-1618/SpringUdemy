package com.br.ent.restspringbootudemy.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.br.ent.restspringbootudemy.data.dtos2.PersonDTOV2;
import com.br.ent.restspringbootudemy.data.models.Person;

@Service
public class PersonConverter {
	public PersonDTOV2 convertEntityToDTO(Person person) {
		PersonDTOV2 dto= new PersonDTOV2();
		dto.setId(person.getId());
		dto.setAdress(person.getAdress());
		dto.setFirstName(person.getFirstName());
		dto.setGender(person.getGender());
		dto.setLastName(person.getLastName());
		dto.setBirthDay(new Date());
		return dto;
	}
	
	public Person convertDTOToEntity(PersonDTOV2 person) {
		Person entity= new Person();
		entity.setId(person.getId());
		entity.setAdress(person.getAdress());
		entity.setFirstName(person.getFirstName());
		entity.setGender(person.getGender());
		entity.setLastName(person.getLastName());
		return entity;
	}
}
