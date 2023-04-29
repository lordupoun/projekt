package projektpack;

import java.util.List;
import java.util.TreeMap;

public class HranyFilm extends Film { 
	private TreeMap<String, Osoba> mapaHercu; 
	public HranyFilm(String nazev, String rezie, int rok, String herci)
	 {
		super(nazev, rezie, rok); 
		vytvorListHercu(herci); 
	 }
	public HranyFilm(String nazev, String rezie, int rok, String herci, List<Hodnoceni> listHodnoceni)
	 {
		super(nazev, rezie, rok, listHodnoceni); 
		vytvorListHercu(herci);
	 }
	public HranyFilm(String nazev, String rezie, int rok, String herci, String hodnoceni)
	 {
		super(nazev, rezie, rok); 
		listHodnoceni=DatabazeFilmu.zpracovaniHodnoceni(hodnoceni);
		vytvorListHercu(herci);
	 }
	public HranyFilm(String nazev, String rezie, int rok, TreeMap<String,Osoba> herci, List<Hodnoceni> listHodnoceni)
	 {
		super(nazev, rezie, rok, listHodnoceni); 
		mapaHercu=herci;
	 }
	public void vytvorListHercu(String herci) {
		String[] poleHercu = herci.split(","); 
		mapaHercu = new TreeMap<>(); 
		for(String i : poleHercu)
		{
		mapaHercu.put(i, null); 
		}
	}
	@Override
	String vypisFilm() { 
		String hodnoceniVypis="";
		for(Hodnoceni i : listHodnoceni) 
		{
			hodnoceniVypis=hodnoceniVypis+"\n Počet hvězdiček: "+i.getBody()+"; Slovní hodnocení: "+i.getSlovniHodnoceni();
		}
		
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nHerci: "+mapaHercu.keySet()+"\nHodnocení: "+hodnoceniVypis);
	}
	@Override
	boolean testBodyHodnoceni(int body) { 
		if(body>0&&body<6) 
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
		return ("H; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+getHerciVypis()+"; "+getHodnoceniVypis());
	}
	protected String getHerciVypis() {
		String herciVypis="";
		for(String i : mapaHercu.keySet())
		{
			herciVypis=herciVypis+i+",";
		}
		return herciVypis;
	}					
	String vypisFilmSouborJednoduse()
	{
		
		return ("Film:/ Hraný\nNázev:/ "+getNazev()+"\nRežie:/ "+getRezie()+"\nRok:/ "+getRok()+"\nHerci:/ "+getHerciVypis()+"\nHodnocení:/ "+getHodnoceniVypis());
	
	
	}
}