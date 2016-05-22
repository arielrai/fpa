package fpa.components.table;

/**
 * Paginação das tabelas do sistema
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public class TablePagination {

	private int page;
	private int pages;
	private int size;
	private int countPerPage = 25;
	
	public TablePagination() {
	}

	public TablePagination(int page, int pages,int size) {
		super();
		this.page = page;
		this.pages = pages;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
	public int getCountPerPage() {
		return countPerPage;
	}
}
