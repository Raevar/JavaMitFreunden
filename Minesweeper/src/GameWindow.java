
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameWindow extends JFrame implements Runnable
{
	private static GameWindow	instance	= new GameWindow();
	private Square[][]			square;
	private int					minecount;
	private JTextArea			area;
	private JButton				yes, no;
	public int					hiddenCells;

	private GameWindow()
	{
		setBounds(660, 240, 600, 600);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBackground(Color.BLACK);
		setVisible(true);
		area = new JTextArea();
		area.setText("Do you want to restart?");
		area.setBounds(0, (getHeight() - 120) / 2, 600, 120);
		area.setBackground(Color.DARK_GRAY);
		area.setForeground(Color.BLACK);
		area.setFont(area.getFont().deriveFont(50f));
		area.setEditable(false);
		yes = new JButton("yes");
		yes.setFont(yes.getFont().deriveFont(32f));
		yes.setBounds(0, (getHeight() + 120) / 2, 300, 120);
		yes.setBackground(Color.DARK_GRAY);
		yes.setForeground(Color.GREEN);
		yes.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				restart();
			}

		});
		no = new JButton("no");
		no.setFont(no.getFont().deriveFont(32f));
		no.setBounds(300, (getHeight() + 120) / 2, 300, 120);
		no.setBackground(Color.DARK_GRAY);
		no.setForeground(Color.red);
		no.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}

		});
		area.setVisible(false);
		no.setVisible(false);
		yes.setVisible(false);
		add(area);
		add(yes);
		add(no);

	}

	public void run()
	{
		square = new Square[10][10];
		hiddenCells = square.length * square[0].length;
		minecount = 15;
		fill();
		JPanel p = new JPanel();
		p.setBounds(450, 450, 50, 50);
		add(p);
		repaint();
		remove(p);
	}

	public void restart()
	{
		for (int x = 0; x < square.length; x++)
			for (int y = 0; y < square[0].length; y++)
				remove(square[x][y]);
		repaint();
		area.setVisible(false);
		no.setVisible(false);
		yes.setVisible(false);
		run();
	}

	public void GameOver()
	{
		showAll();
		area.setVisible(true);
		no.setVisible(true);
		yes.setVisible(true);
	}

	public void victory()
	{
		hiddenCells--;
		if (hiddenCells == minecount)
		{
			area.setText("SUCCESS \t again?");
			area.setVisible(true);
			no.setVisible(true);
			yes.setVisible(true);
		}
	}

	private void showAll()
	{
		for (int i = 0; i < square.length; i++)
			for (int j = 0; j < square[0].length; j++)
			{
				if ((square[i][j].getY() + square[i][j].getHeight() > area.getY()
						&& square[i][j].getY() < no.getY() + no.getHeight()))
				{
					square[i][j].button.setVisible(false);
					square[i][j].setBackground(Color.LIGHT_GRAY);
				} else
				{
					if (!square[i][j].isShown())
						square[i][j].showAll();

				}
			}
	}

	public void floodFill(int x, int y)
	{
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				if (x + i >= 0 && x + i < square.length && y + j >= 0 && y + j < square[0].length
						&& !square[x + i][y + j].isShown())
					square[x + i][y + j].show();
	}

	public int countMines(int x, int y)
	{
		int n = 0;
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
			{
				if (x + i >= 0 && x + i < square.length && y + j >= 0 && y + j < square[0].length
						&& square[x + i][y + j].getMine())
				{
					n++;
				}
			}
		return n;
	}

	private void fill()
	{
		for (int x = 0; x < square.length; x++)
			for (int y = 0; y < square[0].length; y++)
			{
				square[x][y] = new Square(x, y);
				square[x][y].setBounds(x * 60, y * 60, 60, 60);
				add(square[x][y]);
			}
		for (int i = 0; i < minecount; i++)
		{
			boolean b = true;
			do
			{
				int x = (int) (Math.random() * square.length);
				int y = (int) (Math.random() * square.length);
				if (!square[x][y].getMine())
				{
					square[x][y].setMine(true);
					b = false;
				}

			} while (b);
		}
		for (int x = 0; x < square.length; x++)
			for (int y = 0; y < square[0].length; y++)
				square[x][y].countMines();
	}

	public static GameWindow getInstance()
	{
		return instance;
	}

}