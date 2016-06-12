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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@XmlRootElement
public class Projeto implements Serializable, PersistentBean {

	private transient String message;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nm_projeto", nullable = false)
	private String nome;

	@Column(name = "ds_projeto")
	private String descricao;

	@Column(name = "vl_hora", nullable = false)
	private BigDecimal valorHora = new BigDecimal(0);

	@Column(name = "dt_inicio")
	private LocalDate dataInicial;

	@Column(name = "dt_final")
	private LocalDate dataFinal;

	@OneToMany(targetEntity=ProjetoComplexidade.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ProjetoComplexidade> complexidades = new ArrayList<ProjetoComplexidade>();
	
	@OneToMany(targetEntity=Funcao.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<Funcao> funcoes = new ArrayList<Funcao>();
	
	@OneToMany(targetEntity=Tabela.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Tabela> tabelas = new ArrayList<>();

	@Transient
	private String dataInicialFormatada;

	@Transient
	private String dataFinalFormatada;

	@Transient
	private String valorFormatado;

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

	public void setDataFinalFormatada(String dataFinalFormatada) {
		this.dataFinalFormatada = dataFinalFormatada;
	}

	public void setDataInicialFormatada(String dataInicialFormatada) {
		this.dataInicialFormatada = dataInicialFormatada;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public List<Funcao> getFuncoes() {
		return funcoes;
	}
	
	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	
	public List<Tabela> getTabelas() {
		return tabelas;
	}
	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}
}