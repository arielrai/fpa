package fpa.components.wizard;

import fpa.components.form.AbstractFormService;
import fpa.components.form.Form;

public class WizardPart<E, T extends AbstractFormService<E>> {

	private Form<E> view;
	private String state;
	
	public WizardPart(Form<E> form, String state) {
		this.view = form;
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
	
	public Form<E> getView() {
		return view;
	}
}
