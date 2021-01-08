package aica.service;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import aica.model.Lote;

public class Lote_Service {
	private  static  String INSERT_LOTE  = "insert into lote (cod_lote,dias_aprob,cod_producto,cod_estado,cod_tipo_lote,fecha_elaboracion,cantidad_unidades,formato) values (?,?,?,?,?,?,?,?)";
	private  static  String FIND_LOTE = "select * from lote where cod_lote = ?";
	private  static  String ALL_LOTE = "select * from lote order by formato, cod_lote";
	
	private  static  String INSERT_LOTE_PRODUCC  = "insert into lote_produccion (id_lote, analisis, revision, etiquetado, envase, aprobacion_final) values (?, ?, ?, ?, ?, ?)"; //fecha_analisis, fecha_revision, fecha_etiquetado, fecha_envase, fecha_af
	private  static  String UPDATE_LOTE_PRODUCC = "update lote_produccion set fecha_analisis = ?, fecha_revision = ?, fecha_envase = ?, fecha_etiquetado = ?, fecha_af = ?, analisis = ?, revision = ?, etiquetado = ?, envase = ?, aprobacion_final = ? where id_lote = ?"; // analisis revision etiquetado envase
	private  static  String FIND_LOTE_PRODUCC = "select * from lote_produccion where id_lote = ?";
	
	private  static  String DELETE_LOTE = "delete from lote where cod_lote = ?";
	private  static  String UPDATE_LOTE = "update lote set cod_lote = ?, dias_aprob = ?, cod_producto = ?, cod_estado = ?, cod_tipo_lote = ?, fecha_elaboracion = ?, cantidad_unidades = ?, formato = ? where cod_lote = ?";
    private  static  String UPDATE_ESTADO_LOTE = "update lote set cod_estado = ? where cod_lote = ?";
    private  static  String FORMATO = "select formato from formato"; 
    private  static  String FORMATO_NOMB = "select formato from formato where id_formato = ?";
    private  static  String ESTADO = "select estado from estado_lote";
    
    private  static  String INSERT_LOTE_RECHAZADO  = "insert into lote_rechazado (cod_lote, cod_estado, fecha) values (?,?,?)";
    private  static  String ALL_LOTE_RECHAZADO = "select * from lote_rechazado ";
    private  static  String DELETE_LOTE_RECHAZADO = "delete from lote_rechazado where cod_lote = ?";
    private  static  String FIND_LOTE_RECHAZADO = "select * from lote_rechazado where cod_lote = ?";
	    
	private static StatementManage statementManage = StatementManage.getStatementManage();
	
	public static boolean existeLote(String lote)throws SQLException{
		Object[] arg = {lote};
		ResultSet resultSet = statementManage.executeSelectSQL(FIND_LOTE, arg);
		if (resultSet.next()) {
			return true;}
		else
			return false;
		}
	
	public static void createLote(Lote lote) throws SQLException {
		Object[] arg = {lote.getCod(), lote.getDiasAprob(), lote.getProducto(), 1 , lote.getCodigo_tipo_lote(), lote.getFecha_elaboracion(),lote.getCant_unidades(),lote.getFormato()};
		statementManage.executeUpdateSQL(INSERT_LOTE,arg);
		createLoteProduccion(lote);
	}
	
	public static void createLoteProduccion(Lote lote) throws SQLException {
		Object[] arg = {lote.getCod(), lote.isEstado_analisis(), lote.isEstado_revision(), lote.isEstado_etiquetado(), lote.isEstado_envase(), lote.isEstado_aprobacion_final()};
		statementManage.executeUpdateSQL(INSERT_LOTE_PRODUCC,arg);
	}
	
	public static void createLoteRechazado(Lote lote) throws SQLException {
		Object[] arg = {lote.getCod(), lote.getCod_estado(), lote.getFecha()};
		statementManage.executeUpdateSQL(INSERT_LOTE_RECHAZADO,arg);
	}
	
