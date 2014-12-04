package utils;

/**
 * Classe contenant les constantes de l'application.
 * 
 * @author grimonprez
 * 
 */
public class Constantes {

	/**
	 * Constantes de connexion a la BDD
	 */
	public static final String LOGIN = "SA";

	public final static String PASSWORD = "";

	public static final String CONNECTION = "jdbc:hsqldb:file:BDD/ReservationSallesDeMusique;shutdown=true";

	public static final String DRIVER = "org.hsqldb.jdbcDriver";

	/**
	 * Constantes de l'IHM
	 */
	public static final String APPLICATION_TITLE = "R�servation de salle de musique";

	/*
	 * Titres des cas d'utilisation
	 */
	public static final String VISUALISER_RESERVATION = "Visualiser les r�servations";

	public static final String EFFECTUER_RESERVATION_AUTOMATIQUE = "Effectuer une r�servation automatique d'une salle";

	public static final String EFFECTUER_RESERVATION_MANUELLE = "Effectuer une r�servation � la main d'une salle";

	public static final String EDITER_INFO_CLIENT = "Editer les informations d'un client";

	public static final String EFECTUER_RESERVATION_MEME_JOUR_SEMAINE = "Effectuer une r�servation pour une p�riode";

	public static final String ANNULATION_RESERVATION = "Annulation d'une r�servation";

	/*
	 * Boutons
	 */
	public static final String VALIDER = "Valider";

	public static final String ANNULER = "Annuler";

	public static final String ANNULER_RESERVATION = "Annuler la r�servation";

	public static final String ANNULER_LES_RESERVATIONS = "Annuler les r�servations";

	/*
	 * Labels
	 */
	public static final String ANNULER_TOUTES_RESERVATION_LABEL = "Annuler toutes le r�servations non confirm�es pour lesquelles la date de d�but est d�pass�e";

	public static final String NOM_UTILISATEUR_LABEL = "Nom Utilisateur : ";

	public static final String PRENOM_UTILISATEUR_LABEL = "Prenom Utilisateur : ";

	public static final String UTILISATEUR_LABEL = "Utilisateur : ";

	public static final String DATE_LABEL = "Date : ";

	public static final String SLASH_LABEL = " / ";

	/*
	 * Labels d'information
	 */
	public static final String INFO_LABEL = "Information";

	public static final String INFO_RESERVATION_ANNULEE = "<html><font color='red'>Reservation annul�e</font></html>";

	public static final String INFO_RESERVATION_NON_ANNULEE = "<html><font color='green'>Reservation non annul�e</font></html>";

	public static final String INFO_ERREUR = "<html><font color='red'>?</font></html>";

	public static final String INFO_REUSSITE = "<html><font color='green'>?</font></html>";

	/*
	 * Exceptions
	 */
	public static final String DATE_INCORRECTE_EXCEPTION = "La date entr�e n'est pas valide";

}
