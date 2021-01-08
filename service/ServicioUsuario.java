package aica.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import aica.model.Usuario;


public class ServicioUsuario {
	
	private  static  String ALL_usuario = "select * from usuario";
	private  static  String FIND_usuario = "select * from usuario where nombre = ?";
	private  static  String UPDATE_usuario = "update usuario set nombre = ?, contrasena = ?, rol = ? where nombre = ?";
    	
	private static StatementManage statementManage = StatementManage.getStatementManage();
	
	public static Collection getAllUsuario() throws SQLException{
		ArrayList<Usuario> usuarioList = new ArrayList<Usuario>();
		ResultSet resultSet = statementManage.executeSelectSQL(ALL_usuario, null);
		while (resultSet.next()) {
			usuarioList.add(getUsuarioVO(resultSet));
		}
		return usuarioList;
	}
	public static Usuario getUsuarioVO(Object entity) throws SQLException{
		ResultSet resultSet = null;
		if(entity instanceof ResultSet)
			resultSet = (ResultSet) entity;
		else{
			resultSet = statementManage.executeSelectSQL(FIND_usuario, new Object[]{entity});
			resultSet.next();
		}
		Usuario user = new Usuario();
		user.setNombre(resultSet.getString("nombre"));
		user.setContrasena(resultSet.getString("contrasena"));
		user.setRol(resultSet.getInt("rol"));
				
		return user;
	}
	public static void updateUsuario(Usuario user,String nombre) throws SQLException {
		Object[] arg = {user.getNombre(),user.getContrasena(), user.getRol(),nombre};
		statementManage.executeUpdateSQL(UPDATE_usuario, arg);
	}

	public static int getLoginUsuario(String usuario, char [] passw) throws ClassNotFoundException{
		int rol = 0;
		try {
			String sqlSentenc = "SELECT \"public\".\"rol\".\"rol\" " +
			"FROM  \"public\".\"usuario\",\"public\".\"rol\"  " +
			"WHERE  \"public\".\"usuario\".\"rol\" = \"public\".\"rol\".\"rol\" and \"public\".\"usuario\".\"nombre\" = ? and \"public\".\"usuario\".\"contrasena\" = ? "; 
			PreparedStatement prepararCons = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sqlSentenc);
			prepararCons.setString(1, usuario); 
			String passs = new String(passw);
			String pass = Encriptar.getMd5(passs);
			prepararCons.setString(2, pass);
			prepararCons.execute();
			ResultSet resultado = prepararCons.getResultSet();
			
			while (resultado.next()) {
				rol = resultado.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return rol;
	}
	
	public static LinkedList<Usuario> getUsuarios() throws SQLException, ClassNotFoundException{
		LinkedList<Usuario> listUsuarios = new LinkedList<Usuario>();
		Statement consulta = ConnectionBD.getConnectionBD().getConnection().createStatement (ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sqlSentenc = "SELECT \"public\".\"usuario\".\"nombre\",\"public\".\"rol\".\"rol\" " +
		"FROM  \"public\".\"usuario\",\"public\".\"rol\"  " +
		"WHERE  \"public\".\"usuario\".\"rol\" = \"public\".\"rol\".\"rol\" ";
        ResultSet resultado = consulta.executeQuery(sqlSentenc);
		while (resultado.next()) {
			Usuario user = new Usuario();
			user.setNombre(resultado.getString(1));
			user.setContrasena(resultado.getString(2));
			user.setRol(resultado.getInt(3));
			listUsuarios.add(user);
		}
		return listUsuarios;
	}
	
	public static void insertarUsuario(String nombre, char [] pas, int rol) throws SQLException, ClassNotFoundException{
		String pass = new String(pas);
		String sqlSentenc = "INSERT INTO \"public\".\"usuario\" (nombre,contrasena,rol) VALUES  (?,?,?)"; /*Los símbolos ? indican los parámetros que se van a pasar */
		PreparedStatement prepararCons = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, nombre); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
		prepararCons.setString(2, Encriptar.getMd5(pass));
		prepararCons.setInt(3, rol);
		prepararCons.execute();
	}
	
	public static void modificarUsuario(String nuevoUserName, char [] passw, int rol, String userName) throws SQLException, ClassNotFoundException {
		
		String pass = new String(passw);
		String sqlSentenc = "UPDATE \"public\".\"usuario\"  SET nombre= ?, password= ?, rol =? " +
				           " WHERE  nombre = ?; " ; 
		PreparedStatement prepararCons = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, nuevoUserName); /* estamos dándole valor al primer parametro que se pasa, es decir al primer ? que aparezca. */
		prepararCons.setString(2, Encriptar.getMd5(pass));
		prepararCons.setInt(3, rol);
		prepararCons.setString(4, userName);
		prepararCons.execute();	
	}
	
	public static void  EliminarUsuario(String userName) throws SQLException, ClassNotFoundException { 
		String sqlSentenc = "DELETE FROM \"public\".\"usuario\"  WHERE  nombre = ?; " ; 
		PreparedStatement prepararCons = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sqlSentenc);
		prepararCons.setString(1, userName); 
		prepararCons.execute();
		
	}
}
