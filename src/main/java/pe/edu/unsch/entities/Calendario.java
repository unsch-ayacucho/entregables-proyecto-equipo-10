package pe.edu.unsch.entities;
// Generated Jun 28, 2019, 8:47:33 AM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Calendario generated by hbm2java
 */
@Entity
@Table(name="Calendario"
    ,catalog="categorizacionbd"
)
public class Calendario  implements java.io.Serializable {


     private long idcalendario;
     private String proceso;

    public Calendario() {
    }

	
    public Calendario(long idcalendario) {
        this.idcalendario = idcalendario;
    }
    public Calendario(long idcalendario, String proceso) {
       this.idcalendario = idcalendario;
       this.proceso = proceso;
    }
   
     @Id 

    
    @Column(name="idcalendario", unique=true, nullable=false)
    public long getIdcalendario() {
        return this.idcalendario;
    }
    
    public void setIdcalendario(long idcalendario) {
        this.idcalendario = idcalendario;
    }

    
    @Column(name="proceso", length=50)
    public String getProceso() {
        return this.proceso;
    }
    
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }




}


