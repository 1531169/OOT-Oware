package de.hsma.ss16.oot.oware;

class Pitch {
	private int[] fields;

	public Pitch() {
		fields = new int[12];
		reset();
	}
	
	public Pitch(int[] fields) {
		setFields(fields);
	}
	
	public int move(int field) {
		return Pitch.move(this, field);
	}
	
	static int move(Pitch pitch, int field) {
		int lastField = -1;
		int start = field + 1;
		int size = pitch.getField(field);
		pitch.clear(field);
		/*
		 *  prüfen ob 11 ausgewählt wurden, danach muss
		 *  nämlich 0 belegt werden 
		 */
		if(start > (pitch.getLength() - 1)) {
			// start >= 11 somit 0 setzen
			start = 0;
		}
		
		// saen
		for(int i = start; i < pitch.getLength() && size > 0; ) {
			if(i != field) {
				lastField = i;
				pitch.add(i);
				size--;
			}
			if(i == (pitch.getLength() - 1)) {
				i = 0;
			} else {
				i++;
			}
		}
		return lastField;
	}
	
	public int collect(int lastField, int until) {
		return Pitch.collect(this, lastField, until);
	}
	
	static int collect(Pitch pitch, int endedField, int until) {
		int catched = 0;
		for(int i = endedField; i >= until; i--) {
			int f = pitch.getField(i);
			if(f == 2 || f == 3) {
				catched += f;
				pitch.clear(i);
			} else {
				return catched;
			}
		}
		return catched;
	}
	
	/*
	 * ----------------------------------------------------
	 */
	
	void reset() {
		// initial zustand des feldes
		for(int i = 0; i < fields.length; i++) {
			fields[i] = 4;
		}
	}
	
	int getField(int index) {
		return fields[index];
	}
	
	void add(int index) {
		fields[index] += 1;
	}
	
	void clear(int index) {
		fields[index] = 0;
	}
	
	int getLength() {
		return this.fields.length;
	}
	
	public int[] getFields() {
		return this.fields;
	}
	
	private void setFields(int[] fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {
		String result = "| ";
		for(int i = 5; i >= 0; i--) {
			result += getField(i) + " | ";
		}
		result += "\n| ";
		for(int i = 6; i < 12; i++) {
			result += getField(i) + " | ";
		}
		return result;
	}
	
	public Pitch getCopy() {
		return new Pitch(getFields().clone());
	}
}