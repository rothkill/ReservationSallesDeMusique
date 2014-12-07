package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {

	private static ConnectionDB singleton;

	Connection con;

	public ConnectionDB() {
	}

	public static ConnectionDB getInstance() {
		if (singleton == null) {
			singleton = new ConnectionDB();
		}
		return singleton;
	}

	public void connection() {
		try {
			Class.forName(Constantes.DRIVER);
			con = DriverManager.getConnection(
					Constantes.CONNECTION, Constantes.LOGIN, Constantes.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if (con == null) {
			connection();
		}
		return con;
	}

	public void deconnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