	public static void deleteLote(String id) throws SQLException{
		Object[] arg = {id};
		statementManage.executeUpdateSQL(DELETE_LOTE,arg);
	}
	
	public static void deleteLoteRechazado(String id) throws SQLException{
		Object[] arg = {id};
		statementManage.executeUpdateSQL(DELETE_LOTE_RECHAZADO,arg);
	}
	public static void updateLote(Lote lote, String cod) throws SQLException {
		Object[] arg = {lote.getCod(), lote.getDiasAprob(), lote.getProducto(), lote.getCod_estado(), lote.getCodigo_tipo_lote(), lote.getFecha_elaboracion(),lote.getCant_unidades(),lote.getFormato(),cod};
		statementManage.executeUpdateSQL(UPDATE_LOTE, arg);
		
		Object[] argUpt = {lote.getFecha_analisis(), lote.getFecha_revisado(), lote.getFecha_envase(), lote.getFecha_etiquetado(), lote.getFecha_aprobacion_final(), lote.isEstado_analisis(), lote.isEstado_revision(), lote.isEstado_etiquetado(), lote.isEstado_envase(), lote.isEstado_aprobacion_final(), lote.getCod()};
		statementManage.executeUpdateSQL(UPDATE_LOTE_PRODUCC, argUpt);
	}
	public static void updateEstadoLote(Lote lote, String cod) throws SQLException {
		Object[] arg = {lote.getCod_estado(), cod};
		statementManage.executeUpdateSQL(UPDATE_ESTADO_LOTE,arg);
	}
	
	public static Collection getAllLote() throws SQLException{
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_LOTE, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	
	public static Collection getAllLoteRechazado() throws SQLException{
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_LOTE_RECHAZADO, null);
		while (resultSet.next()) {
			loteList.add(getLoteVORechazado(resultSet));
		}
		return loteList;
	}
	
	public static Collection getAllFormato() throws SQLException{
		ArrayList<String> formatosList= new ArrayList<String>();
		ResultSet resultSet = statementManage.executeSelectSQL(FORMATO, null);
		while (resultSet.next()) {
			formatosList.add(getFormatoVO(resultSet));
		}
		return formatosList;
	}
	
