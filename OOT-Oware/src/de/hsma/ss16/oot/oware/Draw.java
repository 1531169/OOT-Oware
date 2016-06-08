package de.hsma.ss16.oot.oware;

class Draw {

	private static int nr = 0;
	private Pitch pitch;
	private int startField;
	private int endField;
	private Player player;

	private int catched;

	public Draw(Player player, int start) {
		init(player, Game.getPitch(), start);
	}

	public Draw(Player player, Pitch pitch, int start) {
		init(player, pitch, start);
	}

	private void init(Player p, Pitch pi, int s) {
		setNr(getNr() + 1);
		this.player = p;
		this.setPitch(pi);
		this.startField = s;
		execute();
	}

	public void execute() {
		// saen
		endField = pitch.move(startField);
		// fangen
		catched = collect(pitch, startField);
		// sammeln
		player.addPoints(catched);
		if (player.getPoints() >= 25) {

		}
		boolean isDrawPossible = true;
		if (!isDrawPossible) {
			// TODO: verteile Punkte, setze Gewinner
		}
		// Zug zu ende
	}

	private int collect(Pitch pitch, int field) {
		int lastPos = endField;
		int catched = 0;
		if (lastPos >= 0) {
			// fangen
			if (field < 6 && lastPos >= 6) {
				catched += pitch.collect(lastPos, 6);
			} else if (field >= 6 && lastPos < 6) {
				catched += pitch.collect(lastPos, 0);
				// einsammeln
			}
		}
		return catched;
	}

	boolean isDrawValid(Player player, int hollow) {
		if(pitch.isDrawValid(player, hollow)){
			return true;
		}
		return false;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch startPitch) {
		this.pitch = startPitch;
	}

	/**
	 * @return the startField
	 */
	public int getStartField() {
		return startField;
	}

	/**
	 * @param startField
	 *            the startField to set
	 */
	public void setStartField(int startField) {
		this.startField = startField;
	}

	/**
	 * @return the endField
	 */
	public int getEndField() {
		return endField;
	}

	/**
	 * @param endField
	 *            the endField to set
	 */
	public void setEndField(int endField) {
		this.endField = endField;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the catched
	 */
	public int getCatched() {
		return catched;
	}

	/**
	 * @param catched
	 *            the catched to set
	 */
	public void setCatched(int catched) {
		this.catched = catched;
	}

	public static int getNr() {
		return nr;
	}

	public static void setNr(int nr) {
		Draw.nr = nr;
	}
}