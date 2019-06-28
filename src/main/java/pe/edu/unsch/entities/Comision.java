package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 8:47:33 AM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comision generated by hbm2java
 */
@Entity
@Table(name="Comision"
    ,catalog="categorizacionbd"
)
public class Comision  implements java.io.Serializable {


     private long idcomision;
     private long idjefedepartamento;
     private String nombre;
     private String razon;
     private Date fechaCreacion;

    public Comision() {
    }

    public Comision(long idcomision, long idjefedepartamento, String nombre, String razon, Date fechaCreacion) {
       this.idcomision = idcomision;
       this.idjefedepartamento = idjefedepartamento;
       this.nombre = nombre;
       this.razon = razon;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="idcomision", unique=true, nullable=false)
    public long getIdcomision() {
        return this.idcomision;
    }
    
    public void setIdcomision(long idcomision) {
        this.idcomision = idcomision;
    }

    
    @Column(name="idjefedepartamento", nullable=false)
    public long getIdjefedepartamento() {
        return this.idjefedepartamento;
    }
    
    public void setIdjefedepartamento(long idjefedepartamento) {
        this.idjefedepartamento = idjefedepartamento;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="razon", nullable=false, length=50)
    public String getRazon() {
        return this.razon;
    }
    
    public void setRazon(String razon) {
        this.razon = razon;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", nullable=false, length=19)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }




}


