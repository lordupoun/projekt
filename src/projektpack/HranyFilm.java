package projektpack;

import java.util.ArrayList;
import java.util.List;

public class HranyFilm extends Film { //pokud máme rozlišovat dva typy filmů asi mají být odlišné kategorie
	//private TreeMap<String, String> mapaHercu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	List<String> listHodnoceni = new ArrayList<>();
	public HranyFilm(String nazev, String rezie, int rok, String herci)//v konstruktoru pomocí stringů, v setu pomocí Treemapy a listu
	 {
		super(nazev, rezie, rok, herci); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		//mapaHercu = new TreeMap<>();
		//mapaHercu.put(herci, herci); // vrátit zpět pokud má být mapa až v extended třídě
	 }					
}
//Film potřebuju uložit do mapy nebo něco, viz cviko
//takhle mám TreeMap v každý třídě zvlášť (kvůli zadání), kdyby to bylo v třídě Filmu, musel bych možná dělat gettery