package aica.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import aica.model.Formato_Producto;
import aica.model.Producto;

public class Prod_Services {

	private  static  String INSERT_PROD  = "insert into producto (cod_producto,nombre,consumo_diario,plan_anual,precio,cod_quimefa) values (?,?,?,?,?,?)";
	private  static  String FIND_PROD = "select * from producto where cod_producto = ?";
	private  static  String ALL_PROD = "select * from producto order by cod_producto";
	private  static  String DELETE_PROD = "delete from producto where cod_producto = ?";
	private  static  String UPDATE_PROD = "update producto set nombre = ?, consumo_diario = ?, plan_anual = ?, precio = ?, cod_quimefa = ? where cod_producto = ?";

    private  static  String INSERT_FORMATO_PRODUCC  = "insert into formato_producto (cod_producto,id_formato) values (?,?)";
	private  static  String ALL_FORMATO_PRODUCC = "select * from formato_producto";
	private  static  String DELETE_FORMATO_PRODUCC = "delete from formato_producto where cod_producto = ? and id_formato = ?";
	private  static  String FIND_FORMATO_PRODUCC = "select * from formato_producto where cod_producto = ? and id_formato = ?";
	
	private static StatementManage statementManage = StatementManage.getStatementManage();
	
	public static boolean existeProducto(String producto)throws SQLException{
		Object[] arg = {producto};
		ResultSet resultSet = statementManage.executeSelectSQL(FIND_PROD, arg);
		if (resultSet.next()) {
			return true;}
		else
			return false;
		}
	public static void createProd(Producto producto) throws SQLException {
		Object[] arg = {producto.getCod_producto(), producto.getNombre(),producto.getConsumo_diario(), producto.getPlan_anual(), producto.getPrecio(), producto.getCod_quimefa()};
		statementManage.executeUpdateSQL(INSERT_PROD,arg);
	}
	public static void createProdFormato(Formato_Producto producto) throws SQLException {
		Object[] arg = {producto.getCod_producto(), producto.getId_formato()};
		statementManage.executeUpdateSQL(INSERT_FORMATO_PRODUCC,arg);
	}
	public static void deleteProd(String id) throws SQLException{
		Object[] arg = {id};
		statementManage.executeUpdateSQL(DELETE_PROD,arg);
	}
	public static void deleteProdFormato(String cod_producto, int id_formato) throws SQLException{
		Object[] arg = {cod_producto, id_formato};
		statementManage.executeUpdateSQL(DELETE_FORMATO_PRODUCC,arg);
	}
	public static void updateProd(Producto producto, String cod) throws SQLException {
		Object[] arg = {producto.getNombre(),producto.getConsumo_diario(), producto.getPlan_anual(), producto.getPrecio(), producto.getCod_quimefa(),cod};
		statementManage.executeUpdateSQL(UPDATE_PROD,arg);
   }
	public static Collection getAllProd() throws SQLException{
		ArrayList<Producto> prodList = new ArrayList<Producto>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_PROD, null);
		while (resultSet.next()) {
			prodList.add(getProdVO(resultSet));
		}
		return prodList;
	}
	public static Collection getAllFormatoProd() throws SQLException{
		ArrayList<Formato_Producto> prodListf = new ArrayList<Formato_Producto>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_FORMATO_PRODUCC, null);
		while (resultSet.next()) {
			prodListf.add(getProdVOFormato(resultSet));
		}
		return prodListf;
	}
	public static Producto getProdVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_PROD, new Object[]{entity});
			resultSet.next();
		}
		Producto prod = new Producto();
		prod.setCod_producto(resultSet.getString("cod_producto"));
		prod.setNombre(resultSet.getString("nombre"));
		prod.setConsumo_diario(resultSet.getInt("consumo_diario"));
		prod.setPlan_anual(resultSet.getInt("plan_anual"));
		prod.setPrecio(resultSet.getFloat("precio"));
		prod.setCod_quimefa(resultSet.getString("cod_quimefa"));
	  return prod;
	}
	public static Producto getProdVOCod(Object cod_producto) throws SQLException{
		ResultSet resultSetX = null;
		if(cod_producto instanceof ResultSet)
			resultSetX = (ResultSet) cod_producto;
		else{
			resultSetX = statementManage.executeSelectSQL(FIND_PROD, new Object[]{cod_producto});
			resultSetX.next();
		}
		Producto prod = new Producto();
		prod.setCod_producto(resultSetX.getString("cod_producto"));
		prod.setNombre(resultSetX.getString("nombre"));
		prod.setConsumo_diario(resultSetX.getInt("consumo_diario"));
		prod.setPlan_anual(resultSetX.getInt("plan_anual"));
		prod.setPrecio(resultSetX.getInt("precio"));
		prod.setCod_quimefa(resultSetX.getString("cod_quimefa"));
	  return prod;
	}
	public static Formato_Producto getProdVOFormato(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_FORMATO_PRODUCC, new Object[]{entity});
			resultSet.next();
		}
		Formato_Producto prodf = new Formato_Producto();
		prodf.setCod_producto(resultSet.getString("cod_producto"));
		prodf.setId_formato(resultSet.getInt("id_formato"));
		return prodf;
	}
	
}
