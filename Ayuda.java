package aica.visual;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTree;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Ayuda extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Ayuda ayuda = null;  //  @jve:decl-index=0:visual-constraint="1016,13"

	private JPanel jContentPane = null;

	private JTree jTree = null;

	private JLabel jLabel = null;

	private DefaultMutableTreeNode root;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode informacion;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode requerimientos;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode acceso;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode menu;//  @jve:decl-index=0:
	
	private DefaultMutableTreeNode usuarios;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode producción;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode planificación ;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode reportes;  //  @jve:decl-index=0:
	
	private DefaultMutableTreeNode productos;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode lotes;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode calidad;  //  @jve:decl-index=0:
	
	private DefaultMutableTreeNode plan;  //  @jve:decl-index=0:
	private DefaultMutableTreeNode parte;  //  @jve:decl-index=0:

	private DefaultTreeModel defaultTreeModel = null;  //  @jve:decl-index=0:visual-constraint="1049,58"

	private JEditorPane jEditorPane ;

	private JScrollPane jScrollPane = null;
	
	private JPanel panel = null;
	
	public String path = "/ayuda/contenido.html";  //  @jve:decl-index=0:
	

	private Ayuda() {
		super();
		initialize();
	}
	public static Ayuda getAyuda() {
		if (ayuda == null) {
			ayuda = new Ayuda();
		}
		return ayuda;
}
	private void initialize() {
		this.setSize(891, 567);
		this.setContentPane(getJContentPane());
		this.setTitle("Ayuda - Sistema para la Gestión y Planificación de la Producción en la Empresa Laboratorios AICA");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2,((screenSize.height - getHeight()) / 2));
		//String path = "/ayuda/contenido.html";
		
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			jLabel.setBounds(new Rectangle(231, 0, 643, 36));
			jLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));
			jLabel.setText("JLabel");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTree(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJScrollPane(path), null);
		}
		return jContentPane;
	}

	private JTree getJTree() {
		if (jTree == null) {
			jTree = new JTree();
			jTree.setBounds(new Rectangle(0, 0, 230, 528));
			jTree.setFont(new Font("Dialog", Font.BOLD, 12));
			jTree.setModel(getDefaultTreeModel());
			jTree.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					System.out.println("mouseClicked()"); // TODO Auto-generated Event stub mouseClicked()
					if(jTree.getSelectionModel().getLeadSelectionRow()== 0){
						path = "/ayuda/contenido.html";
						ActualizarEditor(path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 1){
						path = "/ayuda/Información general.html";
						ActualizarEditor(path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 2){
				 	    path = "/ayuda/Requerimientos.html";
				 	   ActualizarEditor(path);
				 	   
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 3){
				 	    path = "/ayuda/Control de acceso.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 4){
				 	    path = "/ayuda/Menu Principal.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 5){
				 	    path = "/ayuda/Gestión de usuarios.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 6){
				 	    path = "/ayuda/Gestión de la producción.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 7){
				 	    path = "/ayuda/Gestion productos.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 8){
				 	    path = "/ayuda/Gestión de lotes.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 9){
				 	    path = "/ayuda/calidad.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 10){
				 	    path = "/ayuda/Planificación de la producción.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 11){
				 	    path = "/ayuda/Plan anual.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 12){
				 	    path = "/ayuda/Parte diario.html";
				 	   ActualizarEditor (path);
					}
					if(jTree.getSelectionModel().getLeadSelectionRow()== 13){
				 	    path = "/ayuda/Reportes.html";
				 	   ActualizarEditor (path);
					}

					
				}
			});
		}
		return jTree;
	}
	
	  public JPanel getDemoPanel() {
			return panel;
		    }

	private DefaultTreeModel getDefaultTreeModel() {
		if (defaultTreeModel == null) {
			defaultTreeModel = new DefaultTreeModel(root);
			root = new DefaultMutableTreeNode("Contenido");
			informacion = new DefaultMutableTreeNode("Información General");
			requerimientos = new DefaultMutableTreeNode("Requerimientos");
			acceso = new DefaultMutableTreeNode("Control de Acceso");
			menu = new DefaultMutableTreeNode("Menú Principal");
			usuarios = new DefaultMutableTreeNode("Gestión de Usuarios");
			producción = new DefaultMutableTreeNode("Gestión de la Producción");
			planificación = new DefaultMutableTreeNode("Planificación de la Producción");
			reportes = new DefaultMutableTreeNode("Obtención de Reportes");
			productos = new DefaultMutableTreeNode("Gestión de Productos");
			lotes = new DefaultMutableTreeNode("Gestión de Lotes");
		    calidad = new DefaultMutableTreeNode("Valiadar Calidad");
		    plan = new DefaultMutableTreeNode("Plan Anual");
		    parte = new DefaultMutableTreeNode("Parte Diario");
			defaultTreeModel.setRoot(root);
			root.add(informacion);
			root.add(requerimientos);
			root.add(acceso);
			root.add(menu);
			menu.add(usuarios);
			menu.add(producción);
			menu.add(planificación);
			menu.add(reportes);
			producción.add(productos);
			producción.add(lotes);
			producción.add(calidad);
			planificación.add(plan);
			planificación.add(parte);
		}
		return defaultTreeModel;
	}

	public JEditorPane getJEditorPane(String path) {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
								     } 
		try { 
			 URL url = null; 
		 	    try {
		 		url = getClass().getResource(path);
		 	   } catch (Exception e) { 
		 	 		System.err.println("Failed to open " + path); 
		 	 		url = null; 
		 	             } 
		 	  if(url != null) { 
		 		jEditorPane  = new JEditorPane(url); 
		 		jEditorPane.setEditable(false); 
		 		/*jEditorPane.addHyperlinkListener(createHyperLinkListener()); 
		 		JScrollPane scroller = new JScrollPane(); 
		 		scroller.setFont(new Font("Dialog", Font.PLAIN, 14));
		 		JViewport vp = scroller.getViewport(); 
		 		vp.add(jEditorPane); */
		                // getDemoPanel().add(scroller, BorderLayout.CENTER); 
	             } 
		         } catch (MalformedURLException e) { 
		             System.out.println("Malformed URL: " + e);}  
		         catch (IOException e) { 
		             System.out.println("IOException: " + e); } 
		
			
		return jEditorPane;
	}
	public void ActualizarEditor (String path){
		URL url = null; 
 	    url = getClass().getResource(path);
 	   try {
 		   jEditorPane.setPage(url);
			} catch (IOException ioe) {
			    System.out.println("IOE: " + ioe);
			}
	}

		public HyperlinkListener createHyperLinkListener() { 
		 	return new HyperlinkListener() { 
		 	    public void hyperlinkUpdate(HyperlinkEvent e) { 
		 		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) { 
		 		    if (e instanceof HTMLFrameHyperlinkEvent) { 
		 			((HTMLDocument)jEditorPane.getDocument()).processHTMLFrameHyperlinkEvent( 
		 			    (HTMLFrameHyperlinkEvent)e); 
		 		    } else { 
		 			try { 
		 				jEditorPane.setPage(e.getURL()); 
		 			} catch (IOException ioe) { 
		 			    System.out.println("IOE: " + ioe); 
		 			} 
		 		    } 
		 		} 
		 	    } 
		 	}; 
		     } 
		      
		     void updateDragEnabled(boolean dragEnabled) { 
		    	 jEditorPane.setDragEnabled(dragEnabled); 
		     } 

	private JScrollPane getJScrollPane(String path) {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(231, 35, 644, 494));
			jScrollPane.setViewportView(getJEditorPane(path));
		}
		return jScrollPane;
	}


}  //  @jve:decl-index=0:visual-constraint="32,15"
