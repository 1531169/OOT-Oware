package de.hsma.ss16.oot.oware;

class SimulatedDraw {
	private Pitch pitch;
	private int startField;
	private int endField;

	private int catched;
	
	SimulatedDraw(Pitch pitch, int start) {
		init(pitch, start);
	}

	private void init(Pitch pi, int s) {
		setPitch(pi);
		setStartField(s);
		execute();
	}

	void execute() {
		// saen
		setEndField(pitch.move(startField));
		// fangen
		setCatched(collect(pitch, startField));
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

	static boolean isDrawValid(Player player, int hollow, Pitch pitch) {
		if(pitch.isDrawValid(player, hollow)){
			return true;
		}
		return false;
	}

	Pitch getPitch() {
		return pitch;
	}

	void setPitch(Pitch startPitch) {
		this.pitch = startPitch;
	}

	/**
	 * @return the startField
	 */
	int getStartField() {
		return startField;
	}

	/**
	 * @param startField
	 *            the startField to set
	 */
	void setStartField(int startField) {
		this.startField = startField;
	}

	/**
	 * @return the endField
	 */
	int getEndField() {
		return endField;
	}

	/**
	 * @param endField
	 *            the endField to set
	 */
	void setEndField(int endField) {
		this.endField = endField;
	}

	/**
	 * @return the catched
	 */
	int getCatched() {
		return catched;
	}

	/**
	 * @param catched
	 *            the catched to set
	 */
	void setCatched(int catched) {
		this.catched = catched;
	}
}