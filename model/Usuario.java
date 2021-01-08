package aica.model;

public class Usuario {

	private String nombre;
	private String contrasena;
	private int rol;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(String nombre, String contrasena, int rol) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
		this.rol = rol;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}

}
