package projektpack; 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;


public abstract class Film {
	private String nazev; 
	private String rezie; 
	private int rok;
	protected List<Hodnoceni> listHodnoceni = new ArrayList<>(); 
	public Film(String nazev, String rezie, int rok)
	 {
		this.nazev = nazev;
		this.rezie = rezie;
		this.rok=rok;
		
	 }
	public Film(String nazev, String rezie, int rok, List<Hodnoceni> listHodnoceni) 
	 {
		this.nazev = nazev;
		this.rezie = rezie;
		this.rok=rok;
		this.listHodnoceni=listHodnoceni;
		
	 }
	public String getRezie() {
		return rezie;
	}

	public int getRok() {
		return rok;
	}

	public String getNazev() {
		return nazev;
	}
	abstract String vypisFilm();
	abstract String vypisFilmSouborJednoduse();
	abstract String vypisFilmBezH();
	abstract String vypisFilmSoubor();
	abstract TreeMap<String, Osoba> getMapaOsob();
	

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public void setRezie(String rezie) {
		this.rezie = rezie;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}
	void setHodnoceni(Hodnoceni hodnoceniFilmu) 
	{
		listHodnoceni.add(hodnoceniFilmu); 
		Collections.sort(listHodnoceni, Comparator.comparing((Hodnoceni h) -> h.getBody()).reversed()); 
	}
	abstract boolean testBodyHodnoceni(int body);
	protected String getHodnoceniVypis() {
		String hodnoceniVypis="";
		if(listHodnoceni.isEmpty()==true)
		{
			hodnoceniVypis="-";
		}
		else
		{
		for(Hodnoceni i : listHodnoceni) 
		{
			hodnoceniVypis=hodnoceniVypis+i.getBody()+"/-/"+i.getSlovniHodnoceni()+"/-/";
		}
		}
		return hodnoceniVypis;
	}
	
}