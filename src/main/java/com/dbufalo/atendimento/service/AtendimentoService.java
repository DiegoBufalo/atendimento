package com.dbufalo.atendimento.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dbufalo.atendimento.model.Atendimento;
import com.dbufalo.atendimento.model.AtendimentoUsuario;
import com.dbufalo.atendimento.model.Usuario;
import com.dbufalo.atendimento.persistence.entity.AtendimentoEntity;
import com.dbufalo.atendimento.persistence.repository.AtendimentoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class AtendimentoService {
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;

	@Autowired
	public WebClient webUsuario;
	
	public Mono<Atendimento> novoAtendimento(Atendimento atendimento) throws InterruptedException, ExecutionException {

			Usuario usuario = webUsuario
				.method(HttpMethod.GET)
				.uri("clientes/{id}", atendimento.getIdUsuario())
				.retrieve()
				.bodyToMono(Usuario.class)
				.toFuture().get();
		
		return Mono.just(new AtendimentoEntity(atendimento))
					.flatMap(x->{
						AtendimentoEntity entity = (AtendimentoEntity)x;
						entity.setIdUsuario(usuario.getId());
						
						return this.atendimentoRepository.save(entity);
					})
					.flatMap(Atendimento::fromEntity);
				
				
	}
	public Mono<AtendimentoUsuario> buscarAtendimento(Integer id) {
		return atendimentoRepository.findById(id)
					.flatMap(x->{
						AtendimentoUsuario atendimento = new AtendimentoUsuario();
						
						atendimento.setId(x.getId());
						atendimento.setIdUsuario(x.getIdUsuario());
						atendimento.setLogAtendimento(x.getLogAtendimento());
						try {
							atendimento.setNome(buscaUsuario(x.getIdUsuario()));
						} catch (InterruptedException | ExecutionException e) {
							e.printStackTrace();
						}
						return Mono.just(atendimento);
					});
	}

	
	public Flux<AtendimentoUsuario> buscarAtendimentosUsuario(Integer id) {
		return atendimentoRepository.findAllByIdUsuario(id)
				.flatMap(x->{
					Mono<AtendimentoUsuario> atendimento = null;
					try {
						atendimento = AtendimentoUsuario.fromEntity(x,buscaUsuario(id));
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
					return atendimento;
					
				});
}
	
	public String buscaUsuario(Integer id) throws InterruptedException, ExecutionException {
		return webUsuario
				.method(HttpMethod.GET)
				.uri("clientes/{id}",id)
				.retrieve()
				.bodyToMono(Usuario.class)
				.toFuture().get().getNome();
	}
}
	

