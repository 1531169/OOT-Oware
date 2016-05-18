package de.hsma.ss16.oot.oware;

class Player {
	private int depot;
	private String name;

	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * @return the depot
	 */
	int getDepot() {
		return depot;
	}

	/**
	 * @param depot the depot to set
	 */
	void setDepot(int depot) {
		this.depot = depot;
	}

	/**
	 * @return the name
	 */
	String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	void setName(String name) {
		this.name = name;
	}
}