package fpa.components.table;

import java.util.List;

/**
 * Define uma table abstrata
 * 
 * @author Ariel Rai Rodrigues(ariel.rodrigues@publica.inf.br)
 * @param <B> Bean de comunicação cliente servidor
 * 
 */
public abstract class AbstractTableService <B> {

	/**
	 * Cria a tabela
	 * @param page
	 * @param countPerPage
	 * @param sortBy
	 * @param sortOrder
	 * @param searchProps
	 * @return
	 */
	public TableBean<B> createTable(int page, int countPerPage, String sortBy, String sortOrder, List<TableSearchProperty> searchProps){
		try {
			TableBean<B> tableInstance = new TableBean<B>();
			tableInstance.create(page, getTitle()); 
			createTable(tableInstance, page*countPerPage, countPerPage, searchProps, sortBy, sortOrder);
			return tableInstance;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Título da tabela
	 * @return um {@link String} com o título da tabela
	 */
	protected abstract String getTitle();
	
	/**
	 * Cria os dados da tabela
	 * @param tableInstance - bean que será adicionado os dados
	 * @param startPosition - posição inicial da selação
	 * @param searchProps - propriedades de sel
	 * @param sortBy - campo de ordenação
	 * @param sortOrder - tipo de ordenação
	 */
	protected abstract void createTable(TableBean<B> tableInstance, int startPosition, int regPerPage, List<TableSearchProperty> searchProps, String sortBy,
			String sortOrder);

}
