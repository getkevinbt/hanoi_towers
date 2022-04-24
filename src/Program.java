import java.util.*;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*; 

public class Program {
	private Scanner s;
	private String[][] sticks = new String[8][3];
	private Hanoi hanoi;

	
	public Program() {
		s = new Scanner(System.in);
		this.launch();
	}
/*ansi().fg(RED).a("Hello").fg(GREEN).a(" World").reset()*/
	private void launch(){
		
		int difficulty;
		do {
			difficulty = toInt("Choose a difficulty\n1 => Easy\n2 => Medium\n3 => Hard");	
		} while (difficulty < 1 || difficulty > 3);
		hanoi = new Hanoi(difficulty);

		String tmp = "";
		for (int i = 0; i < 2*difficulty+2; i++)tmp += '*';
		for (int i = 0; i < 3; i++) sticks[2*difficulty+1][i] = tmp + (i+1) + tmp;

		this.createDisk(2*difficulty+1);

		int move = 0;

		do {
			int from = -1;
			do{
				this.paint(2*difficulty+1);
				this.cout("From: ");
				from = this.toInt("-1");
			}while(!hanoi.from(from));

			this.cls();

			int to = -1;

			Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> iiii;

			do {
				this.paint(2*difficulty+1);
				this.cout("From: " + from + "\nTo  : ");
				to = this.toInt("-1");
				iiii = hanoi.move(from, to);
			} while (iiii == null);

			this.swap(iiii, difficulty);

			move++;
		} while (!hanoi.win());

		this.cin("\n\n\n                Congratulation\n                   You Win\n                 " + move + " move\n\n\n");
	}

	private void swap(Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> iiii, int difficulty){
		String t = sticks[(2 * difficulty) - iiii.first.first][iiii.first.second];
		sticks[(2 * difficulty) - iiii.first.first][iiii.first.second] = sticks[(2 * difficulty) - iiii.second.first][iiii.second.second];
		sticks[(2 * difficulty) - iiii.second.first][iiii.second.second] = t;
	}

	private void paint(int size){
		this.cls();
		this.cout("\n\n\n");
		for (int i = 0; i <= size; i++){
			this.cout("  ");
			for (int j = 0; j < 3; j++)
				this.cout(sticks[i][j]);
			this.cout("\n");
		}
		this.cout("\n\n\n");
	}

	private void createDisk(int size){
		String tmp = "";
		String space = " ";
		for (int i = 0; i < size*2+1; i++) tmp += "=";

		sticks[--size][0] = space + ansi().fg(BLUE).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";

		sticks[--size][0] = space + ansi().fg(GREEN).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";

		sticks[--size][0] = space + ansi().fg(MAGENTA).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";
		if (size < 1){
			for (int i = 0; i < space.length()-1; i++){
				sticks[i][1] = sticks[i][2] = space + "|" + space;
			}
			return;
		}

		sticks[--size][0] = space + ansi().fg(RED).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";

		sticks[--size][0] = space + ansi().fg(YELLOW).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";
		if (size < 1){
			for (int i = 0; i < space.length()-1; i++)
				sticks[i][1] = sticks[i][2] = space + "|" + space;
			return;
		}

		sticks[--size][0] = space + ansi().fg(CYAN).a(tmp).reset() + space;
		tmp = tmp.substring(2);
		space += " ";

		sticks[--size][0] = space + ansi().fg(GREEN).a(tmp).reset() + space;
		space += " ";
		for (int i = 0; i < space.length()-1; i++)
			sticks[i][1] = sticks[i][2] = space + "|" + space;
	}

	private void cls(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void cout(String msg){
		System.out.print(msg);
	}

	private String cin(String msg){
		if (!msg.equals("-1")){
			cls();
			cout(msg);
		}
		return s.nextLine();
	}

	private int toInt(String msg){
		if (!msg.equals("-1")){
			this.cls();
			this.cout(msg + "\n\n=> only integer numbers\n\n=>");
		}
		boolean flag = false;
		int n = -1;
		try{
			n = Integer.parseInt(this.cin("-1"));
			flag = true;
		}catch (Exception x){
			flag = false;
		}
		if (flag) return n;
		else return toInt(msg);
	}
}