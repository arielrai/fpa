package fpa.components.form;

import java.util.List;
import java.util.Map;

public class Form<T> {
	
	private T pojo;
	private List<FormField> fields;
	private String title;
	private Map<String, Object> params;
	
	public Form(T pojo, List<FormField> fields, String title, Map<String, Object> params) {
		super();
		this.params = params;
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
	
	public Map<String, Object> getParams() {
		return params;
	}
}
