package fpa.components.form.masked;

import fpa.components.form.FormFieldOptions;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
public class MaskedFieldOptions extends FormFieldOptions {

	private String mask;
	
	public MaskedFieldOptions(String label, String placeHolder, boolean required, String mask) {
		super(label, placeHolder, "maskedInput", required);
		this.mask = mask;
	}
	
	public String getMask() {
		return mask;
	}
	
	public void setMask(String mask) {
		this.mask = mask;
	}

}
