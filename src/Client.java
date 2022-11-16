
public class Client {
	/**
	 * nom represente le nom du client     
	 */
	private String nom;
	
	/**
	 * montant represente la somme payee par le client 
	 */
	private double montant;

	public Client(String nom, double montant) {
		super();
		this.nom = nom;
		this.montant = montant;
	}

	/**
	 * methode de lecture du nom
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}	

	/**
	 * methode de lecture du montant
	 * @return montant
	 */
	public double getMontant() {
		return this.montant;
	}
	
	/** 
	 * methode de modification du montant
	 * @param montant
	 */
	public void setMontant(double montant) {
		this.montant=montant;
	}
	
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", montant=" + montant + "]";
	}
	
	
}
