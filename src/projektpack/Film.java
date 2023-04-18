package projektpack; //je to o konvenci, v případě malýho množství tříd je to jedno

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
/**
 * @author lordupoun
 */
//příště by každý film mohl mít všechny osoby dohromady a nebo klidně i takhle sólo a při prohledávání by prostě prohledával array různejch skupin lidí (herců, animátorů) -> je to míň náročný na CPU než je mít v jednom (ify je nebo není osoba herec), trochu náročnější na paměť, ale hlavně umožňuje rychlejc vybrat jen určitou skupinu (lepší pro další práci); Herci a animátoři pak můžou bejt objekty aby k nim šlo připsat víc věcí
//import cv9pack.Employee;

//import java.util.TreeMap; //rozložení nechat tak jak je, jen přidat objekty -> seznam lidí se dá kdykoliv smazat a přehodit do jednotlivejch tříd
//!!Dodělat parametry
public abstract class Film {
	private String nazev; //parametry filmu -> nazev, rezie, rok, seznam hercu //měly by být protected nebo private??
	private String rezie; 
	private int rok;
	protected List<Hodnoceni> listHodnoceni = new ArrayList<>();
	//private TreeMap<String, String> mapaHercu; //??když to dám sólo, jak to vyřeším, když pak budu chtít přidat víc tříd, kde to bude společný //Na jakejch základech se rozhodovat jestli to tam má nebo nemá bejt -> množství metod?
	public Film(String nazev, String rezie, int rok)
	 {
		this.nazev = nazev;
		this.rezie = rezie;
		this.rok=rok;
		//mapaHercu = new TreeMap<>();
		//mapaHercu.put(herci, null); //případně rozvinout mapu animátorů přímo v třídě animáků (herce má stejně většina filmů)
	 }

	public String getRezie() {
		return rezie;
	}

	public int getRok() {
		return rok;
	}

	public String getNazev() {
		return nazev;
	}
	abstract String vypisFilm();
	//metoda pro set herce v rámci třídy film (herce dát sólo poznámka?)
	//abstract setHodnoceni
	abstract String vypisFilmBezH();
	abstract String vypisFilmSoubor();
	abstract TreeMap<String, Osoba> getMapaOsob();
	

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public void setRezie(String rezie) {
		this.rezie = rezie;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}
	void setHodnoceni(Hodnoceni hodnoceniFilmu) //dopsat hodnocení do filmu, metoda bude vracet true, jestli splňuje požadavky filmu
	{
		listHodnoceni.add(hodnoceniFilmu); //nepůjde volit jaký je to film
		Collections.sort(listHodnoceni, Comparator.comparing((Hodnoceni h) -> h.getBody()).reversed()); //srovná v listuHodnoceni Hodnoceni h podle bodů // jakej je rozdíl od Collections.sort(manager.getListOfEmployees(), Comparator.comparing(Employee::getEmail)); //Seřadí v listu Employees podle mailů, s upozorněním od Eclipse
	}
	abstract boolean testBodyHodnoceni(int body);
}
//mapa herců/animátorů by mohla být společná, takhle to ale bude univerzálnejší
//list hodnocení (v pairs body a String)
//mapa herců je společná, tak aby
//nechat to tak jak to je, případně přesun TreeMapy mapaHercu do HranyFilm a TreeMapy animátoři do AnimovanyFilm -> takřka bez změn -> změní se konstruktor třídy Film, ostatní chyby se ukážou(spouštění konstruktoru přes super), a vkládání herců se nebude provádět konstruktorem a metodama třídy Film, ale třídy HranýFilm, případně AnimovanýFilm