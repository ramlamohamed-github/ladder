import java.awt.EventQueue;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class MainTest
	{

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										MainTest frame = new MainTest();

									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the frame.
		 */
		public MainTest()
			{
				JFrame f = new JFrame("???");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				JApplet ap = new Dictionary("");

				f.setResizable(false);
				f.add("Center", ap);
				f.pack();
				f.setVisible(true);
			}

	}
