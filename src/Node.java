public class Node<T> {
	private T node;
	private Node<T> next;

	public Node(T node, Node<T> next){
		this.node = node;
		this.next = next;
	}

	public T getValue(){
		return node;
	}

	public Node<T> getNext(){
		return next;
	}
}
