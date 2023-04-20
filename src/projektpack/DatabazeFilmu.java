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
		/*List<Film> listVybranychFilmu = new ArrayList<>();
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
		
		return finalniVyber;*/
		/*for(Film porovnavaciFilm:mapaFilmu.values())
		{
			if(porovnavaciFilm!=kontrolovanyFilm) //neplatí pro film ve kterým hraje -> minimálně dva filmy
			{
				if(kontrolovanyFilm.getMapaOsob().containsKey(herec)==true)
				{
					listVybranychFilmu.add(kontrolovanyFilm.getNazev());
					if(listVybranychFilmu.contains(porovnavaciFilm.getNazev())==false)
					{
						if(vypisHerce==false)
						{
						vystup="\n"+herec+": ";
						vypisHerce=true;
						}
						listVybranychFilmu.add(porovnavaciFilm.getNazev());
						System.out.println(porovnavaciFilm.getNazev());
						System.out.println(herec);
					}
					
				}
			}
			
		}
		vypisHerce=false;
		for(String i:listVybranychFilmu)
		{
			vystup=vystup+i+", ";
		}
		listVybranychFilmu.clear();*/
		List<String> listVybranychFilmu = new ArrayList<>(); //algoritmus na hledání kdyby to bylo seřazený?;; šlo by vyřešit mapou <Herec, KolekceFilmů> -> Ta by neustále zabírala paměť -> List prostě občas vysypu
		List<String> listProvedeno = new ArrayList<>();
		String vystup="";
		//String f="";
		boolean vypisZakladnihoFilmu = false;
		for(Film kontrolovanyFilm:mapaFilmu.values())
		{
			//System.out.println("Kontrolovaný film:"+kontrolovanyFilm.getNazev());
			for(String herec:kontrolovanyFilm.getMapaOsob().keySet())
			{
				//System.out.println("Herec:"+herec);
				//vystup=herec+": ";
				for(Film porovnavaciFilm:mapaFilmu.values())
				{
					//System.out.println("Porovnávací film:"+porovnavaciFilm.getNazev());
					if(porovnavaciFilm!=kontrolovanyFilm) //neplatí pro film ve kterým hraje -> minimálně dva filmy;; ify logicky seřazený podle četnosti používání
					{
						if(porovnavaciFilm.getMapaOsob().containsKey(herec)==true)
						{
							//listVybranychFilmu.add(kontrolovanyFilm.getNazev());
							if(listVybranychFilmu.contains(porovnavaciFilm.getNazev())==false&&listProvedeno.contains(herec)==false)
							{
								//pokud se podmínky splnili, je potřeba právě jednou zapsat i ten první film -> toho jen forcyklama nedocílím
								if(vypisZakladnihoFilmu==false)
								{
									listVybranychFilmu.add(kontrolovanyFilm.getNazev());
									vystup=vystup+herec+": ";
									vypisZakladnihoFilmu=true;
								}
								listVybranychFilmu.add(porovnavaciFilm.getNazev());
								listProvedeno.add(herec);
								//System.out.println(herec);
								//System.out.println("zapsán");
							}
							
						}
					}
					
				}//herci jsou tam dvakrát a filmy taky dvakrát
				
				for(String i:listVybranychFilmu)
				{
					vystup=vystup+i+", ";
				}
				listVybranychFilmu.clear();
				if(vypisZakladnihoFilmu==true)
				{
					vystup=vystup+"\n";
				}
				vypisZakladnihoFilmu=false;	
			}
			//vystup=vystup+"\n";
		}
		
		//finalniVyber=finalniVyber+f;
		return vystup; //dvakrát to projíždí herce: dvě řešení -> List na herce s kontrolou NEBO jeden velkej list na Filmy, kde budou i herci (tak jak budou ve výpisu)
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
	public void zpracujSoubor(String s)
	 {
		String[] pole = s.split("; ");
		//System.out.println("test");
	if(pole[0].equals("H"))
    {
    	
    	HranyFilm novyFilm2 = new HranyFilm(pole[1], pole[2], Integer.parseInt(pole[3]), pole[4], zpracovaniHodnoceni(pole[5]));
    	addFilm(novyFilm2);
    	//System.out.println("test");
    }
	else if(pole[0].equals("A"))
    {
		//System.out.println(pole[0]);
    	AnimovanyFilm novyFilm2 = new AnimovanyFilm(pole[1], pole[2], Integer.parseInt(pole[3]), Integer.parseInt(pole[4]), pole[5], zpracovaniHodnoceni(pole[6]));
    	addFilm(novyFilm2);
    	
    	//System.out.println("test");
    }
	/*public void vypis(Film film)
	{
		
	}*/
	
}
	protected static List<Hodnoceni> zpracovaniHodnoceni(String radekPole)
	{
		//System.out.println("s");
	//System.out.println(pole[0]); //možná jsem mohl sólo dělat body a sólo herce
	List<Hodnoceni> listHodnoceni =  new ArrayList<>();
	if(radekPole.equals("-"))
	{
		//System.out.println("s");
	}
	else
	{
		String[] poleHodnoceni = radekPole.split("/-/");
    	//System.out.println("t");
    	int counter = 0;
    	int bodyHodnoceni=0;
	for(String i2:poleHodnoceni)
	{
		if(counter==0)
		{
			bodyHodnoceni=Integer.parseInt(i2);
			//System.out.println(i);
			counter=1;
	        //System.out.println("test");
		}
		else if(counter==1) //nemůže být jen if
		{
			
			//System.out.println(i);
			String slovaHodnoceni=i2;
			Hodnoceni hodnoceniSoubor = new Hodnoceni(bodyHodnoceni, slovaHodnoceni);
			listHodnoceni.add(hodnoceniSoubor);
			counter=0;
		}

		
	}
	}
	return listHodnoceni;
	}
	public void zpracujSouborJednoduse(List<String> soubor) {
		if(soubor.get(0).equals("Hraný"))
	    {	
	    //System.out.println(soubor.get(0));
	    HranyFilm novyFilm2 = new HranyFilm(soubor.get(1), soubor.get(2), Integer.parseInt(soubor.get(3)), soubor.get(4), zpracovaniHodnoceni(soubor.get(5)));
	    //System.out.println("test");
    	addFilm(novyFilm2);
	    }
	    else if(soubor.get(0).equals("Animovaný"))
	    {	
		    HranyFilm novyFilm2 = new HranyFilm(soubor.get(1), soubor.get(2), Integer.parseInt(soubor.get(3)), soubor.get(4), zpracovaniHodnoceni(soubor.get(5)));
        	addFilm(novyFilm2);
		    }
		
	}
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