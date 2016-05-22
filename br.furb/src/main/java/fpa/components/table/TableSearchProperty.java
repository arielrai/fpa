package fpa.components.table;

/**
 * TODO
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public class TableSearchProperty {

	//Propriedade buscada
	private String property;
	//Valor da propriedade
	private String value;

	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getProperty() {
		return property;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
