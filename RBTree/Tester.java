package RBTree;

import java.util.ArrayList;

public class Tester {
	public static void main(String[] args) throws CloneNotSupportedException {
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = i;
		}
		
		RedBlackTree<Integer> tree = new RedBlackTree();
		for (int i = 0; i < 10; i++) {
			RBNode put = new RBNode(arr[i], 'R');
			tree.insert((Integer) put.getData());
		}
		
		tree.printTree();
		
		
	}

}
