package projektpack; 
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap; 

public class DatabazeFilmu { 
	private TreeMap<String, Film> mapaFilmu; 
	public DatabazeFilmu()
	 {
		mapaFilmu = new TreeMap<>();
	 }
	public void addFilm(Film movie) 
	{
		mapaFilmu.put(movie.getNazev(), movie); 
	}
	public void addFilmHranyRAW(String nazev, String rezie, int rok, String herci) 
	{
		mapaFilmu.put(nazev, new HranyFilm(nazev, rezie, rok, herci)); 
	}
	public void addFilmAnimovanyRAW(String nazev, String rezie, int rok, int vek, String animatori) 
	{
		mapaFilmu.put(nazev, new AnimovanyFilm(nazev, rezie, rok, vek, animatori)); 
	}
	public Film getFilm(String nazev)
	{
		return mapaFilmu.get(nazev);
	}
	public boolean checkFilm(Film film)
	{
		if(mapaFilmu.containsValue(film)==true)
			return true;
	else
		return false;
	}
	public void deleteFilm(String nazev)
	{
		mapaFilmu.remove(nazev);
	}
	public TreeMap<String, Film> getMapa()
	{
		return mapaFilmu;
	}
	public String spolecniHerci() 
	{	
		List<String> listVybranychFilmu = new ArrayList<>(); 
		List<String> listProvedeno = new ArrayList<>();
		String vystup="";
		boolean vypisZakladnihoFilmu = false;
		for(Film kontrolovanyFilm:mapaFilmu.values())
		{
			for(String herec:kontrolovanyFilm.getMapaOsob().keySet())
			{
				
				for(Film porovnavaciFilm:mapaFilmu.values())
				{
					
					if(porovnavaciFilm!=kontrolovanyFilm) 
					{
						if(porovnavaciFilm.getMapaOsob().containsKey(herec)==true)
						{
							
							if(listVybranychFilmu.contains(porovnavaciFilm.getNazev())==false&&listProvedeno.contains(herec)==false)
							{
								
								if(vypisZakladnihoFilmu==false)
								{
									listVybranychFilmu.add(kontrolovanyFilm.getNazev());
									vystup=vystup+herec+": ";
									vypisZakladnihoFilmu=true;
								}
								listVybranychFilmu.add(porovnavaciFilm.getNazev());
								listProvedeno.add(herec);
								
							}
							
						}
					}
					
				}
				
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
			
		}
		
		
		return vystup; 
	}
	 
	public String getFilmyOsobyString(String osoba) 
	{
		String vybraneFilmy="";
		for(Film i:mapaFilmu.values())
		{
			if(i.getMapaOsob().containsKey(osoba)==true)
			{
				vybraneFilmy=(i.getNazev()+", "+vybraneFilmy); 
			}
		}
		return vybraneFilmy;
	}
	public List<Film> getFilmyOsoby(String osoba) 
	{
		List<Film> listFilmu = new ArrayList<>(); 
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
		
	if(pole[0].equals("H"))
    {
    	
    	HranyFilm novyFilm2 = new HranyFilm(pole[1], pole[2], Integer.parseInt(pole[3]), pole[4], zpracovaniHodnoceni(pole[5]));
    	addFilm(novyFilm2);
    	
    }
	else if(pole[0].equals("A"))
    {
		
    	AnimovanyFilm novyFilm2 = new AnimovanyFilm(pole[1], pole[2], Integer.parseInt(pole[3]), Integer.parseInt(pole[4]), pole[5], zpracovaniHodnoceni(pole[6]));
    	addFilm(novyFilm2);
    	
    	
    }
	
	
}
	protected static List<Hodnoceni> zpracovaniHodnoceni(String radekPole)
	{
		
	List<Hodnoceni> listHodnoceni =  new ArrayList<>();
	if(radekPole.equals("-"))
	{
		
	}
	else
	{
		String[] poleHodnoceni = radekPole.split("/-/");
    	int counter = 0;
    	int bodyHodnoceni=0;
	for(String i2:poleHodnoceni)
	{
		if(counter==0)
		{
			bodyHodnoceni=Integer.parseInt(i2);
			
			counter=1;
	        
		}
		else if(counter==1) 
		{
			
			
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
	   
	    HranyFilm novyFilm2 = new HranyFilm(soubor.get(1), soubor.get(2), Integer.parseInt(soubor.get(3)), soubor.get(4), zpracovaniHodnoceni(soubor.get(5)));
	   
    	addFilm(novyFilm2);
	    }
	    else if(soubor.get(0).equals("Animovaný"))
	    {	
		    AnimovanyFilm novyFilm2 = new AnimovanyFilm(soubor.get(1), soubor.get(2), Integer.parseInt(soubor.get(3)), Integer.parseInt(soubor.get(4)),soubor.get(5), zpracovaniHodnoceni(soubor.get(6)));
        	addFilm(novyFilm2);
		    }
		
	}
 }
