package projektpack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AnimovanyFilm extends Film {
	private int doporucenyVek;
	private TreeMap<String, String> mapaAnimatoru;
	//private List<Hodnoceni> listHodnoceni = new ArrayList<>();
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori)
	 {
		super(nazev, rezie, rok);
		this.doporucenyVek=doporucenyVek;
		mapaAnimatoru = new TreeMap<>();
		mapaAnimatoru.put(animatori, null);
	 }
	@Override
	void vypisFilm() { //přepsat na String co půjde ven
		System.out.println("Název: "+nazev);
		System.out.println("Režie: "+rezie);
		System.out.println("Rok: "+rok);
		System.out.println("Věk: "+doporucenyVek);
		System.out.println("Animátoři: "+mapaAnimatoru.keySet());
		
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
}
