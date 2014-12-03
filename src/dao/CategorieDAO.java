package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.ConnectionDB;
import data.Categorie;

public class CategorieDAO {
	private Connection con = ConnectionDB.getInstance().getConnection();
	
	private static CategorieDAO SINGLETON;
	
	public CategorieDAO(){
	}
	
	public static CategorieDAO getInstance(){
		if(SINGLETON == null){
			SINGLETON = new CategorieDAO();
		}
		return SINGLETON;
	}
	
	public Categorie creer(int idCategory, String nom, int tarifUneHeure,
			int tarifDeuxHeures, int nbPersonne){
		try {
			PreparedStatement st = con.prepareStatement("insert into categorie values(?,?,?,?,?)");
			st.setInt(1, idCategory);
			st.setString(2, nom);
			st.setInt(3, tarifUneHeure);
			st.setInt(4, tarifDeuxHeures);
			st.setInt(5,nbPersonne);
			st.executeUpdate();
			return new Categorie(idCategory,nom,tarifUneHeure,tarifDeuxHeures,nbPersonne);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;	
		}
			
	}
	
	public Categorie rechercher(int idCategory){
		Categorie category = null;
		try {
			PreparedStatement st = con.prepareStatement("select nom,tarifUneHeure,tarifDeuxHeures,nbPersonne from categorie where idcategorie = ?");
			st.setInt(1, idCategory);
			ResultSet rs = st.executeQuery();
			if(rs.next() != false){
				category = new Categorie(idCategory,rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return category;
		
	}
	
	public boolean supprimer(int idCategory){
		try {
			PreparedStatement st = con.prepareStatement("delete from categorie where idcategorie = ?");
			st.setInt(1, idCategory);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}