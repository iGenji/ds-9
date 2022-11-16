import java.io.*;
import java.nio.file.*;

public class DemandePrixAuto {

	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");
		String nom = scanner.next();
		String continuer = "O";
		do {
			System.out.print("Constructeur : ");
			String constructeur = scanner.next();
			System.out.print("Modele : ");
			String modele = scanner.next();
			System.out.print("Version : ");
			String version = scanner.next();
			Path chemin = FileSystems.getDefault().getPath(nom);
			int prix = -1;
			try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin))) {
				boolean finfichier = false;
				do {
					try {
						Voiture voiture = (Voiture) fichier.readObject();
						if (voiture.getConstructeur().equals(constructeur) && voiture.getModele().equals(modele)
								&& voiture.getVersion().equals(version)) {
							finfichier = true;
							prix = voiture.getPrix();
							System.out.println("Le prix de la voiture est de " + prix + " euros.");
						}
					} catch (java.io.EOFException e) {
						finfichier = true;
					}
				} while (!finfichier);
				if (prix == -1) {
					System.out.println("Voiture pas presente.");
				}
			} catch (java.io.IOException ex) {
				System.out.println("probleme I/O: \t" + ex);
				ex.printStackTrace();
			} catch (Exception ex) {
				System.out.println("erreur : \t" + ex);
				ex.printStackTrace();
			}
			System.out.println("Encore un prix ? (O/N)");
			continuer = scanner.next();
		} while (continuer.equals("O"));
	}
}
