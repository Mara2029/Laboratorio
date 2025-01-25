package zegel.edu.pe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "turnos")
public class Turnos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @ManyToOne
	@JoinColumn(name="competiciones_id", referencedColumnName = "id")
    private Competiciones competicion;
	
    @ManyToOne
	@JoinColumn(name="usuario1_id", referencedColumnName = "id")
    private Usuarios usuario1;

    @ManyToOne
	@JoinColumn(name="usuario2_id", referencedColumnName = "id")
    private Usuarios usuario2;

    @ManyToOne
	@JoinColumn(name="ganador_id", referencedColumnName = "id")
    private Usuarios ganador;

	public Turnos() {
	}

	public Turnos(Integer id, Competiciones competicion, Usuarios usuario1, Usuarios usuario2, Usuarios ganador) {
		super();
		this.id = id;
		this.competicion = competicion;
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
		this.ganador = ganador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Competiciones getCompeticion() {
		return competicion;
	}

	public void setCompeticion(Competiciones competicion) {
		this.competicion = competicion;
	}

	public Usuarios getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuarios usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuarios getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuarios usuario2) {
		this.usuario2 = usuario2;
	}

	public Usuarios getGanador() {
		return ganador;
	}

	public void setGanador(Usuarios ganador) {
		this.ganador = ganador;
	}

}
