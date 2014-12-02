1) télécharger HSQLDB (http://sourceforge.net/projects/hsqldb/files/latest/download?source=files)
2) décompresser
3) ajouter hsqldb.jar au buildpath du projet eclipse (se trouve dans hsqldb-2.3.2/hsqldb/lib)
4) lancer le gestionnaire de base de donnée (ligne de commande java -classpath /[chemin du fichier ou se trouve le jar]/hsqldb.jar org.hsqldb.util.DatabaseManager
5) choisir la BDD (ou la crée si besoin: exemple du Projet COO -> nom = "ReservationSallesDeProjet" , utilisateur = "TPCOO", motdepasse = "")
6) file -> open Script... -> Script.sql
7) cliquer sur execute
5) exemple de connection java : 
	Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:bdd/[nom de ma base];shutdom=true","[user]","[password]");
