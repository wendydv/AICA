package aica.visual.seguridad;

import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Point;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import aica.model.Control;
import aica.model.Traza;
import aica.model.Usuario;
import aica.service.ConnectionBD;
import aica.service.Encriptar;
import aica.service.ServicioUsuario;
import aica.service.Traza_Servicio;
import aica.utiles.UneditableTableModel;
import aica.utiles.Validaciones;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.lang.System;
import java.lang.String;

public class Usuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static Usuarios usuario = null;

	private JPanel jContentPane = null;

	private JLabel userjLabel = null;

	private JLabel passjLabel = null;

	private JLabel roljLabel = null;

	private JTextField userjTextField = null;

	private JComboBox roljComboBox = null;

	private JLabel passconfirmjLabel1 = null;

	private JSeparator jSeparator = null;

	private JScrollPane datosuserjScrollPane = null;

	private JTable datosUserjTable = null;

	private JButton insertarjButton = null;

	private JButton modificarjButton = null;

	private JButton eliminarjButton = null;

	private DefaultTableModel datosUsersdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="634,124"

	private JPasswordField passjPasswordField = null;

	private JPasswordField passconfirmjPasswordField = null;

	private DefaultComboBoxModel roldefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="643,184"
	
	public ArrayList<Usuario> userList = new ArrayList<Usuario>();  //  @jve:decl-index=0:
	
	public ArrayList<Traza> listTrazas = new ArrayList<Traza>();  //  @jve:decl-index=0:

	private JLabel logojLabel = null;
	
	private Date fechaActual = null;  //  @jve:decl-index=0:
	    
	private Usuario loguiado = null;  //  @jve:decl-index=0:

	private JLabel jLabelu = null;

	private JLabel jLabelc = null;

	private JLabel jLabelcc = null;

	private JLabel jLabelr = null;

	private JLabel fechanjLabel = null;

	private GregorianCalendar calendar1 = null;

	private Usuarios() {
		super(usuario);
		initialize();
	}

	public static Usuarios getUsuario() {
		if (usuario == null) {
			usuario = new Usuarios();
		}
		return usuario;
}

	private void initialize() {
		ConnectionBD.testConnect();
		loguiado = Control.getSessionUser();
		this.setSize(474, 510);
		this.setTitle("Usuarios");
		this.setContentPane(getJContentPane());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		LlenarTablaUser();
		GregorianCalendar calendar = new GregorianCalendar();
		int i = calendar.get(Calendar.DATE);
		int k = calendar.get(Calendar.MONTH);
		int p = calendar.get(Calendar.YEAR);
		p = p - 1900;
		Date date = new Date(p,k,i);
		fechaActual = date;
		eliminarjButton.setEnabled(false);
		modificarjButton.setEnabled(false);
		try {
			listTrazas =(ArrayList<Traza>)Traza_Servicio.getAllTraza();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void LlenarTablaUser(){
		try {
			userList = (ArrayList<Usuario>) ServicioUsuario.getAllUsuario();
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i).getRol() == 1)
					getDatosUsersdefaultTableModel().addRow(new Object[]{userList.get(i).getNombre(),"Administrador"});
				if(userList.get(i).getRol() == 2)
					getDatosUsersdefaultTableModel().addRow(new Object[]{userList.get(i).getNombre(),"Analista de producción"});
				if(userList.get(i).getRol() == 3)
					getDatosUsersdefaultTableModel().addRow(new Object[]{userList.get(i).getNombre(),"Técnico"});
				if(userList.get(i).getRol() == 4)
					getDatosUsersdefaultTableModel().addRow(new Object[]{userList.get(i).getNombre(),"Tecnólogo"});
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			String Fecha_actual = "" + getCalendar1().get(Calendar.DATE) + "/" + (getCalendar1().get(Calendar.MONTH) + 1) + "/" + getCalendar1().get(Calendar.YEAR);
			fechanjLabel = new JLabel();
			fechanjLabel.setBounds(new Rectangle(306, 0, 66, 15));
			fechanjLabel.setText(Fecha_actual);
			jLabelr = new JLabel();
			jLabelr.setBounds(new Rectangle(113, 113, 10, 20));
			jLabelr.setText("*");
			jLabelr.setForeground(new Color(255, 51, 51));
			jLabelcc = new JLabel();
			jLabelcc.setBounds(new Rectangle(5, 82, 10, 20));
			jLabelcc.setText("*");
			jLabelcc.setForeground(new Color(255, 51, 51));
			jLabelc = new JLabel();
			jLabelc.setBounds(new Rectangle(65, 51, 10, 20));
			jLabelc.setText("*");
			jLabelc.setForeground(new Color(255, 51, 51));
			jLabelu = new JLabel();
			jLabelu.setBounds(new Rectangle(89, 22, 9, 20));
			jLabelu.setText("*");
			jLabelu.setForeground(new Color(255, 51, 51));
			logojLabel = new JLabel();
			logojLabel.setBounds(new Rectangle(372, 0, 86, 73));
			logojLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/aica - copia.png")));
			logojLabel.setText("");
			passconfirmjLabel1 = new JLabel();
			passconfirmjLabel1.setBounds(new Rectangle(15, 82, 129, 20));
			passconfirmjLabel1.setText("Confirmar Contraseña:");
			roljLabel = new JLabel();
			roljLabel.setBounds(new Rectangle(123, 113, 21, 20));
			roljLabel.setText("Rol:");
			passjLabel = new JLabel();
			passjLabel.setBounds(new Rectangle(75, 51, 69, 20));
			passjLabel.setText("Contraseña:");
			userjLabel = new JLabel();
			userjLabel.setBounds(new Rectangle(97, 22, 47, 20));
			userjLabel.setText("Usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(userjLabel, null);
			jContentPane.add(passjLabel, null);
			jContentPane.add(roljLabel, null);
			jContentPane.add(getUserjTextField(), null);
			jContentPane.add(getRoljComboBox(), null);
			jContentPane.add(passconfirmjLabel1, null);
			jContentPane.add(getJSeparator(), null);
			jContentPane.add(getDatosuserjScrollPane(), null);
			jContentPane.add(getInsertarjButton(), null);
			jContentPane.add(getModificarjButton(), null);
			jContentPane.add(getEliminarjButton(), null);
			jContentPane.add(getPassjPasswordField(), null);
			jContentPane.add(getPassconfirmjPasswordField(), null);
			jContentPane.add(logojLabel, null);
			jContentPane.add(jLabelu, null);
			jContentPane.add(jLabelc, null);
			jContentPane.add(jLabelcc, null);
			jContentPane.add(jLabelr, null);
			jContentPane.add(fechanjLabel, null);
			jContentPane.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					userjTextField.setText("");
					userjTextField.setEditable(true);
					passjPasswordField.setText("");
					passconfirmjPasswordField.setText("");
					roljComboBox.setSelectedIndex(0);
					modificarjButton.setEnabled(false);
					eliminarjButton.setEnabled(false);
					insertarjButton.setEnabled(true);	
					datosUserjTable.clearSelection();
				}
			});
		}
		return jContentPane;
	}

	private JTextField getUserjTextField() {
		if (userjTextField == null) {
			userjTextField = new JTextField();
			userjTextField.setLocation(new Point(159, 22));
			userjTextField.setSize(new Dimension(159, 20));
			Validaciones.validateLetter(userjTextField);
		}
		return userjTextField;
	}

	private JComboBox getRoljComboBox() {
		if (roljComboBox == null) {
			roljComboBox = new JComboBox();
			roljComboBox.setLocation(new Point(159, 113));
			roljComboBox.setSize(new Dimension(159, 20));
			roljComboBox.setModel(getRoldefaultComboBoxModel());
		}
		return roljComboBox;
	}

	private JSeparator getJSeparator() {
		if (jSeparator == null) {
			jSeparator = new JSeparator();
			jSeparator.setBounds(new Rectangle(15, 163, 436, 10));
		}
		return jSeparator;
	}

	private JScrollPane getDatosuserjScrollPane() {
		if (datosuserjScrollPane == null) {
			datosuserjScrollPane = new JScrollPane();
			datosuserjScrollPane.setBounds(new Rectangle(14, 179, 429, 245));
			datosuserjScrollPane.setViewportView(getDatosUserjTable());
		}
		return datosuserjScrollPane;
	}

	private JTable getDatosUserjTable() {
		if (datosUserjTable == null) {
			datosUserjTable = new JTable();
			datosUserjTable.setModel(getDatosUsersdefaultTableModel());
			datosUserjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int post = datosUserjTable.getSelectedRow();
					Usuario usuario = userList.get(post);
					userjTextField.setText(usuario.getNombre());
					roljComboBox.setSelectedIndex(usuario.getRol());
					userjTextField.setEditable(false);
					insertarjButton.setEnabled(false);
					eliminarjButton.setEnabled(true);
					modificarjButton.setEnabled(true);
				}
			});
		}
		return datosUserjTable;
	}

	private JButton getInsertarjButton() {
		if (insertarjButton == null) {
			insertarjButton = new JButton();
			insertarjButton.setBounds(new Rectangle(343, 128, 98, 26));
			insertarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/SyncCenter.png")));
			insertarjButton.setText("Insertar");
			insertarjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(userjTextField.getText().equals("")|| roljComboBox.getSelectedIndex()== 0 || passconfirmjPasswordField.getText().equals("")|| passjPasswordField.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);}
					else{
					try {
						if((passjPasswordField.getPassword().length>= 8) &&( Encriptar.getMd5(new String (getPassjPasswordField().getPassword())).equals(Encriptar.getMd5(new String(getPassconfirmjPasswordField().getPassword()))))){
							ServicioUsuario.insertarUsuario(userjTextField.getText(), getPassjPasswordField().getPassword(), getRoljComboBox().getSelectedIndex());
							Usuario user = new Usuario (userjTextField.getText(), Encriptar.getMd5(new String (getPassjPasswordField().getPassword())), getRoljComboBox().getSelectedIndex());
							userList.add(user);
							getDatosUsersdefaultTableModel().addRow(new Object[]{userjTextField.getText(),getRoljComboBox().getSelectedItem()});
							userjTextField.setText("");
							passconfirmjPasswordField.setText("");
							passjPasswordField.setText("");
							roljComboBox.setSelectedIndex(0);
							Traza traza = new Traza();
							String operacion = "CR-" +"Tabla: usuario" + ", Usuario:"+ user.getNombre();;
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
						}
						else{JOptionPane.showMessageDialog(new JPanel(), "La contraseña no es válida", "Error", JOptionPane.ERROR_MESSAGE);}
							
					} catch (Exception e1) {
						System.out.println("error");// TODO: handle exception
					}
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
				}
			});
		}
		return insertarjButton;
	}

	private JButton getModificarjButton() {
		if (modificarjButton == null) {
			modificarjButton = new JButton();
			modificarjButton.setBounds(new Rectangle(215, 435, 116, 26));
			modificarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/96.png")));
			modificarjButton.setText("Modificar");
			modificarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(userjTextField.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if(userjTextField.getText().equals("")|| roljComboBox.getSelectedIndex()== 0 || passconfirmjPasswordField.getText().equals("")|| passjPasswordField.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe insertar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);}
					
					else {
					if((passjPasswordField.getPassword().length>= 8) &&(Encriptar.getMd5(new String (getPassjPasswordField().getPassword())).equals(Encriptar.getMd5(new String(getPassconfirmjPasswordField().getPassword()))))){
						String userName = userList.get(datosUserjTable.getSelectedRow()).getNombre();
						int pos = datosUserjTable.getSelectedRow();
						try {
							Usuario user = new Usuario();
							user = userList.get(pos);
						    user.setNombre(getUserjTextField().getText());
							user.setContrasena(Encriptar.getMd5(new String (getPassjPasswordField().getPassword())));
							user.setRol(getRoljComboBox().getSelectedIndex());
							ServicioUsuario.updateUsuario(user,userName);
							getDatosUsersdefaultTableModel().setValueAt(user.getNombre(), pos, 0);
							if(user.getRol() == 1)
								getDatosUsersdefaultTableModel().setValueAt("Administrador", pos, 1);
							if(user.getRol() == 2)
								getDatosUsersdefaultTableModel().setValueAt("Analista de producción", pos, 1);
							if(user.getRol() ==3)
								getDatosUsersdefaultTableModel().setValueAt("Técnico", pos, 1);
							if(user.getRol() == 4)
								getDatosUsersdefaultTableModel().setValueAt("Tecnólogo", pos, 1);
							
							userjTextField.setText("");
							userjTextField.setEditable(true);
							datosUserjTable.clearSelection();
							passconfirmjPasswordField.setText("");
							passjPasswordField.setText("");
							roljComboBox.setSelectedIndex(0);
							Traza traza = new Traza();
							String operacion = "U-" +"Tabla: usuario" + ", Usuario:"+ user.getNombre();;
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
					else{JOptionPane.showMessageDialog(new JPanel(), "La contraseña no es válida", "Error", JOptionPane.ERROR_MESSAGE);}
					}
				}
			});
		}
		return modificarjButton;
	}

	private JButton getEliminarjButton() {
		if (eliminarjButton == null) {
			eliminarjButton = new JButton();
			eliminarjButton.setBounds(new Rectangle(344, 435, 99, 26));
			eliminarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Recycle Bin Empty.png")));
			eliminarjButton.setText("Eliminar");
			eliminarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(userjTextField.getText().equals("")){
						JOptionPane.showMessageDialog(new JPanel(), "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					try {
						    int pos = datosUserjTable.getSelectedRow();
							Usuario user = new Usuario();
							user = userList.get(pos);
							String userName = userList.get(pos).getNombre();	
							ServicioUsuario.EliminarUsuario(userName);
							getDatosUsersdefaultTableModel().removeRow(pos);
							userList.remove(pos);
							datosUserjTable.clearSelection();
							userjTextField.setText("");
							userjTextField.setEditable(true);
							System.out.println("Usuario Eliminado");
							Traza traza = new Traza();
							String operacion = "D-" +"Tabla: usuario" + ", Usuario:"+ user.getNombre();;
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
						} 
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace(); 
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			  }
			});
		}
		return eliminarjButton;
	}

	private DefaultTableModel getDatosUsersdefaultTableModel() {
		if (datosUsersdefaultTableModel == null) {
			datosUsersdefaultTableModel = new UneditableTableModel(new Object[]{"Usuario","Rol"}, 0);
		}
		return datosUsersdefaultTableModel;
	}

	private JPasswordField getPassjPasswordField() {
		if (passjPasswordField == null) {
			passjPasswordField = new JPasswordField();
			passjPasswordField.setLocation(new Point(159, 51));
			passjPasswordField.setSize(new Dimension(159, 20));
		}
		return passjPasswordField;
	}

	private JPasswordField getPassconfirmjPasswordField() {
		if (passconfirmjPasswordField == null) {
			passconfirmjPasswordField = new JPasswordField();
			passconfirmjPasswordField.setLocation(new Point(159, 82));
			passconfirmjPasswordField.setSize(new Dimension(159, 20));
		}
		return passconfirmjPasswordField;
	}

	private DefaultComboBoxModel getRoldefaultComboBoxModel() {
		if (roldefaultComboBoxModel == null) {
			roldefaultComboBoxModel = new DefaultComboBoxModel();
			roldefaultComboBoxModel.addElement("<Seleccione>");
			roldefaultComboBoxModel.addElement("Administrador");
			roldefaultComboBoxModel.addElement("Analista de producción");
			roldefaultComboBoxModel.addElement("Técnico");
			roldefaultComboBoxModel.addElement("Tecnólogo");
		}
		return roldefaultComboBoxModel;
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

	/**
	 * This method initializes calendar1	
	 * 	
	 * @return java.util.GregorianCalendar	
	 */
	private GregorianCalendar getCalendar1() {
		if (calendar1 == null) {
			calendar1 = new GregorianCalendar();
			calendar1.setTimeInMillis(System.currentTimeMillis());
		}
		return calendar1;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
