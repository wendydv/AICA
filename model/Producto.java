package aica.model;

import java.io.Serializable;

public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cod_producto;
	private String nombre;
	private float consumo_diario;
	private float plan_anual;
	private float precio;
	private String cod_quimefa;
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Producto(String cod_producto, String nombre, float consumo_diario, float plan_anual, float precio, String cod_quimefa) {
		super();
		this.cod_producto = cod_producto;
		this.nombre = nombre;
		this.consumo_diario = consumo_diario;
		this.plan_anual = plan_anual;
		this.precio = precio;
		this.cod_quimefa = cod_quimefa;
	}
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	public String getCod_quimefa() {
		return cod_quimefa;
	}
	public void setCod_quimefa(String cod_quimefa) {
		this.cod_quimefa = cod_quimefa;
	}
	public float getConsumo_diario() {
		return consumo_diario;
	}
	public void setConsumo_diario(float consumo_diario) {
		this.consumo_diario = consumo_diario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPlan_anual() {
		return plan_anual;
	}
	public void setPlan_anual(float plan_anual) {
		this.plan_anual = plan_anual;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
	