package aica.visual.seguridad;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import aica.service.ConnectionBD;
import aica.service.DynamicPackagesLoader;
import aica.visual.Acerca;
import aica.visual.Ayuda;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

public class Presentacion extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel logojLabel = null;

	private JLabel jLabelInformation = null;

	private JLabel jLabelAuthenticate = null;

	private JLabel jLabelHelp = null;

	public Presentacion() {
		super();
		initialize();
	}
	
	public static void main(String[] args) {
		File[] libFiles = (new File("lib")).listFiles();
		for (File file : libFiles) {
			try {
				DynamicPackagesLoader.addFile(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Presentacion presentacion = new Presentacion();
				presentacion.setVisible(true);
			}
		});
	}


	private void initialize() {
		ConnectionBD.testConnect();
		this.setSize(615, 338);
		this.setLocation(new Point(0, 0));
		this.setContentPane(getJContentPane());
		this.setTitle("AICA");
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {		
			
			jLabelHelp = new JLabel();
			jLabelHelp.setBounds(new Rectangle(12, 191, 36, 36));
			jLabelHelp.setText("");
			jLabelHelp.setToolTipText("Ayuda");
			jLabelHelp.setIcon(new ImageIcon(getClass().getResource("/imagenes/help blue.png")));
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
			jLabelAuthenticate = new JLabel();
			jLabelAuthenticate.setBounds(new Rectangle(490, 214, 50, 50));
			jLabelAuthenticate.setText("");
			jLabelAuthenticate.setToolTipText("Autenticarse");
			jLabelAuthenticate.setIcon(new ImageIcon(getClass().getResource("/imagenes/authenticate blue1.png")));
			jLabelAuthenticate.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					jLabelAuthenticate.setIcon(new ImageIcon(getClass().getResource("/imagenes/authenticate blue1.png")));
					System.out.println("mouseExited()"); // TODO Auto-generated Event stub mouseExited()
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Autenticacion.getAutenticacion().setVisible(true);
					dispose();
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
			 jLabelAuthenticate.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
					public void mouseMoved(java.awt.event.MouseEvent e) {
						jLabelAuthenticate.setIcon(new ImageIcon(getClass().getResource("/imagenes/authenticate blue2.png")));
						System.out.println("mouseMoved()"); // TODO Auto-generated Event stub mouseMoved()
						}
					});
			logojLabel = new JLabel();
			logojLabel.setBounds(new Rectangle(-2, 0, 600, 300));
			logojLabel.setText("");
			logojLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/presentacion.png")));
			
			jLabelInformation = new JLabel();
			jLabelInformation.setBounds(new Rectangle(60, 228, 36, 36));
			jLabelInformation.setText("");
			jLabelInformation.setToolTipText("Información");
			jLabelInformation.setIcon(new ImageIcon(getClass().getResource("/imagenes/info blue.png")));	
			jLabelInformation.addMouseListener(new java.awt.event.MouseAdapter() {   
				public void mouseExited(java.awt.event.MouseEvent e) {    
					jLabelInformation.setIcon(new ImageIcon(getClass().getResource("/imagenes/info blue.png")));
					System.out.println("mouseExited()"); // TODO Auto-generated Event stub mouseExited()
				}
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Acerca acerca = new Acerca();
					acerca.setVisible(true);
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
				}
			});
			jLabelInformation.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
						public void mouseMoved(java.awt.event.MouseEvent e) {
							jLabelInformation.setIcon(new ImageIcon(getClass().getResource("/imagenes/info blue1.png")));
							System.out.println("mouseMoved()"); // TODO Auto-generated Event stub mouseMoved()
						}
					});
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelInformation, null);
			jContentPane.add(jLabelAuthenticate, null);
			jContentPane.add(jLabelHelp, null);
			jContentPane.add(logojLabel, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="21,11"
