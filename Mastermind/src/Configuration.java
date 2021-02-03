import java.util.Scanner;
import java.util.*;


public class Configuration {
	private static String[] solution = new String[4];
	private static String[] colors = {"Red", "Blue", "Yellow", "Orange", "Pink", "Green", "Black", "White"};
	private static Scanner sc = new Scanner(System.in);
	private static boolean victory = false, not_twice = true;
	private static int rounds = 1;
	
	public static void main(String[] args) {
		sc.reset();
		String[] Colors = Configuration.random();
		System.out.println("We are ready to play." + "\nPut 4 colors from the following list: Red, Blue, Yellow, Orange, Pink, Green, Black, White." + "\ncolors can be repeated.");
		
		String[] Proposition = Configuration.player();
	
		while(!Configuration.answer(Proposition, Colors)){
			rounds ++;
			Proposition = Configuration.player();
			Configuration.answer(Proposition, Colors);
		}

//		System.out.println("Do you want to play again?");
//		sc.reset();
//		String play_again = sc.next();
//		if (play_again.equals("Yes") || play_again.equals("yes")){
//			System.out.println("play");
//			Configuration.main(args);
//		}else
		System.out.println("Thanks for playing");
	}

	
	// Generating random colors
	public static String[] random() {
		for (int i = 0; i < 4; i++) {
			int x = (int) (Math.random() * (7 - 0));
			solution[i] = colors[x];
			//System.out.println(solution[i]);
		}
		return solution;
	}

	
	// Player's proposition
	public static String[] player() {
		sc.reset();
		sc.useDelimiter(" ");
		String[] proposition = new String[4];
		String user_proposition = sc.nextLine();
		proposition = user_proposition.split(" ");
		
		/*if (proposition[0] != null && proposition[1] == null && proposition[2] != null && proposition[3] != null) {
			System.out.println("You have not put the right number of colors, retry");
			Arrays.fill(proposition, null);
		}*/
		return proposition;
	}

	
	// Return true if the proposition is the right answer, false otherwise
	public static boolean answer(String[] proposition, String[] colors) {
		//System.out.println(colors[0]+ " " + colors[1]+ " "  + colors[2]+ " "  + colors[3]);
		//System.out.println(proposition[0]+ " "  + proposition[1]+ " "  + proposition[2]+ " "  + proposition[3]);
		
		int placed = 0, misplaced = 0;
		List<Integer> verified = new ArrayList<Integer>();
		List<Integer> notverified = new ArrayList<Integer>();
		int i = 0, j = 0;

		// Check placed ones
		while (i < colors.length) {
			if (proposition[i].equals(colors[i])) {
				verified.add(i);
				placed++;

			} else
				notverified.add(i);
			i++;
		}

		// Check misplaced
		while (j < notverified.size()) {
			for (int l = 0; l < notverified.size(); l++) {
				if (colors[notverified.get(j)].equals(proposition[notverified.get(l)])) {
					misplaced++;
					notverified.remove(l);
				}
			}
			j++;
		}

		// Final print
		if ((placed == 4 && rounds == 1) || (placed == 4 && victory)) {
			System.out.println("Victory!" + "\nThe answer was: [" + proposition[0] + ", " + proposition[1] + ", " + proposition[2]
					+ ", " + proposition[3] + "]" );
			return true;
		}
		if (placed == 4) {
			victory = true;
			return true;
		} 
		else if (not_twice){
			System.out.println(misplaced + " misplaced and " + placed + " placed" + "\nTry again");
			not_twice = false;
			return false;
		}
		else {
			not_twice = true;
			return false;
		}
	}

}
