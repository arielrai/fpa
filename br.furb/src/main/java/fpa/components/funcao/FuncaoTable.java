package fpa.components.funcao;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import fpa.components.table.AbstractTableService;
import fpa.components.table.TableBean;
import fpa.components.table.TableSearchProperty;
import fpa.components.table.TableSortOrder;
import fpa.components.table.TableViewType;
import fpa.model.Funcao;

@Singleton
public class FuncaoTable extends AbstractTableService<Funcao> {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;	
	
	public FuncaoTable() {
	}
	
	@Override
	protected void createTable(TableBean<Funcao> tableInstance, int startPosition, int registrosPorPagina,
			List<TableSearchProperty> searchProps, String sortBy, String sortOrder) {
		//TODO colocar em resources os nomes
		tableInstance.createHead("id", "Código", "text-left");
		tableInstance.createHead("nome", "Nome", "text-left");
		tableInstance.createHead("descricao", "Descrição", "text-left");
		
		//Adiciona as rows
		Criteria projetoCriteria = em.unwrap(Session.class).createCriteria(Funcao.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		projetoCriteria.setFirstResult(startPosition);
		projetoCriteria.setMaxResults(registrosPorPagina);

		//TODO adicionar os filtros
		if (searchProps != null && searchProps.isEmpty()) {
		}
		
		if (sortBy != null && !sortBy.isEmpty()) {
			Order hibernateOrder = TableSortOrder.getHibernateOrder(sortBy, sortOrder);
			projetoCriteria.addOrder(hibernateOrder);
		}
		
		tableInstance.setRows(projetoCriteria.list());
	}

	@Override
	protected String getTitle() {
		return "Funções";
	}
	
	@Override
	protected TableViewType getViewType() {
		return TableViewType.CADASTRO;
	}
}
