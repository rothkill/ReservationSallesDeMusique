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
	private String prenomUtilisateur;
	private int pointFidelite;
	private List<Forfait> listForfaitsUtilisateur;

	public Utilisateur(Integer idUtilisateur, String nomUtilisateur,
			String prenomUtilisateur, int pointFidelite) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.pointFidelite = pointFidelite;
		this.listForfaitsUtilisateur = new ArrayList<Forfait>();
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

	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}

	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
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

}
