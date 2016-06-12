/**
 * 
 */
package fpa.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fpa.model.Funcao;
import fpa.model.FuncaoTabela;
import fpa.model.Projeto;

/**
 * @author PauloArnoldo
 * Classe para calcular o total de FPAs.
 */
public class FPACalculator {
	@PersistenceContext(unitName = "primary")
	protected EntityManager manager;
	
	
	public FPACalculator(){
		
	}
	
	public int calcularProjeto(Projeto projeto){
		BigDecimal totalFuncao=null;
		
		List<Funcao> listaFuncoes = obterFuncoesProjeto(projeto.getId());
		
		for (Iterator<Funcao> iterator = listaFuncoes.iterator(); iterator.hasNext();) {
			Funcao funcao = iterator.next();
			
			
		}		
		
		return 0;
	}
	
	private List<Funcao> obterFuncoesProjeto(long projetoId){				
		Projeto projeto = manager.find(Projeto.class, projetoId);		
		return projeto.getFuncoes();
	}
	
	private List<FuncaoTabela> obterTabelasFuncao(Funcao funcao){
		
		Session session = manager.unwrap(Session.class);
		Criteria funcTabelaCriteria = session.createCriteria(FuncaoTabela.class);
		 
		funcTabelaCriteria.add(Restrictions.eq("funcao", funcao));
		List<FuncaoTabela> funcaoTabela = funcTabelaCriteria.list(); 
					
		return funcaoTabela;
	}
	
}
