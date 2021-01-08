package aica.model;

import java.sql.Date;

public class Traza {
	private String usuario;
	private String operacion;
	private Date fecha;
	
	public Traza() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Traza(String usuario, String operacion, Date fecha) {
		super();
		this.usuario = usuario;
		this.operacion = operacion;
		this.fecha = fecha;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
