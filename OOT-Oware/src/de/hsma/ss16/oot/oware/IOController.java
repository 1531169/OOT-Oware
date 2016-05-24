package de.hsma.ss16.oot.oware;

import java.util.Scanner;

class IOController {

	private Scanner sc;
	
	private static final int PLAYER1_SHIFT = -1;
	private static final int PLAYER2_SHIFT = 5;
	
	public IOController() {
		sc = new Scanner(System.in);
	}
	
	Mode getMode() {
		String input;

		System.out.println("Welchen Modus möchten Sie spielen?" +
			"\n Geben Sie einen der folgenden Modi ein: ");
		showModes();
		System.out.print("> ");
		input = sc.nextLine();
		
		for(Mode mode : Mode.values()) {
			if(input.equals(mode.getMode())) {
				return mode;
			}
		}
		System.out.println("Eingabe ungültig\n");
		return getMode();
	}
	
	String getName() {
		System.out.println("Bitte geben Sie ihren Namen ein:");
		System.out.print("> ");
		return sc.nextLine();
	}
	
	// TODO: Exception Handling: Eingabe kein Integer
	int getDifficulty() {
		int input;
		
		System.out.println("Auf welchem Schwierigkeitsgrad soll" +
				"der Computer sein? \n 1 = Leicht ; 2 = Mittel ;" +
				" 3 = Schwer");
		System.out.print("> ");
		input = sc.nextInt();
		
		if(input >= 1 && input <= 3) {
			return input;
		}
		
		System.out.println("Eingabe ungültig\n");
		return getDifficulty();
	}
	
	// TODO: Exception Handling: Eingabe kein Integer
	// TODO: Exception: Übergebener Spieler ist nicht 1 oder 2 (nur maximal 2 Spieler möglich)
	int getHollow(int player) {
		int input;
		
		System.out.println("Wählen Sie die Mulde aus, die sie" +
		" spielen wollen (1 - 6)");
		System.out.print("> ");
		input = sc.nextInt();
		
		if(input < 1 || input > 6) {
			System.out.println("Eingabe ungültig\n");
			return getHollow(player);
		}
		
		if(player == 1) {
			int hollow = input + PLAYER1_SHIFT;
			if(new Draw().isValidDraw(hollow)) {
				return hollow;
			}
			else{
				System.out.println("Diese Mulde dürfen Sie" +
						" nicht spielen!\n");
				return getHollow(player);
			}
		}
		
		if(player == 2) {
			int hollow = input + PLAYER2_SHIFT;
			if(new Draw().isValidDraw(hollow)) {
				return hollow;
			}
			else{
				System.out.println("Diese Mulde dürfen Sie" +
						" nicht spielen!\n");
				return getHollow(player);
			}
		}
		
		System.out.println("Interner Fehler! Übergebener Parameter muss 1 oder 2 sein");
		return -1;
	}
	
	private void showModes() {		
		for(Mode mode : Mode.values()) {
			System.out.print(mode + " ");
		}
		System.out.println();
	}
}
