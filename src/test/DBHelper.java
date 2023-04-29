package test;
import java.sql.*;

public final class DBHelper { // FUNCIONA BIEN TODo
	// Definir la ruta de la base de datos 
	private static  String dbUrl = "jdbc:mysql://localhost:3306/tokyo2021_e3";
	 // Definir el nombre de usuario de la base de datos
	private static  String dbUser;
	public String getDbUser() {
		return dbUser;
	}
	// Definir la contraseña de la base de datos
	private static  String dbPassword;
	 // Definir controlador de carga
	private static  String jdbcName = "com.mysql.jdbc.Driver";
	
	private static  Connection Conn = null;
	
	private static  DBHelper Server = new DBHelper();
	
	 // Conéctate a la base de datos
	public static  Connection getConn() {
		//if (Conn == null) {
		//	System.out.println("Es NUEVO");
		//Connection conn = null;
		try {
			Class.forName(jdbcName);
		} catch (Exception e) {
			 System.out.println ("¡Error al cargar el controlador!");
		}
		try {
			Conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException ex) {
			 System.out.println ("¡Error al conectarse a la base de datos!");
			 return null;
		}
		//}
		//else System.out.println("Uso el que tenia");
		return Conn;
	}
 
	 // prueba
	public void setDbPassword(String dbPassword) {
		DBHelper.dbPassword = dbPassword;
	}
	 public void setDbUser(String dbUser) {
			DBHelper.dbUser = dbUser;
		}
	 public static DBHelper getServer() {
			return Server;
		}
}