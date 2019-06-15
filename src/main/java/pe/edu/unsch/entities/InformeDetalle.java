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
 * InformeDetalle generated by hbm2java
 */
@Entity
@Table(name="InformeDetalle"
    ,catalog="categorizacionbd"
)
public class InformeDetalle  implements java.io.Serializable {


     private long idinformedetalle;
     private Informe informe;
     private TablaEvDetalle tablaEvDetalle;
     private short puntaje;

    public InformeDetalle() {
    }

    public InformeDetalle(long idinformedetalle, Informe informe, TablaEvDetalle tablaEvDetalle, short puntaje) {
       this.idinformedetalle = idinformedetalle;
       this.informe = informe;
       this.tablaEvDetalle = tablaEvDetalle;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idinforme", nullable=false)
    public Informe getInforme() {
        return this.informe;
    }
    
    public void setInforme(Informe informe) {
        this.informe = informe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idtablaevdetalle", nullable=false)
    public TablaEvDetalle getTablaEvDetalle() {
        return this.tablaEvDetalle;
    }
    
    public void setTablaEvDetalle(TablaEvDetalle tablaEvDetalle) {
        this.tablaEvDetalle = tablaEvDetalle;
    }

    
    @Column(name="puntaje", nullable=false)
    public short getPuntaje() {
        return this.puntaje;
    }
    
    public void setPuntaje(short puntaje) {
        this.puntaje = puntaje;
    }




}


