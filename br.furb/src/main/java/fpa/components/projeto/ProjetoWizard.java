package fpa.components.projeto;

import javax.inject.Inject;
import javax.inject.Singleton;

import fpa.components.wizard.AbstractWizardService;
import fpa.components.wizard.WizardPart;
import fpa.model.Projeto;

@Singleton
public class ProjetoWizard extends AbstractWizardService {

	@Inject private ProjetoDescricaoForm projetoForm;
	
	@Override
	protected void init() {
		addPart(new WizardPart<Projeto, ProjetoDescricaoForm>(projetoForm.getForm(), "descricao"));
	}

	@Override
	protected void init(Long id) {
		//TODO buscar bean
		addPart(new WizardPart<Projeto, ProjetoDescricaoForm>(projetoForm.getForm(null), "descricao"));
	}

	@Override
	protected String getTitle() {
		return "Projeto";
	}
}
