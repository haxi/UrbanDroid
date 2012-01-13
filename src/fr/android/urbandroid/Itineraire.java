package fr.android.urbandroid;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;


public class Itineraire  
{
	// Notre station de d�part.
	private Station stationD�part;
	// Notre station d'arriv�e.
	private Station stationArriv�e;
	// Le fameux parcours.
	private StringBuffer parcours;
	// La liste de ligne dont fait partie la station de d�part.
	private ArrayList<Ligne> ligneD�part = new ArrayList<Ligne>();
	// La liste de ligne dont fait partie la station d'arriv�e.
	private ArrayList<Ligne> ligneArriv�e = new ArrayList<Ligne>();
	
	// Constructeur.
	public Itineraire(Station d�part, Station arriv�e, ArrayList<Ligne> listeLigneD�part, ArrayList<Ligne> listeLigneArriv�e)
	{
		this.stationArriv�e = arriv�e;
		this.stationD�part = d�part;
		this.parcours = new StringBuffer("");
		this.ligneArriv�e = listeLigneArriv�e;
		this.ligneD�part = listeLigneD�part;
	}

	// Retourne la station de d�part.
	public Station getStationD�part()
	{
		return this.stationD�part;
	}
	
	// Retourne la station d'arriv�e.
	public Station getStationArriv�e()
	{
		return this.stationArriv�e;
	}
	
	public String toString()
	{
		return this.parcours.toString();
	}
	
	// Retourne le nom de la ligne en commun aux deux stations.
	// Sinon retourne null.
	private String v�rifLigne(Station a, Station b)
	{
		Set<String> listeLigneA = a.listeLigne().keySet();
		Set<String> listeLigneB = b.listeLigne().keySet();
		Iterator<String> itA = listeLigneA.iterator();
		Iterator<String> itB;
		String buffer;
		while (itA.hasNext())
		{
			buffer = itA.next();
			itB = listeLigneB.iterator();
			while(itB.hasNext())
			{
				if (buffer == itB.next())
					return buffer;
			}
		}
		return null;
	}
	
	private String v�rifLigne()
	{
		for (Ligne l1 : this.ligneArriv�e)
		{
			for (Ligne l2 : this.ligneD�part)
			{
				if (l1.getNom() == l2.getNom())
					return l1.getNom();
			}
		}
		return "MA";
	}
	
	// Retourne la liste de station d'une ligne.
	private TreeMap<Integer, Station> listeStation(Ligne ligne)
	{
		return ligne.getListeStation();	
	}
	
	// Retourne la liste de ligne d'une station.
	private HashMap<String, Boolean> listeLigne(Station station)
	{
		return station.listeLigne();
	}
	
	public void calculerItineraire()
	{
		/*
		si (this.stationDepart.getLigne() == this.stationArrivee.getLigne())
			parcours.ajouter("Prendre la station " + this.stationDepart);
		si (this.stationDepart.getPosition() < this.stationArrivee.getPosition())
			parcours.ajouter(" direction " + this.station.Depart.getLigne().getDernierTerminus() + ".\n");
		sinon
			parcours.ajouter(" direction " + this.station.Depart.getLigne().getPremierTerminus() + ".\n");
		finsi;
			parcours.ajouter("S'arretter � la station " + this.station.Arrivee.getNom() + ".\n");
		finsi;
		 */
		if (this.v�rifLigne() != null)
		{
			Ligne l = this.getLigne(/*this.v�rifLigne()*/"MA");
			String terminus1 = l.getListeStation().get(1).getNom();
			String terminus2 = l.getListeStation().get(l.getIndexSecondTerminus()).getNom();
					
			this.parcours.append("Prendre la station " + this.stationD�part.getNom());
			if (l.getIdStation(this.stationD�part)
					<
				l.getIdStation(this.stationArriv�e))
				this.parcours.append(" direction " + terminus2 + ".\n");
			else if (l.getIdStation(this.stationD�part)
					>
					l.getIdStation(this.stationArriv�e))
				this.parcours.append(" direction " + terminus1 + ".\n");
			this.parcours.append("Descendre � " + this.stationArriv�e.getNom() + ".\n");
		}
	}
	
	private Ligne getLigne(String nomLigne)
	{
		for (Ligne l : this.ligneArriv�e)
		{
			if (l.getNom() == nomLigne)
				return l;
		}
		for (Ligne l2 : this.ligneD�part)
		{
			if (l2.getNom() == nomLigne)
				return l2;
		}
		return null;
	}
}
