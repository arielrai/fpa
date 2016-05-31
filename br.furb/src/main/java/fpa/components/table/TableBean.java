package fpa.components.table;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma table
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 * @param <T>
 */
public class TableBean<T>  {

	private int page; 
	private List<T> rows; 
	private int size; 
	private int count; 
	private List<TableHead> headers = new ArrayList<>();
	private TablePagination pagination;
	private String sortBy;
	private String sortOrder;
	private String title;
	
	public TableBean<T> create(int page, String title){
		this.page = page;
		this.title = title;
		return this;
	};

	public TablePagination getPagination() {
		return pagination != null ? pagination
				: new TablePagination(page, (int) (size != 0 ? size / count : size), size);
	}
	
	public void setPagination(TablePagination pagination) {
		this.pagination = pagination;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public void createHead(final String key, final String nome, final String style) {
		headers.add(new TableHead(key, nome, style));
	}
	
	public void setRows(List<T> rows) {
		this.rows = rows;
		this.count = rows.size();
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public List<TableHead> getHeader() {
		return headers;
	}
	
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	
	public String getSortBy() {
		return sortBy;
	}
	
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
