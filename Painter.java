import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Painter extends Component
	{

		String			dictionarySize	= "Words in Dictionary = 0";
		int				progress		= 0;
		boolean			resultIsReady	= false;
		boolean			loadSuccess		= false;
		private String	result;
		private long	timeTaken;
		private double	cost;

		/**
		 * @return the dimensions of the frame
		 */
		public Dimension getPreferredSize()
			{
				return new Dimension(400, 400);
			}

		public void paint(Graphics g2)
			{
				g2.setColor(Color.red);
				g2.drawString(dictionarySize, 5, 385);
				g2.setColor(Color.black);
				g2.drawRect(0, 350, 350, 15);
				g2.setColor(Color.gray);
				if (progress > 0 && progress < 100)
					{
						int start = 1;
						for (int i = 0; i < progress; i++)
							{
								g2.setColor(Color.black);
								g2.drawString("Indexing Dictionary......", 2,
										345);
								g2.setColor(Color.gray);
								g2.fillRect(start, 351, 1, 14);
								start += 2;
							}
					}

				if (loadSuccess)
					{
						g2.setColor(Color.black);
						g2.drawString("Done", 353, 364);
						g2.drawString("Elapsed time for task = " + timeTaken
								+ " ms", 5, 400);
						g2.setColor(Color.green);
						g2.fillRect(1, 351, 349, 14);
						progress = 0;
					}

				if (resultIsReady == true)
					{

						g2.setColor(Color.black);
						g2.drawString("Elapsed time for task = " + timeTaken
								+ " ms", 5, 400);
						g2.drawString("Total cost is = " + cost, 230, 400);
						StringTokenizer st;
						st = new StringTokenizer(result);
						int manyTokens = st.countTokens();
						int dX = 0;
						int dY = 20;
						g2.setFont(new Font(result, Font.BOLD, 18));
						g2.setColor(Color.RED);
						for (int j = 0; j < manyTokens; j++)
							{
								g2.drawString(st.nextToken(), dX, dY);
								dX += 15;
								if (cost > 16)
									{
										dY += 330 / cost;
									} else
									dY += 20;

							}

						resultIsReady = false;
					}

			}

		public void paintString(int length)
			{
				dictionarySize = "Words in Dictionary = " + length;
				repaint();

			}

		public void updateProgress(int k, boolean done)
			{
				if (!done)
					{
						loadSuccess = false;
						progress += k;
						repaint();
					}
				if (done)
					{
						loadSuccess = true;
						progress = 100;
						repaint();

					}

			}

		public void paintResult(String s2)
			{
				resultIsReady = true;
				result = s2;
				repaint();

			}

		public void setTime(long time)
			{
				this.timeTaken = time;
				repaint();
			}

		public void setCost(double c)
			{
				this.cost = c;
			}
	}
