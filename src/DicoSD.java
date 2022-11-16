import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class DicoSD implements java.io.Serializable{
	
	private HashMap<String, ArrayList<String>> dico;

	public DicoSD() {
		dico = new HashMap<String, ArrayList<String>>();
	}

	/**
	 * ajout dans le dico une association sd-lien si celle-ci n'est pas encore presente 
	 * @param sd une structure de donnees
	 * @param lien un lien vers un site internet
	 * @return true si le lien n'etait pas encore present dans le dico, false sinon
	 */
	public boolean ajouter(String sd, String lien){
		if(dico.containsKey(sd)){
			ArrayList<String> listeLiens = dico.get(sd);
			if(listeLiens.contains(lien))
				return false;
			listeLiens.add(lien);
			return true;

		}
		ArrayList<String> listeLiens = new ArrayList<String>();
		listeLiens.add(lien);
		dico.put(sd, listeLiens);
		return true;
	}
	
	/**
	 * verifie si la structure de donnees se trouve dans le dico
	 * cette structure de donnees doit posseder au moins un lien!
	 * @param sd
	 * @return true si sd est present, false sinon
	 */
	public boolean contient(String sd){
		return dico.containsKey(sd);
	}
	
	
	/**
	 * renvoie tous les liens associes a la structure de donnees passee en parametre
	 * @param sd
	 * @return une chaine de caracteres avec les liens selon le format : [lienPile1, lienPile2]
	 */
	public String lesLiens(String sd){
		if(dico.containsKey(sd)){
			ArrayList<String> listeLiens = dico.get(sd);
			return listeLiens.toString();
		}
		return "[]";
	}
	
	/**
	 * supprime dans le dico l'association sd-lien si celle-ci est presente 
	 * @param sd une structure de donnees
	 * @param lien un lien vers un site internet
	 * @return true si le lien etait present dans le dico, false sinon
	 */
	public boolean supprimer(String sd, String lien){
		if(!dico.containsKey(sd))
			return false;
		ArrayList<String> listeLiens = dico.get(sd);
		if(!listeLiens.contains(lien))
			return false;
		listeLiens.remove(lien);
		if(listeLiens.isEmpty())
			dico.remove(sd);
		return true;

	}
		
}
