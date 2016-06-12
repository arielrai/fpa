package fpa.model;

import java.util.List;

public class FuncaoPojo {

	private Long complexidadeId;
	private Funcao funcao;
	private List<Tabela> tabelas;
	
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public List<Tabela> getTabelas() {
		return tabelas;
	}
	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	public void setComplexidadeId(Long complexidadeId) {
		this.complexidadeId = complexidadeId;
	}
	
	public Long getComplexidadeId() {
		return complexidadeId;
	}
	
}
