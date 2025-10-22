package vn.buoi2.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String serverName = "DESKTOP-8VCMSM0";
    private final String instanceName = "SQLSERVER";
    private final String dbName = "WebAppz";
    private final String userID = "vb";
    private final String password = "12345678";
	
//	public Connection getConnection() throws Exception {
//		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";encrypt=true; trustServerCertificate=true;databaseName=" + dbName;
//		if (instance == null || instance.trim().isEmpty())
//			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";encrypt=true; trustServerCertificate=true;databaseName=" + dbName;
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		return DriverManager.getConnection(url, userID, password);
//	}
    
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName +
                ";instanceName=" + instanceName +
                ";databaseName=" + dbName +
                ";encrypt=true;trustServerCertificate=true;";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
	
	public static void main(String[] args) {
		try {
            Connection conn = new DBConnect().getConnection();
            if (conn != null) {
                System.out.println("KẾT NỐI TỚI INSTANCE THÀNH CÔNG!");
                conn.close();
            } else {
                System.out.println("Kết nối thất bại!");
            }
        } catch (Exception e) {
            System.err.println("Lỗi kết nối! Chi tiết: ");
            e.printStackTrace();
        }
	}
}