	public static String getFormatoVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		String formato = resultSet.getString("formato");
		return formato;
	}
	
	public static String getFormato_Nom(int id_formato) throws SQLException{
		String nom_formato = null;
		Object[] arg = {id_formato};
		ResultSet resultSet = statementManage.executeSelectSQL(FORMATO_NOMB, arg);
		while (resultSet.next()) {
			nom_formato= getFormatoVO(resultSet);
		}
		return nom_formato;
	}
	
	public static Collection getAllEstado() throws SQLException{
		ArrayList<String> estadosList = new ArrayList<String>();
		ResultSet resultSet = statementManage.executeSelectSQL(ESTADO, null);
		while (resultSet.next()) {
			estadosList.add(getEstadosVO(resultSet));
		}
		return estadosList;
	}
	
	public static String getEstadosVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		String estados = resultSet.getString("estado");
		return estados;
	}
	
	public static Lote getLoteVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_LOTE, new Object[]{entity});
			resultSet.next();
		}
		
		Lote lote = findLote(resultSet.getString("cod_lote"));
		lote.setProducto(resultSet.getString("cod_producto"));
		lote.setCod_estado(resultSet.getInt("cod_estado"));
		lote.setDiasAprob(resultSet.getInt("dias_aprob"));
		lote.setCodigo_tipo_lote(resultSet.getInt("cod_tipo_lote"));
		lote.setFecha_elaboracion(resultSet.getDate("fecha_elaboracion"));
		lote.setCant_unidades(resultSet.getInt("cantidad_unidades"));
		lote.setFormato(resultSet.getString("formato"));
		
		return lote;
	}
	public static Lote getLoteVORechazado(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_LOTE_RECHAZADO, new Object[]{entity});
			resultSet.next();
		}
		
		Lote lote = findLote(resultSet.getString("cod_lote"));
		lote.setCod_estado(resultSet.getInt("cod_estado"));
		lote.setFecha(resultSet.getDate("fecha"));
		return lote;
	}
	
	public static Lote findLote (Object cod_lote) throws SQLException{
		ResultSet resultSetX = null;
		if(cod_lote instanceof ResultSet)
			resultSetX = (ResultSet) cod_lote;
		else{
			resultSetX = statementManage.executeSelectSQL(FIND_LOTE_PRODUCC, new Object[]{cod_lote});
			resultSetX.next();
		}
		Lote lote = new Lote();
		lote.setCod(resultSetX.getString("id_lote"));
		lote.setFecha_revisado(resultSetX.getDate("fecha_revision")); 
		lote.setFecha_analisis(resultSetX.getDate("fecha_analisis"));
		lote.setFecha_envase(resultSetX.getDate("fecha_envase"));
		lote.setFecha_etiquetado(resultSetX.getDate("fecha_etiquetado"));  
		lote.setEstado_analisis(resultSetX.getBoolean("analisis"));
		lote.setEstado_envase(resultSetX.getBoolean("envase"));
		lote.setEstado_etiquetado(resultSetX.getBoolean("etiquetado"));
		lote.setEstado_revision(resultSetX.getBoolean("revision"));
		lote.setFecha_aprobacion_final(resultSetX.getDate("fecha_af"));
		lote.setEstado_aprobacion_final(resultSetX.getBoolean("aprobacion_final"));
		return lote;
	}
	public static Lote findLoteN (Object cod_lote) throws SQLException{
		ResultSet resultSetX = null;
		if(cod_lote instanceof ResultSet)
			resultSetX = (ResultSet) cod_lote;
		else{
			resultSetX = statementManage.executeSelectSQL(FIND_LOTE, new Object[]{cod_lote});
			resultSetX.next();
		}
		Lote lote = findLote(resultSetX.getString("cod_lote"));
		lote.setProducto(resultSetX.getString("cod_producto"));
		lote.setCod_estado(resultSetX.getInt("cod_estado"));
		lote.setDiasAprob(resultSetX.getInt("dias_aprob"));
		lote.setCodigo_tipo_lote(resultSetX.getInt("cod_tipo_lote"));
		lote.setFecha_elaboracion(resultSetX.getDate("fecha_elaboracion"));
		lote.setCant_unidades(resultSetX.getInt("cantidad_unidades"));
		lote.setFormato(resultSetX.getString("formato"));
		return lote;
	}

	public static ArrayList<Lote> listFiltros(int estado, String formato, int tipo, String producto, Date elaboracion) throws SQLException {
		String sql = "select lote.*, producto.* from producto inner join lote on producto.cod_producto = lote.cod_producto";
		boolean e = false;
		if(estado != 0 || formato != null || tipo != 0 || producto != null || elaboracion != null){
			sql = sql + " where ";
		}
		if(estado != 0){
			sql = sql + "lote.cod_estado = " + estado;
			e = true;
		}
		if(formato != null){
			if(e == true){
				sql = sql + " and lote.formato = '" + formato + "'";	
			}
			else {
				sql = sql + "lote.formato = '" + formato + "'";
				e = true;
			} 
		}
		if(tipo != 0){
			if(e == true){
				sql = sql + " and lote.cod_tipo_lote = " + tipo;
			}
			else {
				sql = sql + "lote.cod_tipo_lote = " + tipo;
				e = true;
			}
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
		if(elaboracion != null){
			if(e == true){
				sql = sql + " and lote.fecha_elaboracion = '" + elaboracion + "'";
			}
			else{
				sql = sql + "lote.fecha_elaboracion = '" + elaboracion + "'";
			}
		}
		
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	
	public static ArrayList<Lote> listFiltrosRev(String formato, int tipo, String producto, Date elaboracion) throws SQLException {
		String sql = "select lote.*, producto.* from producto inner join lote on producto.cod_producto = lote.cod_producto";
		sql = sql + " where (lote.cod_estado = 1 or lote.cod_estado = 2) ";
		if(formato != null){
			sql = sql + " and lote.formato = '" + formato + "'";	
		}
		if(tipo != 0){

			sql = sql + " and lote.cod_tipo_lote = " + tipo;
		}
		if(producto != null){
			sql = sql + " and producto.nombre = '" + producto + "'";
		}
		if(elaboracion != null){
			sql = sql + " and lote.fecha_elaboracion = '" + elaboracion + "'";
		}
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	public static ArrayList<Lote> listReporteP(int estado, String formato, int tipo, Date dateInicio, Date dateFin) throws SQLException {
		String sql = "select lote.* from lote";
		boolean e = false;
		if(estado != 0 || formato != null || tipo != 0 || dateInicio == null || dateFin == null){
			sql = sql + " where ";
		}
		if(estado != 0){
			sql = sql + "lote.cod_estado = " + estado;
			e = true;
		}
		if(formato != null){
			if(e == true){
				sql = sql + " and lote.formato = '" + formato + "'";	
			}
			else {
				sql = sql + "lote.formato = '" + formato + "'";
				e = true;
			} 
		}
		if(tipo != 0){
			if(e == true){
				sql = sql + " and lote.cod_tipo_lote = " + tipo;
			}
			else {
				sql = sql + "lote.cod_tipo_lote = " + tipo;
				e = true;
			}
		}
		if(dateInicio != null){
			if(e == true){
				sql = sql + " and lote.fecha_elaboracion >= '" + dateInicio + "'";
			}
			else{
				sql = sql + "lote.fecha_elaboracion >= '" + dateInicio + "'";
			}
		}
		if(dateFin != null){
			if(e == true){
				sql = sql + " and lote.fecha_elaboracion <= '" + dateFin + "'";
			}
			else{
				sql = sql + "lote.fecha_elaboracion <= '" + dateFin + "'";
			}
		}
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	public static ArrayList<Lote> listReporteA(int estado, String formato, int tipo, Date dateInicio, Date dateFin) throws SQLException {
		String sql = "select lote.* from lote";
		boolean e = false;
		if(estado != 0 || formato != null || tipo != 0 || dateInicio == null || dateFin == null){
			sql = sql + " where ";
		}
		if(estado != 0){
			sql = sql + "lote.cod_estado >= " + estado;
			e = true;
		}
		if(formato != null){
			if(e == true){
				sql = sql + " and lote.formato = '" + formato + "'";	
			}
			else {
				sql = sql + "lote.formato = '" + formato + "'";
				e = true;
			} 
		}
		if(tipo != 0){
			if(e == true){
				sql = sql + " and lote.cod_tipo_lote = " + tipo;
			}
			else {
				sql = sql + "lote.cod_tipo_lote = " + tipo;
				e = true;
			}
		}
		if(dateInicio != null){
			if(e == true){
				sql = sql + " and lote.fecha_elaboracion >= '" + dateInicio + "'";
			}
			else{
				sql = sql + "lote.fecha_elaboracion >= '" + dateInicio + "'";
			}
		}
		if(dateFin != null){
			if(e == true){
				sql = sql + " and lote.fecha_elaboracion <= '" + dateFin + "'";
			}
			else{
				sql = sql + "lote.fecha_elaboracion <= '" + dateFin + "'";
			}
		}
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	public static ArrayList<Lote> listPlan(String producto) throws SQLException {
		String sql = "select lote.* from lote";
		boolean e = false;
		if(producto != null){
			sql = sql + " where ";
		}
		if(producto != null){
			if(e == true){
				sql = sql + " and lote.cod_producto = '" + producto + "'";
			}
			else {
				sql = sql + "lote.cod_producto =  '" + producto + "'";
				e = true;
			}
		}
		ArrayList<Lote> loteList = new ArrayList<Lote>();
		ResultSet resultSet = statementManage.executeSelectSQL(sql, null);
		while (resultSet.next()) {
			loteList.add(getLoteVO(resultSet));
		}
		return loteList;
	}
	
}
