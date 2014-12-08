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
	private Integer forfait;
	private Integer validite;
	private Integer categorie;
	private Integer prix;
	
	public Forfait(Integer idForfait, Integer forfait, Integer validite,
			Integer categorie, Integer prix) {
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

	public Integer getCategorie() {
		return categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}

	
}
