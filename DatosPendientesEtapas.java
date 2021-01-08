package aica.visual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import aica.model.Control;
import aica.model.Formato_Producto;
import aica.model.Lote;
import aica.model.Producto;
import aica.model.Traza;
import aica.model.Usuario;
import aica.reporte.outputToEXL;
import aica.service.ConnectionBD;
import aica.service.CustomTableCellRenderer;
import aica.service.Lote_Service;
import aica.service.Prod_Services;
import aica.service.Traza_Servicio;
import aica.utiles.UneditableTableModel;
import aica.utiles.Validaciones;
import java.awt.Rectangle;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.lang.System;
import java.lang.String;
import java.awt.event.KeyEvent;

public class DatosPendientesEtapas extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static DatosPendientesEtapas datos = null;  //  @jve:decl-index=0:visual-constraint="1096,12"

	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="9,39"

	private JTabbedPane jTabbedPane = null;
	
	public String fecha_actual;
    
	public ArrayList<Lote> listLote = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProducto = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listFiltros = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public List<String> listFormatos = new ArrayList<String>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLoteAnalisis = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
    public ArrayList<Lote> listLoteRevision = new ArrayList<Lote>();  //  @jve:decl-index=0:
    
	public ArrayList<Lote> listLoteEtiquetado = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLoteEnvase = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLoteAF = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Traza> listTrazas = new ArrayList<Traza>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLotesRechazados = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Formato_Producto> listProdForm= new ArrayList<Formato_Producto>();  //  @jve:decl-index=0:
    
	private Date fechaActual = null;  //  @jve:decl-index=0:
		
	private Usuario loguiado = null;  //  @jve:decl-index=0:
	
	public String addressA="reportes/Excel/Pendientes de Aprobación Analítica.xls";  //  @jve:decl-index=0:
	
	public String addressR="reportes/Excel/Pendientes de Revisión.xls";  //  @jve:decl-index=0:
	
	public String addressE="reportes/Excel/Pendientes de Etiquetado.xls";  //  @jve:decl-index=0:
	
	public String addressEnv="reportes/Excel/Pendientes de Envase.xls";  //  @jve:decl-index=0:
	
	public String addressAF="reportes/Excel/Pendientes de Aprobación Final.xls";  //  @jve:decl-index=0:
	
//-------------------------------------------------- DatosPendientesAnalisis ----------------------------------------------------------------------------
	private JPanel jContentPaneAnalisis = null;

	private JSeparator jSeparatorA = null;

	private JScrollPane datosPAjScrollPane = null;

	private JTable datosPAjTable = null;

	private JButton modificarjButtonA = null;

	private JButton analisisjButtonA = null;

	private DefaultTableModel datosPAdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1532,265"
	
	private JLabel codigojLabelA = null;

	private JLabel formatojLabelA = null;

	private JLabel cantunidjLabelA = null;

	private JLabel cantidadjLabelA = null;

	private JLabel tipojLabelA = null;

	private JLabel fechajLabelA = null;

	private JCalendar fechjCalendarA = null;

	private JPanel jPanelA = null;
	
	private JCheckBox fechaelaboracionjCheckBoxA = null;
	
	private JLabel codigoMjLabelA = null;

	private JLabel formatoMjLabelA = null;

	private JLabel tipoMjLabelA = null;

	public DefaultTableCellRenderer colorRenderer = new DefaultTableCellRenderer();  //  @jve:decl-index=0:

	private JPanel jPanelBuscarA = null;

	private JLabel formatoBjLabelA = null;

	private JLabel tipoBjLabelA = null;

	private JComboBox formatojComboBoxA = null;

	private JComboBox tipojComboBoxA = null;

	private JCalendar fechBjCalendarA = null;

	private JLabel logojLabelA = null;

	private DefaultComboBoxModel tipodefaultComboBoxModelA = null;  //  @jve:decl-index=0:visual-constraint="1315,266"

	private DefaultComboBoxModel formatodefaultComboBoxModelA = null;  //  @jve:decl-index=0:visual-constraint="1105,76"

	private JButton buscarjButtonA = null;

	private JLabel productoPjLabelA = null;
	
	private JComboBox productojComboBoxA = null;

	private DefaultComboBoxModel productodefaultComboBoxModelA = null;  //  @jve:decl-index=0:visual-constraint="1100,110"
	
	Date dateActual = null;
//	-------------------------------------------------- DatosPendientesRevision ----------------------------------------------------------------------------
	private JPanel jContentPaneRevision = null;  //  @jve:decl-index=0:visual-constraint="1248,339"

	private JLabel codigojLabelR = null;

	private JLabel formatojLabelR = null;

	private JLabel cantidadUnidadesjLabelR = null;

	private JLabel tipojLabelR = null;

	private JLabel fechaRevisionjLabel = null;

	private JLabel codigoMjLabelR = null;

	private JLabel formatoMjLabelR = null;

	private JLabel tipoMjLabelR = null;

	private JCalendar fechajCalendarR = null;

	private JPanel datosjPanelR = null;

	private JSeparator jSeparatorR = null;

	private JScrollPane datosRevisionjScrollPane = null;

	private JTable datosRevisionjTable = null;

	private DefaultTableModel datosRevisiondefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1101,145"

	private JButton revisionjButton = null;

	private JButton modificarjButtonR = null;

	private JTextField cantidadUnidadesjTextFieldR = null;

	private JLabel jLabelR = null;

	private JLabel logojLabelR = null;
	
	private JPanel jPanelBuscarR = null;

	private JLabel formatoBjLabelR = null;

	private JLabel tipoBjLabelR = null;

	private JLabel nombrePjLabelR = null;

	private JComboBox formatojComboBoxR = null;

	private JComboBox tipojComboBoxR = null;

	private JComboBox productojComboBoxR = null;

	private JCalendar fechBjCalendarR = null;
	
	private DefaultComboBoxModel formatodefaultComboBoxModelR = null;  //  @jve:decl-index=0:visual-constraint="1102,183"
	
	private DefaultComboBoxModel tipodefaultComboBoxModelR = null;  //  @jve:decl-index=0:visual-constraint="1105,219"
	
	private DefaultComboBoxModel productodefaultComboBoxModelR = null;  //  @jve:decl-index=0:visual-constraint="1097,266"
	
	private JButton buscarjButtonR = null;
	
	private JCheckBox fechaelaboracionjCheckBoxR = null;
	
//-------------------------------------------------- DatosPendientesEtiquetado --------------------------------------------------------------------------
	private JPanel jContentPaneEtiquetado = null;

	private JLabel nombrejLabelE = null;

	private JLabel formatojLabelE = null;

	private JLabel cantidadUjLabelE = null;

	private JLabel tipojLabelE = null;

	private JLabel codigoMjLabelE = null;

	private JLabel formatoMjLabelE = null;

	private JLabel cantidadjLabelE = null;

	private JLabel tipoMjLabelE = null;

	private JLabel fechajLabelE = null;
	
	private JScrollPane datosPEjScrollPane = null;

	private JTable datosPEjTable = null;
	
	private DefaultTableModel datosPEdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1296,87"

	private JButton modificarjButtonE = null;  //  @jve:decl-index=0:
	
	private JButton etiquetadojButton = null;  //  @jve:decl-index=0:
	
	private JCalendar fechajCalendarE = null;

	private JLabel logojLabelE = null;

	private JButton crearjButtonE = null;

	private JPanel jPanelBuscarE = null;

	private JLabel formatoBjLabelE = null;

	private JLabel tipoBjLabelE = null;

	private JLabel nombrePjLabelE = null;

	private JComboBox formatojComboBoxE = null;

	private JComboBox tipojComboBoxE = null;

	private JComboBox productojComboBoxE = null;

	private JCalendar fechBjCalendarE = null;
	
	private DefaultComboBoxModel tipodefaultComboBoxModelE = null;  //  @jve:decl-index=0:visual-constraint="1305,126"

	private DefaultComboBoxModel formatodefaultComboBoxModelE = null;  //  @jve:decl-index=0:visual-constraint="1284,9"

	private DefaultComboBoxModel productodefaultComboBoxModelE = null;  //  @jve:decl-index=0:visual-constraint="1289,49"
	
	private JButton buscarjButtonE = null;
	
	private JCheckBox fechaelaboracionjCheckBoxE = null;
//-------------------------------------------------- DatosPendientesEnvase ------------------------------------------------------------------------------
	private JPanel jContentPaneEnvase = null;  //  @jve:decl-index=0:visual-constraint="1145,493"

	private JLabel codigojLabelEN = null;

	private JLabel formatojLabelEN = null;

	private JLabel cantidadUnidadesjLabelEN  = null;

	private JLabel tipojLabelEN  = null;

	private JLabel fechajLabelEN = null;

	private JLabel codigoMjLabelEN  = null;

	private JLabel formatoMjLabelEN  = null;

	private JLabel tipoMjLabelEN  = null;

	private JCalendar fechajCalendarEN  = null;

	private JPanel datosjPanelEN  = null;

	private JSeparator jSeparatorEN  = null;

	private JScrollPane datosjScrollPaneEN  = null;

	private JTable datosjTableEN  = null;

	private DefaultTableModel datosdefaultTableModelEN  = null;  //  @jve:decl-index=0:visual-constraint="1321,172"

	private JButton envasejButton = null;

	private JButton modificarjButtonEN  = null;

	private JTextField cantidadUnidadesjTextFieldEN  = null;

	private JLabel jLabelEN  = null;

	private JLabel logojLabelEN  = null;
	
	private JPanel jPanelBuscarEN  = null;

	private JLabel formatoBjLabelEN  = null;

	private JLabel tipoBjLabelEN  = null;

	private JLabel nombrePjLabelEN  = null;

	private JComboBox formatojComboBoxEN  = null;

	private JComboBox tipojComboBoxEN  = null;

	private JComboBox productojComboBoxEN  = null;

	private JCalendar fechBjCalendarEN  = null;
	
	private DefaultComboBoxModel tipodefaultComboBoxModelEN  = null;  //  @jve:decl-index=0:visual-constraint="1323,213"

	private DefaultComboBoxModel formatodefaultComboBoxModelEN  = null;  //  @jve:decl-index=0:visual-constraint="1505,180"

	private DefaultComboBoxModel productodefaultComboBoxModelEN  = null;  //  @jve:decl-index=0:visual-constraint="1511,218"
	
	private JButton buscarjButtonEN  = null;
	
	private JCheckBox fechaelaboracionjCheckBoxEN  = null;
