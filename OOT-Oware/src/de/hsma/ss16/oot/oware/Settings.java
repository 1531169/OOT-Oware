package de.hsma.ss16.oot.oware;

class Settings {
	
	private int view = 1;
	private int severity = 1;
	private Mode mode = Mode.PVC;

	public Settings() {
	}

	public Settings(int severity) {
		this.severity = severity;
	}
	
	public Settings(int severity, Mode mode) {
		this.severity = severity;
		this.mode = mode;
	}
	
	/**
	 * @return the view
	 */
	int getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	void setView(int view) {
		this.view = view;
	}
	
	/**
	 * @return the severity
	 */
	int getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	void setSeverity(int severity) {
		this.severity = severity;
	}

	/**
	 * @return the mode
	 */
	Mode getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	void setMode(Mode mode) {
		this.mode = mode;
	}
}