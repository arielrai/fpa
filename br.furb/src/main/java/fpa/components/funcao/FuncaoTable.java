package fpa.components.funcao;

import java.util.List;

import javax.persistence.EntityManager;

import fpa.components.table.AbstractTableService;
import fpa.components.table.TableBean;
import fpa.components.table.TableSearchProperty;
import fpa.components.table.TableViewType;
import fpa.model.Funcao;
import fpa.model.Projeto;

public class FuncaoTable extends AbstractTableService<Funcao> {

	private EntityManager em;	
	
	public FuncaoTable(EntityManager em) {
		this.em = em;
	}
	
	@Override
	protected void createTable(TableBean<Funcao> tableInstance, int startPosition, int registrosPorPagina,
			List<TableSearchProperty> searchProps, String sortBy, String sortOrder) {
		//TODO colocar em resources os nomes
		tableInstance.createHead("id", "Código", "text-left");
		tableInstance.createHead("nome", "Nome", "text-left");
		tableInstance.createHead("descricao", "Descrição", "text-left");
		tableInstance.createHead("valorFormatado", "Valor da função", "text-right");
		
		//Adiciona as rows
		Projeto projeto = em.find(Projeto.class, new Long(searchProps.get(0).getValue()));

		//TODO adicionar os filtros
		if (searchProps != null && searchProps.isEmpty()) {
//			projetoCriteria.add(Restrictions.eq(propertyName, value))
		}
		
//		if (sortBy != null && !sortBy.isEmpty()) {
//			Order hibernateOrder = TableSortOrder.getHibernateOrder(sortBy, sortOrder);
//			projetoCriteria.addOrder(hibernateOrder);
//		}
		
		tableInstance.setRows(projeto.getFuncoes());
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
