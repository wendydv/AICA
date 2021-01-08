package aica.visual;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import java.awt.ComponentOrientation;
import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import java.awt.Point;

public class Acerca extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jButtonAceptar = null;

	private JLabel jLabel4 = null;

	private JEditorPane jEditorPane = null;

	private JPanel jPanel = null;

	private JEditorPane jEditorPane1 = null;

	private JEditorPane jEditorPane2 = null;

	private JEditorPane jEditorPane3 = null;

	private JLabel jLabel = null;

	private JEditorPane jEditorPane4 = null;

	private JLabel jLabelHelp = null;

	/**
	 * This is the default constructor
	 */
	public Acerca() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(538, 339);
		this.setFont(new Font("Dialog", Font.PLAIN, 12));
		this.setContentPane(getJContentPane());
		this.setTitle("Acerca de...");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelHelp = new JLabel();
			jLabelHelp.setBounds(new Rectangle(14, 247, 36, 36));
			jLabelHelp.setIcon(new ImageIcon(getClass().getResource("/imagenes/help blue.png")));
			jLabelHelp.setText("");
			jLabelHelp.setToolTipText("Ayuda");
			jLabelHelp.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) { 
					jLabelHelp.setIcon(new ImageIcon(getClass().getResource("/imagenes/help blue.png")));
					System.out.println("mouseExited()"); // TODO Auto-generated Event stub mouseExited()
				}   
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Ayuda.getAyuda().setVisible(true);
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
			jLabelHelp.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
				public void mouseMoved(java.awt.event.MouseEvent e) {
					jLabelHelp.setIcon(new ImageIcon(getClass().getResource("/imagenes/help blue1.png")));
					System.out.println("mouseMoved()"); // TODO Auto-generated Event stub mouseMoved()
				}
			});
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(149, 1, 373, 25));
			jLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/acerca.png")));
			jLabel.setText("");
			jLabel4 = new JLabel();
			jLabel4.setIcon(new ImageIcon(getClass().getResource("/imagenes/informacion.png")));
			jLabel4.setSize(new Dimension(148, 236));
			jLabel4.setLocation(new Point(0, -1));
			jLabel4.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButtonAceptar(), null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabelHelp, null);
		}
		return jContentPane;
	}

	private JButton getJButtonAceptar() {
		if (jButtonAceptar == null) {
			jButtonAceptar = new JButton();
			jButtonAceptar.setBounds(new Rectangle(405, 257, 98, 26));
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.setIcon(new ImageIcon(getClass().getResource("/imagenes/Default Printer.png")));
			jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dispose();
				}
			});
		}
		return jButtonAceptar;
	}

	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setContentType("text/plain");
			jEditorPane.setComponentOrientation(ComponentOrientation.UNKNOWN);
			jEditorPane.setBounds(new Rectangle(-2, 10, 380, 54));
			jEditorPane.setForeground(Color.black);
			jEditorPane.setFont(new Font("Dialog", Font.BOLD, 12));
			jEditorPane.setText("         Sistema para la Gestión y Planificación de la Producción                                    en la Empresa Laboratorios AICA");
			jEditorPane.setEditable(false);
		}
		return jEditorPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(147, 26, 376, 204));
			jPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			jPanel.add(getJEditorPane(), null);
			jPanel.add(getJEditorPane1(), null);
			jPanel.add(getJEditorPane2(), null);
			jPanel.add(getJEditorPane3(), null);
			jPanel.add(getJEditorPane4(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jEditorPane1	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane1() {
		if (jEditorPane1 == null) {
			jEditorPane1 = new JEditorPane();
			jEditorPane1.setBounds(new Rectangle(-2, 63, 380, 40));
			jEditorPane1.setFont(new Font("Dialog", Font.BOLD, 12));
			jEditorPane1.setText("         Versión: 1.0                                                                                                      Autores: Wendy Díaz Valdés ");
		    jEditorPane1.setEditable(false);
		}
		return jEditorPane1;
	}

	private JEditorPane getJEditorPane2() {
		if (jEditorPane2 == null) {
			jEditorPane2 = new JEditorPane();
			jEditorPane2.setBounds(new Rectangle(-2, 102, 378, 66));
			jEditorPane2.setFont(new Font("Dialog", Font.BOLD, 12));
			jEditorPane2.setText("                         Jenny Fajardo Calderín                                                                                  " +
					"Ernesto López LLanusa");
			jEditorPane2.setEditable(false);
		}
		return jEditorPane2;
	}

	private JEditorPane getJEditorPane3() {
		if (jEditorPane3 == null) {
			jEditorPane3 = new JEditorPane();
			jEditorPane3.setFont(new Font("Dialog", Font.BOLD, 12));
			jEditorPane3.setText("                                                2012-2013");
			jEditorPane3.setBounds(new Rectangle(-1, 166, 379, 38));
			jEditorPane3.setEditable(false);
		}
		return jEditorPane3;
	}

	/**
	 * This method initializes jEditorPane4	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane4() {
		if (jEditorPane4 == null) {
			jEditorPane4 = new JEditorPane();
			jEditorPane4.setBounds(new Rectangle(-2, 0, 376, 12));
			jEditorPane4.setEditable(false);
		}
		return jEditorPane4;
	}

}  //  @jve:decl-index=0:visual-constraint="39,6"
