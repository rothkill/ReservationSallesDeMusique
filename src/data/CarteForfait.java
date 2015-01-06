package data;

import java.util.Date;

/**
 * Classe representant l'association entre Utilisateur et Forfait.
 * 
 * @author JeanBaptiste
 *
 */
public class CarteForfait {

	private Date dateAchat;
	private Date dateFinValidite;
	private int dureeRestante;
	private Forfait forfait;
	private Utilisateur utilisateur;

	public CarteForfait(int idCarteForfait, Date dateAchat,
			Date dateFinValidite, int dureeRestante, Forfait forfait,
			Utilisateur utilisateur) {
		super();
		this.dateAchat = dateAchat;
		this.dateFinValidite = dateFinValidite;
		this.dureeRestante = dureeRestante;
		this.forfait = forfait;
		this.utilisateur = utilisateur;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Date getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(Date dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public int getDureeRestante() {
		return dureeRestante;
	}

	public void setDureeRestante(int dureeRestante) {
		this.dureeRestante = dureeRestante;
	}

	public Forfait getForfait() {
		return forfait;
	}

	public void setForfait(Forfait forfait) {
		this.forfait = forfait;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
