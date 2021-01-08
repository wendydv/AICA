package aica.reporte;

import java.sql.Date;

public abstract class outputToFile {
	protected static int full = 3;
	protected String address;
	protected int SaltarIteracion = 1;
	
	public outputToFile(String address){
		this.address = address;
	}
	public abstract void saveAnalisis(int type);
	
	public abstract void saveRevision(int type);
	
	public abstract void saveEtiquetado(int type);
	
	public abstract void saveEnvase(int type);
	
	public abstract void saveAproF(int type);
	
	public abstract void savePlanP(int type);
	
	public abstract void saveReporteDinamicoP (int type, int estado, String formato, int tipo, Date dateInicio, Date dateFin);
	
	public abstract void saveReporteDinamicoA (int type, int estado, String formato, int tipo, Date dateInicio, Date dateFin);
	
	public abstract void savePlanAprobacion(int type);
	
	public abstract void saveRechazados(int type);
	
	public abstract void saveParteD(int type);
	
	public abstract void saveReportePlan(int type);

	public int getSaltarIteracion() {
		return SaltarIteracion;
	}
	public void setSaltarIteracion(int saltarIteracion) {
		SaltarIteracion = saltarIteracion;
	}
}