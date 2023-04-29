package projektpack;

import java.util.List;
import java.util.TreeMap;

public class AnimovanyFilm extends Film {
	private int doporucenyVek;
	private TreeMap<String, Osoba> mapaAnimatoru;
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori)
	 {
		super(nazev, rezie, rok);
		this.doporucenyVek=doporucenyVek;
		vytvorListAnimatoru(animatori);
	 }
	public void vytvorListAnimatoru(String animatori) {
		String[] poleAnimatoru = animatori.split(",");
		mapaAnimatoru = new TreeMap<>();
		for(String i : poleAnimatoru)
		{
		mapaAnimatoru.put(i, null); 	
		}
	}
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori, List<Hodnoceni> listHodnoceni)
	 {
		super(nazev, rezie, rok, listHodnoceni);
		this.doporucenyVek=doporucenyVek;
		vytvorListAnimatoru(animatori);
		
	 }
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori, String hodnoceni)
	 {
		super(nazev, rezie, rok);
		listHodnoceni=DatabazeFilmu.zpracovaniHodnoceni(hodnoceni);
		this.doporucenyVek=doporucenyVek;
		vytvorListAnimatoru(animatori);
		
	 }
	public void setDoporucenyVek(int doporucenyVek) {
		this.doporucenyVek = doporucenyVek;
	}
	@Override
	String vypisFilm() { 
		String hodnoceniVypis="";
		for(Hodnoceni i : listHodnoceni) 
		{
			hodnoceniVypis=hodnoceniVypis+"\n Počet bodů: "+i.getBody()+"; Slovní hodnocení: "+i.getSlovniHodnoceni();
		}
		
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nDoporučený věk: "+doporucenyVek+"\nAnimátoři: "+mapaAnimatoru.keySet()+"\nHodnocení: "+hodnoceniVypis);
		
	}
	@Override
	boolean testBodyHodnoceni(int body) { 
		if(body>0&&body<11)
		{
			return true;
		}
		else
		return false;
	}
	@Override
	String vypisFilmBezH() {
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nDoporučený věk: "+doporucenyVek+"\nAnimátoři: "+mapaAnimatoru.keySet());
	}	
	@Override
	TreeMap<String, Osoba> getMapaOsob() {
		return mapaAnimatoru;
	}
	@Override
	String vypisFilmSoubor() { 
		
		return ("A; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+doporucenyVek+"; "+getAnimatoriVypis()+"; "+getHodnoceniVypis());
	}
	protected String getAnimatoriVypis() {
		String animatoriVypis=""; 
		for(String i : mapaAnimatoru.keySet()) 
		{
			animatoriVypis=animatoriVypis+i+",";
		}
		return animatoriVypis;
	}
	public int getDoporucenyVek() {
		return doporucenyVek;
	}
	@Override
	String vypisFilmSouborJednoduse() {
		
		
		return ("Film:/ Animovaný\nNázev:/ "+getNazev()+"\nRežie:/ "+getRezie()+"\nRok:/ "+getRok()+"\nDoporučený věk:/ "+doporucenyVek+"\nHerci:/ "+getAnimatoriVypis()+"\nHodnocení:/ "+getHodnoceniVypis());
		
	}		
}
