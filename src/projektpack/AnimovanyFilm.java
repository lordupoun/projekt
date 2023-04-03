package projektpack;

public class AnimovanyFilm extends Film {
	int doporucenyVek;
	public AnimovanyFilm(String nazev, String rezie, int rok, int doporucenyVek, String animatori)
	 {
		super(nazev, rezie, rok, animatori);
		this.doporucenyVek=doporucenyVek;
	 }	
}
