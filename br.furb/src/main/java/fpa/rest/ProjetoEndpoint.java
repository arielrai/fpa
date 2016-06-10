package fpa.rest;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;
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

import fpa.components.projeto.ProjetoDescricaoForm;
import fpa.components.projeto.ProjetoTable;
import fpa.components.table.TableBean;
import fpa.model.Projeto;
import fpa.model.ProjetoPojo;


/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
@Path("/projetos")
@Stateless
public class ProjetoEndpoint{
	
	@Inject private ProjetoTable projetoTable;
	@Inject private ProjetoDescricaoForm projetoForm;
	
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;

	public Class<Projeto> getClazz() {
		return Projeto.class;
	}
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("table")
	public Response getTable(TableBean<Projeto> entity) {
		return Response.ok(projetoTable.createTable(entity.getPagination().getPage(), 
				entity.getPagination().getCountPerPage(), entity.getSortBy(), entity.getSortOrder(), null)).build();
	}
	
	@GET
	@Produces("application/json")
	@Path("table")
	public Response getTable() {
		return Response.ok(projetoTable.createTable(0, 20, "nome", "desc", null)).build();
	}


	@GET
	@Produces("application/json")
	@Path("form/{id}")
	public Response getForm(@PathParam("id") Long id) {
		return Response.ok(projetoForm.getForm(em.find(Projeto.class, id))).build();
	}

	@GET
	@Produces("application/json")
	@Path("form")
	public Response getForm() {
		return Response.ok(projetoForm.getForm()).build();
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
		
		
		return Response.noContent().build();
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
