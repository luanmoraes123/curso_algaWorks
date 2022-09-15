package com.algaworks.cursoalga.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.cursoalga.domain.exception.NegocioException;
import com.algaworks.cursoalga.domain.model.Cliente;
import com.algaworks.cursoalga.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail()).stream()
				.anyMatch(c -> !c.equals(cliente));
		
		if(emailExistente) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}	
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
}
