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
	public static final String LOGIN = "TPCOO";

	public final String PASSWORD = "";

	public static final String CONNECTION = "jdbc:hsqldb:file:bdd/ReservationSallesDeMusique;shutdown=true";

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

	public static final String ANNULATION_RESERVATION = "Annulation s'une réservation";

	/*
	 * Boutons
	 */
	public static final String VALIDER = "Valider";

	public static final String ANNULER = "Annuler";
}
