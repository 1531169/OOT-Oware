package de.hsma.ss16.oot.oware;

import java.security.InvalidParameterException;

class Pitch {
	private int[] fields;
	private static final int HALF_OF_FIELD_LENGTH = 6;
	private static final int TWO = 2;
	private static final int ZERO = 0;

	public Pitch() {
		fields = new int[12];
		reset();
	}

	public Pitch(int[] fields) {
		setFields(fields);
	}

	public int move(int field) {
		return distribute(field);
//		return Pitch.move(this, field);
	}

	static int move(Pitch pitch, int field) {
		int lastField = -1;
		int start = field + 1;
		int size = pitch.getField(field);
		pitch.clear(field);
		/*
		 * prüfen ob 11 ausgewählt wurden, danach muss nämlich 0 belegt werden
		 */
		if (start > (pitch.getLength() - 1)) {
			// start >= 11 somit 0 setzen
			start = 0;
		}

		// saen
		for (int i = start; i < pitch.getLength() && size > 0;) {
			if (i != field) {
				lastField = i;
				pitch.add(i);
				size--;
			}
			if (i == (pitch.getLength() - 1)) {
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
		for (int i = endedField; i >= until; i--) {
			int f = pitch.getField(i);
			if (f == 2 || f == 3) {
				catched += f;
				pitch.clear(i);
			} else {
				return catched;
			}
		}
		return catched;
	}
	
	private int distribute(int hollow) {
		int position = -1;
		if(this.fields[hollow] == ZERO){
			return position;
		}
		int hollowContents = this.fields[hollow];
		position = hollow + 1;
		for (int i = position; hollowContents > 0; i++) {
			position = i % fields.length;
			
			if (position != hollow) {
				hollowContents--;
				this.fields[position]++;
			}
		}
		this.fields[hollow] = hollowContents;
		return position;
	}

	/**
	 * this method verify if the draw is valid.
	 * 
	 * @param playerplayer
	 *            is the current player as Player.
	 * @param hollow
	 * @return
	 */
	boolean isDrawValid(Player player, int hollow) {
		if (hollow < player.getStartRange() || hollow >= player.getEndRange()) {
			// TODO: Exception is hier zu schmeißen.
			throw new InvalidParameterException();
		}

		if (this.fields[hollow] > 0) {
			if (this.isOppositeFieldEmpty(player)) {
				if (this.isOppositeFieldReachable(hollow)) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		}
		return false;
	}
	boolean isNeverReachable(Player player){
		int rest = 0;
		if(isOppositeFieldEmpty(player)){
			for(int i = player.getStartRange();i< player.getEndRange();i++){
				if(isOppositeFieldReachable(i)){
					return false;
				}
			}
			
			for(int i = player.getStartRange();i< player.getEndRange();i++){
				rest += this.fields[i];
				this.fields[i] = 0;
			}
			player.addPoints(rest);
		}
		return false;
	}
	/**
	 * this method verify if the opposite field is empty.
	 * 
	 * @param player
	 *            is the current player as Player.
	 * @return true if the opposite field is empty, else false.
	 */
	private boolean isOppositeFieldEmpty(Player player) {
		int[] array = this.getFields();
		int start;
		if (player.getEndRange() == HALF_OF_FIELD_LENGTH) {
			start = HALF_OF_FIELD_LENGTH;
		} else {
			start = ZERO;
		}
		for (int i = start; i < start + HALF_OF_FIELD_LENGTH; i++) {
			if (array[i] > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * this method validate the selected hollow if the opposite field is
	 * reachable when opposite field is empty
	 * 
	 * @param hollow
	 *            is the current hollow as integer;
	 * @return true if the selected hollow is valid.
	 */
	private boolean isOppositeFieldReachable(int hollow) {
		int minimumStep;
		if (hollow < 6) {
			minimumStep = HALF_OF_FIELD_LENGTH;
		} else {
			minimumStep = TWO * HALF_OF_FIELD_LENGTH;
		}

		if (this.fields[hollow] - (minimumStep - hollow) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * ----------------------------------------------------
	 */

	void reset() {
		// initial zustand des feldes
		for (int i = 0; i < getLength(); i++) {
			fields[i] = 4;
		}
	}
	
	int getAmountTotal() {
		int amount = 0;
		for (int i = 0; i < getLength(); i++) {
			amount += getField(i);
		}
		return amount;
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
		result = "\n- ";
		for(char i = 'f'; i >= 'a'; i--){
			result += i + " "+ "-" + " ";  
		}
		result += "\n| ";
		for (int i = 5; i >= 0; i--) {
			result += getField(i) + " | ";
		}
		result += "\n| ";
		for (int i = 6; i < 12; i++) {
			result += getField(i) + " | ";
		}
		result += "\n- ";
		for(char i = 'a'; i<= 'f'; i++){
			result += i+ " "+ "-" +" ";
		}
		return result;
	}

	public Pitch getCopy() {
		return new Pitch(getFields().clone());
	}
}