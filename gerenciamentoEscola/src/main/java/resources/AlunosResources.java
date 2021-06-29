package resources;

import java.io.UnsupportedEncodingException;
import modules.Alunos;
import modules.Disciplinas;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Path("alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunosResources extends javax.servlet.http.HttpServlet {
    @PersistenceContext(unitName = "gerenciamentoEscolaPU")
    EntityManager entityManager;
    
    @GET
//    public List<Alunos> getAlunos(HttpServletRequest request,;
//         HttpServletResponse response) {
//        String query = request.getParameter("query");
    public List<Alunos> getAlunos() {
//        if(query != "all" && query != null) {
//             return entityManager
//                .createQuery("SELECT c FROM Alunos c WHERE c.nome LIKE '%"+ query +"%'", Alunos.class)
//                .getResultList();
//        };
        
        return entityManager
                .createQuery("SELECT c FROM Alunos c", Alunos.class)
                .getResultList();
    }
    
    @POST
    public Response addAluno(Alunos alunos) {
        
       if(!this.validate(alunos)){
            Response.serverError().entity("Esta disciplina está cheia.").build();
       }
        
        entityManager.persist(alunos);
        
        return Response
                .status(Response.Status.CREATED)
                .entity(alunos)
                .build();
    }
    
    @GET
    @Path("{id}")
    public Alunos getAluno(@PathParam("id") Integer id) {
        return entityManager.find(Alunos.class, id);
    }

    @DELETE
    @Path("{id}")
    public void removeAluno(@PathParam("id") Integer id) {
        Alunos alunoEncontrado = entityManager.find(Alunos.class, id);
        entityManager.remove(alunoEncontrado);
    }

    @PUT
    @Path("{id}")
    public Response updateAluno(@PathParam("id") Integer id, Alunos aluno) {
        if(!this.validate(aluno)){
           Response.serverError().entity("Esta disciplina está cheia.").build();
       }
             
        aluno.setId(id);
        entityManager.merge(aluno);
        return Response
                .status(Response.Status.CREATED)
                .entity(aluno)
                .build();
    }

    private void build() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean validate(Alunos alunos)
    {
        // nao salva alunos se o curso estiver cheio
         Disciplinas disciplina = entityManager.find(Disciplinas.class, alunos.getDisciplinas().getId());        
        List<Alunos> alunosList = entityManager.createQuery("SELECT a FROM Alunos a WHERE a.disciplinas = " + disciplina.getId(), Alunos.class).getResultList();     
        
        if(disciplina.getCursos().getQuantidadeAlunos() == alunosList.toArray().length) {
             return false;
        }
        
        return true;
    }
}
