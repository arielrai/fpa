package fpa.components.projeto;

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
import fpa.model.Projeto;

@Singleton
public class ProjetoTable extends AbstractTableService<Projeto> {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;	
	
	@Override
	protected void createTable(TableBean<Projeto> tableInstance, int startPosition, int registrosPorPagina,
			List<TableSearchProperty> searchProps, String sortBy, String sortOrder) {
		//TODO colocar em resources os nomes
		tableInstance.createHead("nome", "Nome", "text-left");
		tableInstance.createHead("dataInicial", "Data Inicial", "text-left");
		tableInstance.createHead("dataFinal", "Data Entrega", "text-left");
		tableInstance.createHead("valorFormatado", "Valor Hora", "text-right");
		
		//Adiciona as rows
		Criteria projetoCriteria = em.unwrap(Session.class).createCriteria(Projeto.class);
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
		return "Projetos";
	}
}
