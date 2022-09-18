package com.algaworks.cursoalga.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.cursoalga.api.assembler.OcorrenciaAssembler;
import com.algaworks.cursoalga.api.model.OcorrenciaModel;
import com.algaworks.cursoalga.api.model.input.OcorrenciaInput;
import com.algaworks.cursoalga.domain.model.Entrega;
import com.algaworks.cursoalga.domain.model.Ocorrencia;
import com.algaworks.cursoalga.domain.service.BuscarEntregaService;
import com.algaworks.cursoalga.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {

	private BuscarEntregaService buscarEntregaService;
	private RegistroOcorrenciaService ocorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel solicitar(@PathVariable(name = "id") Long entregaId,@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrencia = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
		return ocorrenciaAssembler.toModel(ocorrencia);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable(name = "id") Long entregaId){
		Entrega entrega = buscarEntregaService.buscar(entregaId);
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}
	
}
