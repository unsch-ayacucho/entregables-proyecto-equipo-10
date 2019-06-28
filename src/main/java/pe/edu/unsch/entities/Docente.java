package pe.edu.unsch.entities;
// Generated Jun 28, 2019, 3:58:00 PM by Hibernate Tools 4.3.2-SNAPSHOT


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
 * Docente generated by hbm2java
 */
@Entity
@Table(name="Docente"
    ,catalog="categorizacionbd"
)
public class Docente  implements java.io.Serializable {


     private Long iddocente;
     private Usuario usuario;
     private String categoria;
     private String nombres;
     private String apepaterno;
     private String apematerno;
     private String nrodoc;
     private Date fechaNacimiento;
     private String domicilio;
     private String celular;
     private String sexo;
     private Set<Promocion> promocions = new HashSet<Promocion>(0);
     private Set<Archivo> archivos = new HashSet<Archivo>(0);
     private Set<ComisionMiembro> comisionMiembros = new HashSet<ComisionMiembro>(0);

    public Docente() {
    }

	
    public Docente(Usuario usuario, String categoria, String nombres, String apepaterno, String apematerno, String nrodoc, Date fechaNacimiento) {
        this.usuario = usuario;
        this.categoria = categoria;
        this.nombres = nombres;
        this.apepaterno = apepaterno;
        this.apematerno = apematerno;
        this.nrodoc = nrodoc;
        this.fechaNacimiento = fechaNacimiento;
    }
    public Docente(Usuario usuario, String categoria, String nombres, String apepaterno, String apematerno, String nrodoc, Date fechaNacimiento, String domicilio, String celular, String sexo, Set<Promocion> promocions, Set<Archivo> archivos, Set<ComisionMiembro> comisionMiembros) {
       this.usuario = usuario;
       this.categoria = categoria;
       this.nombres = nombres;
       this.apepaterno = apepaterno;
       this.apematerno = apematerno;
       this.nrodoc = nrodoc;
       this.fechaNacimiento = fechaNacimiento;
       this.domicilio = domicilio;
       this.celular = celular;
       this.sexo = sexo;
       this.promocions = promocions;
       this.archivos = archivos;
       this.comisionMiembros = comisionMiembros;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="iddocente", unique=true, nullable=false)
    public Long getIddocente() {
        return this.iddocente;
    }
    
    public void setIddocente(Long iddocente) {
        this.iddocente = iddocente;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idusuario", nullable=false)
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Column(name="categoria", nullable=false, length=50)
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    @Column(name="nombres", nullable=false, length=50)
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    
    @Column(name="apepaterno", nullable=false, length=50)
    public String getApepaterno() {
        return this.apepaterno;
    }
    
    public void setApepaterno(String apepaterno) {
        this.apepaterno = apepaterno;
    }

    
    @Column(name="apematerno", nullable=false, length=50)
    public String getApematerno() {
        return this.apematerno;
    }
    
    public void setApematerno(String apematerno) {
        this.apematerno = apematerno;
    }

    
    @Column(name="nrodoc", nullable=false, length=8)
    public String getNrodoc() {
        return this.nrodoc;
    }
    
    public void setNrodoc(String nrodoc) {
        this.nrodoc = nrodoc;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_nacimiento", nullable=false, length=19)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
    @Column(name="domicilio", length=100)
    public String getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    
    @Column(name="celular", length=9)
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }

    
    @Column(name="sexo", length=1)
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="docente")
    public Set<Promocion> getPromocions() {
        return this.promocions;
    }
    
    public void setPromocions(Set<Promocion> promocions) {
        this.promocions = promocions;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="docente")
    public Set<Archivo> getArchivos() {
        return this.archivos;
    }
    
    public void setArchivos(Set<Archivo> archivos) {
        this.archivos = archivos;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="docente")
    public Set<ComisionMiembro> getComisionMiembros() {
        return this.comisionMiembros;
    }
    
    public void setComisionMiembros(Set<ComisionMiembro> comisionMiembros) {
        this.comisionMiembros = comisionMiembros;
    }




}


