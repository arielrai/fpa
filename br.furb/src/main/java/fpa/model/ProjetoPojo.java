package fpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetoPojo {

	private Long id;
	private Long version;
	private String nome;
	private String descricao;
	private String valorFormatado;
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private String valorHoraFormatada;
	private List<ProjetoComplexidade> complexidades = new ArrayList<ProjetoComplexidade>();

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}
	
	public String getValorFormatado() {
		return valorFormatado;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public String getValorHoraFormatada() {
		return valorHoraFormatada;
	}
	public void setValorHoraFormatada(String valorHoraFormatada) {
		this.valorHoraFormatada = valorHoraFormatada;
	}
	public List<ProjetoComplexidade> getComplexidades() {
		return complexidades;
	}
	public void setComplexidades(List<ProjetoComplexidade> complexidades) {
		this.complexidades = complexidades;
	}
	
	
}
