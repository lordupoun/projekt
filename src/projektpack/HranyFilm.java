package projektpack;

//import java.util.ArrayList;
//import java.util.List;
import java.util.TreeMap;

public class HranyFilm extends Film { //pokud máme rozlišovat dva typy filmů asi mají být odlišné kategorie
	private TreeMap<String, String> mapaHercu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	//private List<Hodnoceni> listHodnoceni = new ArrayList<>();
	public HranyFilm(String nazev, String rezie, int rok, String herci)//v konstruktoru pomocí stringů, v setu pomocí Treemapy a listu
	 {
		super(nazev, rezie, rok); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		mapaHercu = new TreeMap<>();
		mapaHercu.put(herci, null); // vrátit zpět pokud má být mapa až v extended třídě
	 }
	@Override
	void vypisFilm() {
		// TODO Auto-generated method stub
		System.out.println("Název: "+nazev);
		System.out.println("Režie: "+rezie);
		System.out.println("Rok: "+rok);
		System.out.println("Herci: "+mapaHercu.keySet());
		System.out.println("Hodnocení: ");
		for(Hodnoceni i : listHodnoceni)
		{
			System.out.println(i.getBody());
			System.out.println(i.getSlovniHodnoceni());
		}
	}
	@Override
	boolean testBodyHodnoceni(int body) { //testuji zvlášť tak aby bodové hodnocení mohlo být vyplněné dříve než slovní
		if(body>0&&body<6) //nechci v mainu...
		{
			return true;
		}
		else
		return false;
	}					
	
}
//Film potřebuju uložit do mapy nebo něco, viz cviko
//takhle mám TreeMap v každý třídě zvlášť (kvůli zadání), kdyby to bylo v třídě Filmu, musel bych možná dělat gettery
//Pokud by zůstalo takhle, tak aimák může mít svůj vlastní konstruktor bez Treemapy a hranej s ní
//Třeba