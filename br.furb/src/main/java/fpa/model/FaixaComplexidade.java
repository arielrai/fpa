package fpa.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Faixa_Complexidade")
@XmlRootElement
public class FaixaComplexidade implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "ds_faixa", nullable = false)
	private String descricao;

	@Column(name = "qt_de", nullable = false)
	private int quantidadeDe;

	@Column(name = "qt_ate", nullable = false)
	private int quantidadeAte;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidadeDe() {
		return quantidadeDe;
	}

	public void setQuantidadeDe(int quantidadeDe) {
		this.quantidadeDe = quantidadeDe;
	}

	public int getQuantidadeAte() {
		return quantidadeAte;
	}

	public void setQuantidadeAte(int quantidadeAte) {
		this.quantidadeAte = quantidadeAte;
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