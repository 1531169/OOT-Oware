package de.hsma.ss16.oot.oware;

public class Game {
	
	private Settings settings;

	public Game() {
		this.settings = new Settings();
	}
	
	public static void main(String[] args) {

	}
	
	void init() {
		
	}
	
	void run() {
		
	}
	
	boolean isFinished() {
		// TODO: check whether the game is finished or not
		return true;
	}
	
	void reset() {
		// TODO: reset the game(finished)
	}
	
	/**
	 * @return the settings
	 */
	Settings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	void setSettings(Settings settings) {
		this.settings = settings;
	}
}
