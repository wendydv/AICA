package aica.reporte;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import aica.model.Lote;
import aica.model.Plan_Produccion;
import aica.model.Producto;
import aica.service.Lote_Service;
import aica.service.PlanProduccion_Service;
import aica.service.Prod_Services;
import aica.visual.DatosPendientesEtapas;
import aica.visual.ParteDiario;
import aica.visual.PlanProduccion_Visual;
import aica.visual.reportes.ConsultasDinamicasAprobados;
import aica.visual.reportes.ConsultasDinamicasPendientes;
import aica.visual.reportes.ConsultasDinamicasPlan;

public class outputToEXL extends outputToFile{
	
    public ArrayList<Lote> listLoteAnalisis = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
    public ArrayList<Lote> listLoteRevision = new ArrayList<Lote>();  //  @jve:decl-index=0:
    
	public ArrayList<Lote> listLoteEtiquetado = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLoteEnvase = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLoteAF = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLote = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Plan_Produccion> listPlan = new ArrayList<Plan_Produccion>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listReporteP = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listReporteA = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProducto = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listPendientes = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listRechazados = new ArrayList<Lote>();  //  @jve:decl-index=0:
	

	public outputToEXL(String address) {
		super(address);
		// TODO Auto-generated constructor stub
	}
	public void LlenarListas(){
		try {
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			for(int i=0; i<listLote.size(); i++){
				if(listLote.get(i).getCod_estado()==1){
				   listLoteAnalisis.add(listLote.get(i));
				   listLoteRevision.add(listLote.get(i));}
				else
				  if(listLote.get(i).getCod_estado()==2){
					 listLoteRevision.add(listLote.get(i));}
				else
				  if(listLote.get(i).getCod_estado()==3){
					 listLoteEtiquetado.add(listLote.get(i));}
				else
				  if(listLote.get(i).getCod_estado()==4){
					 listLoteEnvase.add(listLote.get(i));}
				else
				  if(listLote.get(i).getCod_estado()==5){
					 listLoteAF.add(listLote.get(i));}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void LlenarListaPendientes(){
		try {
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			for(int i=0; i<listLote.size(); i++){
				if((listLote.get(i).getCod_estado()!=5)||(listLote.get(i).getCod_estado()!=6)){
					 listPendientes.add(listLote.get(i));}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void saveAnalisis(int type) {
		    File file = new File(address);
			HSSFWorkbook wb = null;
			HSSFSheet sheet = null;
			int columna = 0;
			int pos = 0;
			LlenarListas();
			Producto producto = null;
			String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
			if(!file.exists()){
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			}
			else{
				file.delete();
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			 }
	         if (columna ==0 ){
	        	pos = listLoteAnalisis.size()+5; 
	        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
	        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes de Aprobación  Analítica");
	        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
				HSSFRow row = sheet.createRow(3);
				if (type == full){
					row.createCell(columna).setCellValue("Código");
					row.createCell(columna+1).setCellValue("Producto");
					row.createCell(columna+2).setCellValue("Formato");
					row.createCell(columna+3).setCellValue("Destino");
					row.createCell(columna+4).setCellValue("Cantidad de Unidades");
					row.createCell(columna+5).setCellValue("D/A");
					row.createCell(columna+6).setCellValue("Fecha Análisis");
				}
			}
	        float cantidad_total=0;
			int j = 3;
			if(listLoteAnalisis.size()!=0){
			for (int i = 0; i < listLoteAnalisis.size() && j < 65000; i+=SaltarIteracion) {
				cantidad_total= cantidad_total+listLoteAnalisis.get(i).getCant_unidades();
				if (columna ==0 ){
					HSSFRow	row1 = null;
					try {
						row1 = sheet.createRow(j+1);
					} catch (Exception e) {}
				if (type == full){
						try {
							producto = Prod_Services.getProdVO(listLoteAnalisis.get(i).getProducto());
							row1.createCell(columna).setCellValue(listLoteAnalisis.get(i).getCod());
							row1.createCell(columna+1).setCellValue(producto.getNombre());
							row1.createCell(columna+2).setCellValue(listLoteAnalisis.get(i).getFormato());
							if(listLoteAnalisis.get(i).getCodigo_tipo_lote()==1){
								row1.createCell(columna+3).setCellValue("Nacional");
							}
							if(listLoteAnalisis.get(i).getCodigo_tipo_lote()==2){
								row1.createCell(columna+3).setCellValue("Exportación");
							}
							row1.createCell(columna+4).setCellValue(listLoteAnalisis.get(i).getCant_unidades());
							row1.createCell(columna+5).setCellValue(listLoteAnalisis.get(i).getDiasAprob());
							int p = listLoteAnalisis.get(i).getFecha_analisis().getYear() + 1900;
							int m = listLoteAnalisis.get(i).getFecha_analisis().getMonth() + 1;
							int d = listLoteAnalisis.get(i).getFecha_analisis().getDate();
							row1.createCell(columna+6).setCellValue( p + "-" + m + "-" + d);
						} catch (Exception e) {}
					}
				}
				j++;	
			}
			sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
		}
			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Pendientes de Aprobación Analítica.", "Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
	}
	@Override
	public void saveRevision(int type) {
		    File file = new File(address);
			HSSFWorkbook wb = null;
			HSSFSheet sheet = null;
			int columna = 0;
			int pos = 0;
			LlenarListas();
			Producto producto = null;
			String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
			if(!file.exists()){
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			}
			else{
				file.delete();
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			 }
	         if (columna ==0 ){
	        	pos = listLoteRevision.size()+5; 
	        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
	        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes de Revisión");
	        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
	        	HSSFRow row = sheet.createRow(3);
				if (type == full){
					row.createCell(columna).setCellValue("Código");
					row.createCell(columna+1).setCellValue("Producto");
					row.createCell(columna+2).setCellValue("Formato");
					row.createCell(columna+3).setCellValue("Destino");
					row.createCell(columna+4).setCellValue("Cantidad de Unidades");
					row.createCell(columna+5).setCellValue("D/A");
					row.createCell(columna+6).setCellValue("Análisis");
					row.createCell(columna+7).setCellValue("Fecha Revisión");
				}
			}
	        float cantidad_total=0;
			int j = 3;
			if(listLoteRevision.size()!=0){
			for (int i = 0; i < listLoteRevision.size() && j < 65000; i+=SaltarIteracion) {
				cantidad_total= cantidad_total+listLoteRevision.get(i).getCant_unidades();
				if (columna ==0 ){
					HSSFRow	row1 = null;
					try {
						row1 = sheet.createRow(j+1);
					} catch (Exception e) {}
				if (type == full){
						try {
							producto = Prod_Services.getProdVO(listLoteRevision.get(i).getProducto());
							row1.createCell(columna).setCellValue(listLoteRevision.get(i).getCod());
							row1.createCell(columna+1).setCellValue(producto.getNombre());
							row1.createCell(columna+2).setCellValue(listLoteRevision.get(i).getFormato());
							if(listLoteRevision.get(i).getCodigo_tipo_lote()==1){
								row1.createCell(columna+3).setCellValue("Nacional");
							}
							if(listLoteRevision.get(i).getCodigo_tipo_lote()==2){
								row1.createCell(columna+3).setCellValue("Exportación");
							}
							row1.createCell(columna+4).setCellValue(listLoteRevision.get(i).getCant_unidades());
							row1.createCell(columna+5).setCellValue(listLoteRevision.get(i).getDiasAprob());
							if(listLoteRevision.get(i).getCod_estado()== 2){
							 row1.createCell(columna+6).setCellValue("SI");	
							}
							else{
							 row1.createCell(columna+6).setCellValue("NO");		
							}
							int p = listLoteRevision.get(i).getFecha_revisado().getYear() + 1900;
							int m = listLoteRevision.get(i).getFecha_revisado().getMonth() + 1;
							int d = listLoteRevision.get(i).getFecha_revisado().getDate();
							row1.createCell(columna+7).setCellValue( p + "-" + m + "-" + d);
						} catch (Exception e) {}
					}
				}
				j++;	
			} 
			sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
		}
			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Pendientes de Revisión.", "Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
	}
    public  void saveEtiquetado(int type){
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		int pos = 0;
		LlenarListas();
		Producto producto = null;
		String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	pos = listLoteEtiquetado.size()+5; 
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
	        sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes de Etiquetado");
        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
				row.createCell(columna).setCellValue("Código");
				row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Formato");
				row.createCell(columna+3).setCellValue("Destino");
				row.createCell(columna+4).setCellValue("Cantidad de Unidades");
				row.createCell(columna+5).setCellValue("D/A");
				row.createCell(columna+6).setCellValue("Fecha Etiquetado");
			}
		}
        float cantidad_total=0;
		int j = 3;
		if(listLoteEtiquetado.size()!=0){
		for (int i = 0; i < listLoteEtiquetado.size() && j < 65000; i+=SaltarIteracion) {
			cantidad_total= cantidad_total+listLoteEtiquetado.get(i).getCant_unidades();
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						producto = Prod_Services.getProdVO(listLoteEtiquetado.get(i).getProducto());
						row1.createCell(columna).setCellValue(listLoteEtiquetado.get(i).getCod());
						row1.createCell(columna+1).setCellValue(producto.getNombre());
						row1.createCell(columna+2).setCellValue(listLoteEtiquetado.get(i).getFormato());
						if(listLoteEtiquetado.get(i).getCodigo_tipo_lote()==1){
							row1.createCell(columna+3).setCellValue("Nacional");
						}
						if(listLoteEtiquetado.get(i).getCodigo_tipo_lote()==2){
							row1.createCell(columna+3).setCellValue("Exportación");
						}
						row1.createCell(columna+4).setCellValue(listLoteEtiquetado.get(i).getCant_unidades());
						row1.createCell(columna+5).setCellValue(listLoteEtiquetado.get(i).getDiasAprob());
						int p = listLoteEtiquetado.get(i).getFecha_etiquetado().getYear() + 1900;
						int m = listLoteEtiquetado.get(i).getFecha_etiquetado().getMonth() + 1;
						int d = listLoteEtiquetado.get(i).getFecha_etiquetado().getDate();
						row1.createCell(columna+6).setCellValue( p + "-" + m + "-" + d);
					} catch (Exception e) {}
				}
			}
			j++;	
		} 
		sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Pendientes de Etiquetado.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
    }
	
	public  void saveEnvase(int type){
		 File file = new File(address);
			HSSFWorkbook wb = null;
			HSSFSheet sheet = null;
			int columna = 0;
			int pos = 0;
			LlenarListas();
			Producto producto = null;
			String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
			if(!file.exists()){
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			}
			else{
				file.delete();
				wb = new HSSFWorkbook();//se crea un nuevo fichero
				sheet = wb.createSheet("Hoja 1");
			 }
	         if (columna ==0 ){
	        	pos = listLoteEnvase.size()+5; 
	        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
	        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes de Envase");
	         	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
				HSSFRow row = sheet.createRow(3);
				if (type == full){
					row.createCell(columna).setCellValue("Código");
					row.createCell(columna+1).setCellValue("Producto");
					row.createCell(columna+2).setCellValue("Formato");
					row.createCell(columna+3).setCellValue("Destino");
					row.createCell(columna+4).setCellValue("Cantidad de Unidades");
					row.createCell(columna+5).setCellValue("D/A");
					row.createCell(columna+6).setCellValue("Fecha Envase");
				}
			}
	        float cantidad_total=0;
			int j = 3;
			if(listLoteEnvase.size()!=0){
			for (int i = 0; i < listLoteEnvase.size() && j < 65000; i+=SaltarIteracion) {
				cantidad_total= cantidad_total+listLoteEnvase.get(i).getCant_unidades();
				if (columna ==0 ){
					HSSFRow	row1 = null;
					try {
						row1 = sheet.createRow(j+1);
					} catch (Exception e) {}
				if (type == full){
						try {
							producto = Prod_Services.getProdVO(listLoteEnvase.get(i).getProducto());
							row1.createCell(columna).setCellValue(listLoteEnvase.get(i).getCod());
							row1.createCell(columna+1).setCellValue(producto.getNombre());
							row1.createCell(columna+2).setCellValue(listLoteEnvase.get(i).getFormato());
							if(listLoteEnvase.get(i).getCodigo_tipo_lote()==1){
								row1.createCell(columna+3).setCellValue("Nacional");
							}
							if(listLoteEnvase.get(i).getCodigo_tipo_lote()==2){
								row1.createCell(columna+3).setCellValue("Exportación");
							}
							row1.createCell(columna+4).setCellValue(listLoteEnvase.get(i).getCant_unidades());
							row1.createCell(columna+5).setCellValue(listLoteEnvase.get(i).getDiasAprob());
							int p = listLoteEnvase.get(i).getFecha_envase().getYear() + 1900;
							int m = listLoteEnvase.get(i).getFecha_envase().getMonth() + 1;
							int d = listLoteEnvase.get(i).getFecha_envase().getDate();
							row1.createCell(columna+6).setCellValue( p + "-" + m + "-" + d);
						} catch (Exception e) {}
					}
				}
				j++;	
			}
			sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
		}
			FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(file);
				wb.write(fileOut);
				fileOut.close();
				JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Pendientes de Envase.", "Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
				JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
	}
	
	public  void saveAproF(int type){
		File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		int pos = 0;
		LlenarListas();
		Producto producto = null;
		String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	pos = listLoteAF.size()+5; 
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes de Aprobación Final");
	        sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
				row.createCell(columna).setCellValue("Código");
				row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Formato");
				row.createCell(columna+3).setCellValue("Destino");
				row.createCell(columna+4).setCellValue("Cantidad de Unidades");
				row.createCell(columna+5).setCellValue("D/A");
				row.createCell(columna+6).setCellValue("Fecha Aprobación Final");
			}
		}
        float cantidad_total=0;
		int j = 3;
		if(listLoteAF.size()!=0){
		for (int i = 0; i < listLoteAF.size() && j < 65000; i+=SaltarIteracion) {
			cantidad_total= cantidad_total+listLoteAF.get(i).getCant_unidades();
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						producto = Prod_Services.getProdVO(listLoteAF.get(i).getProducto());
						row1.createCell(columna).setCellValue(listLoteAF.get(i).getCod());
						row1.createCell(columna+1).setCellValue(producto.getNombre());
						row1.createCell(columna+2).setCellValue(listLoteAF.get(i).getFormato());
						if(listLoteAF.get(i).getCodigo_tipo_lote()==1){
							row1.createCell(columna+3).setCellValue("Nacional");
						}
						if(listLoteAF.get(i).getCodigo_tipo_lote()==2){
							row1.createCell(columna+3).setCellValue("Exportación");
						}
						row1.createCell(columna+4).setCellValue(listLoteAF.get(i).getCant_unidades());
						row1.createCell(columna+5).setCellValue(listLoteAF.get(i).getDiasAprob());
						int p = listLoteAF.get(i).getFecha_aprobacion_final().getYear() + 1900;
						int m = listLoteAF.get(i).getFecha_aprobacion_final().getMonth() + 1;
						int d = listLoteAF.get(i).getFecha_aprobacion_final().getDate();
						row1.createCell(columna+6).setCellValue( p + "-" + m + "-" + d);
					} catch (Exception e) {}
				}
			}
			j++;	
		} 
		sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Pendientes de Aprobación Final.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}	
	}
	
	public  void savePlanP(int type){
		File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		String fecha = PlanProduccion_Visual.getPlanV().Fecha_actual;
		int columna = 0;
		try {
			listPlan = (ArrayList<Plan_Produccion>) PlanProduccion_Service.getAllPlan();
			listProducto = (ArrayList<Producto>)Prod_Services.getAllProd();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Producto producto = null;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(2).createCell(columna).setCellValue("Plan Anual de Producción"); 
			HSSFRow row = sheet.createRow(3);
			if (type == full){
				row.createCell(columna).setCellValue("Producto");
				row.createCell(columna+1).setCellValue("Enero");
				row.createCell(columna+2).setCellValue("Febrero");
				row.createCell(columna+3).setCellValue("Marzo");
				row.createCell(columna+4).setCellValue("Abril");
				row.createCell(columna+5).setCellValue("Mayo");
				row.createCell(columna+6).setCellValue("Junio");
				row.createCell(columna+7).setCellValue("Julio");
				row.createCell(columna+8).setCellValue("Agosto");
				row.createCell(columna+9).setCellValue("Septiembre");
				row.createCell(columna+10).setCellValue("Octubre");
				row.createCell(columna+11).setCellValue("Noviembre");
				row.createCell(columna+12).setCellValue("Diciembre");
				row.createCell(columna+13).setCellValue("Total");
			}
		}
		int j = 3;
		if(listProducto.size()!=0 && listPlan.size()!=0){
		    for (int i=0 ;i < listProducto.size() && j < 65000; i+=SaltarIteracion){
		    	producto=listProducto.get(i);
		    		  if (columna ==0 ){
		  				HSSFRow	row1 = null;
		  				try {
		  					row1 = sheet.createRow(j+1);
		  				} catch (Exception e) {}
		  			if (type == full){
		  					try {
		  					  for (int k = 0; k < listPlan.size();k++) {
		  				    	  if (listPlan.get(k).getCod_producto().equals(producto.getCod_producto())){
		  				    		  int elaborar=0;
		  				    		  int aprobar=0;
		  				    		  int totale=0;
		  				    		  int totala=0;
		  						row1.createCell(columna).setCellValue(producto.getNombre());
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Enero")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+1).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Febrero")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+2).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Marzo")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+3).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Abril")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+4).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Mayo")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+5).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Junio")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+6).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Julio")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+7).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Agosto")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+8).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Septiembre")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+9).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Octubre")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+10).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Noviembre")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+11).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						if(listPlan.get(k).getMes().equalsIgnoreCase("Diciembre")){
		  							elaborar= listPlan.get(k).getElaborar();
		  							aprobar= listPlan.get(k).getAprobar();
		  							totale=totale+elaborar;
		  			    		    totala=totala+aprobar;
		  							row1.createCell(columna+12).setCellValue("Elaborar:"+elaborar+","+" "+"Aprobar:"+aprobar);	
		  						}
		  						row1.createCell(columna+13).setCellValue("Elaborar:"+totale+","+" "+"Aprobar:"+totala);
		  				    }
		  				  }
		  				} catch (Exception e) {}
		  			}  
		    	 }
		      j++;
		   } 
	  }
	
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Plan Anual de Producción.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}	
	}
	public void saveReporteDinamicoP(int type, int estado, String formato, int tipo, Date dateInicio, Date dateFin) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		int pos = 0;
		String fecha = ConsultasDinamicasAprobados.getAprobados().fecha_actual;
		try {
			listReporteP = Lote_Service.listReporteP(estado, formato, tipo, dateInicio, dateFin);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Producto producto = null;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	pos = listReporteP.size()+5; 
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Pendientes");
        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
				row.createCell(columna).setCellValue("Código");
				row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Formato");
				row.createCell(columna+3).setCellValue("Cantidad de Unidades");
				row.createCell(columna+4).setCellValue("D/A");
				row.createCell(columna+5).setCellValue("Fecha de Análisis");
				row.createCell(columna+6).setCellValue("Fecha de Revisión");
				row.createCell(columna+7).setCellValue("Fecha de Etiquetado");
				row.createCell(columna+8).setCellValue("Fecha de Envase");
				row.createCell(columna+9).setCellValue("Fecha de Aprobación Final");
				row.createCell(columna+10).setCellValue("Estado");
			}
		}
        float cantidad_total=0;
		int j = 3;
		if(listReporteP.size()!=0){
		for (int i = 0; i < listReporteP.size() && j < 65000; i+=SaltarIteracion) {
			cantidad_total= cantidad_total+listReporteP.get(i).getCant_unidades();
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						producto = Prod_Services.getProdVO(listReporteP.get(i).getProducto());
						row1.createCell(columna).setCellValue(listReporteP.get(i).getCod());
						row1.createCell(columna+1).setCellValue(producto.getNombre());
						row1.createCell(columna+2).setCellValue(listReporteP.get(i).getFormato());
						row1.createCell(columna+3).setCellValue(listReporteP.get(i).getCant_unidades());
						row1.createCell(columna+4).setCellValue(listReporteP.get(i).getDiasAprob());
						if(listReporteP.get(i).getFecha_analisis()!=null){
							int pa = listReporteP.get(i).getFecha_analisis().getYear() + 1900;
							int ma = listReporteP.get(i).getFecha_analisis().getMonth() + 1;
							int da = listReporteP.get(i).getFecha_analisis().getDate();
							row1.createCell(columna+5).setCellValue( pa + "-" + ma + "-" + da);	
						}
						if (listReporteP.get(i).getFecha_revisado()!=null){
							int pr = listReporteP.get(i).getFecha_revisado().getYear() + 1900;
							int mr = listReporteP.get(i).getFecha_revisado().getMonth() + 1;
							int dr = listReporteP.get(i).getFecha_revisado().getDate();
							row1.createCell(columna+6).setCellValue( pr + "-" + mr + "-" + dr);
						}
						if(listReporteP.get(i).getFecha_etiquetado()!=null){
							int pe = listReporteP.get(i).getFecha_etiquetado().getYear() + 1900;
							int me = listReporteP.get(i).getFecha_etiquetado().getMonth() + 1;
							int de = listReporteP.get(i).getFecha_etiquetado().getDate();
							row1.createCell(columna+7).setCellValue( pe + "-" + me + "-" + de);
						}
						if (listReporteP.get(i).getFecha_envase()!=null){
							int pen = listReporteP.get(i).getFecha_envase().getYear() + 1900;
							int men = listReporteP.get(i).getFecha_envase().getMonth() + 1;
							int den = listReporteP.get(i).getFecha_envase().getDate();
							row1.createCell(columna+8).setCellValue( pen + "-" + men + "-" + den);	
						}
						if(listReporteP.get(i).getFecha_aprobacion_final()!=null){
							int pap = listReporteP.get(i).getFecha_aprobacion_final().getYear() + 1900;
							int map = listReporteP.get(i).getFecha_aprobacion_final().getMonth() + 1;
							int dap = listReporteP.get(i).getFecha_aprobacion_final().getDate();
							row1.createCell(columna+9).setCellValue( pap + "-" + map + "-" + dap);	
						}
						if(listReporteP.get(i).getCod_estado() == 1){
						 row1.createCell(columna+10).setCellValue("Pendiente Aprobación Analítica");	
						}
						if(listReporteP.get(i).getCod_estado() == 2){
						 row1.createCell(columna+10).setCellValue("Pendiente Revisión");	
						}
						if(listReporteP.get(i).getCod_estado() == 3){
						 row1.createCell(columna+10).setCellValue("Pendiente Etiquetado");	
						}
						if(listReporteP.get(i).getCod_estado() == 4){
						 row1.createCell(columna+10).setCellValue("Pendiente Envase");
						}
						if(listReporteP.get(i).getCod_estado() == 5){
						 row1.createCell(columna+10).setCellValue("Pendiente Aprobación Final");
						}						
					} catch (Exception e) {}
				}
			}
			j++;	
		}
		sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);	
		} 
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Consulta Dinámica Pendientes.xls", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }

	public void saveReporteDinamicoA(int type, int estado, String formato, int tipo, Date dateInicio, Date dateFin) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		int pos = 0;
		String fecha = ConsultasDinamicasPendientes.getStrategy().fecha_actual;
		try {
			listReporteA = Lote_Service.listReporteA(estado, formato, tipo, dateInicio, dateFin);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Producto producto = null;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	pos = listReporteA.size()+5; 
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(2).createCell(columna).setCellValue("Lotes Aprobados");
        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
				row.createCell(columna).setCellValue("Código");
				row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Formato");
				row.createCell(columna+3).setCellValue("Cantidad de Unidades");
				row.createCell(columna+4).setCellValue("D/A");
				row.createCell(columna+5).setCellValue("Fecha de Análisis");
				row.createCell(columna+6).setCellValue("Fecha de Revisión");
				row.createCell(columna+7).setCellValue("Fecha de Etiquetado");
				row.createCell(columna+8).setCellValue("Fecha de Envase");
				row.createCell(columna+9).setCellValue("Fecha de Aprobación Final");
				row.createCell(columna+10).setCellValue("Fecha de Aprobación");
			}
		}
        float cantidad_total=0;
		int j = 3;
		if(listReporteA.size()!=0){
		for (int i = 0; i < listReporteA.size() && j < 65000; i+=SaltarIteracion) {
			cantidad_total= cantidad_total+listReporteA.get(i).getCant_unidades();
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						producto = Prod_Services.getProdVO(listReporteA.get(i).getProducto());
						row1.createCell(columna).setCellValue(listReporteA.get(i).getCod());
						row1.createCell(columna+1).setCellValue(producto.getNombre());
						row1.createCell(columna+2).setCellValue(listReporteA.get(i).getFormato());
						row1.createCell(columna+3).setCellValue(listReporteA.get(i).getCant_unidades());
						row1.createCell(columna+4).setCellValue(listReporteA.get(i).getDiasAprob());
						int pa = listReporteA.get(i).getFecha_analisis().getYear() + 1900;
						int ma = listReporteA.get(i).getFecha_analisis().getMonth() + 1;
						int da = listReporteA.get(i).getFecha_analisis().getDate();
						row1.createCell(columna+5).setCellValue( pa + "-" + ma + "-" + da);
						int pr = listReporteA.get(i).getFecha_revisado().getYear() + 1900;
						int mr = listReporteA.get(i).getFecha_revisado().getMonth() + 1;
						int dr = listReporteA.get(i).getFecha_revisado().getDate();
						row1.createCell(columna+6).setCellValue( pr + "-" + mr + "-" + dr);
						int pe = listReporteA.get(i).getFecha_etiquetado().getYear() + 1900;
						int me = listReporteA.get(i).getFecha_etiquetado().getMonth() + 1;
						int de = listReporteA.get(i).getFecha_etiquetado().getDate();
						row1.createCell(columna+7).setCellValue( pe + "-" + me + "-" + de);
						int pen = listReporteA.get(i).getFecha_envase().getYear() + 1900;
						int men = listReporteA.get(i).getFecha_envase().getMonth() + 1;
						int den = listReporteA.get(i).getFecha_envase().getDate();
						row1.createCell(columna+8).setCellValue( pen + "-" + men + "-" + den);
						int pap = listReporteA.get(i).getFecha_aprobacion_final().getYear() + 1900;
						int map = listReporteA.get(i).getFecha_aprobacion_final().getMonth() + 1;
						int dap = listReporteA.get(i).getFecha_aprobacion_final().getDate();
						row1.createCell(columna+9).setCellValue( pap + "-" + map + "-" + dap);
						if(listReporteA.get(i).getCod_estado() == 2){
						    row1.createCell(columna+10).setCellValue( pa + "-" + ma + "-" + da);
						}
						if(listReporteA.get(i).getCod_estado() == 3){
						    row1.createCell(columna+10).setCellValue( pr + "-" + mr + "-" + dr);	
						}
						if(listReporteA.get(i).getCod_estado() == 4){
							row1.createCell(columna+10).setCellValue( pe + "-" + me + "-" + de);	
						}
						if(listReporteA.get(i).getCod_estado() == 5){
							row1.createCell(columna+10).setCellValue( pen + "-" + men + "-" + den);
						}
						if(listReporteA.get(i).getCod_estado() == 6){
							row1.createCell(columna+10).setCellValue( pap + "-" + map + "-" + dap);
						}						
					} catch (Exception e) {}
				}
			}
			j++;	
		}
		sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);	
		} 
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en:AICA/reportes/Excel/Consulta Dinámica Aprobados.xls", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }
	public void savePlanAprobacion(int type) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
		LlenarListaPendientes();
		try {
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(2).createCell(columna).setCellValue("Productos Elaborados Pendientes de Etiquetado y Envase");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
			    row.createCell(columna).setCellValue("Producto");
				row.createCell(columna+1).setCellValue("Lotes");
				row.createCell(columna+2).setCellValue("Cantidad");
				row.createCell(columna+3).setCellValue("Precio");
				row.createCell(columna+4).setCellValue("Valor");
			}
		}
        float cantidad=0;
 		String cod_lotes = "Código:";
		int j = 3;
		if(listProducto.size()!=0){
		for (int i = 0; i < listProducto.size() && j < 65000; i+=SaltarIteracion) {
			for (int w = 0; w < listPendientes.size(); w++){
			  if(listProducto.get(i).getCod_producto().equals(listPendientes.get(w).getProducto())){
				cantidad= cantidad+listPendientes.get(w).getCant_unidades();
				cod_lotes= cod_lotes + ","+listPendientes.get(w).getCod();
				}
			  }
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						row1.createCell(columna).setCellValue(listProducto.get(i).getNombre());
						row1.createCell(columna+1).setCellValue(cod_lotes);
						row1.createCell(columna+2).setCellValue(cantidad);
						row1.createCell(columna+3).setCellValue(listProducto.get(i).getPrecio());
						row1.createCell(columna+4).setCellValue(cantidad*(listProducto.get(i).getPrecio())*1000);
					} catch (Exception e) {}
				}
			}
			cod_lotes="Código:";
			j++;	
		} 
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Plan de Aprobaciones.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }
	public void saveRechazados(int type) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		int columna = 0;
		int pos = 0;
		String fecha = DatosPendientesEtapas.getDatosPendientesEtapas().fecha_actual;
		Producto producto = null;
		Lote lote = null;
		try {
			listRechazados = (ArrayList<Lote>) Lote_Service.getAllLoteRechazado();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	pos = listRechazados.size()+5;
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
        	sheet.createRow(pos).createCell(columna).setCellValue("Cantidad Total de Unidades:");
         	sheet.createRow(2).createCell(columna).setCellValue("Lotes Rechazados");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
			    row.createCell(columna).setCellValue("Lote");
			    row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Formato");
				row.createCell(columna+3).setCellValue("Destino");
				row.createCell(columna+4).setCellValue("Cantidad de Unidades");
				row.createCell(columna+5).setCellValue("D/A");
				row.createCell(columna+6).setCellValue("Etapa");
				row.createCell(columna+7).setCellValue("Fecha");
			}
		}
        float cantidad_total=0;
		int j = 3;
		if(listRechazados.size()!=0){
		for (int i = 0; i < listRechazados.size() && j < 65000; i+=SaltarIteracion) {
			try {
				lote = Lote_Service.findLoteN(listRechazados.get(i).getCod());
				cantidad_total = cantidad_total+lote.getCant_unidades();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						producto = Prod_Services.getProdVOCod(lote.getProducto());
						row1.createCell(columna).setCellValue(listRechazados.get(i).getCod());
						row1.createCell(columna+1).setCellValue(producto.getNombre());
						row1.createCell(columna+2).setCellValue(lote.getFormato());
						if(lote.getCodigo_tipo_lote()==1){
							row1.createCell(columna+3).setCellValue("Nacional");
						}
						if(lote.getCodigo_tipo_lote()==2){
							row1.createCell(columna+3).setCellValue("Exportación");
						}
						row1.createCell(columna+4).setCellValue(lote.getCant_unidades());
						row1.createCell(columna+5).setCellValue(lote.getDiasAprob());
						if(listRechazados.get(i).getCod_estado() == 1){
							row1.createCell(columna+6).setCellValue("Aprobación Analítica");	
							}
						if(listRechazados.get(i).getCod_estado() == 2){
							row1.createCell(columna+6).setCellValue("Revisión");	
							}
						if(listRechazados.get(i).getCod_estado() == 3){
							row1.createCell(columna+6).setCellValue("Etiquetado");	
							}
						if(listRechazados.get(i).getCod_estado() == 4){
							row1.createCell(columna+6).setCellValue("Envase");
							}
						if(listRechazados.get(i).getCod_estado() == 5){
							row1.createCell(columna+6).setCellValue("Aprobación Final");
							}
						int p = listRechazados.get(i).getFecha().getYear() + 1900;
						int m = listRechazados.get(i).getFecha().getMonth() + 1;
						int d = listRechazados.get(i).getFecha().getDate();
						row1.createCell(columna+7).setCellValue(p + "-" + m + "-" + d);
					} catch (Exception e) {}
				}
			}
			j++;	
		}
		sheet.createRow(pos+1).createCell(columna+1).setCellValue(cantidad_total);
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Lotes Rechazados.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }
	public void saveParteD(int type) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		String fecha = ParteDiario.getParteDiario().fecha;
		int columna = 0;
		if(!file.exists()){
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();//se crea un nuevo fichero
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	sheet.createRow(1).createCell(columna+8).setCellValue(fecha);
         	sheet.createRow(2).createCell(columna).setCellValue("Parte Diario");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
			    row.createCell(columna).setCellValue("Clave");
			    row.createCell(columna+1).setCellValue("Producto");
				row.createCell(columna+2).setCellValue("Plan del Mes");
				row.createCell(columna+3).setCellValue("Diario");
				row.createCell(columna+4).setCellValue("Acumulado");
				row.createCell(columna+5).setCellValue("Total");
			}
		}
		int j = 3;
		if(ParteDiario.getParteDiario().defaultTableModel1.getRowCount()!=0){
		for (int i = 0; i < ParteDiario.getParteDiario().defaultTableModel1.getRowCount()&& j < 65000; i+=SaltarIteracion) {
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						row1.createCell(columna).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 0));
						row1.createCell(columna+1).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 1));
						row1.createCell(columna+2).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 2));
						row1.createCell(columna+3).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 3));
						row1.createCell(columna+4).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 4));
						row1.createCell(columna+5).setCellValue((String)ParteDiario.getParteDiario().defaultTableModel1.getValueAt(i, 5));
						
					} catch (Exception e) {}
				}
			}
			j++;	
		}
		
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/ParteDiario.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }
	public void saveReportePlan(int type) {
	    File file = new File(address);
		HSSFWorkbook wb = null;
		HSSFSheet sheet = null;
		String fecha = ConsultasDinamicasPlan.getPlan().fecha_actual;
		int columna = 0;
		if(!file.exists()){
			wb = new HSSFWorkbook();
			sheet = wb.createSheet("Hoja 1");
		}
		else{
			file.delete();
			wb = new HSSFWorkbook();
			sheet = wb.createSheet("Hoja 1");
		 }
         if (columna ==0 ){
        	sheet.createRow(1).createCell(columna+5).setCellValue(fecha);
         	sheet.createRow(2).createCell(columna).setCellValue("Plan de Producción");
			HSSFRow row = sheet.createRow(3);
			if (type == full){
			    row.createCell(columna).setCellValue("Producto");
			    row.createCell(columna+1).setCellValue("Elaborar");
				row.createCell(columna+2).setCellValue("Aprobar");
			}
		}
		int j = 3;
		if(ConsultasDinamicasPlan.getPlan().datosReportedefaultTableModel.getRowCount()!=0){
		for (int i = 0; i < ConsultasDinamicasPlan.getPlan().datosReportedefaultTableModel.getRowCount()&& j < 65000; i+=SaltarIteracion) {
			if (columna ==0 ){
				HSSFRow	row1 = null;
				try {
					row1 = sheet.createRow(j+1);
				} catch (Exception e) {}
			if (type == full){
					try {
						row1.createCell(columna).setCellValue((String)ConsultasDinamicasPlan.getPlan().datosReportedefaultTableModel.getValueAt(i, 0));
						row1.createCell(columna+1).setCellValue((Integer)ConsultasDinamicasPlan.getPlan().datosReportedefaultTableModel.getValueAt(i, 1));
						row1.createCell(columna+2).setCellValue((Integer)ConsultasDinamicasPlan.getPlan().datosReportedefaultTableModel.getValueAt(i, 2));
						
					} catch (Exception e) {}
				}
			}
			j++;	
		}
		
	}
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(file);
			wb.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(new JPanel(), "Reporte Generado, Visualizarlo en: AICA/reportes/Excel/Consulta Dinámica Plan.", "Información", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocurrio el siguiente error al escribir el archivo " + e.getMessage() );
			JOptionPane.showMessageDialog(new JPanel(), "Ocurrio el siguiente error al escribir el archivo " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
   }
}
