package aica.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatementManage {
	
private static final StatementManage statementManage = new StatementManage();
	
	public static  StatementManage getStatementManage(){
		
		return statementManage;
	}
	
	public int executeUpdateSQL (String sql,Object[] arg) throws SQLException{
		
		PreparedStatement statement = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sql);
			for (int i = 0; i < arg.length; i++){
				System.out.println(arg[i]);
				statement.setObject(i+1, arg[i]);
			}
		int rowcount = statement.executeUpdate();
		statement.close();
		return rowcount;
	}

	public ResultSet executeSelectSQL (String sql,Object[] arg) throws SQLException{
		
		PreparedStatement statement = ConnectionBD.getConnectionBD().getConnection().prepareStatement(sql);
		if(arg != null){
			for (int i = 0; i < arg.length; i++){
				System.out.println(arg[i]);
				statement.setObject(i+1, arg[i]);
			}
		}
		ResultSet resultSet = statement.executeQuery();
		return resultSet;
	}
}
