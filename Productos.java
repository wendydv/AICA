package aica.visual;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Point;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import aica.model.Control;
import aica.model.Formato_Producto;
import aica.model.Producto;
import aica.model.Traza;
import aica.model.Usuario;
import aica.service.ConnectionBD;
import aica.service.Lote_Service;
import aica.service.Prod_Services;
import aica.service.Traza_Servicio;
import aica.utiles.UneditableTableModel;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Productos extends JFrame {

	private JPanel jProductosContentPane = null;
	
	private JLabel jnombrePLabel = null;
	
	private JLabel jconsumo_diarioPLabel = null;
	
	private JTextField jnombrePTextField = null;
	
	private JTextField jconsumoDiarioPTextField = null;
	
	private JSeparator jSeparator = null;
	
	private UneditableTableModel datosPdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="730,167"
	
	private JScrollPane jScrollPane = null;
	
	private JTable datosPjTable = null;
	
	private JButton insertarjButton = null;
	
	private JButton modificarjButton = null;
	
	private JButton eliminarjButton = null;
	
	private JLabel jcod_prodLabel = null;
	
	private JTextField jcod_prodTextField = null;
	
	private static Productos producto = null;
	
	public ArrayList<Producto> listProductos = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Traza> listTrazas = new ArrayList<Traza>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProd= new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<Formato_Producto> listProdForm= new ArrayList<Formato_Producto>();  //  @jve:decl-index=0:
	
	public ArrayList<String> listFormatos = new ArrayList<String>();  //  @jve:decl-index=0:
	
	private JLabel jLabel = null;
	
	private JLabel jLabel1 = null;
	
	private JLabel jLabel2 = null;
	
	private JLabel plan_anualjLabel3 = null;
	
	private JTextField plananualjTextField = null;
	
	private JLabel jLabel21 = null;
	
	private JLabel logojLabel3 = null;
	
	private JLabel fechanjLabel = null;
	
	private JTextField jTextFieldPrecio = null;
	
	private JLabel jLabelPrecio = null;
	
	private JLabel jLabel11 = null;
	
	private JComboBox formatojComboBox = null;
	
	private DefaultComboBoxModel formatodefaultComboBoxModel = null;
	
	private DefaultComboBoxModel formatodefaultComboBoxModel2 = null;
	
    private Date fechaActual = null;  //  @jve:decl-index=0:
    
	private Usuario loguiado = null;  //  @jve:decl-index=0:

	private JRadioButton jRadioButton1 = null;

	private JRadioButton jRadioButton2 = null;

	private JLabel jLabelFormatos = null;

	private JLabel jLabel1111 = null;

	private JComboBox formatojComboBox2 = null;

	private JLabel jLabelcod_quimefa = null;

	private JLabel jLabel22 = null;

	private JTextField jTextFieldCod_quimefa = null;
	
	private ButtonGroup buttonGroup = null;  //  @jve:decl-index=0:visual-constraint="785,133"
	
	private Productos() {
		super();
		initialize();
	}
	public static Productos getProductosV() {
		if (producto == null) {
			producto = new Productos();
		}
		return producto;
}
	
	private void initialize() {
		ConnectionBD.testConnect();
		loguiado = Control.getSessionUser();
		this.setSize(672, 539);
		this.setContentPane(getJProductosContentPane());
		this.setTitle("Productos");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		modificarjButton.setEnabled(false);
		eliminarjButton.setEnabled(false);
		getFormatojComboBox().setVisible(false);
		getFormatojComboBox2().setVisible(false);
		getButtonGroup();
		GregorianCalendar calendar = new GregorianCalendar();
		int i = calendar.get(Calendar.DATE);
		int k = calendar.get(Calendar.MONTH);
		int p = calendar.get(Calendar.YEAR);
		p = p - 1900;
		Date date = new Date(p,k,i);
		fechaActual = date;
		String formato1=null;
		String formato2=null;
		List<Integer> listaformatos = new ArrayList<Integer>(); 
		try {
			listTrazas =(ArrayList<Traza>)Traza_Servicio.getAllTraza();
			listProductos = (ArrayList<Producto>) Prod_Services.getAllProd();
			listProdForm = (ArrayList<Formato_Producto>)Prod_Services.getAllFormatoProd();  //  @jve:decl-index=0:
			for (int j = 0; j < listProductos.size(); j++) {
				for (int w = 0; w < listProdForm.size(); w++){
					if(listProductos.get(j).getCod_producto().equals(listProdForm.get(w).getCod_producto())){
						listaformatos.add(listProdForm.get(w).getId_formato());
					}
				}
				if(listaformatos.size()==2){
					formato1= Lote_Service.getFormato_Nom(listaformatos.get(0));
					formato2= Lote_Service.getFormato_Nom(listaformatos.get(1));
					datosPdefaultTableModel.addRow(new Object[]{listProductos.get(j).getCod_producto(), listProductos.get(j).getNombre(), listProductos.get(j).getConsumo_diario(), listProductos.get(j).getPlan_anual(), listProductos.get(j).getPrecio(),listProductos.get(j).getCod_quimefa(), "("+formato1+","+formato2+")"});
				}
				else{
					formato1= Lote_Service.getFormato_Nom(listaformatos.get(0));
					datosPdefaultTableModel.addRow(new Object[]{listProductos.get(j).getCod_producto(), listProductos.get(j).getNombre(), listProductos.get(j).getConsumo_diario(), listProductos.get(j).getPlan_anual(), listProductos.get(j).getPrecio(),listProductos.get(j).getCod_quimefa(), "("+formato1+")"});	
				}
				listaformatos.clear();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private ButtonGroup getButtonGroup() {
		if (buttonGroup == null) {
			buttonGroup = new ButtonGroup();
			getButtonGroup().add(jRadioButton1);
			getButtonGroup().add(jRadioButton2);
			
			
		}
		
		return buttonGroup;
	}

	private JPanel getJProductosContentPane() {
		if (jProductosContentPane == null) {
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(12, 166, 10, 16));
			jLabel22.setForeground(Color.red);
			jLabel22.setText("*");
			jLabel22.setBackground(Color.red);
			jLabelcod_quimefa = new JLabel();
			jLabelcod_quimefa.setBounds(new Rectangle(21, 166, 93, 16));
			jLabelcod_quimefa.setText("Código Quimefa:");
			jLabel1111 = new JLabel();
			jLabel1111.setBounds(new Rectangle(392, 60, 10, 16));
			jLabel1111.setForeground(Color.red);
			jLabel1111.setText("*");
			jLabel1111.setBackground(Color.red);
			jLabelFormatos = new JLabel();
			jLabelFormatos.setBounds(new Rectangle(403, 60, 61, 16));
			jLabelFormatos.setText("Formatos:");
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(57, 71, 10, 16));
			jLabel11.setForeground(Color.red);
			jLabel11.setText("*");
			jLabel11.setBackground(Color.red);
			jLabelPrecio = new JLabel();
			jLabelPrecio.setBounds(new Rectangle(66, 71, 48, 16));
			jLabelPrecio.setText("Precio:");
			fechanjLabel = new JLabel();
			long fecha=System.currentTimeMillis();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(fecha);
			int i = calendar.get(Calendar.DATE);
			int k = calendar.get(Calendar.MONTH);
			int p = calendar.get(Calendar.YEAR);
			fechanjLabel.setText(""+i+"/"+(k+1)+"/"+p);
			fechanjLabel.setBounds(new Rectangle(505, 0, 65, 20));
			logojLabel3 = new JLabel();
			logojLabel3.setBounds(new Rectangle(569, 0, 85, 73));
			logojLabel3.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabel3.setText("");
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(42, 135, 10, 16));
			jLabel21.setForeground(Color.red);
			jLabel21.setText("*");
			jLabel21.setBackground(Color.red);
			plan_anualjLabel3 = new JLabel();
			plan_anualjLabel3.setBounds(new Rectangle(52, 135, 63, 16));
			plan_anualjLabel3.setText("Plan Anual:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(13, 100, 10, 16));
			jLabel2.setForeground(Color.red);
			jLabel2.setText("*");
			jLabel2.setBackground(Color.red);
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(56, 41, 10, 16));
			jLabel1.setForeground(Color.red);
			jLabel1.setText("*");
			jLabel1.setBackground(Color.red);
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(63, 14, 10, 16));
			jLabel.setBackground(Color.red);
			jLabel.setForeground(Color.red);
			jLabel.setText("*");
			jcod_prodLabel = new JLabel();
			jcod_prodLabel.setBounds(new Rectangle(72, 14, 42, 16));
			jcod_prodLabel.setText("Código:");
			jconsumo_diarioPLabel = new JLabel();
			jconsumo_diarioPLabel.setBounds(new Rectangle(22, 100, 93, 16));
			jconsumo_diarioPLabel.setText("Consumo Diario:");
			jnombrePLabel = new JLabel();
			jnombrePLabel.setBounds(new Rectangle(66, 41, 48, 16));
			jnombrePLabel.setText("Nombre:");
			jProductosContentPane = new JPanel();
			jProductosContentPane.setLayout(null);
			jProductosContentPane.add(jnombrePLabel, null);
			jProductosContentPane.add(jconsumo_diarioPLabel, null);
			jProductosContentPane.add(getJnombrePTextField(), null);
			jProductosContentPane.add(getJconsumoDiarioPTextField(), null);
			jProductosContentPane.add(getJSeparator(), null);
			jProductosContentPane.add(getJScrollPane(), null);
			jProductosContentPane.add(getInsertarjButton(), null);
			jProductosContentPane.add(getModificarjButton(), null);
			jProductosContentPane.add(getEliminarjButton(), null);
			jProductosContentPane.add(jcod_prodLabel, null);
			jProductosContentPane.add(getJcod_prodTextField(), null);
			jProductosContentPane.add(jLabel, null);
			jProductosContentPane.add(jLabel1, null);
			jProductosContentPane.add(jLabel2, null);
			jProductosContentPane.add(plan_anualjLabel3, null);
			jProductosContentPane.add(getPlananualjTextField(), null);
			jProductosContentPane.add(jLabel21, null);
			jProductosContentPane.add(logojLabel3, null);
			jProductosContentPane.add(fechanjLabel, null);
			jProductosContentPane.add(getJTextFieldPrecio(), null);
			jProductosContentPane.add(jLabelPrecio, null);
			jProductosContentPane.add(jLabel11, null);
			jProductosContentPane.add(getFormatojComboBox(), null);
			jProductosContentPane.add(getJRadioButton1(), null);
			jProductosContentPane.add(getJRadioButton2(), null);
			jProductosContentPane.add(jLabelFormatos, null);
			jProductosContentPane.add(jLabel1111, null);
			jProductosContentPane.add(getFormatojComboBox2(), null);
			jProductosContentPane.add(jLabelcod_quimefa, null);
			jProductosContentPane.add(jLabel22, null);
			jProductosContentPane.add(getJTextFieldCod_quimefa(), null);
			jProductosContentPane.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					jcod_prodTextField.setText("");
					jcod_prodTextField.setEditable(true);
					jnombrePTextField.setText("");
					jTextFieldPrecio.setText("");
					formatojComboBox.setSelectedIndex(0);
					formatojComboBox2.setSelectedIndex(0);
					formatojComboBox.setVisible(false);
					formatojComboBox2.setVisible(false);
					jconsumoDiarioPTextField.setText("");
					plananualjTextField.setText("");
					jTextFieldCod_quimefa.setText("");
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);
					datosPjTable.clearSelection();				
				}
			});
		}
		return jProductosContentPane;
	}

	private JTextField getJnombrePTextField() {
		if (jnombrePTextField == null) {
			jnombrePTextField = new JTextField();
			jnombrePTextField.setLocation(new Point(123, 41));
			jnombrePTextField.setSize(new Dimension(243, 19));
		}
		return jnombrePTextField;
	}

	private JTextField getJconsumoDiarioPTextField() {
		if (jconsumoDiarioPTextField == null) {
			jconsumoDiarioPTextField = new JTextField();
			aica.utiles.Validaciones.validateDigitAndComma(jconsumoDiarioPTextField);
			jconsumoDiarioPTextField.setBounds(new Rectangle(123, 100, 243, 19));
		}
		return jconsumoDiarioPTextField;
	}

	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(14, 199, 631, 11));
		}
		return jSeparator;
	}

	private UneditableTableModel getDatosPdefaultTableModel() {
		if (datosPdefaultTableModel == null) {
			datosPdefaultTableModel = new UneditableTableModel(new Object[] {"Código", "Nombre", "Consumo Diario", "Plan Anual", "Precio", "Código Quimefa" , "Formatos", }, 0);
		}
      return datosPdefaultTableModel;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(8, 209, 639, 245));
			jScrollPane.setViewportView(getDatosPjTable());
		}
		return jScrollPane;
	}

	private JTable getDatosPjTable() {
		if (datosPjTable == null) {
			datosPjTable = new JTable();
			datosPjTable.setModel(getDatosPdefaultTableModel());
			datosPjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					modificarjButton.setEnabled(true);
					eliminarjButton.setEnabled(true);
					insertarjButton.setEnabled(false);
					int pos = datosPjTable.getSelectedRow();
					Producto producto;
					String formato1= null;
					String formato2= null;
					producto = listProductos.get(pos); 
					List<Integer> listaf = new ArrayList<Integer>();
					listaf= Formatos(producto.getCod_producto());
					jcod_prodTextField.setText(producto.getCod_producto());
					jcod_prodTextField.setEditable(false);
					jnombrePTextField.setText(producto.getNombre());
					jTextFieldPrecio.setText(String.valueOf(producto.getPrecio()));
					jconsumoDiarioPTextField.setText(String.valueOf(producto.getConsumo_diario()));
					plananualjTextField.setText(String.valueOf(producto.getPlan_anual()));
					jTextFieldCod_quimefa.setText(producto.getCod_quimefa());
					if (listaf.size()==2){
						formatojComboBox.setVisible(true);
						formatojComboBox2.setVisible(true);
						try {
							formato1= Lote_Service.getFormato_Nom(listaf.get(0));
							formato2= Lote_Service.getFormato_Nom(listaf.get(1));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						formatojComboBox.setSelectedItem(formato1);
						formatojComboBox2.setSelectedItem(formato2);	
					}
					else{
						formatojComboBox.setVisible(true);
						formatojComboBox2.setSelectedIndex(0);
						formatojComboBox2.setVisible(false);
						try {
							formato1= Lote_Service.getFormato_Nom(listaf.get(0));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						formatojComboBox.setSelectedItem(formato1);
					}
				}
			});
			
		}
		return datosPjTable;
	}
	private JButton getInsertarjButton() {
		if (insertarjButton == null) {
			insertarjButton = new JButton();
			insertarjButton.setBounds(new Rectangle(10, 464, 98, 26));
			insertarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			insertarjButton.setText("Insertar");
			insertarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String codigo_prod = getJcod_prodTextField().getText();
					if(jcod_prodTextField.getText().equals("") || jnombrePTextField.getText().equals("") || jTextFieldPrecio.getText().equals("")
							|| formatojComboBox.getSelectedIndex() == 0 || jconsumoDiarioPTextField.getText().equals("")|| plananualjTextField.getText().equals("") || jTextFieldCod_quimefa.getText().equals("") ){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);} 
					else
						try {
							if (Prod_Services.existeProducto(codigo_prod)== true){
								JOptionPane.showMessageDialog(new JPanel(), "Ese código ya existe, inserte otro.", "Error", JOptionPane.ERROR_MESSAGE);}
							if (formatojComboBox.getSelectedIndex() != 0 && formatojComboBox2.getSelectedIndex() != 0 && (formatojComboBox.getSelectedItem().equals(formatojComboBox2.getSelectedItem()))){
								JOptionPane.showMessageDialog(new JPanel(), "Verifique los formatos.", "Error", JOptionPane.ERROR_MESSAGE);}
								
							else {
								Producto prod = new Producto();
								Formato_Producto prof = new Formato_Producto();
								prod.setCod_producto(getJcod_prodTextField().getText());
								prod.setNombre(getJnombrePTextField().getText());
								prod.setConsumo_diario(Float.valueOf(getJconsumoDiarioPTextField().getText()));
								prod.setPlan_anual(Float.valueOf(getPlananualjTextField().getText()));
								prod.setPrecio(Float.valueOf(getJTextFieldPrecio().getText()));
								prod.setCod_quimefa(getJTextFieldCod_quimefa().getText());
								prof.setCod_producto(getJcod_prodTextField().getText());
								if(formatojComboBox.getSelectedIndex() != 0){
									prof.setId_formato((Integer)formatojComboBox.getSelectedIndex());
									Prod_Services.createProdFormato(prof);
								}
							    if(formatojComboBox2.getSelectedIndex() != 0){
							       prof.setId_formato((Integer)formatojComboBox2.getSelectedIndex());
								   Prod_Services.createProdFormato(prof);
							    }
								try {
									Prod_Services.createProd(prod);
									listProductos.add(prod);
									Traza traza = new Traza();
									String operacion = "CR-" +"Tabla: producto" + ", Producto:"+ prod.getCod_producto();;
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
								if((formatojComboBox.getSelectedIndex() != 0) && (formatojComboBox2.getSelectedIndex() != 0)){
								datosPdefaultTableModel.addRow(new Object[]{prod.getCod_producto(), prod.getNombre(), prod.getConsumo_diario(), prod.getPlan_anual(), prod.getPrecio(), prod.getCod_quimefa(),("("+formatojComboBox.getSelectedItem()+","+formatojComboBox2.getSelectedItem()+")")});
								}
								else
									datosPdefaultTableModel.addRow(new Object[]{prod.getCod_producto(), prod.getNombre(), prod.getConsumo_diario(), prod.getPlan_anual(), prod.getPrecio(), prod.getCod_quimefa(),"("+formatojComboBox.getSelectedItem()+")"});
									
								jcod_prodTextField.setText("");
								jnombrePTextField.setText("");
								jTextFieldPrecio.setText("");
								formatojComboBox.setSelectedIndex(0);
								formatojComboBox2.setSelectedIndex(0);
								jconsumoDiarioPTextField.setText("");
								plananualjTextField.setText("");
								jTextFieldCod_quimefa.setText("");
								Lote_Visual.getLote_Visual().LlenarListaProd();
							}
						} catch (HeadlessException e1) {
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
			});
		}
		return insertarjButton;
	}

	private JButton getModificarjButton() {
		if (modificarjButton == null) {
			modificarjButton = new JButton();
			modificarjButton.setBounds(new Rectangle(410, 464, 116, 26));
			modificarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButton.setText("Modificar");
			modificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jcod_prodTextField.getText().equals("") || jnombrePTextField.getText().equals("") || jTextFieldPrecio.getText().equals("")
					   || formatojComboBox.getSelectedIndex() == 0 || jconsumoDiarioPTextField.getText().equals("")|| plananualjTextField.getText().equals("") || jTextFieldCod_quimefa.getText().equals("") ){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);} 
					else {
						if (formatojComboBox.getSelectedIndex() != 0 && formatojComboBox2.getSelectedIndex() != 0 && (formatojComboBox.getSelectedItem().equals(formatojComboBox2.getSelectedItem()))){
							JOptionPane.showMessageDialog(new JPanel(), "Verifique los formatos.", "Error", JOptionPane.ERROR_MESSAGE);}
						else{
					int pos = datosPjTable.getSelectedRow();
					try {
						Producto producto;
						Formato_Producto prof = new Formato_Producto();
						producto = listProductos.get(pos);
						String cod_old = producto.getCod_producto();
						producto.setNombre(jnombrePTextField.getText());
						producto.setPrecio(Float.valueOf(getJTextFieldPrecio().getText()));
						producto.setConsumo_diario(Float.valueOf(jconsumoDiarioPTextField.getText()));
						producto.setPlan_anual(Float.valueOf(plananualjTextField.getText()));
						producto.setCod_quimefa(jTextFieldCod_quimefa.getText());
						prof.setCod_producto(getJcod_prodTextField().getText());
						Prod_Services.updateProd(producto, cod_old);
						listProductos.set(pos, producto);
						List<Integer> listaf = new ArrayList<Integer>();
						listaf = Formatos(producto.getCod_producto());
						if (listaf.size()==2){
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(0));
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(1));		
						}
						else{
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(0));
					    }
						if(formatojComboBox.getSelectedIndex() != 0){
							prof.setId_formato((Integer)formatojComboBox.getSelectedIndex());
							Prod_Services.createProdFormato(prof);
						}
					    if(formatojComboBox2.getSelectedIndex() != 0){
					       prof.setId_formato((Integer)formatojComboBox2.getSelectedIndex());
						   Prod_Services.createProdFormato(prof);
					    }
						getDatosPdefaultTableModel().setValueAt(producto.getCod_producto(), pos, 0);
						getDatosPdefaultTableModel().setValueAt(producto.getNombre(), pos, 1);
						getDatosPdefaultTableModel().setValueAt(producto.getConsumo_diario(), pos, 2);
						getDatosPdefaultTableModel().setValueAt(producto.getPlan_anual(), pos, 3);
						getDatosPdefaultTableModel().setValueAt(producto.getPrecio(), pos, 4);
						getDatosPdefaultTableModel().setValueAt(producto.getCod_quimefa(), pos, 5);
						if((formatojComboBox.getSelectedIndex() != 0) && (formatojComboBox2.getSelectedIndex() != 0)){
							getDatosPdefaultTableModel().setValueAt(("("+formatojComboBox.getSelectedItem()+","+formatojComboBox2.getSelectedItem()+")"), pos, 6);
					     }
						else{
							getDatosPdefaultTableModel().setValueAt(("("+formatojComboBox.getSelectedItem()+")"), pos, 6);
						}
							
						Traza traza = new Traza();
						String operacion = "U-" +"Tabla: producto" + ", Producto:"+ producto.getCod_producto();;
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
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);
					datosPjTable.clearSelection();
					jcod_prodTextField.setText("");
					jnombrePTextField.setText("");
					jTextFieldPrecio.setText("");
					formatojComboBox.setVisible(false);
					formatojComboBox2.setVisible(false);
					formatojComboBox.setSelectedIndex(0);
					formatojComboBox2.setSelectedIndex(0);
					jconsumoDiarioPTextField.setText("");
					plananualjTextField.setText("");
					jTextFieldCod_quimefa.setText("");
					Lote_Visual.getLote_Visual().LlenarListaProd();
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
			eliminarjButton.setBounds(new Rectangle(545, 464, 99, 26));
			eliminarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			eliminarjButton.setText("Eliminar");
			eliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int pos = datosPjTable.getSelectedRow();
					Producto producto;
					try {
						producto = listProductos.get(pos);
						Prod_Services.deleteProd(producto.getCod_producto());
						listProductos.remove(pos);
						getDatosPdefaultTableModel().removeRow(pos);
						List<Integer> listaf = new ArrayList<Integer>();
						listaf = Formatos(producto.getCod_producto());
						if (listaf.size()==2){
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(0));
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(1));
							formatojComboBox.setVisible(false);
							formatojComboBox2.setVisible(false);
							formatojComboBox.setSelectedIndex(0);
							formatojComboBox2.setSelectedIndex(0);
								
						}
						else{
							Prod_Services.deleteProdFormato(producto.getCod_producto(), listaf.get(0));
							formatojComboBox.setVisible(false);
							formatojComboBox.setSelectedIndex(0);
						}
						Traza traza = new Traza();
						String operacion = "D-" +"Tabla: producto" + ", Producto:"+ producto.getCod_producto();;
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
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);
					datosPjTable.clearSelection();
					jcod_prodTextField.setEditable(true);
					jcod_prodTextField.setText("");
					jnombrePTextField.setText("");
					jTextFieldPrecio.setText("");
					jconsumoDiarioPTextField.setText("");
					plananualjTextField.setText("");
					jTextFieldCod_quimefa.setText("");
					Lote_Visual.getLote_Visual().LlenarListaProd();
				}
			});
		}
		return eliminarjButton;
	}

	private JTextField getJcod_prodTextField() {
		if (jcod_prodTextField == null) {
			jcod_prodTextField = new JTextField();
			jcod_prodTextField.setLocation(new Point(123, 14));
			jcod_prodTextField.setSize(new Dimension(243, 19));
		}
		return jcod_prodTextField;
	}

	private JTextField getPlananualjTextField() {
		if (plananualjTextField == null) {
			plananualjTextField = new JTextField();
			plananualjTextField.setLocation(new Point(123, 135));
			plananualjTextField.setSize(new Dimension(243, 19));
			aica.utiles.Validaciones.validateDigitAndComma(plananualjTextField);
		}
		return plananualjTextField;
	}

	private JTextField getJTextFieldPrecio() {
		if (jTextFieldPrecio == null) {
			jTextFieldPrecio = new JTextField();
			jTextFieldPrecio.setBounds(new Rectangle(123, 71, 243, 19));
			aica.utiles.Validaciones.validateDigitAndComma(jTextFieldPrecio);
		}
		return jTextFieldPrecio;
	}
	
	private JComboBox getFormatojComboBox() {
		if (formatojComboBox == null) {
			formatojComboBox = new JComboBox();
			formatojComboBox.setBounds(new Rectangle(435, 88, 139, 19));
			formatojComboBox.setModel(getFormatodefaultComboBoxModel());
		}
		return formatojComboBox;
	}
	private DefaultComboBoxModel getFormatodefaultComboBoxModel() {
		if (formatodefaultComboBoxModel == null) {
			formatodefaultComboBoxModel = new DefaultComboBoxModel();
			formatodefaultComboBoxModel.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModel.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModel;
	}

	private DefaultComboBoxModel getFormatodefaultComboBoxModel2() {
		if (formatodefaultComboBoxModel2 == null) {
			formatodefaultComboBoxModel2 = new DefaultComboBoxModel();
			formatodefaultComboBoxModel2.addElement("<Seleccione>");
			try {
				listFormatos = (ArrayList<String>) Lote_Service.getAllFormato();
				for (int i = 0; i < listFormatos.size(); i++) {
					formatodefaultComboBoxModel2.addElement(listFormatos.get(i));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return formatodefaultComboBoxModel2;
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
	
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setBounds(new Rectangle(392, 86, 32, 24));
			jRadioButton1.setText("1");
			jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (jRadioButton1.isSelected()==true)
					{
						getFormatojComboBox().setVisible(true);
						getFormatojComboBox2().setVisible(false);
					}
					else
					{
						getFormatojComboBox().setVisible(false);
						getFormatojComboBox2().setVisible(false);
					}
					
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
		}
		return jRadioButton1;
	}
	
	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setBounds(new Rectangle(392, 132, 32, 24));
			jRadioButton2.setText("2");
			jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (jRadioButton2.isSelected()==true)
					{   getFormatojComboBox().setVisible(true);
						getFormatojComboBox2().setVisible(true);
					}
					else
					{
						 getFormatojComboBox().setVisible(false);
						 getFormatojComboBox2().setVisible(false);
					}
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
		}
		return jRadioButton2;
	}

	private JComboBox getFormatojComboBox2() {
		if (formatojComboBox2 == null) {
			formatojComboBox2 = new JComboBox();
			formatojComboBox2.setBounds(new Rectangle(434, 132, 139, 19));
			formatojComboBox2.setModel(getFormatodefaultComboBoxModel2());
		}
		return formatojComboBox2;
	}

	private JTextField getJTextFieldCod_quimefa() {
		if (jTextFieldCod_quimefa == null) {
			jTextFieldCod_quimefa = new JTextField();
			jTextFieldCod_quimefa.setBounds(new Rectangle(123, 166, 243, 19));
		}
		return jTextFieldCod_quimefa;
	}
   
	private List<Integer> Formatos(String cod_pro){
	List<Integer> lista = new ArrayList<Integer>();
	try {
		listProdForm = (ArrayList<Formato_Producto>)Prod_Services.getAllFormatoProd();
			for (int i = 0; i < listProdForm.size(); i++){
				if((listProdForm.get(i).getCod_producto()).equals(cod_pro)){
					lista.add(listProdForm.get(i).getId_formato());
				}
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  //  @jve:decl-index=0:
	
	return lista;
}

}  //  @jve:decl-index=0:visual-constraint="40,11"
