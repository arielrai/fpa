package fpa.components.form;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 * @param <T>
 */
public class FormFieldOptions {

	private String type;
	private String label;
	private String placeholder;
	private boolean required;
	
	public FormFieldOptions(String type, String label, String placeholder, boolean required) {
		super();
		this.type = type;
		this.label = label;
		this.placeholder = placeholder;
		this.required = required;
	}

	public String getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public boolean isRequired() {
		return required;
	}
}
