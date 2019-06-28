package pe.edu.unsch.entities;
// Generated Jun 28, 2019, 8:47:33 AM by Hibernate Tools 4.3.2-SNAPSHOT


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * InformeDetalle generated by hbm2java
 */
@Entity
@Table(name="InformeDetalle"
    ,catalog="categorizacionbd"
)
public class InformeDetalle  implements java.io.Serializable {


     private long idinformedetalle;
     private long idinforme;
     private long idtablaevdetalle;
     private short puntaje;

    public InformeDetalle() {
    }

    public InformeDetalle(long idinformedetalle, long idinforme, long idtablaevdetalle, short puntaje) {
       this.idinformedetalle = idinformedetalle;
       this.idinforme = idinforme;
       this.idtablaevdetalle = idtablaevdetalle;
       this.puntaje = puntaje;
    }
   
     @Id 

    
    @Column(name="idinformedetalle", unique=true, nullable=false)
    public long getIdinformedetalle() {
        return this.idinformedetalle;
    }
    
    public void setIdinformedetalle(long idinformedetalle) {
        this.idinformedetalle = idinformedetalle;
    }

    
    @Column(name="idinforme", nullable=false)
    public long getIdinforme() {
        return this.idinforme;
    }
    
    public void setIdinforme(long idinforme) {
        this.idinforme = idinforme;
    }

    
    @Column(name="idtablaevdetalle", nullable=false)
    public long getIdtablaevdetalle() {
        return this.idtablaevdetalle;
    }
    
    public void setIdtablaevdetalle(long idtablaevdetalle) {
        this.idtablaevdetalle = idtablaevdetalle;
    }

    
    @Column(name="puntaje", nullable=false)
    public short getPuntaje() {
        return this.puntaje;
    }
    
    public void setPuntaje(short puntaje) {
        this.puntaje = puntaje;
    }




}


