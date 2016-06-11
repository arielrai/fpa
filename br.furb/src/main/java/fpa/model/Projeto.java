package fpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.Length;

import fpa.util.DateUtils;
import fpa.validation.AfterDateValidator;

/**
 * Represent a Project
 * 
 * @author arielrai (arielrairodrigues@gmail.com)
 *
 */
@Entity
@Table(name = "projeto")
@AfterDateValidator(getPreviousDateField = "dataInicial", getAfterDateField = "dataFinal")
public class Projeto implements Serializable, PersistentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nm_projeto", nullable = false)
	@Length(min = 5)
	private String nome;

	@Column(length = 4000, name = "ds_projeto")
	@Length(max = 4000)
	private String descricao;

	@Column(name = "vl_hora", nullable = false)
	private BigDecimal valorHora = new BigDecimal(0);

	@Column(name = "dt_inicio")
	private LocalDate dataInicial;

	@Column(name = "dt_final")
	private LocalDate dataFinal;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProjetoComplexidade> complexidades = new ArrayList<ProjetoComplexidade>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Projeto)) {
			return false;
		}
		Projeto other = (Projeto) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorHora() {
		return valorHora;
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		if (descricao != null && !descricao.trim().isEmpty())
			result += ", descricao: " + descricao;
		return result;
	}

	public String getValorFormatado() {
		if (this.getValorHora() == null) {
			return "";
		}
		return NumberFormat.getCurrencyInstance().format(this.getValorHora());
	}

	public String getDataInicialFormatada() {
		return DateUtils.format(dataInicial);
	}

	public String getDataFinalFormatada() {
		return DateUtils.format(dataFinal);
	}

	public List<ProjetoComplexidade> getComplexidades() {
		return this.complexidades;
	}

	public void setComplexidades(final List<ProjetoComplexidade> complexidades) {
		this.complexidades = complexidades;
	}
}