//-------------------------------------------------- DatosPendientesAF ----------------------------------------------------------------------------------
	private JPanel jContentPaneAF = null;  //  @jve:decl-index=0:visual-constraint="1145,357"

	private JLabel codigojLabelAF = null;

	private JLabel formatojLabelAF = null;

	private JLabel cantidadUnidadesjLabelAF = null;

	private JLabel tipojLabelAF = null;

	private JLabel fechaAFjLabel = null;

	private JLabel codigoMjLabelAF = null;

	private JLabel formatoMjLabelAF = null;

	private JLabel tipoMjLabelAF = null;

	private JCalendar fechaAFjCalendar = null;

	private JPanel datosjPanelAF = null;

	private JSeparator jSeparatorAF = null;

	private JScrollPane datosAFjScrollPane = null;

	private JTable datosAFjTable = null;

	private DefaultTableModel datosAFdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1493,135"

	private JButton AFjButton = null;
	
    private JButton modificarjButtonAF = null;

	private JLabel jLabelAF = null;

	private JLabel logojLabelAF = null;
	
	private JPanel jPanelBuscarAF = null;

	private JLabel formatoBjLabelAF = null;

	private JLabel tipoBjLabelAF = null;

	private JLabel nombrePjLabelAF = null;

	private JComboBox formatojComboBoxAF = null;

	private JComboBox tipojComboBoxAF = null;

	private JComboBox productojComboBoxAF = null;

	private JCalendar fechBjCalendarAF = null;
	
	private DefaultComboBoxModel tipodefaultComboBoxModelAF = null;  //  @jve:decl-index=0:visual-constraint="1488,18"

	private DefaultComboBoxModel formatodefaultComboBoxModelAF = null;  //  @jve:decl-index=0:visual-constraint="1489,58"

	private DefaultComboBoxModel productodefaultComboBoxModelAF = null;  //  @jve:decl-index=0:visual-constraint="1489,93"
	
	private DefaultComboBoxModel defaultComboBoxModelRechazarA = null;  //  @jve:decl-index=0:visual-constraint="1489,58"
	
	private DefaultComboBoxModel defaultComboBoxModelRechazarR = null;  //  @jve:decl-index=0:visual-constraint="1489,58"
	
	private DefaultComboBoxModel defaultComboBoxModelRechazarE = null;  //  @jve:decl-index=0:visual-constraint="1489,58"
	
	private DefaultComboBoxModel defaultComboBoxModelRechazarEn = null;  //  @jve:decl-index=0:visual-constraint="1489,58"
	
	private DefaultComboBoxModel defaultComboBoxModelRechazarAF = null;  //  @jve:decl-index=0:visual-constraint="1489,58"
	
	private JButton buscarjButtonAF = null;
	
	private JCheckBox fechaelaboracionjCheckBoxAF = null;

	private JLabel fechanjLabel = null;

	private GregorianCalendar calendar1 = null;

	private JButton jButtonReporteA = null;

	private JButton jButtonReporteRevision = null;

	private JButton jButtonReporteE = null;

	private JButton jButtonReporteEnv = null;

	private JButton jButtonReporteAF = null;

	private JButton EliminarRjButton = null;

	private JButton EliminarEjButton = null;

	private JButton EliminarEnjButton = null;

	private JButton EliminarAjButton = null;

	private JLabel jLabelRechazarA = null;

	private JLabel jLabelRechazarR = null;

	private JLabel jLabelRechazarE = null;

	private JLabel jLabelRechazarEn = null;

	private JLabel jLabelRechazar = null;

	private JComboBox jComboBoxRechazarA = null;
	
	private JComboBox jComboBoxRechazarR = null;
	
	private JComboBox jComboBoxRechazarE = null;
	
	private JComboBox jComboBoxRechazarEn = null;
	
	private JComboBox jComboBoxRechazarAF = null;

	private DatosPendientesEtapas() {
		super();
		initialize();
	}
	
	public static DatosPendientesEtapas getDatosPendientesEtapas() {
		if (datos == null) {
			datos = new DatosPendientesEtapas();
			datos.setSize(new Dimension(1078, 653));
		}
		return datos;
	}

	private void initialize() {
		ConnectionBD.testConnect();
		loguiado = Control.getSessionUser();
		this.setSize(1077, 657);
		this.setContentPane(getJContentPane());
		this.setTitle("Datos Pendientes");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		this.setResizable(false);
		GregorianCalendar calendar = new GregorianCalendar();
		int i = calendar.get(Calendar.DATE);
		int k = calendar.get(Calendar.MONTH);
		int p = calendar.get(Calendar.YEAR);
		p = p - 1900;
		Date date = new Date(p,k,i);
		fechaActual = date;
		try {
			listTrazas =(ArrayList<Traza>)Traza_Servicio.getAllTraza();
			ActualizarInterfazAnalisis();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
		    fecha_actual = "" + getCalendar1().get(Calendar.DATE) + "/" + (getCalendar1().get(Calendar.MONTH) + 1) + "/" + getCalendar1().get(Calendar.YEAR);
			fechanjLabel = new JLabel();
			fechanjLabel.setBounds(new Rectangle(994, 2, 66, 17));
			fechanjLabel.setText(fecha_actual);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPane(), null);
			jContentPane.add(fechanjLabel, null);
		}
		return jContentPane;
	}

	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(9, 2, 1052, 616));
			jTabbedPane.add("Pendientes de Aprobación  Analítica", getJContentPaneAnalisis());
			jTabbedPane.add("Pendientes de Revisión", getJContentPaneRevision());
			jTabbedPane.add("Pendientes de Etiquetado", getJContentPaneEtiquetado());
			jTabbedPane.add("Pendientes de Envase", getJContentPaneEN());
			jTabbedPane.add("Pendientes de Aprobación  Final", getJContentPaneAF());
			jTabbedPane.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					listFiltros.clear();
					listFormatos.clear();
					listProducto.clear();
					listLote.clear();
					if(jTabbedPane.getSelectedIndex() == 0){
						ActualizarInterfazAnalisis();
					}
					if(jTabbedPane.getSelectedIndex() == 1){
						ActualizarInterfazRevision();
					}
					if(jTabbedPane.getSelectedIndex() == 2){
						ActualizarInterfazEtiquetado();
					}
					if(jTabbedPane.getSelectedIndex() == 3){
						ActualizarInterfazEnvase();
					}
					if(jTabbedPane.getSelectedIndex() == 4){
						ActualizarInterfazAF();
					}
					
				}
			});
		}
		return jTabbedPane;
	}
	public String Nombre_Prod (Lote lote){
		int j=0;
		String nombre= null;
		boolean found= false;
		while (j < listProducto.size()&& !found){
			if (listProducto.get(j).getCod_producto().equals(lote.getProducto()))
			 {
			    nombre = listProducto.get(j).getNombre();
				found = true;
			 }
			else j++;
		     } 
			 if (found == true){
				 return nombre;
	    }
		return null;
	}

	// devuelve el codigo del producto dado su nombre
	public String Cod_Producto (String nombre){
		String Cod_producto = null;
    	try {
    		int j = 0;
    		boolean found = false;
    		listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
			while (found == false && j < listProducto.size()) {
				if(listProducto.get(j).getNombre().equals(nombre)){
					Cod_producto =listProducto.get(j).getCod_producto();
					found = true;
					}  
				   j++;
			}
    	}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	return Cod_producto;
}
	public Traza FindTraza (String usuario, Date fecha){
		int i=0;
		boolean found= false;
		while (i < listTrazas.size()&& !found){
			if (listTrazas.get(i).getUsuario().equals(usuario)&& (listTrazas.get(i).getFecha().equals(fecha)) )
			 {
				found = true;
			 }
			else i++;
		     } 
			 if (found == true){
				 return listTrazas.get(i);
	    }
		return null;
	}
	// devuelve true o false si el lote esta como rechazado
	public boolean Rechazado (Lote lote){
		String Cod_producto = lote.getCod();
		boolean found = false;
    	try {
    		int j = 0;
    		listLotesRechazados = (ArrayList<Lote>) Lote_Service.getAllLoteRechazado();
			while (found == false && j < listLotesRechazados.size()) {
				if(listLotesRechazados.get(j).getCod().equals(Cod_producto)){
					found = true;
					}  
				   j++;
			}
    	}
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return found;
}
	//devuelve la cantidad de dias que hay entre una fecha y otra
	 public static long daysBetween(Date startDate, Date endDate) {
	        Calendar startCalendar = Calendar.getInstance();
	        startCalendar.setTime(startDate);
	        Calendar endCalendar = Calendar.getInstance();
	        endCalendar.setTime(endDate);
	        Calendar startCal = (Calendar)startCalendar.clone();
	        long daysBetween = 0;
	        while (startCal.before(endCalendar)) {
	            startCal.add(Calendar.DAY_OF_MONTH, 1);
	            daysBetween++;
	        }
	        return daysBetween;
	    }
	 private List<String> Formatos(String cod_pro){
			List<String> lista = new ArrayList<String>();
			try {
				listProdForm = (ArrayList<Formato_Producto>)Prod_Services.getAllFormatoProd();
					for (int i = 0; i < listProdForm.size(); i++){
						if((listProdForm.get(i).getCod_producto()).equals(cod_pro)){
							lista.add(Lote_Service.getFormato_Nom (listProdForm.get(i).getId_formato()));
						}
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  //  @jve:decl-index=0:
			
			return lista;
		}
//	-------------------------------------------------- DatosPendientesAnalisis ----------------------------------------------------------------------------
	
	public void ActualizarInterfazAnalisis(){
		ConnectionBD.testConnect();
		tipojComboBoxA.setSelectedIndex(0);
		productojComboBoxA.setSelectedIndex(0);
		formatojComboBoxA.setSelectedIndex(0);
		modificarjButtonA.setEnabled(false);
		fechBjCalendarA.setEnabled(false);
		jComboBoxRechazarA.setEnabled(false);
		listLote.clear();
		listFiltros.clear();
		GregorianCalendar calendar = (GregorianCalendar) fechjCalendarA.getCalendar();
		int l = calendar.get(Calendar.DATE);
		int k = calendar.get(Calendar.MONTH);
		int p = calendar.get(Calendar.YEAR);
		p = p - 1900;
		Date date = new Date(p,k,l);
		dateActual=date; 
		LlenarTablaAnalisis();
	}
	
	public void LlenarTablaAnalisis (){
		try {
			for (int w = datosPAdefaultTableModel.getRowCount(); w > 0 ; w--) {
				datosPAdefaultTableModel.removeRow(w - 1);
			}
			listLoteAnalisis.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
			Date dateA = dateActual;
			Date dateL = null;
			long cantD = 0;
			String producto;
			for (int i = 0; i < listLote.size(); i++) {
				producto = Nombre_Prod(listLote.get(i)); 
				dateL = listLote.get(i).getFecha_elaboracion();
                cantD = daysBetween (dateL,dateA);
				if(cantD >= 18){
					if((listLote.get(i).getCod_estado() == 1) &&(listLote.get(i).isEstado_analisis() == false)){
						if (listLote.get(i).getCodigo_tipo_lote() == 1){
							if(Rechazado(listLote.get(i))== true){
								getDatosPAdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), cantD +" días pendientes"});
							}
							else{
							getDatosPAdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), cantD +" días pendientes"});
							}
						}
						else {
							getDatosPAdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), cantD +" días pendientes"});

						}
						listLoteAnalisis.add(listLote.get(i));
					}
				}
				else
				{
					if((listLote.get(i).getCod_estado() == 1) &&(listLote.get(i).isEstado_analisis() == false)){
						if (listLote.get(i).getCodigo_tipo_lote() == 0){
							getDatosPAdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(),listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_analisis()});

						}
						else {
							getDatosPAdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(),listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_analisis()});

						}
						listLoteAnalisis.add(listLote.get(i));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datosPAjTable.clearSelection();
		codigoMjLabelA.setText("");
		tipoMjLabelA.setText("");
		formatoMjLabelA.setText("");
		cantidadjLabelA.setText("");
		modificarjButtonA.setEnabled(false);
	}

	private JPanel getJContentPaneAnalisis() {
		if (jContentPaneAnalisis == null) {
			logojLabelA = new JLabel();
			logojLabelA.setBounds(new Rectangle(962, 0, 85, 67));
			logojLabelA.setText("");
			logojLabelA.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			logojLabelA.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			fechajLabelA = new JLabel();
			fechajLabelA.setText("Fecha de Análisis:");
			fechajLabelA.setBounds(new Rectangle(284, 20, 102, 16));
			tipojLabelA = new JLabel();
			tipojLabelA.setText("Destino:");
			tipojLabelA.setBounds(new Rectangle(87, 120, 49, 16));
			cantidadjLabelA = new JLabel();
			cantidadjLabelA.setText("");
			cantidadjLabelA.setBounds(new Rectangle(148, 86, 123, 19));
			cantunidjLabelA = new JLabel();
			cantunidjLabelA.setText("Cantidad de Unidades:");
			cantunidjLabelA.setBounds(new Rectangle(10, 87, 126, 16));
			formatojLabelA = new JLabel();
			formatojLabelA.setText("Formato:");
			formatojLabelA.setBounds(new Rectangle(86, 54, 50, 16));
			codigojLabelA = new JLabel();
			codigojLabelA.setText("Código:");
			codigojLabelA.setBounds(new Rectangle(94, 21, 42, 16));
			jContentPaneAnalisis = new JPanel();
			jContentPaneAnalisis.setLayout(null);
			jContentPaneAnalisis.setLocation(new Point(3, 23));
			jContentPaneAnalisis.setSize(new Dimension(1049, 635));
			jContentPaneAnalisis.add(getJSeparatorA(), null);
			jContentPaneAnalisis.add(getDatosPAjScrollPane(), null);
			jContentPaneAnalisis.add(getModificarjButtonA(), null);
			jContentPaneAnalisis.add(getAnalisisjButtonA(), null);
			jContentPaneAnalisis.add(getJPanelA(), null);
			jContentPaneAnalisis.add(getJPanelBuscarA(), null);
			jContentPaneAnalisis.add(logojLabelA, null);
			jContentPaneAnalisis.add(getJButtonReporteA(), null);
			jContentPaneAnalisis.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("pendinte revision"); // TODO Auto-generated Event stub mouseClicked()
					datosPAjTable.clearSelection();
					codigoMjLabelA.setText("");
					tipoMjLabelA.setText("");
					formatoMjLabelA.setText("");
					cantidadjLabelA.setText("");
					modificarjButtonA.setEnabled(false);
					jComboBoxRechazarA.setEnabled(false);
					
				}
			});
		}
		return jContentPaneAnalisis;
	}

	private JSeparator getJSeparatorA() {
		if (jSeparatorA == null) {
			jSeparatorA = new JSeparator();
			jSeparatorA.setBounds(new Rectangle(16, 234, 1013, 8));
		}
		return jSeparatorA;
	}

	private JScrollPane getDatosPAjScrollPane() {
		if (datosPAjScrollPane == null) {
			datosPAjScrollPane = new JScrollPane();
			datosPAjScrollPane.setBounds(new Rectangle(10, 245, 1012, 299));
			datosPAjScrollPane.setViewportView(getDatosPAjTable());
		}
		return datosPAjScrollPane;
	}

	private JTable getDatosPAjTable() {
		if (datosPAjTable == null) {
			datosPAjTable = new JTable();
			datosPAjTable.setFont(new Font("Dialog", Font.PLAIN, 12));
			datosPAjTable.setModel(getDatosPAdefaultTableModel());
			datosPAjTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
			datosPAjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosPAjTable.getSelectedRow();
					Lote lote = listLoteAnalisis.get(pos);
					modificarjButtonA.setEnabled(true);
					jComboBoxRechazarA.setEnabled(true);
					codigoMjLabelA.setText(lote.getCod());
					if(lote.getCodigo_tipo_lote() == 1)
						tipoMjLabelA.setText("Nacional");
					else tipoMjLabelA.setText("Exportación");
					if(Rechazado(lote)== true){
						jComboBoxRechazarA.setSelectedIndex(0);
					}
					else{
						jComboBoxRechazarA.setSelectedIndex(1);
					}
					formatoMjLabelA.setText(String.valueOf(lote.getFormato()));
					cantidadjLabelA.setText(String.valueOf(lote.getCant_unidades()));
				}
			});
		}
		return datosPAjTable;
	}

	private JButton getModificarjButtonA() {
		if (modificarjButtonA == null) {
			modificarjButtonA = new JButton();
			modificarjButtonA.setBounds(new Rectangle(907, 553, 116, 26));
			modificarjButtonA.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButtonA.setText("Modificar");
			modificarjButtonA.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosPAjTable.getSelectedRow();
				        try {
				        	Lote lote = Lote_Service.getLoteVO(listLoteAnalisis.get(pos).getCod());
							String cod_old = lote.getCod();
							GregorianCalendar calendar = (GregorianCalendar) fechjCalendarA.getCalendar();
							int i = calendar.get(Calendar.DATE);
							int k = calendar.get(Calendar.MONTH);
							int p = calendar.get(Calendar.YEAR);
							p = p - 1900;
							Date date = new Date(p,k,i);
							if (date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())){
					        	 lote.setFecha_analisis(date);
						        }
						        else{
						        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
						        }
							Lote_Service.updateLote(lote,cod_old);
							if(jComboBoxRechazarA.getSelectedIndex()==0){
								lote.setFecha(date);
								Lote_Service.createLoteRechazado(lote);
							}
							else{
								Lote_Service.deleteLoteRechazado(cod_old);
							}
							LlenarTablaAnalisis();
							Traza traza = new Traza();
							String operacion = "U-" +"Tabla: lote_produccion" + ", Lote:"+ lote.getCod();;
							if (Traza_Servicio.existeTraza(loguiado.getNombre(), fechaActual)== true){
								traza= FindTraza(loguiado.getNombre(), fechaActual);
								traza.setOperacion(traza.getOperacion()+ "/ "+ operacion);
								traza.setUsuario(loguiado.getNombre());
								traza.setFecha(fechaActual);
								Traza_Servicio.updateTraza(traza, loguiado.getNombre(), fechaActual);
							}
							else{
								traza.setOperacion(operacion);
								traza.setUsuario(loguiado.getNombre());
								traza.setFecha(fechaActual);
								Traza_Servicio.createTraza(traza);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						codigoMjLabelA.setText("");
						tipoMjLabelA.setText("");
						formatoMjLabelA.setText("");
						cantidadjLabelA.setText("");
						modificarjButtonA.setEnabled(false);
						datosPAjTable.clearSelection();
						jComboBoxRechazarA.setEnabled(false);
			}
			});
		}
		return modificarjButtonA;
	}

	private JButton getAnalisisjButtonA() {
		if (analisisjButtonA == null) {
			analisisjButtonA = new JButton();
			analisisjButtonA.setBounds(new Rectangle(11, 553, 169, 26));
			analisisjButtonA.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			analisisjButtonA.setText("Análisis Completado");
			analisisjButtonA.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelA.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int pos = datosPAjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteAnalisis.get(pos).getCod());
						String cod_old = lote.getCod();
						GregorianCalendar calendar = (GregorianCalendar) fechjCalendarA.getCalendar();
						int l = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,l);
				        if(Rechazado(listLoteAnalisis.get(pos))== false){
						 if (date.after(lote.getFecha_elaboracion()) || date.equals(lote.getFecha_elaboracion())){
				        	lote.setFecha_analisis(date);
				        	lote.setEstado_analisis(true);
				        	 if(lote.isEstado_analisis() == true && lote.isEstado_revision() == true){
						        	lote.setCod_estado(3);}
						        else {lote.setCod_estado(2);}
						        Lote_Service.updateLote(lote,cod_old);
						        LlenarTablaAnalisis();
					        }
					        else{
					        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida, rectifíquela!", "Error", JOptionPane.ERROR_MESSAGE);
					        }
						 }
				        else{
				        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				       
						@SuppressWarnings("unused")
						Producto producto = Prod_Services.getProdVO(lote.getProducto());
			        } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        codigoMjLabelA.setText("");
					tipoMjLabelA.setText("");
					formatoMjLabelA.setText("");
					cantidadjLabelA.setText("");
					modificarjButtonA.setEnabled(false);
					jComboBoxRechazarA.setEnabled(false);
					datosPAjTable.clearSelection();
					Lote_Visual.getLote_Visual().LlenarTablaLote();
					}
				}
			});
		}
		return analisisjButtonA;
	}

	private DefaultTableModel getDatosPAdefaultTableModel() {
		if (datosPAdefaultTableModel == null) {
			datosPAdefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Destino", "Cantidad Unidades", "D/A","Fecha Elaboración", "Fecha Análisis"}, 0);
		}
		return datosPAdefaultTableModel;
	}

	private JCalendar getFechjCalendarA() {
		if (fechjCalendarA == null) {
			fechjCalendarA = new JCalendar();
			fechjCalendarA.setBounds(new Rectangle(285, 45, 196, 153));
		}
		return fechjCalendarA;
	}

	private JPanel getJPanelA() {
		if (jPanelA == null) {
			jLabelRechazarA = new JLabel();
			jLabelRechazarA.setBounds(new Rectangle(67, 155, 69, 14));
			jLabelRechazarA.setText("Rechazado:");
			tipoMjLabelA = new JLabel();
			tipoMjLabelA.setText("");
			tipoMjLabelA.setSize(new Dimension(123, 19));
			tipoMjLabelA.setLocation(new Point(148, 119));
			formatoMjLabelA = new JLabel();
			formatoMjLabelA.setText("");
			formatoMjLabelA.setSize(new Dimension(123, 19));
			formatoMjLabelA.setLocation(new Point(148, 53));
			codigoMjLabelA = new JLabel();
			codigoMjLabelA.setText("");
			codigoMjLabelA.setSize(new Dimension(123, 19));
			codigoMjLabelA.setLocation(new Point(148, 20));
			jPanelA = new JPanel();
			jPanelA.setLayout(null);
			jPanelA.setBounds(new Rectangle(18, 16, 495, 209));
			jPanelA.setBorder(BorderFactory.createTitledBorder(null, "Datos Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelA.add(codigojLabelA, null);
			jPanelA.add(formatojLabelA, null);
			jPanelA.add(cantunidjLabelA, null);
			jPanelA.add(cantidadjLabelA, null);
			jPanelA.add(tipojLabelA, null);
			jPanelA.add(fechajLabelA, null);
			jPanelA.add(getFechjCalendarA(), null);
			jPanelA.add(codigoMjLabelA, null);
			jPanelA.add(formatoMjLabelA, null);
			jPanelA.add(tipoMjLabelA, null);
			jPanelA.add(jLabelRechazarA, null);
			jPanelA.add(getJComboBoxRechazarA(), null);
		}
		return jPanelA;
	}

	private JPanel getJPanelBuscarA() {
		if (jPanelBuscarA == null) {
			productoPjLabelA = new JLabel();
			productoPjLabelA.setBounds(new Rectangle(15, 30, 58, 16));
			productoPjLabelA.setText("Producto:");
			tipoBjLabelA = new JLabel();
			tipoBjLabelA.setBounds(new Rectangle(18, 90, 53, 16));
			tipoBjLabelA.setText("Destino:");
			formatoBjLabelA = new JLabel();
			formatoBjLabelA.setBounds(new Rectangle(18, 58, 52, 16));
			formatoBjLabelA.setText("Formato:");
			jPanelBuscarA = new JPanel();
			jPanelBuscarA.setLayout(null);
			jPanelBuscarA.setBounds(new Rectangle(512, 16, 438, 209));
			jPanelBuscarA.setBorder(BorderFactory.createTitledBorder(null, "Buscar Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelBuscarA.add(formatoBjLabelA, null);
			jPanelBuscarA.add(tipoBjLabelA, null);
			jPanelBuscarA.add(getFormatojComboBoxA(), null);
			jPanelBuscarA.add(getTipojComboBoxA(), null);
			jPanelBuscarA.add(getFechBjCalendarA(), null);
			jPanelBuscarA.add(getBuscarjButtonA(), null);
			jPanelBuscarA.add(getFechaElaboracionjCheckBoxA(), null);
			jPanelBuscarA.add(productoPjLabelA, null);
			jPanelBuscarA.add(getProductojComboBoxA(), null);
			
		}
		return jPanelBuscarA;
	}

	private JComboBox getFormatojComboBoxA() {
		if (formatojComboBoxA == null) {
			formatojComboBoxA = new JComboBox();
			formatojComboBoxA.setBounds(new Rectangle(84, 58, 136, 20));
			formatojComboBoxA.setModel(getFormatodefaultComboBoxModelA());
		}
		return formatojComboBoxA;
	}

	private JComboBox getTipojComboBoxA() {
		if (tipojComboBoxA == null) {
			tipojComboBoxA = new JComboBox();
			tipojComboBoxA.setBounds(new Rectangle(84, 89, 136, 20));
			tipojComboBoxA.setModel(getTipodefaultComboBoxModelA());
		}
		return tipojComboBoxA;
	}
	
	private JComboBox getProductojComboBoxA() {
		if (productojComboBoxA == null) {
			productojComboBoxA = new JComboBox();
			productojComboBoxA.setBounds(new Rectangle(84, 27, 136, 20));
			productojComboBoxA.setModel(getProductodefaultComboBoxModelA());
		    productojComboBoxA.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String producto = null;
					String codigo = null;
					List<String> listaf = new ArrayList<String>();
					if(productojComboBoxA.getSelectedIndex() != 0){
					    producto = (String) productojComboBoxA.getSelectedItem();
					    codigo = Cod_Producto(producto);
						formatodefaultComboBoxModelA.removeAllElements();
						listaf.clear();
					    listaf = Formatos(codigo);
					    for(int j=0; j<listaf.size(); j++){
					      formatodefaultComboBoxModelA.addElement(listaf.get(j));
						 }	
					   }
					else
						if(productojComboBoxA.getSelectedItem().equals("<Seleccione>")){
						formatodefaultComboBoxModelA.removeAllElements();
						formatodefaultComboBoxModelA.addElement("<Seleccione>");
						try {
							listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
							for (int i = 0; i < listFormatos.size(); i++) {
								formatodefaultComboBoxModelA.addElement(listFormatos.get(i));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return productojComboBoxA;
	}

	private JCalendar getFechBjCalendarA() {
		if (fechBjCalendarA == null) {
			fechBjCalendarA = new JCalendar();
			fechBjCalendarA.setBounds(new Rectangle(232, 49, 193, 151));
		}
		return fechBjCalendarA;
	}

	private DefaultComboBoxModel getTipodefaultComboBoxModelA() {
		if (tipodefaultComboBoxModelA == null) {
			tipodefaultComboBoxModelA = new DefaultComboBoxModel();
			tipodefaultComboBoxModelA.addElement("<Seleccione>");
			tipodefaultComboBoxModelA.addElement("Nacional");
			tipodefaultComboBoxModelA.addElement("Exportación");
		}
		return tipodefaultComboBoxModelA;
	}

	private DefaultComboBoxModel getFormatodefaultComboBoxModelA() {
		if (formatodefaultComboBoxModelA == null) {
			formatodefaultComboBoxModelA = new DefaultComboBoxModel();
			formatodefaultComboBoxModelA.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModelA.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModelA;
	}

	private JCheckBox getFechaElaboracionjCheckBoxA() {
		if (fechaelaboracionjCheckBoxA == null) {
			fechaelaboracionjCheckBoxA = new JCheckBox();
			fechaelaboracionjCheckBoxA.setText("Fecha de Elaboración:");
			fechaelaboracionjCheckBoxA.setBounds(new Rectangle(231, 19, 155, 21));
			fechaelaboracionjCheckBoxA.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechaelaboracionjCheckBoxA.isSelected())
						fechBjCalendarA.setEnabled(true);
					else fechBjCalendarA.setEnabled(false);
				}
			});
		}
		return fechaelaboracionjCheckBoxA;
	}

	private JButton getBuscarjButtonA() {
		if (buscarjButtonA == null) {
			buscarjButtonA = new JButton();
			buscarjButtonA.setBounds(new Rectangle(15, 134, 37, 33));
			buscarjButtonA.setIcon(new ImageIcon(getClass().getResource("/imagenes/search12.jpg")));
			buscarjButtonA.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {		
					String Nomb_producto;
					//si no hay seleccionado nada
					if(formatojComboBoxA.getSelectedIndex() == 0 && tipojComboBoxA.getSelectedIndex() == 0 && productojComboBoxA.getSelectedIndex() == 0 && !fechaelaboracionjCheckBoxA.isSelected())
					  {
						listFiltros.clear();
						LlenarTablaAnalisis();
					}
					else{
					if(listFiltros.isEmpty()){
						for (int w = datosPAdefaultTableModel.getRowCount(); w > 0 ; w--) {
							datosPAdefaultTableModel.removeRow(w - 1);
							
						}
						    String formato = null;
							int tipo = 0;
							String producto = null;
							if(formatojComboBoxA.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxA.getSelectedItem();}
							if(tipojComboBoxA.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxA.getSelectedIndex();}
							if(productojComboBoxA.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxA.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxA.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarA.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltros(1, formato, tipo, producto,dateElaboracion);
								for (int i = 0; i < listFiltros.size(); i++) {
										Nomb_producto= Nombre_Prod(listFiltros.get(i));
										if(listFiltros.get(i).getCodigo_tipo_lote() == 1){
											getDatosPAdefaultTableModel().addRow(new Object[]{listFiltros.get(i),Nomb_producto, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_analisis()});
											}
										else {
											getDatosPAdefaultTableModel().addRow(new Object[]{listFiltros.get(i),Nomb_producto, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_analisis()});
											}
									}
									}	
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					  }
					else {
						    String formato = null;
						    int tipo = 0;
						    String producto = null;
							if(formatojComboBoxA.getSelectedIndex() != 0)
								formato = (String)formatojComboBoxA.getSelectedItem();
							for (int w =  datosPAdefaultTableModel.getRowCount(); w > 0 ; w--) {
								datosPAdefaultTableModel.removeRow(w - 1);
							}
							listFiltros.clear();
						
							if(tipojComboBoxA.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxA.getSelectedIndex();}
							if(productojComboBoxA.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxA.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxA.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarA.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
								try {
									listFiltros = Lote_Service.listFiltros(1, formato, tipo, producto, dateElaboracion);
									for (int j = 0; j < listFiltros.size(); j++) {
											Nomb_producto = Nombre_Prod(listLote.get(j));
												if(listFiltros.get(j).getCodigo_tipo_lote() == 1){
													getDatosPAdefaultTableModel().addRow(new Object[]{listFiltros.get(j),Nomb_producto, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(),listFiltros.get(j).getFecha_analisis()});
												}
												else {
													getDatosPAdefaultTableModel().addRow(new Object[]{listFiltros.get(j),Nomb_producto, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(),listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_analisis()});
												}
											}
										}
								   catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						}
					}
				}
			});
		}
		return buscarjButtonA;
	}

	private DefaultComboBoxModel getProductodefaultComboBoxModelA() {
		if (productodefaultComboBoxModelA == null) {
			productodefaultComboBoxModelA = new DefaultComboBoxModel();
			productodefaultComboBoxModelA.addElement("<Seleccione>");
			try {
				listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProducto.size(); i++) {
					productodefaultComboBoxModelA.addElement(listProducto.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModelA;
	}

	private JButton getJButtonReporteA() {
		if (jButtonReporteA == null) {
			jButtonReporteA = new JButton();
			jButtonReporteA.setBounds(new Rectangle(747, 553, 147, 26));
			jButtonReporteA.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			jButtonReporteA.setText("Generar Reporte");
			jButtonReporteA.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/*Reportes reportes = new Reportes();
					reportes.CargarReportesAnalisis();*/
					outputToEXL excel = new outputToEXL(addressA);
					excel.saveAnalisis(3);
				}
			});
		}
		return jButtonReporteA;
	}
	private JComboBox getJComboBoxRechazarA() {
		if (jComboBoxRechazarA == null) {
			jComboBoxRechazarA = new JComboBox();
			jComboBoxRechazarA.setBounds(new Rectangle(148, 150, 123, 19));
			jComboBoxRechazarA.setModel(getdefaultComboBoxModelRechazarA());
		}
		return jComboBoxRechazarA;
	}
	private DefaultComboBoxModel getdefaultComboBoxModelRechazarA() {
		if (defaultComboBoxModelRechazarA == null) {
			defaultComboBoxModelRechazarA = new DefaultComboBoxModel();
			defaultComboBoxModelRechazarA.addElement("Si");
			defaultComboBoxModelRechazarA.addElement("No");
		}
		return defaultComboBoxModelRechazarA;
	}
	
//	-------------------------------------------------- DatosPendientesRevision ----------------------------------------------------------------------------
	
	public void ActualizarInterfazRevision(){
		ConnectionBD.testConnect();
		formatojComboBoxR.setSelectedIndex(0);
		tipojComboBoxR.setSelectedIndex(0);
		productojComboBoxR.setSelectedIndex(0);
		jComboBoxRechazarR.setEnabled(false);
		cantidadUnidadesjTextFieldR.setEnabled(false);
		fechBjCalendarR.setEnabled(false);
		modificarjButtonR.setEnabled(false);
		EliminarRjButton.setEnabled(false);
		listLote.clear();
		listFiltros.clear();
		LlenarTablaRevision();
	}
	
	public void LlenarTablaRevision (){
		try {
			for (int w = datosRevisiondefaultTableModel.getRowCount(); w > 0 ; w--) {
				datosRevisiondefaultTableModel.removeRow(w - 1);
			}
			listLoteRevision.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
			String Nomb_producto;
			for (int i = 0; i < listLote.size(); i++) {
				Nomb_producto = Nombre_Prod(listLote.get(i));
				if((listLote.get(i).getCod_estado() == 1 || listLote.get(i).getCod_estado() == 2) && (listLote.get(i).isEstado_revision() == false)){
					if(listLote.get(i).getCodigo_tipo_lote() == 1 && listLote.get(i).isEstado_analisis() == true)
						getDatosRevisiondefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_revisado(), "SI"});
					if(listLote.get(i).getCodigo_tipo_lote() == 1 && listLote.get(i).isEstado_analisis() == false)
						getDatosRevisiondefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_revisado(), "NO"});
					if(listLote.get(i).getCodigo_tipo_lote() == 2 && listLote.get(i).isEstado_analisis() == true)
						getDatosRevisiondefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(),listLote.get(i).getFecha_revisado(), "SI"});
					if(listLote.get(i).getCodigo_tipo_lote() == 2 && listLote.get(i).isEstado_analisis() == false)
						getDatosRevisiondefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_revisado(), "NO"});
					
					listLoteRevision.add(listLote.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigoMjLabelR.setText("");
		tipoMjLabelR.setText("");
		formatoMjLabelR.setText("");
		cantidadUnidadesjTextFieldR.setText(null);
		modificarjButtonR.setEnabled(false);
		datosRevisionjTable.clearSelection();
	}

	private JPanel getJContentPaneRevision() {
		if (jContentPaneRevision == null) {
			logojLabelR = new JLabel();
			logojLabelR.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabelR.setLocation(new Point(962, 2));
			logojLabelR.setSize(new Dimension(84, 72));
			logojLabelR.setText("");
			tipoMjLabelR = new JLabel();
			tipoMjLabelR.setText("");
			tipoMjLabelR.setSize(new Dimension(123, 19));
			tipoMjLabelR.setLocation(new Point(153, 110));
			formatoMjLabelR = new JLabel();
			formatoMjLabelR.setText("");
			formatoMjLabelR.setSize(new Dimension(123, 19));
			formatoMjLabelR.setLocation(new Point(153, 48));
			codigoMjLabelR = new JLabel();
			codigoMjLabelR.setText("");
			codigoMjLabelR.setSize(new Dimension(123, 19));
			codigoMjLabelR.setLocation(new Point(153, 19));
			fechaRevisionjLabel = new JLabel();
			fechaRevisionjLabel.setText("Fecha de Revisión:");
			fechaRevisionjLabel.setBounds(new Rectangle(299, 18, 114, 18));
			tipojLabelR = new JLabel();
			tipojLabelR.setText("Destino:");
			tipojLabelR.setBounds(new Rectangle(86, 110, 46, 16));
			cantidadUnidadesjLabelR = new JLabel();
			cantidadUnidadesjLabelR.setText("Cantidad de Unidades:");
			cantidadUnidadesjLabelR.setBounds(new Rectangle(6, 82, 126, 16));
			formatojLabelR = new JLabel();
			formatojLabelR.setText("Formato:");
			formatojLabelR.setBounds(new Rectangle(82, 48, 50, 16));
			codigojLabelR = new JLabel();
			codigojLabelR.setText("Código:");
			codigojLabelR.setBounds(new Rectangle(90, 19, 42, 16));
			jContentPaneRevision = new JPanel();
			jContentPaneRevision.setLayout(null);
			jContentPaneRevision.setSize(new Dimension(1049, 635));
			jContentPaneRevision.add(getDatosjPanelR(), null);
			jContentPaneRevision.add(getJSeparatorR(), null);
			jContentPaneRevision.add(getDatosRevisionjScrollPane(), null);
			jContentPaneRevision.add(getRevisionjButton(), null);
			jContentPaneRevision.add(getModificarjButtonR(), null);
			jContentPaneRevision.add(logojLabelR, null);
			jContentPaneRevision.add(getJPanelBuscarR(), null);
			jContentPaneRevision.add(getJButtonReporteRevision(), null);
			jContentPaneRevision.add(getEliminarRjButton(), null);
			jContentPaneRevision.add(getDatosRevisionjScrollPane(), null);
			jContentPaneRevision.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					codigoMjLabelR.setText("");
					tipoMjLabelR.setText("");
					formatoMjLabelR.setText("");
					cantidadUnidadesjTextFieldR.setText(null);
					modificarjButtonR.setEnabled(false);
					EliminarRjButton.setEnabled(false);
					datosRevisionjTable.clearSelection();
					cantidadUnidadesjTextFieldR.setEnabled(false);
					jComboBoxRechazarR.setEnabled(false);
				}
			});
		}
		return jContentPaneRevision;
	}
	
	private JCalendar getFechajCalendarR() {
		if (fechajCalendarR == null) {
			fechajCalendarR = new JCalendar();
			fechajCalendarR.setBounds(new Rectangle(299, 43, 193, 148));
		}
		return fechajCalendarR;
	}

	private JPanel getDatosjPanelR() {
		if (datosjPanelR == null) {
			jLabelRechazarR = new JLabel();
			jLabelRechazarR.setBounds(new Rectangle(65, 140, 67, 16));
			jLabelRechazarR.setText("Rechazado:");
			jLabelR = new JLabel();
			jLabelR.setBounds(new Rectangle(1, 82, 10, 16));
			jLabelR.setText("*");
			jLabelR.setForeground(new Color(255, 51, 51));
			datosjPanelR = new JPanel();
			datosjPanelR.setLayout(null);
			datosjPanelR.setBorder(BorderFactory.createTitledBorder(null, "Datos Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			datosjPanelR.setBounds(new Rectangle(10, 14, 502, 205));
			datosjPanelR.add(codigojLabelR, null);
			datosjPanelR.add(formatojLabelR, null);
			datosjPanelR.add(codigoMjLabelR, null);
			datosjPanelR.add(formatoMjLabelR, null);
			datosjPanelR.add(cantidadUnidadesjLabelR, null);
			datosjPanelR.add(tipojLabelR, null);
			datosjPanelR.add(tipoMjLabelR, null);
			datosjPanelR.add(getFechajCalendarR(), null);
			datosjPanelR.add(getCantidadUnidadesjTextFieldR(), null);
			datosjPanelR.add(jLabelR, null);
			datosjPanelR.add(fechaRevisionjLabel, null);
			datosjPanelR.add(jLabelRechazarR, null);
			datosjPanelR.add(getJComboBoxRechazarR(), null);
		}
		return datosjPanelR;
	}
	
	private JPanel getJPanelBuscarR() {
		if (jPanelBuscarR == null) {
			nombrePjLabelR = new JLabel();
			nombrePjLabelR.setBounds(new Rectangle(10, 22, 58, 15));
			nombrePjLabelR.setText("Producto:");
			tipoBjLabelR = new JLabel();
			tipoBjLabelR.setBounds(new Rectangle(12, 82, 54, 15));
			tipoBjLabelR.setText("Destino:");
			formatoBjLabelR = new JLabel();
			formatoBjLabelR.setBounds(new Rectangle(13, 53, 52, 15));
			formatoBjLabelR.setText("Formato:");
			jPanelBuscarR = new JPanel();
			jPanelBuscarR.setLayout(null);
			jPanelBuscarR.setBorder(BorderFactory.createTitledBorder(null, "Buscar Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelBuscarR.setBounds(new Rectangle(512, 14, 436, 205));
			jPanelBuscarR.add(formatoBjLabelR, null);
			jPanelBuscarR.add(tipoBjLabelR, null);
			jPanelBuscarR.add(nombrePjLabelR, null);
			jPanelBuscarR.add(getFormatojComboBoxR(), null);
			jPanelBuscarR.add(getTipojComboBoxR(), null);
			jPanelBuscarR.add(getProductojComboBoxR(), null);
			jPanelBuscarR.add(getFechBjCalendarR(), null);
			jPanelBuscarR.add(getBuscarjButtonR(), null);
			jPanelBuscarR.add(getFechaElaboracionjCheckBoxR(), null);
			
		}
		return jPanelBuscarR;
	}
	
	private JButton getBuscarjButtonR() {
		if (buscarjButtonR == null) {
			buscarjButtonR = new JButton();
			buscarjButtonR.setBounds(new Rectangle(15, 134, 37, 33));
			buscarjButtonR.setIcon(new ImageIcon(getClass().getResource("/imagenes/search12.jpg")));
			buscarjButtonR.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {		
					int cant=0;
					String producto = null;
					String Nomb_producto;
//					si no hay seleccionado nada
					if(formatojComboBoxR.getSelectedIndex() == 0 && tipojComboBoxR.getSelectedIndex() == 0 && productojComboBoxR.getSelectedIndex() == 0 && !fechaelaboracionjCheckBoxR.isSelected())
						  {
						listFiltros.clear();
						LlenarTablaRevision();
					}
					else						
					if(listFiltros.isEmpty()){
						for (int w = datosRevisiondefaultTableModel.getRowCount(); w > 0 ; w--) {
							datosRevisiondefaultTableModel.removeRow(w - 1);
						}
						    String formato = null;
							int tipo = 0;
							if(formatojComboBoxR.getSelectedIndex() != 0)
								formato = (String)formatojComboBoxR.getSelectedItem();
							if(tipojComboBoxR.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxR.getSelectedIndex();}
							if(productojComboBoxR.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxR.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxR.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarR.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltrosRev(formato, tipo, producto, dateElaboracion);
								 cant= listFiltros.size();
								for (int i = 0; i < listFiltros.size(); i++) {
									Nomb_producto = Nombre_Prod(listFiltros.get(i));
									 if((listFiltros.get(i).getCod_estado() == 1 || listFiltros.get(i).getCod_estado() == 2) && (listFiltros.get(i).isEstado_revision() == false)){
									    if(listFiltros.get(i).getCodigo_tipo_lote() == 1 && listFiltros.get(i).isEstado_analisis() == true)
											getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_revisado(), "SI"});
										if(listFiltros.get(i).getCodigo_tipo_lote() == 1 && listFiltros.get(i).isEstado_analisis() == false)
											getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_revisado(), "NO"});
										if(listFiltros.get(i).getCodigo_tipo_lote() == 2 && listFiltros.get(i).isEstado_analisis() == true)
											getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_revisado(), "SI"});
										if(listFiltros.get(i).getCodigo_tipo_lote() == 2 && listFiltros.get(i).isEstado_analisis() == false)
											getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_revisado(), "NO"});
														
													  }
												}
									    }	
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					else {
						    String formato = null;
						    int tipo = 0;
							if(formatojComboBoxR.getSelectedIndex() != 0)
								formato = (String)formatojComboBoxR.getSelectedItem();
							for (int w = datosRevisiondefaultTableModel.getRowCount(); w > 0 ; w--) {
								datosRevisiondefaultTableModel.removeRow(w - 1);
							}
							listFiltros.clear();
						
							if(tipojComboBoxR.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxR.getSelectedIndex();}
							if(productojComboBoxR.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxR.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxR.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarR.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);
								}	
							try {
								listFiltros = Lote_Service.listFiltrosRev(formato, tipo, producto, dateElaboracion);
								for (int j = 0; j < listFiltros.size(); j++) {
										Nomb_producto = Nombre_Prod(listFiltros.get(j));
												 if((listFiltros.get(j).getCod_estado() == 1 || listFiltros.get(j).getCod_estado() == 2) && (listFiltros.get(j).isEstado_revision() == false)){
													if(listFiltros.get(j).getCodigo_tipo_lote() == 1 && listFiltros.get(j).isEstado_analisis() == true)
														getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(),listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_revisado(), "SI"});
													if(listFiltros.get(j).getCodigo_tipo_lote() == 1 && listFiltros.get(j).isEstado_analisis() == false)
														getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(),listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_revisado(), "NO"});
													if(listFiltros.get(j).getCodigo_tipo_lote() == 2 && listFiltros.get(j).isEstado_analisis() == true)
														getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(),listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_revisado(), "SI"});
													if(listFiltros.get(j).getCodigo_tipo_lote() == 2 && listFiltros.get(j).isEstado_analisis() == false)
														getDatosRevisiondefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(),listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_revisado(), "NO"});
													 }
												 }
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						}
			});
		}
		return buscarjButtonR;
	}
	
	private JCalendar getFechBjCalendarR() {
		if (fechBjCalendarR == null) {
			fechBjCalendarR = new JCalendar();
			fechBjCalendarR.setBounds(new Rectangle(232, 49, 193, 148));
		}
		return fechBjCalendarR;
	}
	private JCheckBox getFechaElaboracionjCheckBoxR() {
		if (fechaelaboracionjCheckBoxR == null) {
			fechaelaboracionjCheckBoxR = new JCheckBox();
			fechaelaboracionjCheckBoxR.setText("Fecha de Elaboración:");
			fechaelaboracionjCheckBoxR.setBounds(new Rectangle(232, 20, 155, 21));
			fechaelaboracionjCheckBoxR.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechaelaboracionjCheckBoxR.isSelected())
						fechBjCalendarR.setEnabled(true);
					else fechBjCalendarR.setEnabled(false);
				}
			});
		}
		return fechaelaboracionjCheckBoxR;
	}
	
	private JComboBox getProductojComboBoxR() {
		if (productojComboBoxR == null) {
			productojComboBoxR = new JComboBox();
			productojComboBoxR.setBounds(new Rectangle(80, 22, 136, 20));
			productojComboBoxR.setModel(getProductodefaultComboBoxModel11());
			productojComboBoxR.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
						String producto = null;
						String codigo = null;
						List<String> listaf = new ArrayList<String>();
						if(productojComboBoxR.getSelectedIndex() != 0){
						    producto = (String) productojComboBoxR.getSelectedItem();
						    codigo = Cod_Producto(producto);
							formatodefaultComboBoxModelR.removeAllElements();
							listaf.clear();
						    listaf = Formatos(codigo);
						    for(int j=0; j<listaf.size(); j++){
						      formatodefaultComboBoxModelR.addElement(listaf.get(j));
							 }	
						   }
						else
							if(productojComboBoxR.getSelectedItem().equals("<Seleccione>")){
							formatodefaultComboBoxModelR.removeAllElements();
							formatodefaultComboBoxModelR.addElement("<Seleccione>");
							try {
								listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
								for (int i = 0; i < listFormatos.size(); i++) {
									formatodefaultComboBoxModelR.addElement(listFormatos.get(i));
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						}
				});
			}
		return productojComboBoxR;
	}
	
	private DefaultComboBoxModel getProductodefaultComboBoxModel11() {
		if (productodefaultComboBoxModelR == null) {
			productodefaultComboBoxModelR = new DefaultComboBoxModel();
			productodefaultComboBoxModelR.addElement("<Seleccione>");
			try {
				listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProducto.size(); i++) {
					productodefaultComboBoxModelR.addElement(listProducto.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModelR;
	}
	
	private JComboBox getTipojComboBoxR() {
		if (tipojComboBoxR == null) {
			tipojComboBoxR = new JComboBox();
			tipojComboBoxR.setBounds(new Rectangle(80, 82, 136, 20));
			tipojComboBoxR.setModel(getTipodefaultComboBoxModelR());
		}
		return tipojComboBoxR;
	}
	
	private DefaultComboBoxModel getTipodefaultComboBoxModelR() {
		if (tipodefaultComboBoxModelR == null) {
			tipodefaultComboBoxModelR = new DefaultComboBoxModel();
			tipodefaultComboBoxModelR.addElement("<Seleccione>");
			tipodefaultComboBoxModelR.addElement("Nacional");
			tipodefaultComboBoxModelR.addElement("Exportación");
		}
		return tipodefaultComboBoxModelR;
	}

	private JComboBox getFormatojComboBoxR() {
		if (formatojComboBoxR == null) {
			formatojComboBoxR = new JComboBox();
			formatojComboBoxR.setBounds(new Rectangle(80, 53, 136, 20));
			formatojComboBoxR.setModel(getFormatodefaultComboBoxModelR());
		}
		return formatojComboBoxR;
	}
	private DefaultComboBoxModel getFormatodefaultComboBoxModelR() {
		if (formatodefaultComboBoxModelR == null) {
			formatodefaultComboBoxModelR = new DefaultComboBoxModel();
			formatodefaultComboBoxModelR.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModelR.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModelR;
	}

	private JSeparator getJSeparatorR() {
		if (jSeparatorR == null) {
			jSeparatorR = new JSeparator();
			jSeparatorR.setBounds(new Rectangle(9, 226, 1024, 8));
		}
		return jSeparatorR;
	}

	private JScrollPane getDatosRevisionjScrollPane() {
		if (datosRevisionjScrollPane == null) {
			datosRevisionjScrollPane = new JScrollPane();
			datosRevisionjScrollPane.setBounds(new Rectangle(17, 238, 1016, 296));
			datosRevisionjScrollPane.setViewportView(getDatosRevisionjTable());
		}
		return datosRevisionjScrollPane;
	}

	private JTable getDatosRevisionjTable() {
		if (datosRevisionjTable == null) {
			datosRevisionjTable = new JTable();
			datosRevisionjTable.setModel(getDatosRevisiondefaultTableModel());
			datosRevisionjTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
			datosRevisionjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosRevisionjTable.getSelectedRow();
					Lote lote = listLoteRevision.get(pos);
					modificarjButtonR.setEnabled(true);
					EliminarRjButton.setEnabled(true);
					cantidadUnidadesjTextFieldR.setEnabled(true);
					jComboBoxRechazarR.setEnabled(true);
					codigoMjLabelR.setText(lote.getCod());
					if(lote.getCodigo_tipo_lote() == 1)
						tipoMjLabelR.setText("Nacional");
					else tipoMjLabelR.setText("Exportación");
					if(Rechazado(lote)== true){
						jComboBoxRechazarR.setSelectedIndex(0);
					}
					else{
						jComboBoxRechazarR.setSelectedIndex(1);
					}
					formatoMjLabelR.setText(String.valueOf(lote.getFormato()));
					cantidadUnidadesjTextFieldR.setText(String.valueOf(lote.getCant_unidades()));
				}
			});
		}
		return datosRevisionjTable;
	}

	private DefaultTableModel getDatosRevisiondefaultTableModel() {
		if (datosRevisiondefaultTableModel == null) {
			datosRevisiondefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Destino", "Cantidad Unidades", "D/A", "Fecha Elaboración", "Fecha Revisión", "Análisis"}, 0);
		}
		return datosRevisiondefaultTableModel;
	}

	private JButton getRevisionjButton() {
		if (revisionjButton  == null) {
			revisionjButton  = new JButton();
			revisionjButton .setBounds(new Rectangle(18, 546, 172, 26));
			revisionjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			revisionjButton .setText("Revisión Completada");
			revisionjButton .addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelR.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
						if(cantidadUnidadesjTextFieldR.getText().equals("")){
							JOptionPane.showMessageDialog(new JPanel(), "Debe insertar la cantidad de unidades", "Error", JOptionPane.ERROR_MESSAGE);
						}
					else {
					int pos = datosRevisionjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteRevision.get(pos).getCod());
						String cod_old = lote.getCod();
						GregorianCalendar calendar = (GregorianCalendar) fechajCalendarR.getCalendar();
						int l = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,l);
				        float cantidad_old = lote.getCant_unidades();
				        if(Rechazado(listLoteRevision.get(pos))== false){
				        if (date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())){
					         lote.setFecha_revisado(date);	
					         lote.setEstado_revision(true);
					        }
				        else{
				        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida, rectifíquela!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
					 }
			        else{
			        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				        if((lote.isEstado_analisis() == true) && (lote.isEstado_revision() == true))
				        	lote.setCod_estado(3);
				        else lote.setCod_estado(1);
				        if(cantidad_old >=(Float.valueOf(cantidadUnidadesjTextFieldR.getText()))){
					        lote.setCant_unidades(Float.valueOf(cantidadUnidadesjTextFieldR.getText()));
					        }
					        else
					        JOptionPane.showMessageDialog(new JPanel(), "La cantidad de unidades insertada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					       
				        Lote_Service.updateLote(lote,cod_old);
						@SuppressWarnings("unused")
						Producto producto = Prod_Services.getProdVO(lote.getProducto());
						
						for (int w = listLoteRevision.size(); w > 0 ; w--) {
							datosRevisiondefaultTableModel.removeRow(w - 1);
						}
						listLote.clear();
						listLoteRevision.clear();
						LlenarTablaRevision();
			        } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        codigoMjLabelR.setText("");
					tipoMjLabelR.setText("");
					formatoMjLabelR.setText("");
					cantidadUnidadesjTextFieldR.setText(null);
					cantidadUnidadesjTextFieldR.setEnabled(false);
					modificarjButtonR.setEnabled(false);
					jComboBoxRechazarR.setEnabled(false);
					datosRevisionjTable.clearSelection();
					Lote_Visual.getLote_Visual().LlenarTablaLote();
					}
				}
			});
		}
		return revisionjButton;
	}

	private JButton getModificarjButtonR() {
		if (modificarjButtonR == null) {
			modificarjButtonR = new JButton();
			modificarjButtonR.setText("Modificar");
			modificarjButtonR.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButtonR.setBounds(new Rectangle(797, 546, 116, 26));
			modificarjButtonR.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosRevisionjTable.getSelectedRow();
					Lote lote = listLoteRevision.get(pos);
					GregorianCalendar calendar = (GregorianCalendar) fechajCalendarR.getCalendar();
					int j = calendar.get(Calendar.DATE);
					int k = calendar.get(Calendar.MONTH);
					int p = calendar.get(Calendar.YEAR);
					p = p - 1900;
			        Date date = new Date(p,k,j);
			      
			        float cantidad_old = lote.getCant_unidades();
			        if (date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())){
			         lote.setFecha_revisado(date);	
			        }
			        else{
			        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
			        }  
			        if(cantidad_old >=(Float.valueOf(cantidadUnidadesjTextFieldR.getText()))){
			        lote.setCant_unidades(Float.valueOf(cantidadUnidadesjTextFieldR.getText()));
			        }
			        else{
			        	JOptionPane.showMessageDialog(new JPanel(), "La cantidad de unidades insertada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			        try {
						Lote_Service.updateLote(lote, lote.getCod());
						 if(jComboBoxRechazarR.getSelectedIndex()==0){
							    lote.setFecha(date);
								Lote_Service.createLoteRechazado(lote);
							}
							else{
								lote.setFecha(null);
								Lote_Service.deleteLoteRechazado(lote.getCod());
							}
						LlenarTablaRevision();
						jComboBoxRechazarR.setEnabled(false);
						cantidadUnidadesjTextFieldR.setEnabled(false);
						EliminarRjButton.setEnabled(false);
						Traza traza = new Traza();
						String operacion = "U-" +"Tabla: lote_produccion" + ", Lote:"+ lote.getCod();;
						if (Traza_Servicio.existeTraza(loguiado.getNombre(), fechaActual)== true){
							traza= FindTraza(loguiado.getNombre(), fechaActual);
							traza.setOperacion(traza.getOperacion()+ "/ "+ operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.updateTraza(traza, loguiado.getNombre(), fechaActual);
						}
						else{
							traza.setOperacion(operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.createTraza(traza);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
				}
			});
		}
		return modificarjButtonR;
	}
	
	private JButton getEliminarRjButton() {
	if (EliminarRjButton == null) {
		EliminarRjButton = new JButton();
		EliminarRjButton.setBounds(new Rectangle(934, 546, 99, 26));
		EliminarRjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
		EliminarRjButton.setText("Eliminar");
		EliminarRjButton.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int pos = datosRevisionjTable.getSelectedRow();
				Lote lote = listLoteRevision.get(pos);
		        lote.setEstado_analisis(false);
		        lote.setCod_estado(1);
		        lote.setFecha_analisis(null);
		        lote.setFecha_revisado(null);
		        try {
					Lote_Service.updateLote(lote, lote.getCod());
					LlenarTablaAnalisis();
					LlenarTablaRevision();
					modificarjButtonR.setEnabled(false);
					jComboBoxRechazarR.setEnabled(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
			}
		});
	}
	return EliminarRjButton;
}

	private JTextField getCantidadUnidadesjTextFieldR() {
		if (cantidadUnidadesjTextFieldR == null) {
			cantidadUnidadesjTextFieldR = new JTextField();
			cantidadUnidadesjTextFieldR.setLocation(new Point(153, 80));
			cantidadUnidadesjTextFieldR.setSize(new Dimension(123, 19));
			Validaciones.validateDigitAndComma(cantidadUnidadesjTextFieldR);
		}
		return cantidadUnidadesjTextFieldR;
	}
	
	private JButton getJButtonReporteRevision() {
		if (jButtonReporteRevision == null) {
			jButtonReporteRevision = new JButton();
			jButtonReporteRevision.setBounds(new Rectangle(629, 546, 147, 26));
			jButtonReporteRevision.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			jButtonReporteRevision.setText("Generar Reporte");
			jButtonReporteRevision.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/*Reportes reportes = new Reportes();
					reportes.CargarReportesRevision();*/
					outputToEXL excel = new outputToEXL(addressR);
					excel.saveRevision(3);
				}
			});
		}
		return jButtonReporteRevision;
	}
	private JComboBox getJComboBoxRechazarR() {
		if (jComboBoxRechazarR == null) {
			jComboBoxRechazarR = new JComboBox();
			jComboBoxRechazarR.setBounds(new Rectangle(153, 138, 123, 19));
			jComboBoxRechazarR.setModel(getdefaultComboBoxModelRechazarR());
		}
		return jComboBoxRechazarR;
	}
	private DefaultComboBoxModel getdefaultComboBoxModelRechazarR() {
		if (defaultComboBoxModelRechazarR == null) {
			defaultComboBoxModelRechazarR = new DefaultComboBoxModel();
			defaultComboBoxModelRechazarR.addElement("Si");
			defaultComboBoxModelRechazarR.addElement("No");
		}
		return defaultComboBoxModelRechazarR;
	}

