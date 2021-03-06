package pe.edu.unsch.entities;
// Generated Jul 22, 2019, 6:56:50 AM by Hibernate Tools 4.3.2-SNAPSHOT


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Expediente generated by hbm2java
 */
@Entity
@Table(name="Expediente"
    ,catalog="categorizacionbd"
)
public class Expediente  implements java.io.Serializable {


     private Long idexpediente;
     private Docente docente;
     private String nombre;
     private boolean isActive;
     private Boolean isClosed;
     private boolean haveSolicitud;
     private Date fechaCreacion;
     private Date fechaCierre;
     private Set<Archivo> archivos = new HashSet<Archivo>(0);

    public Expediente() {
    }

	
    public Expediente(Docente docente, String nombre, boolean isActive, boolean haveSolicitud, Date fechaCreacion) {
        this.docente = docente;
        this.nombre = nombre;
        this.isActive = isActive;
        this.haveSolicitud = haveSolicitud;
        this.fechaCreacion = fechaCreacion;
    }
    public Expediente(Docente docente, String nombre, boolean isActive, boolean isClosed, boolean haveSolicitud, Date fechaCreacion) {
        this.docente = docente;
        this.nombre = nombre;
        this.isActive = isActive;
        this.isClosed = isClosed;
        this.haveSolicitud = haveSolicitud;
        this.fechaCreacion = fechaCreacion;
    }
    public Expediente(Docente docente, String nombre, boolean isActive, Boolean isClosed, boolean haveSolicitud, Date fechaCreacion, Date fechaCierre, Set<Archivo> archivos) {
       this.docente = docente;
       this.nombre = nombre;
       this.isActive = isActive;
       this.isClosed = isClosed;
       this.haveSolicitud = haveSolicitud;
       this.fechaCreacion = fechaCreacion;
       this.fechaCierre = fechaCierre;
       this.archivos = archivos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idexpediente", unique=true, nullable=false)
    public Long getIdexpediente() {
        return this.idexpediente;
    }
    
    public void setIdexpediente(Long idexpediente) {
        this.idexpediente = idexpediente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iddocente", nullable=false)
    public Docente getDocente() {
        return this.docente;
    }
    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    
    @Column(name="nombre", nullable=false, length=50)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="is_active", nullable=false)
    public boolean isIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    
    @Column(name="is_closed")
    public Boolean getIsClosed() {
        return this.isClosed;
    }
    
    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    
    @Column(name="have_solicitud", nullable=false)
    public boolean isHaveSolicitud() {
        return this.haveSolicitud;
    }
    
    public void setHaveSolicitud(boolean haveSolicitud) {
        this.haveSolicitud = haveSolicitud;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", nullable=false, length=19)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_cierre", length=19)
    public Date getFechaCierre() {
        return this.fechaCierre;
    }
    
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="expediente")
    public Set<Archivo> getArchivos() {
        return this.archivos;
    }
    
    public void setArchivos(Set<Archivo> archivos) {
        this.archivos = archivos;
    }




}


