package fpa.components.table;

import org.hibernate.criterion.Order;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public enum TableSortOrder {

	ASC("asc"), DESC("desc");

	private String desc;

	private TableSortOrder(String desc) {
		this.desc = desc;
	}

	public static Order getHibernateOrder(String property, String sortType) {
		if (sortType == null) {
			return null;
		}
		return sortType.equals(ASC.desc) ? Order.asc(property) : Order.desc(property);
	}
}