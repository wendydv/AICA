package aica.model;

public class Formato_Producto {
	private String cod_producto;
	private int id_formato;
	public Formato_Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Formato_Producto(String cod_producto, int id_formato) {
		super();
		this.cod_producto = cod_producto;
		this.id_formato = id_formato;
	}
	public String getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}
	public int getId_formato() {
		return id_formato;
	}
	public void setId_formato(int id_formato) {
		this.id_formato = id_formato;
	}
}
