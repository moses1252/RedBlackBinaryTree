package RBTree;

public class RBNode <T extends Comparable<T>> {
	private T data;
	protected char color;
	protected RBNode<T> parent;
	protected RBNode<T> left;
	protected RBNode<T> right;
	
	public RBNode() {
		data = null;
		color = ' ';
		parent = null;
		left = null;
		right = null;
	}
	
	public RBNode(T data, char color) {
		this.data = data;
		this.color = color;
	}
	
	//getters and setters
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
}
