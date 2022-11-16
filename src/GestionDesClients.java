import java.io.IOException;


public class GestionDesClients {

	private static java.util.Scanner scanner = new java.util.Scanner(System.in);
	private static RegistreClients registreClients;

	public static void main(String[] args) {
		try {
			registreClients = new RegistreClients();
			int choix;
			do {
				System.out.println("1 -> Ajouter un client");
				System.out.println("2 -> Consulter un client");
				System.out.println("3 -> Supprimer un client");
				System.out.println("4 -> Mettre a jour le montant d'un client");
				System.out.println("5 -> Lister tous les clients");
				System.out.println("Autre -> Quitter");
				System.out.println();
				System.out.println("Entrez votre choix :");
				choix = scanner.nextInt();

				switch (choix) {
				case 1:
					ajout();
					break;
				case 2:
					consultation();
					break;
				case 3:
					suppression();
					break;
				case 4:
					miseAJour();
					break;
				case 5:
					liste();
					break;
				}
			} while (choix >= 1 && choix <= 5);
			registreClients.fermerRegistre();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void ajout() throws IOException {
		System.out.print("Nom client : ");
		String nomC = scanner.next();
		System.out.print("Montant : ");
		double montant = scanner.nextDouble();
		registreClients.ajouterClient(new Client(nomC, montant));
	}

	private static void liste() throws IOException {
		System.out.println(registreClients.tousLesClients());
	}

	private static void consultation() throws IOException {
		System.out.print("Numero client : ");
		int numeroClient = scanner.nextInt();
		Client c = registreClients.consulterClient(numeroClient);
		if (c == null) {
			System.out.println("Numero incorrect.\n");
		} else {
			System.out.println("Nom ->" + c.getNom());
			System.out.println("Montant ->" + c.getMontant() + "\n");
		}
	}

	private static void suppression() throws IOException {
		System.out.print("Numero client : ");
		int numeroClient = scanner.nextInt();
		Client c = registreClients.supprimerClient(numeroClient);
		if (c == null) {
			System.out.println("Client incorrect.\n");
		} else {
			System.out.println("Client supprime avec succes.\n");
		}
	}

	private static void miseAJour() throws IOException {
		System.out.print("Numero client : ");
		int numeroClient = scanner.nextInt();
		System.out.print("Nouveau montant : ");
		double nouveauMontant = scanner.nextDouble();
		Client c = registreClients.mettreAJourClient(numeroClient, nouveauMontant);
		if (c == null) {
			System.out.println("Numero incorrect.\n");
		} else {
			System.out.println("Client mis a jour.\n");
		}
	}
}
