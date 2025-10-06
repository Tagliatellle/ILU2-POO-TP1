package histoire;

import personnages.Gaulois;
import villagegaulois.Etal.*;
import villagegaulois.Etal;

public class ScenarioCasDegrade {


	public static void main(String[] args) {
		
		Etal etal = new Etal();
		Etal etalVide = new Etal();
		
		Gaulois gaulois = new Gaulois("Pedrux",1);
		etal.occuperEtal(gaulois, "fleurs", 20);
		
//		etal.libererEtal();
//		System.out.println("Fin du test");	
		
		etal.acheterProduit(1, null);
		
		System.out.println(etal.afficherEtal());
		try {
			etal.acheterProduit(-10, gaulois);
		} catch (QuantiteNonConformeException e) {
			etal.acheterProduit(10, gaulois);
		}
		System.out.println(etal.afficherEtal());
		
		try {
			etalVide.acheterProduit(10, gaulois);
		} catch (EtalNonOccupeException e) {
			System.out.println("Cet etal n'est pas occupé.");
		}
		
		System.out.println("Fin du test");
	}

}
