package de.hsma.ss16.oot.oware;

import java.util.ArrayList;

public class KI {
	private DrawTreeNode[] nodes;
	private Pitch pitch;
	private int deep;
	private Player computer;
	// private Player opponent!!!!
	
	public KI(int difficulty, Pitch currentPitch, Player player) {
		computer = player;
		this.nodes = new DrawTreeNode[6];
		this.pitch = currentPitch;
		this.setDeep(2 * difficulty);
	}
	
	public int getNextDraw() {
		calc(getDeep(), 0);
		ArrayList<Draw> way = getBestWay();
		for (Draw draw : way) {
			System.out.println(draw.getPitch());
		}
		if(way.size() > 0) {
			return way.get(0).getStartField();
		}
		return -1;
	}
	
	public void calc(int deep, int level) {
		//System.out.println(deep + " " +  level);
		int index = 0;
		for (int i = 0; i < 6; i++, index++) {
			// only make draw if in the field are balls
			if (pitch.getFields()[i] > 0) {
				Draw draw = new Draw(new HumanPlayer("MAX"), pitch.getCopy(), i);
				//System.out.println(draw.getPitch());
				DrawTreeNode node = new DrawTreeNode(draw);
				node.calc(deep, level + 1);
				nodes[index] = node;
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
	private ArrayList<Draw> getBestWay() {
		ArrayList<Draw> way = new ArrayList<>();
		int condition = getMax();
		int sum = 0;
		for(DrawTreeNode node : getNodes()) {
			int tmpSum = 0;
			if(node != null) {
				ArrayList<Draw> tmpWay = node.getWay(computer.getPoints(), condition);
				for (Draw draw : tmpWay) {
					tmpSum += draw.getCatched();
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
	public boolean isPossibleToWin() {
		boolean isWinable = false;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				if(node.isWinnableInTree(computer.getPoints())) {
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
	public int getMax() {
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
	
	/**
	 * Returns the deep of the tree./Counts the levels.
	 * 
	 * @return	deep of the tree.
	 */
	public int deep() {
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
	public int size() {
		int sum = 0;
		for(DrawTreeNode node : getNodes()) {
			if(node != null) {
				sum += node.size();
			}
		}
		return sum;
	}
	
	/**
	 * Shows the current best way. THIS IS ONLY FOR TESTS!
	 */
	public void showBestWay() {
		for(Draw draw : getBestWay()) {
			System.out.println(draw.getPlayer().getName() + ": ");
			System.out.println("Punkte des Zuges: " + draw.getCatched());
			System.out.println(draw.getPitch());
			System.out.println("\n-----------------------------------\n");
		}
	}
	
	/*
	 * ----------------------------------------------------
	 * GETTER / SETTER
	 * ----------------------------------------------------
	 */
	
	/**
	 * @return the tree
	 */
	public DrawTreeNode[] getNodes() {
		return nodes;
	}
	
	/**
	 * Sets the nodes
	 */
	public void getNodes(DrawTreeNode[] nodes) {
		this.nodes = nodes;
	}

	/**
	 * Defines the maximum level of the tree. 
	 * @return	maximum level of the tree.
	 */
	public int getDeep() {
		return deep;
	}

	/**
	 * Set the max level of the tree for calculation.
	 * 
	 * @param deep max level
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}

	/**
	 * @param nodes the nodes to set
	 */
	void setNodes(DrawTreeNode[] nodes) {
		this.nodes = nodes;
	}

}