import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class ModificationPrixAuto {
	public static ArrayList<Voiture> listeVoitures = new ArrayList<Voiture>();
	public static java.util.Scanner scanner = new java.util.Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Entrez le nom du fichier ");

		String nom = scanner.next();
		Path chemin = FileSystems.getDefault().getPath(nom);
		chargerFichier(chemin);
		String continuer = "O";

		do {
			System.out.print("Constructeur : ");
			String constructeur = scanner.next();
			System.out.print("Modele : ");
			String modele = scanner.next();
			System.out.print("Version : ");
			String version = scanner.next();
			System.out.print("Prix : ");
			int prix = scanner.nextInt();
			Voiture v = null;
			int i = 0;
			for (Voiture voiture : listeVoitures) {
				if (voiture.getConstructeur().equals(constructeur) && voiture.getModele().equals(modele)
						&& voiture.getVersion().equals(version)) {
					v = voiture;
					break;
				}
				i++;
			}
			if (v != null) {
				listeVoitures.remove(v); // idealement une methode setPrix dans la classe Voiture
				listeVoitures.add(i, new Voiture(constructeur, modele, version, prix));
			}
			System.out.println("Continuer ? (O/N)");
			continuer = scanner.next();
		} while (continuer.equals("O"));
		updateFile(chemin);
	}

	private static void chargerFichier(Path chemin) {
		try (ObjectInputStream fichier1 = new ObjectInputStream(Files.newInputStream(chemin))) {
			boolean finfichier = false;
			do {
				try {
					Voiture voiture = (Voiture) fichier1.readObject();
					listeVoitures.add(voiture);
				} catch (java.io.EOFException e) {
					finfichier = true;
				}
			} while (!finfichier);

		} catch (java.io.IOException ex) {
			System.out.println("probleme I/O: \t" + ex);
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("erreur : \t" + ex);
			ex.printStackTrace();
		}
	}

	private static void updateFile(Path chemin) {
		try (ObjectOutputStream fichier = new ObjectOutputStream(Files.newOutputStream(chemin))) {
			for (Voiture v : listeVoitures) {
				fichier.writeObject(v);
			}
		} catch (java.io.IOException ex) {
			System.out.println("probleme I/O: \t" + ex);
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("erreur : \t" + ex);
			ex.printStackTrace();
		}
	}
}
