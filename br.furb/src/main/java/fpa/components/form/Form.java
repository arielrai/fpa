package fpa.components.form;

import java.util.List;

public class Form<T> {
	
	private T pojo;
	private List<FormField> fields;
	
	public Form(T pojo, List<FormField> fields) {
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

}
