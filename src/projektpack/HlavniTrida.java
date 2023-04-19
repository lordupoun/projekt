package projektpack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
//import java.util.Map.Entry;
import java.util.Scanner;
/*GUITODO:
 * Dodělat hodnocení (hvězdičky atd.)
 * Přidat "film nenalezen v databázi)
 * Přidat seřazení hodnocení filmů viz notebook...
 */
public class HlavniTrida {
	//Mapa bude skladovat filmy, key bude jméno -> zhruba na motivy chatgpt //místo vypisFilm -> vypisFilmu
	//abstraktní třída zadefinuje film, potom budou třídy hranej a animovanej
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DatabazeFilmu databaze1 = new DatabazeFilmu();
		//databaze1.addFilmHranyRAW("Forrest Gump", "Zjistim Jmeno", 1993,"Tom Hanks,Otm Shank");
		//databaze1.addFilmHranyRAW("Zelené Brýle", "Zjistim Jmeno", 1994,"Tom Hanks,Zjistim Jmeno");
		databaze1.addFilmHranyRAW("Zelené Brýle 2", "Zjistim Jmeno", 1994,"Tom Hanks,Zjistim Jmeno");
		boolean konecProgramu = false;
		while(konecProgramu==false) {
		System.out.println("           -Filmotéka 3000-");
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
				System.out.println(" Napište jméno herce nebo seznam jejich jmen (oddělený čárkami):");
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
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete ohodnotit:");
			Film vybranyFilm2=databaze1.getFilm(readString(sc));
			int body = 0;
			if(vybranyFilm2 instanceof HranyFilm)
			{
				{
					System.out.println(" Napište číselné hodnocení v rozsahu 1 až 5 hvěziček.");
					body = readInt(sc);
				}
				while(vybranyFilm2.testBodyHodnoceni(body)==false);
			}
			else if(vybranyFilm2 instanceof AnimovanyFilm)
			{
				{
					System.out.println(" Napište číselné hodnocení v rozsahu 1 až 10 bodů.");
					body = readInt(sc);
				}
				while(vybranyFilm2.testBodyHodnoceni(body)==false);
			}
			sc.nextLine();
			System.out.println(" Napište slovní hodnocení filmu:");
			Hodnoceni noveHodnoceni = new Hodnoceni(body, readString(sc));
			vybranyFilm2.setHodnoceni(noveHodnoceni);
			
			break;
		case 5: //nemá vypisovat hodnocení filmů
			topDesign();
			sc.nextLine();
			/*for(Entry<String, Film> i : databaze1.getMapa().entrySet())
			{
				System.out.println(databaze1.getFilm(i.getKey()).vypisFilm());
			}
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();*/
			/*for(String i : databaze1.getMapa().keySet())
			{
				System.out.println(databaze1.getFilm(i).vypisFilm());
			}*/
			for(Film i : databaze1.getMapa().values())//přesunout do DatabazeFilmu, pro jednodušší přístup
			{
				System.out.println(i.vypisFilmBezH()+"\n"); //nechci vypisovat vypisFilm(String) podle stringu z mapy, protože Film je objekt (musel by nejdřív najít objekt Filmu podle názvu a potom ho vypsat; Objekt "Forrest Gump" neexistuje, jen objekt v mapě databáze s klíčem Forrest Gump -> jinak se k němu nedostanu. Musel bych z toho udělat jeden příkaz -> vypisFilm(ForrestGump) -> getFilm(Forrest Gump) -> vypis.getFilm(ForrestGump), čímž by ale původní funkce dost ztratily na významu.
	
			}
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------test");
			sc.nextLine();
			//databaze1.vypis(ForrestGump);
			break;
		case 6:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete vyhledat:");
			//Film vybranyFilm3=databaze1.getFilm(readString(sc));
			System.out.println(databaze1.getFilm(readString(sc)).vypisFilm());
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();
			break;
		case 7:
			topDesign();
			sc.nextLine();
			System.out.println(databaze1.spolecniHerci());
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();
			break;
		case 8:
			topDesign();
			sc.nextLine();
			System.out.println(" Zadejte jméno hledaného herce:");
			String jmenoHerce=readString(sc);
			System.out.println(" Filmy ve kterých hraje:");
			System.out.println(databaze1.getFilmyOsobyString(jmenoHerce));
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();
			break;
		case 9:
			topDesign(); //upravit výpis herců atd.
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete zapsat do souboru:");
			String nazevFilmu =readString(sc);
			File file = new File(System.getProperty("user.dir")+File.separator + "Filmy" + File.separator + nazevFilmu+".txt");
			file.getParentFile().mkdirs();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			    //bw.write(databaze1.getFilm(nazevFilmu).vypisFilmSoubor());
				bw.write(databaze1.getFilm(nazevFilmu).vypisFilmSoubor());
			    bw.flush();
			    System.out.println("Soubor "+nazevFilmu+".txt ve složce "+file.getParentFile()+" byl úspěšně vytvořen!");
			} catch (Exception e) {
			    System.out.println("Do souboru se nepovedlo zapsat.");
			}
			
