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
}