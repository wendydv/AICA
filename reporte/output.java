package aica.reporte;

import java.sql.SQLException;
import java.util.ArrayList;
import aica.model.Lote;
import aica.service.Lote_Service;

public class output {
	
	private outputToFile outputfile;
	private int SaltarIteracion = 1;
	public ArrayList<Lote> listLote = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public int getSaltarIteracion() {
		return SaltarIteracion;
	}
	public void setSaltarIteracion(int saltarIteracion) {
		SaltarIteracion = saltarIteracion;
	}
	public void setOutputToXLS(String address){
		outputfile = new outputToEXL(address);
	}
	public void LlenarLista(){
		try {
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public void saveFull(){
		if (outputfile == null)
//			   outputfile = new outputToTXT("results.txt");
			   outputfile.setSaltarIteracion(SaltarIteracion);
		       LlenarLista();
			   if (listLote.size() != 0)
				  try {
						outputfile.saveAnalisis(outputToFile.full);
					}catch(java.lang.OutOfMemoryError javaheapspace){
						System.out.println("No se pueden guardar los resultados. Memoria llena");
					}			
	}
}
