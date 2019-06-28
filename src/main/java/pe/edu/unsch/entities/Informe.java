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
 * Informe generated by hbm2java
 */
@Entity
@Table(name="Informe"
    ,catalog="categorizacionbd"
)
public class Informe  implements java.io.Serializable {


     private long idinforme;
     private long idpromocion;
     private long idcomision;
     private long idtablaev;
     private String nombre;
     private Date fechaCreacion;

    public Informe() {
    }

    public Informe(long idinforme, long idpromocion, long idcomision, long idtablaev, String nombre, Date fechaCreacion) {
       this.idinforme = idinforme;
       this.idpromocion = idpromocion;
       this.idcomision = idcomision;
       this.idtablaev = idtablaev;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
    }
   
     @Id 

    
    @Column(name="idinforme", unique=true, nullable=false)
    public long getIdinforme() {
        return this.idinforme;
    }
    
    public void setIdinforme(long idinforme) {
        this.idinforme = idinforme;
    }

    
    @Column(name="idpromocion", nullable=false)
    public long getIdpromocion() {
        return this.idpromocion;
    }
    
    public void setIdpromocion(long idpromocion) {
        this.idpromocion = idpromocion;
    }

    
    @Column(name="idcomision", nullable=false)
    public long getIdcomision() {
        return this.idcomision;
    }
    
    public void setIdcomision(long idcomision) {
        this.idcomision = idcomision;
    }

    
    @Column(name="idtablaev", nullable=false)
    public long getIdtablaev() {
        return this.idtablaev;
    }
    
    public void setIdtablaev(long idtablaev) {
        this.idtablaev = idtablaev;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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


