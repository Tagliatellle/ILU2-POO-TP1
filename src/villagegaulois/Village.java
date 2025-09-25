package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
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
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
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
			for (int i = 0; i < nbEtals && !etals[i].isEtalOccupe(); i++) {
				return i;
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			int nbEtalOccupe = 0;
			int nbEtals = etals.length;

			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit)) {
					nbEtalOccupe++;
				}
			}

			Etal[] etalProduit = new Etal[nbEtalOccupe];
			int indiceTabProduit = 0;

			for (int i = 0; i < nbEtals; i++) {
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
			str.append("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n");

			return str.toString();
		}

	}
}