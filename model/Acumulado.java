package aica.model;

public class Acumulado {
	private float elaborado;
	private float envasado;
	private float aprobado;
	
	public Acumulado() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Acumulado(float elaborado, float envasado, float aprobado) {
		super();
		this.elaborado = elaborado;
		this.envasado = envasado;
		this.aprobado = aprobado;
	}
	public float getAprobado() {
		return aprobado;
	}
	public void setAprobado(float aprobado) {
		this.aprobado = aprobado;
	}
	public float getElaborado() {
		return elaborado;
	}
	public void setElaborado(float elaborado) {
		this.elaborado = elaborado;
	}
	public float getEnvasado() {
		return envasado;
	}
	public void setEnvasado(float envasado) {
		this.envasado = envasado;
	}
	

}
