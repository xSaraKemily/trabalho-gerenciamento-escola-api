package resources;

import modules.Professores;
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
@Path("professores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfessoresResources {
    
    @PersistenceContext(unitName = "gerenciamentoEscolaPU")
    EntityManager entityManager;
    
    @GET
    public List<Professores> getProfessores() {
        return entityManager
                .createQuery("SELECT c FROM Professores c", Professores.class)
                .getResultList();
    }
    
    @POST
    public Response addProfessor(Professores professores) {
        
        if(!this.validate(professores)) {
              return Response.serverError().entity("Esta disciplina já possui um professor cadastrado.").build();
        }
        
        if (professores.getImagem() == "") {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(professores)
                    .build();
        } else{
            entityManager.persist(professores);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(professores)
                    .build();
        }
    }
    
    @GET
    @Path("{id}")
    public Professores getProfessor(@PathParam("id") Integer id) {
        return entityManager.find(Professores.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeProfessor(@PathParam("id") Integer id) {
        Professores professorEncontrado = entityManager.find(Professores.class, id);
        entityManager.remove(professorEncontrado);
    }

    @PUT
    @Path("{id}")
    public Response updateProfessor(@PathParam("id") Integer id, Professores professor) {
         if(!this.validate(professor)) {
            return Response.serverError().entity("Esta disciplina já possui um professor cadastrado.").build();
        }
         
        professor.setId(id);
        entityManager.merge(professor);
        return Response
                .status(Response.Status.CREATED)
                .entity(professor)
                .build();
    }
    
    private boolean validate(Professores professores)
    {
       //nao podem existir mais de um professor para uma mesma disciplina
       List<Professores> professoresList  = entityManager .createQuery("SELECT c FROM Professores c WHERE disciplinas_id = "+ professores.getDisciplinas().getId() 
               + " AND id <> "+ professores.getId(), Professores.class).getResultList();
       
       if(!professoresList.isEmpty()) {
           return false;
       }
       return true;
    }
}
