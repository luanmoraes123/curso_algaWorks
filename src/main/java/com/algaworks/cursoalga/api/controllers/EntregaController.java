package com.algaworks.cursoalga.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.cursoalga.api.model.DestinatarioModel;
import com.algaworks.cursoalga.api.model.EntregaModel;
import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.repository.EntregaRepository;
import com.algaworks.cursoalga.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaService.solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar(){
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long id){
		return entregaRepository.findById(id).map(e -> {
			EntregaModel entregaModel = modelMapper.map(e, EntregaModel.class);
									
			return ResponseEntity.ok(entregaModel);
		}).orElse(ResponseEntity.notFound().build());
		}
	
}
