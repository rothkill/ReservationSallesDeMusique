package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ConnectionDB;


public class ReservationDAO {
	
	private Connection con = ConnectionDB.getInstance().getConnection();
	
	private static ReservationDAO SINGLETON;
	
	public ReservationDAO(){
	}
	
	public static ReservationDAO getInstance(){
		if(SINGLETON == null){
			SINGLETON = new ReservationDAO();
		}
		return SINGLETON;
	}
	
	public boolean creer(int idBureau, String description){
		try {
			PreparedStatement st = con.prepareStatement("insert into bureau values(?,?)");
			st.setInt(1, idBureau);
			st.setString(2, description);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
	
	public Bureau rechercher(int idBureau){
		Bureau bureau = null;
		try {
			PreparedStatement st = con.prepareStatement("select idbureau,description from bureau where idbureau = ?");
			st.setInt(1, idBureau);
			ResultSet rs = st.executeQuery();
			if(rs.next() != false){
				bureau = new Bureau(idBureau,rs.getString(2));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return bureau;
		
	}
	
	
	

}
