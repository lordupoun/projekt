package projektpack; //je to o konvenci, v případě malýho množství tříd je to jedno

import java.util.TreeMap; //rozložení nechat tak jak je, jen přidat objekty -> seznam lidí se dá kdykoliv smazat a přehodit do jednotlivejch tříd

public abstract class Film {
	String nazev; //parametry filmu -> nazev, rezie, rok, seznam hercu
	String rezie;
	int rok;
	private TreeMap<String, String> mapaHercu; //??když to dám sólo, jak to vyřeším, když pak budu chtít přidat víc tříd, kde to bude společný //Na jakejch základech se rozhodovat jestli to tam má nebo nemá bejt -> množství metod?
	public Film(String nazev, String rezie, int rok, String herci)
	 {
		this.nazev = nazev;
		this.rezie = rezie;
		this.rok=rok;
		mapaHercu = new TreeMap<>();
		mapaHercu.put(herci, null); //případně rozvinout mapu animátorů přímo v třídě animáků (herce má stejně většina filmů)
	 }

	public String getNazev() {
		return nazev;
	}
	//metoda pro set herce v rámci třídy film (herce dát sólo poznámka?)
	//abstract setHodnoceni
}
//mapa herců/animátorů by mohla být společná, takhle to ale bude univerzálnejší
//list hodnocení (v pairs body a String)
//mapa herců je společná, tak aby
//nechat to tak jak to je, případně přesun TreeMapy mapaHercu do HranyFilm a TreeMapy animátoři do AnimovanyFilm -> takřka bez změn -> změní se konstruktor třídy Film, ostatní chyby se ukážou(spouštění konstruktoru přes super), a vkládání herců se nebude provádět konstruktorem a metodama třídy Film, ale třídy HranýFilm, případně AnimovanýFilm