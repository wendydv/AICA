package aica.visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import aica.model.Control;
import aica.model.Formato_Producto;
import aica.model.Lote;
import aica.model.Producto;
import aica.model.Traza;
import aica.model.Usuario;
import aica.service.ConnectionBD;
import aica.service.Lote_Service;
import aica.service.Prod_Services;
import aica.service.Traza_Servicio;
import aica.utiles.UneditableTableModel;
import com.toedter.calendar.JCalendar;

public class Lote_Visual extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Lote_Visual lote_visual = null;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JLabel jLabel1 = null;

	private JScrollPane jScrollPane = null;

	private JList productosjList = null;

	private JTextField codigojTextField = null;

	private JButton insertarjButton = null;

	private JSeparator jSeparator = null;

	private JScrollPane datosLotesjScrollPane1 = null;

	private JTable datosLotesjTable = null;

	private JButton modificarjButton = null;

	private JButton eliminarjButton = null;

	private DefaultTableModel datosLotesdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="919,169"

	private JLabel jLabel6 = null;

	private JLabel jLabel631 = null;
	
	public ArrayList<Producto> listProd = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProdnew = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLotenew = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Lote> listLote = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Traza> listTrazas = new ArrayList<Traza>();  //  @jve:decl-index=0:
	
	public ArrayList<Formato_Producto> listProdForm= new ArrayList<Formato_Producto>();  //  @jve:decl-index=0:

	private DefaultListModel listProddefaultListModel = null;  //  @jve:decl-index=0:visual-constraint="941,234"

	private JLabel formatojLabel7 = null;

	private JLabel cant_unijLabel7 = null;

	private JTextField cant_unidadesjTextField = null;

	private JLabel fecha_elaboracionjLabel7 = null;

	private JLabel jLabel6411 = null;

	private JLabel jLabel6412 = null;

	private JLabel jLabel6413 = null;

	private JCalendar jCalendar = null;

	private JLabel tipojLabel7 = null;

	private JLabel jLabel64121 = null;

	private DefaultComboBoxModel tipodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="925,300"

	private JComboBox tipojComboBox = null;

	private String estado = null;

	private JComboBox formatojComboBox = null;

	private DefaultComboBoxModel formatodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="902,103"
	
	public List<Integer> listFormatos = new ArrayList<Integer>();  //  @jve:decl-index=0:

	private JLabel logojLabel2 = null;

	private JButton actualizarjButton1 = null;
	
	private Date fechaActual = null;
	
	private JLabel fechanjLabel = null;
	
	private Usuario loguiado = null;  //  @jve:decl-index=0:

	private Lote_Visual() {
		super();
		initialize();
	}
	// patron singleton
	public static Lote_Visual getLote_Visual() {
		if (lote_visual == null) {
			lote_visual = new Lote_Visual();
		}
		return lote_visual;
}
	private void initialize() {
		ConnectionBD.testConnect();
		loguiado = Control.getSessionUser();
		this.setSize(860, 654);
		this.setContentPane(getJContentPane());
		this.setTitle("Lote");
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		modificarjButton.setEnabled(false);
		eliminarjButton.setEnabled(false);
		GregorianCalendar calendar = (GregorianCalendar) jCalendar.getCalendar();
		int i = calendar.get(Calendar.DATE);
		int k = calendar.get(Calendar.MONTH);
		int p = calendar.get(Calendar.YEAR);
		p = p - 1900;
		Date date = new Date(p,k,i);
		fechaActual = date;
		LlenarTablaLote();
		LlenarListaProd();
		try {
			listTrazas =(ArrayList<Traza>)Traza_Servicio.getAllTraza();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void LlenarTablaLote(){
		try {
			for (int w = datosLotesdefaultTableModel.getRowCount(); w > 0 ; w--) {
				datosLotesdefaultTableModel.removeRow(w - 1);
			}
			listLote.clear();
			listLote = (ArrayList<Lote>) Lote_Service.getAllLote();
			listProd = (ArrayList<Producto>) Prod_Services.getAllProd();
			String producto;
			for (int w = 0; w < listLote.size(); w++) {
				producto = Nombre_Prod(listLote.get(w));
				if(listLote.get(w).getCod_estado() == 1)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Aprobación Analítica", listLote.get(w).getFecha_elaboracion()});
				if(listLote.get(w).getCod_estado() == 2)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Revisión", listLote.get(w).getFecha_elaboracion()});
				if(listLote.get(w).getCod_estado() == 3)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Etiquetado", listLote.get(w).getFecha_elaboracion()});
				if(listLote.get(w).getCod_estado() == 4)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Envase", listLote.get(w).getFecha_elaboracion()});
				if(listLote.get(w).getCod_estado() == 5)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Aprobación Final", listLote.get(w).getFecha_elaboracion()});
				if(listLote.get(w).getCod_estado() == 6)
					getDatosLotesdefaultTableModel().addRow(new Object[]{listLote.get(w).getCod(),producto, listLote.get(w).getFormato(), listLote.get(w).getCant_unidades(), listLote.get(w).getDiasAprob(), "Aprobado"+"("+listLote.get(w).getFecha_aprobacion_final()+")", listLote.get(w).getFecha_elaboracion()});
					
					 }
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void LlenarListaProd(){
	try {
		for (int w = listProddefaultListModel.getSize(); w > 0 ; w--) {
			listProddefaultListModel.remove(w - 1);
		}
		listProd.clear();
		listProd = (ArrayList<Producto>) Prod_Services.getAllProd();
		for (int i = 0; i < listProd.size(); i++) {
			getListProddefaultListModel().addElement(listProd.get(i).getNombre());
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			fechanjLabel = new JLabel();
			long fecha=System.currentTimeMillis();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(fecha);
			int i = calendar.get(Calendar.DATE);
			int k = calendar.get(Calendar.MONTH);
			int p = calendar.get(Calendar.YEAR);
			fechanjLabel.setText(""+i+"/"+(k+1)+"/"+p);
			fechanjLabel.setBounds(new Rectangle(707, 1, 61, 20));
			logojLabel2 = new JLabel();
			logojLabel2.setBounds(new Rectangle(767, 1, 86, 68));
			logojLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabel2.setText("");
			jLabel64121 = new JLabel();
			jLabel64121.setBounds(new Rectangle(97, 92, 10, 16));
			jLabel64121.setText("*");
			jLabel64121.setForeground(new Color(255, 51, 51));
			tipojLabel7 = new JLabel();
			tipojLabel7.setBounds(new Rectangle(107, 92, 51, 16));
			tipojLabel7.setText("Destino:");
			jLabel6413 = new JLabel();
			jLabel6413.setBounds(new Rectangle(29, 119, 10, 16));
			jLabel6413.setText("*");
			jLabel6413.setForeground(new Color(255, 51, 51));
			jLabel6412 = new JLabel();
			jLabel6412.setBounds(new Rectangle(22, 68, 10, 16));
			jLabel6412.setText("*");
			jLabel6412.setForeground(new Color(255, 51, 51));
			jLabel6411 = new JLabel();
			jLabel6411.setBounds(new Rectangle(99, 39, 10, 16));
			jLabel6411.setText("*");
			jLabel6411.setForeground(new Color(255, 51, 51));
			fecha_elaboracionjLabel7 = new JLabel();
			fecha_elaboracionjLabel7.setBounds(new Rectangle(38, 119, 121, 16));
			fecha_elaboracionjLabel7.setText("Fecha de Elaboracón:");
			cant_unijLabel7 = new JLabel();
			cant_unijLabel7.setBounds(new Rectangle(33, 68, 126, 16));
			cant_unijLabel7.setText("Cantidad de Unidades:");
			formatojLabel7 = new JLabel();
			formatojLabel7.setBounds(new Rectangle(110, 39, 50, 16));
			formatojLabel7.setText("Formato:");
			jLabel631 = new JLabel();
			jLabel631.setBounds(new Rectangle(395, 14, 10, 16));
			jLabel631.setText("*");
			jLabel631.setForeground(new Color(255, 51, 51));
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(106, 10, 10, 16));
			jLabel6.setForeground(new Color(255, 51, 51));
			jLabel6.setText("*");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(405, 14, 62, 16));
			jLabel1.setText("Productos:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(118, 10, 42, 16));
			jLabel.setText("Código:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setEnabled(true);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getCodigojTextField(), null);
			jContentPane.add(getInsertarjButton(), null);
			jContentPane.add(getJSeparator(), null);
			jContentPane.add(getDatosLotesjScrollPane1(), null);
			jContentPane.add(getModificarjButton(), null);
			jContentPane.add(getEliminarjButton(), null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel631, null);
			jContentPane.add(formatojLabel7, null);
			jContentPane.add(cant_unijLabel7, null);
			jContentPane.add(getCant_unidadesjTextField(), null);
			jContentPane.add(fecha_elaboracionjLabel7, null);
			jContentPane.add(jLabel6411, null);
			jContentPane.add(jLabel6412, null);
			jContentPane.add(jLabel6413, null);
			jContentPane.add(getJCalendar(), null);
			jContentPane.add(tipojLabel7, null);
			jContentPane.add(jLabel64121, null);
			jContentPane.add(getTipojComboBox(), null);
			jContentPane.add(getFormatojComboBox(), null);
			jContentPane.add(logojLabel2, null);
			jContentPane.add(getActualizarjButton1(), null);
			jContentPane.add(fechanjLabel, null);
			jContentPane.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					codigojTextField.setText("");
					codigojTextField.setEditable(true);
					tipojComboBox.setSelectedIndex(0);
					formatodefaultComboBoxModel.removeAllElements();
					formatodefaultComboBoxModel.addElement("<Seleccione>");
					cant_unidadesjTextField.setText("");
					getProductosjList().clearSelection();
					productosjList.setEnabled(true);
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);	
					datosLotesjTable.clearSelection();
				}
			});
		}
		return jContentPane;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(395, 37, 292, 261));
			jScrollPane.setViewportView(getProductosjList());
		}
		return jScrollPane;
	}

	private JList getProductosjList() {
		if (productosjList == null) {
			productosjList = new JList();
			productosjList.setModel(getListProddefaultListModel());
			productosjList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					String producto = null;
					List<String> listaf = new ArrayList<String>();
					producto = (listProd.get(getProductosjList().getSelectedIndex()).getCod_producto());
					if(productosjList.getSelectedIndex() != -1 ){
						producto = (listProd.get(getProductosjList().getSelectedIndex()).getCod_producto());//como obtener el elemento que esta en la lista de productos
						codigojTextField.setText(producto);
						if(productosjList.getSelectedIndex() != -1){
							formatodefaultComboBoxModel.removeAllElements();
							listaf.clear();
						    listaf = Formatos(producto);
						    for(int j=0; j<listaf.size(); j++){
						      formatodefaultComboBoxModel.addElement(listaf.get(j));
						 }	
					   }
					}
				}
			});
		}
		return productosjList;
	}

	private JTextField getCodigojTextField() {
		if (codigojTextField == null) {
			codigojTextField = new JTextField();
			codigojTextField.setLocation(new Point(175, 10));
			codigojTextField.setSize(new Dimension(192, 19));
		}
		return codigojTextField;
	}

	private JButton getInsertarjButton() {
		if (insertarjButton == null) {
			insertarjButton = new JButton();
			insertarjButton.setBounds(new Rectangle(11, 589, 98, 26));
			insertarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			insertarjButton.setText("Insertar");
			insertarjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e){
					String codigo_lote = getCodigojTextField().getText();
					if(codigojTextField.getText().equals("") || productosjList.getSelectedIndex() == -1 || tipodefaultComboBoxModel.equals(0)|| formatojComboBox.getSelectedIndex() == -1 || cant_unidadesjTextField.equals("")	){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					else
					    if  (getCodigojTextField().getText().equals(listProd.get(getProductosjList().getSelectedIndex()).getCod_producto())){
							  JOptionPane.showMessageDialog(new JPanel(), "El código insertado no es válido, rectifíquelo.", "Error", JOptionPane.ERROR_MESSAGE);
								} 
					    else
							try {
								if (Lote_Service.existeLote(codigo_lote)== true){
										JOptionPane.showMessageDialog(new JPanel(), "Ese código ya existe, inserte otro.", "Error", JOptionPane.ERROR_MESSAGE);
										}
								 else{
						             try {
						            	 GregorianCalendar calendar = (GregorianCalendar) jCalendar.getCalendar();
							             int i1 = calendar.get(Calendar.DATE);
							             int k1 = calendar.get(Calendar.MONTH);
							             int p1 = calendar.get(Calendar.YEAR);
							             p1 = p1 - 1900;
							             Date dateNew = new Date(p1,k1,i1);
						            	 if  ((dateNew.equals(fechaActual))||(dateNew.before(fechaActual))){
							             Lote lote = new Lote();
							             lote.setFecha_elaboracion(dateNew); 
							            	 lote.setEstado_analisis(false);
											 lote.setEstado_envase(false);
											 lote.setEstado_etiquetado(false);
											 lote.setEstado_revision(false);
											 lote.setEstado_aprobacion_final(false);
											 lote.setCod(getCodigojTextField().getText());	 
											 lote.setProducto(listProd.get(getProductosjList().getSelectedIndex()).getCod_producto());
											 lote.setFormato((String) formatojComboBox.getSelectedItem());
											 lote.setCant_unidades(Float.valueOf(getCant_unidadesjTextField().getText()));
											 lote.setCodigo_tipo_lote(tipojComboBox.getSelectedIndex());
											 float diasAbast = lote.getCant_unidades() / listProd.get(getProductosjList().getSelectedIndex()).getConsumo_diario();
											 lote.setDiasAprob(diasAbast);
											 listLote.add(lote);
											 Traza traza = new Traza();
												String operacion = "CR-" +"Tabla: lote" + ", Lote:"+ lote.getCod();;
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
							             
							try {
								Lote_Service.createLote(lote);
								listLote.add(lote);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							estado= "Aprobación Analítica";
							getDatosLotesdefaultTableModel().addRow(new Object[]{lote.getCod(),listProd.get(getProductosjList().getSelectedIndex()).getNombre(), lote.getFormato(), lote.getCant_unidades(), lote.getDiasAprob(),estado, lote.getFecha_elaboracion()});
							codigojTextField.setText("");
							tipojComboBox.setSelectedIndex(0);
							formatodefaultComboBoxModel.removeAllElements();
							formatodefaultComboBoxModel.addElement("<Seleccione>");
							cant_unidadesjTextField.setText("");
							getProductosjList().clearSelection();
							DatosPendientesEtapas.getDatosPendientesEtapas().ActualizarInterfazAnalisis();
							
						}
					else
						{
						JOptionPane.showMessageDialog(new JPanel(), "La fecha insertada no es válida, rectifíquela.", "Error", JOptionPane.ERROR_MESSAGE);
						}
												
						  }catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
							} catch (HeadlessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				}
			});
		}
		return insertarjButton;
	}

	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(13, 308, 829, 8));
		}
		return jSeparator;
	}

	private JScrollPane getDatosLotesjScrollPane1() {
		if (datosLotesjScrollPane1 == null) {
			datosLotesjScrollPane1 = new JScrollPane();
			datosLotesjScrollPane1.setBounds(new Rectangle(10, 319, 828, 260));
			datosLotesjScrollPane1.setViewportView(getDatosLotesjTable());
		}
		return datosLotesjScrollPane1;
	}
 public int encontrar(String prod){
	 int i = 0;
	 boolean enc = true;
	 while (enc  ){
		 if(listProd.get(i).getCod_producto().equals(prod)){
			 enc = false;
		 }
			 
		 else{
			 i++;
		 }
	 }
	 return i;
 }

	private JTable getDatosLotesjTable() {
		if (datosLotesjTable == null) {
			datosLotesjTable = new JTable();
			datosLotesjTable.setModel(getDatosLotesdefaultTableModel());
			datosLotesjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int pos = datosLotesjTable.getSelectedRow();
					Lote lote = listLote.get(pos);
					String producto = null;
					List<String> listaf = new ArrayList<String>();
					modificarjButton.setEnabled(true);
					insertarjButton.setEnabled(false);
					eliminarjButton.setEnabled(true);
					codigojTextField.setText(lote.getCod());
					codigojTextField.setEditable(false);
					tipojComboBox.setSelectedIndex(lote.getCodigo_tipo_lote());
					cant_unidadesjTextField.setText(String.valueOf(lote.getCant_unidades()));
					jCalendar.setDate(lote.getFecha_elaboracion());
					productosjList.setSelectedIndex(encontrar(lote.getProducto()));
					productosjList.setEnabled(false);
					producto = lote.getProducto();
					formatodefaultComboBoxModel.removeAllElements();
					listaf.clear();
				    listaf = Formatos(producto);
				    for(int j=0; j<listaf.size(); j++){
				      formatodefaultComboBoxModel.addElement(listaf.get(j));
				 }
					
				}
			});
		}
		return datosLotesjTable;
	}

	private JButton getModificarjButton() {
		if (modificarjButton == null) {
			modificarjButton = new JButton();
			modificarjButton.setBounds(new Rectangle(605, 588, 116, 26));
			modificarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButton.setText("Modificar");
			modificarjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosLotesjTable.getSelectedRow();
					
					if(codigojTextField.getText().equals("") || tipodefaultComboBoxModel.equals(0)|| formatojComboBox.getSelectedItem().equals("")|| cant_unidadesjTextField.equals("")	)
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						GregorianCalendar calendar = (GregorianCalendar) jCalendar.getCalendar();
						int i = calendar.get(Calendar.DATE);
						int k = calendar.get(Calendar.MONTH);
						int p = calendar.get(Calendar.YEAR);
						p = p - 1900;
				        Date date = new Date(p,k,i);
				        if  ((date.equals(fechaActual))||(date.before(fechaActual))){
						Lote lote = new Lote();
						lote = listLote.get(pos);
						String cod_old = lote.getCod();
						lote.setCod(getCodigojTextField().getText());
						lote.setProducto(listProd.get(getProductosjList().getSelectedIndex()).getCod_producto());
						lote.setFormato((String) formatojComboBox.getSelectedItem());
						lote.setCant_unidades(Float.valueOf(getCant_unidadesjTextField().getText()));
						lote.setCodigo_tipo_lote(tipojComboBox.getSelectedIndex());
				        lote.setFecha_elaboracion(date);
				        float diasAbast = lote.getCant_unidades() / listProd.get(getProductosjList().getSelectedIndex()).getConsumo_diario();
				        lote.setDiasAprob(diasAbast);
				        try {
							Lote_Service.updateLote(lote,cod_old);
							getDatosLotesdefaultTableModel().setValueAt(lote.getCod(), pos, 0);
							getDatosLotesdefaultTableModel().setValueAt(listProd.get(getProductosjList().getSelectedIndex()).getNombre(), pos, 1);
							getDatosLotesdefaultTableModel().setValueAt(lote.getFormato(), pos, 2);
							getDatosLotesdefaultTableModel().setValueAt(lote.getCant_unidades(), pos, 3);
							getDatosLotesdefaultTableModel().setValueAt(lote.getDiasAprob(), pos, 4);
							if(lote.getCod_estado() == 1)
				        		getDatosLotesdefaultTableModel().setValueAt("Elaborado", pos, 5);
							if(lote.getCod_estado() == 2)
				        		getDatosLotesdefaultTableModel().setValueAt("Análisis", pos, 5);
							if(lote.getCod_estado() == 3)
				        		getDatosLotesdefaultTableModel().setValueAt("Pendiente Aprobación", pos, 5);
							if(lote.getCod_estado() == 4)
				        		getDatosLotesdefaultTableModel().setValueAt("Envase", pos, 5);
							if(lote.getCod_estado() == 5)
				        		getDatosLotesdefaultTableModel().setValueAt("Etiquetado", pos, 5);
							getDatosLotesdefaultTableModel().setValueAt(lote.getFecha_elaboracion(), pos, 6);
							Traza traza = new Traza();
							String operacion = "U-" +"Tabla: lote" + ", Lote:"+ lote.getCod();;
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
						codigojTextField.setText("");
						codigojTextField.setEditable(true);
						tipojComboBox.setSelectedIndex(0);
						formatodefaultComboBoxModel.removeAllElements();
						formatodefaultComboBoxModel.addElement("<Seleccione>");
						cant_unidadesjTextField.setText("");
						getProductosjList().clearSelection();
						productosjList.setEnabled(true);
						modificarjButton.setEnabled(false);
						eliminarjButton.setEnabled(false);
						insertarjButton.setEnabled(true);	
						datosLotesjTable.clearSelection();
					}
				  else
					{
					JOptionPane.showMessageDialog(new JPanel(), "La fecha insertada no es válida, rectifíquela.", "Error", JOptionPane.ERROR_MESSAGE);
					}    
				  }
				}
			});
		
		}
		return modificarjButton;
	}

	private JButton getEliminarjButton() {
		if (eliminarjButton == null) {
			eliminarjButton = new JButton();
			eliminarjButton.setBounds(new Rectangle(738, 588, 99, 26));
			eliminarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			eliminarjButton.setText("Eliminar");
			eliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosLotesjTable.getSelectedRow();
					Lote lote;
					try {
						lote = listLote.get(pos);
						Lote_Service.deleteLote(lote.getCod());
						listLote.remove(pos);
						getDatosLotesdefaultTableModel().removeRow(pos);
						Traza traza = new Traza();
						String operacion = "D-" +"Tabla: lote" + ", Lote:"+ lote.getCod();;
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
					codigojTextField.setText("");
					codigojTextField.setEditable(true);
					tipojComboBox.setSelectedIndex(0);
					formatodefaultComboBoxModel.removeAllElements();
					formatodefaultComboBoxModel.addElement("<Seleccione>");
					cant_unidadesjTextField.setText("");
					getProductosjList().clearSelection();
					productosjList.setEnabled(true);
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);
					datosLotesjTable.clearSelection();
				}
			});
		}
		return eliminarjButton;
	}

	private DefaultTableModel getDatosLotesdefaultTableModel() {
		if (datosLotesdefaultTableModel == null) {
			datosLotesdefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Cantidad Unidades", "D/A","Estado", "Fecha Elaboración"}, 0);	
		}
		return datosLotesdefaultTableModel;
	}

	private DefaultListModel getListProddefaultListModel() {
		if (listProddefaultListModel == null) {
			listProddefaultListModel = new DefaultListModel();
		}
		return listProddefaultListModel;
	}
	public String Codigo_Prod (String nombre){
		int j=0;
		String codigo= null;
		boolean found= false;
		while (j < listProd.size()&& !found){
			if (listProd.get(j).getNombre().equals(nombre))
			 {
				codigo = listProd.get(j).getCod_producto();
				found = true;
			 }
			else j++;
		     } 
			 if (found == true){
				 return codigo;
	    }
		return null;
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

	private JTextField getCant_unidadesjTextField() {
		if (cant_unidadesjTextField == null) {
			cant_unidadesjTextField = new JTextField();
			aica.utiles.Validaciones.validateDigitAndComma(cant_unidadesjTextField);
			cant_unidadesjTextField.setLocation(new Point(175, 67));
			cant_unidadesjTextField.setSize(new Dimension(191, 19));
		}
		return cant_unidadesjTextField;
	}

	private JCalendar getJCalendar() {
		if (jCalendar == null) {
			jCalendar = new JCalendar();
			jCalendar.setBounds(new Rectangle(173, 120, 200, 167));
		}
		return jCalendar;
	}

	private DefaultComboBoxModel getTipodefaultComboBoxModel() {
		if (tipodefaultComboBoxModel == null) {
			tipodefaultComboBoxModel = new DefaultComboBoxModel();
			tipodefaultComboBoxModel.addElement("<Seleccione>");
			tipodefaultComboBoxModel.addElement("Nacional");
			tipodefaultComboBoxModel.addElement("Exportación");
		}
		return tipodefaultComboBoxModel;
	}

	private JComboBox getTipojComboBox() {
		if (tipojComboBox == null) {
			tipojComboBox = new JComboBox();
			tipojComboBox.setLocation(new Point(174, 91));
			tipojComboBox.setSize(new Dimension(191, 19));
			tipojComboBox.setModel(getTipodefaultComboBoxModel());
		}
		return tipojComboBox;
	}

	private JComboBox getFormatojComboBox() {
		if (formatojComboBox == null) {
			formatojComboBox = new JComboBox();
			formatojComboBox.setLocation(new Point(175, 39));
			formatojComboBox.setSize(new Dimension(192, 19));
			formatojComboBox.setModel(getFormatodefaultComboBoxModel());
		}
		return formatojComboBox;
	}

	private DefaultComboBoxModel getFormatodefaultComboBoxModel() {
		if (formatodefaultComboBoxModel == null) {
			formatodefaultComboBoxModel = new DefaultComboBoxModel();
			formatodefaultComboBoxModel.addElement("<Seleccione>");	
		}
		return formatodefaultComboBoxModel;
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

	private JButton getActualizarjButton1() {
		if (actualizarjButton1 == null) {
			actualizarjButton1 = new JButton();
			actualizarjButton1.setBounds(new Rectangle(694, 272, 146, 26));
			actualizarjButton1.setIcon(new ImageIcon(getClass().getResource("/imagenes/mobsync.png")));
			actualizarjButton1.setText("Actualizar Datos");
			actualizarjButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					LlenarTablaLote();
				}
			});
		}
		return actualizarjButton1;
	}
	// devuelve el nombre de un producto dado un lote
	public String Nombre_Prod (Lote lote){
		int j=0;
		String nombre= null;
		boolean found= false;
		while (j < listProd.size()&& !found){
			if (listProd.get(j).getCod_producto().equals(lote.getProducto()))
			 {
			    nombre = listProd.get(j).getNombre();
				found = true;
			 }
			else j++;
		     } 
			 if (found == true){
				 return nombre;
	    }
		return null;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
