package de.hsma.ss16.oot.oware;

import java.util.HashMap;

class HumanPlayer extends Player {

	private HashMap<Character, Integer> inputMap;
	
	public HumanPlayer(String name, HashMap<Character, Integer> inputMap) {
		super(name);
		this.inputMap = inputMap;
	}

	@Override
	Draw doDraw(Pitch pitch) {
		// TODO Auto-generated method stub
		return null;
	}
	
	HashMap<Character, Integer> getInputMap() {
		return this.inputMap;
	}
}
