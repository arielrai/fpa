package fpa.components.projeto;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import fpa.components.table.AbstractTableService;
import fpa.components.table.TableBean;
import fpa.components.table.TableSearchProperty;
import fpa.components.table.TableSortOrder;
import fpa.components.table.TableViewType;
import fpa.model.Projeto;

public class ProjetoTable extends AbstractTableService<Projeto> {

	private EntityManager em;
	public ProjetoTable(EntityManager em) {
		this.em = em;
	}
	
	@Override
	protected void createTable(TableBean<Projeto> tableInstance, int startPosition, int registrosPorPagina,
			List<TableSearchProperty> searchProps, String sortBy, String sortOrder) {
		//TODO colocar em resources os nomes
		tableInstance.createHead("nome", "Nome", "text-left");
		tableInstance.createHead("dataInicialFormatada", "Data Inicial", "text-left");
		tableInstance.createHead("dataFinalFormatada", "Data Entrega", "text-left");
		tableInstance.createHead("valorFormatado", "Valor Hora", "text-right");
		
		//Adiciona as rows
		Criteria projetoCriteria = em.unwrap(Session.class).createCriteria(Projeto.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		projetoCriteria.setFirstResult(startPosition);
		projetoCriteria.setMaxResults(registrosPorPagina);

		//TODO adicionar os filtros
		if (searchProps != null && searchProps.isEmpty()) {
		}
		
		if (sortBy != null && !sortBy.isEmpty()) {
			Order hibernateOrder = TableSortOrder.getHibernateOrder(sortBy, sortOrder);
			projetoCriteria.addOrder(hibernateOrder);
		}
		
		List<Projeto> list = projetoCriteria.list();
		;
		
		for (Projeto projeto : list) {
			Hibernate.initialize(projeto.getFuncoes());
			Hibernate.initialize(projeto.getTabelas());
		}
		tableInstance.setRows(list);
	}

	@Override
	protected String getTitle() {
		return "Projetos";
	}
	
	@Override
	protected TableViewType getViewType() {
		return TableViewType.CADASTRO;
	}
}
