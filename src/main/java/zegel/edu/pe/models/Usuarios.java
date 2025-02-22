package zegel.edu.pe.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "usuarios")
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	private String contrasena;
	private String dni;
	private String foto;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="categorias_id", referencedColumnName = "id")
	private Categorias categorias;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="niveles_id", referencedColumnName = "id")
	private Niveles niveles;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="puntaje_id", referencedColumnName = "id")
	private Puntaje puntaje;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="clubes_id", referencedColumnName = "id")
	private Clubes clubes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="solicitudes_id", referencedColumnName = "id")
	private Solicitudes solicitudes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tipo_usuario_id", referencedColumnName = "id")
	private Tipo_Usuario tipo_usuario;

	public Usuarios() {
	
	}

	public Usuarios(Integer id, String nombre, String apellido, String telefono, String correo, String contrasena,
			String dni, String foto, Categorias categorias, Niveles niveles, Puntaje puntaje, Clubes clubes,
			Solicitudes solicitudes, Tipo_Usuario tipo_usuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.contrasena = contrasena;
		this.dni = dni;
		this.foto = foto;
		this.categorias = categorias;
		this.niveles = niveles;
		this.puntaje = puntaje;
		this.clubes = clubes;
		this.solicitudes = solicitudes;
		this.tipo_usuario = tipo_usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categorias getCategorias() {
		return categorias;
	}

	public void setCategorias(Categorias categorias) {
		this.categorias = categorias;
	}

	public Niveles getNiveles() {
		return niveles;
	}

	public void setNiveles(Niveles niveles) {
		this.niveles = niveles;
	}

	public Puntaje getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Puntaje puntaje) {
		this.puntaje = puntaje;
	}

	public Clubes getClubes() {
		return clubes;
	}

	public void setClubes(Clubes clubes) {
		this.clubes = clubes;
	}

	public Solicitudes getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Solicitudes solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Tipo_Usuario getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(Tipo_Usuario tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}


}
