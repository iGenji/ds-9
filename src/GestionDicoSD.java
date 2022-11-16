import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

public class GestionDicoSD {
	
	private static DicoSD dicoSD;
	
	private static Scanner scanner = new Scanner(System.in);
	private static Path chemin = FileSystems.getDefault().getPath("dicoSD");

	
	public static void main(String[] args) {
		
		System.out.println("****************");
		System.out.println("Programme DicoSD");
		System.out.println("****************");
		
		chargerDico();
		
		int choix = 0;
		do{
			System.out.println();
			System.out.println("1 -> Ajouter un lien");
			System.out.println("2 -> Supprimer un lien");
			System.out.println("3 -> Donner les liens");
			System.out.println();
			System.out.print("Entrez votre choix : ");

			choix = scanner.nextInt();
			switch (choix) {


			case 1:
				ajouterLien();
				break;	
			case 2:
				supprimerLien();
				break;	
			case 3:
				donnerLiens();
				break;	
			
			default:
				break;
			}
		} while ((choix > 0) && (choix < 4));
		
		sauvegarderDico();
	}

	private static void chargerDico() {
		try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin))){	
			dicoSD = (DicoSD)fichier.readObject();
			LocalDate date = (LocalDate)fichier.readObject();
			System.out.println("Recuperation du dicoSD sur disque : DicoSD");
			System.out.println("Derniere sauvegarde : " + date);
		}
		catch ( java.io.EOFException ex) { 
			// FinFichier
			System.out.println( "erreur : \t" + ex );		
		}
		catch ( java.io.IOException ex) { 
			// DicoSD n'a pas pu etre ouvert
			System.out.println("Creation du dicoSD sur disque : DicoSD");
			dicoSD = new DicoSD();
		}
		catch ( Exception ex) { // autre type d'erreur
			System.out.println( "erreur : \t" + ex );
			ex.printStackTrace();
		}
		
	}

	private static void sauvegarderDico() {
		try (ObjectOutputStream fichier = new ObjectOutputStream(Files.newOutputStream(chemin))){			
			fichier.writeObject(dicoSD);
			fichier.writeObject(LocalDate.now());
			System.out.println("Sauvegarde du dicoSD sur disque : DicoSD");
		}
		catch ( java.io.IOException ex) { 
			System.out.println( "probleme I/O: \t" + ex );
			ex.printStackTrace();
		}
		catch ( Exception ex) { // autre type d'erreur
			System.out.println( "erreur : \t" + ex );
			ex.printStackTrace();
		}
		
	}
	
	private static void donnerLiens() {
		System.out.print("Entrez la structure de donnees : ");
		String sd = scanner.next();
		String lesLiens = dicoSD.lesLiens(sd);
		if(lesLiens.equals("[]"))
			System.out.println("Aucun lien pour cette structure de donnees.");
		else{
			System.out.println("Voici les liens : ");
			System.out.println(lesLiens);	
		}
	}

	private static void supprimerLien() {
		System.out.print("Entrez la structure de donnees : ");
		String sd = scanner.next();
		System.out.print("Entrez le lien : ");
		String lien = scanner.next();
		if(dicoSD.supprimer(sd, lien))
			System.out.println("Le lien a ete supprime avec succes.");
		else
			System.out.println("Ce lien n'existe pas");
		
	}

	private static void ajouterLien() {
		System.out.print("Entrez la structure de donnees : ");
		String sd = scanner.next();
		System.out.print("Entrez le lien : ");
		String lien = scanner.next();
		if(dicoSD.ajouter(sd, lien))
			System.out.println("Le lien a ete ajoute avec succes.");
		else
			System.out.println("Ce lien existe deja.");
	}

}
