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
		String[] poleHercu = herci.split(","); //zrušit házení přes další proměnnou
		mapaHercu = new TreeMap<>();
		for(String i : poleHercu)
		{
		mapaHercu.put(i, null); // vrátit zpět pokud má být mapa až v extended třídě	
		}
	 }
	public HranyFilm(String nazev, String rezie, int rok, String herci, List<Hodnoceni> listHodnoceni)//v konstruktoru pomocí stringů, v setu pomocí Treemapy a listu
	 {
		super(nazev, rezie, rok, listHodnoceni); //this.nazev=nazev; //nebo super -> zavolá celý konstruktor z původní třídy; u super záleží na pořádí
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
	String vypisFilmSoubor() {
		String hodnoceniVypis="";
		if(listHodnoceni.isEmpty()==true)
		{
			hodnoceniVypis="-";
		}
		else
		{
		for(Hodnoceni i : listHodnoceni) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			hodnoceniVypis=hodnoceniVypis+i.getBody()+"/-/"+i.getSlovniHodnoceni()+"/-/";
		}
		}
		String herciVypis="";
		for(String i : mapaHercu.keySet()) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			herciVypis=herciVypis+i+",";
		}
		
		return ("H; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+herciVypis+"; "+hodnoceniVypis);
	}					
	String vypisFilmSouborJednoduse()
	{
		String hodnoceniVypis="";
		if(listHodnoceni.isEmpty()==true)
		{
			hodnoceniVypis="-";
		}
		else
		{
		for(Hodnoceni i : listHodnoceni) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			hodnoceniVypis=hodnoceniVypis+i.getBody()+"/-/"+i.getSlovniHodnoceni()+"/-/";
		}
		}
		String herciVypis="";
		for(String i : mapaHercu.keySet()) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			herciVypis=herciVypis+i+",";
		}
		
		return ("Hraný\nNázev:/ "+getNazev()+"\nRežie:/ "+getRezie()+"\nRok:/ "+getRok()+"\nHerci:/ "+herciVypis+"\nHodnocení:/ "+hodnoceniVypis);
	}
}
//Film potřebuju uložit do mapy nebo něco, viz cviko
//takhle mám TreeMap v každý třídě zvlášť (kvůli zadání), kdyby to bylo v třídě Filmu, musel bych možná dělat gettery
//Pokud by zůstalo takhle, tak aimák může mít svůj vlastní konstruktor bez Treemapy a hranej s ní
//Třeba