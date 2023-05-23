package com.br.ent.restspringbootudemy.data.dtos;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@JsonPropertyOrder({"id","lastName","firstName","adress","enabled"})
public class PersonDTO extends RepresentationModel<PersonDTO>{

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	@JsonProperty("primeiro_nome")
	private String firstName;
	@JsonProperty("ultimo_nome")
	private String lastName;
	@JsonProperty("endereco")
	private String adress;
	@JsonIgnore
	private String gender;
	@JsonProperty("ativo")
	private Boolean enabled;
	
	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(adress, enabled, firstName, gender, key, lastName);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		return Objects.equals(adress, other.adress) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(gender, other.gender)
				&& Objects.equals(key, other.key) && Objects.equals(lastName, other.lastName);
	}
	
}
