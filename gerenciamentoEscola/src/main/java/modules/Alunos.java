package modules;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "alunos", schema = "public")
public class Alunos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cpf;
    
    @Column(nullable = false)
    private boolean aprovado;
    
    @Column(name = "imagem_url", nullable = true)
    private String imagem;
     
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="disciplinas_id")
    private Disciplinas disciplinas;

    public Alunos() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Disciplinas getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Disciplinas disciplinas) {
        this.disciplinas = disciplinas;
    }

    
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
}
