package projektpack;

import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.TreeMap;

public class HranyFilm extends Film { //pokud máme rozlišovat dva typy filmů asi mají být odlišné kategorie
	private TreeMap<String, Osoba> mapaHercu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	//private List<Hodnoceni> listHodnoceni = new ArrayList<>();
	public HranyFilm(String nazev, String rezie, int rok, String herci)//v konstruktoru pomocí stringů, v setu pomocí Treemapy a listu
	 {
		super(nazev, rezie, rok); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		vytvorListHercu(herci); //!!Pro příště -> Vytvoř list herců bude mít pro Hraný i Animák společnou metodu v DatabazeFilmu, kam se pošle String a on z něj naseká MapuOsob, která se uloží do objektu daného filmu (bude se předávat, ale teď to není priorita -> šlo by jen o to aby to bylo víc standardizovaný, ale zase tu nechci tvořit kód, kterej bude zbytečně alokovat paměť na předávání map, když to není potřeba -> nechám to v třídách Filmů -> max udělat mapuHercu společnou pro všechny a metodu přesunout do třídy Film, ale to je teď zbytečný, protože Animovanej film by neměl mít herce -> i když by to asi nevadilo -> Stejně se na to ukazuje ukazatelama)
	 }
	public HranyFilm(String nazev, String rezie, int rok, String herci, List<Hodnoceni> listHodnoceni)//v konstruktoru pomocí stringů, v setu pomocí Treemapy a listu
	 {
		super(nazev, rezie, rok, listHodnoceni); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		vytvorListHercu(herci);
	 }
	public HranyFilm(String nazev, String rezie, int rok, String herci, String hodnoceni)//Pro jednoduché vložení filmu
	 {
		super(nazev, rezie, rok); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		listHodnoceni=DatabazeFilmu.zpracovaniHodnoceni(hodnoceni);
		vytvorListHercu(herci);
	 }
	public HranyFilm(String nazev, String rezie, int rok, TreeMap<String,Osoba> herci, List<Hodnoceni> listHodnoceni)//Pro imporotvání filmu s mapou a listem
	 {
		super(nazev, rezie, rok, listHodnoceni); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
		mapaHercu=herci;
	 }
	private void vytvorListHercu(String herci) {
		String[] poleHercu = herci.split(","); //zrušit házení přes další proměnnou
		mapaHercu = new TreeMap<>();
		for(String i : poleHercu)
		{
		mapaHercu.put(i, null); // vrátit zpět pokud má být mapa až v extended třídě	
		}
	}
	@Override
	String vypisFilm() { //Nevhodný na velkej seznam filmů
		// TODO Auto-generated method stub
		/*System.out.println("Název: "+nazev);
		System.out.println("Režie: "+rezie);
		System.out.println("Rok: "+rok);
		System.out.println("Herci: "+mapaHercu.keySet());
		System.out.println("Hodnocení: ");*/
		String hodnoceniVypis="";
		for(Hodnoceni i : listHodnoceni) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			hodnoceniVypis=hodnoceniVypis+"\n Počet hvězdiček: "+i.getBody()+"; Slovní hodnocení: "+i.getSlovniHodnoceni();
		}
		
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nHerci: "+mapaHercu.keySet()+"\nHodnocení: "+hodnoceniVypis);
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

	@Override
	String vypisFilmBezH() {
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nHerci: "+mapaHercu.keySet());
	}

	@Override
	TreeMap<String, Osoba> getMapaOsob() {
		return mapaHercu;
	}

	@Override
	String vypisFilmSoubor() { //více filmů jednoho souboru
		//String hodnoceniVypis = getHodnoceni();
		String herciVypis="";
		for(String i : mapaHercu.keySet()) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			herciVypis=herciVypis+i+",";
		}
		
		return ("H; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+herciVypis+"; "+getHodnoceniVypis());
	}					
	String vypisFilmSouborJednoduse() //1 soubor, jeden film
	{
		//String hodnoceniVypis = getHodnoceni();
		String herciVypis="";
		for(String i : mapaHercu.keySet()) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			herciVypis=herciVypis+i+",";
		}
		
		return ("Film:/ Hraný\nNázev:/ "+getNazev()+"\nRežie:/ "+getRezie()+"\nRok:/ "+getRok()+"\nHerci:/ "+herciVypis+"\nHodnocení:/ "+getHodnoceniVypis());
	
	
	}
}
//Film potřebuju uložit do mapy nebo něco, viz cviko
//takhle mám TreeMap v každý třídě zvlášť (kvůli zadání), kdyby to bylo v třídě Filmu, musel bych možná dělat gettery
//Pokud by zůstalo takhle, tak aimák může mít svůj vlastní konstruktor bez Treemapy a hranej s ní
//Třeba