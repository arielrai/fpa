package fpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "faixa_complexidade_intervalo")
public class FaixaComplexidadeIntervalo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "qt_de_coluna")
	private int qtDeColuna;

	@Column(name = "qt_ate_coluna")
	private int qtAteColuna;

	@Column(name = "qt_de_linha")
	private int qtDeLinha;

	@Column(name = "qt_ate_linha")
	private int qtAteLinha;

	@Enumerated(EnumType.STRING)
	@Column(name = "tp_complexidade")
	private OrdemComplexidade tipoComplexidade;

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
		if (!(obj instanceof FaixaComplexidadeIntervalo)) {
			return false;
		}
		FaixaComplexidadeIntervalo other = (FaixaComplexidadeIntervalo) obj;
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

	public int getQtDeColuna() {
		return qtDeColuna;
	}

	public void setQtDeColuna(int qtDeColuna) {
		this.qtDeColuna = qtDeColuna;
	}

	public int getQtAteColuna() {
		return qtAteColuna;
	}

	public void setQtAteColuna(int qtAteColuna) {
		this.qtAteColuna = qtAteColuna;
	}

	public int getQtDeLinha() {
		return qtDeLinha;
	}

	public void setIntervaloLinha(int de, int ate) {
		this.qtDeLinha = de;
		this.qtAteLinha = ate;
	}

	public void setIntervaloColuna(int de, int ate) {
		this.qtDeColuna = de;
		this.qtAteColuna = ate;
	}

	public void setQtDeLinha(int qtDeLinha) {
		this.qtDeLinha = qtDeLinha;
	}

	public int getQtAteLinha() {
		return qtAteLinha;
	}

	public void setQtAteLinha(int qtAteLinha) {
		this.qtAteLinha = qtAteLinha;
	}

	public OrdemComplexidade getTipoComplexidade() {
		return tipoComplexidade;
	}

	public void setTipoComplexidade(OrdemComplexidade tipoComplexidade) {
		this.tipoComplexidade = tipoComplexidade;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		result += "qtDeColuna: " + qtDeColuna;
		result += ", qtAteColuna: " + qtAteColuna;
		result += ", qtDeLinha: " + qtDeLinha;
		result += ", qtAteLinha: " + qtAteLinha;
		return result;
	}

}