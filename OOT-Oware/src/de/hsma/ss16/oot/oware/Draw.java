package de.hsma.ss16.oot.oware;

abstract class Draw {
	Pitch pitch;
	int startField;
	int endField;
	int catched;
	
	abstract void execute();
	
	Draw(Pitch pitch, int start) {
		setPitch(pitch);
		setStartField(start);
	}
	
	protected int collect(Pitch pitch, int field) {
		int lastPos = getEndField();
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
		if (pitch.isDrawValid(player, hollow)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return the pitch
	 */
	final Pitch getPitch() {
		return pitch;
	}

	/**
	 * @return the startField
	 */
	final int getStartField() {
		return startField;
	}

	/**
	 * @return the endField
	 */
	final int getEndField() {
		return endField;
	}

	/**
	 * @return the catched
	 */
	final int getCatched() {
		return catched;
	}
	
	/**
	 * @param pitch the pitch to set
	 */
	private void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	/**
	 * @param startField the startField to set
	 */
	private void setStartField(int startField) {
		this.startField = startField;
	}
}
