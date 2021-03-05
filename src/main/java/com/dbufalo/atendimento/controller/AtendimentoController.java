package com.dbufalo.atendimento.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbufalo.atendimento.model.Atendimento;
import com.dbufalo.atendimento.model.AtendimentoUsuario;
import com.dbufalo.atendimento.service.AtendimentoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("atendimento")
public class AtendimentoController {
 
	@Autowired
	private AtendimentoService atendimentoService;

	@PostMapping("/novo")
	public Mono<Atendimento> novoAtendimento(@RequestBody Atendimento atendimento) throws InterruptedException, ExecutionException{
		return atendimentoService.novoAtendimento(atendimento);
	}
	
	@GetMapping("/{id}")
	public Mono<AtendimentoUsuario> buscarAtendimento(@PathVariable Integer id){
		return atendimentoService.buscarAtendimento(id);
	}
	
	@GetMapping("/usuario/{id}")
	public Flux<AtendimentoUsuario> buscarAtendimentosUsuario(@PathVariable Integer id){
		return atendimentoService.buscarAtendimentosUsuario(id);
	}
	
	@PostMapping("finalizar/{id}")
	public Mono<AtendimentoUsuario> finalizarAtendimento(@RequestBody Atendimento atendimento, @PathVariable Integer id){
		return atendimentoService.finalizarAtendimento(id, atendimento);
	}
	
}
