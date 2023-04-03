package projektpack;
import java.util.TreeMap;

public class DatabazeFilmu { //do databaze vložím hotový film v mainu
	private TreeMap<String, Film> mapaFilmu; //vkládám Film -> který bude buď z instance Hraného nebo Animovaného
	public DatabazeFilmu()
	 {
		mapaFilmu = new TreeMap<>();
	 }
	public void setFilm(Film movie)
	{
		mapaFilmu.put(movie.getNazev(), movie); //!!když nechám duplicitně název filmu stejnej jako key, bude to sice náročnější, ale přehlednější a víc futureproof
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