package com.mktech.mktechlog.domain.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank  //(message = "Nome não pode ser nulo ou vazio")
	@Size(max = 60)
	private String nome;

	@NotBlank  //(message = "Email não pode ser nulo ou vazio")
	@Email  //(message = "Email tem que estar formatado - seuendereço@email.com")
	@Size(max = 255)
	private String email;

	@NotBlank  //(message = "Telefone não pode ser nulo ou vazio")
	@Size(max = 20)
	private String telefone;


}
