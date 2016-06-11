package fpa.components.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpa.components.form.enum_.EnumField;
import fpa.components.form.masked.MaskedField;
import fpa.components.form.masked.MaskedFieldOptions;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 * @param <T>
 */
public abstract class AbstractFormService<T> {

	protected abstract T newBean();
	
	protected Map<String, Object> params(T newBean){
		return new HashMap<>();
	}
	
	public AbstractFormService() {
	}
	
	public Form<T> getForm(){
		T newBean = newBean();
		return new Form<T>(newBean, getFields(new ArrayList<FormField>()), getTitle(newBean), params(newBean));
	}; 
	
	public Form<T> getForm(T bean){
		return new Form<T>(bean, getFields(new ArrayList<FormField>()), getTitle(bean), params(bean));
	}; 
	
	protected abstract List<FormField> getFields(ArrayList<FormField> fields);
	
	protected MaskedField createMaskedField(String key, String label, String placeHolder, String mask, boolean required){
		return new MaskedField(key, new MaskedFieldOptions(label, placeHolder, required, mask));
	}
	
	protected FormField createTextField(String key, String label, String placeHolder, boolean required){
		return new FormField(key, "input", new FormFieldOptions("text", label, placeHolder, required));
	}
	
	protected FormField createTextArea(String key, String label, String placeHolder, boolean required){
		return new FormField(key, "textarea", new FormFieldOptions("text", label, placeHolder, required));
	}
	
	protected FormField createNumberField(String key, String label, String placeHolder, boolean required){
		return new FormField(key, "input", new FormFieldOptions("number", label, placeHolder, required));
	}
	
	protected FormField createEmailField(String key, String label, String placeHolder, boolean required){
		return new FormField(key, "input", new FormFieldOptions("email", label, placeHolder, required));
	}
	
	protected FormField createDateField(String key, String label, String placeHolder, boolean required){
		return new FormField(key, "input", new FormFieldOptions("date", label, placeHolder, required));
	}
	
	protected FormField createCheckField(String key, String label, boolean required){
		return new FormField(key, "checkbox", new FormFieldOptions(null, label, null, required));
	}
	
	protected EnumField<T> createEnumField(String key, String label, boolean required, T[] options){
		return new EnumField<T>(key, new FormFieldOptions(null, label, null, required), options);
	}
	
	public abstract String getTitle(T entity);
}

