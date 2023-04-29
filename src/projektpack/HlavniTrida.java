package projektpack; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HlavniTrida {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DatabazeFilmu databaze1 = new DatabazeFilmu();
		DatabazeSQL.nactiData(databaze1);
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
		System.out.println("-----------------------------------");
		System.out.println("11) Uložit a ukončit");
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
				HranyFilm movieHrany = new HranyFilm(nazev, rezie, rok, herci); 
				databaze1.addFilm(movieHrany);
				break;
			case 2:
				System.out.println(" Napište minimální doporučený věk diváka:");
				int vek=readInt(sc);
				System.out.println(" Napište jméno animátora nebo seznam jejich jmen (oddělený čárkami):");
				sc.nextLine();
				String animatori=readString(sc);
				databaze1.addFilmAnimovanyRAW(nazev, rezie, rok, vek, animatori); 
				break;	
			}
			System.out.println("------------------------------");
			System.out.println("Film byl vložen do databáze.");
			break;
		case 2:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete upravit:");
			Film vybranyFilm = readFilm(sc, databaze1);

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
				System.out.println(" Zadejte nový název filmu:");
				System.out.println(" test:"+vybranyFilm.getNazev());
				sc.nextLine();
				Film zamena = vybranyFilm;
				databaze1.deleteFilm(vybranyFilm.getNazev());
				zamena.setNazev(readString(sc));
				
				
				databaze1.addFilm(zamena);
				
				
				
				
			break;				
			case 2:
				System.out.println(" Zadejte novou režii filmu:");
				sc.nextLine();
				vybranyFilm.setRezie(readString(sc));
			break;
			case 3:
				System.out.println(" Zadejte nový rok filmu:");
				sc.nextLine();
				vybranyFilm.setRok(Integer.parseInt(readString(sc)));
			break;
			case 4:			
				sc.nextLine();
				if(vybranyFilm instanceof HranyFilm)
				{
					System.out.println(" Zadejte nový seznam herců oddělený čárkami:");
					((HranyFilm) vybranyFilm).vytvorListHercu(readString(sc));
				}
				else
				{
					System.out.println(" Zadejte nový seznam animátorů oddělený čárkami:");
					((AnimovanyFilm) vybranyFilm).vytvorListAnimatoru(readString(sc));
				}

				
			break;
			case 5:
				System.out.println(" Zadejte nový doporučený věk diváka:");
				sc.nextLine();
				((AnimovanyFilm) vybranyFilm).setDoporucenyVek(Integer.parseInt(readString(sc)));
			break;
			}
			break;
		case 3:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete smazat:");
			databaze1.deleteFilm(readFilm(sc,databaze1).getNazev());
			System.out.println(" Film byl vymazán z databáze.");
			break;
		case 4:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete ohodnotit:");
			Film vybranyFilm2=readFilm(sc,databaze1);
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
		case 5: 
			topDesign();
			sc.nextLine();
			
			for(Film i : databaze1.getMapa().values())
			{
				System.out.println(i.vypisFilmBezH()+"\n"); 
	
			}
			bottomDesign(sc);			
			break;
		case 6:
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete vyhledat:");			
			System.out.println(readFilm(sc,databaze1).vypisFilm());
			bottomDesign(sc);
			break;
		case 7:
			topDesign();
			sc.nextLine();
			System.out.println(databaze1.spolecniHerci());
			bottomDesign(sc);
			break;
		case 8:
			topDesign();
			sc.nextLine();
			System.out.println(" Zadejte jméno hledaného herce:");
			String jmenoHerce=readString(sc);
			System.out.println(" Filmy ve kterých hraje:");
			System.out.println(databaze1.getFilmyOsobyString(jmenoHerce));
			bottomDesign(sc);
			break;
		case 9:
			topDesign(); 
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete zapsat do souboru:");
			String nazevFilmu =readFilm(sc,databaze1).getNazev();
			File file = new File(System.getProperty("user.dir")+File.separator + "Filmy" + File.separator + nazevFilmu+".txt");
			file.getParentFile().mkdirs();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {			  
				bw.write(databaze1.getFilm(nazevFilmu).vypisFilmSouborJednoduse());
			    bw.flush();
			    System.out.println("Soubor "+nazevFilmu+".txt ve složce "+file.getParentFile()+" byl úspěšně vytvořen!");
			} catch (Exception e) {
			    System.out.println("Chyba při zápisu do souboru. "+e);
			}
			
			
			bottomDesign(sc);
			break;
		case 10: 
			topDesign();
			sc.nextLine();
			System.out.println(" Napište název filmu, který chcete přečíst ze souboru:");
			String nazevFilmu2 =readString(sc);
			File file2 = new File(System.getProperty("user.dir")+File.separator + "Filmy" + File.separator + nazevFilmu2+".txt");
			try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
			    String s;			   
			    List<String> soubor = new ArrayList<>();
			    while ((s = br.readLine()) != null) {			    	
			        String[] pole = s.split(":/ "); 
			        soubor.add(pole[1]);			        				      			     
			    }
			    databaze1.zpracujSouborJednoduse(soubor);			    
			        System.out.println("Film byl úspěšně načten.");
			        bottomDesign(sc);
			} catch (Exception e) {
			    System.out.println("Chyba při čtení ze souboru. "+e);
			    bottomDesign(sc);
			}
			break;
		case 11:			
			System.out.println("Probíhá ukládání do databáze Filmy.db");
			DatabazeSQL.SmazatObsahDatabaze();
			DatabazeSQL.vlozMapu(databaze1.getMapa());
			System.out.println("Ukončování.");
			konecProgramu=true;
			break;
		}}
		sc.close();
	}

	private static void bottomDesign(Scanner sc) {
		System.out.println("-----------------");
		System.out.println("-Pokračujte stisknutím klávesy ENTER-");
		System.out.println("-----------------");
		sc.nextLine();
	}

	public static int readInt(Scanner sc)
	{
		int cislo;
			try
			{
				cislo = sc.nextInt();
			}
			catch(InputMismatchException n) 
			{
				System.out.println("Zadejte prosím celé číslo.");
				sc.nextLine();
				cislo = readInt(sc);
			}
			return cislo;
	}
	public static Film readFilm(Scanner sc,DatabazeFilmu databaze)
	{
		Film vybranyFilm = databaze.getFilm(sc.nextLine());
		if(databaze.checkFilm(vybranyFilm)==true)
		{
			return vybranyFilm;
 		}
		else
		{
		
			System.out.println("Vybraný film nebyl nalezen v databázi. Zadejte název platného filmu.");
				return vybranyFilm=readFilm(sc, databaze);
		}
		
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

}