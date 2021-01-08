package aica.service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import aica.model.Lote;
import aica.visual.DatosPendientesEtapas;


public class CustomTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Object value0 = table.getValueAt(row, 0);
		if (value0 != null) {
			Lote lote = (Lote) value0;
			if(DatosPendientesEtapas.getDatosPendientesEtapas().Rechazado(lote)==true){
				cell.setFont(new Font("Dialog", Font.PLAIN, 12));
				cell.setForeground(Color.RED);
			}
			else{
			if (lote.getCodigo_tipo_lote() == 1) {
				cell.setFont(new Font("Dialog", Font.PLAIN, 12));
				cell.setForeground(Color.BLUE);
			 }else {
				cell.setFont(new Font("Dialog", Font.PLAIN, 12));
				cell.setForeground(new Color(20,122,20));
			}
		  }
		}
		return cell;
	}
	
	

}