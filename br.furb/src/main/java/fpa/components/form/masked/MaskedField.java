package fpa.components.form.masked;

import fpa.components.form.FormField;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 * 
 */
public class MaskedField extends FormField {

	public MaskedField(String key, MaskedFieldOptions maskOptions) {
		super(key, "maskedInput", maskOptions);
	}

}
