package vn.buoi2.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private final String serverName = "DESKTOP-8VCMSM0\\SQLSERVER";
	private final String dbName = "WebAppz";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String password = "12345678";
	private final String userID = "vb";
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";encrypt=true; trustServerCertificate=true;databaseName=" + dbName;
		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";encrypt=true; trustServerCertificate=true;databaseName=" + dbName;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnect().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
