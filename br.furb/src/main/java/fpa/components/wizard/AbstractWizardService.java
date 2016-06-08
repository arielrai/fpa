package fpa.components.wizard;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWizardService {

	@SuppressWarnings("rawtypes")
	private List<WizardPart> parts = new ArrayList<>();
	
	protected abstract void init();

	protected abstract void init(Long id);
	
	@SuppressWarnings("rawtypes")
	public void addPart(WizardPart part){
		parts.add(part);
	}
	
	public Wizard getParts() {
		init();
		return new Wizard(getTitle(), parts);
	}
	
	protected abstract String getTitle();

	public Wizard getParts(Long id) {
		init(id);
		return new Wizard(getTitle(), parts);
	}
}
