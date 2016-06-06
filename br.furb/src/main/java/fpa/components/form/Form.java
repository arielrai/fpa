package fpa.components.form;

import java.util.List;

public class Form<T> {
	
	private T pojo;
	private List<FormField> fields;
	private String title;
	
	public Form(T pojo, List<FormField> fields, String title) {
		super();
		this.pojo = pojo;
		this.fields = fields;
	}

	public T getPojo() {
		return pojo;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public String getTitle() {
		return title;
	}
}
