package de.hsma.ss16.oot.oware;

import java.security.InvalidParameterException;
import java.util.ArrayList;

class ArtificialIntelligence {
	private static final String EX_MSG_NULL_PARAM = "Das übergebene Feld ist null.";
	private static final String EX_MSG_OUT_OF_RANGE = "Der übergebene Schwierigkeitsgrad ist "
			+ "kleiner  1 oder größer 3.";
	private ArrayList<DrawTreeNode> nodes;
	private Pitch pitch;
	private int deep;
	
	ArtificialIntelligence(int difficulty, Pitch currentPitch) {
		if(currentPitch == null) {
			throw new InvalidParameterException(EX_MSG_NULL_PARAM);
		}
		if(difficulty < 1 || difficulty > 3) {
			throw new InvalidParameterException(EX_MSG_OUT_OF_RANGE);
		}
		setNodes(new ArrayList<>());
		this.pitch = currentPitch;
		this.deep = difficulty;
	}
	
	private void calc(int deep, int level) {
		for (int i = 6; i < 12; i++) {
			// only make draw if in the field are balls
			if (pitch.getFields()[i] > 0) {
				SimulatedDraw draw = new SimulatedDraw(pitch.getCopy(), i);
				DrawTreeNode node = new DrawTreeNode(-1, i, draw.getCatched());
				node.calc(deep, level + 1, draw.getPitch());
				getNodes().add(node);
			}
		}
	}
	
	/**
	 * Calculates the maximum point that the computer can 
	 * with the current tree.
	 * 
	 * @return	max point
	 */
	int getMax() {
		int max = 0;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				int minmax = node.getMax();
				if(minmax > max) {
					max = node.getMax();
				}
			}
		}
		return max;
	}
	
	int getBestDraw() {
		calc(getDeep(), 0);
		DrawTreeNode bestNode = null;
		int max = -1;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				int minmax = node.getMax();
				if(minmax > max) {
					bestNode = node;
					max = node.getMax();
				}
			}
		}
		setNodes(new ArrayList<>());
		return bestNode.getOwnPlayedField();
	}
	
	/**
	 * Returns the deep of the tree./Counts the levels.
	 * 
	 * @return	deep of the tree.
	 */
	int deep() {
		int max = 0;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				if(node.getDeep() > max) {
					max = node.getDeep();
				}
			}
		}
		return max;
	}
	
	/**
	 * Returns the count of all elements in the tree.
	 * 
	 * @return	count of element in tree
	 */
	int size() {
		int sum = 0;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				sum += node.size();
			}
		}
		return sum;
	}
	
	/**
	 * @return the tree
	 */
	ArrayList<DrawTreeNode> getNodes() {
		return nodes;
	}

	/**
	 * Defines the maximum level of the tree. 
	 * @return	maximum level of the tree.
	 */
	int getDeep() {
		return deep;
	}

	/**
	 * @param nodes the nodes to set
	 */
	private void setNodes(ArrayList<DrawTreeNode> nodes) {
		this.nodes = nodes;
	}

}