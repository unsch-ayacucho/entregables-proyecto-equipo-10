package pe.edu.unsch.entities;
// Generated Jul 23, 2019, 11:56:23 AM by Hibernate Tools 4.3.2-SNAPSHOT


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
 * Calendario generated by hbm2java
 */
@Entity
@Table(name="Calendario"
    ,catalog="categorizacionbd"
)
public class Calendario  implements java.io.Serializable {


     private long idcalendario;
     private String proceso;
     private Date fechaInicio;
     private Date fechaFin;
     private Set<CalendarioDetalle> calendarioDetalles = new HashSet<CalendarioDetalle>(0);

    public Calendario() {
    }

	
    public Calendario(long idcalendario, Date fechaInicio) {
        this.idcalendario = idcalendario;
        this.fechaInicio = fechaInicio;
    }
    public Calendario(long idcalendario, String proceso, Date fechaInicio, Date fechaFin, Set<CalendarioDetalle> calendarioDetalles) {
       this.idcalendario = idcalendario;
       this.proceso = proceso;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.calendarioDetalles = calendarioDetalles;
    }
   
     @Id 

    
    @Column(name="idcalendario", unique=true, nullable=false)
    public long getIdcalendario() {
        return this.idcalendario;
    }
    
    public void setIdcalendario(long idcalendario) {
        this.idcalendario = idcalendario;
    }

    
    @Column(name="proceso", length=50)
    public String getProceso() {
        return this.proceso;
    }
    
    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_inicio", nullable=false, length=19)
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_fin", length=19)
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="calendario")
    public Set<CalendarioDetalle> getCalendarioDetalles() {
        return this.calendarioDetalles;
    }
    
    public void setCalendarioDetalles(Set<CalendarioDetalle> calendarioDetalles) {
        this.calendarioDetalles = calendarioDetalles;
    }




}


