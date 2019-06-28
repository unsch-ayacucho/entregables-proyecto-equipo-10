package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 10:54:14 AM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="Usuario"
    ,catalog="categorizacionbd"
)
public class Usuario  implements java.io.Serializable {


     private long idusuario;
     private String usuario;
     private String password;
     private String cargo;
     private boolean esAdmin;
     private Set<Docente> docentes = new HashSet<Docente>(0);
     private Set<JefeDepartamento> jefeDepartamentos = new HashSet<JefeDepartamento>(0);

    public Usuario() {
    }

	
    public Usuario(long idusuario, String usuario, String password, String cargo, boolean esAdmin) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.password = password;
        this.cargo = cargo;
        this.esAdmin = esAdmin;
    }
    public Usuario(long idusuario, String usuario, String password, String cargo, boolean esAdmin, Set<Docente> docentes, Set<JefeDepartamento> jefeDepartamentos) {
       this.idusuario = idusuario;
       this.usuario = usuario;
       this.password = password;
       this.cargo = cargo;
       this.esAdmin = esAdmin;
       this.docentes = docentes;
       this.jefeDepartamentos = jefeDepartamentos;
    }
   
     @Id 

    
    @Column(name="idusuario", unique=true, nullable=false)
    public long getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    
    @Column(name="usuario", nullable=false, length=50)
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="password", nullable=false, length=50)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="cargo", nullable=false, length=50)
    public String getCargo() {
        return this.cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    
    @Column(name="es_admin", nullable=false)
    public boolean isEsAdmin() {
        return this.esAdmin;
    }
    
    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<Docente> getDocentes() {
        return this.docentes;
    }
    
    public void setDocentes(Set<Docente> docentes) {
        this.docentes = docentes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<JefeDepartamento> getJefeDepartamentos() {
        return this.jefeDepartamentos;
    }
    
    public void setJefeDepartamentos(Set<JefeDepartamento> jefeDepartamentos) {
        this.jefeDepartamentos = jefeDepartamentos;
    }




}


