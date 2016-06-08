package fpa.model;

import java.io.Serializable;
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

@Entity
@Table(name = "faixa_complexidade")
public class FaixaComplexidade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nm_faixa", nullable = false)
	private String nome;
	
	@Column(name = "ds_faixa", nullable = false)
	private String descricao;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, 
			targetEntity=FaixaComplexidadeIntervalo.class, orphanRemoval=true)
    private List<FaixaComplexidadeIntervalo> intervaloDados;

	public Long getId() {
		return this.id;
	}
	
	public List<FaixaComplexidadeIntervalo> getIntervaloDados() {
		return intervaloDados;
	}

	public void setIntervaloDados(List<FaixaComplexidadeIntervalo> intervaloDados) {
		this.intervaloDados = intervaloDados;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FaixaComplexidade)) {
			return false;
		}
		FaixaComplexidade other = (FaixaComplexidade) obj;
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
}