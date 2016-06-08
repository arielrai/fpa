package fpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
@Table(name = "funcao_tabela")
public class FuncaoTabela implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, 
			mappedBy="tabela",targetEntity=Campo.class, orphanRemoval=true)
    private List<Campo> campos;
	
	@JoinColumn(foreignKey=@ForeignKey(name="fk_funcao_id_funcao"))
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL 
			,optional=false, targetEntity=Tabela.class )
	private Funcao funcao;

	@JoinColumn(foreignKey=@ForeignKey(name="fk_funcao_tabela_id_tabela"))
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL 
			,optional=false, targetEntity=Tabela.class )
	private Tabela tabela;
	
	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
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
		if (!(obj instanceof FuncaoTabela)) {
			return false;
		}
		FuncaoTabela other = (FuncaoTabela) obj;
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