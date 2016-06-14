/**
 * 
 */
package fpa.core;

import java.math.BigDecimal;

import fpa.model.Contribuicao;
import fpa.model.FaixaComplexidade;
import fpa.model.FaixaComplexidadeIntervalo;
import fpa.model.Funcao;
import fpa.model.ProjetoComplexidade;

/**
 * Classe responsável por calcular o valor de uma função
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public class FPACalculator {
	
	public static BigDecimal calculaValorFuncao(Funcao funcao, FaixaComplexidade faixaComplexidade, Contribuicao contribuicao, int tiposDeDado, int tiposDeRegistro){
		
		BigDecimal valorAjuste = new BigDecimal(0);
		
		//Calcula o fator de ajuste
		for (ProjetoComplexidade nivelInfluencia : funcao.getProjeto().getComplexidades()) {
			switch (nivelInfluencia.getNivelInfluencia()) {
			case NENHUMA:
				valorAjuste = valorAjuste.add(new BigDecimal(0));
				break;
			case MINIMA:
				valorAjuste = valorAjuste.add(new BigDecimal(1).multiply(new BigDecimal(1)));			
				break;
			case MODERADA:
				valorAjuste = valorAjuste.add(new BigDecimal(2).multiply(new BigDecimal(1)));
				break;
			case MEDIA:
				valorAjuste = valorAjuste.add(new BigDecimal(3).multiply(new BigDecimal(1)));
				break;
			case SIGNIFICATIVA:
				valorAjuste = valorAjuste.add(new BigDecimal(4).multiply(new BigDecimal(1)));
				break;
			case GRANDE:
				valorAjuste = valorAjuste.add(new BigDecimal(5).multiply(new BigDecimal(1)));
				break;	
			default:
				break;
			}
		}
		valorAjuste = valorAjuste.multiply(new BigDecimal(0.01));
		valorAjuste = valorAjuste.add(new BigDecimal(0.65));
		
		FaixaComplexidadeIntervalo faixaEscolhida = null;
		//calcula o fator de produção bruto
		for (FaixaComplexidadeIntervalo intervalo : faixaComplexidade.getIntervaloDados()) {
			if (intervalo.getQtDeColuna() <= tiposDeDado && intervalo.getQtAteColuna() >= tiposDeDado) {
				if (intervalo.getQtDeLinha() <= tiposDeRegistro && intervalo.getQtAteLinha() >= tiposDeRegistro) {
					faixaEscolhida = intervalo;
					break;
				}
			}
		}
		int valorContribuicao = 0;
		switch (faixaEscolhida.getTipoComplexidade()) {
		case BAIXA:
			valorContribuicao = contribuicao.getBaixa();
			break;
		case MEDIA:
			valorContribuicao = contribuicao.getMedia();
			break;
		case ALTA:
			valorContribuicao = contribuicao.getAlta();
			break;
		default:
			break;
		}
		return ((new BigDecimal(valorContribuicao).multiply(valorAjuste)).multiply(new BigDecimal(funcao.getHoras()))).multiply(funcao.getProjeto().getValorHora());
		
	}
}
