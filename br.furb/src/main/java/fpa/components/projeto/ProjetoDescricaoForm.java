package fpa.components.projeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import fpa.components.form.AbstractFormService;
import fpa.components.form.FormField;
import fpa.components.form.enum_.FormEnumBean;
import fpa.model.Projeto;
import fpa.model.ProjetoComplexidade;
import fpa.model.domain.FatorAjuste;
import fpa.model.domain.NivelInfluencia;

@Singleton
public class ProjetoDescricaoForm extends AbstractFormService<Projeto>{

	public ProjetoDescricaoForm() {
	}
	
	@Override
	protected Map<String, Object> params() {
		Map<String, Object> params = super.params();
		
		params.put("niveis", java.util.stream.Stream.of(NivelInfluencia.values()).map(e ->new FormEnumBean<NivelInfluencia>(e.getDescricao(), e)).collect(Collectors.toList()));
		params.put("fatores", java.util.stream.Stream.of(FatorAjuste.values()).map(e ->new FormEnumBean<FatorAjuste>(e.getDescricao(), e)).collect(Collectors.toList()));
		return params;
	}

	@Override
	protected Projeto newBean() {
		Projeto projeto = new Projeto();
		List<ProjetoComplexidade> complexidades = new ArrayList<ProjetoComplexidade>();
		FatorAjuste[] values = FatorAjuste.values();
		for (FatorAjuste fatorAjuste : values) {
			ProjetoComplexidade projetoComplexidade = new ProjetoComplexidade();
			projetoComplexidade.setFatorAjuste(fatorAjuste);
			projetoComplexidade.setNivelInfluencia(NivelInfluencia.MINIMA);
			complexidades.add(projetoComplexidade);
		}
		projeto.setComplexidades(complexidades);
		return projeto;
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
