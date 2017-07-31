
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Square extends JPanel
{
	private int			x, y;
	private boolean		mine;
	private boolean		shown	= false;
	public JButton		button;
	private int			mineCount;
	private Cellstate	state;
	JTextArea			area;

	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.mine = false;
		state = Cellstate.hidden;
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		button = new JButton();
		button.setLayout(null);
		button.setBounds(3, 3, 54, 54);
		button.addActionListener(alistener());
		button.addMouseListener(mlistener());
		button.setBackground(Color.DARK_GRAY);
		button.setBorderPainted(true);
		button.setFont(button.getFont().deriveFont(32f));
		button.setForeground(Color.RED);
		add(button);
		area = new JTextArea();
	}

	public void showAll()
	{
		if (mine == true)
		{
			area.setText("M");
			area.setBackground(Color.RED);
		} else
		{
			area.setBackground(Color.LIGHT_GRAY);
			setBackground(Color.LIGHT_GRAY);
			if (mineCount != 0)
				area.setText(Integer.toString(mineCount));
		}
		area.setFont(area.getFont().deriveFont(32f));
		area.setBounds(0,0,60,60);
		area.setEditable(false);
		add(area);
		remove(button);
	}

	public void countMines()
	{
		mineCount = GameWindow.getInstance().countMines(x, y);
	}

	public void setMine(boolean mine)
	{
		this.mine = mine;
	}

	public boolean getMine()
	{
		return mine;
	}

	private void update()
	{
		switch (state)
		{
			case hidden:
				button.setText("");
				break;
			case marked:
				button.setText("T");
				break;
			case questioned:
				button.setText("?");
				break;
			case shown:
				break;
			default:
				break;
		}
		GameWindow.getInstance().repaint();
	}

	public MouseListener mlistener()
	{
		MouseListener l = new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.getModifiers() == 4)
				{
					switch (state)
					{
						case hidden:
							state = Cellstate.marked;
							break;
						case marked:
							state = Cellstate.questioned;
							break;
						case questioned:
							state = Cellstate.hidden;
							break;
						case shown:
							return;
					}
				}
				update();
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}

		};
		return l;
	}

	public ActionListener alistener()
	{
		ActionListener l = new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				show();
			}
		};
		return l;
	}

	public void show()
	{
		if (state == Cellstate.marked)
			return;
		state = Cellstate.shown;
		GameWindow.getInstance().victory();
		shown = true;
		if (mine == true)
		{
			area.setText("M");
			area.setBackground(Color.RED);
			setBackground(Color.red);
			GameWindow.getInstance().GameOver();
		} else
		{
			area.setBackground(Color.LIGHT_GRAY);
			setBackground(Color.LIGHT_GRAY);
			if (mineCount == 0)
			{
				GameWindow.getInstance().floodFill(x, y);
			} else
			{
				area.setText(Integer.toString(mineCount));
			}
		}
		area.setFont(area.getFont().deriveFont(32f));
		area.setBounds(5, 5, 40, 40);
		area.setEditable(false);
		add(area);
		remove(button);
		area.setFont(area.getFont().deriveFont(32f));
		area.setBounds(5, 5, 40, 40);
		area.setEditable(false);
		add(area);
		remove(button);
		GameWindow.getInstance().repaint();
		return;
	}

	public boolean isShown()
	{
		return shown;
	}
}
