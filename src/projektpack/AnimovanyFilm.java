package projektpack;

public class AnimovanyFilm extends Film {
	int doporucenyVek;
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori)
	 {
		super(nazev, rezie, rok, animatori);
		this.doporucenyVek=doporucenyVek;
	 }
	@Override
	void vypisFilm() {
		System.out.println("Název: "+nazev);
		System.out.println("Režie: "+rezie);
		System.out.println("Rok: "+rok);
		System.out.println("Věk: "+doporucenyVek);
		System.out.println("Animátoři: ");
		
	}	
}
