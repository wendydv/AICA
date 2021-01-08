package aica.visual.reportes;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import aica.model.Lote;
import aica.model.Producto;
import aica.reporte.outputToEXL;
import aica.service.ConnectionBD;
import aica.service.Lote_Service;
import aica.service.Prod_Services;
import aica.utiles.UneditableTableModel;

import com.toedter.calendar.JCalendar;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.lang.System;
import java.lang.String;

public class ConsultasDinamicasPendientes extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public String address="reportes/Excel/Consulta Dinámica Pendientes.xls";  //  @jve:decl-index=0:
	
	private static ConsultasDinamicasPendientes strategy = null;

	private JPanel jContentPane = null;

	private JLabel seleccionejLabel = null;

	private JLabel seleccioneFormatojLabel = null;

	private JLabel tipojLabel = null;

	private JComboBox estadojComboBox = null;

	private JComboBox formatojComboBox = null;

	private JComboBox tipojComboBox = null;

	private DefaultComboBoxModel formatodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="1116,25"

	private DefaultComboBoxModel estadodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="1126,91"

	private DefaultComboBoxModel tipodefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="1128,157"
	
	public List<String> listFormatos = new ArrayList<String>();  //  @jve:decl-index=0:

	public List<String> listEstados = new ArrayList<String>();  //  @jve:decl-index=0:

	private JPanel jPanel = null;

	private JCalendar fechainicialjCalendar = null;

	private JCalendar fechafinaljCalendar = null;

	private JCheckBox fechainiciojCheckBox = null;

	private JCheckBox fechafinjCheckBox = null;

	@SuppressWarnings("unused")
	private ButtonGroup fechasbuttonGroup = null;  //  @jve:decl-index=0:visual-constraint="1174,250"

	private JButton generarReportejButton = null;

	private JSeparator jSeparator = null;

	private JScrollPane datosjScrollPane = null;

	private JTable datosReportejTable = null;

	private DefaultTableModel datosReportedefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="1122,307"

	private JButton crearReportejButton = null;

	private JLabel iconjLabel = null;
	
	public ArrayList<Lote> listReporte = new ArrayList<Lote>();  //  @jve:decl-index=0:
	
	public ArrayList<Producto> listProducto = new ArrayList<Producto>();  //  @jve:decl-index=0:

	private JLabel fechanjLabel = null;
	
	public String fecha_actual;

	private GregorianCalendar calendar1 = null;

	private ConsultasDinamicasPendientes() {
		super();
		initialize();
	}

	public static ConsultasDinamicasPendientes getStrategy() {
		if (strategy == null) {
			strategy = new ConsultasDinamicasPendientes();
		}
		return strategy;
}

	private void initialize() {
		ConnectionBD.testConnect();
		this.setSize(960, 539);
		this.setContentPane(getJContentPane());
		this.setTitle("Consultas Dinámicas de Lotes Pendientes");
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		fechafinaljCalendar.setEnabled(false);
		fechainicialjCalendar.setEnabled(false);
		try {
			listProducto = (ArrayList<Producto>) Prod_Services.getAllProd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			fecha_actual = "" + getCalendar1().get(Calendar.DATE) + "/" + (getCalendar1().get(Calendar.MONTH) + 1) + "/" + getCalendar1().get(Calendar.YEAR);
			fechanjLabel = new JLabel();
			fechanjLabel.setBounds(new Rectangle(807, 2, 58, 22));
			fechanjLabel.setText(fecha_actual);
			iconjLabel = new JLabel();
			iconjLabel.setBounds(new Rectangle(865, 1, 88, 76));
			iconjLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			iconjLabel.setText("");
			tipojLabel = new JLabel();
			tipojLabel.setText("Destino:");
			tipojLabel.setBounds(new Rectangle(30, 87, 47, 16));
			seleccioneFormatojLabel = new JLabel();
			seleccioneFormatojLabel.setText("Formato:");
			seleccioneFormatojLabel.setBounds(new Rectangle(29, 57, 50, 16));
			seleccionejLabel = new JLabel();
			seleccionejLabel.setText("Etapa:");
			seleccionejLabel.setBounds(new Rectangle(37, 26, 42, 16));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getFechainicialjCalendar(), null);
			jContentPane.add(getFechafinaljCalendar(), null);
			jContentPane.add(getFechainiciojCheckBox(), null);
			jContentPane.add(getFechafinjCheckBox(), null);
			jContentPane.add(getGenerarReportejButton(), null);
			jContentPane.add(getJSeparator(), null);
			jContentPane.add(getDatosjScrollPane(), null);
			jContentPane.add(getCrearReportejButton(), null);
			jContentPane.add(iconjLabel, null);
			jContentPane.add(fechanjLabel, null);
		}
		return jContentPane;
	}

	private JComboBox getEstadojComboBox() {
		if (estadojComboBox == null) {
			estadojComboBox = new JComboBox();
			estadojComboBox.setBounds(new Rectangle(93, 26, 204, 22));
			estadojComboBox.setModel(getEstadodefaultComboBoxModel());
		}
		return estadojComboBox;
	}

	private JComboBox getFormatojComboBox() {
		if (formatojComboBox == null) {
			formatojComboBox = new JComboBox();
			formatojComboBox.setBounds(new Rectangle(93, 57, 204, 22));
			formatojComboBox.setModel(getFormatodefaultComboBoxModel());
		}
		return formatojComboBox;
	}

	private JComboBox getTipojComboBox() {
		if (tipojComboBox == null) {
			tipojComboBox = new JComboBox();
			tipojComboBox.setBounds(new Rectangle(93, 87, 204, 22));
			tipojComboBox.setModel(getTipodefaultComboBoxModel());
		}
		return tipojComboBox;
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

	private DefaultComboBoxModel getEstadodefaultComboBoxModel() {
		if (estadodefaultComboBoxModel == null) {
			estadodefaultComboBoxModel = new DefaultComboBoxModel();
			estadodefaultComboBoxModel.addElement("<Seleccione>");
			estadodefaultComboBoxModel.addElement("Aprobación Analítica");
			estadodefaultComboBoxModel.addElement("Revisión");
			estadodefaultComboBoxModel.addElement("Etiquetado");
			estadodefaultComboBoxModel.addElement("Envase");
			estadodefaultComboBoxModel.addElement("Aprobación Final");
		}
		return estadodefaultComboBoxModel;
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

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(18, 22, 356, 134));
			jPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanel.add(seleccionejLabel, null);
			jPanel.add(seleccioneFormatojLabel, null);
			jPanel.add(getEstadojComboBox(), null);
			jPanel.add(getFormatojComboBox(), null);
			jPanel.add(getTipojComboBox(), null);
			jPanel.add(tipojLabel, null);
		}
		return jPanel;
	}

	private JCalendar getFechainicialjCalendar() {
		if (fechainicialjCalendar == null) {
			fechainicialjCalendar = new JCalendar();
			fechainicialjCalendar.setBounds(new Rectangle(404, 41, 200, 167));
		}
		return fechainicialjCalendar;
	}

	private JCalendar getFechafinaljCalendar() {
		if (fechafinaljCalendar == null) {
			fechafinaljCalendar = new JCalendar();
			fechafinaljCalendar.setBounds(new Rectangle(636, 41, 200, 167));
		}
		return fechafinaljCalendar;
	}

	private JCheckBox getFechainiciojCheckBox() {
		if (fechainiciojCheckBox == null) {
			fechainiciojCheckBox = new JCheckBox();
			fechainiciojCheckBox.setBounds(new Rectangle(405, 11, 92, 24));
			fechainiciojCheckBox.setText("Fecha Inicio");
			fechainiciojCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechainiciojCheckBox.isSelected())
						fechainicialjCalendar.setEnabled(true);
					else fechainicialjCalendar.setEnabled(false);
				}
			});
		}
		return fechainiciojCheckBox;
	}

	private JCheckBox getFechafinjCheckBox() {
		if (fechafinjCheckBox == null) {
			fechafinjCheckBox = new JCheckBox();
			fechafinjCheckBox.setBounds(new Rectangle(633, 12, 78, 24));
			fechafinjCheckBox.setText("Fecha Fin");
			fechafinjCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(fechafinjCheckBox.isSelected())
						fechafinaljCalendar.setEnabled(true);
					else fechafinaljCalendar.setEnabled(false);
				}
			});
		}
		return fechafinjCheckBox;
	}

	private JButton getGenerarReportejButton() {
		if (generarReportejButton == null) {
			generarReportejButton = new JButton();
			generarReportejButton.setBounds(new Rectangle(76, 182, 133, 26));
			generarReportejButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/reporte.png")));
			generarReportejButton.setText("Crear Reporte");
			generarReportejButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(listReporte.isEmpty()){
						if(estadojComboBox.getSelectedIndex() == 0 && formatojComboBox.getSelectedIndex() == 0 && tipojComboBox.getSelectedIndex() == 0 && !fechafinjCheckBox.isSelected() && !fechainiciojCheckBox.isSelected())
							JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar algún campo", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							int estado = estadojComboBox.getSelectedIndex();
							String formato = null;
							if(formatojComboBox.getSelectedIndex() != 0)
								formato = (String)formatojComboBox.getSelectedItem();
							int tipo = tipojComboBox.getSelectedIndex();
							Date dateInicio = null;
							Date dateFin =  null;
							if(fechainiciojCheckBox.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechainicialjCalendar.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateInicio = new Date(p,k,i);	
							}
							if(fechafinjCheckBox.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechafinaljCalendar.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateFin = new Date(p,k,i);	
							}
							try {
								listReporte = Lote_Service.listReporteP(estado, formato, tipo, dateInicio, dateFin);

								for (int i = 0; i < listReporte.size(); i++) {
									String producto = null;
									int count = 0;
									boolean found = false;
									while (found == false && count < listProducto.size()) {
										if(listProducto.get(count).getCod_producto().equals(listReporte.get(i).getProducto())){
											producto=listProducto.get(count).getNombre();
											found = true;}
										else count++;
									}
									if(listReporte.get(i).getCod_estado() == 1)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Aprobación"});
									if(listReporte.get(i).getCod_estado() == 2)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Revisión"});
									if(listReporte.get(i).getCod_estado() == 3)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Etiquetado"});
									if(listReporte.get(i).getCod_estado() == 4)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Envase"});
									if(listReporte.get(i).getCod_estado() == 5)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Aprobación Final"});
									if(listReporte.get(i).getCod_estado() == 6)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Listo"});
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}

					else {
						if(estadojComboBox.getSelectedIndex() == 0 && formatojComboBox.getSelectedIndex() == 0 && tipojComboBox.getSelectedIndex() == 0 && !fechafinjCheckBox.isSelected() && !fechainiciojCheckBox.isSelected())
							JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar algún campo", "Error", JOptionPane.ERROR_MESSAGE);
						else {
							int estado = estadojComboBox.getSelectedIndex();
							String formato = null;
							if(formatojComboBox.getSelectedIndex() != 0)
								formato = (String)formatojComboBox.getSelectedItem();
							for (int w = listReporte.size(); w > 0 ; w--) {
								datosReportedefaultTableModel.removeRow(w - 1);
							}
							listReporte.clear();

							int tipo = tipojComboBox.getSelectedIndex();
							Date dateInicio = null;
							Date dateFin =  null;
							if(fechainiciojCheckBox.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechainicialjCalendar.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateInicio = new Date(p,k,i);	
							}
							if(fechafinjCheckBox.isSelected()){
								GregorianCalendar calendar = (GregorianCalendar) fechafinaljCalendar.getCalendar();
								int i = calendar.get(Calendar.DATE);
								int k = calendar.get(Calendar.MONTH);
								int p = calendar.get(Calendar.YEAR);
								p = p - 1900;
								dateFin = new Date(p,k,i);	
							}
							try {
								listReporte = Lote_Service.listReporteP(estado, formato, tipo, dateInicio, dateFin);
								for (int i = 0; i < listReporte.size(); i++) {
									String producto = null;
									int count = 0;
									boolean found = false;
									while (found == false && count < listProducto.size()) {
										if(listProducto.get(count).getCod_producto().equals(listReporte.get(i).getProducto())){
											producto=listProducto.get(count).getNombre();
											found = true;}
										else count++;
									}
									if(listReporte.get(i).getCod_estado() == 1)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Aprobación"});
									if(listReporte.get(i).getCod_estado() == 2)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Revisión"});
									if(listReporte.get(i).getCod_estado() == 3)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Etiquetado"});
									if(listReporte.get(i).getCod_estado() == 4)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Pendiente Envase"});
									if(listReporte.get(i).getCod_estado() == 5)
										getDatosReportedefaultTableModel().addRow(new Object[]{listReporte.get(i).getCod(), producto, listReporte.get(i).getFormato(), listReporte.get(i).getCant_unidades(), listReporte.get(i).getDiasAprob(),"Listo"});
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

	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(12, 221, 921, 8));
		}
		return jSeparator;
	}

	private JScrollPane getDatosjScrollPane() {
		if (datosjScrollPane == null) {
			datosjScrollPane = new JScrollPane();
			datosjScrollPane.setBounds(new Rectangle(22, 242, 899, 243));
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
			datosReportedefaultTableModel = new UneditableTableModel(new Object[]{"Código", "Producto", "Formato", "Cantidad Unidades", "D/A","Estado"}, 0);
		}
		return datosReportedefaultTableModel;
	}

	private JButton getCrearReportejButton() {
		if (crearReportejButton == null) {
			crearReportejButton = new JButton();
			crearReportejButton.setBounds(new Rectangle(227, 182, 147, 26));
			crearReportejButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Picture3.png")));
			crearReportejButton.setText("Generar Reporte");
			crearReportejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(estadojComboBox.getSelectedIndex() == 0 && formatojComboBox.getSelectedIndex() == 0 && tipojComboBox.getSelectedIndex() == 0 && !fechafinjCheckBox.isSelected() && !fechainiciojCheckBox.isSelected())
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar algún campo", "Error", JOptionPane.ERROR_MESSAGE);
					
					else{
						int estado = 0;
						String formato = null;
						int tipo = 0;
						if(estadojComboBox.getSelectedIndex() != 0)
							estado = estadojComboBox.getSelectedIndex();
						if(formatojComboBox.getSelectedIndex() != 0)
							formato = (String)formatojComboBox.getSelectedItem();
						if(tipojComboBox.getSelectedIndex() != 0)
						  { tipo = tipojComboBox.getSelectedIndex();}
						
						Date dateInicio = null;
						Date dateFin =  null;
						if(fechainiciojCheckBox.isSelected()){
							GregorianCalendar calendar = (GregorianCalendar) fechainicialjCalendar.getCalendar();
							int i = calendar.get(Calendar.DATE);
							int k = calendar.get(Calendar.MONTH);
							int p = calendar.get(Calendar.YEAR);
							p = p - 1900;
							dateInicio = new Date(p,k,i);	
						}
						if(fechafinjCheckBox.isSelected()){
							GregorianCalendar calendar = (GregorianCalendar) fechafinaljCalendar.getCalendar();
							int i = calendar.get(Calendar.DATE);
							int k = calendar.get(Calendar.MONTH);
							int p = calendar.get(Calendar.YEAR);
							p = p - 1900;
							dateFin = new Date(p,k,i);	
						}
						outputToEXL excel = new outputToEXL(address);
						excel.saveReporteDinamicoP(3,estado,formato,tipo,dateInicio,dateFin);
					}
				}
		   });
		}
		return crearReportejButton;
	}

	private GregorianCalendar getCalendar1() {
		if (calendar1 == null) {
			calendar1 = new GregorianCalendar();
			calendar1.setTimeInMillis(System.currentTimeMillis());
		}
		return calendar1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
