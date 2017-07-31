import java.awt.Color;

import javax.swing.JLabel;

public class Time extends JLabel implements Updater {
	private int x;

	public Time(int x, int y) {
		x = 0;
		setBounds(x, y, 500, 100);
		setText(Integer.toString(x));
		setBackground(Color.black);
		setForeground(Color.LIGHT_GRAY);
		setFont(this.getFont().deriveFont(100f));
		new Timer(1000, this);
	}

	public void update() {
		setText(Integer.toString(++x));
	}

	public void reset() {
		setText(Integer.toString(x = 0));
	}

}
