package de.hsma.ss16.oot.oware;

class Game {
	private static final Pitch pitch = new Pitch();
	private static Player player1;
	private static Player player2;
	private static Player onWait;
	private static Player onDraw;
	private static Player winner;
	private static boolean isRunning;

	public Game() {
		init();
	}

	void init() {
		// TODO: Abfrage in Konsole
		Mode inGameMode = Mode.PVC;
		String inPlayer1 = "Cedric";
		String inPlayer2 = "Tobias";
		isRunning = true;

		switch (inGameMode) {
			case PVC:
				player1 = new ComputerPlayer();
				player2 = new HumanPlayer(inPlayer2);
				player2.setRange(6, 12);
				break;
			case PVP:
				player1 = new HumanPlayer(inPlayer1);
				player2 = new HumanPlayer(inPlayer2);
				player1.setRange(0, 6);
				player2.setRange(6, 12);
				break;
			default:
				// TODO: throw exception
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

		while (isRunning) {
			//onDraw.doDraw(getPitch());
			System.out.println(onDraw.getName());
			nextPlayer();
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}

	/**
	 * wechselt Spieler
	 */
	void nextPlayer() {
		Player tmpPlayer = onDraw;
		setOnDraw(getOnWait());
		setOnWait(tmpPlayer);
	}
	
	void reset() {
		init();
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
}