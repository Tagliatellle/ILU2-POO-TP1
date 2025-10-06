package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {


	public static void main(String[] args) {
		
		Etal etal = new Etal();
		Gaulois gaulois = new Gaulois("Pedrux",1);
//		etal.libererEtal();
//		System.out.println("Fin du test");	
		
		etal.acheterProduit(1, null);
		etal.acheterProduit(-10, gaulois);
		
		System.out.println("Fin du test");
	}

}
