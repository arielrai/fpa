package fpa.rest;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import fpa.components.table.TableBean;
import fpa.model.PersistentBean;

public abstract class AbstractEndPoint<T extends PersistentBean> {
	@PersistenceContext(unitName = "primary")
	protected EntityManager em;
	
	public abstract Class<T> getClazz();
	
	@POST
	@Consumes("application/json")
	public Response create(T entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(this.getClass())
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		T entity = em.find(getClazz(), id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<T> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Projeto p WHERE p.id = :entityId ORDER BY p.id",
						getClazz());
		findByIdQuery.setParameter("entityId", id);
		T entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, T entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(getClazz(), id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
	

	//TODO seperar em mais serviços abstratos
	@POST
	@Produces("application/json")
	@Path("form/{id:[0-9][0-9]*}")
	public Response getForm(@PathParam("id")Long id){
		return Response.serverError().build();
	};
	
	@GET
	@Produces("application/json")
	@Path("form")
	public Response getForm(){
		return Response.serverError().build();
	};

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("table")
	public Response getTable(TableBean<T> entity){
		return Response.serverError().build();
	};
	
	@GET
	@Produces("application/json")
	@Path("table")
	public Response getTable(){
		return Response.serverError().build();
	};
	
	@POST
	@Produces("application/json")
	@Path("wizard/{id:[0-9][0-9]*}")
	public Response getWizard(@PathParam("id")Long id){
		return Response.serverError().build();
	};
	
	@GET
	@Produces("application/json")
	@Path("wizard")
	public Response getWizard(){
		return Response.serverError().build();
	};
	
}
