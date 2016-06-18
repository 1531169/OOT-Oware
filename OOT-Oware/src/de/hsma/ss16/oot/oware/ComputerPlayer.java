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
	GameDraw doDraw() {
		return new GameDraw(this, getField());
	}
	
	@Override
	protected int getField() {
		return ki.getBestDraw();
	}
	
	int getDifficulty() {
		return this.difficulty;
	}
}
