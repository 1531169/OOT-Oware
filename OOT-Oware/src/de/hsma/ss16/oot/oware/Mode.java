package de.hsma.ss16.oot.oware;

public enum Mode {
	PVP("PVP"),
	PVC("PVC");
	
	private String mode;
	
	private Mode(String mode) {
		this.mode = mode;
	}
	
	public String getMode() {
		return mode;
	}
	
	@Override
	public String toString() {
		return getMode();
	}
}
