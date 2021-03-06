package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe representant un client.
 * 
 * @author grimonprez
 * 
 */
public class Utilisateur {
	private Integer idUtilisateur;
	private String nomUtilisateur;
	private String telephone;
	private int pointFidelite;
	private List<Forfait> listForfaitsUtilisateur;
	private List<Reservation> listReservationsUtilisateur;

	public Utilisateur(Integer idUtilisateur, String nomUtilisateur,
			String telephone, int pointFidelite) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.telephone = telephone;
		this.pointFidelite = pointFidelite;
		this.listForfaitsUtilisateur = new ArrayList<Forfait>();
		this.setListReservationsUtilisateur(new ArrayList<Reservation>());
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getPointFidelite() {
		return pointFidelite;
	}

	public void setPointFidelite(int pointFidelite) {
		this.pointFidelite = pointFidelite;
	}

	public List<Forfait> getListForfaitsUtilisateur() {
		return listForfaitsUtilisateur;
	}

	public void setListForfaitsUtilisateur(List<Forfait> listForfaitsUtilisateur) {
		this.listForfaitsUtilisateur = listForfaitsUtilisateur;
	}


	public List<Reservation> getListReservationsUtilisateur() {
		return listReservationsUtilisateur;
	}

	public void setListReservationsUtilisateur(
			List<Reservation> listReservationsUtilisateur) {
		this.listReservationsUtilisateur = listReservationsUtilisateur;
	}

	@Override
	public String toString() {
		return nomUtilisateur;
	}
}
