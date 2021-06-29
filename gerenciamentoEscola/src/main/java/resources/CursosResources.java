package resources;

import modules.Cursos;
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

@Stateless
@Path("cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursosResources {
    
    @PersistenceContext(unitName = "gerenciamentoEscolaPU")
    EntityManager entityManager;
    
    @GET
    public List<Cursos> getCursos() {
        return entityManager
                .createQuery("SELECT c FROM Cursos c", Cursos.class)
                .getResultList();
    }

    @POST
    public Response addCurso(Cursos cursos) {
        entityManager.persist(cursos);
        return Response
                .status(Response.Status.CREATED)
                .entity(cursos)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Cursos getCurso(@PathParam("id") Integer id) {
        return entityManager.find(Cursos.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeCurso(@PathParam("id") Integer id) {
        Cursos cursoEncontrado = entityManager.find(Cursos.class, id);
        entityManager.remove(cursoEncontrado);
    }

    @PUT
    @Path("{id}")
    public Cursos updateCurso(@PathParam("id") Integer id, Cursos curso) {
        curso.setId(id);
        entityManager.merge(curso);
        return curso;
    }
}
