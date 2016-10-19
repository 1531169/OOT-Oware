package de.hsma.ss16.oot.oware;

abstract class Player {
	private String name;
	private int points = 0;
	private int[] range = new int[2];

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartRange() {
		return this.range[0];
	}

	public void setRange(int start, int end) {
		this.range[0] = start;
		this.range[1] = end;
	}

	public int getEndRange() {
		return this.range[1];
	}

	public int getPoints() {
		return this.points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	abstract GameDraw doDraw();

	abstract protected int getField();

	@Override
	public String toString() {
		int score = 1;
		if (points >= 10) {
			score = 2;
		}
		int laenge = (25 - name.length() - 4 - score) / 2;
		
		String side = "";
		for (int i = 0; i < laenge; i++) {
			side += "-";
		}
		String space = "";
		if((name.length()%2)!=0){
			space = " ";
		}
		return side + "|" + name + space + "(" + points + ")|" + side;
	}
}