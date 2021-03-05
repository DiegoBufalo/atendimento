package com.dbufalo.atendimento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	private Integer id;

	private String nome;

	private String cpf;

	private String email;

}
