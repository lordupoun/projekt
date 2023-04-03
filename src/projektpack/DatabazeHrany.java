package projektpack;

import java.util.TreeMap;

public class DatabazeHrany extends DatabazeFilmu {
	public void setFilm()
	 {
		Film film = new Film(name, year, genre, director);
	    mapaFilmu.put(name, film);
	 }
}
