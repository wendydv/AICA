package aica.model;

import java.sql.Date;

public class Plan_Produccion {
	private String cod_producto;
	private Date fecha;
	private String mes;
	private int elaborar;
	private int aprobar;
	
	public Plan_Produccion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Plan_Produccion(String cod_producto, Date fecha, String mes, int elaborar, int aprobar) {
		super();
		this.cod_producto = cod_producto;
		this.fecha = fecha;
		this.mes = mes;
		this.elaborar = elaborar;
		this.aprobar = aprobar;
	}
	public int getAprobar() {
		return aprobar;
	}
	public void setAprobar(int aprobar) {
		this.aprobar = aprobar;
	}
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	public int getElaborar() {
		return elaborar;
	}
	public void setElaborar(int elaborar) {
		this.elaborar = elaborar;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
}
