package pe.edu.unsch.hibernate;
// Generated Jun 28, 2019, 10:54:14 AM by Hibernate Tools 4.3.2-SNAPSHOT


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
 * ConsejoUniversitario generated by hbm2java
 */
@Entity
@Table(name="ConsejoUniversitario"
    ,catalog="categorizacionbd"
)
public class ConsejoUniversitario  implements java.io.Serializable {


     private long idconsejouniversitario;
     private String nombre;
     private Date fechaCreacion;
     private Set<InformeCu> informeCus = new HashSet<InformeCu>(0);
     private Set<Resolucion> resolucions = new HashSet<Resolucion>(0);

    public ConsejoUniversitario() {
    }

	
    public ConsejoUniversitario(long idconsejouniversitario, String nombre, Date fechaCreacion) {
        this.idconsejouniversitario = idconsejouniversitario;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
    }
    public ConsejoUniversitario(long idconsejouniversitario, String nombre, Date fechaCreacion, Set<InformeCu> informeCus, Set<Resolucion> resolucions) {
       this.idconsejouniversitario = idconsejouniversitario;
       this.nombre = nombre;
       this.fechaCreacion = fechaCreacion;
       this.informeCus = informeCus;
       this.resolucions = resolucions;
    }
   
     @Id 

    
    @Column(name="idconsejouniversitario", unique=true, nullable=false)
    public long getIdconsejouniversitario() {
        return this.idconsejouniversitario;
    }
    
    public void setIdconsejouniversitario(long idconsejouniversitario) {
        this.idconsejouniversitario = idconsejouniversitario;
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="consejoUniversitario")
    public Set<InformeCu> getInformeCus() {
        return this.informeCus;
    }
    
    public void setInformeCus(Set<InformeCu> informeCus) {
        this.informeCus = informeCus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="consejoUniversitario")
    public Set<Resolucion> getResolucions() {
        return this.resolucions;
    }
    
    public void setResolucions(Set<Resolucion> resolucions) {
        this.resolucions = resolucions;
    }




}


