package modules;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author aula
 */
@Entity
@Table(name = "escola", schema = "public")
public class Escola implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String site;
    
    @Column(nullable = false)
    private String endereco;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = true)
    private String telefone;
        
    public Escola() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
     public String getTelefone() {
        return telefone;
    }

    public void seTelefone(String telefone) {
        this.telefone = telefone;
    }
}
