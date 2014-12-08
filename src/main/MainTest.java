package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import utils.ConnectionDB;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.CategorieDAO;
import dao.ReservationDAO;
import dao.UtilisateurDAO;

public class MainTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("Entrez les informations utilisateurs :  ");
//		System.out.println("idUtilisateur :  ");
//		int idUtilisateur = sc.nextInt();
//		System.out.println("nom :  ");
//		String nom = sc.next();
//		System.out.println("telephone  ");
//		String telephone = sc.next();
//		System.out.println("points Fidelite:  ");
//		int pointsFidelite = sc.nextInt();
//		
//		UtilisateurDAO.getInstance().creer(idUtilisateur, nom, telephone, pointsFidelite);
//		
//		System.out.println("Rechercher utilisateur : idUtilisateur ");
//		int idUtilisateurRecherche = sc.nextInt();
//		
//		System.out.println(UtilisateurDAO.getInstance().rechercher(idUtilisateurRecherche));
//		
//		System.out.println("Supprimer utilisateur : idUtilisateur ");
//		int idUtilisateurSup = sc.nextInt();
//		System.out.println(UtilisateurDAO.getInstance().supprimer(idUtilisateurSup));
//		ConnectionDB.getInstance().deconnection();
//		
//		System.out.println("Entrez les informations categorie :  ");
//		System.out.println("idcategorie :  ");
//		int idCategorie = sc.nextInt();
//		System.out.println("nom :  ");
//		String nom = sc.next();
//		System.out.println("tarifUneHeure  ");
//		int tarifUneHeure = sc.nextInt();
//		System.out.println("tarifDeuxHeures :  ");
//		int tarifDeuxHeures = sc.nextInt();
//		System.out.println("nbPersonne :  ");
//		int npPersonne = sc.nextInt();
//		
//		CategorieDAO.getInstance().creer(idCategorie, nom, tarifUneHeure, tarifDeuxHeures, npPersonne);
//		
//		System.out.println("Rechercher categorie : idCategorie ");
//		int idCategorieRecherche = sc.nextInt();
//		
//		System.out.println(CategorieDAO.getInstance().rechercher(idCategorieRecherche));
//		
//		System.out.println("Supprimer Categorie : idCategorie ");
//		int idCategorieSup = sc.nextInt();
//		System.out.println(CategorieDAO.getInstance().supprimer(idCategorieSup));
		
//		System.out.println("Entrez les informations categorie :  ");
//		System.out.println("idReservation :  ");
//		int idCategorie = sc.nextInt();
//		System.out.println("DateReservation :  ");
//		String resultDateReserv = sc.next("[0-9]{2}.[0-9]{2}.[0-9]{4}");
//		Date dateReservation = null;
//		try {
//		    dateReservation = new SimpleDateFormat("dd.MM.yyyy").parse(resultDateReserv);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		} catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("DateFinReservation  ");
//		String resultDateFinReserv = sc.next("[0-9]{2}.[0-9]{2}.[0-9]{4}");
//		Date dateFinReservation = null;
//		try {
//		    dateFinReservation = new SimpleDateFormat("dd.MM.yyyy").parse(resultDateFinReserv);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		} catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("idUtilisateur :  ");
//		int idUtilisateur = sc.nextInt();
//		System.out.println("idSalle :  ");
//		int idSalle = sc.nextInt();
//		
//		ReservationDAO.getInstance().creer(idCategorie,dateReservation, dateFinReservation,false, idUtilisateur, idSalle);
//		
//		System.out.println("Rechercher Reservation : Reservation ");
//		int idReservationRech = sc.nextInt();
		
//		System.out.println(ReservationDAO.getInstance().rechercher(idReservationRech));
//		
//		System.out.println("Supprimer Reservation : idReservation ");
//		int idReservationSup = sc.nextInt();
//		System.out.println(ReservationDAO.getInstance().supprimer(idReservationSup));
//		
		ConnectionDB.getInstance().deconnection();
		

	}

}
