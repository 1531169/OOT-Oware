package de.hsma.ss16.oot.oware;

import java.util.ArrayList;

class KI {
	private ArrayList<DrawTreeNode> nodes;
	private Pitch pitch;
	private int deep;
	
	KI(int difficulty, Pitch currentPitch) {
		setNodes(new ArrayList<>());
		this.pitch = currentPitch;
		// maximum 5 calculation => 5 Draw of computer and 5 from human
		this.setDeep(difficulty + 2);
	}
	
	void calc(int deep, int level) {
		for (int i = 6; i < 12; i++) {
			// only make draw if in the field are balls
			if (pitch.getFields()[i] > 0) {
				SimulatedDraw draw = new SimulatedDraw(pitch.getCopy(), i);
				// System.out.println(draw.getPitch());
				DrawTreeNode node = new DrawTreeNode(-1, i, draw.getCatched());
				node.calc(deep, level + 1, draw.getPitch());
				getNodes().add(node);
			}
		}
	}
	
	/*
	 * !			!			!			!			!			!	 !
	 * -------------------------------------------------------------------
	 * Hier ist noch ein Bug drinnen. Beim suchen des besten weges gibt es
	 * noch einen Fehler!
	 * -------------------------------------------------------------------
	 */
	private ArrayList<Integer> getBestWay(int currentPoints) {
		ArrayList<Integer> way = new ArrayList<>();
		int condition = getMax();
		int sum = 0;
		for(DrawTreeNode node : getNodes()) {
			int tmpSum = 0;
			if(node != null) {
				ArrayList<Integer> tmpWay = node.getWay(currentPoints, condition);
				for (Integer draw : tmpWay) {
					//tmpSum += draw.getCatched();
				}
				if(tmpSum > sum) {
					sum = tmpSum;
					way = tmpWay;
				}
			}
		}
		return way;
	}
	
	/**
	 * Calculates whether if its possible to win the game in the current
	 * tree.
	 * 
	 * @return	boolean if computer can win
	 */
	boolean isPossibleToWin(int curPoints) {
		boolean isWinable = false;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				if(node.isWinnableInTree(curPoints)) {
					isWinable = true;
				}
			}
		}
		return isWinable;
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
		/* (-1), because the node "tree" is the root node
		 *  and is a draw which is already done.
		 */
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
	 * Set the max level of the tree for calculation.
	 * 
	 * @param deep max level
	 */
	void setDeep(int deep) {
		this.deep = deep;
	}

	/**
	 * @param nodes the nodes to set
	 */
	void setNodes(ArrayList<DrawTreeNode> nodes) {
		this.nodes = nodes;
	}

}