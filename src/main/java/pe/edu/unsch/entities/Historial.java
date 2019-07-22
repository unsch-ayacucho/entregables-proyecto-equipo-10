package pe.edu.unsch.entities;
// Generated Jul 22, 2019, 1:14:44 PM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Historial generated by hbm2java
 */
@Entity
@Table(name="Historial"
    ,catalog="categorizacionbd"
)
public class Historial  implements java.io.Serializable {


     private Long idhistorial;
     private Usuario usuario;
     private String detalle;
     private Date fecha;

    public Historial() {
    }

    public Historial(Usuario usuario, String detalle, Date fecha) {
       this.usuario = usuario;
       this.detalle = detalle;
       this.fecha = fecha;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idhistorial", unique=true, nullable=false)
    public Long getIdhistorial() {
        return this.idhistorial;
    }
    
    public void setIdhistorial(Long idhistorial) {
        this.idhistorial = idhistorial;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="detalle", nullable=false, length=150)
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", nullable=false, length=19)
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }




}


