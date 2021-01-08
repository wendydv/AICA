package aica.visual.reportes;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.lang.System;
import java.lang.String;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import aica.model.Plan_Produccion;
import aica.model.Producto;
import aica.reporte.outputToEXL;
import aica.service.PlanProduccion_Service;
import aica.service.Prod_Services;
import aica.utiles.UneditableTableModel;

public class ConsultasDinamicasPlan extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static ConsultasDinamicasPlan plan = null;  //  @jve:decl-index=0:visual-constraint="868,14"

	private JPanel jContentPane = null;

	private JPanel jPanel = null;

	private JLabel jLabelMes = null;

	private JLabel jLabelBproducto = null;

	private JLabel fechanjLabel = null;

	private GregorianCalendar calendar1 = null;

	private JLabel logojLabel2 = null;

	private JSeparator jSeparator = null;

	private JButton generarReportejButton = null;

	private JButton crearReportejButton = null;

	private JComboBox jComboBoxMes = null;

	private JComboBox jComboBoxBproducto = null;

	private DefaultComboBoxModel productodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="857,77"
	
	private DefaultComboBoxModel defaultComboBoxModelMes = null;  //  @jve:decl-index=0:visual-constraint="869,128"
	
	public ArrayList<Producto> listProd = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	private JScrollPane datosjScrollPane = null;

	private JTable datosReportejTable = null;

	public DefaultTableModel datosReportedefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1122,307"
	
	public ArrayList<Plan_Produccion> listReportePlan = new ArrayList<Plan_Produccion>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProducto = new ArrayList<Producto>();  //  @jve:decl-index=0:
	
	public String address="reportes/Excel/Consulta Dinámica Plan.xls";  //  @jve:decl-index=0:
	
	public String fecha_actual;

	private ConsultasDinamicasPlan() {
		super();
		initialize();
	}

	public static ConsultasDinamicasPlan getPlan() {
		if (plan == null) {
			plan = new ConsultasDinamicasPlan();
		}
		return plan;
   }

	private void initialize() {
		this.setSize(475, 494);
		this.setContentPane(getJContentPane());
		this.setTitle("Consultas Dinámicas del Plan de Producción");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		try {
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			logojLabel2 = new JLabel();
			logojLabel2.setBounds(new Rectangle(374, 0, 84, 69));
			logojLabel2.setText("");
			logojLabel2.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			fecha_actual = "" + getCalendar1().get(Calendar.DATE) + "/" + (getCalendar1().get(Calendar.MONTH) + 1) + "/" + getCalendar1().get(Calendar.YEAR);
			fechanjLabel = new JLabel();
			fechanjLabel.setBounds(new Rectangle(316, 2, 57, 16));
			fechanjLabel.setText(fecha_actual);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(fechanjLabel, null);
			jContentPane.add(logojLabel2, null);
			jContentPane.add(getJSeparator(), null);
			jContentPane.add(getGenerarReportejButton(), null);
			jContentPane.add(getCrearReportejButton(), null);
			jContentPane.add(getDatosjScrollPane(), null);
		}
		return jContentPane;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabelBproducto = new JLabel();
			jLabelBproducto.setBounds(new Rectangle(42, 60, 55, 16));
			jLabelBproducto.setText("Producto:");
			jLabelMes = new JLabel();
			jLabelMes.setBounds(new Rectangle(70, 17, 27, 16));
			jLabelMes.setText("Mes:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(24, 22, 339, 99));
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(jLabelMes, null);
			jPanel.add(jLabelBproducto, null);
			jPanel.add(getJComboBoxMes(), null);
			jPanel.add(getJComboBoxBproducto(), null);
		}
		return jPanel;
	}

	private GregorianCalendar getCalendar1() {
		if (calendar1 == null) {
			calendar1 = new GregorianCalendar();
			calendar1.setTimeInMillis(System.currentTimeMillis());
		}
		return calendar1;
	}

	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(12, 180, 419, 8));
		}
		return jSeparator;
	}

	private JButton getGenerarReportejButton() {
		if (generarReportejButton == null) {
			generarReportejButton = new JButton();
			generarReportejButton.setBounds(new Rectangle(73, 134, 133, 26));
			generarReportejButton.setText("Crear Reporte");
			generarReportejButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/reporte.png")));
			generarReportejButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(listReportePlan.isEmpty()){
						if(jComboBoxMes.getSelectedIndex() == 0 && jComboBoxBproducto.getSelectedIndex() == 0)
							JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar algún campo", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							String mes = null;
							if(jComboBoxMes.getSelectedIndex() != 0){
								mes = (String)jComboBoxMes.getSelectedItem();}
							String producto = null;
							if(jComboBoxBproducto.getSelectedIndex() != 0){
								producto = (String)jComboBoxBproducto.getSelectedItem();}
							try {
								listReportePlan = PlanProduccion_Service.listReportePlan(mes, producto);
								for (int i = 0; i < listReportePlan.size(); i++) {
								    String producton = null;
									int count = 0;
									boolean found = false;
									while (found == false && count < listProducto.size()) {
										if(listProducto.get(count).getCod_producto().equals(listReportePlan.get(i).getCod_producto())){
											producton=listProducto.get(count).getNombre();
											found = true;}
										else count++;
									}
									getDatosReportedefaultTableModel().addRow(new Object[]{producton, listReportePlan.get(i).getElaborar(), listReportePlan.get(i).getAprobar()});
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}

					else {
						if(jComboBoxMes.getSelectedIndex() == 0 && jComboBoxBproducto.getSelectedIndex() == 0)
							JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar algún campo", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							String mes = null;
							if(jComboBoxMes.getSelectedIndex() != 0){
								mes = (String)jComboBoxMes.getSelectedItem();}
							String producto = null;
							if(jComboBoxBproducto.getSelectedIndex() != 0){
								producto = (String)jComboBoxBproducto.getSelectedItem();}
							for (int w = listReportePlan.size(); w > 0 ; w--) {
								datosReportedefaultTableModel.removeRow(w - 1);
							}
							listReportePlan.clear();
							try {
								listReportePlan = PlanProduccion_Service.listReportePlan(mes, producto);
								for (int i = 0; i < listReportePlan.size(); i++) {
									String producton = null;
									int count = 0;
									boolean found = false;
									while (found == false && count < listProducto.size()) {
										if(listProducto.get(count).getCod_producto().equals(listReportePlan.get(i).getCod_producto())){
											producton=listProducto.get(count).getNombre();
											found = true;}
										else count++;
									}
									getDatosReportedefaultTableModel().addRow(new Object[]{producton, listReportePlan.get(i).getElaborar(), listReportePlan.get(i).getAprobar()});
									}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

					}
				}
			});
		}
		return generarReportejButton;
	}

	private JButton getCrearReportejButton() {
		if (crearReportejButton == null) {
			crearReportejButton = new JButton();
			crearReportejButton.setBounds(new Rectangle(237, 135, 147, 26));
			crearReportejButton.setText("Generar Reporte");
			crearReportejButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			crearReportejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					outputToEXL excel = new outputToEXL(address);
					excel.saveReportePlan(3);
				}
			});
		}
		return crearReportejButton;
	}

	private JComboBox getJComboBoxMes() {
		if (jComboBoxMes == null) {
			jComboBoxMes = new JComboBox();
			jComboBoxMes.setModel(getdefaultComboBoxModelMes());	
			jComboBoxMes.setBounds(new Rectangle(119, 14, 169, 21));
		}
		return jComboBoxMes;
	}

	private JComboBox getJComboBoxBproducto() {
		if (jComboBoxBproducto == null) {
			jComboBoxBproducto = new JComboBox();
			jComboBoxBproducto.setBounds(new Rectangle(119, 60, 169, 21));
			jComboBoxBproducto.setModel(getProductodefaultComboBoxModel());
		}
		return jComboBoxBproducto;
	}
	private DefaultComboBoxModel getProductodefaultComboBoxModel() {
		if (productodefaultComboBoxModel == null) {
			productodefaultComboBoxModel = new DefaultComboBoxModel();
			productodefaultComboBoxModel.addElement("<Seleccione>");
			try {
				listProd = (ArrayList<Producto>) Prod_Services.getAllProd();
				for (int i = 0; i < listProd.size(); i++) {
					productodefaultComboBoxModel.addElement(listProd.get(i).getNombre());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productodefaultComboBoxModel;
	}
	
	private DefaultComboBoxModel getdefaultComboBoxModelMes() {
		if (defaultComboBoxModelMes == null) {
			defaultComboBoxModelMes = new DefaultComboBoxModel();
			defaultComboBoxModelMes.addElement("<Seleccione>");
			defaultComboBoxModelMes.addElement("Enero");
			defaultComboBoxModelMes.addElement("Febrero");
			defaultComboBoxModelMes.addElement("Marzo");
			defaultComboBoxModelMes.addElement("Abril");
			defaultComboBoxModelMes.addElement("Mayo");
			defaultComboBoxModelMes.addElement("Junio");
			defaultComboBoxModelMes.addElement("Julio");
			defaultComboBoxModelMes.addElement("Agosto");
			defaultComboBoxModelMes.addElement("Septiembre");
			defaultComboBoxModelMes.addElement("Octubre");
			defaultComboBoxModelMes.addElement("Noviembre");
			defaultComboBoxModelMes.addElement("Diciembre");
		}
		return defaultComboBoxModelMes;
	}
	private JScrollPane getDatosjScrollPane() {
		if (datosjScrollPane == null) {
			datosjScrollPane = new JScrollPane();
			datosjScrollPane.setBounds(new Rectangle(14, 196, 426, 243));
			datosjScrollPane.setViewportView(getDatosReportejTable());
		}
		return datosjScrollPane;
	}

	private JTable getDatosReportejTable() {
		if (datosReportejTable == null) {
			datosReportejTable = new JTable();
			datosReportejTable.setModel(getDatosReportedefaultTableModel());
		}
		return datosReportejTable;
	}

	private DefaultTableModel getDatosReportedefaultTableModel() {
		if (datosReportedefaultTableModel == null) {
			datosReportedefaultTableModel = new UneditableTableModel(new Object[]{"Producto", "Elaborar", "Aprobar"}, 0);
		}
		return datosReportedefaultTableModel;
	}

}  //  @jve:decl-index=0:visual-constraint="34,20"
