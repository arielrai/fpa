package fpa.start;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import fpa.model.FaixaComplexidade;
import fpa.model.FaixaComplexidadeIntervalo;
import fpa.model.OrdemComplexidade;

@Singleton
@Startup
public class StartupBean {

	@PersistenceContext(unitName = "primary")
	protected EntityManager em;
	
	
	@PostConstruct
	private void startup() {
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.id());
		
		List list = em.unwrap(Session.class).createCriteria(FaixaComplexidade.class).setMaxResults(1)
				.setProjection(projectionList).list();
		if (list.isEmpty()) {
			
			
			List<FaixaComplexidadeIntervalo> faixasComplexidadeIntervalo = new ArrayList<>();
			
			carregaDados(faixasComplexidadeIntervalo);

			for (FaixaComplexidadeIntervalo faixaComplexidadeIntervalo : faixasComplexidadeIntervalo) {
				em.persist(faixaComplexidadeIntervalo);
			}
			
		}
	}

	@PreDestroy
	private void shutdown() {

	}
	
	private List<FaixaComplexidade> carregaDados(List<FaixaComplexidadeIntervalo>faixasComplexidadeIntervalo){
		
		List<FaixaComplexidade> faixaDeComplexidade = new ArrayList<>();
		
		//ALI AIE
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       0,      19,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       20,     49,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       50,     99999,   OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  5,       0,      19,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  5,       20,     49,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  5,       50,     99999,   OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(5,  9999,    0,      19,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(5,  9999,    20,     49,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(5,  9999,    50,     99999,   OrdemComplexidade.ALTA    ));

		//SE CE
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       0,      5,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       6,      18,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       19,     9999,    OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  3,       0,      5,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  3,       6,      18,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  3,       19,     9999,    OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(4,  9999,    0,      5,       OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(4,  9999,    6,      18,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(4,  9999,    19,     9999,    OrdemComplexidade.ALTA    ));


		//EE
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       0,      4,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       5,      14,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(0,  1,       15,     99999,   OrdemComplexidade. MEDIA  ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  2,       0,      4,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  2,       5,      14,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(2,  2,       15,     99999,   OrdemComplexidade. ALTA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(3,  9999,    0,      4,       OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(3,  9999,    5,      14,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervalo.add(inserirLinhaColuna(3,  9999,    15,     99999,   OrdemComplexidade.ALTA    ));
		
		
		return faixaDeComplexidade;
	}
	

	private FaixaComplexidadeIntervalo inserirLinhaColuna(int deLinha, int ateLinha, int deColuna,
			int ateColuna, OrdemComplexidade complexidade){
		FaixaComplexidadeIntervalo intervalo = new FaixaComplexidadeIntervalo();
		intervalo.setIntervaloLinha(deLinha, ateLinha);
		intervalo.setIntervaloColuna(deColuna, ateColuna);
		intervalo.setTipoComplexidade(complexidade);
		return intervalo;
	}

}