package de.hsma.ss16.oot.oware;

class ComputerPlayer extends Player {
	private static String NAME = "Computer";
	private int difficulty;

	public ComputerPlayer(int difficulty) {
		super(NAME);
		this.difficulty = difficulty;
	}

	@Override
	Draw doDraw(Pitch pitch) {
		// TODO Auto-generated method stub
		return null;
	}
	
	int getDifficulty() {
		return this.difficulty;
	}
}
