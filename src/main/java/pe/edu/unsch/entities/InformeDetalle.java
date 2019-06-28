package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 3:58:00 PM by Hibernate Tools 4.3.2-SNAPSHOT


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
 * InformeDetalle generated by hbm2java
 */
@Entity
@Table(name="InformeDetalle"
    ,catalog="categorizacionbd"
)
public class InformeDetalle  implements java.io.Serializable {


     private Long idinformedetalle;
     private Informe informe;
     private TablaEvDetalle tablaEvDetalle;
     private short puntaje;

    public InformeDetalle() {
    }

    public InformeDetalle(Informe informe, TablaEvDetalle tablaEvDetalle, short puntaje) {
       this.informe = informe;
       this.tablaEvDetalle = tablaEvDetalle;
       this.puntaje = puntaje;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idinformedetalle", unique=true, nullable=false)
    public Long getIdinformedetalle() {
        return this.idinformedetalle;
    }
    
    public void setIdinformedetalle(Long idinformedetalle) {
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


