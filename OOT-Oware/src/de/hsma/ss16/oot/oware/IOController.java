package de.hsma.ss16.oot.oware;

import java.util.Scanner;

// TODO: Add JavaDoc
class IOController {

	private static Scanner sc = new Scanner(System.in);
	
	private static final String TXT_WELCOME = "Herzlich Willkommen zum Spiel Oware!\n" +
					" Sie können nach Spielstart das Spiel jederzeit\n" +
					" über die Eingabe von \"fertig\" beenden!\n";
	
	private static final String TXT_ENTER_NAME = "Bitte geben Sie ihren Namen ein:";
	
	private static final String TXT_ENTER_MODE = "Welchen Modus möchten Sie spielen?" +
			"\n Geben Sie einen der folgenden Modi ein: ";
	
	private static final String TXT_ENTER_DIFFICULTY = "Auf welchem Schwierigkeitsgrad soll " +
				"der Computer sein? \n 1 = Leicht ; 2 = Mittel ;" +
				" 3 = Schwer";
	
	private static final String TXT_ENTER_FIELD = "\nWählen Sie die Mulde aus, die sie" +
		" spielen wollen (a - f):";
	
	private static final String TXT_INPUT_NOT_VALID = "Eingabe ungültig\n";
	
	private static final String TXT_INPUT_MORE_THAN_ONE_SIGN = "Eingabe darf nur aus einem Zeichen bestehen!";
	
	private static final String TXT_DRAW_NOT_VALID = "Diese Mulde dürfen Sie" +
					" nicht spielen!\n";
	
	private static final String TXT_ANOTHER_ROUND = "Möchten Sie eine Revanche?\n (ja für Revanche)";
	
	private static final String YES = "ja";
	
	private static final String TXT_DO_INPUT = "> ";
	
	private static final String ONESTR = "1", TWOSTR = "2", THREESTR = "3";
	
	private static final int ONE = 1, TWO = 2, THREE = 3, MINUS_ONE = -1;
	
	private static final String TXT_TIE = "Unentschieden";
	
	private static final String TXT_WINNER = " hat gewonnen! \nHerzlichen Glückwunsch!";
	
	private static final String TXT_ON_DRAW = " ist jetzt am Zug";
	
	private static final String TXT_PLAYER_ONE = "Spieler 1, ";
	
	private static final String TXT_PLAYER_TWO = "Spieler 2, ";
	
	private static final String TXT_PLAYER_UNKNOWN = "Spieler, ";
	
	private static final String TXT_CANCELED = "fertig";
	
	private static final String TXT_GAME_CANCELED = " hat das Spiel abgebrochen";
	
	private static final String TXT_ENDLESS_LOOP = "Das Spiel scheint sich in einer Endlosschleife " +
					"zu befinden.\nMöchten Sie es beenden?\n (ja für beenden)\n";
	
		
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
		
		if(checkCanceled(inputStr)) {
			return MINUS_ONE;
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
		
		if(GameDraw.isDrawValid(player, field, Game.getPitch())) {
			return field;
		}
		
		else {
			System.out.println(TXT_DRAW_NOT_VALID);
			return getField(player);
		}
	}
	
	static boolean getAnotherRound() {
		String input;
		
		System.out.println(TXT_ANOTHER_ROUND);
		System.out.print(TXT_DO_INPUT);
		
		try{
			input = sc.nextLine();
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getAnotherRound();
		}
		
		if(input.equals(YES)) {
			return true;
		}
		return false;
	}
	
	static boolean getEndlessLoopConfirm() {
		String input;
		
		System.out.println(TXT_ENDLESS_LOOP);
		System.out.print(TXT_DO_INPUT);
		
		try{
			input = sc.nextLine();
		}
		catch(Exception e) {
			System.out.println(TXT_INPUT_NOT_VALID);
			return getAnotherRound();
		}
		
		if(input.equals(YES)) {
			return true;
		}
		return false;
	}
	
	static void printWelcome() {
		System.out.println(TXT_WELCOME);
	}
	
	static void printPitch() {
		System.out.println(Game.getPitch());
	}
	
	static void printTie() {
		System.out.println(TXT_TIE);
	}
	
	static void printWinner(Player player) {
		System.out.println(player.getName() + TXT_WINNER);
	}
	
	static void printOnDraw(Player player) {
		System.out.println("\n" + player.getName() + TXT_ON_DRAW);
	}
	
	static void printPlayer(int nr) {
		if(nr == ONE) {
			System.out.print(TXT_PLAYER_ONE);
		}
		else if(nr == TWO) {
			System.out.print(TXT_PLAYER_TWO);
		}
		else{
			System.out.print(TXT_PLAYER_UNKNOWN);
		}
	}
	
	static void printCanceled(Player player) {
		System.out.println("\n" + player.getName() + TXT_GAME_CANCELED);
	}
	
	private static boolean checkCanceled(String str) {
		if(str.equals(TXT_CANCELED)) {
			return true;
		}
		return false;
	}
	
	private static void showModes() {		
		for(Mode mode : Mode.values()) {
			System.out.print(mode + " ");
		}
		System.out.println();
	}
}
