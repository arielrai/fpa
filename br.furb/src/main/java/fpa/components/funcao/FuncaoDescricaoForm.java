package fpa.components.funcao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fpa.components.form.AbstractFormService;
import fpa.components.form.FormField;
import fpa.components.form.enum_.FormEnumBean;
import fpa.model.Funcao;
import fpa.model.domain.FatorAjuste;
import fpa.model.domain.NivelInfluencia;

public class FuncaoDescricaoForm extends AbstractFormService<Funcao>{

	
	@Override
	protected Map<String, Object> params() {
		Map<String, Object> params = super.params();
		params.put("niveis", java.util.stream.Stream.of(NivelInfluencia.values()).map(e ->new FormEnumBean<NivelInfluencia>(e.getDescricao(), e)).collect(Collectors.toList()));
		params.put("fatores", java.util.stream.Stream.of(FatorAjuste.values()).map(e ->new FormEnumBean<FatorAjuste>(e.getDescricao(), e)).collect(Collectors.toList()));
		return params;
	}
	
	@Override
	protected Funcao newBean() {
		return new Funcao();
	}

	@Override
	protected List<FormField> getFields(ArrayList<FormField> fields) {
		fields.add(createTextField(		"nome", 		"Nome", 		"Digite o nome", true));
		fields.add(createTextArea(		"descricao", 	"Descrição", 	"Digite uma Descrição do Projeto", true));
		return fields;
	}

	@Override
	public String getTitle(Funcao entity) {
		return entity.getId() == null ? "Funcao" : entity.getNome();
	}

}
