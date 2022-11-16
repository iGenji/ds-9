/** 
 *	Enregistrement utilise par le programme FichierRelatifClients
 */
public class EnregistrementClient{
	
	private Client client;
	
	/**
 	 * L'enregistrement est libre
	 */
	public static final char LIBRE='L';
 
	/**
 	 * L'enregistrement est occupe
	 */
	public static final char OCCUPE='O';
	
	/**
 	 * statut represente le statut de l'enregistrement 
	 *	L --> LIBRE
	 *  O --> OCCUPE
	 */
	private char statut;
	
	/**
	 * construit un objet par defaut
	 */
	public EnregistrementClient(){
		client = new Client("",0);
 		setStatut(EnregistrementClient.LIBRE);
	}
	
	public EnregistrementClient(Client client, char statut){
		this.client = client;
		setStatut(statut);
	}
	
	/**
	 * methode de lecture du client
	 * @return client
	 */
	public Client getClient() {
		return this.client;
	}	
	/**
	 * methode de lecture du statut
	 * @return statut
	 */
	public char getStatut() {
		return this.statut;
	}	
			
	/**
	 * methode de modification du statut
	 * @param statut
	 */
	public void setStatut(char statut) {
		this.statut=statut;
	}
	
	
	public String toString(){
		return client +"\t"+statut;
	}			
}























































































