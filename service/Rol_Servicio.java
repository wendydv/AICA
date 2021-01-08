package aica.service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import aica.model.Rol;

public class Rol_Servicio {

	public static LinkedList<Rol> getRoles() throws SQLException, ClassNotFoundException{

		LinkedList<Rol> listRol = new LinkedList<Rol>();
		Statement consulta = ConnectionBD.getConnectionBD().getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"rol\".\"rol\",\"public\".\"rol\".\"descripcion\" " +
		"FROM  \"public\".\"rol\"";
		ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Rol rol = new Rol();
			rol.setRol(resultado.getString(1));
			rol.setDescripcion(resultado.getString(2));
			listRol.add(rol);
		}
		return listRol;
	}
}
