package fpa.components.form;

import java.util.List;
import java.util.Map;

public class Form<T> {
	
	private T pojo;
	private List<FormField> fields;
	private String title;
	private Map<String, Object> params;
	
	public Form() {
	}
	
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

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	
}
