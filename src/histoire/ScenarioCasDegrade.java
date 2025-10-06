package histoire;

import personnages.Gaulois;
import villagegaulois.Etal.*;
import villagegaulois.Etal;

public class ScenarioCasDegrade {


	public static void main(String[] args) {
		
		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("Pedrux",1);
		etal.occuperEtal(gaulois, "fleurs", 20);
		
//		etal.libererEtal();
//		System.out.println("Fin du test");	
		
		etal.acheterProduit(1, null);
		
//		try {
//			etal.acheterProduit(-10, gaulois);
//		} catch (QuantiteNonConformeException e) {
//			System.out.println(e.printStackTrace());
//		}
//		
//		try {
//			etal.acheterProduit(-10, gaulois);
//		} catch (EtalNonOccupeException e) {
//			System.out.println(e.printStackTrace());
//		}
		
		System.out.println("Fin du test");
	}

}
