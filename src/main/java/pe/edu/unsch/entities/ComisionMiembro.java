package pe.edu.unsch.entities;
// Generated Jun 14, 2019, 3:18:26 PM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ComisionMiembro generated by hbm2java
 */
@Entity
@Table(name="ComisionMiembro"
    ,catalog="categorizacionbd"
)
public class ComisionMiembro  implements java.io.Serializable {


     private long idcomisionmiembro;
     private Comision comision;
     private Docente docente;

    public ComisionMiembro() {
    }

    public ComisionMiembro(long idcomisionmiembro, Comision comision, Docente docente) {
       this.idcomisionmiembro = idcomisionmiembro;
       this.comision = comision;
       this.docente = docente;
    }
   
     @Id 

    
    @Column(name="idcomisionmiembro", unique=true, nullable=false)
    public long getIdcomisionmiembro() {
        return this.idcomisionmiembro;
    }
    
    public void setIdcomisionmiembro(long idcomisionmiembro) {
        this.idcomisionmiembro = idcomisionmiembro;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idcomision", nullable=false)
    public Comision getComision() {
        return this.comision;
    }
    
    public void setComision(Comision comision) {
        this.comision = comision;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }




}


