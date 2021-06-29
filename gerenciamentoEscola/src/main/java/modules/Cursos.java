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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cursos", schema = "public")
public class Cursos implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = true)
    private String descricao;
     
    @Column(nullable = false, name="carga_horaria")
    private int cargaHoraria;
    
    @Column(nullable = false, name="quantidade_alunos")
    private int quantidadeAlunos;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="escola_id")
    private Escola escola;
    
    public Cursos() {
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    } 
    
       public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }
}
