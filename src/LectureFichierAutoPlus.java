import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class LectureFichierAutoPlus {
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");
		String nom = scanner.next();
		Path chemin = FileSystems.getDefault().getPath(nom);
		int nbVoitures = 0;
		int nbVoituresMoins = 0;

		try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin))) {
			boolean finFichier = false;
			do {
				try {
					Voiture voiture = (Voiture) fichier.readObject();
					nbVoitures++;
					if (voiture.getPrix() < 10000) {
						nbVoituresMoins++;
					}
					System.out.println(voiture);
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
		System.out.println("nombre de voitures : " + nbVoitures);
		System.out.println("nombre de voitres moins de 10000 euros : " + nbVoituresMoins);
	}
}
