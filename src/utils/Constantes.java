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

	public static final String CONNECTION = "jdbc:hsqldb:file:bdd/ReservationSallesDeMusique;shutdown=true";

	public static final String DRIVER = "org.hsqldb.jdbcDriver";

	/**
	 * Constantes de l'IHM
	 */
	public static final String APPLICATION_TITLE = "Réservation de salle de musique";

	/*
	 * Titres des cas d'utilisation
	 */
	public static final String VISUALISER_RESERVATION = "Visualiser les réservations";

	public static final String EFFECTUER_RESERVATION_AUTOMATIQUE = "Effectuer une réservation automatique d'une salle";

	public static final String EFFECTUER_RESERVATION_MANUELLE = "Effectuer une réservation à la main d'une salle";

	public static final String EDITER_INFO_CLIENT = "Editer les informations d'un client";

	public static final String EFECTUER_RESERVATION_MEME_JOUR_SEMAINE = "Effectuer une réservation pour une période";

	public static final String ANNULATION_RESERVATION = "Annulation d'une réservation";

	/*
	 * Boutons
	 */
	public static final String VALIDER = "Valider";

	public static final String ANNULER = "Annuler";

	public static final String ANNULER_RESERVATION = "Annuler la réservation";

	public static final String ANNULER_LES_RESERVATIONS = "Annuler les réservations";

	/*
	 * Labels
	 */
	public static final String ANNULER_TOUTES_RESERVATION_LABEL = "Annuler toutes le réservations non confirmées pour lesquelles la date de début est dépassée";

	public static final String NOM_UTILISATEUR_LABEL = "Nom Utilisateur : ";

	public static final String PRENOM_UTILISATEUR_LABEL = "Prenom Utilisateur : ";

	public static final String DATE_LABEL = "Date : ";

	/*
	 * Labels d'information
	 */
	public static final String INFO_LABEL = "Information";

	public static final String INFO_RESERVATION_ANNULEE = "<html><font color='green'>Reservation annulée</font></html>";

	public static final String INFO_RESERVATION_NON_ANNULEE = "<html><font color='green'>Reservation non annulée</font></html>";

}
