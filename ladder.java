import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ladder {

	static int shortestChainLen(String start, String target, Set<String> D)
			{
			if(start == target)
			return 0;
			// If the target String is not
			// present in the dictionary
			if (!D.contains(target))
			return 0;
			// To store the current chain length
			// and the length of the words
			int level = 0, wordlength = start.length();

			// Push the starting word into the queue
			Queue<String> Q = new LinkedList<>();
			Q.add(start);
		
			// While the queue is non-empty
			while (!Q.isEmpty())
			{
			
			// Increment the chain length
			++level;
			
			// Current size of the queue
			int sizeofQ = Q.size();
		
			// Since the queue is being updated while
			// it is being traversed so only the
			// elements which were already present
			// in the queue before the start of this
			// loop will be traversed for now
			for (int i = 0; i < sizeofQ; ++i) {
			
			// Remove the first word from the queue
			char []word = Q.peek().toCharArray();
			Q.remove();
			
			// For every character of the word
			for (int pos = 0; pos < wordlength; ++pos)
			{
			
			// Retain the original character
			// at the current position
			char orig_char = word[pos];
			
			// Replace the current character with
			// every possible lowercase alphabet
			for (char c = 'a'; c <= 'z'; ++c)  {
			word[pos] = c;
			
			// If the new word is equal
			// to the target word
			if (String.valueOf(word).equals(target))
			return level + 1;
			
			// Remove the word from the set
			// if it is found in it
			if (!D.contains(String.valueOf(word)))
			continue;
			D.remove(String.valueOf(word));
			
			// And push the newly generated word
			// which will be a part of the chain
			Q.add(String.valueOf(word));
			}
			
			// Restore the original character
			// at the current position
			word[pos] = orig_char;
			}
			}
			}
			
			return 0;
			}
	
	public void loadDictionary(String file_name) {
  		databaseInfo dbInfo = new databaseInfo();
        ArrayList<String> dictionaryWords = dbInfo.getWordsFromDatabase();
  
  		String[] dictionary = new String[dictionaryWords.size()];
  
  	    for (int i = 0; i < dictionaryWords.size(); i++) {
          dictionary[i] = dictionaryWords.get(i);
        }
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Hello and Welcome, please choose a game mode");
		System.out.println("1. Build Swap Graph");
		System.out.println("2. Find the path between 2 words");
		System.out.println("3. Find all paths of a word with N Steps");
		System.out.println("4. Find a path from a word with 10 steps and generate a html file");

		// System.out.println("Enter a number to choose that game mode:");
		int choiceentry = -1;

		while (choiceentry < 1 || choiceentry > 4) {

			System.out.println("Enter a number to choose that game mode:");
			if (input.hasNextInt())
				choiceentry = input.nextInt();

		}

		switch (choiceentry) {
		case 1:
			// do logic
			System.out.println("Graph built successful");
			break;
		case 2:
			// do logic
			System.out.println("Enter the starting word");
			int startValue = input.nextInt();
			System.out.println("Enter the ending word");
			int endValue = input.nextInt();
			break;
		case 3:
			// do logic
			System.out.println("Enter the starting word");
			int start = input.nextInt();
			break;
		case 4:
			System.out.println("HTML file puzzle created");
			break;
		case 5:
			System.out.println("Goodbye");
			break;
		}
	}
}