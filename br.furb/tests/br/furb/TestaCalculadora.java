package br.furb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fpa.core.FPACalculator;
import fpa.model.Contribuicao;

public class TestaCalculadora {

	private Contribuicao contribuicao;

	@Before
	public void setUp() throws Exception {
		contribuicao = new Contribuicao();
		
		contribuicao.setTipo("ALI");
		contribuicao.setBaixa(7);
		contribuicao.setMedia(10);
		contribuicao.setAlta(15);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		FPACalculator.calculaValorFuncao(funcao, faixaComplexidade, contribuicao, tiposDeDado, tiposDeRegistro)
	}

}
