import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow implements Updater {

	JFrame frame;
	JPanel panel;
	User player;
	public static final int size = 100;
	public static final int width = 1920;
	public static final int height = 1080;
	private static GameWindow instance = new GameWindow();
	ArrayList<Blocks> blocks;
	Time time;
	public static boolean gameover = false;

	private GameWindow() {
		blocks = new ArrayList<Blocks>();
		player = new User();
		new Timer(10, player);
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.getKeyCode()) {
				case 27:
					System.exit(0);
					break;
				case 32:
					player.jump();
					break;
				}
				if (arg0.getKeyCode() == 27) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		panel = new JPanel();
		time = new Time(0, 0);
		panel.add(time);
		panel.setLayout(null);
		frame.setLayout(null);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.setBackground(Color.black);
		frame.add(panel);
		panel.add(player);
		frame.setVisible(true);
		setUp();
	}

	public int checkBottom(int x, int y) // Überprüfung Punkt unten (Übergebener Punkt ist Links Oben)
	{ // 0 kein Boden 1 Boden 2 GameOver
		if (y > height)
			return 2;
		for (Blocks o : blocks) { // TODO <= +24-25
			if (o.onTop(x, y, size, size)) {
				if (o instanceof Friendly)
					return 1;
				else
					return 2;
			}
		}
		return 0;
	}

	public int checkSide(int x, int y) {
		// 0 alles in Ordnung 2 GameOver
		for (Blocks o : blocks) {
			if (o.isSide(x, y, size, size)) {
				return 2;
			}
		}
		return 0;
	}

	public void GameOver() {
		gameover = true;
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
		while (!blocks.isEmpty()) {
			Blocks o = blocks.remove(0);
			panel.remove(o);
		}
		setUp();
		frame.repaint();
	}

	private void setUp() {
		time.reset();
		PatternGenerator.lvl = height - 100;
		for (int i = 0; i < width; i += size) {
			Square s = new Square(i, height - size, size, size, PatternGenerator.c);
			panel.add(s);
			blocks.add(s);
		}

	}

	public void update() {
		if (gameover)
			return;
		scroll();
	}

	public void scroll() {
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).scroll();
			if (blocks.get(i).getX() + size * 2 < 0) {
				blocks.remove(i);
				panel.remove(blocks.get(i));
			}
		}
		if (blocks.get(blocks.size() - 1).getX() < width) {
			PatternGenerator.generatePattern(blocks,blocks.get(blocks.size() - 1).getX() + size);
		}
	}

	public static GameWindow getInstance() {
		return instance;
	}
}
