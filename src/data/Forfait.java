package data;

import java.util.Date;

import dao.CategorieDAO;

/**
 * Classe representant un forfait de reservation de salles de musique.
 * 
 * @author grimonprez
 * 
 */
public class Forfait {
	private Integer idForfait;
	private Integer forfait;
	private Integer validite;
	private Categorie categorie;
	private Integer prix;
	
	public Forfait(Integer idForfait, Integer forfait, Integer validite,
			Categorie categorie, Integer prix) {
		super();
		this.idForfait = idForfait;
		this.forfait = forfait;
		this.validite = validite;
		this.categorie = categorie;
		this.prix = prix;
	}



	public Integer getIdForfait() {
		return idForfait;
	}



	public void setIdForfait(Integer idForfait) {
		this.idForfait = idForfait;
	}



	public Integer getForfait() {
		return forfait;
	}



	public void setForfait(Integer forfait) {
		this.forfait = forfait;
	}



	public Integer getValidite() {
		return validite;
	}



	public void setValidite(Integer validite) {
		this.validite = validite;
	}



	public Categorie getCategorie() {
		return categorie;
	}



	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}



	public Integer getPrix() {
		return prix;
	}



	public void setPrix(Integer prix) {
		this.prix = prix;
	}



	@Override
	public String toString() {
		return "forfait=" + forfait
				+ ", validite=" + validite + ", categorie=" + categorie.getNom()
				+ ", prix=" + prix ;
	}

	
}
