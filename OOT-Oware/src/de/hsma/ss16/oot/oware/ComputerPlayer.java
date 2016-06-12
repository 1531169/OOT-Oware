package de.hsma.ss16.oot.oware;

class ComputerPlayer extends Player {
	private static String NAME = "Computer";
	private int difficulty;
	private ArtificialIntelligence ki;

	ComputerPlayer(int difficulty) {
		super(NAME);
		this.difficulty = difficulty;
		this.ki = new ArtificialIntelligence(getDifficulty(), Game.getPitch());
	}

	@Override
	Draw doDraw() {
		return new Draw(this, getField());
	}
	
	@Override
	protected int getField() {
		// TODO implementation
		return -1;
	}
	
	int getDifficulty() {
		return this.difficulty;
	}
}
