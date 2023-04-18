package projektpack;

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
		String[] poleAnimatoru = animatori.split(",");
		mapaAnimatoru = new TreeMap<>();
		for(String i : poleAnimatoru)
		{
		mapaAnimatoru.put(i, null); // vrátit zpět pokud má být mapa až v extended třídě	
		}
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
	String vypisFilmSoubor() {
		String hodnoceniVypis="";
		for(Hodnoceni i : listHodnoceni) //možná by to bylo lepší vypisovat přímo tady ->System.out.println();
		{
			hodnoceniVypis=hodnoceniVypis+","+i.getBody()+","+i.getSlovniHodnoceni();
		}
		
		return ("H; "+getNazev()+"; "+getRezie()+"; "+getRok()+"; "+doporucenyVek+"; "+mapaAnimatoru.keySet()+"; "+hodnoceniVypis);
	}		
}
