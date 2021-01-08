package aica.service;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {

	private static java.sql.Connection connection;
	static ConnectionBD conect;

	private ConnectionBD(){
		super();
//		TODO Auto-generated constructor stub
	}

	public static ConnectionBD getConnectionBD(){
		if(conect == null){
			conect = new ConnectionBD();
		}
		return conect;
	}

	public void setConnection(String serveraddres, String database, String user,String pass) throws ClassNotFoundException, SQLException {

		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + serveraddres + ":5432/"+ database;
		connection = DriverManager.getConnection(url, user, pass);
	}

	public java.sql.Connection getConnection() {
		return connection;
	}

	public static void testConnect() {
		try {
			ConnectionBD.getConnectionBD().setConnection("localhost","aica","postgres","postgres");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