			//Film vybranyFilm3=databaze1.getFilm(readString(sc));
			//System.out.println();
			System.out.println("-----------------");
			System.out.println("-Pokračujte stisknutím klávesy ENTER-");
			System.out.println("-----------------");
			sc.nextLine();
			break;
		case 10: //čte i více souborů, i když nemusí -> šlo řešit pouhým výpisem, jedna věc na jeden řádek -> šlo by vytvořit "GUI" v souboru Hodnocení:/
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete přečíst ze souboru:");
			String nazevFilmu2 =readString(sc);
			File file2 = new File(System.getProperty("user.dir")+File.separator + "Filmy" + File.separator + nazevFilmu2+".txt");
			try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
			    String s;
			    //String vystup;
			    List<String> soubor = new ArrayList<>();
			    while ((s = br.readLine()) != null) {
			    	databaze1.zpracujSoubor(s);
			        //String[] pole = s.split(":/ "); //řádek se rozloží do pole dle , -> každej prvek z pole podle čísla -> teoreticky by se mohly načítat pouze metodou, zbytek udělat v databázi -> Hraný/Animák -> prvek 0 -> jméno, prvek 5 -> hodnocení, rozděli podle , na body a hodnocení, pole řešit sudý lichý
			        	 //<-Kdyby tu bylo místo pětky počet parametrů, bylo by to víc futureproof
			        //System.out.println("test");	
			        //soubor.add(pole[1]);
			        	
			        
			        //System.out.println(pole[0]);
			        
			        //databaze1.zpracujSouborJednoduse(pole);
			        
			        
			    }
			    //databaze1.zpracujSouborJednoduse(soubor);
			    //System.out.println(soubor.get(0));
			     //pro možnost využití obou metod
			    
			    /*while ((s = br.readLine()) != null) {
			        String[] pole = s.split("; "); //řádek se rozloží do pole dle , -> každej prvek z pole podle čísla -> teoreticky by se mohly načítat pouze metodou, zbytek udělat v databázi -> Hraný/Animák -> prvek 0 -> jméno, prvek 5 -> hodnocení, rozděli podle , na body a hodnocení, pole řešit sudý lichý
			        //databaze1.zpracujSoubor(pole);//Vice filmu ze souboru
			       
			        
			        
			    }*/
			        System.out.println("Film byl úspěšně načten.");
			        System.out.println("-----------------");
					System.out.println("-Pokračujte stisknutím klávesy ENTER-");
					System.out.println("-----------------");
					sc.nextLine();
			    //}??
			} catch (Exception e) {
			    System.out.println("Chyba při čtení ze souboru. "+e);
			    System.out.println("-----------------");
				System.out.println("-Pokračujte stisknutím klávesy ENTER-");
				System.out.println("-----------------");
				sc.nextLine();
			}
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
			catch(InputMismatchException n) //pro špatné zadání čísla
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