package fpa.components.projeto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import fpa.components.form.AbstractFormService;
import fpa.components.form.FormField;
import fpa.model.Projeto;

@Singleton
public class ProjetoDescricaoForm extends AbstractFormService<Projeto>{

	public ProjetoDescricaoForm() {
	}

	@Override
	protected Projeto newBean() {
		return new Projeto();
	}

	@Override
	protected List<FormField> getFields(ArrayList<FormField> fields) {
		fields.add(createTextField(		"nome", 		"Nome", 		"Digite o nome", true));
		fields.add(createTextArea(		"descricao", 	"Descrição", 	"Digite uma Descrição do Projeto", true));
		fields.add(createMaskedField(	"valorHora", 	"Valor Hora", 	"Digite o valor da Hora","R$ 9?9?99,99", true));
		fields.add(createDateField(		"dataInicial", 	"Data Inicial", "Digite a data Inicial", true));
		fields.add(createDateField(		"dataFinal", 	"Data Final", 	"Digite a Data Final", true));
		return fields;
	}

	@Override
	public String getTitle(Projeto entity) {
		return entity.getId() == null ? "Projeto" : entity.getNome();
	}
}
