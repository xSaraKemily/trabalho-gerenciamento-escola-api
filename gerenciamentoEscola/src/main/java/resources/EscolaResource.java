/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import modules.Escola;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aula
 */
@Stateless
@Path("escola")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EscolaResource {
    
    @PersistenceContext(unitName = "gerenciamentoEscolaPU")
    EntityManager entityManager;
    
    @GET
    public List<Escola> getEscolas() {
        return entityManager
                .createQuery("SELECT a FROM Escola a", Escola.class)
                .getResultList();
    }
    
    @POST
    public Response addEscola(Escola escola) {
        entityManager.persist(escola);
        return Response
                .status(Response.Status.CREATED)
                .entity(escola)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Escola getEscola(@PathParam("id") Integer id) {
        return entityManager.find(Escola.class, id);
    }
        
    @DELETE
    @Path("{id}")
    public void removeEscola(@PathParam("id") Integer id) {
        Escola escola = entityManager.find(Escola.class, id);
        entityManager.remove(escola);
    }
    
    @PUT
    @Path("{id}")
    public Escola updateEscola(@PathParam("id") Integer id, Escola a) {
        a.setId(id);
        entityManager.merge(a);
        return a;
    }
  
} 
