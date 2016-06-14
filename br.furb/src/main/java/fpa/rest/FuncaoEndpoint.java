package fpa.rest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fpa.components.form.Form;
import fpa.components.funcao.FuncaoDescricaoForm;
import fpa.components.funcao.FuncaoTable;
import fpa.components.table.TableSearchProperty;
import fpa.core.FPACalculator;
import fpa.model.Campo;
import fpa.model.Contribuicao;
import fpa.model.FaixaComplexidade;
import fpa.model.Funcao;
import fpa.model.FuncaoPojo;
import fpa.model.FuncaoTabela;
import fpa.model.Projeto;
import fpa.model.Tabela;


/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
@Path("/funcoes")
@Stateless
public class FuncaoEndpoint{
	
	private FuncaoTable funcaoTable;
	private FuncaoDescricaoForm descricaoForm;
	
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;
	private RestResponse restResponse;

	public Class<Projeto> getClazz() {
		return Projeto.class;
	}
	
	public FuncaoDescricaoForm getdescricaoForm() {
		if (descricaoForm == null) {
			descricaoForm = new FuncaoDescricaoForm(em);
		}
		return descricaoForm;
	}
	
	public FuncaoTable getFuncaoTable() {
		if (funcaoTable == null) {
			funcaoTable = new FuncaoTable(em);
		}
		return funcaoTable;
	}
	
	@GET
	@Produces("application/json")
	
	@Path("table/{id}")
	public Response getTable(@PathParam("id")Long id) {
		List<TableSearchProperty> searchProp = new ArrayList<TableSearchProperty>();
		TableSearchProperty tableSearchProperty = new TableSearchProperty();
		tableSearchProperty.setProperty("projetoId");
		tableSearchProperty.setValue(id.toString());
		searchProp.add(tableSearchProperty);
		return Response.ok(getFuncaoTable().createTable(0, 20, "nome", "desc", searchProp)).build();
	}


	@GET
	@Produces("application/json")
	@Path("form/{id}/{projetoId}")
	public Response getForm(@PathParam("id") Long id, @PathParam("projetoId") Long projetoId) {
		Projeto projeto = em.find(Projeto.class, projetoId);
		Hibernate.initialize(projeto.getComplexidades());
		Hibernate.initialize(projeto.getFuncoes());
		Hibernate.initialize(projeto.getTabelas());
		Funcao find = em.find(Funcao.class, id);
		find.setProjeto(projeto);
		
		Hibernate.initialize(find.getComplexidade());
		return Response.ok(getdescricaoForm().getForm(find)).build();
	}

	@GET
	@Produces("application/json")
	@Path("form/{projetoId}")
	public Response getForm(@PathParam("projetoId") Long projetoId) {
		Projeto projeto = em.find(Projeto.class, projetoId);
		Hibernate.initialize(projeto.getComplexidades());
		Hibernate.initialize(projeto.getFuncoes());
		Hibernate.initialize(projeto.getTabelas());
		
		Form<Funcao> form2 = getdescricaoForm().getForm(projeto);
		return Response.ok(form2).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response save(FuncaoPojo pojo) {
		Long id = pojo.getFuncao().getProjeto().getId();
		pojo.getFuncao().setComplexidade(em.find(FaixaComplexidade.class, pojo.getComplexidadeId()));
		Projeto projeto = em.find(Projeto.class, id);
		pojo.getFuncao().setProjeto(projeto);
		ArrayList<Tabela> tabealasSave = new ArrayList<Tabela>();
		if (pojo.getFuncao().getId() == null) {
			List<Tabela> tabelas = pojo.getTabelas();
			List<FuncaoTabela> funcoesTabela = new ArrayList<FuncaoTabela>();
			for (Tabela tabela : tabelas) {
				Tabela tabela2;
				if (tabela.getId() != null) {
					tabela2 = em.find(Tabela.class, tabela.getId());
					tabealasSave.add(tabela2);
					for (Campo campo : tabela.getCampos()) {
						if (campo.getId() == null) {
							tabela2.getCampos().add(campo);
						}
						if (campo.isSelecionado()) {
							for (Campo cmp : tabela2.getCampos()) {
								cmp.setSelecionado(true);
							}
						}
					}
				}else{
					tabela2 = tabela;
					tabealasSave.add(tabela);
				}
				if (tabela2.getCampos().stream().anyMatch(c -> c.isSelecionado())) {
					FuncaoTabela funcaoTabela = new FuncaoTabela();
					funcaoTabela.setFuncao(pojo.getFuncao());
					funcaoTabela.setTabela(tabela);
					funcaoTabela.setCampos(tabela2.getCampos().stream().filter(c->c.isSelecionado()).collect(Collectors.toList()));
					funcoesTabela.add(funcaoTabela);
				}
			}
			projeto.getFuncoes().add(pojo.getFuncao());
			for (Tabela tab : tabealasSave) {
				if (tab.getId() == null) {
					projeto.getTabelas().add(tab);
				}
			}
			em.persist(projeto);
			for (FuncaoTabela funcaoTabela : funcoesTabela) {
				funcaoTabela.setTabela(em.find(Tabela.class, funcaoTabela.getTabela().getId()));
				funcaoTabela.setFuncao(em.find(Funcao.class, funcaoTabela.getFuncao().getId()));
				em.persist(funcaoTabela);
			}
			Contribuicao contribuicao = (Contribuicao)em.unwrap(Session.class).createCriteria(Contribuicao.class).add(Restrictions.eq("tipo", pojo.getFuncao().getComplexidade().getNome())).uniqueResult();
			BigDecimal calculaValorFuncao = FPACalculator.calculaValorFuncao(pojo.getFuncao(), pojo.getFuncao().getComplexidade(), 
					contribuicao, funcoesTabela.size(), 
					funcoesTabela.stream().mapToInt(f -> f.getCampos().size()).sum());
			
			Funcao funcao = pojo.getFuncao();
			funcao.setValorfuncao(calculaValorFuncao);
			em.persist(funcao);
			
			restResponse = new RestResponse();
			restResponse.setMessage("Função cadastrada com sucesso");
			restResponse.setEntity(NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(calculaValorFuncao));
			
			return Response.ok().build();
		}else{
			return Response.ok("Função atualizada com sucesso").build();
		}
	}
	
}
