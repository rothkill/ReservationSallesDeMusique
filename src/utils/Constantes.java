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

	public static final String MONNAIE_UTILISEE = " �";

	/**
	 * Titres des cas d'utilisation
	 */
	public static final String VISUALISER_RESERVATION = "Visualiser les r�servations";

	public static final String VISUALISER_RESERVATION_ET_RESERVER = "Visualiser les r�servations / Reserver";

	public static final String EFFECTUER_RESERVATION_AUTOMATIQUE = "Effectuer une r�servation automatique d'une salle";

	public static final String EFFECTUER_RESERVATION_MANUELLE = "Effectuer une r�servation � la main d'une salle";

	public static final String EDITER_INFO_CLIENT = "Editer les informations d'un client";

	public static final String EFECTUER_RESERVATION_MEME_JOUR_SEMAINE = "Effectuer une r�servation pour une p�riode";

	public static final String ANNULATION_RESERVATION = "Annulation d'une r�servation";

	/**
	 * Boutons
	 */
	public static final String VALIDER = "Valider";

	public static final String ANNULER = "Annuler";

	public static final String RETOUR_MENU = "Retour au menu";

	public static final String ANNULER_RESERVATION = "Annuler la r�servation";

	public static final String ANNULER_LES_RESERVATIONS = "Annuler les r�servations";

	public static final String CONFIRMER_RESERVATION = "Confirmer une reservation";

	public static final String ACHETER_FORFAIT = "Acheter un forfait";

	public static final String NOUVEL_UTILISATEUR = "Nouvel utilisateur";

	public static final String CONFIRMER_RESERVATION_BUTTON = "Confirmer";

	public static final String CONFIRMER_RESERVATION_FORFAIT_BUTTON = "Utiliser Forfait";

	public static final String CONFIRMER_RESERVATION_FIDELITE_BUTTON = "Utiliser Points Fidelite";

	public static final String RESERVER_MANUELLEMENT = "Reservation manuelle";

	public static final String RESERVER_AUTOMATIQUEMENT = "Reservation automatique";

	/**
	 * Labels
	 */
	public static final String ANNULER_TOUTES_RESERVATION_LABEL = "Annuler toutes le r�servations non confirm�es pour lesquelles la date de d�but est d�pass�e";

	public static final String NOM_UTILISATEUR_LABEL = "Nom Utilisateur : ";

	public static final String UTILISATEUR_LABEL = "Utilisateur : ";

	public static final String DATE_LABEL = "Date : ";

	public static final String SLASH_LABEL = " / ";

	public static final String JJ_LABEL = "JJ";

	public static final String MM_LABEL = "MM";

	public static final String AAAA_LABEL = "AAAA";

	public static final String HH_LABEL = "HH";

	public static final String DUREE_LABEL = "dur�e";

	public static final String TRANCHE_HORAIRE_LABEL = "tranche horaire : ";

	public static final String SEMAINES_LABEL = "nb semaines";

	public static final String TELEPHONE_LABEL = "Telephone : ";

	public static final String TYPE_FORFAIT = "Type de forfait : ";

	public static final String MULTIPLE_RESERVATION_LABEL = "Reserver sur une p�riode : ";

	public static final String CONFIRMER_ANNULATION_RESERVATION_LABEL = "Une r�servation existe d�j�, confirmer la suppression ?";

	/**
	 * Labels d'information de la popup d'alerte
	 */
	public static final String TITRE_POPUP = "Alerte";

	public static final String INFO_LABEL = "Information";

	public static final String INFO_RESERVATION_ANNULEE = "<html><font color='red'>Reservation annul�e</font></html>";

	public static final String INFO_RESERVATION_NON_ANNULEE = "<html><font color='green'>Reservation non annul�e</font></html>";

	public static final String INFO_ERREUR = "<html><font color='red'>?</font></html>";

	public static final String INFO_REUSSITE = "<html><font color='green'>?</font></html>";

	public static final String AUCUNE_RESERVATION_INFO = "Aucune reservation";

	/**
	 * Exceptions
	 */
	public static final String DATE_INCORRECTE_EXCEPTION = "La date entr�e n'est pas valide";

	public static final String CATEGORIE_NON_SELECTIONNEE_EXCEPTION = "Aucune cat�gorie s�lectionn�e";

	public static final String RESERVATION_NON_SELECTIONNEE_EXCEPTION = "Aucune reservation s�lectionn�e";

	public static final String UTILISATEUR_NON_SELECTIONNEE_EXCEPTION = "Aucun client s�lectionn�e";

	public static final String SALLE_NON_SELECTIONNEE_EXCEPTION = "Aucune salle s�lectionn�e";

	public static final String FORFAIT_NON_SELECTIONNEE_EXCEPTION = "Aucun forfait s�lectionn�e";

	public static final String LUNDI_EXCEPTION = "La date correspond � un lundi";

	public static final String AUCUNE_RESERVATION_NON_CONFIRMEE_EXCEPTION = "Toutes les reservations sont confirm�es";

	public static final String AUCUN_UTILISATEUR_EXCEPTION = "Aucun client n'est renseign�";

	public static final String AUCUNE_RESERVATION_UTILISATEUR_EXCEPTION = "Aucune reservation n'a �t� effeectu�e par cet utilisateur";

	public static final String AUCUN_FORFAIT_EXISTANT = "Aucun forfait existant";

	public static final String AUCUNE_SALLE_EXISTANTE = "Aucune salle existante";

	public static final String SALLE_RESEVEE_EXCEPTION = "Salle d�j� r�serv�e";

	public static final String UTILISATEUR_CREE = "Utilisateur cr��";

	public static final String UTILISATEUR_NON_CREE = "Utilisateur non cr��";

	public static final String TT_RES_AN = "Toutes les r�servations d�but�e et non confirm�es annul�es";

	public static final String RES_AN = "R�servation annul�e";

	/**
	 * Constantes liees au fonctionnement de l'application
	 */

	public static final int CORRESPONDANCE_HEURE_POINTS_FIDELITE = 5;

	public static final int CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE = 75;

	/**
	 * Labels de DatePicker
	 */

	public static final String MOIS_PICKER = "Mois";

	public static final String ANNEE_PICKER = "Ann�e";

	public static final String JOUR_PICKER = "Aujourd'hui";

}
