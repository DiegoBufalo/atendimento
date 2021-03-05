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
public class Atendimento {
	
	private Integer id;

	private Integer idUsuario;
	
	private List<String> logAtendimento;
	
	public AtendimentoEntity fromModel(Atendimento atendimento) {
		return 
				new AtendimentoEntity(
						atendimento.getId(),
						atendimento.getIdUsuario(),
						atendimento.getLogAtendimento()
						);
	}
		
	public static Mono<Atendimento> fromEntity(AtendimentoEntity entity){
		return Mono.just(entity)
					.flatMap(x ->{
						Atendimento atendimento = new Atendimento();
					
							atendimento.setId(x.getId());
							atendimento.setIdUsuario(x.getIdUsuario());
							atendimento.setLogAtendimento(x.getLogAtendimento());
							
						return Mono.just(atendimento);
				});
	}
	
	
	public static Mono<Atendimento> fromEntity(Mono<AtendimentoEntity> entity){
		return entity
				.flatMap(x->{
					Atendimento atendimento = new Atendimento();
					
						atendimento.setId(x.getId());
						atendimento.setIdUsuario(x.getIdUsuario());
						atendimento.setLogAtendimento(x.getLogAtendimento());
						
					return Mono.just(atendimento);
				});
	}
	

}
