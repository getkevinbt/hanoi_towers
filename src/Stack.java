public class Stack<T> {
	private Node<T> current = null;
	private int length = 0;

	public Stack(){
		//Create an empty stack
	}

	public Stack(T object){
		this.current = new Node<>(object, null);
		this.length++;
	}

	public void push(T object){
		this.current = new Node<>(object, this.current);
		this.length++;
	}

	public T pop(){
		if (!this.empty()){
			T object = current.getValue();
			this.current = current.getNext();
			this.length--;
			return object;
		}
		return null;
	}

	public T top(){
		if (!this.empty())
			return current.getValue();
		return null;
	}

	public int size(){
		return this.length;
	}

	public boolean empty(){
		return this.length == 0;
	}
}
