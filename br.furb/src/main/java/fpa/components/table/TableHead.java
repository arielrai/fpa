package fpa.components.table;

/**
 * Header da table
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public class TableHead {

	private String key;
	private String name;
	private String style;
	
	public TableHead(String key, String name, String style) {
		super();
		this.key = key;
		this.name = name;
		this.style = style;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
}
