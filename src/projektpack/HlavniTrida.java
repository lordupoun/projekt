package projektpack;

import java.util.Scanner;

public class HlavniTrida {
	//TODO: Ošetřit switch
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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
		switch(readInt(sc)) 
		{
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		
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
}
