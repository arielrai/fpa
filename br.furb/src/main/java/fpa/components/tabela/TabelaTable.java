package fpa.components.tabela;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import fpa.components.table.AbstractTableService;
import fpa.components.table.TableBean;
import fpa.components.table.TableSearchProperty;
import fpa.components.table.TableSortOrder;
import fpa.components.table.TableViewType;
import fpa.model.Tabela;

public class TabelaTable extends AbstractTableService<Tabela>{

	private EntityManager em;
	
	public TabelaTable(EntityManager em){
		this.em = em;
	}
	
	@Override
	protected String getTitle() {
		return "Tabelas";
	}

	@Override
	protected void createTable(TableBean<Tabela> tableInstance, int startPosition, int registrosPorPagina,
			List<TableSearchProperty> searchProps, String sortBy, String sortOrder) {
		//TODO colocar em resources os nomes
		tableInstance.createHead("nome", "Nome", "text-left");
		tableInstance.createHead("dataInicialFormatada", "Data Inicial", "text-left");
		tableInstance.createHead("dataFinalFormatada", "Data Entrega", "text-left");
		tableInstance.createHead("valorFormatado", "Valor Hora", "text-right");
		
		//Adiciona as rows
		Criteria tabelaCriteria = em.unwrap(Session.class).createCriteria(Tabela.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		tabelaCriteria.setFirstResult(startPosition);
		tabelaCriteria.setMaxResults(registrosPorPagina);

		//TODO adicionar os filtros
		if (searchProps != null && searchProps.isEmpty()) {
		}
		
		if (sortBy != null && !sortBy.isEmpty()) {
			Order hibernateOrder = TableSortOrder.getHibernateOrder(sortBy, sortOrder);
			tabelaCriteria.addOrder(hibernateOrder);
		}
		
		List<Tabela> list = tabelaCriteria.list();
		tableInstance.setRows(list);

	}

	@Override
	protected TableViewType getViewType() {
		// TODO Auto-generated method stub
		return null;
	}

}
