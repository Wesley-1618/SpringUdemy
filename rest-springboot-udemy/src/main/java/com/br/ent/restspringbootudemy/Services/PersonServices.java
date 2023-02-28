package com.br.ent.restspringbootudemy.Services;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.br.ent.restspringbootudemy.models.Person;

@Service
public class PersonServices {
	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("a");
		person.setLastName("b");
		person.setAdress("r n c");
		person.setGender("m");
		return person ;
	}
}
