package de.hsma.ss16.oot.oware;

import java.util.ArrayList;
import java.util.HashMap;

class Game {
	private static final Pitch pitch  = new Pitch();;
	private static Player onWait;
	private static Player onDraw;
	private static Player winner;
	private static boolean isFinished;
	private static boolean isCanceled;
	private static int lowFilledDrawsNr = 0;
	private static HashMap<Character, Integer> inputMap1;
	private static HashMap<Character, Integer> inputMap2;
	private static ArrayList<GameDraw> drawlist;
	Player player1;
	Player player2;

	public Game() {
		init();
		play();
	}

	private void init() {
		initMaps();
		pitch.reset();
		drawlist = new ArrayList<>();
		isFinished = false;
		IOController.printWelcome();
		Mode inGameMode = IOController.getMode();

		switch (inGameMode) {
		case PVC:
			IOController.printPlayer(1);
			player1 = new HumanPlayer(IOController.getName(), inputMap1);
			player2 = new ComputerPlayer(IOController.getDifficulty());
			player1.setRange(0, 6);
			player2.setRange(6, 12);
			break;
		case PVP:
			IOController.printPlayer(1);
			player1 = new HumanPlayer(IOController.getName(), inputMap1);
			IOController.printPlayer(2);
			player2 = new HumanPlayer(IOController.getName(), inputMap2);
			player1.setRange(0, 6);
			player2.setRange(6, 12);
			break;
		}

		// startenden Spieler ermitteln
		int startTurn = (int) (Math.random() * 10);

		if (startTurn < 5) {
			setOnDraw(player1);
			setOnWait(player2);
		} else {
			setOnWait(player1);
			setOnDraw(player2);
		}
	}

	private void play() {
		while (!isFinished) {
			IOController.printPitch(player1,player2);
			playTurn(onDraw);
			nextPlayer();
		}
		if (!isCanceled) {
			showResult();
		}
		anotherRound();
	}

	private void playTurn(Player player) {

		if (!pitch.isNeverReachable(player)) {
			IOController.printOnDraw(player);
			GameDraw draw = player.doDraw();

			if (draw == null) {
				setCanceled(player);
			} else {
				drawlist.add(draw);
			}
			checkFinished(player);
		} else {
			setWinner(hasMorePoints());
			showResult();
		}
	}

	public static void main(String[] args) {
		new Game();
		IOController.printLOSER();
	}

	/**
	 * wechselt Spieler
	 */
	void nextPlayer() { 
		Player tmpPlayer = getOnDraw();
		setOnDraw(getOnWait());
		setOnWait(tmpPlayer);
	}

	private static void checkFinished(Player player) {
		if (player.getPoints() >= 25) {
			setWinner(player);
		}
		checkEndlessLoop();
	}

	private static void checkEndlessLoop() {
		if (pitchLowFilled()) {
			lowFilledDrawsNr++;
		}
		if (lowFilledDrawsNr == 5) {
			if (IOController.getEndlessLoopConfirm()) {
				setWinner(hasMorePoints());
			}
		}
		if (lowFilledDrawsNr == 10) {
			setWinner(hasMorePoints());
		}
	}

	private static boolean pitchLowFilled() {
		if (getPitch().getAmountTotal() <= 4) {
			return true;
		}
		return false;
	}

	private static Player hasMorePoints() {
		if (getOnDraw().getPoints() > getOnWait().getPoints()) {
			return getOnDraw();
		} else if (getOnWait().getPoints() > getOnDraw().getPoints()) {
			return getOnWait();
		} else {
			return null;
		}
	}

	private static void showResult() {
		if (getWinner() == null) {
			IOController.printTie();
		} else {
			IOController.printWinner(getWinner());
		}
	}

	private void anotherRound() {
		if (IOController.getAnotherRound()) {
			reset();
		}
	}

	void reset() {
		init();
		play();
	}

	private void initMaps() {
		inputMap1 = new HashMap<>();
		inputMap2 = new HashMap<>();

		inputMap1.put('A', 0);
		inputMap1.put('B', 1);
		inputMap1.put('C', 2);
		inputMap1.put('D', 3);
		inputMap1.put('E', 4);
		inputMap1.put('F', 5);

		inputMap2.put('a', 6);
		inputMap2.put('b', 7);
		inputMap2.put('c', 8);
		inputMap2.put('d', 9);
		inputMap2.put('e', 10);
		inputMap2.put('f', 11);

	}

	public static Player getOnDraw() {
		return onDraw;
	}

	public static void setOnDraw(Player onDraw) {
		Game.onDraw = onDraw;
	}

	public static Pitch getPitch() {
		return pitch;
	}

	public static Player getOnWait() {
		return onWait;
	}

	public static void setOnWait(Player onWait) {
		Game.onWait = onWait;
	}

	public static Player getWinner() {
		return winner;
	}

	private static void setWinner(Player player) {
		winner = player;
		isFinished = true;
	}

	private static void setCanceled(Player player) {
		isCanceled = true;
		isFinished = true;
		IOController.printCanceled(player);
	}
}