package de.hsma.ss16.oot.oware;

import java.util.ArrayList;

public class DrawTreeNode {
	/**
	 * Draw which is done in this node.
	 */
	private Draw draw;
	/**
	 * Calculated subnodes of this node which contains simulated
	 * posibility of next draws.
	 */
	private DrawTreeNode[] nodes;
	/**
	 * isMax = true means this node is in level of computer player otherwise it
	 * is a level for human player
	 */
	private boolean isMax;

	/**
	 * Contructor of the single Node.
	 * 
	 * @param draw	executed draw
	 */
	public DrawTreeNode(Draw draw) {
		this.setDraw(draw);
		this.setNodes(new DrawTreeNode[6]);
	}
	
	/**
	 * Creates the subnodes in recursice way.
	 * 
	 * @param deep	max level of the tree
	 * @param level	current level of calculation
	 */
	void calc(int deep, int level) {
		String name = "MAX";
		int start = 0;
		int end = 6;
		setMax(false);
		if (level % 2 == 0) {
			// aktuelle Node ist Computer-Zug
			// => for schleife für menschliche Züge
			name = "MIN";
			start = 6;
			end = 12;
			setMax(true);
		}
		if (deep > level) {
			int index = 0;
			for (int i = start; i < end; i++, index++) {
				// only make draw if in the field are balls
				if (getDraw().getPitch().getFields()[i] > 0) {
					Draw draw = new Draw(new HumanPlayer(name), getDraw().getPitch().getCopy(), i);
					DrawTreeNode subNode = new DrawTreeNode(draw);
					subNode.calc(deep, level + 1);
					setNodeByIndex(index, subNode);
				}
			}
		}
	}
	
	/*
	 * -----------------------------------------------------
	 * 
	 * Buggy Methoden!!
	 * -----------------------------------------------------
	 */

	public ArrayList<Draw> getWay(int curPoints, int winCondition) {
		ArrayList<Draw> way = new ArrayList<>();
		isPossibleToWin(this, curPoints, winCondition, way);
		return way;
	}

	public boolean isWinnableInTree(int curPoints) {
		return isPossibleToWin(this, curPoints, 25, null);
	}

	private static boolean isPossibleToWin(DrawTreeNode node, int collected, int winCondition, ArrayList<Draw> way) {
		int start;
		int end;
		if (!node.isMax()) {
			start = 0;
			end = 6;
			collected -= node.getDraw().getCatched();
		} else {
			start = 6;
			end = 12;
			collected += node.getDraw().getCatched();
		}

		int index = 0;

		if (node.isMax() && way != null) {
			way.add(node.getDraw());
		}
		// TODO: abbruchbedingung muss noch definiert werden
		if (collected >= winCondition) // bester weg weil mehr als hälfte der
										// Kugeln
		{
			return true;
		} else {
			// subelemente durchgehen
			for (int i = start; i < end; i++, index++) {
				// falls es weniger als 6 züge gibt
				DrawTreeNode subNode = node.getNode(index);
				if (subNode != null) {
					boolean found = DrawTreeNode.isPossibleToWin(subNode, collected, winCondition, way);
					if (found) {
						return true;
					}
				}
			}
		}

		if (node.isMax() && way != null) {
			way.remove(way.indexOf(node.getDraw()));
		}
		return false;
	}
	
	/*
	 * -----------------------------------------------------
	 * 
	 * FERTIGE METHODEN 
	 * -----------------------------------------------------
	 */

	/**
	 * @see look at static DrawTreeNode.getMax(DrawTreeNode node).
	 * @return max points which are getable
	 */
	public int getMax() {
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
	public static int getMax(DrawTreeNode node) {
		int max = 0;
		if (node.isMax()) {
			// dieser Zug ist ein Computer zug, dahier Punkte addieren
			max += node.getDraw().getCatched();
		}

		int tmpMax = 0;
		for (DrawTreeNode subNode : node.getNodes()) {
			// computerzüege
			// hier wollen wir maximum
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
	public int getDeep() {
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
	public int size() {
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
	public boolean hasElements() {
		for (DrawTreeNode node : getNodes()) {
			if (node == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether a index is in the range of the subnodes.
	 * 
	 * @param index
	 *            position to check
	 * @return true if in range of subnodes otherwise false
	 */
	private boolean isInRange(int index) {
		if (index >= 0 || index < getNodes().length) {
			return true;
		}
		// TODO: throw exception
		return false;
	}

	/**
	 * Set nodex by a given index.
	 * 
	 * @param index
	 *            position to set the node
	 * @param node
	 *            node to set
	 */
	void setNodeByIndex(int index, DrawTreeNode node) {
		if (!isInRange(index)) {
			return;
		}
		set(index, node);
	}

	/**
	 * Gives a node from the subnodes by the index.
	 * 
	 * @param index
	 *            position from the wanted subnode
	 * @return node from subnodes
	 */
	DrawTreeNode getNode(int index) {
		if (!isInRange(index)) {
			return null;
		}
		return getNodes()[index];
	}

	/**
	 * Sete a node at the given index.
	 * 
	 * @param index
	 *            position in the subnodes
	 * @param node
	 *            node to set
	 */
	private void set(int index, DrawTreeNode node) {
		nodes[index] = node;
	}

	/**
	 * @return subnodes
	 */
	public DrawTreeNode[] getNodes() {
		return nodes;
	}

	/**
	 * Set the draws.
	 * 
	 * @param nodes
	 *            with draw, maybe with subnodes
	 */
	public void setNodes(DrawTreeNode[] nodes) {
		this.nodes = nodes;
	}

	/**
	 * @return the draw
	 */
	public Draw getDraw() {
		return draw;
	}

	/**
	 * @param draw
	 *            the draw to set
	 */
	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	/**
	 * If isMax = true, then the node defines a draw of the computer.
	 * 
	 * @return if isMax = true => computer node
	 */
	public boolean isMax() {
		return isMax;
	}

	/**
	 * Define whether the current node is from computer by setting isMax = true
	 * 
	 * @param isMax
	 *            if true then node if from computer
	 */
	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}
}