//	-------------------------------------------------- DatosPendientesEtiquetado --------------------------------------------------------------------------
	public void ActualizarInterfazEtiquetado(){
		ConnectionBD.testConnect();
		formatojComboBoxE.setSelectedIndex(0);
		tipojComboBoxE.setSelectedIndex(0);
		productojComboBoxE.setSelectedIndex(0);
		modificarjButtonE.setEnabled(false);
		EliminarEjButton.setEnabled(false);
		crearjButtonE.setEnabled(false);
		fechBjCalendarE.setEnabled(false);
		jComboBoxRechazarE.setEnabled(false);
		listLote.clear();
		listFiltros.clear();
		LlenarTablaEtiquetado();
	}
	public void LlenarTablaEtiquetado (){
		try {
			for (int w = datosPEdefaultTableModel.getRowCount(); w > 0 ; w--) {
				datosPEdefaultTableModel.removeRow(w - 1);
			}
			listLoteEtiquetado.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProducto= (ArrayList<Producto>) Prod_Services.getAllProd();
			String producto;
			for (int i = 0; i < listLote.size(); i++) {
				producto = Nombre_Prod(listLote.get(i));
				if(listLote.get(i).isEstado_analisis() && listLote.get(i).isEstado_revision() && listLote.get(i).getCod_estado() == 3){
					if(listLote.get(i).getCodigo_tipo_lote() == 1)
						getDatosPEdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_etiquetado()});
					else getDatosPEdefaultTableModel().addRow(new Object[]{listLote.get(i), producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_etiquetado()});
					listLoteEtiquetado.add(listLote.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigoMjLabelE.setText("");
		formatoMjLabelE.setText("");
		cantidadjLabelE.setText("");
		tipoMjLabelE.setText("");
		datosPEjTable.clearSelection();
	}

	private JPanel getJContentPaneEtiquetado() {
		if (jContentPaneEtiquetado == null) {
			jLabelRechazarE = new JLabel();
			jLabelRechazarE.setBounds(new Rectangle(63, 135, 70, 16));
			jLabelRechazarE.setText("Rechazado:");
			logojLabelE = new JLabel();
			logojLabelE.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabelE.setLocation(new Point(961, 1));
			logojLabelE.setSize(new Dimension(85, 74));
			logojLabelE.setText("");
			JSeparator jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(13, 230, 1018, 8));
			JPanel jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "Datos Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.setSize(new Dimension(495, 209));
			jPanel.setLocation(new Point(15, 14));
			fechajLabelE = new JLabel();
			fechajLabelE.setText("Fecha Etiquetado:");
			fechajLabelE.setBounds(new Rectangle(286, 16, 100, 16));
			tipoMjLabelE = new JLabel();
			tipoMjLabelE.setText("");
			tipoMjLabelE.setSize(new Dimension(123, 19));
			tipoMjLabelE.setLocation(new Point(145, 105));
			cantidadjLabelE = new JLabel();
			cantidadjLabelE.setText("");
			cantidadjLabelE.setSize(new Dimension(123, 19));
			cantidadjLabelE.setLocation(new Point(145, 76));
			formatoMjLabelE = new JLabel();
			formatoMjLabelE.setText("");
			formatoMjLabelE.setSize(new Dimension(123, 19));
			formatoMjLabelE.setLocation(new Point(145, 48));
			codigoMjLabelE = new JLabel();
			codigoMjLabelE.setText("");
			codigoMjLabelE.setSize(new Dimension(123, 19));
			codigoMjLabelE.setLocation(new Point(145, 18));
			tipojLabelE = new JLabel();
			tipojLabelE.setText("Destino:");
			tipojLabelE.setBounds(new Rectangle(83, 107, 50, 16));
			cantidadUjLabelE = new JLabel();
			cantidadUjLabelE.setText("Cantidad de Unidades:");
			cantidadUjLabelE.setBounds(new Rectangle(7, 78, 126, 16));
			formatojLabelE = new JLabel();
			formatojLabelE.setText("Formato:");
			formatojLabelE.setBounds(new Rectangle(83, 50, 50, 16));
			nombrejLabelE = new JLabel();
			nombrejLabelE.setText("Código:");
			nombrejLabelE.setBounds(new Rectangle(91, 20, 42, 16));
			jPanel.add(nombrejLabelE, null);
			jPanel.add(codigoMjLabelE, null);
			jPanel.add(formatojLabelE, null);
			jPanel.add(formatoMjLabelE, null);
			jPanel.add(cantidadUjLabelE, null);
			jPanel.add(cantidadjLabelE, null);
			jPanel.add(tipojLabelE, null);
			jPanel.add(tipoMjLabelE, null);
			jPanel.add(fechajLabelE, null);
			jPanel.add(getFechajCalendarE(), null);
			jPanel.add(jLabelRechazarE, null);
			jPanel.add(getJComboBoxRechazarE(), null);
			jContentPaneEtiquetado = new JPanel();
			jContentPaneEtiquetado.setLayout(null);
			jContentPaneEtiquetado.setSize(new Dimension(1049, 635));
			jContentPaneEtiquetado.add(jPanel, null);
			jContentPaneEtiquetado.add(jSeparator, null);
			jContentPaneEtiquetado.add(getDatosPEjScrollPane(), null);
			jContentPaneEtiquetado.add(getModificarjButtonE(), null);
			jContentPaneEtiquetado.add(getEtiquetadojButton(), null);
			jContentPaneEtiquetado.add(logojLabelE, null);
			jContentPaneEtiquetado.add(getCrearjButton(), null);
			jContentPaneEtiquetado.add(getJPanelBuscarE(), null);
			jContentPaneEtiquetado.add(getJButtonReporteE(), null);
			jContentPaneEtiquetado.add(getEliminarEjButton(), null);
			jContentPaneEtiquetado.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					codigoMjLabelE.setText("");
					tipoMjLabelE.setText("");
					formatoMjLabelE.setText("");
					cantidadjLabelE.setText("");
					modificarjButtonE.setEnabled(false);
					EliminarEjButton.setEnabled(false);
					crearjButtonE.setEnabled(false);
					datosPEjTable.clearSelection();
					jComboBoxRechazarE.setEnabled(false);
				}
			});
			
		}
		return jContentPaneEtiquetado;
	}
	
	private JPanel getJPanelBuscarE() {
		if (jPanelBuscarE == null) {
			nombrePjLabelE = new JLabel();
			nombrePjLabelE.setBounds(new Rectangle(10, 26, 58, 15));
			nombrePjLabelE.setText("Producto:");
			tipoBjLabelE = new JLabel();
			tipoBjLabelE.setBounds(new Rectangle(14, 87, 50, 15));
			tipoBjLabelE.setText("Destino");
			formatoBjLabelE = new JLabel();
			formatoBjLabelE.setBounds(new Rectangle(13, 58, 52, 15));
			formatoBjLabelE.setText("Formato:");
			jPanelBuscarE = new JPanel();
			jPanelBuscarE.setLayout(null);
			jPanelBuscarE.setBorder(BorderFactory.createTitledBorder(null, "Buscar Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelBuscarE.setBounds(new Rectangle(511, 14, 438, 209));
			jPanelBuscarE.add(formatoBjLabelE, null);
			jPanelBuscarE.add(tipoBjLabelE, null);
			jPanelBuscarE.add(nombrePjLabelE, null);
			jPanelBuscarE.add(getFormatojComboBoxE(), null);
			jPanelBuscarE.add(getTipojComboBoxE(), null);
			jPanelBuscarE.add(getProductojComboBoxE(), null);
			jPanelBuscarE.add(getFechBjCalendarE(), null);
			jPanelBuscarE.add(getBuscarjButtonE(), null);
			jPanelBuscarE.add(getFechaElaboracionjCheckBoxE(), null);
			
		}
		return jPanelBuscarE;
	}
	
	private JButton getBuscarjButtonE() {
		if (buscarjButtonE == null) {
			buscarjButtonE = new JButton();
			buscarjButtonE.setBounds(new Rectangle(15, 134, 37, 33));
			buscarjButtonE.setIcon(new ImageIcon(getClass().getResource("/imagenes/search12.jpg")));
			buscarjButtonE.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {		
					String producto = null;
					String Nomb_producto;
//					//si no hay seleccionado nada
					if(formatojComboBoxE.getSelectedIndex() == 0 && tipojComboBoxE.getSelectedIndex() == 0 && productojComboBoxE.getSelectedIndex() == 0 && !fechaelaboracionjCheckBoxE.isSelected())
					  {
						listFiltros.clear();
						LlenarTablaEtiquetado();
					  }
			    else						
					if(listFiltros.isEmpty()){
						for (int w = datosPEdefaultTableModel.getRowCount(); w > 0 ; w--) {
							datosPEdefaultTableModel.removeRow(w - 1);
						}
						    String formato = null;
							int tipo = 0;
							if(formatojComboBoxE.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxE.getSelectedItem();}
							if(tipojComboBoxE.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxE.getSelectedIndex();}
							if(productojComboBoxE.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxE.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxE.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarE.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltros(3, formato, tipo, producto, dateElaboracion);
								for (int i = 0; i < listFiltros.size(); i++) {
										Nomb_producto = Nombre_Prod(listFiltros.get(i));
										if(listFiltros.get(i).getCodigo_tipo_lote() == 1){
											getDatosPEdefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_etiquetado()});
											}
										else {
											getDatosPEdefaultTableModel().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_etiquetado()});
											}
									   }
								   }	
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					else {
						    String formato = null;
						    int tipo = 0;
							if(formatojComboBoxE.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxE.getSelectedItem();}
							for (int w = datosPEdefaultTableModel.getRowCount(); w > 0 ; w--) {
								datosPEdefaultTableModel.removeRow(w - 1);
							}
							listFiltros.clear();
						
							if(tipojComboBoxE.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxE.getSelectedIndex();}
							if(productojComboBoxE.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxE.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxE.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarE.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);
							  }
							try {
								listFiltros = Lote_Service.listFiltros(3, formato, tipo, producto, dateElaboracion);
								for (int j = 0; j < listFiltros.size(); j++) {
										Nomb_producto = Nombre_Prod(listFiltros.get(j));
										if(listFiltros.get(j).getCodigo_tipo_lote() == 1){
											getDatosPEdefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_etiquetado()});
											}
										else {
											getDatosPEdefaultTableModel().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_etiquetado()});
											}
									}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						}
			});
		}
		return buscarjButtonE;
	}

	private JCalendar getFechBjCalendarE() {
		if (fechBjCalendarE == null) {
			fechBjCalendarE = new JCalendar();
			fechBjCalendarE.setBounds(new Rectangle(232, 49, 193, 151));
		}
		return fechBjCalendarE;
	}
	
	private JCheckBox getFechaElaboracionjCheckBoxE() {
		if (fechaelaboracionjCheckBoxE == null) {
			fechaelaboracionjCheckBoxE = new JCheckBox();
			fechaelaboracionjCheckBoxE.setText("Fecha de Elaboración:");
			fechaelaboracionjCheckBoxE.setBounds(new Rectangle(232, 19, 155, 21));
			fechaelaboracionjCheckBoxE.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechaelaboracionjCheckBoxE.isSelected())
						fechBjCalendarE.setEnabled(true);
					else fechBjCalendarE.setEnabled(false);
				}
			});
		}
		return fechaelaboracionjCheckBoxE;
	}
	
	private JComboBox getProductojComboBoxE() {
		if (productojComboBoxE == null) {
			productojComboBoxE = new JComboBox();
			productojComboBoxE.setBounds(new Rectangle(82, 26, 136, 20));
			productojComboBoxE.setModel(getProductodefaultComboBoxModelE());
			productojComboBoxE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String producto = null;
					String codigo = null;
					List<String> listaf = new ArrayList<String>();
					if(productojComboBoxE.getSelectedIndex() != 0){
					    producto = (String) productojComboBoxE.getSelectedItem();
					    codigo = Cod_Producto(producto);
						formatodefaultComboBoxModelE.removeAllElements();
						listaf.clear();
					    listaf = Formatos(codigo);
					    for(int j=0; j<listaf.size(); j++){
					      formatodefaultComboBoxModelE.addElement(listaf.get(j));
						 }	
					   }
					else
						if(productojComboBoxE.getSelectedItem().equals("<Seleccione>")){
						formatodefaultComboBoxModelE.removeAllElements();
						formatodefaultComboBoxModelE.addElement("<Seleccione>");
						try {
							listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
							for (int i = 0; i < listFormatos.size(); i++) {
								formatodefaultComboBoxModelE.addElement(listFormatos.get(i));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
			});
		}
		return productojComboBoxE;
	}
	
	private DefaultComboBoxModel getProductodefaultComboBoxModelE() {
		if (productodefaultComboBoxModelE == null) {
			productodefaultComboBoxModelE = new DefaultComboBoxModel();
			productodefaultComboBoxModelE.addElement("<Seleccione>");
			try {
				listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProducto.size(); i++) {
					productodefaultComboBoxModelE.addElement(listProducto.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModelE;
	}
	
	private JComboBox getTipojComboBoxE() {
		if (tipojComboBoxE == null) {
			tipojComboBoxE = new JComboBox();
			tipojComboBoxE.setBounds(new Rectangle(82, 87, 136, 20));
			tipojComboBoxE.setModel(getTipodefaultComboBoxModelE());
		}
		return tipojComboBoxE;
	}
	private DefaultComboBoxModel getTipodefaultComboBoxModelE() {
		if (tipodefaultComboBoxModelE == null) {
			tipodefaultComboBoxModelE = new DefaultComboBoxModel();
			tipodefaultComboBoxModelE.addElement("<Seleccione>");
			tipodefaultComboBoxModelE.addElement("Nacional");
			tipodefaultComboBoxModelE.addElement("Exportación");
		}
		return tipodefaultComboBoxModelE;
	}
	
	private JComboBox getFormatojComboBoxE() {
		if (formatojComboBoxE == null) {
			formatojComboBoxE = new JComboBox();
			formatojComboBoxE.setBounds(new Rectangle(82, 58, 136, 20));
			formatojComboBoxE.setModel(getFormatodefaultComboBoxModelE());
		}
		return formatojComboBoxE;
	}
	private DefaultComboBoxModel getFormatodefaultComboBoxModelE() {
		if (formatodefaultComboBoxModelE == null) {
			formatodefaultComboBoxModelE = new DefaultComboBoxModel();
			formatodefaultComboBoxModelE.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModelE.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModelE;
	}

	private JScrollPane getDatosPEjScrollPane() {
		if (datosPEjScrollPane == null) {
			datosPEjScrollPane = new JScrollPane();
			datosPEjScrollPane.setBounds(new Rectangle(16, 240, 1011, 299));
			datosPEjScrollPane.setViewportView(getDatosPEjTable());
		}
		return datosPEjScrollPane;
	}

	private JTable getDatosPEjTable() {
		if (datosPEjTable == null) {
			datosPEjTable = new JTable();
			datosPEjTable.setModel(getDatosPEdefaultTableModel());
			datosPEjTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
			datosPEjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosPEjTable.getSelectedRow();
					Lote lote = listLoteEtiquetado.get(pos);
					modificarjButtonE.setEnabled(true);
					EliminarEjButton.setEnabled(true);
					crearjButtonE.setEnabled(true);
					jComboBoxRechazarE.setEnabled(true);
					codigoMjLabelE.setText(lote.getCod());
					if(lote.getCodigo_tipo_lote() == 1)
						tipoMjLabelE.setText("Nacional");
					else tipoMjLabelE.setText("Exportación");
					if(Rechazado(lote)== true){
						jComboBoxRechazarE.setSelectedIndex(0);
					}
					else{
						jComboBoxRechazarE.setSelectedIndex(1);
					}
					formatoMjLabelE.setText(String.valueOf(lote.getFormato()));
					cantidadjLabelE.setText(String.valueOf(lote.getCant_unidades()));
				}
			});
		}
		return datosPEjTable;
	}

	private DefaultTableModel getDatosPEdefaultTableModel() {
		if (datosPEdefaultTableModel == null) {
			datosPEdefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Destino", "Cantidad Unidades", "D/A", "Fecha Elaboración", "Fecha Etiquetado"}, 0);
		}
		return datosPEdefaultTableModel;
	}

	private JButton getModificarjButtonE() {
		if (modificarjButtonE == null) {
			modificarjButtonE = new JButton();
			modificarjButtonE.setBounds(new Rectangle(801, 550, 116, 26));
			modificarjButtonE.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButtonE.setText("Modificar");
			modificarjButtonE.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosPEjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteEtiquetado.get(pos).getCod());
						String cod_old = lote.getCod();
						GregorianCalendar calendar = (GregorianCalendar) fechajCalendarE.getCalendar();
						int l = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,l);
				        if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_revisado())|| date.equals(lote.getFecha_revisado()))){
				        	lote.setFecha_etiquetado(date);
					        }
					        else{
					        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					        }
				        
						Lote_Service.updateLote(lote,cod_old);
						if(jComboBoxRechazarE.getSelectedIndex()==0){
							lote.setFecha(date);
							Lote_Service.createLoteRechazado(lote);
						}
						else{
							lote.setFecha(null);
							Lote_Service.deleteLoteRechazado(lote.getCod());
						}
						LlenarTablaEtiquetado();
						Traza traza = new Traza();
						String operacion = "U-" +"Tabla: lote_produccion" + ", Lote:"+ lote.getCod();;
						if (Traza_Servicio.existeTraza(loguiado.getNombre(), fechaActual)== true){
							traza= FindTraza(loguiado.getNombre(), fechaActual);
							traza.setOperacion(traza.getOperacion()+ "/ "+ operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.updateTraza(traza, loguiado.getNombre(), fechaActual);
						}
						else{
							traza.setOperacion(operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.createTraza(traza);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					codigoMjLabelE.setText("");
					tipoMjLabelE.setText("");
					formatoMjLabelE.setText("");
					cantidadjLabelE.setText("");
					modificarjButtonE.setEnabled(false);
					EliminarEjButton.setEnabled(false);
					datosPEjTable.clearSelection();
					jComboBoxRechazarE.setEnabled(false);
				}
			});
		}
		return modificarjButtonE;
	}

	private JButton getEtiquetadojButton() {
		if (etiquetadojButton == null) {
			etiquetadojButton = new JButton();
			etiquetadojButton.setBounds(new Rectangle(15, 550, 184, 26));
			etiquetadojButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			etiquetadojButton.setText("Etiquetado Completado");
			etiquetadojButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelE.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int pos = datosPEjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteEtiquetado.get(pos).getCod());
						String cod_old = lote.getCod();
						GregorianCalendar calendar = (GregorianCalendar) fechajCalendarE.getCalendar();
						int l = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,l);
				        if(Rechazado(listLoteEtiquetado.get(pos))== false){
						if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_revisado())|| date.equals(lote.getFecha_revisado()))){
				        	 lote.setFecha_etiquetado(date);
				        	 lote.setEstado_etiquetado(true);
						     lote.setCod_estado(4);
						     Lote_Service.updateLote(lote,cod_old);
					        }
						  else{
					        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida, rectifíquela!", "Error", JOptionPane.ERROR_MESSAGE);
					        }
						 }
				        else{
				        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				       @SuppressWarnings("unused")
						Producto producto = Prod_Services.getProdVO(lote.getProducto());
						for (int w = listLoteEtiquetado.size(); w > 0 ; w--) {
							datosPEdefaultTableModel.removeRow(w - 1);
						}
						listLote.clear();
						listLoteEtiquetado.clear();
						listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
						String Nomb_producto;
						for (int i = 0; i < listLote.size(); i++) {
							Nomb_producto= Nombre_Prod(listLote.get(i));
							if(listLote.get(i).getCod_estado() == 3){
								if(listLote.get(i).getCodigo_tipo_lote() == 1)
									getDatosPEdefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(),listLote.get(i).getFecha_etiquetado()});
								else getDatosPEdefaultTableModel().addRow(new Object[]{listLote.get(i),Nomb_producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_etiquetado()});
								listLoteEtiquetado.add(listLote.get(i));
							}
						}
			        } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        codigoMjLabelE.setText("");
					tipoMjLabelE.setText("");
					formatoMjLabelE.setText("");
					cantidadjLabelE.setText("");
					modificarjButtonE.setEnabled(false);
					jComboBoxRechazarE.setEnabled(false);
					datosPEjTable.clearSelection();
					Lote_Visual.getLote_Visual().LlenarTablaLote();
					}
				}
			});
		}
		return etiquetadojButton;
	}

	private JCalendar getFechajCalendarE() {
		if (fechajCalendarE == null) {
			fechajCalendarE = new JCalendar();
			fechajCalendarE.setBounds(new Rectangle(288, 41, 196, 153));
		}
		return fechajCalendarE;
	}

	private JButton getCrearjButton() {
		if (crearjButtonE == null) {
			crearjButtonE = new JButton();
			crearjButtonE.setBounds(new Rectangle(643, 550, 147, 26));
			crearjButtonE.setIcon(new ImageIcon(getClass().getResource("/imagenes/globe-icon.png")));
			crearjButtonE.setText("Cambiar Destino");
			crearjButtonE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelE.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int pos = datosPEjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteEtiquetado.get(pos).getCod());
			        	if(Rechazado(listLoteEtiquetado.get(pos))== false){
						ModEnvaseTipo cambDestino = new ModEnvaseTipo(lote);
						cambDestino.setVisible(true);
			        	}
			        	else{
				        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
			});
		}
		return crearjButtonE;
	}

	private JButton getJButtonReporteE() {
		if (jButtonReporteE == null) {
			jButtonReporteE = new JButton();
			jButtonReporteE.setBounds(new Rectangle(482, 550, 147, 26));
			jButtonReporteE.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			jButtonReporteE.setText("Generar Reporte");
			jButtonReporteE.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/*Reportes reportes = new Reportes();
					reportes.CargarReportesEtiquetado();*/
					outputToEXL excel = new outputToEXL(addressE);
					excel.saveEtiquetado(3);
				}
			});
		}
		return jButtonReporteE;
	}
	private JComboBox getJComboBoxRechazarE() {
		if (jComboBoxRechazarE == null) {
			jComboBoxRechazarE = new JComboBox();
			jComboBoxRechazarE.setBounds(new Rectangle(145, 134, 123, 19));
			jComboBoxRechazarE.setModel(getdefaultComboBoxModelRechazarE());
		}
		return jComboBoxRechazarE;
	}
	private DefaultComboBoxModel getdefaultComboBoxModelRechazarE() {
		if (defaultComboBoxModelRechazarE == null) {
			defaultComboBoxModelRechazarE = new DefaultComboBoxModel();
			defaultComboBoxModelRechazarE.addElement("Si");
			defaultComboBoxModelRechazarE.addElement("No");
		}
		return defaultComboBoxModelRechazarE;
	}
