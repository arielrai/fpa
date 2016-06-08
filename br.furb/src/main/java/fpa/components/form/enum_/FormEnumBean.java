package fpa.components.form.enum_;

public class FormEnumBean<T> {

	private String desc; 
	private T value;
	
	public FormEnumBean(String desc, T value) {
		super();
		this.desc = desc;
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	
	
}
