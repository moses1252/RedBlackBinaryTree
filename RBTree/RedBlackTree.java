package RBTree;


public class RedBlackTree <T extends Comparable<T>> {
	
	//data fields
	RBNode root;
	RBNode NIL; //node with no right, left, or parent value will point to this node
	
	//default constructor
	public RedBlackTree() {
		root = null;
	}
	
	//use the values in the array to populate the tree
	public RedBlackTree(T[] arr) {
		//TODO: fill tree with array values
		for (int i = 0; i < arr.length; i++) {
			insert(arr[i]);
		}
	}
	
	public void insert(T key) {
		//TODO: adds a new value to the tree, rules of a Binary Search Tree
		RBNode z = new RBNode(key, 'R');
		RBNode y = NIL;
		RBNode x = root;
		
		while (x != NIL) {
			y = x;
			
			if (z.getData().compareTo(x.getData()) < 0) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		
		z.parent = y;
		if (y == NIL) {
			root = z;
		} else if (z.getData().compareTo(y.getData()) < 0) {
			y.left = z;
		} else {
			y.right = z;
		}
		
		z.left = NIL;
		z.right = NIL;
		this.insertCleanup(z);
	}
	
	public void delete(T key) {
		//TODO: take a key and remove the node from the tree.
	}
	
	public boolean find(T key) {
		//TODO: return true or false depending on if the key is found in the tree or not
		return false;
	}
	
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
	
	public T[] preorder() {
		return null;
	}
	
	public T[] inorder() {
		return null;
	}
	
	public T[] postorder() {
		return null;
	}
	
	public T[] breadthfirst() {
		return null;
	}
	
//	public void printTree() {
//		//TODO: print tree
//	}
	
	private boolean isLeaf(RBNode node) {
		if (node.left == NIL && node.right == NIL) {
			return true;
		}
		return false;
	}
	
	private boolean isLeftChild(RBNode node) {
		if (node.parent.left == node) {
			return true;
		}
		return false;
	}
	
	private boolean isRightChild(RBNode node) {
		if (node.parent.right == node) {
			return true;
		}
		return false;
	}
	
	private RBNode sibling(RBNode node) {
		if (node.parent == null) {
			return null;
		} else if (node.parent.left == node) {
			return node.right;
		}
		return node.left;
	}
	
	private RBNode<T> uncle(RBNode<T> node) {
		if (node.parent.parent != null) {
			if (node.parent.parent.right == node.parent) {
				return node.parent.parent.left;
			}
			return node.parent.parent.right;
		}
		return null;
	}
	
	private RBNode grandparent(RBNode node) {
		if (node.parent.parent != NIL) {
			return node.parent.parent;
		}
		return null;
	}
	
	private void leftRotate(RBNode subTreeRootNode) {
		//TODO: Perform a left rotation rooted at subTreeRoot.
		RBNode y = subTreeRootNode.right;
		subTreeRootNode.right = y.left;
		
		if (y.left != NIL) {
			y.left.parent = subTreeRootNode;
		}
		
		y.parent = subTreeRootNode.parent;
		if (subTreeRootNode.parent == NIL) {
			root = y;
		} else if (subTreeRootNode == subTreeRootNode.parent.left) {
			subTreeRootNode.parent.left = y;
		} else {
			subTreeRootNode.parent.right = y;
		}
		
		y.left = subTreeRootNode;
		subTreeRootNode.parent = y;
	}
	
	private void rightRotate(RBNode subTreeRootNode) {
		//TODO: Perform a right rotation rooted at subTreeRoot.
		RBNode y = subTreeRootNode.left;
		subTreeRootNode.left = y.right;

		if (y.right != NIL) {
					y.right.parent = subTreeRootNode;
		}
		y.parent = subTreeRootNode.parent;
		if (subTreeRootNode.parent == NIL) {
					root = y;
		} else if (subTreeRootNode == subTreeRootNode.parent.right) {
					subTreeRootNode.parent.right = y;
		} else {
					subTreeRootNode.parent.left = y;
		}
		y.right = subTreeRootNode;
		subTreeRootNode.parent = y;
	}
	
	private void insertCleanup(RBNode node) {
		//TODO: This method will be used after insertion to rebalance the tree.
		if (root == null || root.parent == null) {
			return;
		}
		while (node.parent.color == 'R') {
			if (node.parent == uncle(node).left) {
				RBNode y = uncle(node).right;
				
				if (y.color == 'R') {
					node.parent.color = 'B';
					y.color = 'B';
					grandparent(node).color = 'R';
					node = grandparent(node);
				} else if (node == node.parent.right) {
					node = node.parent;
					leftRotate(node);
				}
				node.parent.color = 'B';
				grandparent(node).color = 'R';
				rightRotate(grandparent(node));
			} else {
				if (node.parent == uncle(node).right) {
					RBNode y = uncle(node).left;
					
					if (y.color == 'R') {
						node.parent.color = 'B';
						y.color = 'B';
						grandparent(node).color = 'R';
						node = grandparent(node);
					} else if (node == node.parent.left) {
						node = node.parent;
						rightRotate(node);
					}
					node.parent.color = 'B';
					grandparent(node).color = 'R';
					leftRotate(grandparent(node));
				}
			}
		
		}
		root.color = 'B';
	}
	
	private void fixDoubleBlack(RBNode node) {
		//TODO: his method is required if you are going to implement delete.
		//This method is used with delete to rebalance the tree after a node is removed.
	}
	
	public void printTree() {
		if (root.right != null) {
			printTree(root.right, true, "");
		}

		printNodeValue(root);

		if (this.root.left != null) {
			printTree(this.root.left, false, "");
		}

	}

	private void printTree(RBNode node, boolean isRight, String indent) {

		if (node.right != null) {         
			printTree(node.right, true, indent + (isRight ? "        " : " |      "));
		}
		System.out.print(indent);

		if (isRight) {
			System.out.print(" /");
		}
		else {
			System.out.print(" \\");
		}
		System.out.print("----- ");

		printNodeValue(node);

		if (node.left != null) {
			printTree(node.left, false, indent + (isRight ? " |      " : "        "));
		}
	}

	private void printNodeValue(RBNode node) {
		if (node == null) {
			System.out.print("<null>");
		}
		else {
			System.out.print(node.getData());
			//System.out.print("(" + node.color + ")");
		}
		System.out.println();
	}
    
    private static void printSpace(double n, RBNode removed) {
    	for (; n > 0; n--) {
    		System.out.print("\t");
    	}
    	if (removed == null) {
    		System.out.print(" ");
    	} else {
    		System.out.print(removed.getData());
    	}
    }
}
