package fpa.components.projeto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import fpa.components.form.AbstractFormService;
import fpa.components.form.FormField;
import fpa.model.Projeto;

@Singleton
public class ProjetoForm extends AbstractFormService<Projeto>{

	@Override
	protected Projeto newBean() {
		return new Projeto();
	}

	@Override
	protected List<FormField> getFields(ArrayList<FormField> fields) {
		fields.add(createTextField("nome", "Nome", "Digite o nome", true));
		return fields;
	}

}
