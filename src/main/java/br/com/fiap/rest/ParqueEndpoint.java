package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.ParqueDao;
import br.com.fiap.model.Parque;

@Path("/parques")
public class ParqueEndpoint {

	private ParqueDao dao = new ParqueDao();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Parque> index() {
		return dao.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Parque parque) {
		if(parque == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.salvar(parque);
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.CREATED).entity(parque).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show(@PathParam("id") Long id) {
		Parque parque = dao.buscar(id);
		if(parque == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.status(Response.Status.OK).entity(parque).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Parque parque) {
		if(dao.buscar(id) == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();			
		}
		
		if(parque == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();			
		}

		parque.setId(id);
		dao.atualizar(parque);
		return Response.status(Response.Status.OK).entity(parque).build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id")Long id) {
		if(dao.buscar(id) == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();			
		}
		dao.deletar(id);
		return Response.status(Response.Status.OK).build();
	}
}