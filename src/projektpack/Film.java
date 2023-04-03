package projektpack;

import java.util.TreeMap;

public abstract class Film {
	String nazev;
	String rezie;
	int rok;
	private TreeMap<String, String> mapaHercu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	public Film()
	 {
		mapaHercu = new TreeMap<>();
	 }
	//list hodnocení (v pairs body a String)
	public String getNazev() {
		return nazev;
	}
}
