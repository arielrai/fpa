package fpa.components.funcao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import fpa.components.form.AbstractFormService;
import fpa.components.form.Form;
import fpa.components.form.FormField;
import fpa.components.form.enum_.FormEnumBean;
import fpa.model.FaixaComplexidade;
import fpa.model.Funcao;
import fpa.model.Projeto;
import fpa.model.Tabela;

@Singleton
public class FuncaoDescricaoForm extends AbstractFormService<Funcao>{

	private EntityManager em;
	
	public FuncaoDescricaoForm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	protected Map<String, Object> params(Funcao funcao) {
		Map<String, Object> params = super.params(funcao);
		Session session = em.unwrap(Session.class);
		List<FaixaComplexidade> list = session.createCriteria(FaixaComplexidade.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		List<FormEnumBean<FaixaComplexidade>> tipos = list.stream().map(e -> new FormEnumBean<>(e.getNome(), e)).collect(Collectors.toList());
		params.put("tipo", tipos);
		
		List<Tabela> tabelas = new ArrayList<Tabela>();
		tabelas.addAll(em.find(Projeto.class, funcao.getProjeto().getId()).getTabelas());
		params.put("tabelas", tabelas);
		
		
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
	
	public Form<Funcao> getForm(Projeto entity) {
		Funcao newBean = newBean();
		newBean.setProjeto(entity);
		return new Form<Funcao>(newBean, getFields(new ArrayList<FormField>()), getTitle(newBean), params(newBean));
	}

}
