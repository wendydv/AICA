package aica.model;

public class Rol {

	private String descripcion;
	private String rol;

	public Rol() {
		super();
	}
	public Rol(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return this.descripcion;
	}
	
	
}
