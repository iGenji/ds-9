import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class CreationFichierAutoMoins10000 {
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");
		String nom = scanner.next();
		System.out.print("Entrez le nom du fichier destination ");
		String nom2 = scanner.next();
		Path chemin = FileSystems.getDefault().getPath(nom);
		Path cheminDest = FileSystems.getDefault().getPath(nom2);

		try (ObjectInputStream fichier = new ObjectInputStream(Files.newInputStream(chemin));
				ObjectOutputStream fichierDest = new ObjectOutputStream(Files.newOutputStream(cheminDest))) {
			boolean finFichier = false;
			do {
				try {
					Voiture voiture = (Voiture) fichier.readObject();
					if (voiture.getPrix() < 10000) {
						fichierDest.writeObject(voiture);
					}
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
	}
}
