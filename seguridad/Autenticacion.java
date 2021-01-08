package aica.visual.seguridad;

import javax.swing.JPanel;

import java.awt.Toolkit;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Point;
import javax.swing.JButton;
import aica.model.Control;
import aica.model.Usuario;
import aica.service.ConnectionBD;
import aica.service.ServicioUsuario;
import aica.visual.MainAICA;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Autenticacion extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static Autenticacion autenticacion = null;

	private JPanel jContentPane = null;

	private JLabel userjLabel = null;

	private JLabel passjLabel = null;

	private JPasswordField jPasswordField = null;

	private JTextField userjTextField = null;

	private JButton conectarjButton = null;

	private JButton canceljButton = null;

	//private Control control = null;  //  @jve:decl-index=0:

	private JLabel roljLabel1 = null;

	private JComboBox roljComboBox = null;
	
	private DefaultComboBoxModel roldefaultComboBoxModel = null;  //  @jve:decl-index=0:visual-constraint="643,184"
	

	private Autenticacion() {
		super();
		initialize();
	}
	// patron singleton
	public static Autenticacion getAutenticacion() {
		if (autenticacion == null) {
			autenticacion = new Autenticacion();
		}
		return autenticacion;
}

	private void initialize() {
		ConnectionBD.testConnect();
		this.setSize(287, 199);
		this.setTitle("Control de acceso");
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			roljLabel1 = new JLabel();
			roljLabel1.setBounds(new Rectangle(62, 82, 33, 20));
			roljLabel1.setText("Tipo:");
			passjLabel = new JLabel();
			passjLabel.setBounds(new Rectangle(26, 47, 69, 20));
			passjLabel.setText("Contraseña:");
			userjLabel = new JLabel();
			userjLabel.setBounds(new Rectangle(48, 15, 47, 20));
			userjLabel.setText("Usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(userjLabel, null);
			jContentPane.add(passjLabel, null);
			jContentPane.add(getJPasswordField(), null);
			jContentPane.add(getUserjTextField(), null);
			jContentPane.add(getConectarjButton(), null);
			jContentPane.add(getCanceljButton(), null);
			jContentPane.add(roljLabel1, null);
			jContentPane.add(getRoljComboBox(), null);
		}
		return jContentPane;
	}

	private JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setBounds(new Rectangle(101, 47, 146, 20));
		}
		return jPasswordField;
	}

	private JTextField getUserjTextField() {
		if (userjTextField == null) {
			userjTextField = new JTextField();
			userjTextField.setLocation(new Point(101, 15));
			userjTextField.setSize(new Dimension(146, 20));
		}
		return userjTextField;
	}

	private JButton getConectarjButton() {
		if (conectarjButton == null) {
			conectarjButton = new JButton();
			conectarjButton.setBounds(new Rectangle(23, 118, 98, 26));
			conectarjButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Default Printer.png")));
			conectarjButton.setText("Aceptar");
			conectarjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int rol;
					try {
						int tipo= roljComboBox.getSelectedIndex();
						rol = ServicioUsuario.getLoginUsuario(userjTextField.getText(), jPasswordField.getPassword());
						String contrasena= jPasswordField.getText();
						if(rol==0|| tipo!=rol){
							JOptionPane.showMessageDialog(new JPanel(), "Error en la autenticación", "Error", JOptionPane.ERROR_MESSAGE);
							jPasswordField.setText("");
							roljComboBox.setSelectedIndex(0);
							userjTextField.setText("");
						}else{
							Usuario usuario = new Usuario(userjTextField.getText(),contrasena, rol);
							Control control = new Control();
							control.setSessionUser(usuario);
							MainAICA application = new MainAICA();
							application.getJFrame().setVisible(true);
							dispose();
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return conectarjButton;
	}

	private JButton getCanceljButton() {
		if (canceljButton == null) {
			canceljButton = new JButton();
			canceljButton.setBounds(new Rectangle(143, 118, 104, 26));
			canceljButton.setIcon(new ImageIcon(getClass().getResource("/imagenes/Error.png")));
			canceljButton.setText("Cancelar");
			canceljButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Presentacion presentacion = new Presentacion();
					presentacion.setVisible(true);
					userjTextField.setText("");
					jPasswordField.setText("");
					roljComboBox.setSelectedIndex(0);
					dispose();
				}
			});
		}
		return canceljButton;
	}

	private JComboBox getRoljComboBox() {
		if (roljComboBox == null) {
			roljComboBox = new JComboBox();
			roljComboBox.setBounds(new Rectangle(101, 82, 146, 20));
			roljComboBox.setModel(getRoldefaultComboBoxModel());
		}
		return roljComboBox;
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

}  //  @jve:decl-index=0:visual-constraint="104,11"
