package fpa.components.wizard;

import java.util.ArrayList;
import java.util.List;

public class Wizard {

	private String title;
	@SuppressWarnings("rawtypes")
	private List<WizardPart> parts = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	public Wizard(String title, List<WizardPart> parts) {
		super();
		this.title = title;
		this.parts = parts;
	}
	
	public String getTitle() {
		return title;
	}
	
	@SuppressWarnings("rawtypes")
	public List<WizardPart> getParts() {
		return parts;
	}
	
}
