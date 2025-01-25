package zegel.edu.pe.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "solicitudes")
public class Solicitudes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String solicitud_competidor;
	private String encargado_competidor;
	private String solicitud_club;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="estado_id", referencedColumnName = "id")
	private Estados estados;

	public Solicitudes() {

	}
	
	public Solicitudes(String solicitud_competidor, String encargado_competidor, String solicitud_club, Estados estados) {
		this.solicitud_competidor = solicitud_competidor;
		this.encargado_competidor = encargado_competidor;
		this.solicitud_club = solicitud_club;
		this.estados = estados;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSolicitud_competidor() {
		return solicitud_competidor;
	}

	public void setSolicitud_competidor(String solicitud_competidor) {
		this.solicitud_competidor = solicitud_competidor;
	}

	public String getEncargado_competidor() {
		return encargado_competidor;
	}

	public void setEncargado_competidor(String encargado_competidor) {
		this.encargado_competidor = encargado_competidor;
	}

	public String getSolicitud_club() {
		return solicitud_club;
	}

	public void setSolicitud_club(String solicitud_club) {
		this.solicitud_club = solicitud_club;
	}

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

}
