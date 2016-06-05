package fpa.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import fpa.components.projeto.ProjetoForm;
import fpa.components.projeto.ProjetoTable;
import fpa.components.table.TableBean;
import fpa.model.Projeto;

/**
 * 
 * @author Ariel Rai Rodrigues (ariel.rai.rodrigues@gmail.com)
 *
 */
@Stateless
@Path("/projetos")
public class ProjetoEndpoint 
			extends AbstractEndPoint<Projeto> 
//			implements TableEndpoint<Projeto>,FormEndpoint<Projeto>
{
	@Inject private ProjetoTable projetoTable;
	@Inject private ProjetoForm projetoForm;
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;

	@Override
	public Class<Projeto> getClazz() {
		return Projeto.class;
	}
	
	
	@Override
	public Response getTable(TableBean<Projeto> entity) {
		return Response.ok(projetoTable.createTable(entity.getPagination().getPage(), 
				entity.getPagination().getCountPerPage(), entity.getSortBy(), entity.getSortOrder(), null)).build();
	}
	
	@Override
	public Response getTable() {
		return Response.ok(projetoTable.createTable(0, 20, "nome", "desc", null)).build();
	}


	@Override
	public Response getForm(Long id) {
		return Response.ok(projetoForm.getForm(em.find(Projeto.class, id))).build();
	}


	@Override
	public Response getForm() {
		return Response.ok(projetoForm.getForm()).build();
	}
}
