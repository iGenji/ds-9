
public class Voiture implements java.io.Serializable { 					
	
	//private static final long serialVersionUID = 6058622951218372374L;
		
	private String constructeur;
	private String modele;
	private String version;
	private int prix;

	
	public Voiture (String constructeur,String modele,String version,int prix) {
		this.constructeur = constructeur;
		this.modele = modele;
		this.version = version;
		this.prix = prix;	
	}
	
	public String toString() {
		if(version.length()<7)
			return "Constructeur : " + constructeur + "\t\tModele : " + modele + "\t\tVersion : " + version + "\t\tPrix : " + prix;
		return "Constructeur : " + constructeur + "\t\tModele : " + modele + "\t\tVersion : " + version + "\tPrix : " + prix;
	}
	
	public String getConstructeur(){
		return constructeur;
	}
	
	public String getModele(){
		return modele;
	}

	public String getVersion(){
		return version;
	}

	public int getPrix(){
		return prix;
	}

} 
