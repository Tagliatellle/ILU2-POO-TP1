package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois != null && nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			// TODO
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {

		if (this.chef == null) {
			throw new VillageSansChefException("Ce village ne contient pas de chef.");
		}

		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtal = marche.trouverEtalLibre();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		if (indiceEtal == -1) {
			chaine.append(vendeur.getNom() + " n'a pas trouvé de place.\n");
		} else {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n°"
					+ (indiceEtal + 1) + ".\n");
		}

		return chaine.toString();
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();

		Etal[] listEtal = marche.trouverEtals(produit);

		if (listEtal.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " sur le marché.\n");
		} 
		//TODO pas d'else if
		else if (listEtal.length == 1) {

			Gaulois vendeur = listEtal[0].getVendeur();
			if (vendeur != null) {
				String nomVendeur = vendeur.getNom();
				chaine.append("Seul le vendeur " + nomVendeur + " propose des fleurs au marché;\n");
			}
		} else if (listEtal.length > 1) {

			chaine.append("Les vendeurs qui proposent des " + produit + " sont :\n");

			for (int i = 0; i < listEtal.length; i++) {
				Gaulois vendeur = listEtal[i].getVendeur();
				if (vendeur != null) {
					String nomVendeur = vendeur.getNom();
					chaine.append("- " + nomVendeur + "\n");
				}
			}
		}

		return chaine.toString();
	}

	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();

		Etal etal = marche.trouverVendeur(vendeur);

		if (etal != null) {
			chaine.append(etal.libererEtal());
		}

		return chaine.toString();
	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}

	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"" + this.nom + "\" possède plusieurs étals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}

	private class Marche {
		private Etal[] etals;

		public Marche(int nombreEtals) {
			etals = new Etal[nombreEtals];

			for (int i = 0; i < nombreEtals; i++) {
				etals[i] = new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (!etals[indiceEtal].isEtalOccupe()) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}

		public int trouverEtalLibre() {
			int nbEtals = etals.length;
			for (int i = 0; i < nbEtals; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			int nbEtalOccupe = 0;

			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalOccupe++;
				}
			}

			Etal[] etalProduit = new Etal[nbEtalOccupe];

			for (int i = 0, indiceTabProduit = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					etalProduit[indiceTabProduit] = etals[i];
					indiceTabProduit++;
				}
			}

			return etalProduit;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			int nbEtals = etals.length;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].getVendeur().equals(gaulois)) {
					return etals[i];
				}
			}
			return null;
		}

		public String afficherMarche() {
			int nbEtals = etals.length;
			StringBuilder str = new StringBuilder();
			int nbEtalVide = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].isEtalOccupe()) {
					str.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}
			str.append("Il reste " + nbEtalVide + " �tals non utilis�s dans le march�.\n");

			return str.toString();
		}

	}
}