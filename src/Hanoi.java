import java.util.ArrayList;

public class Hanoi {
	private ArrayList<Stack<Integer>> stacks = new ArrayList<>();
	private int difficulty;

	public Hanoi(int difficulty){
		for(int i = 0; i < 3; i++) stacks.add(new Stack<>());
		
		this.difficulty = difficulty;

		this.build(difficulty*2+1);
	}

	private void build(int size){
		for (; size > 0; size--)
			stacks.get(0).push(size);
	}

	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> move(int from, int to){
		if(from < 1 || 3 < from || to < 1 || 3 < to)return null;
		from--;to--;
		if (stacks.get(from).empty())return null;
		if (stacks.get(to).empty() || stacks.get(from).top() < stacks.get(to).top()){
			stacks.get(to).push(stacks.get(from).pop());
			return new Pair<>(new Pair<>(stacks.get(from).size(), from), new Pair<>(stacks.get(to).size()-1, to));
		}
		return null;
	}

	public boolean from(int n){
		if (1 > n || n > 3)return false;

		n--;
		if (stacks.get(n).size() == 0)return false;

		boolean flag = false;
		for (int i = 0; i < 3; i++){
			if (i == n)continue;
			if (stacks.get(i).top() == null){
				flag = true;
				break;
			}
			if (stacks.get(i).top() > stacks.get(n).top())flag = true;
		}

		return !stacks.get(n).empty() && flag;
	}

	public ArrayList<Stack<Integer>> getStatus(){
		return stacks;
	}

	public boolean win(){
		return stacks.get(0).empty() && stacks.get(1).empty();
	}
}
