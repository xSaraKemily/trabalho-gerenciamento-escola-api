package resources;

import modules.Disciplinas;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("disciplinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisciplinasResources {
    
    @PersistenceContext(unitName = "gerenciamentoEscolaPU")
    EntityManager entityManager;
    
    @GET
    public List<Disciplinas> getDisciplina() {
        return entityManager
                .createQuery("SELECT c FROM Disciplinas c", Disciplinas.class)
                .getResultList();
    }
    
    @POST
    public Response addDisciplina(Disciplinas disciplinas) {
      
        // valida a carga horaria da disciplina em relação ao curso
        if(disciplinas.getCargaHoraria() > disciplinas.getCursos().getCargaHoraria()) {
              return Response.serverError().entity("A carga horária da disciplina deve ser menor que a do curso.").build();
        }
        
        entityManager.persist(disciplinas);

        return Response
            .status(Response.Status.CREATED)
            .entity(disciplinas)
            .build();
    }
    
    @GET
    @Path("{id}")
    public Disciplinas getDisciplina(@PathParam("id") Integer id) {
        return entityManager.find(Disciplinas.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeDisciplina(@PathParam("id") Integer id) {
        Disciplinas disciplinaEncontrado = entityManager.find(Disciplinas.class, id);
        entityManager.remove(disciplinaEncontrado);
    }

    @PUT
    @Path("{id}")
    public Response updateDisciplina(@PathParam("id") Integer id, Disciplinas disciplina) {
         // valida a carga horaria da disciplina em relação ao curso
        if(disciplina.getCargaHoraria() > disciplina.getCursos().getCargaHoraria()) {
              return Response.serverError().entity("A carga horária da disciplina deve ser menor que a do curso.").build();
        }
        
        disciplina.setId(id);
        entityManager.merge(disciplina);
       
        return Response
            .status(Response.Status.CREATED)
            .entity(disciplina)
            .build();
    }
}
