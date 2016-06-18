package de.hsma.ss16.oot.oware;

class GameDraw extends Draw {
	private Player player;

	public GameDraw(Player player, int start) {
		super(Game.getPitch(), start);
		setPlayer(player);
		execute();
	}

	@Override
	void execute() {
		if (getPitch().isDrawValid(getPlayer(), getStartField())) {
			// saen
			setEndField(getPitch().move(getStartField()));
			// fangen
			setCatched(collect(getPitch(), getStartField()));
			// sammeln
			getPlayer().addPoints(getCatched());
		}
	}
	
	/**
	 * @return the player
	 */
	Player getPlayer() {
		return player;
	}
	
	/**
	 * @param player the player to set
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * @param endField the endField to set
	 */
	private void setEndField(int endField) {
		this.endField = endField;
	}

	/**
	 * @param catched the catched to set
	 */
	private void setCatched(int catched) {
		this.catched = catched;
	}
}