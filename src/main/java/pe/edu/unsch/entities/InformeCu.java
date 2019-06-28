package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 8:47:33 AM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InformeCu generated by hbm2java
 */
@Entity
@Table(name="InformeCU"
    ,catalog="categorizacionbd"
)
public class InformeCu  implements java.io.Serializable {


     private long idinformecu;
     private long idinforme;
     private boolean aprobado;
     private long idconsejouniversitario;
     private String detalle;

    public InformeCu() {
    }

    public InformeCu(long idinformecu, long idinforme, boolean aprobado, long idconsejouniversitario, String detalle) {
       this.idinformecu = idinformecu;
       this.idinforme = idinforme;
       this.aprobado = aprobado;
       this.idconsejouniversitario = idconsejouniversitario;
       this.detalle = detalle;
    }
   
     @Id 

    
    @Column(name="idinformecu", unique=true, nullable=false)
    public long getIdinformecu() {
        return this.idinformecu;
    }
    
    public void setIdinformecu(long idinformecu) {
        this.idinformecu = idinformecu;
    }

    
    @Column(name="idinforme", nullable=false)
    public long getIdinforme() {
        return this.idinforme;
    }
    
    public void setIdinforme(long idinforme) {
        this.idinforme = idinforme;
    }

    
    @Column(name="aprobado", nullable=false)
    public boolean isAprobado() {
        return this.aprobado;
    }
    
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    
    @Column(name="idconsejouniversitario", nullable=false)
    public long getIdconsejouniversitario() {
        return this.idconsejouniversitario;
    }
    
    public void setIdconsejouniversitario(long idconsejouniversitario) {
        this.idconsejouniversitario = idconsejouniversitario;
    }

    
    @Column(name="detalle", nullable=false, length=65535)
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }




}


