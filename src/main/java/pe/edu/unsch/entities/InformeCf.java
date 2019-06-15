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
 * InformeCf generated by hbm2java
 */
@Entity
@Table(name="InformeCF"
    ,catalog="categorizacionbd"
)
public class InformeCf  implements java.io.Serializable {


     private long idinformecf;
     private ConsejoFacultad consejoFacultad;
     private Informe informe;
     private boolean aprobado;
     private String detalle;

    public InformeCf() {
    }

    public InformeCf(long idinformecf, ConsejoFacultad consejoFacultad, Informe informe, boolean aprobado, String detalle) {
       this.idinformecf = idinformecf;
       this.consejoFacultad = consejoFacultad;
       this.informe = informe;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idconsejofacultad", nullable=false)
    public ConsejoFacultad getConsejoFacultad() {
        return this.consejoFacultad;
    }
    
    public void setConsejoFacultad(ConsejoFacultad consejoFacultad) {
        this.consejoFacultad = consejoFacultad;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idinforme", nullable=false)
    public Informe getInforme() {
        return this.informe;
    }
    
    public void setInforme(Informe informe) {
        this.informe = informe;
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