//	-------------------------------------------------- DatosPendientesEnvase ------------------------------------------------------------------------------
	
	public void ActualizarInterfazEnvase(){
		ConnectionBD.testConnect();
		formatojComboBoxEN.setSelectedIndex(0);
		tipojComboBoxEN.setSelectedIndex(0);
		productojComboBoxEN.setSelectedIndex(0);
		fechBjCalendarEN.setEnabled(false);
		modificarjButtonEN.setEnabled(false);
		EliminarEnjButton.setEnabled(false);
		jComboBoxRechazarEn.setEnabled(false);
		cantidadUnidadesjTextFieldEN.setEnabled(false);
		listLote.clear();
		listFiltros.clear();
		LlenarTablaEnvase();
	}
	
	public void LlenarTablaEnvase (){
		try {
			for (int w = datosdefaultTableModelEN.getRowCount(); w > 0 ; w--) {
				datosdefaultTableModelEN.removeRow(w - 1);
			}
			listLoteEnvase.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
			String Nomb_producto;
			for (int i = 0; i < listLote.size(); i++) {
				Nomb_producto = Nombre_Prod(listLote.get(i));
				if(listLote.get(i).getCod_estado() == 4 && listLote.get(i).isEstado_etiquetado()){
					if(listLote.get(i).getCodigo_tipo_lote() == 1)
						getDatosdefaultTableModelEN().addRow(new Object[]{listLote.get(i), Nomb_producto, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_envase()});
					else getDatosdefaultTableModelEN().addRow(new Object[]{listLote.get(i), Nomb_producto, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_envase()});
					listLoteEnvase.add(listLote.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigoMjLabelEN.setText("");
		tipoMjLabelEN.setText("");
		formatoMjLabelEN.setText("");
		cantidadUnidadesjTextFieldEN.setText(null);
		modificarjButtonEN.setEnabled(false);
		datosjTableEN.clearSelection();
	}

	private JPanel getJContentPaneEN() {
		if (jContentPaneEnvase == null) {
			logojLabelEN = new JLabel();
			logojLabelEN.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabelEN.setLocation(new Point(961, 1));
			logojLabelEN.setSize(new Dimension(86, 73));
			logojLabelEN.setText("");
			tipoMjLabelEN = new JLabel();
			tipoMjLabelEN.setText("");
			tipoMjLabelEN.setSize(new Dimension(123, 19));
			tipoMjLabelEN.setLocation(new Point(152, 110));
			formatoMjLabelEN = new JLabel();
			formatoMjLabelEN.setText("");
			formatoMjLabelEN.setSize(new Dimension(123, 19));
			formatoMjLabelEN.setLocation(new Point(152, 48));
			codigoMjLabelEN = new JLabel();
			codigoMjLabelEN.setText("");
			codigoMjLabelEN.setSize(new Dimension(123, 19));
			codigoMjLabelEN.setLocation(new Point(152, 19));
			fechajLabelEN = new JLabel();
			fechajLabelEN.setText("Fecha de Envase:");
			fechajLabelEN.setBounds(new Rectangle(290, 19, 98, 16));
			tipojLabelEN = new JLabel();
			tipojLabelEN.setText("Destino:");
			tipojLabelEN.setBounds(new Rectangle(90, 111, 52, 16));
			cantidadUnidadesjLabelEN = new JLabel();
			cantidadUnidadesjLabelEN.setText("Cantidad de Unidades:");
			cantidadUnidadesjLabelEN.setBounds(new Rectangle(16, 81, 126, 16));
			formatojLabelEN = new JLabel();
			formatojLabelEN.setText("Formato:");
			formatojLabelEN.setBounds(new Rectangle(92, 49, 50, 16));
			codigojLabelEN = new JLabel();
			codigojLabelEN.setText("Código:");
			codigojLabelEN.setBounds(new Rectangle(100, 20, 42, 16));
			jContentPaneEnvase = new JPanel();
			jContentPaneEnvase.setLayout(null);
			jContentPaneEnvase.setSize(new Dimension(1049, 635));
			jContentPaneEnvase.add(getDatosjPanelEN(), null);
			jContentPaneEnvase.add(getJSeparatorEN(), null);
			jContentPaneEnvase.add(getDatosjScrollPaneEN(), null);
			jContentPaneEnvase.add(getEnvasejButton(), null);
			jContentPaneEnvase.add(getModificarjButtonEN(), null);
			jContentPaneEnvase.add(logojLabelEN, null);
			jContentPaneEnvase.add(getJPanelBuscarEN(), null);
			jContentPaneEnvase.add(getJButtonReporteEnv(), null);
			jContentPaneEnvase.add(getEliminarEnjButton(), null);
			jContentPaneEnvase.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					codigoMjLabelEN.setText("");
					tipoMjLabelEN.setText("");
					formatoMjLabelEN.setText("");
					cantidadUnidadesjTextFieldEN.setText(null);
					modificarjButtonEN.setEnabled(false);
					EliminarEnjButton.setEnabled(false);
					jComboBoxRechazarEn.setEnabled(false);
					cantidadUnidadesjTextFieldEN.setEnabled(false);
					datosjTableEN.clearSelection();
					
				}
			});
		}
		return jContentPaneEnvase;
	}

	private JPanel getDatosjPanelEN() {
		if (datosjPanelEN == null) {
			jLabelRechazarEn = new JLabel();
			jLabelRechazarEn.setBounds(new Rectangle(71, 141, 71, 16));
			jLabelRechazarEn.setText("Rechazado:");
			jLabelEN = new JLabel();
			jLabelEN.setBounds(new Rectangle(6, 81, 10, 16));
			jLabelEN.setText("*");
			jLabelEN.setForeground(new Color(255, 51, 51));
			datosjPanelEN = new JPanel();
			datosjPanelEN.setLayout(null);
			datosjPanelEN.setBorder(BorderFactory.createTitledBorder(null, "Datos Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			datosjPanelEN.setLocation(new Point(14, 15));
			datosjPanelEN.setSize(new Dimension(495, 214));
			datosjPanelEN.add(codigojLabelEN, null);
			datosjPanelEN.add(formatojLabelEN, null);
			datosjPanelEN.add(codigoMjLabelEN, null);
			datosjPanelEN.add(formatoMjLabelEN, null);
			datosjPanelEN.add(cantidadUnidadesjLabelEN, null);
			datosjPanelEN.add(tipojLabelEN, null);
			datosjPanelEN.add(tipoMjLabelEN, null);
			datosjPanelEN.add(getFechajCalendarEN(), null);
			datosjPanelEN.add(getCantidadUnidadesjTextFieldEN(), null);
			datosjPanelEN.add(jLabelEN, null);
			datosjPanelEN.add(fechajLabelEN, null);
			datosjPanelEN.add(jLabelRechazarEn, null);
			datosjPanelEN.add(getJComboBoxRechazarEn(), null);
		}
		return datosjPanelEN;
	}
	
	private JPanel getJPanelBuscarEN() {
		if (jPanelBuscarEN == null) {
			nombrePjLabelEN = new JLabel();
			nombrePjLabelEN.setBounds(new Rectangle(13, 26, 58, 15));
			nombrePjLabelEN.setText("Producto:");
			tipoBjLabelEN = new JLabel();
			tipoBjLabelEN.setBounds(new Rectangle(15, 88, 55, 15));
			tipoBjLabelEN.setText("Destino:");
			formatoBjLabelEN = new JLabel();
			formatoBjLabelEN.setBounds(new Rectangle(16, 59, 52, 15));
			formatoBjLabelEN.setText("Formato:");
			jPanelBuscarEN = new JPanel();
			jPanelBuscarEN.setLayout(null);
			jPanelBuscarEN.setBorder(BorderFactory.createTitledBorder(null, "Buscar Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelBuscarEN.setBounds(new Rectangle(508, 15, 438, 214));
			jPanelBuscarEN.add(formatoBjLabelEN, null);
			jPanelBuscarEN.add(tipoBjLabelEN, null);
			jPanelBuscarEN.add(nombrePjLabelEN, null);
			jPanelBuscarEN.add(getFormatojComboBoxEN(), null);
			jPanelBuscarEN.add(getTipojComboBoxEN(), null);
			jPanelBuscarEN.add(getProductojComboBoxEN(), null);
			jPanelBuscarEN.add(getFechBjCalendarEN(), null);
			jPanelBuscarEN.add(getBuscarjButtonEN(), null);
			jPanelBuscarEN.add(getFechaElaboracionjCheckBoxEN(), null);
			
		}
		return jPanelBuscarEN;
	}
	
	private JButton getBuscarjButtonEN() {
		if (buscarjButtonEN == null) {
			buscarjButtonEN = new JButton();
			buscarjButtonEN.setBounds(new Rectangle(15, 134, 37, 33));
			buscarjButtonEN.setIcon(new ImageIcon(getClass().getResource("/imagenes/search12.jpg")));
			buscarjButtonEN.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {		
					String producto = null;
					String Nomb_producto;
					//si no hay seleccionado nada
					if(formatojComboBoxEN.getSelectedIndex() == 0 && tipojComboBoxEN.getSelectedIndex() == 0 && productojComboBoxEN.getSelectedIndex() == 0 && !fechaelaboracionjCheckBoxEN.isSelected())
					  {
						listFiltros.clear();
						LlenarTablaEnvase();
					}
					else						
					if(listFiltros.isEmpty()){
						for (int w = datosdefaultTableModelEN.getRowCount(); w > 0 ; w--) {
							datosdefaultTableModelEN.removeRow(w - 1);
						}
						    String formato = null;
							int tipo = 0;
							if(formatojComboBoxEN.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxEN.getSelectedItem();}
							if(tipojComboBoxEN.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxEN.getSelectedIndex();}
							if(productojComboBoxEN.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxEN.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxEN.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarEN.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltros(4, formato, tipo, producto, dateElaboracion);
								for (int i = 0; i < listFiltros.size(); i++) {
										Nomb_producto= Nombre_Prod(listFiltros.get(i));
										if(listFiltros.get(i).getCodigo_tipo_lote() == 1){
											getDatosdefaultTableModelEN().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(),listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_envase()});
											}
										else {
											getDatosdefaultTableModelEN().addRow(new Object[]{listFiltros.get(i), Nomb_producto, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_envase()});
											}
									  }
								}	
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					else {
						    String formato = null;
						    int tipo = 0;
							if(formatojComboBoxEN.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxEN.getSelectedItem();}
							for (int w = datosdefaultTableModelEN.getRowCount(); w > 0 ; w--) {
								datosdefaultTableModelEN.removeRow(w - 1);
							}
							listFiltros.clear();
						
							if(tipojComboBoxEN.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxEN.getSelectedIndex();}
							if(productojComboBoxEN.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxEN.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxEN.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarEN.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltros(4, formato, tipo, producto, dateElaboracion);
								for (int j = 0; j < listFiltros.size(); j++) {
								Nomb_producto= Nombre_Prod(listFiltros.get(j));
										if(listFiltros.get(j).getCodigo_tipo_lote() == 1){
											getDatosdefaultTableModelEN().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_envase()});
											}
										else {
											getDatosdefaultTableModelEN().addRow(new Object[]{listFiltros.get(j), Nomb_producto, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_envase()});
											}
									}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						}
			});
		}
		return buscarjButtonEN;
	}
	
	private JCalendar getFechBjCalendarEN() {
		if (fechBjCalendarEN == null) {
			fechBjCalendarEN = new JCalendar();
			fechBjCalendarEN.setBounds(new Rectangle(232, 49, 193, 151));
		}
		return fechBjCalendarEN;
	}
	
	private JCheckBox getFechaElaboracionjCheckBoxEN() {
		if (fechaelaboracionjCheckBoxEN == null) {
			fechaelaboracionjCheckBoxEN = new JCheckBox();
			fechaelaboracionjCheckBoxEN.setText("Fecha de Elaboración:");
			fechaelaboracionjCheckBoxEN.setBounds(new Rectangle(231, 20, 155, 21));
			fechaelaboracionjCheckBoxEN.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechaelaboracionjCheckBoxEN.isSelected())
						fechBjCalendarEN.setEnabled(true);
					else fechBjCalendarEN.setEnabled(false);
				}
			});
		}
		return fechaelaboracionjCheckBoxEN;
	}
	
	private JComboBox getProductojComboBoxEN() {
		if (productojComboBoxEN == null) {
			productojComboBoxEN = new JComboBox();
			productojComboBoxEN.setBounds(new Rectangle(83, 26, 136, 20));
			productojComboBoxEN.setModel(getProductodefaultComboBoxModelEN());
			productojComboBoxEN.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String producto = null;
					String codigo = null;
					List<String> listaf = new ArrayList<String>();
					if(productojComboBoxEN.getSelectedIndex() != 0){
					    producto = (String) productojComboBoxEN.getSelectedItem();
					    codigo = Cod_Producto(producto);
						formatodefaultComboBoxModelEN.removeAllElements();
						listaf.clear();
					    listaf = Formatos(codigo);
					    for(int j=0; j<listaf.size(); j++){
					      formatodefaultComboBoxModelEN.addElement(listaf.get(j));
						 }	
					   }
					else
						if(productojComboBoxEN.getSelectedItem().equals("<Seleccione>")){
						formatodefaultComboBoxModelEN.removeAllElements();
						formatodefaultComboBoxModelEN.addElement("<Seleccione>");
						try {
							listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
							for (int i = 0; i < listFormatos.size(); i++) {
								formatodefaultComboBoxModelEN.addElement(listFormatos.get(i));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
			});
		}
		return productojComboBoxEN;
	}
	
	private DefaultComboBoxModel getProductodefaultComboBoxModelEN() {
		if (productodefaultComboBoxModelEN == null) {
			productodefaultComboBoxModelEN = new DefaultComboBoxModel();
			productodefaultComboBoxModelEN.addElement("<Seleccione>");
			try {
				listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProducto.size(); i++) {
					productodefaultComboBoxModelEN.addElement(listProducto.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModelEN;
	}
	
	private JComboBox getTipojComboBoxEN() {
		if (tipojComboBoxEN == null) {
			tipojComboBoxEN = new JComboBox();
			tipojComboBoxEN.setBounds(new Rectangle(83, 88, 136, 20));
			tipojComboBoxEN.setModel(getTipodefaultComboBoxModelEN());
		}
		return tipojComboBoxEN;
	}
	private DefaultComboBoxModel getTipodefaultComboBoxModelEN() {
		if (tipodefaultComboBoxModelEN == null) {
			tipodefaultComboBoxModelEN = new DefaultComboBoxModel();
			tipodefaultComboBoxModelEN.addElement("<Seleccione>");
			tipodefaultComboBoxModelEN.addElement("Nacional");
			tipodefaultComboBoxModelEN.addElement("Exportación");
		}
		return tipodefaultComboBoxModelEN;
	}
	
	private JComboBox getFormatojComboBoxEN() {
		if (formatojComboBoxEN == null) {
			formatojComboBoxEN = new JComboBox();
			formatojComboBoxEN.setBounds(new Rectangle(83, 59, 136, 20));
			formatojComboBoxEN.setModel(getFormatodefaultComboBoxModelEN());
		}
		return formatojComboBoxEN;
	}
	private DefaultComboBoxModel getFormatodefaultComboBoxModelEN() {
		if (formatodefaultComboBoxModelEN == null) {
			formatodefaultComboBoxModelEN = new DefaultComboBoxModel();
			formatodefaultComboBoxModelEN.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModelEN.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModelEN;
	}

	private JSeparator getJSeparatorEN() {
		if (jSeparatorEN == null) {
			jSeparatorEN = new JSeparator();
			jSeparatorEN.setBounds(new Rectangle(12, 237, 1022, 8));
		}
		return jSeparatorEN;
	}

	private JScrollPane getDatosjScrollPaneEN() {
		if (datosjScrollPaneEN == null) {
			datosjScrollPaneEN = new JScrollPane();
			datosjScrollPaneEN.setBounds(new Rectangle(15, 251, 1020, 280));
			datosjScrollPaneEN.setViewportView(getDatosjTableEN());
		}
		return datosjScrollPaneEN;
	}

	private JTable getDatosjTableEN() {
		if (datosjTableEN == null) {
			datosjTableEN = new JTable();
			datosjTableEN.setModel(getDatosdefaultTableModelEN());
			datosjTableEN.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
			datosjTableEN.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosjTableEN.getSelectedRow();
					Lote lote = listLoteEnvase.get(pos);
					modificarjButtonEN.setEnabled(true);
					EliminarEnjButton.setEnabled(true);
					jComboBoxRechazarEn.setEnabled(true);
					cantidadUnidadesjTextFieldEN.setEnabled(true);
					codigoMjLabelEN.setText(lote.getCod());
					if(lote.getCodigo_tipo_lote() == 1)
						tipoMjLabelEN.setText("Nacional");
					else tipoMjLabelEN.setText("Exportación");
					if(Rechazado(lote)== true){
						jComboBoxRechazarEn.setSelectedIndex(0);
					}
					else{
						jComboBoxRechazarEn.setSelectedIndex(1);
					}
					formatoMjLabelEN.setText(String.valueOf(lote.getFormato()));
					cantidadUnidadesjTextFieldEN.setText(String.valueOf(lote.getCant_unidades()));
				}
			});
		}
		return datosjTableEN;
	}

	private JButton getEnvasejButton() {
		if (envasejButton == null) {
			envasejButton = new JButton();
			envasejButton.setBounds(new Rectangle(15, 545, 165, 26));
			envasejButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			envasejButton.setText("Envase Completado");
			envasejButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelEN.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					if(cantidadUnidadesjTextFieldEN.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar la cantidad de unidades", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
					    int pos = datosjTableEN.getSelectedRow();
						Lote lote = listLoteEnvase.get(pos);
						lote.setEstado_revision(true);
						GregorianCalendar calendar = (GregorianCalendar) fechajCalendarEN.getCalendar();
						int j = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,j);
				        if(Rechazado(listLoteEnvase.get(pos))== false){
				        if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_etiquetado())|| date.equals(lote.getFecha_etiquetado()))){
				        	lote.setFecha_envase(date);
				        	lote.setEstado_envase(true);
				        	lote.setCod_estado(5);
					        }
				        else{
				        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida, rectifíquela!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
					 }
			        else{
			        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
						float cantidad_old = lote.getCant_unidades();
				       
				        if(cantidad_old >=(Float.valueOf(cantidadUnidadesjTextFieldEN.getText()))){
				        	 lote.setCant_unidades(Float.valueOf(cantidadUnidadesjTextFieldEN.getText()));
						        	}
						        else
						        JOptionPane.showMessageDialog(new JPanel(), "La cantidad de unidades insertada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					  try {
				        	Lote_Service.updateLote(lote, lote.getCod());
				        	for (int w = listLoteEnvase.size(); w > 0 ; w--) {
				        		datosdefaultTableModelEN.removeRow(w - 1);
				        	}
				        	listLote.clear();
				        	listLoteEnvase.clear();
				        	LlenarTablaEnvase(); 
				        }catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        codigoMjLabelEN.setText("");
						tipoMjLabelEN.setText("");
						formatoMjLabelEN.setText("");
						cantidadUnidadesjTextFieldEN.setText(null);
						cantidadUnidadesjTextFieldEN.setEnabled(false);
						modificarjButtonEN.setEnabled(false);
						jComboBoxRechazarEn.setEnabled(false);
						datosjTableEN.clearSelection();
						Lote_Visual.getLote_Visual().LlenarTablaLote();
					}
					}
				}
			});
		}
		return envasejButton;
	}

	private JButton getModificarjButtonEN() {
		if (modificarjButtonEN == null) {
			modificarjButtonEN = new JButton();
			modificarjButtonEN.setBounds(new Rectangle(800, 545, 116, 26));
			modificarjButtonEN.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButtonEN.setText("Modificar");
			modificarjButtonEN.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelEN.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int pos = datosjTableEN.getSelectedRow();
					Lote lote = listLoteEnvase.get(pos);
					GregorianCalendar calendar = (GregorianCalendar) fechajCalendarEN.getCalendar();
					int j = calendar.get(Calendar.DATE);
					int k = calendar.get(Calendar.MONTH);
					int p = calendar.get(Calendar.YEAR);
					p = p - 1900;
					Date date = new Date(p,k,j);
					if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_etiquetado())|| date.equals(lote.getFecha_etiquetado()))){
				        	lote.setFecha_envase(date);
					        }
					        else{
					        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					        }
					  float cantidad_old = lote.getCant_unidades();
					
					 if(cantidad_old >=(Float.valueOf(cantidadUnidadesjTextFieldEN.getText()))){
						 lote.setCant_unidades(Float.valueOf(cantidadUnidadesjTextFieldEN.getText()));
							}
					        else
					        JOptionPane.showMessageDialog(new JPanel(), "La cantidad de unidades insertada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					       
					try {
						Lote_Service.updateLote(lote, lote.getCod());
						if(jComboBoxRechazarEn.getSelectedIndex()==0){
							lote.setFecha(date);
							Lote_Service.createLoteRechazado(lote);
						}
						else{
							lote.setFecha(null);
							Lote_Service.deleteLoteRechazado(lote.getCod());
						}
						LlenarTablaEnvase();
						Traza traza = new Traza();
						String operacion = "U-" +"Tabla: lote_produccion" + ", Lote:"+ lote.getCod();;
						if (Traza_Servicio.existeTraza(loguiado.getNombre(), fechaActual)== true){
							traza= FindTraza(loguiado.getNombre(), fechaActual);
							traza.setOperacion(traza.getOperacion()+ "/ "+ operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.updateTraza(traza, loguiado.getNombre(), fechaActual);
						}
						else{
							traza.setOperacion(operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.createTraza(traza);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					codigoMjLabelEN.setText("");
					tipoMjLabelEN.setText("");
					formatoMjLabelEN.setText("");
					cantidadUnidadesjTextFieldEN.setText("");
					modificarjButtonEN.setEnabled(false);
					EliminarEnjButton.setEnabled(false);
					datosjTableEN.clearSelection();
					jComboBoxRechazarEn.setEnabled(false);
				}
				}
			});
		}
		return modificarjButtonEN;
	}

	private JTextField getCantidadUnidadesjTextFieldEN() {
		if (cantidadUnidadesjTextFieldEN == null) {
			cantidadUnidadesjTextFieldEN = new JTextField();
			cantidadUnidadesjTextFieldEN.setLocation(new Point(152, 80));
			cantidadUnidadesjTextFieldEN.setSize(new Dimension(123, 19));
			Validaciones.validateDigitAndComma(cantidadUnidadesjTextFieldEN);
		}
		return cantidadUnidadesjTextFieldEN;
	}

	private DefaultTableModel getDatosdefaultTableModelEN() {
		if (datosdefaultTableModelEN == null) {
			datosdefaultTableModelEN = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Destino", "Cantidad Unidades", "D/A", "Fecha Elaboración", "Fecha Envase"}, 0);
		}
		return datosdefaultTableModelEN;
	}

	private JCalendar getFechajCalendarEN() {
		if (fechajCalendarEN == null) {
			fechajCalendarEN = new JCalendar();
			fechajCalendarEN.setLocation(new Point(290, 43));
			fechajCalendarEN.setSize(new Dimension(193, 151));
		}
		return fechajCalendarEN;
	}
	private JButton getJButtonReporteEnv() {
		if (jButtonReporteEnv == null) {
			jButtonReporteEnv = new JButton();
			jButtonReporteEnv.setBounds(new Rectangle(639, 545, 147, 26));
			jButtonReporteEnv.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			jButtonReporteEnv.setText("Generar Reporte");
			jButtonReporteEnv.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					/*Reportes reportes = new Reportes();
					reportes.CargarReportesEnvase();*/
					outputToEXL excel = new outputToEXL(addressEnv);
					excel.saveEnvase(3);
				}
			});
		}
		return jButtonReporteEnv;
	}
	private JComboBox getJComboBoxRechazarEn() {
		if (jComboBoxRechazarEn == null) {
			jComboBoxRechazarEn = new JComboBox();
			jComboBoxRechazarEn.setBounds(new Rectangle(152, 140, 123, 19));
			jComboBoxRechazarEn.setModel(getdefaultComboBoxModelRechazarEn());
		}
		return jComboBoxRechazarEn;
	}
	private DefaultComboBoxModel getdefaultComboBoxModelRechazarEn() {
		if (defaultComboBoxModelRechazarEn == null) {
			defaultComboBoxModelRechazarEn = new DefaultComboBoxModel();
			defaultComboBoxModelRechazarEn.addElement("Si");
			defaultComboBoxModelRechazarEn.addElement("No");
		}
		return defaultComboBoxModelRechazarEn;
	}
