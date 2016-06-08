package fpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import fpa.model.domain.FatorAjuste;
import fpa.model.domain.NivelInfluencia;

@Entity
@Table(name = "projeto_complexidade")
public class ProjetoComplexidade implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Enumerated
	@Column(name = "nr_nivel_influencia", nullable = false)
	private NivelInfluencia nivelInfluencia;

	@Enumerated
	@Column(name = "nr_fator_ajuste", nullable = false)
	private FatorAjuste fatorAjuste;

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
		if (!(obj instanceof ProjetoComplexidade)) {
			return false;
		}
		ProjetoComplexidade other = (ProjetoComplexidade) obj;
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

	public NivelInfluencia getNivelInfluencia() {
		return nivelInfluencia;
	}

	public void setNivelInfluencia(NivelInfluencia nivelInfluencia) {
		this.nivelInfluencia = nivelInfluencia;
	}

	public FatorAjuste getFatorAjuste() {
		return fatorAjuste;
	}

	public void setFatorAjuste(FatorAjuste fatorAjuste) {
		this.fatorAjuste = fatorAjuste;
	}
}