package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		etalOccupe = false;
		StringBuilder chaine = new StringBuilder();
		try {
			chaine.append("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
		} catch (NullPointersException e) {
			chaine.append("Le vendeur n'est plus sur cette etal.\n");
		}
		
		int produitVendu = quantiteDebutMarche - quantite;
		if (produitVendu > 0) {
			chaine.append(
					"il a vendu " + produitVendu + " parmi " + produit + ".\n");
		} else {
			chaine.append("il n'a malheureusement rien vendu.\n");
		}
		
		return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		
		StringBuilder chaine = new StringBuilder();
		
		if (vendeur==null) {
			throw new EtalNonOccupeException("L'étal n'est pas occupé");
		}
		
		try {
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter + " " + produit + " à " + vendeur.getNom());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return "";
		}
		
		if (quantite<0) {
			throw new QuantiteNonConformeException("La quantité" + quantite + "n'est pas une valeur positive");
		}
		
		if (quantite == 0) {
			chaine.append(", malheureusement il n'y en a plus !");
			quantiteAcheter = 0;
		}
		if (quantiteAcheter > quantite) {
			chaine.append(", comme il n'y en a plus que " + quantite + ", " + acheteur.getNom() + " vide l'étal de " + vendeur.getNom() + ".\n");
			quantiteAcheter = quantite;
			quantite = 0;
		}
		if (quantite != 0) {
			quantite -= quantiteAcheter;
			chaine.append(". " + acheteur.getNom()
					+ ", est ravi de tout trouver sur l'étal de "
					+ vendeur.getNom() + "\n");
		}
		return chaine.toString();
	}

	public boolean contientProduit(String produit) {
		return produit.equals(this.produit);
	}
	
	public class QuantiteNonConformeException extends IllegalArgumentException {
	
		private static final long serialVersionUID = 1L;
		
		public QuantiteNonConformeException() {
	
		}

		public QuantiteNonConformeException(String message) {
			super(message);
		}
		
		public QuantiteNonConformeException(Throwable cause) {
			super(cause);
		}
		
		public QuantiteNonConformeException(String message,Throwable cause) {
			super(message,cause);
		}
	}
	
	public class EtalNonOccupeException extends IllegalStateException {
		
		private static final long serialVersionUID = 1L;
		
		public EtalNonOccupeException() {
	
		}

		public EtalNonOccupeException(String message) {
			super(message);
		}
		
		public EtalNonOccupeException(Throwable cause) {
			super(cause);
		}
		
		public EtalNonOccupeException(String message,Throwable cause) {
			super(message,cause);
		}
	}

}
