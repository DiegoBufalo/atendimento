package com.dbufalo.atendimento.persistence.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.dbufalo.atendimento.persistence.entity.AtendimentoEntity;

import reactor.core.publisher.Flux;

public interface AtendimentoRepository extends ReactiveCrudRepository<AtendimentoEntity, Integer>{

	Flux<AtendimentoEntity> findByIdUsuario(Integer idUsuario);
	
	Flux<AtendimentoEntity> findAllByIdUsuario(Integer idUsuario);


}
