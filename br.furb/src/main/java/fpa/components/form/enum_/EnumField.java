package fpa.components.form.enum_;

import java.util.Arrays;
import java.util.List;

import fpa.components.form.FormField;
import fpa.components.form.FormFieldOptions;

public class EnumField<T> extends FormField {
	
	public List<T> options;
	
	public EnumField(String key, FormFieldOptions templateOptions, T[] options) {
		super(key, "enum", templateOptions);
		this.options = Arrays.asList(options);
	}

	public List<T> getOptions() {
		return options;
	}
}
