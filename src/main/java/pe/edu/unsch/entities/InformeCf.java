package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 8:47:33 AM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InformeCf generated by hbm2java
 */
@Entity
@Table(name="InformeCF"
    ,catalog="categorizacionbd"
)
public class InformeCf  implements java.io.Serializable {


     private long idinformecf;
     private long idconsejofacultad;
     private long idinforme;
     private boolean aprobado;
     private String detalle;

    public InformeCf() {
    }

    public InformeCf(long idinformecf, long idconsejofacultad, long idinforme, boolean aprobado, String detalle) {
       this.idinformecf = idinformecf;
       this.idconsejofacultad = idconsejofacultad;
       this.idinforme = idinforme;
       this.aprobado = aprobado;
       this.detalle = detalle;
    }
   
     @Id 

    
    @Column(name="idinformecf", unique=true, nullable=false)
    public long getIdinformecf() {
        return this.idinformecf;
    }
    
    public void setIdinformecf(long idinformecf) {
        this.idinformecf = idinformecf;
    }

    
    @Column(name="idconsejofacultad", nullable=false)
    public long getIdconsejofacultad() {
        return this.idconsejofacultad;
    }
    
    public void setIdconsejofacultad(long idconsejofacultad) {
        this.idconsejofacultad = idconsejofacultad;
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

    
    @Column(name="detalle", nullable=false, length=65535)
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }




}


