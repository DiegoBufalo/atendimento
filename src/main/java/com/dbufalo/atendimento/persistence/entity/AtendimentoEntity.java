package com.dbufalo.atendimento.persistence.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.dbufalo.atendimento.model.Atendimento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("atendimento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoEntity {
	
	@Id
	private Integer id;
	
	@Column("id_usuario")
	private Integer idUsuario;
	
	@Column("log_atendimento")
	private List<String> logAtendimento;
	
	public AtendimentoEntity(Atendimento atendimento) {
		super();
		this.id = atendimento.getId();
		this.idUsuario = atendimento.getIdUsuario();
		this.logAtendimento = atendimento.getLogAtendimento();
	}

}
