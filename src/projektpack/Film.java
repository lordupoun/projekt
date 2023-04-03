package projektpack; //je to o konvenci

import java.util.TreeMap;

public abstract class Film {
	String nazev;
	String rezie;
	int rok;
	private TreeMap<String, String> mapaHercu;
	public Film(String nazev, String rezie, int rok, String lide)
	 {
		this.nazev = nazev;
		this.rezie = rezie;
		this.rok=rok;
		mapaHercu.put(lide, null); //případně rozvinout mapu animátorů přímo v třídě animáků (herce má stejně většina filmů)
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