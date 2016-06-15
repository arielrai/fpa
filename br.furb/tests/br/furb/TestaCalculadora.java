package br.furb;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fpa.model.Contribuicao;
import fpa.model.FaixaComplexidade;
import fpa.model.Funcao;
import fpa.model.Projeto;

public class TestaCalculadora {

	private Contribuicao contribuicao;
	private Funcao funcao;
	private FaixaComplexidade faixaComplexidade;

	@Before
	public void setUp() throws Exception {
		contribuicao = new Contribuicao();
		
		contribuicao.setTipo("ALI");
		contribuicao.setBaixa(7);
		contribuicao.setMedia(10);
		contribuicao.setAlta(15);
		
		funcao = new Funcao();
		funcao.setHoras(4);
		
		Projeto projeto = new Projeto();
		projeto.setValorHora(new BigDecimal(100));
		funcao.setProjeto(projeto);
		
		faixaComplexidade = new FaixaComplexidade();
//		faixaComplexidade.setIntervaloDados(intervaloDados);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
//		FPACalculator.calculaValorFuncao(funcao, faixaComplexidade, contribuicao, tiposDeDado, tiposDeRegistro)
	}

}
