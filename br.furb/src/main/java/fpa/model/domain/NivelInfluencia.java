package fpa.model.domain;

public enum NivelInfluencia {

	NENHUMA("Nenhuma influência"),
	MINIMA("Influência mínima"),
	MODERADA("Influência moderada"),
	MEDIA("Influência média"),
	SIGNIFICATIVA("Influência significativa"),
	GRANDE("Grande influência");
	
	private String descricao;
	
	NivelInfluencia(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
