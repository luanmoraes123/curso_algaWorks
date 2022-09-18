package com.algaworks.cursoalga.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.algaworks.cursoalga.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		
	
	@ManyToOne
	private Cliente cliente;
	
	
	@Embedded
	private Destinatario destinatario;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	
	private BigDecimal taxa;
	
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	
	private OffsetDateTime dataPedido;
	
	
	private OffsetDateTime dataFinalizacao;


	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);
		this.getOcorrencias().add(ocorrencia);
		return ocorrencia;		
	}


	public void finalizar() {
		if (!this.getStatus().equals(StatusEntrega.PENDENTE)) {
			throw new NegocioException("Entrega não pode ser finalizada");
		}
		this.setStatus(StatusEntrega.FINALIZADA);
		this.setDataFinalizacao(OffsetDateTime.now());
	}
}
