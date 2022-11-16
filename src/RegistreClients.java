import java.io.IOException;
import java.util.ArrayDeque;

public class RegistreClients {

	private static FichierRelatifClients fichier;
	private ArrayDeque<Integer> stackPlacesLibres;

	public RegistreClients() throws IOException {
		fichier = new FichierRelatifClients("Client.dta");
		fichier.ouvrirFichier();
		stackPlacesLibres = new ArrayDeque<Integer>();
		for (int i = FichierRelatifClients.NOMBRE_ENREG - 1; i >= 0; i--) {
			if (fichier.lireEnregistrement().getStatut() == 'L') {
				stackPlacesLibres.push(i);
			}
		}
	}
	
	public void fermerRegistre() throws IOException {
		fichier.fermerFichier();
	}
	
	/**
	 * recherche un enregistrement libre en partant du debut du fichier
	 * @return le numero du premier enregistrement libre ou -1 si le fichier est plein
	 * @throws IOException
	 */
	private int rechercherEnregistrementLibre() throws IOException {
		if (stackPlacesLibres.isEmpty())
			return -1;
		return stackPlacesLibres.pop();
	}

	/**
	 * ajoute le client passe en parametre si le fichier n'est pas plein
	 * il ne faut pas verifier s'il est deja present 
	 * @param client le client a ajouter
	 * @return le numero du client ajoute ou -1 si le fichier est plein
	 * @throws IOException
	 */
	public int ajouterClient(Client client) throws IOException {
		if (client == null)
			throw new IllegalArgumentException();
		int indice = rechercherEnregistrementLibre();
		if (indice == -1)
			return -1;
		fichier.positionnerEn(indice);
		fichier.ecrireEnregistrement(new EnregistrementClient(client, 'O'));
		return indice;
	}
	
	/**
	 * recherche le client dont le numero est passe en parametre
	 * @param numeroClient le numero du client recherche
	 * @return le client ou null si aucun client ne correspond a ce numero
	 * @throws IOException
	 */
	public Client consulterClient(int numeroClient) throws IOException {
		if (numeroClient < 0 || numeroClient > FichierRelatifClients.NOMBRE_ENREG)
			return null;
		fichier.positionnerEn(numeroClient - 1);
		EnregistrementClient eClient = fichier.lireEnregistrement();
		if (eClient.getStatut() == 'L') {
			return null;
		}
		Client c = eClient.getClient();
		return c;
	}
	
	/**
	 * supprime le client dont le numero est passe en parametre
	 * @param numeroClient le numero du client recherche
	 * @return le client ou null si aucun client ne correspond a ce numero
	 * @throws IOException
	 */
	public Client supprimerClient(int numeroClient) throws IOException {
		if (numeroClient < 0 || numeroClient > FichierRelatifClients.NOMBRE_ENREG)
			return null;
		fichier.positionnerEn(numeroClient - 1);
		EnregistrementClient eClient = fichier.lireEnregistrement();
		if (eClient.getStatut() == 'L') {
			return null;
		}
		eClient.setStatut('L');
		stackPlacesLibres.push(numeroClient - 1);
		fichier.positionnerEn(numeroClient - 1);
		fichier.ecrireEnregistrement(eClient);
		return eClient.getClient();
	}
	
	/**
	 * met a jour le montant du client dont le numero est passe en parametre
	 * @param numeroClient le numero du client recherche
	 * @param nouveauMontant le nouveau montant
	 * @return le client ou null si aucun client ne correspond a ce numero
	 * @throws IOException
	 */
	public Client mettreAJourClient(int numeroClient, double nouveauMontant) throws IOException {
		if (numeroClient < 0 || numeroClient > FichierRelatifClients.NOMBRE_ENREG)
			return null;
		fichier.positionnerEn(numeroClient - 1);
		EnregistrementClient eClient = fichier.lireEnregistrement();
		if (eClient.getStatut() == 'L') {
			return null;
		}
		eClient.getClient().setMontant(nouveauMontant);
		fichier.positionnerEn(numeroClient - 1);
		fichier.ecrireEnregistrement(eClient);
		return eClient.getClient();
	}
	
	/**
	 * renvoie sous forme d'un String tous les clients existants et leurs montants
	 * @return tous les clients existants et leurs montants
	 * @throws IOException
	 */
	public String tousLesClients() throws IOException {
		fichier.positionnerAuDebut(); // Partir depuis le debut
		String s = "";
		for (int i = 0; i < FichierRelatifClients.NOMBRE_ENREG; i++) {
			EnregistrementClient eClient = fichier.lireEnregistrement();
			if (eClient.getStatut() == 'O') {
				s += eClient.getClient().getNom() + " " + eClient.getClient().getMontant() + "\n";
			}
		}
		return s;
	}
}
