package pe.edu.unsch.entities;
// Generated Jul 21, 2019, 9:48:04 AM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ConsejoFacultad generated by hbm2java
 */
@Entity
@Table(name="ConsejoFacultad"
    ,catalog="categorizacionbd"
)
public class ConsejoFacultad  implements java.io.Serializable {


     private long idconsejofacultad;
     private String nombre;
     private Date fechaCreacion;
     private Set<InformeCf> informeCfs = new HashSet<InformeCf>(0);

    public ConsejoFacultad() {
    }

	
    public ConsejoFacultad(long idconsejofacultad, String nombre, Date fechaCreacion) {
        this.idconsejofacultad = idconsejofacultad;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }
    public ConsejoFacultad(long idconsejofacultad, String nombre, Date fechaCreacion, Set<InformeCf> informeCfs) {
       this.idconsejofacultad = idconsejofacultad;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.informeCfs = informeCfs;
    }
   
     @Id 

    
    @Column(name="idconsejofacultad", unique=true, nullable=false)
    public long getIdconsejofacultad() {
        return this.idconsejofacultad;
    }
    
    public void setIdconsejofacultad(long idconsejofacultad) {
        this.idconsejofacultad = idconsejofacultad;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="consejoFacultad")
    public Set<InformeCf> getInformeCfs() {
        return this.informeCfs;
    }
    
    public void setInformeCfs(Set<InformeCf> informeCfs) {
        this.informeCfs = informeCfs;
    }




}


