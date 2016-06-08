package fpa.components.form;

public class FormField {

	private String type;
	private String key;
	private FormFieldOptions templateOptions;
	
	public FormField() {
	}

	public FormField(String key, String type, FormFieldOptions templateOptions) {
		super();
		this.type = type;
		this.key = key;
		this.templateOptions = templateOptions;
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public FormFieldOptions getTemplateOptions() {
		return templateOptions;
	}
	
	
}
