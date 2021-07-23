
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FindAnagrams {
    private Map<String, Set<String>> map;

    public FindAnagrams() {
        this.map =new TreeMap<>();
    }

    public void addWords(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }

    private void addWord(String word) {
        String key = generateKey(word);

        if (!map.containsKey(key)) {
            map.put(key, new TreeSet<String>(Arrays.asList(word)));
        } else {
            Set<String> set = map.get(key);
            set.add(word);
        }
    }

    private static String generateKey(String word) {
        Map<Character, Integer> map = new TreeMap<>();
        StringBuilder builder = new StringBuilder();
        char arr[] = word.toLowerCase().toCharArray();

        for (char key : arr) {
            int value = map.getOrDefault(key, 0);
            map.put(key, ++value);
        }

        Set<Character> set = map.keySet();
        for (Character ch : set) {
            builder.append(ch + Integer.toString(map.get(ch)));
        }

        return builder.toString();
    }

    public String[] getAnagrams(String word) {
        String key = generateKey(word);
        Set<String> set = map.getOrDefault(key, new TreeSet<>());
        return set.stream().toArray(String[]::new);
    }

    public static void main(String[] args) throws IOException 
    {
    	FindAnagrams obj = new FindAnagrams();
    	
        System.out.println("To see Anagrams of your word:");
        
        List<String> list = Files.readAllLines(Paths.get("dictionary3.txt"), StandardCharsets.UTF_8);
        String words[]  = list.toArray(new String[list.size()]); 
        
        obj.addWords(words);
        Scanner input = new Scanner(System.in);
	    System.out.println("Enter a word:");
	    String word = input.nextLine();
        
        for (String anagram : obj.getAnagrams(word))
        
        	//System.out.println("Available anagrams in the dictionary are:");
            System.out.println(anagram);
        
    }
}
