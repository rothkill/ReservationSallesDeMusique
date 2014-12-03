package main;

import java.util.Scanner;

import dao.CategorieDAO;
import dao.UtilisateurDAO;

public class MainTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
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
		System.out.println("Entrez les informations categorie :  ");
		System.out.println("idcategorie :  ");
		int idCategorie = sc.nextInt();
		System.out.println("nom :  ");
		String nom = sc.next();
		System.out.println("tarifUneHeure  ");
		int tarifUneHeure = sc.nextInt();
		System.out.println("tarifDeuxHeures :  ");
		int tarifDeuxHeures = sc.nextInt();
		System.out.println("nbPersonne :  ");
		int npPersonne = sc.nextInt();
		
		CategorieDAO.getInstance().creer(idCategorie, nom, tarifUneHeure, tarifDeuxHeures, npPersonne);
		
		System.out.println("Rechercher categorie : idCategorie ");
		int idCategorieRecherche = sc.nextInt();
		
		System.out.println(CategorieDAO.getInstance().rechercher(idCategorieRecherche));
		
		System.out.println("Supprimer Categorie : idCategorie ");
		int idCategorieSup = sc.nextInt();
		System.out.println(CategorieDAO.getInstance().supprimer(idCategorieSup));
		
		ConnectionDB.getInstance().deconnection();
		

	}

}
