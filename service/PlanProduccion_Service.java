package aica.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import aica.model.Plan_Produccion;

public class PlanProduccion_Service {
	private  static  String INSERT_PLAN  = "insert into plan_produccion (cod_producto,fecha,mes,elaborar,aprobar) values (?,?,?,?,?)";
	private  static  String FIND_PLAN = "select * from plan_produccion where cod_producto = ? and mes= ?";
	private  static  String ALL_PLAN = "select * from plan_produccion cod_producto";
	private  static  String DELETE_PLAN = "delete from plan_produccion where cod_producto = ?";
	private  static  String DELETE_PLAN_MES = "delete from plan_produccion where cod_producto = ? and mes= ?";
	private  static  String UPDATE_PLAN = "update plan_produccion set cod_producto = ?, fecha = ?, mes = ?, elaborar = ?, aprobar = ? where cod_producto = ?";
	private  static  String ALL_Mes = "select mes from mes";
	
	private static StatementManage statementManage = StatementManage.getStatementManage();
	
	public static boolean existePlan(String cod_producto, String mes )throws SQLException{
		Object[] arg = {cod_producto, mes};
		ResultSet resultSet = statementManage.executeSelectSQL(FIND_PLAN, arg);
		if (resultSet.next()) {
			return true;}
		else
			return false;
		}
	
	public static void createPlan(Plan_Produccion plan) throws SQLException {
		Object[] arg = {plan.getCod_producto(), plan.getFecha(), plan.getMes(), plan.getElaborar(),plan.getAprobar()};
		statementManage.executeUpdateSQL(INSERT_PLAN,arg);
	}
	
	public static void deletePlan(String cod_producto) throws SQLException{
		Object[] arg = {cod_producto};
		statementManage.executeUpdateSQL(DELETE_PLAN,arg);
	}
	public static void deletePlanMes(String cod_producto, String mes) throws SQLException{
		Object[] arg = {cod_producto, mes};
		statementManage.executeUpdateSQL(DELETE_PLAN_MES,arg);
	}
	public static void updatePlan(Plan_Produccion plan, String cod_producto) throws SQLException {
		Object[] arg = {plan.getCod_producto(), plan.getFecha(), plan.getMes(), plan.getElaborar(),plan.getAprobar(), cod_producto};
		statementManage.executeUpdateSQL(UPDATE_PLAN, arg);
	}
	public static Collection getAllPlan() throws SQLException{
		ArrayList<Plan_Produccion> PlanList = new ArrayList<Plan_Produccion>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_PLAN, null);
		while (resultSet.next()) {
			PlanList.add(getPlanVO(resultSet));
		}
		return PlanList;
	}
	public static Plan_Produccion getPlanVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_PLAN, new Object[]{entity});
			resultSet.next();
		}
		Plan_Produccion plan = new Plan_Produccion();
		plan.setCod_producto(resultSet.getString("cod_producto"));
		plan.setFecha(resultSet.getDate("fecha"));
		plan.setMes(resultSet.getString("mes"));
		plan.setElaborar(resultSet.getInt("elaborar"));
		plan.setAprobar(resultSet.getInt("aprobar"));
		return plan;
	}
	
	public static ArrayList<Plan_Produccion> listFiltros(String producto) throws SQLException {
		String sql = "select plan_produccion.*, producto.* from producto inner join plan_produccion on producto.cod_producto = plan_produccion.cod_producto";
		boolean e = false;
		if( producto != null){
			sql = sql + " where ";
		}
		if(producto != null){
			if(e == true){
				sql = sql + " and producto.nombre = '" + producto + "'";
			}
			else {
				sql = sql + "producto.nombre =  '" + producto + "'";
				e = true;
			}
		}
		ArrayList<Plan_Produccion> PlanList = new ArrayList<Plan_Produccion>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			PlanList.add(getPlanVO(resultSet));
		}
		return PlanList;
	}
	public static Collection getAllMes() throws SQLException{
		ArrayList<String> mesList= new ArrayList<String>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_Mes, null);
		while (resultSet.next()) {
			mesList.add(getMesVO(resultSet));
		}
		return mesList;
	}
	public static String getMesVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		String mes = resultSet.getString("mes");
		return mes;
	}
	public static ArrayList<Plan_Produccion> PlanList(String mes) throws SQLException {
		String sql = "select plan_produccion.* from plan_produccion";
		boolean e = false;
		if( mes != null){
			sql = sql + " where ";
		}
		if(mes != null){
			if(e == true){
				sql = sql + " and plan_produccion.mes = '" + mes + "'";
			}
			else {
				sql = sql + "plan_produccion.mes =  '" + mes + "'";
				e = true;
			}
		}
		ArrayList<Plan_Produccion> PlanList = new ArrayList<Plan_Produccion>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			PlanList.add(getPlanVO(resultSet));
		}
		return PlanList;
	}
	public static ArrayList<Plan_Produccion> listReportePlan(String mes, String producto) throws SQLException {
		String sql = "select plan_produccion.*, producto.* from producto inner join plan_produccion on producto.cod_producto = plan_produccion.cod_producto";
		boolean e = false;
		if(mes != null || producto != null){
			sql = sql + " where ";
		}
		if(mes != null){
			sql = sql + "plan_produccion.mes = '" + mes + "'";
			e = true;
		}
		if(producto != null){
			if(e == true){
				sql = sql + " and producto.nombre = '" + producto + "'";	
			}
			else {
				sql = sql + "producto.nombre = '" + producto + "'";
				e = true;
			} 
		}
		ArrayList<Plan_Produccion> loteList = new ArrayList<Plan_Produccion>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getPlanVO(resultSet));
		}
		return loteList;
	}
}
