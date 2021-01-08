package aica.reporte;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;

import aica.service.ConnectionBD;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class Reportes {
public static Reportes reportes = new Reportes();
	
	private static java.sql.Connection myConnection = null;
	
	public Reportes(){
		super();
          try {
			ConnectionBD.getConnectionBD().setConnection("localhost","aica","postgres","postgres");
			myConnection = ConnectionBD.getConnectionBD().getConnection();
			//Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void CargarReporteLote_Dinamico(Integer estado, Integer formato, Integer tipo, Date dateInicio, Date dateFin ){
		HashMap<Object, Object> myParameters = new HashMap<Object, Object>();
		myParameters.put("estado", estado);
		myParameters.put("formato", formato);
		myParameters.put("tipo", tipo);
		myParameters.put("dateInicio", dateInicio);
		myParameters.put("dateFin", dateFin);
		
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReporteDinamicoLotes.jasper", myParameters, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e2) {
			e2.printStackTrace();
		}
	}
	public void CargarReporteLote_DinamicoSinFecha(Integer estado, Integer formato, Integer tipo ){
		HashMap<Object, Object> myParameters = new HashMap<Object, Object>();
		myParameters.put("estado", estado);
		myParameters.put("formato", formato);
		myParameters.put("tipo", tipo);
		
		try {
			JasperFillManager.fillReportToFile("reportes/ReporteDinamicoLotesSinFecha.jasper", myParameters, myConnection);
			JasperViewer.viewReport("reportes/ReporteDinamicoLotesSinFecha.jrprint", false, false);
		} catch (JRException e2) {
			e2.printStackTrace();
		}
	}
       
	public void  CargarReportesAnalisis() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReporteAnalisis.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  CargarReportesRevision() {
		try {
			JasperPrint print =JasperFillManager.fillReport("reportes/ReporteRevision.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  CargarReportesEtiquetado() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReporteEtiquetado.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void  CargarReportesEnvase() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReporteEnvase.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  CargarReportesAprobacionF() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReporteAprobacionF.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  CargarReportesPlanProduccion() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/ReportePlanProduccion.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  CargarReporteProductos() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/iReport/ReporteProductos.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  CargarReporteLotesProduccion() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/iReport/LotesProduccion.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  CargarReporteListaUsuarios() {
		try {
			JasperPrint print = JasperFillManager.fillReport("reportes/iReport/ListaUsuarios.jasper", null, myConnection);
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
