import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class VerificationTriFichierAuto {
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");
		String nom = scanner.next();
		Path chemin = FileSystems.getDefault().getPath(nom);
		boolean estTrie = true;
		String constructeurPrecedent = "aaaa";

		try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin))) {
			boolean finFichier = false;
			do {
				try {
					Voiture voiture = (Voiture) fichier.readObject();
					if (voiture.getConstructeur().compareTo(constructeurPrecedent) < 0) {
						estTrie = false;
						finFichier = true;
					}
					constructeurPrecedent = voiture.getConstructeur();
				} catch (java.io.EOFException e) {
					finFichier = true;
				}
			} while (!finFichier);
		} catch (java.io.IOException ex) {
			System.out.println("probleme I/O: \t" + ex);
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("erreur : \t" + ex);
			ex.printStackTrace();
		}
		if (estTrie)
			System.out.println("Le fichier est trie.");
		else {
			System.out.println("Le fichier n'est pas trie.");
		}
	}
}
