package fpa.model.domain;

public enum FatorAjuste {

	COMUNICACAO_DE_DADOS("Comunicação de dados"),
	PROCESSAMENTO_DISTRIBUIDO("Processamento distribuido"),
	PERFORMANCE("Performance"),
	UTILIZACAO_DE_EQUIPAMENTOS("Utilização de equipamentos"),
	VOLUME_DE_TRANSACOES("Volume de transaçõe"),
	ENTRADA_DE_DADOS_ONLINE("Entrada de dados \"online\""),
	EFICIENCIA_DO_USUARIO_FINAL("Eficiencia do usuário final"),
	ATUALIZACAO_ONLINE("Atualização \"online\""),
	PROCESSAMENTO_COMPLEXO("Processamento complexo"),
	REUTILIZACAO_DE_CODIGO("Reutilização de código"),
	FACILIDADE_DE_IMPLANTACAO("Facilidade de implantação"),
	FACILIDADE_OPERACIONAL("Facilidade operacional"),
	MULTIPLOS_LOCAIS("Múltiplos locais"),
	FACILIDADE_DE_MUDANCAS("Facilidade de mudanças");
	
	private String descricao;
	
	FatorAjuste(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}

