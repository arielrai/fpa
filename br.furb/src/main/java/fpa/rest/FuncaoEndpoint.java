package fpa.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import fpa.components.form.Form;
import fpa.components.funcao.FuncaoDescricaoForm;
import fpa.components.funcao.FuncaoTable;
import fpa.components.table.TableSearchProperty;
import fpa.model.Funcao;
import fpa.model.Projeto;
import fpa.model.ProjetoPojo;


/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
@Path("/funcoes")
@Stateless
public class FuncaoEndpoint{
	
	private FuncaoTable funcaoTable;
	private FuncaoDescricaoForm form = new FuncaoDescricaoForm();
	
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;

	public Class<Projeto> getClazz() {
		return Projeto.class;
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
		return Response.ok(form.getForm(em.find(Funcao.class, id))).build();
	}

	@GET
	@Produces("application/json")
	@Path("form/{projetoId}")
	public Response getForm(@PathParam("projetoId") Long projetoId) {
		Projeto projeto = em.find(Projeto.class, projetoId);
		Form<Funcao> form2 = form.getForm();
		form2.getPojo().setProjeto(projeto);
		return Response.ok(form2).build();
	}
	
	@PUT
	@Path("{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, ProjetoPojo pojo) {
		if (pojo == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(pojo.getId())) {
			return Response.status(Status.CONFLICT).entity(pojo).build();
		}
		Projeto find = em.find(getClazz(), id);
		if (find == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		convertPojo(find, pojo);
		
		try {
			find = em.merge(find);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(find).build();
		}
		
		
		find.setMessage("Projeto atualizado com sucesso!");
		return Response.ok(find).build();
	}
	
	@POST
	@Consumes("application/json")
	public Response create(ProjetoPojo pojo) {
		Projeto projeto = new Projeto();
		convertPojo(projeto, pojo);
		em.merge(projeto);
		projeto.setMessage("Projeto cadastrado com sucesso!");
		return Response.ok(projeto).build();
	}

	private void convertPojo(Projeto projeto, ProjetoPojo pojo) {
		projeto.setNome(pojo.getNome());
		projeto.setComplexidades(pojo.getComplexidades());
		String valorFormatado = pojo.getValorFormatado().replaceAll("\\.", "");
		projeto.setValorHora(new BigDecimal(new Double(
				new StringBuilder(valorFormatado).insert(valorFormatado.length()-2, ".").toString())));
		projeto.setDataInicial(pojo.getDataInicial());
		projeto.setDataFinal(pojo.getDataFinal());
	}
}
