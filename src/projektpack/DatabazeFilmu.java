package projektpack; //
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap; //DatabazeFilmu jen zprostredkovava pristup do mapy -> vlastne se jedna jen o takovou upravenou mapu s vlastními metodami

public class DatabazeFilmu { //do databaze vložím hotový film v mainu
	private TreeMap<String, Film> mapaFilmu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	public DatabazeFilmu()
	 {
		mapaFilmu = new TreeMap<>();
	 }
	public void addFilm(Film movie) //Co je lepší? Dělá mapa jen odkaz, nebo ty hodnoty zkopíruje, zeptám se zítra
	{
		mapaFilmu.put(movie.getNazev(), movie); //!!když nechám duplicitně název filmu stejnej jako key, bude to sice náročnější, ale přehlednější a víc futureproof
	}
	public void addFilmHranyRAW(String nazev, String rezie, int rok, String herci) //definovaný, že je hranej -> horší
	{
		mapaFilmu.put(nazev, new HranyFilm(nazev, rezie, rok, herci)); //!!když nechám duplicitně název filmu stejnej jako key, bude to sice náročnější, ale přehlednější a víc futureproof
	}
	public void addFilmAnimovanyRAW(String nazev, String rezie, int rok, int vek, String animatori) 
	{
		mapaFilmu.put(nazev, new AnimovanyFilm(nazev, rezie, rok, vek, animatori)); //!!když nechám duplicitně název filmu stejnej jako key, bude to sice náročnější, ale přehlednější a víc futureproof
	}
	public Film getFilm(String nazev)
	{
		return mapaFilmu.get(nazev);
	}
	public void deleteFilm(String nazev)
	{
		mapaFilmu.remove(nazev);
	}
	public TreeMap<String, Film> getMapa()//zrušit
	{
		return mapaFilmu;
	}
	public String spolecniHerci() //uživatel by musel specifikovat, že chce jenom herce nebo jenom animátory -> program nemůže vědět jestli myslí herce nebo animátora
	{
		List<Film> listVybranychFilmu = new ArrayList<>();
		String vybraneFilmy=""; //zaměnit za list Filmů, ne String s jejich názvy
		String prefix="";
		String finalniVyber="";
		String f="";
		for(Film film:mapaFilmu.values())
		{
			for(String herec:film.getMapaOsob().keySet())
			{
				
				for(Film film2:mapaFilmu.values())
				{
					if(film2.getMapaOsob().containsKey(herec)==true)
					{
						prefix=(herec+": ");
						//vybraneFilmy=(film2.getNazev()+", "+vybraneFilmy);
						listVybranychFilmu.add(film2);
					}
				}
				finalniVyber=finalniVyber+prefix;
				for(Film i:listVybranychFilmu)
				{
					f=f+i.getNazev();
				}
				finalniVyber=finalniVyber+f;
			}
			
		}
		
		return finalniVyber;
	}
	//public 
	public String getFilmyOsobyString(String osoba) //herce/animatora
	{
		String vybraneFilmy="";
		for(Film i:mapaFilmu.values())
		{
			if(i.getMapaOsob().containsKey(osoba)==true)
			{
				vybraneFilmy=(i.getNazev()+", "+vybraneFilmy); //
			}
		}
		return vybraneFilmy;
	}
	public List<Film> getFilmyOsoby(String osoba) //!!Pokud by filmy měli více parametrů (hraný by měl i animátory, musel bych to předělat -> záleží ale jak by se to měnilo. Pravděpodobně by se změnil i výpis a pak bych prostě vytvořil více různých metod, pro různé věci) -> getOsoby by vyhodila array více map různých typů osob a metoda v DatabaziFilmu by array prozkoušela
	{
		List<Film> listFilmu = new ArrayList<>(); //pro vypsání
		for(Film i:mapaFilmu.values())
		{
			if(i.getMapaOsob().containsKey(osoba)==true)
			{
				listFilmu.add(i);
			}
		}
		return listFilmu; 
	}	
	/*public void vypis(Film film)
	{
		
	}*/
	
}
//jak jsem to chtěl udělat před tím? dvě třídy sólo, každá s vlastní mapou pro hraný/animovaný film -> takhle vše v jedné mapě
//jak ID? nechci to udělat jako list
//výpis je v pořádku i bez ID
/*

for (String key: map.keySet()) {
    System.out.println("key : " + key);
    System.out.println("value : " + map.get(key));
}
*/
//Mapa vyhleda podle jmena rychleji, i když to bude náročnější na paměť -> je to výhodnější -> v hodnocení odkazuji jménem -> záleží na aplikaci, volím mapu -> je to zajímavější než for cykly pole, navíc výhodné kvůli hledání -> stejně to někde musí být uloženo
//Třída Film abstraktní tak jako zboží
//V rámci třídy Film nadefinovaný gettery a settery, hodnocení také v rámci třídy Film jako list -> Prostě objekt film s určitejma vlastnostma
//Objekty filmu (filmy) budu ukládat do objektů databází (instancí databází). Různé filmy ale mají různé parametry -> proto dvě různé třídy.
//jak funguje u abstraktní třídy konstruktor
//Původně jsem chtěl mít sólo třídu sólo na hraný a sólo na animáky 
//Můžu mít instanci Filmu, která je jednou uložená v poli a jednou mimo něj?