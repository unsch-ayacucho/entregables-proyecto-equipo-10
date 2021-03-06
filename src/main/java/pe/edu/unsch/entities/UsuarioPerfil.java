package pe.edu.unsch.entities;
// Generated Jul 21, 2019, 9:48:04 AM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UsuarioPerfil generated by hbm2java
 */
@Entity
@Table(name="UsuarioPerfil"
    ,catalog="categorizacionbd"
)
public class UsuarioPerfil  implements java.io.Serializable {


     private Long idusuarioperfil;
     private Perfil perfil;
     private Usuario usuario;

    public UsuarioPerfil() {
    }

    public UsuarioPerfil(Perfil perfil, Usuario usuario) {
       this.perfil = perfil;
       this.usuario = usuario;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idusuarioperfil", unique=true, nullable=false)
    public Long getIdusuarioperfil() {
        return this.idusuarioperfil;
    }
    
    public void setIdusuarioperfil(Long idusuarioperfil) {
        this.idusuarioperfil = idusuarioperfil;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idperfil", nullable=false)
    public Perfil getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




}


