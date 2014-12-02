package data;

import java.util.Date;

/**
 * Classe representant un forfait de reservation de salles de musique.
 * 
 * @author grimonprez
 * 
 */
public class Forfait {
	private Integer idForfait;
	private Date dateAchat;
	private Date dateFinValidite;
	private int nbHeures;

	public Forfait(Integer idForfait, Date dateAchat, Date dateFinValidite,
			int nbHeures) {
		super();
		this.idForfait = idForfait;
		this.dateAchat = dateAchat;
		this.dateFinValidite = dateFinValidite;
		this.nbHeures = nbHeures;
	}

	public Integer getIdForfait() {
		return idForfait;
	}

	public void setIdForfait(Integer idForfait) {
		this.idForfait = idForfait;
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

	public int getNbHeures() {
		return nbHeures;
	}

	public void setNbHeures(int nbHeures) {
		this.nbHeures = nbHeures;
	}

}
