package de.hsma.ss16.oot.oware;

import java.util.Scanner;

// TODO: Add JavaDoc
class IOController {

	private static Scanner sc = new Scanner(System.in);
	
	private static final String TXT_ENTER_NAME = "Bitte geben Sie ihren Namen ein:";
	
	private static final String TXT_ENTER_MODE = "Welchen Modus möchten Sie spielen?" +
			"\n Geben Sie einen der folgenden Modi ein: ";
	
	private static final String TXT_ENTER_DIFFICULTY = "Auf welchem Schwierigkeitsgrad soll " +
				"der Computer sein? \n 1 = Leicht ; 2 = Mittel ;" +
				" 3 = Schwer";
	
	private static final String TXT_ENTER_FIELD = "Wählen Sie die Mulde aus, die sie" +
		" spielen wollen (a - f):";
	
	private static final String TXT_INPUT_NOT_VALID = "Eingabe ungültig\n";
	
	private static final String TXT_INPUT_MORE_THAN_ONE_SIGN = "Eingabe darf nur aus einem Zeichen bestehen!";
	
	private static final String TXT_DRAW_NOT_VALID = "Diese Mulde dürfen Sie" +
					" nicht spielen!\n";
	
	private static final String TXT_DO_INPUT = "> ";
	
	private static final String ONESTR = "1", TWOSTR = "2", THREESTR = "3";
	
	private static final int ONE = 1, TWO = 2, THREE = 3;
	
		
	public IOController() {

	}
	
	static Mode getMode() {
		String input;

		System.out.println(TXT_ENTER_MODE);
		showModes();
		System.out.print(TXT_DO_INPUT);
		
		try{
			input = sc.nextLine();
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getMode();
		}
				
		for(Mode mode : Mode.values()) {
			if(input.equals(mode.getMode())) {
				return mode;
			}
			if(input.toUpperCase().equals(mode.getMode())) {
				return mode;
			}
		}
		System.out.println(TXT_INPUT_NOT_VALID);
		return getMode();
	}
	
	static String getName() {
		String input;
		
		System.out.println(TXT_ENTER_NAME);
		System.out.print(TXT_DO_INPUT);
		
		try{
			input = sc.nextLine();
			return input;
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getName();
		}
	}
	
	static int getDifficulty() {
		String input;
		
		System.out.println(TXT_ENTER_DIFFICULTY);
		System.out.print(TXT_DO_INPUT);
		
		try{
			input = sc.nextLine();			
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getDifficulty();
		}
		
		if(input.equals(ONESTR)) {
			return ONE;
		}
		else if(input.equals(TWOSTR)) {
			return TWO;
		}
		else if(input.equals(THREESTR)) {
			return THREE;
		}
		else {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getDifficulty();
		}
	}
	
	static int getField(HumanPlayer player) {
		char input;
		String inputStr;
		
		System.out.println(TXT_ENTER_FIELD);
		System.out.print(TXT_DO_INPUT);
		
		try {
			inputStr = sc.nextLine();
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getField(player);
		}
		
		if(!(inputStr.length() == 1)) {
			System.out.println(TXT_INPUT_MORE_THAN_ONE_SIGN);
			return getField(player);
		}
		
		if(player.getInputMap().containsKey(inputStr.charAt(0))) {
			input = inputStr.charAt(0);
		}
		else if(player.getInputMap().containsKey(inputStr.toUpperCase().charAt(0))) {
			input = inputStr.toUpperCase().charAt(0);
		}
		else {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getField(player);
		}
		
		int field = player.getInputMap().get(input);
		
		if(Draw.isDrawValid(player, field, Game.getPitch())) {
			return field;
		}
		
		else {
			System.out.println(TXT_DRAW_NOT_VALID);
			return getField(player);
		}
	}
	
	private static void showModes() {		
		for(Mode mode : Mode.values()) {
			System.out.print(mode + " ");
		}
		System.out.println();
	}
}
