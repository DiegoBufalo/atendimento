package com.dbufalo.atendimento.model;

import java.util.List;

import com.dbufalo.atendimento.persistence.entity.AtendimentoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoUsuario {
	
	private Integer id;

	private Integer idUsuario;
	
	private String nome;
	
	private List<String> logAtendimento;
		
	public static Mono<AtendimentoUsuario> fromEntity(AtendimentoEntity entity){
		return Mono.just(entity)
					.flatMap(x ->{
						AtendimentoUsuario atendimento = new AtendimentoUsuario();
					
							atendimento.setId(x.getId());
							atendimento.setIdUsuario(x.getIdUsuario());
							atendimento.setLogAtendimento(x.getLogAtendimento());
							
						return Mono.just(atendimento);
				});
	}
	
	
	public static Mono<AtendimentoUsuario> fromEntity(Mono<AtendimentoEntity> entity){
		return entity
				.flatMap(x->{
					AtendimentoUsuario atendimento = new AtendimentoUsuario();
					
						atendimento.setId(x.getId());
						atendimento.setIdUsuario(x.getIdUsuario());
						atendimento.setLogAtendimento(x.getLogAtendimento());
						
					return Mono.just(atendimento);
				});
	}
	
	public static Mono<AtendimentoUsuario> fromEntity(AtendimentoEntity entity, String usuairo){
		return Mono.just(entity)
					.flatMap(x ->{
						AtendimentoUsuario atendimento = new AtendimentoUsuario();
					
							atendimento.setId(x.getId());
							atendimento.setIdUsuario(x.getIdUsuario());
							atendimento.setLogAtendimento(x.getLogAtendimento());
							atendimento.setNome(usuairo);
						return Mono.just(atendimento);
				});
	}
	
	public static Mono<AtendimentoUsuario> fromEntity(Mono<AtendimentoEntity> entity, String usuairo){
		return entity
					.flatMap(x ->{
						AtendimentoUsuario atendimento = new AtendimentoUsuario();
					
							atendimento.setId(x.getId());
							atendimento.setIdUsuario(x.getIdUsuario());
							atendimento.setLogAtendimento(x.getLogAtendimento());
							atendimento.setNome(usuairo);
						return Mono.just(atendimento);
				});
	}



}