//	-------------------------------------------------- DatosPendientesAF --------------------------------------------------------------------------------------
	public void ActualizarInterfazAF(){
		ConnectionBD.testConnect();
		formatojComboBoxAF.setSelectedIndex(0);
		tipojComboBoxAF.setSelectedIndex(0);
		productojComboBoxAF.setSelectedIndex(0);
		fechBjCalendarAF.setEnabled(false);
		modificarjButtonAF.setEnabled(false);
		EliminarAjButton.setEnabled(false);
		jComboBoxRechazarAF.setEnabled(false);
		listLote.clear();
		listFiltros.clear();
		LlenarTablaAF();
	}
	
	public void LlenarTablaAF (){
		try {
			for (int w = datosAFdefaultTableModel.getRowCount(); w > 0 ; w--) {
				datosAFdefaultTableModel.removeRow(w - 1);
			}
			listLoteAF.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
			String Nombre_prod;
			for (int i = 0; i < listLote.size(); i++) {
				Nombre_prod = Nombre_Prod(listLote.get(i));
				if(listLote.get(i).getCod_estado() == 5 && listLote.get(i).isEstado_envase()){
					if(listLote.get(i).getCodigo_tipo_lote() == 1)
						getDatosAFdefaultTableModel().addRow(new Object[]{listLote.get(i), Nombre_prod, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_aprobacion_final()});
					else getDatosAFdefaultTableModel().addRow(new Object[]{listLote.get(i), Nombre_prod, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(), listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_aprobacion_final()});
					listLoteAF.add(listLote.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codigoMjLabelAF.setText("");
		tipoMjLabelAF.setText("");
		formatoMjLabelAF.setText("");
		jLabelAF.setText("");
		modificarjButtonAF.setEnabled(false);
		datosAFjTable.clearSelection();
	}

	private JPanel getJContentPaneAF() {
		if (jContentPaneAF == null) {
			logojLabelAF = new JLabel();
			logojLabelAF.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabelAF.setLocation(new Point(961, 1));
			logojLabelAF.setSize(new Dimension(85, 71));
			logojLabelAF.setText("");
			tipoMjLabelAF = new JLabel();
			tipoMjLabelAF.setText("");
			tipoMjLabelAF.setSize(new Dimension(123, 19));
			tipoMjLabelAF.setLocation(new Point(145, 111));
			formatoMjLabelAF = new JLabel();
			formatoMjLabelAF.setText("");
			formatoMjLabelAF.setSize(new Dimension(123, 19));
			formatoMjLabelAF.setLocation(new Point(145, 49));
			codigoMjLabelAF = new JLabel();
			codigoMjLabelAF.setText("");
			codigoMjLabelAF.setSize(new Dimension(123, 19));
			codigoMjLabelAF.setLocation(new Point(145, 20));
			fechaAFjLabel = new JLabel();
			fechaAFjLabel.setText("Fecha de Revisión:");
			fechaAFjLabel.setBounds(new Rectangle(285, 18, 105, 16));
			tipojLabelAF = new JLabel();
			tipojLabelAF.setText("Destino:");
			tipojLabelAF.setBounds(new Rectangle(80, 112, 52, 16));
			cantidadUnidadesjLabelAF = new JLabel();
			cantidadUnidadesjLabelAF.setText("Cantidad de Unidades:");
			cantidadUnidadesjLabelAF.setBounds(new Rectangle(6, 82, 126, 16));
			formatojLabelAF = new JLabel();
			formatojLabelAF.setText("Formato:");
			formatojLabelAF.setBounds(new Rectangle(82, 50, 50, 16));
			codigojLabelAF = new JLabel();
			codigojLabelAF.setText("Código:");
			codigojLabelAF.setBounds(new Rectangle(90, 21, 42, 16));
			jContentPaneAF = new JPanel();
			jContentPaneAF.setLayout(null);
			jContentPaneAF.setSize(new Dimension(1049, 635));
			jContentPaneAF.add(getDatosjPanelAF(), null);
			jContentPaneAF.add(getJSeparatorAF(), null);
			jContentPaneAF.add(getDatosjScrollPaneAF(), null);
			jContentPaneAF.add(getAprobacionFjButton(), null);
			jContentPaneAF.add(getModificarjButtonAF(), null);
			jContentPaneAF.add(logojLabelAF, null);
			jContentPaneAF.add(getJPanelBuscarAF(), null);
			jContentPaneAF.add(getJButtonReporteAF(), null);
			jContentPaneAF.add(getEliminarAjButton(), null);
			jContentPaneAF.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					codigoMjLabelAF.setText("");
					tipoMjLabelAF.setText("");
					formatoMjLabelAF.setText("");
					jLabelAF.setText("");
					modificarjButtonAF.setEnabled(false);
					EliminarAjButton.setEnabled(false);
					jComboBoxRechazarAF.setEnabled(false);
					datosAFjTable.clearSelection();
				}
			});
		}
		return jContentPaneAF;
	}

	private JPanel getDatosjPanelAF() {
		if (datosjPanelAF == null) {
			jLabelRechazar = new JLabel();
			jLabelRechazar.setBounds(new Rectangle(65, 144, 67, 16));
			jLabelRechazar.setText("Rechazado:");
			jLabelAF = new JLabel();
			jLabelAF.setText("");
			jLabelAF.setSize(new Dimension(123, 19));
			jLabelAF.setLocation(new Point(145, 81));
			datosjPanelAF = new JPanel();
			datosjPanelAF.setLayout(null);
			datosjPanelAF.setBorder(BorderFactory.createTitledBorder(null, "Datos Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			datosjPanelAF.setLocation(new Point(15, 15));
			datosjPanelAF.setSize(new Dimension(495, 209));
			datosjPanelAF.add(codigojLabelAF, null);
			datosjPanelAF.add(formatojLabelAF, null);
			datosjPanelAF.add(codigoMjLabelAF, null);
			datosjPanelAF.add(formatoMjLabelAF, null);
			datosjPanelAF.add(cantidadUnidadesjLabelAF, null);
			datosjPanelAF.add(tipojLabelAF, null);
			datosjPanelAF.add(tipoMjLabelAF, null);
			datosjPanelAF.add(getFechajCalendarAF(), null);
			datosjPanelAF.add(fechaAFjLabel, null);
			datosjPanelAF.add(jLabelAF, null);
			datosjPanelAF.add(jLabelRechazar, null);
			datosjPanelAF.add(getJComboBoxRechazarAF(), null);
		}
		return datosjPanelAF;
	}
	
	private JPanel getJPanelBuscarAF() {
		if (jPanelBuscarAF == null) {
			nombrePjLabelAF = new JLabel();
			nombrePjLabelAF.setBounds(new Rectangle(7, 24, 58, 15));
			nombrePjLabelAF.setText("Producto:");
			tipoBjLabelAF = new JLabel();
			tipoBjLabelAF.setBounds(new Rectangle(9, 81, 54, 15));
			tipoBjLabelAF.setText("Destino:");
			formatoBjLabelAF = new JLabel();
			formatoBjLabelAF.setBounds(new Rectangle(10, 52, 52, 15));
			formatoBjLabelAF.setText("Formato:");
			jPanelBuscarAF = new JPanel();
			jPanelBuscarAF.setLayout(null);
			jPanelBuscarAF.setBorder(BorderFactory.createTitledBorder(null, "Buscar Lote", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelBuscarAF.setBounds(new Rectangle(511, 15, 438, 209));
			jPanelBuscarAF.add(formatoBjLabelAF, null);
			jPanelBuscarAF.add(tipoBjLabelAF, null);
			jPanelBuscarAF.add(nombrePjLabelAF, null);
			jPanelBuscarAF.add(getFormatojComboBoxAF(), null);
			jPanelBuscarAF.add(getTipojComboBoxAF(), null);
			jPanelBuscarAF.add(getProductojComboBoxAF(), null);
			jPanelBuscarAF.add(getFechBjCalendarAF(), null);
			jPanelBuscarAF.add(getBuscarjButtonAF(), null);
			jPanelBuscarAF.add(getFechaElaboracionjCheckBoxAF(), null);
			
		}
		return jPanelBuscarAF;
	}
	
	private JButton getBuscarjButtonAF() {
		if (buscarjButtonAF == null) {
			buscarjButtonAF = new JButton();
			buscarjButtonAF.setBounds(new Rectangle(15, 134, 37, 33));
			buscarjButtonAF.setIcon(new ImageIcon(getClass().getResource("/imagenes/search12.jpg")));
			buscarjButtonAF.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {	
					datosPAjTable.removeAll();
					String producto = null;
					String Nombre_prod;
					//si no hay seleccionado nada
					if(formatojComboBoxAF.getSelectedIndex() == 0 && tipojComboBoxAF.getSelectedIndex() == 0 && productojComboBoxAF.getSelectedIndex() == 0 && !fechaelaboracionjCheckBoxAF.isSelected())
					  {
						listFiltros.clear();
						LlenarTablaAF();
					}
					else
					if(listFiltros.isEmpty()){
						for (int w = datosAFdefaultTableModel.getRowCount(); w > 0 ; w--) {
							datosAFdefaultTableModel.removeRow(w - 1);
						}
						    String formato = null;
							int tipo = 0;
							if(formatojComboBoxAF.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxAF.getSelectedItem();}
							if(tipojComboBoxAF.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxAF.getSelectedIndex();}
							if(productojComboBoxAF.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxAF.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxAF.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarAF.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);	
							}
							try {
								listFiltros = Lote_Service.listFiltros(5, formato, tipo, producto, dateElaboracion);
								for (int i = 0; i < listFiltros.size(); i++) {
								   Nombre_prod = Nombre_Prod(listFiltros.get(i));
										if(listFiltros.get(i).getCodigo_tipo_lote() == 1){
											getDatosAFdefaultTableModel().addRow(new Object[]{listFiltros.get(i),Nombre_prod, listFiltros.get(i).getFormato(), "Nacional", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_aprobacion_final()});
											}
										else {
											getDatosAFdefaultTableModel().addRow(new Object[]{listFiltros.get(i),Nombre_prod, listFiltros.get(i).getFormato(), "Exportación", listFiltros.get(i).getCant_unidades(), listFiltros.get(i).getDiasAprob(), listFiltros.get(i).getFecha_elaboracion(), listFiltros.get(i).getFecha_aprobacion_final()});
											}
									   }
									}	
							 catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				       else {
				    	    String formato = null;
						    int tipo = 0;
							if(formatojComboBoxAF.getSelectedIndex() != 0){
								formato = (String)formatojComboBoxAF.getSelectedItem();}
							for (int w = datosAFdefaultTableModel.getRowCount(); w > 0 ; w--) {
								datosAFdefaultTableModel.removeRow(w - 1);
							}
							listFiltros.clear();
						
							if(tipojComboBoxAF.getSelectedIndex() != 0)
							  { tipo = tipojComboBoxAF.getSelectedIndex();}
							if(productojComboBoxAF.getSelectedIndex() != 0)
							  { producto = (String)productojComboBoxAF.getSelectedItem();}
							Date dateElaboracion = null;
							if(fechaelaboracionjCheckBoxAF.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechBjCalendarAF.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateElaboracion = new Date(p,k,i);
							   }
							try {
								listFiltros = Lote_Service.listFiltros(5, formato, tipo, producto, dateElaboracion);
								for (int j = 0; j < listFiltros.size(); j++) {
							       Nombre_prod = Nombre_Prod(listFiltros.get(j));
										if(listFiltros.get(j).getCodigo_tipo_lote() == 1){
											getDatosAFdefaultTableModel().addRow(new Object[]{listFiltros.get(j),Nombre_prod, listFiltros.get(j).getFormato(), "Nacional", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_aprobacion_final()});
											}
										else {
											getDatosAFdefaultTableModel().addRow(new Object[]{listFiltros.get(j),Nombre_prod, listFiltros.get(j).getFormato(), "Exportación", listFiltros.get(j).getCant_unidades(), listFiltros.get(j).getDiasAprob(), listFiltros.get(j).getFecha_elaboracion(), listFiltros.get(j).getFecha_aprobacion_final()});
											}
									  }
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						  }
						}
			});
		}
		return buscarjButtonAF;
	}

	private JCalendar getFechBjCalendarAF() {
		if (fechBjCalendarAF == null) {
			fechBjCalendarAF = new JCalendar();
			fechBjCalendarAF.setBounds(new Rectangle(232, 49, 193, 151));
		}
		return fechBjCalendarAF;
	}
	
	private JCheckBox getFechaElaboracionjCheckBoxAF() {
		if (fechaelaboracionjCheckBoxAF == null) {
			fechaelaboracionjCheckBoxAF = new JCheckBox();
			fechaelaboracionjCheckBoxAF.setText("Fecha de Elaboración:");
			fechaelaboracionjCheckBoxAF.setBounds(new Rectangle(231, 19, 155, 21));
			fechaelaboracionjCheckBoxAF.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechaelaboracionjCheckBoxAF.isSelected())
						fechBjCalendarAF.setEnabled(true);
					else fechBjCalendarAF.setEnabled(false);
				}
			});
		}
		return fechaelaboracionjCheckBoxAF;
	}
	
	private JComboBox getProductojComboBoxAF() {
		if (productojComboBoxAF == null) {
			productojComboBoxAF = new JComboBox();
			productojComboBoxAF.setBounds(new Rectangle(77, 24, 136, 20));
			productojComboBoxAF.setModel(getProductodefaultComboBoxModelAF());
			productojComboBoxAF.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String producto = null;
					String codigo = null;
					List<String> listaf = new ArrayList<String>();
					if(productojComboBoxAF.getSelectedIndex() != 0){
					    producto = (String) productojComboBoxAF.getSelectedItem();
					    codigo = Cod_Producto(producto);
						formatodefaultComboBoxModelAF.removeAllElements();
						listaf.clear();
					    listaf = Formatos(codigo);
					    for(int j=0; j<listaf.size(); j++){
					      formatodefaultComboBoxModelAF.addElement(listaf.get(j));
						 }	
					   }
					else
						if(productojComboBoxAF.getSelectedItem().equals("<Seleccione>")){
						formatodefaultComboBoxModelAF.removeAllElements();
						formatodefaultComboBoxModelAF.addElement("<Seleccione>");
						try {
							listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
							for (int i = 0; i < listFormatos.size(); i++) {
								formatodefaultComboBoxModelAF.addElement(listFormatos.get(i));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
			});
		}
		return productojComboBoxAF;
	}
	
	private DefaultComboBoxModel getProductodefaultComboBoxModelAF() {
		if (productodefaultComboBoxModelAF == null) {
			productodefaultComboBoxModelAF = new DefaultComboBoxModel();
			productodefaultComboBoxModelAF.addElement("<Seleccione>");
			try {
				listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProducto.size(); i++) {
					productodefaultComboBoxModelAF.addElement(listProducto.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModelAF;
	}
	
	private JComboBox getTipojComboBoxAF() {
		if (tipojComboBoxAF == null) {
			tipojComboBoxAF = new JComboBox();
			tipojComboBoxAF.setBounds(new Rectangle(77, 81, 136, 20));
			tipojComboBoxAF.setModel(getTipodefaultComboBoxModelAF());
		}
		return tipojComboBoxAF;
	}
	private DefaultComboBoxModel getTipodefaultComboBoxModelAF() {
		if (tipodefaultComboBoxModelAF == null) {
			tipodefaultComboBoxModelAF = new DefaultComboBoxModel();
			tipodefaultComboBoxModelAF.addElement("<Seleccione>");
			tipodefaultComboBoxModelAF.addElement("Nacional");
			tipodefaultComboBoxModelAF.addElement("Exportación");
		}
		return tipodefaultComboBoxModelAF;
	}
	
	private JComboBox getFormatojComboBoxAF() {
		if (formatojComboBoxAF == null) {
			formatojComboBoxAF = new JComboBox();
			formatojComboBoxAF.setBounds(new Rectangle(77, 52, 136, 20));
			formatojComboBoxAF.setModel(getFormatodefaultComboBoxModelAF());
		}
		return formatojComboBoxAF;
	}
	private DefaultComboBoxModel getFormatodefaultComboBoxModelAF() {
		if (formatodefaultComboBoxModelAF == null) {
			formatodefaultComboBoxModelAF = new DefaultComboBoxModel();
			formatodefaultComboBoxModelAF.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModelAF.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModelAF;
	}

	private JSeparator getJSeparatorAF() {
		if (jSeparatorAF == null) {
			jSeparatorAF = new JSeparator();
			jSeparatorAF.setBounds(new Rectangle(14, 232, 1019, 8));
		}
		return jSeparatorAF;
	}

	private JScrollPane getDatosjScrollPaneAF() {
		if (datosAFjScrollPane == null) {
			datosAFjScrollPane = new JScrollPane();
			datosAFjScrollPane.setBounds(new Rectangle(17, 242, 1017, 293));
			datosAFjScrollPane.setViewportView(getDatosAFjTable());
		}
		return datosAFjScrollPane;
	}

	private JTable getDatosAFjTable() {
		if (datosAFjTable == null) {
			datosAFjTable = new JTable();
			datosAFjTable.setModel(getDatosAFdefaultTableModel());
			datosAFjTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
			datosAFjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosAFjTable.getSelectedRow();
					Lote lote = listLoteAF.get(pos);
					modificarjButtonAF.setEnabled(true);
					EliminarAjButton.setEnabled(true);
					jComboBoxRechazarAF.setEnabled(true);
					codigoMjLabelAF.setText(lote.getCod());
					if(lote.getCodigo_tipo_lote() == 1)
						tipoMjLabelAF.setText("Nacional");
					else tipoMjLabelAF.setText("Exportación");
					if(Rechazado(lote)== true){
						jComboBoxRechazarAF.setSelectedIndex(0);
					}
					else{
						jComboBoxRechazarAF.setSelectedIndex(1);
					}
					formatoMjLabelAF.setText(String.valueOf(lote.getFormato()));
					jLabelAF.setText(String.valueOf(lote.getCant_unidades()));
				}
			});
		}
		return datosAFjTable;
	}

	private JButton getAprobacionFjButton() {
		if (AFjButton == null) {
			AFjButton = new JButton();
			AFjButton.setBounds(new Rectangle(15, 545, 147, 26));
			AFjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			AFjButton.setText("Aprobación Final");
			AFjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(codigoMjLabelAF.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un lote de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					int pos = datosAFjTable.getSelectedRow();
			        try {
			        	Lote lote = Lote_Service.getLoteVO(listLoteAF.get(pos).getCod());
						String cod_old = lote.getCod();
						GregorianCalendar calendar = (GregorianCalendar) fechaAFjCalendar.getCalendar();
						int l = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,l);
				        if(Rechazado(listLoteAF.get(pos))== false){
				        if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_envase())|| date.equals(lote.getFecha_envase()))){
					        	lote.setFecha_aprobacion_final(date);
					        	lote.setEstado_aprobacion_final(true);
							    lote.setCod_estado(6);
							    Lote_Service.updateLote(lote,cod_old);
						        }
				        else{
				        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida, rectifíquela!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
					 }
			        else{
			        	JOptionPane.showMessageDialog(new JPanel(), "Operación no válida, lote rechazado!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
					    @SuppressWarnings("unused")
						Producto producto = Prod_Services.getProdVO(lote.getProducto());
						
						for (int w = listLoteAF.size(); w > 0 ; w--) {
							datosAFdefaultTableModel.removeRow(w - 1);
						}
						listLote.clear();
						listLoteAF.clear();
						listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
						String Nombre_prod;
						for (int i = 0; i < listLote.size(); i++) {
							Nombre_prod = Nombre_Prod(listLote.get(i));
							if(listLote.get(i).getCod_estado() == 5){
								if(listLote.get(i).getCodigo_tipo_lote() == 1)
									getDatosAFdefaultTableModel().addRow(new Object[]{listLote.get(i),Nombre_prod, listLote.get(i).getFormato(), "Nacional", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(),listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_aprobacion_final()});
								else getDatosAFdefaultTableModel().addRow(new Object[]{listLote.get(i),Nombre_prod, listLote.get(i).getFormato(), "Exportación", listLote.get(i).getCant_unidades(), listLote.get(i).getDiasAprob(),listLote.get(i).getFecha_elaboracion(), listLote.get(i).getFecha_aprobacion_final()});
								listLoteAF.add(listLote.get(i));
							}
						}
			        } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        codigoMjLabelAF.setText("");
					tipoMjLabelAF.setText("");
					formatoMjLabelAF.setText("");
					jLabelAF.setText("");
					modificarjButtonAF.setEnabled(false);
					jComboBoxRechazarAF.setEnabled(false);
					datosAFjTable.clearSelection();
					Lote_Visual.getLote_Visual().LlenarTablaLote();
					}
				}
			});
		}
		return AFjButton;
	}

	private JButton getModificarjButtonAF() {
		if (modificarjButtonAF == null) {
			modificarjButtonAF = new JButton();
			modificarjButtonAF.setBounds(new Rectangle(801, 545, 116, 26));
			modificarjButtonAF.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButtonAF.setText("Modificar");
			modificarjButtonAF.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosAFjTable.getSelectedRow();
					Lote lote = listLoteAF.get(pos);
					GregorianCalendar calendar = (GregorianCalendar) fechaAFjCalendar.getCalendar();
					int j = calendar.get(Calendar.DATE);
					int k = calendar.get(Calendar.MONTH);
					int p = calendar.get(Calendar.YEAR);
					p = p - 1900;
					Date date = new Date(p,k,j); 
					if ((date.after(lote.getFecha_elaboracion())|| date.equals(lote.getFecha_elaboracion())) && (date.after(lote.getFecha_envase())|| date.equals(lote.getFecha_envase()))){
				        	lote.setFecha_aprobacion_final(date);
					        }
					        else{
					        	JOptionPane.showMessageDialog(new JPanel(), "La fecha selecionada no es válida", "Error", JOptionPane.ERROR_MESSAGE);
					        }
					try {
						Lote_Service.updateLote(lote, lote.getCod());
						if(jComboBoxRechazarAF.getSelectedIndex()==0){
							lote.setFecha(date);
							Lote_Service.createLoteRechazado(lote);
						}
						else{
							lote.setFecha(null);
							Lote_Service.deleteLoteRechazado(lote.getCod());
						}
						LlenarTablaAF();
						Traza traza = new Traza();
						String operacion = "U-" +"Tabla: lote_produccion" + ", Lote:"+ lote.getCod();;
						if (Traza_Servicio.existeTraza(loguiado.getNombre(), fechaActual)== true){
							traza= FindTraza(loguiado.getNombre(), fechaActual);
							traza.setOperacion(traza.getOperacion()+ "/ "+ operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.updateTraza(traza, loguiado.getNombre(), fechaActual);
						}
						else{
							traza.setOperacion(operacion);
							traza.setUsuario(loguiado.getNombre());
							traza.setFecha(fechaActual);
							Traza_Servicio.createTraza(traza);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					codigoMjLabelAF.setText("");
					tipoMjLabelAF.setText("");
					formatoMjLabelAF.setText("");
					jLabelAF.setText("");
					modificarjButtonAF.setEnabled(false);
					EliminarAjButton.setEnabled(false);
					datosAFjTable.clearSelection();
					jComboBoxRechazarAF.setEnabled(false);
				}
			});
		}
		return modificarjButtonAF;
	}

	private JCalendar getFechajCalendarAF() {
		if (fechaAFjCalendar == null) {
			fechaAFjCalendar = new JCalendar();
			fechaAFjCalendar.setLocation(new Point(288, 41));
			fechaAFjCalendar.setSize(new Dimension(196, 153));
		}
		return fechaAFjCalendar;
	}

	private DefaultTableModel getDatosAFdefaultTableModel() {
		if (datosAFdefaultTableModel == null) {
			datosAFdefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Destino", "Cantidad Unidades", "D/A", "Fecha Elaboración", "Fecha Final"}, 0);
		}
		return datosAFdefaultTableModel;
	}

	private GregorianCalendar getCalendar1() {
		if (calendar1 == null) {
			calendar1 = new GregorianCalendar();
			calendar1.setTimeInMillis(System.currentTimeMillis());
		}
		return calendar1;
	}

	private JButton getJButtonReporteAF() {
		if (jButtonReporteAF == null) {
			jButtonReporteAF = new JButton();
			jButtonReporteAF.setBounds(new Rectangle(639, 545, 147, 26));
			jButtonReporteAF.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			jButtonReporteAF.setText("Generar Reporte");
			jButtonReporteAF.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()'
					/*Reportes reportes = new Reportes();
					reportes.CargarReportesAprobacionF();*/
					outputToEXL excel = new outputToEXL(addressAF);
					excel.saveAproF(3);
				}
			});
		}
		return jButtonReporteAF;
	}

	private JButton getEliminarEjButton() {
		if (EliminarEjButton == null) {
			EliminarEjButton = new JButton();
			EliminarEjButton.setBounds(new Rectangle(928, 550, 99, 26));
			EliminarEjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			EliminarEjButton.setText("Eliminar");
			EliminarEjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosPEjTable.getSelectedRow();
					Lote lote = listLoteEtiquetado.get(pos);
			        lote.setEstado_revision(false);
			        lote.setCod_estado(2);
			        lote.setFecha_revisado(null);
			        lote.setFecha_etiquetado(null);
			        try {
						Lote_Service.updateLote(lote, lote.getCod());
						LlenarTablaRevision();
						LlenarTablaEtiquetado();
						modificarjButtonE.setEnabled(false);
						EliminarEjButton.setEnabled(false);
						jComboBoxRechazarE.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
				}
			});
		}
		return EliminarEjButton;
	}

	private JButton getEliminarEnjButton() {
		if (EliminarEnjButton == null) {
			EliminarEnjButton = new JButton();
			EliminarEnjButton.setBounds(new Rectangle(935, 545, 99, 26));
			EliminarEnjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			EliminarEnjButton.setText("Eliminar");
			EliminarEnjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosjTableEN.getSelectedRow();
					Lote lote = listLoteEnvase.get(pos);
			        lote.setEstado_etiquetado(false);
			        lote.setCod_estado(3);
			        lote.setFecha_envase(null);
			        lote.setFecha_etiquetado(null);
			        try {
						Lote_Service.updateLote(lote, lote.getCod());
						LlenarTablaEtiquetado();
						LlenarTablaEnvase();
						modificarjButtonEN.setEnabled(false);
						EliminarEnjButton.setEnabled(false);
						jComboBoxRechazarEn.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
				}
			});
		}
		return EliminarEnjButton;
	}

	private JButton getEliminarAjButton() {
		if (EliminarAjButton == null) {
			EliminarAjButton = new JButton();
			EliminarAjButton.setBounds(new Rectangle(934, 545, 99, 26));
			EliminarAjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			EliminarAjButton.setText("Eliminar");
			EliminarAjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosAFjTable.getSelectedRow();
					Lote lote = listLoteAF.get(pos);
					lote.setEstado_envase(false);
			        lote.setCod_estado(4);
			        lote.setFecha_envase(null);
			        lote.setFecha_aprobacion_final(null);
			        try {
						Lote_Service.updateLote(lote, lote.getCod());
						LlenarTablaEnvase();
						LlenarTablaAF();
						modificarjButtonAF.setEnabled(false);
						EliminarAjButton.setEnabled(false);
						jComboBoxRechazarAF.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        
				}
			});
		}
		return EliminarAjButton;
	}
	private JComboBox getJComboBoxRechazarAF() {
		if (jComboBoxRechazarAF == null) {
			jComboBoxRechazarAF = new JComboBox();
			jComboBoxRechazarAF.setBounds(new Rectangle(145, 144, 123, 19));
			jComboBoxRechazarAF.setModel(getdefaultComboBoxModelRechazarAF());
		}
		return jComboBoxRechazarAF;
	}
	private DefaultComboBoxModel getdefaultComboBoxModelRechazarAF() {
		if (defaultComboBoxModelRechazarAF == null) {
			defaultComboBoxModelRechazarAF = new DefaultComboBoxModel();
			defaultComboBoxModelRechazarAF.addElement("Si");
			defaultComboBoxModelRechazarAF.addElement("No");
		}
		return defaultComboBoxModelRechazarAF;
	}
	
}  //  @jve:decl-index=0:visual-constraint="6,6"
