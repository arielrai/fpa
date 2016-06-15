package br.furb;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fpa.core.FPACalculator;
import fpa.model.Contribuicao;
import fpa.model.FaixaComplexidade;
import fpa.model.Funcao;
import fpa.model.Projeto;
import fpa.start.StartupBean;

public class TestaCalculadora {

	private Contribuicao contribuicao;
	private Funcao funcao;
	private FaixaComplexidade faixaComplexidadeALI;
	private int tiposDeDado;
	private int tiposDeRegistro;

	@Before
	public void setUp() throws Exception {
		contribuicao = new Contribuicao();
		
		contribuicao.setTipo("ALI");
		contribuicao.setBaixa(7);
		contribuicao.setMedia(10);
		contribuicao.setAlta(15);
		tiposDeDado = 10;
		tiposDeRegistro = 15;
		
		funcao = new Funcao();
		funcao.setHoras(4);
		
		Projeto projeto = new Projeto();
		projeto.setValorHora(new BigDecimal(100));
		funcao.setProjeto(projeto);
		
		FaixaComplexidade faixaComplexidadeALI = new FaixaComplexidade();
		FaixaComplexidade faixaComplexidadeAIE = new FaixaComplexidade();
		FaixaComplexidade faixaComplexidadeSE = new FaixaComplexidade();
		FaixaComplexidade faixaComplexidadeCE = new FaixaComplexidade();
		FaixaComplexidade faixaComplexidadeEE = new FaixaComplexidade();
		StartupBean.carregaDados(faixaComplexidadeALI, faixaComplexidadeAIE, faixaComplexidadeSE, faixaComplexidadeCE, faixaComplexidadeEE);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void test() {
		
		FPACalculator.calculaValorFuncao(funcao, faixaComplexidadeALI, contribuicao, tiposDeDado, tiposDeRegistro);
	}

}
