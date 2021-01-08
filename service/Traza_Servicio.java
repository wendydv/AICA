package aica.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import aica.model.Traza;

public class Traza_Servicio {
	private  static  String INSERT_Traza  = "insert into Traza (usuario,operacion,fecha) values (?,?,?)";
	private  static  String FIND_Traza = "select * from Traza where usuario = ? and fecha = ?";
	private  static  String ALL_Traza = "select * from Traza";
	private  static  String DELETE_Traza = "delete from Traza where usuario = ? and fecha = ?";
	private  static  String UPDATE_Traza = "update Traza set usuario = ?, operacion = ?, fecha = ? where usuario = ? and fecha = ?";

	private static StatementManage statementManage = StatementManage.getStatementManage();
	
	public static boolean existeTraza(String usuario, Date fecha)throws SQLException{
		Object[] arg = {usuario,fecha};
		ResultSet resultSet = statementManage.executeSelectSQL(FIND_Traza, arg);
		if (resultSet.next()) {
			return true;}
		else
			return false;
		}
		
	public static void createTraza(Traza traza) throws SQLException {
		Object[] arg = {traza.getUsuario(), traza.getOperacion(), traza.getFecha()};
		statementManage.executeUpdateSQL(INSERT_Traza,arg);
	}
	public static void deleteTraza(String usuario, Date fecha) throws SQLException{
		Object[] arg = {usuario,fecha};
		statementManage.executeUpdateSQL(DELETE_Traza,arg);
	}
	public static void updateTraza(Traza traza, String usuario, Date fecha) throws SQLException {
		Object[] arg = {traza.getUsuario(), traza.getOperacion(), traza.getFecha(), usuario, fecha};
		statementManage.executeUpdateSQL(UPDATE_Traza,arg);
	}
	
	public static Collection getAllTraza() throws SQLException{
		ArrayList<Traza> prodList = new ArrayList<Traza>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_Traza, null);
		while (resultSet.next()) {
			prodList.add(getTrazaVO(resultSet));
		}
		return prodList;
		
	}
	public static Traza getTrazaVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_Traza, new Object[]{entity});
			resultSet.next();
		}
		Traza traza = new Traza();
		traza.setUsuario(resultSet.getString("usuario"));
		traza.setOperacion(resultSet.getString("operacion"));
		traza.setFecha(resultSet.getDate("fecha"));
		return traza;
	}
}
