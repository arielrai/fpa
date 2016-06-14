package fpa.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "funcao")
public class Funcao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nm_funcao", nullable = false)
	private String nome;

	@Column(name = "ds_funcao")
	private String descricao;
	
	@Column(name = "vl_funcao", nullable = false)
	private BigDecimal valorfuncao = new BigDecimal(0);
	
	@Column(name = "nr_horas", nullable = false)
	private int horas = 0;
	
	@Transient private String valorFormatado; 

//	@JoinColumn(foreignKey=@ForeignKey(name="fk_funcao_tabela_id_tabela"))
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL 
			,optional=false, targetEntity=FaixaComplexidade.class )
	private FaixaComplexidade complexidade;


	@Transient private Projeto projeto;


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
		if (!(obj instanceof Funcao)) {
			return false;
		}
		Funcao other = (Funcao) obj;
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

	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public FaixaComplexidade getComplexidade() {
		return complexidade;
	}
	
	public void setComplexidade(FaixaComplexidade complexidade) {
		this.complexidade = complexidade;
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
	
	public BigDecimal getValorfuncao() {
		return valorfuncao;
	}
	
	public void setValorfuncao(BigDecimal valorfuncao) {
		this.valorfuncao = valorfuncao;
	}
	
	public int getHoras() {
		return horas;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}
	
	public String getValorFormatado() {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorfuncao);
	}
}