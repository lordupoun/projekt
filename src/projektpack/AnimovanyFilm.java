package projektpack;

import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.TreeMap;

public class AnimovanyFilm extends Film {
	private int doporucenyVek;
	private TreeMap<String, Osoba> mapaAnimatoru;
	//private List<Hodnoceni> listHodnoceni = new ArrayList<>();
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori)
	 {
		super(nazev, rezie, rok);
		this.doporucenyVek=doporucenyVek;
		vytvorListAnimatoru(animatori);
	 }
	private void vytvorListAnimatoru(String animatori) {
		String[] poleAnimatoru = animatori.split(",");
		mapaAnimatoru = new TreeMap<>();
		for(String i : poleAnimatoru)
		{
		mapaAnimatoru.put(i, null); // vrátit zpět pokud má být mapa až v extended třídě	
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
	@Override
	String vypisFilm() { //přepsat na String co půjde ven
		/*System.out.println("Název: "+nazev);
		System.out.println("Režie: "+rezie);
		System.out.println("Rok: "+rok);
		System.out.println("Věk: "+doporucenyVek);
		System.out.println("Animátoři: "+mapaAnimatoru.keySet());
		System.out.println("Hodnocení: ");
		for(Hodnoceni i : listHodnoceni)
		{
			System.out.println(i.getBody());
			System.out.println(i.getSlovniHodnoceni());
		}
		return "";*/
		String hodnoceniVypis="";
		for(Hodnoceni i : listHodnoceni) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			hodnoceniVypis=hodnoceniVypis+"\n Počet bodů: "+i.getBody()+"; Slovní hodnocení: "+i.getSlovniHodnoceni();
		}
		
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nVěk: "+doporucenyVek+"\nAnimátoři: "+mapaAnimatoru.keySet()+"\nHodnocení: "+hodnoceniVypis);
		
	}
	@Override
	boolean testBodyHodnoceni(int body) { //testuji zvlášť tak aby bodové hodnocení mohlo být vyplněné dříve než slovní
		if(body>0&&body<11)
		{
			return true;
		}
		else
		return false;
	}
	@Override
	String vypisFilmBezH() {
		return ("Název: "+getNazev()+"\nRežie: "+getRezie()+"\nRok: "+getRok()+"\nVěk: "+doporucenyVek+"\nAnimátoři: "+mapaAnimatoru.keySet());
	}	
	@Override
	TreeMap<String, Osoba> getMapaOsob() {
		return mapaAnimatoru;
	}
	@Override
	String vypisFilmSoubor() { //mohla by být jedna metoda s vypisFilmSoubor() jednoduse
		
		return ("A; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+doporucenyVek+"; "+getAnimatoriVypis()+"; "+getHodnoceniVypis());
	}
	protected String getAnimatoriVypis() {
		String animatoriVypis=""; //Vypis animatoru ve tride Film -> bude obecne vypisovat seznam a je jedno jestli animatoru nebo hercu
		for(String i : mapaAnimatoru.keySet()) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
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
		
		
		return ("Film: Animovaný\nNázev:/ "+getNazev()+"\nRežie:/ "+getRezie()+"\nRok:/ "+getRok()+"\n Doporučený Věk:/ "+doporucenyVek+"\nHerci:/ "+getAnimatoriVypis()+"\nHodnocení:/ "+getHodnoceniVypis());
		//return null;
	}		
}
