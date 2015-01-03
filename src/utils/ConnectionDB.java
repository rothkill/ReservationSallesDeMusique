package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de connection.
 *
 */
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
			con = DriverManager.getConnection(Constantes.CONNECTION,
					Constantes.LOGIN, Constantes.PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
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
			e.printStackTrace();
			System.exit(0);
		}
	}

}
