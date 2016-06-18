package de.hsma.ss16.oot.oware;

class SimulatedDraw extends Draw {

	SimulatedDraw(Pitch pitch, int start) {
		super(pitch, start);
		execute();
	}

	@Override
	void execute() {
		// saen
		setEndField(getPitch().move(getStartField()));
		// fangen
		setCatched(collect(getPitch(), getStartField()));
		// sammeln
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