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
			FaixaComplexidade faixaComplexidadeALI = new FaixaComplexidade();
			faixaComplexidadeALI.setDescricao("Tabela ALI AIE");
			faixaComplexidadeALI.setNome("Tabela ALI AIE");
			
			FaixaComplexidade faixaComplexidadeSE = new FaixaComplexidade();
			faixaComplexidadeSE.setDescricao("Tabela SE CE");
			faixaComplexidadeSE.setNome("Tabela SE CE");
			
			FaixaComplexidade faixaComplexidadeEE = new FaixaComplexidade();
			faixaComplexidadeEE.setDescricao("Tabela EE");
			faixaComplexidadeEE.setNome("Tabela EE");
			
			carregaDados(faixaComplexidadeALI, faixaComplexidadeSE, faixaComplexidadeEE);

			em.persist(faixaComplexidadeALI);
			em.persist(faixaComplexidadeSE);
			em.persist(faixaComplexidadeEE);
			
		}
	}

	@PreDestroy
	private void shutdown() {

	}
	
	private void carregaDados(FaixaComplexidade faixaComplexidadeALI, FaixaComplexidade faixaComplexidadeSE, FaixaComplexidade faixaComplexidadeEE){
		
		List<FaixaComplexidadeIntervalo> faixasComplexidadeIntervaloALI = new ArrayList<>();
		
		//ALI AIE
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(0,  1,       0,      19,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(0,  1,       20,     49,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(0,  1,       50,     99999,   OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(2,  5,       0,      19,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(2,  5,       20,     49,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(2,  5,       50,     99999,   OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(5,  9999,    0,      19,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(5,  9999,    20,     49,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervaloALI.add(inserirLinhaColuna(5,  9999,    50,     99999,   OrdemComplexidade.ALTA    ));
		
		faixaComplexidadeALI.setIntervaloDados(faixasComplexidadeIntervaloALI);
		List<FaixaComplexidadeIntervalo> faixasComplexidadeIntervaloSE = new ArrayList<>();

		//SE CE
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(0,  1,       0,      5,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(0,  1,       6,      18,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(0,  1,       19,     9999,    OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(2,  3,       0,      5,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(2,  3,       6,      18,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(2,  3,       19,     9999,    OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(4,  9999,    0,      5,       OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(4,  9999,    6,      18,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervaloSE.add(inserirLinhaColuna(4,  9999,    19,     9999,    OrdemComplexidade.ALTA    ));

		faixaComplexidadeSE.setIntervaloDados(faixasComplexidadeIntervaloSE);
		List<FaixaComplexidadeIntervalo> faixasComplexidadeIntervaloEE = new ArrayList<>();

		//EE
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(0,  1,       0,      4,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(0,  1,       5,      14,      OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(0,  1,       15,     99999,   OrdemComplexidade. MEDIA  ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(2,  2,       0,      4,       OrdemComplexidade.BAIXA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(2,  2,       5,      14,      OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(2,  2,       15,     99999,   OrdemComplexidade. ALTA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(3,  9999,    0,      4,       OrdemComplexidade.MEDIA   ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(3,  9999,    5,      14,      OrdemComplexidade.ALTA    ));
		faixasComplexidadeIntervaloEE.add(inserirLinhaColuna(3,  9999,    15,     99999,   OrdemComplexidade.ALTA    ));
		
		faixaComplexidadeEE.setIntervaloDados(faixasComplexidadeIntervaloEE);
		
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