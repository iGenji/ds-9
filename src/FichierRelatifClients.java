import java.io.IOException;
import java.io.RandomAccessFile;

public class FichierRelatifClients { 

	private String nomFichier; // contient le nom complet du fichier. ( path+nom)
	private RandomAccessFile fichier;			

	/**
	 *	Donne la longueur d'un enregisrement : 30 bytes pour le nom (15 caracteres), 
	 * + 8 bytes pour le montant (double)
	 * + 2 bytes pour le statut(caractere unicode)
	 */
	static final int LONG_ENREG = 40;

	/**
	 *	Donne le nombre d'enregistrements : 5 
	 */
	static final int NOMBRE_ENREG = 5;

	/**
	 *	Donne la longueur du nom  : 15 caracteres
	 */
	static final int LONG_NOM=30;

	/**
	 *	Donne la longueur du montant : 1 double= 8 bytes
	 */
	static final int LONG_MONTANT=8;

	/**
	 *	Donne la longueur du statut  : 1 caractere(unicode)
	 */
	static final int LONG_STATUT=2;							


	/** constructeur de la classe Fichier.
	@param nomFichier String specifiant le chemin d'acces et le nom du fichier.
	Exemple : "C:/java/dossier/donnees.dat" ('/' obligatoire et non '\')
	 */
	public FichierRelatifClients(String nom) {
		this.nomFichier = nom;
		fichier = null;					
	}	


	/** ouverture du fichier en lecture/ecriture
	@throws java.io.IOException
	 */
	public void ouvrirFichier()throws IOException{
		fichier = new RandomAccessFile(nomFichier,"rw");
		if (fichier.length()==0){
			EnregistrementClient eClient = new EnregistrementClient();
			for (int i=0;i<FichierRelatifClients.NOMBRE_ENREG;i++){
				ecrireEnregistrement(eClient);
			}
		}
	}

	/**  fermeture du fichier en lecture/ecriture
	@throws java.io.IOException si erreur lors de la fermeture.
	 */
	public void fermerFichier()throws IOException {
		fichier.close();			
	}

	public void positionnerAuDebut()throws IOException{
		fichier.seek(0);
	}

	public void positionnerEn(int numeroClient)throws IOException{
		fichier.seek(numeroClient*LONG_ENREG);
	}


	/**
	 * methode d'ecriture d'un enregistrement
	 *	@param	eClient l'enregistrement a ecrire
	 *	@exception	IOException
	 */

	public void ecrireEnregistrement(EnregistrementClient eClient) throws IOException{
		String s = formaterString(eClient.getClient().getNom(),LONG_NOM/2);
		fichier.writeChars(s);
		fichier.writeDouble(eClient.getClient().getMontant());
		fichier.writeChar(eClient.getStatut());
	}

	/**
	 * methode de lecture d'un enregistrement
	 *	@return	l'enregistrement lu
	 *	@exception	IOException
	 */	
	public EnregistrementClient lireEnregistrement() throws IOException{
		String nom = lireString(LONG_NOM/2);
		double montant = fichier.readDouble();
		char statut = fichier.readChar();
		return new EnregistrementClient(new Client(nom, montant),statut);
	}


	/**
	 * methode de lecture d'une string
	 *	@param	taille	la taille de la string
	 *	@return	l'enregistrement lu
	 *  	@exception	IOException
	 */
	private String lireString(int taille)throws IOException{
		String ch = "";
		for (int i = 0; i < taille; i++) {
			ch += fichier.readChar();	
		}
		return ch;
	}

	/*
	 * formatage d'une string a dimension en completant par des ' '
	 * ou en tronquant si necessaire	
	 *	@param	s		la string a completer
	 *	@param	taille	la taille de la string resultat
	 *	@return	la string mise a dimension
	 */
	public static String formaterString(String s, int taille){
		String ch = "";
		for(int i = 0; i < taille; i++) {
			if(i < s.length()){
				ch += s.charAt(i);
			}else{
				ch += " ";
			}
		}
		return ch;
	}
}

