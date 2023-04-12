package projektpack;

import java.util.Scanner;

public class HlavniTrida {
	//Mapa bude skladovat filmy, key bude jméno -> zhruba na motivy chatgpt
	//abstraktní třída zadefinuje film, potom budou třídy hranej a animovanej
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DatabazeFilmu databaze1 = new DatabazeFilmu();
		databaze1.addFilmHranyRAW("Forrest Gump", "Zjistim Jmeno", 1993,"Tom Hanks");
		boolean konecProgramu = false;
		while(konecProgramu==false) {
		topDesign();
		System.out.println(" 1) Přidat nový film do databáze");
		System.out.println(" 2) Upravit stávající film");
		System.out.println(" 3) Smazat stávající film");
		System.out.println(" 4) Ohodnotit film");
		System.out.println("-----------------------------------");
		System.out.println(" 5) Vypsat všechny filmy z databáze");
		System.out.println(" 6) Vyhledat film v databázi");
		System.out.println(" 7) Vypsat herce/animátory s více filmy v databázi");
		System.out.println(" 8) Vyhledat filmy dle herce/animátora");
		System.out.println("-----------------------------------");
		System.out.println(" 9) Uložit informace o filmu do souboru");
		System.out.println("10) Načíst informace o filmu ze souboru");
		//System.out.println("10) test.");
		switch(readInt(sc)) 
		{
		case 1:
			System.out.println();
			topDesign();
			System.out.println(" 1) Hraný");
			System.out.println(" 2) Animovaný");
			int switchCislo=sc.nextInt();
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu:");
			String nazev=readString(sc);
			System.out.println(" Napište jméno režie:");
			String rezie=readString(sc);
			System.out.println(" Napište rok vydání:");
			int rok=readInt(sc);
			switch(switchCislo)
			{
			case 1:
				System.out.println(" Napište jméno herce nebo seznam jejich jmen:");
				sc.nextLine();
				String herci=readString(sc);
				HranyFilm movieHrany = new HranyFilm(nazev, rezie, rok, herci); //vymazat objekt po zapsání do databáze?
				databaze1.addFilm(movieHrany);
				break;
			case 2:
				System.out.println(" Napište minimální doporučený věk diváka:");
				int vek=readInt(sc);
				System.out.println(" Napište jméno animátora nebo seznam jejich jmen (oddělený čárkami):");
				sc.nextLine();
				String animatori=readString(sc);
				databaze1.addFilmAnimovanyRAW(nazev, rezie, rok, vek, animatori); //ušetřím proměnnou movieHrany ...
				break;	//spojit herce a animatory dohromady?
			}
			System.out.println("------------------------------");
			System.out.println("Film byl vložen do databáze.");
			break;
		case 2:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete upravit:");
			Film vybranyFilm=databaze1.getFilm(readString(sc));
			System.out.println(" Zvolte parametr, který chcete upravit:");
			System.out.println();
			topDesign();
			System.out.println(" 1) Název");
			System.out.println(" 2) Režie");
			System.out.println(" 3) Rok");
			if(vybranyFilm instanceof HranyFilm)
			{
				System.out.println(" 4) Herci");
			}
			else
			{
				System.out.println(" 4) Seznam animátorů");
				System.out.println(" 5) Doporučený věk");
			}
			switch(readInt(sc)) 
			{
			case 1:
				System.out.println(" Zadej název:");
				sc.nextLine();
				vybranyFilm.setNazev(readString(sc));
			break;				
			case 2:
				sc.nextLine();
				vybranyFilm.setRezie(readString(sc));
			break;
			case 3:
				sc.nextLine();
				vybranyFilm.setRok(Integer.parseInt(readString(sc)));
			break;
			case 4:
				sc.nextLine();
				//vybranyFilm.setHerci(readString(sc));
			break;
			case 5:
				sc.nextLine();
				vybranyFilm.setRok(Integer.parseInt(readString(sc)));
			break;
			}
			break;
		case 3:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete smazat:");
			databaze1.deleteFilm(readString(sc));
			System.out.println(" Film byl vymazán z databáze.");
			break;
		case 4:
			topDesign();
			System.out.println(" Napište název filmu, který chcete ohodnotit:");
			Film vybranyFilm2=databaze1.getFilm(readString(sc));
			System.out.println(" Napiště číselné hodnocení filmu.");
			Hodnoceni hodnoceniFilmu = new Hodnoceni();
				do
				{
					hodnoceniFilmu.setBody(readInt(sc));
				}
				while(vybranyFilm2.setHodnoceni(hodnoceniFilmu)==false);
		
				
			String slovne = readString(sc);
			Hodnoceni hodnoceniFilmu = new Hodnoceni();
			vybranyFilm2.setHodnoceni(hodnoceniFilmu)
			
			break;
		case 5:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete vyhledat:");
			Film vybranyFilm3=databaze1.getFilm(readString(sc));
			vybranyFilm3.vypisFilm();
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();
			break;
		}}
		sc.close();
	}

	public static int readInt(Scanner sc)
	{
		//Scanner sc = new Scanner(System.in); ----> není potřeba, zavede se v přetížení -> předá ji z mainu.
		int cislo;
			try
			{
				cislo = sc.nextInt();
			}
			catch(NumberFormatException n) //pro špatné zadání čísla
			{
				System.out.println("Zadejte prosím celé číslo.");
				sc.nextLine();
				cislo = readInt(sc);
			}
			return cislo;
	}
	public static String readString(Scanner sc)
	{
		String text=sc.nextLine();
		return text;
	}
	public static void topDesign()
	{
		System.out.println();
		System.out.println("           -Filmotéka 3000-");
	}
	//public static void vypisFilm(Film movie) //vypis filmu je umisteny v jinych tridach
	//{
	//	System.out.println(movie.getNazev());
	//}
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
}

//TODO: Ošetřit switch
//Rozdíl list/map -> klíče v mapě jsou rychlejší na hledání
//Nechat CHATGPT vytvořit mapu tabulky (filmů)
//Jak udělat abstrkatní třídy?
//třídy privátní, udělat gettery a settery¨
//Mapa kde je klíč a film -> datový typ film který obsahuje podrobnosti o filmu