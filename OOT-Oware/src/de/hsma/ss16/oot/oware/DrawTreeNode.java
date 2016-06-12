package de.hsma.ss16.oot.oware;

import java.util.ArrayList;

class DrawTreeNode {
	/**
	 * Calculated subnodes of this node which contains simulated
	 * posibility of next draws.
	 */
	private ArrayList<DrawTreeNode> nodes;
	
	private int priorPlayedField = 0;
	private int catched = 0;
	private int ownPlayedField;
	
	/**
	 * Contructor of the single Node.
	 * 
	 * @param ownDraw	executed draw
	 */
	DrawTreeNode(int prior, int own, int catched) {
		setPriorPlayedField(prior);
		setOwnPlayedField(own);
		setCatched(catched);
		setNodes(new ArrayList<>());
	}
	
	/**
	 * Creates the subnodes in recursice way.
	 * 
	 * @param deep	max level of the tree
	 * @param level	current level of calculation
	 */
	void calc(int deep, int level, Pitch pitch) {
		// gegnerseite
		int opStart = 0;
		int opEnd = 6;
		// eigene seite
		int ownStart = 6;
		int ownEnd = 12;
		
		if (deep > level) {
			for (int i = opStart; i < opEnd; i++) {
				if (pitch.getField(i) < 1) {
					// keine Kugeln vorhanden
					continue;
				}
				// vorausgehender Zug
				SimulatedDraw priorDraw = new SimulatedDraw(pitch.getCopy(), i);
				for (int j = ownStart; j < ownEnd; j++) {
					if (priorDraw.getPitch().getField(j) < 1) {
						// keine Kugeln vorhanden
						continue;
					}
					SimulatedDraw ownDraw = new SimulatedDraw(priorDraw.getPitch(), j);
					int points = ownDraw.getCatched() - priorDraw.getCatched();
					DrawTreeNode subNode = new DrawTreeNode(i, j, points);
					subNode.calc(deep, level + 1, ownDraw.getPitch());
					getNodes().add(subNode);
				}
			}
		}
	}

	/**
	 * @see look at static DrawTreeNode.getMax(DrawTreeNode node).
	 * @return max points which are getable
	 */
	int getMax() {
		return DrawTreeNode.getMax(this);
	}

	/**
	 * Calculates the maximum of the points that the computer can win in the
	 * given tree.
	 * 
	 * @param node
	 *            to search
	 * @return max points
	 */
	static int getMax(DrawTreeNode node) {
		int max = node.getCatched();

		int tmpMax = 0;
		for (DrawTreeNode subNode : node.getNodes()) {
			if (subNode != null) {
				int minmax = subNode.getMax();
				if (minmax > tmpMax) {
					tmpMax = subNode.getMax();
				}
			}
		}

		return (max + tmpMax);
	}

	/**
	 * Calculates the deep of the complete tree.
	 * 
	 * @return deep of the tree.
	 */
	int getDeep() {
		int max = 0;
		for (DrawTreeNode node : getNodes()) {
			if (node != null) {
				int newMax = node.getDeep();
				if (max < newMax) {
					max = newMax;
				}
			}
		}
		return 1 + max;
	}

	/**
	 * Calculates how many draws are in the tree.
	 * 
	 * @return sum of all elements
	 */
	int size() {
		int size = 1;
		for (DrawTreeNode node : getNodes()) {
			if (node != null) {
				size += node.size();
			}
		}
		return size;
	}

	/**
	 * Checks whether the node has subnodes.
	 * 
	 * @return true if there is any subnode otherwise false
	 */
	boolean hasElements() {
		for (DrawTreeNode node : getNodes()) {
			if (node == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gives a node from the subnodes by the index.
	 * 
	 * @param index
	 *            position from the wanted subnode
	 * @return node from subnodes
	 */
	DrawTreeNode getNode(int index) {
		try {
			return getNodes().get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * @return subnodes
	 */
	ArrayList<DrawTreeNode> getNodes() {
		return nodes;
	}

	/**
	 * Set the draws.
	 * 
	 * @param nodes
	 *            with draw, maybe with subnodes
	 */
	void setNodes(ArrayList<DrawTreeNode> nodes) {
		this.nodes = nodes;
	}

	int getPriorPlayedField() {
		return priorPlayedField;
	}

	void setPriorPlayedField(int priorPlayedField) {
		this.priorPlayedField = priorPlayedField;
	}
	
	/**
	 * @return the catched
	 */
	int getCatched() {
		return catched;
	}

	/**
	 * @param catched the catched to set
	 */
	void setCatched(int catched) {
		this.catched = catched;
	}

	/**
	 * @return the ownPlayedField
	 */
	int getOwnPlayedField() {
		return ownPlayedField;
	}

	/**
	 * @param ownPlayedField the ownPlayedField to set
	 */
	void setOwnPlayedField(int ownPlayedField) {
		this.ownPlayedField = ownPlayedField;
	}
}