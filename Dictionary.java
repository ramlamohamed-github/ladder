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
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author Simeon Gbolo
 *Comments not added yet!
 *Please see {@link appendPremutations()}
 */
@SuppressWarnings("serial")
public class Dictionary extends JApplet
	{

		private String[]	dictionary;
		private String[][]	orginizedByLengthDict	= new String[29][];

		private JTextField	someFile;
		private JTextField	start;
		private JTextField	end;

		private JTextArea	textArea;

		private JScrollPane	sP;

		private JButton		b1;
		private JButton		b2;
		private JButton		b3;
		private JButton		b4;
		//private JButton		b5;
		private JButton		b6;
		private JButton		b7;

		private Painter		pA;

		private long		elapsedTime;

		/**
		 * 
		 * @param string
		 */
		public Dictionary(String string)
			{
				initComponents();
			}

		/**
	 * 
	 */
		public void initComponents()
			{
				setLayout(new BorderLayout());
				sP = new JScrollPane(textArea);
				sP.setEnabled(true);
				textArea = new JTextArea("a\nb\nc\none\ntwo\nthree\ntoo"
						+ "\nbleed\nblood\ncake\nstorm\nflood"
						+ "\nblend\nblond\nyes\nno");
				textArea.setWrapStyleWord(true);

				// textArea.setRows(20);
				textArea.setPreferredSize(new Dimension(100, 250));
				// textArea.setBounds(getBounds());
				someFile = new JTextField("dictionary.txt");
				start = new JTextField("Start");
				end = new JTextField("End");
				b1 = new JButton("Load Dictinary from file (SWAP)");
				b2 = new JButton("Load words from text field");
				b3 = new JButton("Find Path Swap(Load Dictinary First)");
				//add anagrams button
				b4 = new JButton("Find Path Anagram(Load Dictinary First)");
				//add tail head buttons
				//b5 = new JButton("Find Path Head-Tail(Load Dictinary First)");
				//b5.layout(new b);
				//HTML button
				b6 = new JButton("HTML");
				b3.setEnabled(false);
				//disable the button
				//b4.setEnabled(false);
				//disable the button
				//b5.setEnabled(false);
				//b5.setEnabled(false);
				b7= new JButton("Load words from dictionary (Anagram)");
				
				JPanel p = new JPanel();
				JPanel p2 = new JPanel();
				JPanel p3 = new JPanel();
				//JPanel p4 = new JPanel();// buttons
				
				pA = new Painter();
				add("West", p);
				add("East", p2);
				add("South", p3);
				//add("south", p4);
				p2.add(pA);
				p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
				p.add(textArea);
				// p3.setLayout(new BoxLayout(p3,BoxLayout.Y_AXIS));
				p3.setLayout(new GridLayout(5, 2, 0, 10));
				p3.add(b2);
				p3.add(start);
				p3.add(b1);
				p3.add(end);
				p3.add(someFile);
				p3.add(b7);
				
				//p4.setLayout(new());
				
				p3.add(b3);
				//p3.add(someFile);
				//a panel to add button 4 and a layout
				p3.add(b4);
				//p3.add(someFile);
				//a panel to add buttons 5 and a layout
				//p3.add(b5);
				//To set the background color of the buttons panel red
				//p3.setBackground(Color.red);
				p3.add(b6);
				//anagram dictionary
				p3.add(b7);
				
				
				//swap dictionary
				b1.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
							{
								orginizedByLengthDict = new String[29][];
								loadDictionary(someFile.getText());
								pA.paintString(dictionary.length);

								start();
							}
					});
				//text field dictionary
				b2.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
							{
								orginizedByLengthDict = new String[29][];
								String[] tmpArray;
								StringTokenizer st;
								String s = textArea.getText();
								st = new StringTokenizer(s);
								tmpArray = new String[st.countTokens()];
								String tmp;
								for (int i = 0; i < tmpArray.length; i++)
									{
										tmp = st.nextToken();
										if (tmp != null)
											{
												tmpArray[i] = tmp;
											}
									}
								dictionary = tmpArray;
								pA.paintString(dictionary.length);
								start();

							}
					});
				//swap method
				b3.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
							{
								long startTime = System.currentTimeMillis();
								long totalTime = 0;
								if (start.getText().length() != end.getText()
										.length())
									{
										JOptionPane.showMessageDialog(null,
												"Error: Strings must be"
														+ "of equal length");
										return;
									}

								if (exist(start.getText().toLowerCase()) == false)
									{
										JOptionPane
												.showMessageDialog(
														null,
														"Error: "
																+ start.getText()
																+ " Is not in the Dictionary");
										return;
									}

								if (exist(end.getText().toLowerCase()) == false)
									{
										JOptionPane
												.showMessageDialog(
														null,
														"Error: "
																+ end.getText()
																+ " Is not in the Dictionary");
										return;
									}
								
								// swap result
								int length = start.getText().length();
								Graph g = new Graph();
								String[] someStrings;
								String line;
								someStrings = getAllStrings(length);
								StringTokenizer st;
								for (int i = 0; i < someStrings.length; i++)
									{
										line = someStrings[i];
										st = new StringTokenizer(line);
										String source = st.nextToken();
										int manyTokens = st.countTokens();
										for (int j = 0; j < manyTokens; j++)
											{
												int destIndex = Integer
														.parseInt(st
																.nextToken());
												g.addEdge(
														source,
														someStrings[destIndex]
																.substring(0,
																		length),
														1);
											}
									}

								g.unweighted(start.getText().toLowerCase()
										.trim());
								g.printPath(end.getText().toLowerCase().trim());
								String s2 = g.getS();
								totalTime = System.currentTimeMillis()
										- startTime;
								elapsedTime = totalTime;
								pA.setTime(elapsedTime);
								pA.setCost(g.getCost());
								pA.paintResult(s2);
								// System.out.println(s2);
					
							}
					});
				
				//HTML button
				b6.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
						{
						try {
							FileWriter html_file = new FileWriter("ladder.html");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
						hg.append("\t\t<caption> No. Word </caption>\n");
						
						hg.append("\t</table>\n");
						hg.append("</body>\n\n");
						hg.append("</html>");
						
						//write the string to a file and close the file
						//html_file.write(hg.toString());
						
						
						//open the html file and launch it using the default browser.
						File htmlGrid = new File("ladder.html");
						try {
							Desktop.getDesktop().browse(htmlGrid.toURI());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						}
				});
				
				//anagram dictionary
				b7.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						orginizedByLengthDict = new String[29][];
						loadDictionary(someFile.getText());
						pA.paintString(dictionary.length);

						start();
					}
				});
			}

		/**
	 * Starts a new thread for indexting dictionary
	 */
		public void start()
			{
				Runnable r = new Runnable()
					{
						public void run()
							{
								runWork();
							}
					};
				Thread t = new Thread(r);
				t.start();
			}

		/**
	 * Disables all the buttons while 
	 * appendPremutations is running
	 * {@link appendPremutations()}
	 * 
	 */
		private void runWork()
			{
				long startTime = System.currentTimeMillis();
				long totalTime = 0;
				b3.setEnabled(false);
				b1.setEnabled(false);
				b2.setEnabled(false);
				appendPremutations();
				b1.setEnabled(true);
				b2.setEnabled(true);
				b3.setText("Find Path");
				b3.setEnabled(true);
				totalTime = System.currentTimeMillis() - startTime;
				elapsedTime = totalTime;
				pA.setTime(elapsedTime);
			}

		/**
		 * 
		 * @param file_name
		 */
		public void loadDictionary(String file_name)
			{

				File file = new File(file_name);
				byte[] bytes = new byte[(int) file.length()];
				FileInputStream fis = null;
				try
					{
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e1)
					{
						JOptionPane.showMessageDialog(null,
								"There was an error" + " reading the file");
						e1.printStackTrace();
					}
				try
					{
						fis.read(bytes);
						fis.close();
					} catch (IOException k)
					{
						JOptionPane.showMessageDialog(null,
								"There was an error"
										+ " reading the file's bytes");
						k.printStackTrace();
					}

				dictionary = new String(bytes).trim().split("\\s+");

			}

		

		/**
		 * 
		 * @return
		 */
		public String[] getDictionary()
			{
				return this.dictionary;
			}

		/**
	 * First this method Organizes each string according to 
	 * their length {@link organize()}
	 * 
	 */
		public void appendPremutations()
			{
				boolean done = false;
				for (int i = 1; i < 28; i++)
					{
						if (organize(i) != null)
							{
								//Organize puts all the strings of the same length 
								//in their own array 
								orginizedByLengthDict[i] = organize(i);
							}
					}
				System.out.println("DONE");

				for (int k = 1; k < 20; k++)
					{
						if (orginizedByLengthDict[k] != null)
							{
								for (int i = 0; i < orginizedByLengthDict[k].length; i++)
									{
										GetOffByOnes(
												orginizedByLengthDict[k][i], i);// find
																				// whats
																				// off
																				// by
																				// one

									}
							}
						pA.updateProgress(10, done);

					}
				done = true;
				pA.updateProgress(100, done);

			}

		/**
		 * This method sorts the dictionary into arrays 
		 * that have strings of equal length
		 * @param length
		 * the string length to be organized
		 * @return
		 * An array of Strings that are of equal length.
		 * -This pretty fast and shouldnt need Optimization
		 */
		public String[] organize(int length)
			{

				int count = 0;
				for (int i = 0; i < dictionary.length; i++)
					{
						if (dictionary[i].length() == length)
							{
								count++;
							}
					}

				if (count == 0)
					{
						return null;
					}
				String[] s2 = new String[count];
				int count2 = 0;
				for (int i = 0; i < dictionary.length; i++)
					{
						if (dictionary[i].length() == length)
							{
								s2[count2] = dictionary[i];
								count2++;
							}
					}
				return s2;
			}
		
		/**
		 * Goes down an array of organized strings, starting at the top and 
		 * comparing them all one by one, as soon as a sequence is off by 2
		 * the loop breaks and moves on to the next word- 
		 * THIS PROCESS TAKES ALONG TIME! and needs to be optimized
		 * Example finding words off by one in a 
		 * 60,000 word dictionary takes about 10 seconds
		 * a 200,000 word dictionary takes about 4-5 minutes
		 * @param string
		 * to be compared to other strings, to find sequences
		 * that are off by one
		 * @param index
		 * of the string being passed
		 */
		private void GetOffByOnes(String string, int index)
			{
				// TODO Auto-generated method stub
				//Lengh  = the about of strings in an array of a specific length
				int length = (orginizedByLengthDict[string.length()].length);
				
				//length of the string
				int stringLength = string.length();
				
				//loop is used to iteratwe through each element in the array
				for (int i = 0; i < length; i++)
					{

						int offByOneTracker = 0;// keeps track of of by one
												// strings
						
						//inner loos is to compare each string to find 
						//ones that are off by one
						for (int j = 0; j < stringLength; j++)
							{
								if (string.charAt(j) != orginizedByLengthDict[stringLength][i]
										.charAt(j))// indexes them all!!!
									{
										offByOneTracker++;
									}

								if (offByOneTracker >= 2)
									{
										break;
									}

							}

						if (offByOneTracker == 1)
							{
								// System.out.println("Indexing! something!");
								//if a string is off by one, the index(Space) is appended 
								//the string  so that string tokenizer can parse the string
								orginizedByLengthDict[stringLength][index] += " "
										+ i;
							}
					}

				// System.out.println("Done Indexing!");
			}

		/**
		 * 
		 * @param length
		 *            all strings of the requested length
		 * @return
		 */
		public String[] getAllStrings(int length)
			{
				return orginizedByLengthDict[length];
			}

		/**
		 * 
		 * @param start
		 * @return
		 */
		private boolean exist(String start)
			{
				int stringLength = start.length();
				if (orginizedByLengthDict[stringLength] == null)
					{
						return false;
					}
				int arrayLength = orginizedByLengthDict[stringLength].length;
				String temp;
				for (int i = 0; i < arrayLength; i++)
					{
						temp = orginizedByLengthDict[stringLength][i]
								.substring(0, stringLength);

						if (start.equals(temp))
							{
								return true;
							}
					}
				return false;
			}
	}
