package aica.model;

import java.io.Serializable;
import java.sql.Date;

public class Lote implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cod;
	private String producto;
	private int cod_estado;
	private float diasAprob;
	private int codigo_tipo_lote;
	
	private Date fecha_elaboracion;
	private Date fecha_analisis;
	private Date fecha_revisado;
	private Date fecha_envase;
	private Date fecha_etiquetado;
	private Date fecha_aprobacion_final;
	
	private float cant_unidades;
	private String formato;
	
	private boolean estado_analisis;
	private boolean estado_revision;
	private boolean estado_etiquetado;
	private boolean estado_envase;
	private boolean estado_aprobacion_final;
	
	private Date fecha;

	public Lote() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lote(String cod, String producto, int cod_estado, float diasAprob, int codigo_tipo_lote, Date fecha_elaboracion, Date fecha_analisis, Date fecha_revisado, Date fecha_envase, Date fecha_etiquetado, Date fecha_aprobacion_final, float cant_unidades, String formato, boolean estado_analisis, boolean estado_revision, boolean estado_etiquetado, boolean estado_envase, boolean estado_aprobacion_final, Date fecha) {
		super();
		this.cod = cod;
		this.producto = producto;
		this.cod_estado = cod_estado;
		this.diasAprob = diasAprob;
		this.codigo_tipo_lote = codigo_tipo_lote;
		this.fecha_elaboracion = fecha_elaboracion;
		this.fecha_analisis = fecha_analisis;
		this.fecha_revisado = fecha_revisado;
		this.fecha_envase = fecha_envase;
		this.fecha_etiquetado = fecha_etiquetado;
		this.fecha_aprobacion_final = fecha_aprobacion_final;
		this.cant_unidades = cant_unidades;
		this.formato = formato;
		this.estado_analisis = estado_analisis;
		this.estado_revision = estado_revision;
		this.estado_etiquetado = estado_etiquetado;
		this.estado_envase = estado_envase;
		this.estado_aprobacion_final = estado_aprobacion_final;
		this.fecha = fecha;
	}

	public float getCant_unidades() {
		return cant_unidades;
	}

	public void setCant_unidades(float cant_unidades) {
		this.cant_unidades = cant_unidades;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(int cod_estado) {
		this.cod_estado = cod_estado;
	}

	public int getCodigo_tipo_lote() {
		return codigo_tipo_lote;
	}

	public void setCodigo_tipo_lote(int codigo_tipo_lote) {
		this.codigo_tipo_lote = codigo_tipo_lote;
	}

	public float getDiasAprob() {
		return diasAprob;
	}

	public void setDiasAprob(float diasAprob) {
		this.diasAprob = diasAprob;
	}

	public boolean isEstado_analisis() {
		return estado_analisis;
	}

	public void setEstado_analisis(boolean estado_analisis) {
		this.estado_analisis = estado_analisis;
	}

	public boolean isEstado_aprobacion_final() {
		return estado_aprobacion_final;
	}

	public void setEstado_aprobacion_final(boolean estado_aprobacion_final) {
		this.estado_aprobacion_final = estado_aprobacion_final;
	}

	public boolean isEstado_envase() {
		return estado_envase;
	}

	public void setEstado_envase(boolean estado_envase) {
		this.estado_envase = estado_envase;
	}

	public boolean isEstado_etiquetado() {
		return estado_etiquetado;
	}

	public void setEstado_etiquetado(boolean estado_etiquetado) {
		this.estado_etiquetado = estado_etiquetado;
	}

	public boolean isEstado_revision() {
		return estado_revision;
	}

	public void setEstado_revision(boolean estado_revision) {
		this.estado_revision = estado_revision;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFecha_analisis() {
		return fecha_analisis;
	}

	public void setFecha_analisis(Date fecha_analisis) {
		this.fecha_analisis = fecha_analisis;
	}

	public Date getFecha_aprobacion_final() {
		return fecha_aprobacion_final;
	}

	public void setFecha_aprobacion_final(Date fecha_aprobacion_final) {
		this.fecha_aprobacion_final = fecha_aprobacion_final;
	}

	public Date getFecha_elaboracion() {
		return fecha_elaboracion;
	}

	public void setFecha_elaboracion(Date fecha_elaboracion) {
		this.fecha_elaboracion = fecha_elaboracion;
	}

	public Date getFecha_envase() {
		return fecha_envase;
	}

	public void setFecha_envase(Date fecha_envase) {
		this.fecha_envase = fecha_envase;
	}

	public Date getFecha_etiquetado() {
		return fecha_etiquetado;
	}

	public void setFecha_etiquetado(Date fecha_etiquetado) {
		this.fecha_etiquetado = fecha_etiquetado;
	}

	public Date getFecha_revisado() {
		return fecha_revisado;
	}

	public void setFecha_revisado(Date fecha_revisado) {
		this.fecha_revisado = fecha_revisado;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String toString(){
		return this.cod;		
	}
}
