import java.io.*;
import java.nio.file.*;

/**
 * Parcours d'un fichier binaire contenant des objets de la classe
 * Voiture.
 * 
 */
public class LectureFichierAuto {
	
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);
	
	
	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");
		String nom = scanner.next();
		Path chemin = FileSystems.getDefault().getPath(nom);
		
		try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin))){
			
			boolean finfichier = false;
			do { 
				try{
					Voiture voiture = (Voiture) fichier.readObject();
					System.out.println( voiture );
				}catch ( java.io.EOFException e) { // fin du fichier rencontree
					finfichier = true;
				}
			} while(!finfichier);
			System.out.println("fin du fichier");					
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
}
