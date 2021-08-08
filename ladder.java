import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.String;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ladder extends databaseInfo {

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
	public static boolean matching(String word,String line){
		 String newline = line.replaceAll("[AEIOUaeiou]","");
		 String newword = word.replaceAll("[AEIOUaeiou]","");
		  char[] wordarr = newword.toCharArray();
		  char[] linearr = newline.toCharArray();
		  Arrays.sort(wordarr);
		  Arrays.sort(linearr);
		  return Arrays.equals(wordarr,linearr);
		}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws SQLException, IOException {
		  
		  ArrayList<String> dictionaryWords = new ArrayList<String>();

		Connection db_connection = null;
		String url = "jdbc:mysql://127.0.0.1:3308/ladder";
		String user = "root";
		String password = "";
		db_connection = DriverManager.getConnection(url, user, password);
		System.out.println("Success: Connection established");
		Statement statement_object = db_connection.createStatement();
		String sql_query_str = "SELECT * FROM dictionary";
		ResultSet result_set = statement_object.executeQuery(sql_query_str);

	
		  
		Scanner input = new Scanner(System.in);
		System.out.println("Hello and Welcome, please choose a game mode");
		System.out.println("1. Build Anagram Graph");
		System.out.println("2. Find the path between 2 words");
		System.out.println("3. Find all paths of a word with N Steps");
		System.out.println("4. Find a path from a word with 10 steps and generate a html file");
		System.out.println("5. Exit game");


		int choiceentry = -1;

		while (choiceentry < 1 || choiceentry > 5) {

			System.out.println("Enter a number to choose that game mode:");
			if (input.hasNextInt())
				choiceentry = input.nextInt();

		}
		
		switch (choiceentry) {
		case 1:
			System.out.println("Enter the starting word");
			String startValue = input.next();
			System.out.println("Enter the ending word");
			String endValue = input.next();
			int j=0;
			while (result_set.next()) {
				
				String Words = result_set.getString("Word");
				
					dictionaryWords.add(Words);	
					j++;
				
				}
			int startpoint = dictionaryWords.indexOf(startValue);
			int endpoint = dictionaryWords.indexOf(endValue);
			
			
			 ArrayList<String> arr = new ArrayList<String>();
	            List<String> arrlist2 = dictionaryWords.subList(startpoint, endpoint);

			  HashMap<String, ArrayList> map = new HashMap<String, ArrayList>();
			  int length = startValue.length();
			  for(int a=0;a<arrlist2.size();a++) {
				  String word = arrlist2.get(a);
				  if(word.length()==length) {
					  String result = word.replaceAll("[AEIOUaeiou]","");
						 char[] resultarr = result.toCharArray();
						 Arrays.sort(resultarr);
						 result = new String(resultarr);
						 result = result.replaceAll(" ", "");
						 boolean exists = map.containsKey(result);
					     if(exists){
					              map.get(result).add(word);
					            }
					      
					      else{
					        ArrayList<String> newarr = new ArrayList<String>();
					         newarr.add(word);
					         map.put(result,newarr);
				  }
				  
				  
			  }
			  }
			  
			  for (Map.Entry<String, ArrayList> entry : map.entrySet()) {
			         String key = entry.getKey();
			        ArrayList<String> value = new ArrayList<String>();
			        value = entry.getValue();
			         System.out.println("Words for the consonants: " + key); 
			         for(int i=0; i < value.size(); i++){
			            System.out.println( value.get(i) );
			        }
			        System.out.println(" ");
			         
			  }
	
			
		
			
			break;
		case 2:
			
			System.out.println("Enter the starting word");
			String startValue2 = input.next();
			System.out.println("Enter the ending word");
			String endValue2 = input.next();
			if(matching(startValue2,endValue2)){
				int j1=0;
				while (result_set.next()) {
					
					String Words = result_set.getString("Word");
					 Words=Words.toLowerCase();

						dictionaryWords.add(Words);	
						j1++;
					
					}
				int startpoint2 = dictionaryWords.indexOf(startValue2);
				int endpoint2 = dictionaryWords.indexOf(endValue2);
				
				
				 ArrayList<String> arr2 = new ArrayList<String>();
		            List<String> arrlist22 = dictionaryWords.subList(startpoint2, endpoint2);
		            
				  HashMap<String, ArrayList> map2 = new HashMap<String, ArrayList>();
				  int length2 = startValue2.length();
				  for(int a=0;a<arrlist22.size();a++) {
					  String word = arrlist22.get(a);
					  if(word.length()==length2) {
						  String result = word.replaceAll("[AEIOUaeiou]","");
							 char[] resultarr = result.toCharArray();
							 Arrays.sort(resultarr);
							 result = new String(resultarr);
							 result = result.replaceAll(" ", "");
							 boolean exists = map2.containsKey(result);
						     if(exists){
						              map2.get(result).add(word);
						            }
						      
						      else{
						        ArrayList<String> newarr = new ArrayList<String>();
						         newarr.add(word);
						         map2.put(result,newarr);
					  }
					  
					  
				  }
				  }
				  String result = startValue2.replaceAll("[AEIOUaeiou]","");
					 char[] resultarr = result.toCharArray();
					 Arrays.sort(resultarr);
					 result = new String(resultarr);
					 result = result.replaceAll(" ", "");
					
					 for (Map.Entry<String, ArrayList> entry : map2.entrySet()) {
				         String key = entry.getKey();
				         ArrayList<String> value = new ArrayList<String>();
					     value = entry.getValue();
					     if(key.equals(result)) {
					         for(int i=0; i < value.size(); i++){
					        	 if(i==(value.size()-1)) {
							            System.out.print( value.get(i)+" > "+endValue2);

					        	 }
					        	 else {
					        		 System.out.print( value.get(i) + " > ");
					        	 }
					            
					        };
					     }
					     
				  }
				  

		
			
			}
			else {
				System.out.println("Words are not Anagrams");
			}
			
			break;
		case 3:
		
			System.out.println("Enter number of steps");
			int steps = input.nextInt();
			System.out.println("Enter word");
			String startValue3 =input.next();
			startValue3 = startValue3.toLowerCase();
			int j2=0;
			while (result_set.next()) {
				
				String Words = result_set.getString("Word");
				 Words=Words.toLowerCase();
					dictionaryWords.add(Words);	
					j2++;
				
				}
		
			
			  int startpoint3 = dictionaryWords.indexOf(startValue3);

			  int length3 = startValue3.length();
			  String endValue3 = "null";
			  for(int a=startpoint3;a<dictionaryWords.size();a++) {
				  String word = dictionaryWords.get(a);
				  

				  if(word.length()==length3) {
					if(matching(word,startValue3)) {
						  ArrayList<String> arr3 = new ArrayList<String>();
						 endValue3 = word;
						 endValue3 = endValue3.toLowerCase();
							int endpoint3 = dictionaryWords.indexOf(endValue3);
					          List<String> arrlist23 = dictionaryWords.subList(startpoint3, (endpoint3+1));
					         
					          HashMap<String, ArrayList> map3 = new HashMap<String, ArrayList>();				        	  
								  for(int c=0;c<arrlist23.size();c++) {
									  String words = arrlist23.get(c);
									  if(words.length()==length3) {
										  String result = words.replaceAll("[AEIOUaeiou]","");
											 char[] resultarr = result.toCharArray();
											 Arrays.sort(resultarr);
											 result = new String(resultarr);
											 result = result.replaceAll(" ", "");
											 boolean exists = map3.containsKey(result);
										     if(exists){
										    	 
										              map3.get(result).add(words);
										            }
										      
										      else{
										        ArrayList<String> newarr = new ArrayList<String>();
										         newarr.add(words);
										         map3.put(result,newarr);
									  }
									  
									  
								  }
								  }
								  
								  
								
					          
					          String result = startValue3.replaceAll("[AEIOUaeiou]","");
								 char[] resultarr = result.toCharArray();
								 Arrays.sort(resultarr);
								 result = new String(resultarr);
								 result = result.replaceAll(" ", "");
							  for (Map.Entry<String, ArrayList> entry : map3.entrySet()) {
							         String key = entry.getKey();
							         ArrayList<String> value = new ArrayList<String>();
								     value = entry.getValue();
								     if(startValue3.equals(value.get(0))) {
								    	 int size = value.size();
								    	if(size==steps) {
								    		  for(int i=0; i < value.size(); i++){
										        	 if(i==(value.size()-1)) {
												            System.out.print( value.get(i));

										        	 }
										        	 else {
										        		 System.out.print( value.get(i) + " > ");
										        	 }
										            
										        }
								    	}
								        
								     }

							  }

					       
					  }
					 
				  }
				 
			  }
	
			
			break;
		case 4: System.out.println("Enter word");
			String startValue4 =input.next();
			startValue4 = startValue4.toLowerCase();
			int j3=0;
			while (result_set.next()) {
				
				String Words = result_set.getString("Word");
				 Words=Words.toLowerCase();
					dictionaryWords.add(Words);	
					j3++;
				
				}
		
			dictionaryWords.replaceAll(String::toUpperCase);

			
			  int startpoint4 = dictionaryWords.indexOf(startValue4);

			  int length4 = startValue4.length();
			  String endValue4 = "null";
			  for(int a=startpoint4;a<dictionaryWords.size();a++) {
				  String word = dictionaryWords.get(a);
				  if(word.length()==length4) {
					if(matching(word,startValue4)) {
						  ArrayList<String> arr3 = new ArrayList<String>();
						 endValue4 = word;
						 endValue4 = endValue4.toLowerCase();
							int endpoint3 = dictionaryWords.indexOf(endValue4);
					          List<String> arrlist23 = dictionaryWords.subList(startpoint4, (endpoint3+1));
					         
					          HashMap<String, ArrayList> map3 = new HashMap<String, ArrayList>();				        	  
								  for(int c=0;c<arrlist23.size();c++) {
									  String words = arrlist23.get(c);
									  if(words.length()==length4) {
										  String result = words.replaceAll("[AEIOUaeiou]","");
											 char[] resultarr = result.toCharArray();
											 Arrays.sort(resultarr);
											 result = new String(resultarr);
											 result = result.replaceAll(" ", "");
											 boolean exists = map3.containsKey(result);
										     if(exists){
										    	 
										              map3.get(result).add(words);
										            }
										      
										      else{
										        ArrayList<String> newarr = new ArrayList<String>();
										         newarr.add(words);
										         map3.put(result,newarr);
									  }
									  
									  
								  }
								  }
								  
								  
								
					          
					          String result = startValue4.replaceAll("[AEIOUaeiou]","");
								 char[] resultarr = result.toCharArray();
								 Arrays.sort(resultarr);
								 result = new String(resultarr);
								 result = result.replaceAll(" ", "");
							  for (Map.Entry<String, ArrayList> entry : map3.entrySet()) {
							         String key = entry.getKey();
							         ArrayList<String> value = new ArrayList<String>();
								     value = entry.getValue();
								     if(startValue4.equals(value.get(0))) {
								    	 int size = value.size();
								    	if(size==10) {
								    		  for(int i=0; i < value.size(); i++){
										        	 if(i==(value.size()-1)) {
												            System.out.print( value.get(i));

										        	 }
										        	 else {
										        		 System.out.print( value.get(i) + " > ");
										        	 }
										            
										        }
								    	}
								        
								     }

							  }

					       
					  }
					 
				  }
				 
			  }
				 FileWriter html_file = new FileWriter("ladder.html");
				 /*
				 try {
						html_file = new FileWriter("ladder.html");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
						StringBuilder hg = new StringBuilder();

						hg.append("<!DOCTYPE html>\n");
						hg.append("<html>\n\n");
						hg.append("<head>\n");
						hg.append("\t<title>Ladder</title>\n");
						hg.append("</head>\n\n");
						hg.append("<body>\n");
						hg.append("</body>\n\n");
						hg.append("</html>");*/
						StringBuilder hg = new StringBuilder();
						hg.append("<!DOCTYPE html>\n");
						hg.append("<html>\n\n");
						hg.append("<head>\n");
						hg.append("\t<title>Ladder</title>\n");
						hg.append("\t<style>\n");
						hg.append("\ttable{\n\t\tbackground-color: #ffd700;\n\t\tcolor: #ff0000;\n\t\tborder-spacing: 10px;\n" + 
						          "\t\tfont-family: Arial;\n\t\tmargin-left: auto;\n\t\tmargin-right: auto;\n" + 
								  "\t\tbox-shadow: inset -3px -3px 3px rgba(0,0,0,0.4), 3px 3px 5px 0px rgba(0,0,0,0.5);\n" + 
						          "\t\tborder-radius: 0.8rem;\n\t}\n\n");
						hg.append("\ttd{\n\t\tbackground-color: #ffff00;\n\t\tpadding: 30px;\n\t\twidth: 30px;\n\t\ttext-align: center;\n" + 
						          "\t\tfont-size: 24px;\n\t\tfont-weight: bold;\n\t\ttext-shadow: 0px 1px 0px rgba(255,255,255,0.8)," + 
								  "0px -1px 0px rgba(0,0,0,.8);\n\t\tbox-shadow: inset -5px -5px 7px rgba(0,0,0,0.4), " + 
						          "2px 2px 3px 0px rgba(0,0,0,0.5);\n\t\tborder-radius: 0.8rem;\n\t}\n");
						hg.append("\t</style>\n");
						hg.append("</head>\n\n");
						hg.append("<body>\n");
						hg.append("\t<table>\n");
						hg.append("\t\t<tr> ");
						hg.append("\t\t<th> Words");
						hg.append("\t\t</th>");
						hg.append("\t\t<th> More Words ");
						hg.append("\t\t</th>");
						hg.append("\t\t</tr>");
						hg.append("\t\t<tr> ");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t</tr>");
						hg.append("\t\t<tr>  ");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t</tr>");
						hg.append("\t\t<tr> ");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t<td> No. Word ");
						hg.append("\t\t</td>");
						hg.append("\t\t</tr>");
						hg.append("\t\t<caption> No. Word </caption>\n");
						
						hg.append("\t</table>\n");
						hg.append("</body>\n\n");
						hg.append("</html>");
						html_file.write(hg.toString());
						/*File htmlGrid = ("ladder.html");
						try {
							Desktop.getDesktop().browse(htmlGrid.toURI());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						html_file.close();
			  

			break;
		case 5:
			System.out.println("Goodbye");
			break;
			
		default:
			break;
		}
	
