import java.awt.Color;
import java.util.ArrayList;

public class PatternGenerator {
	public static int red = 100, blue = 172, green = 50;
	public static Color c = new Color(red, green, blue);
	public static int lvl = GameWindow.height - 100;
	public static int size = 100;

	// Oben Links vom ersten Block
	public static ArrayList<Blocks> generatePattern(ArrayList<Blocks> pattern, int x) {
		red += (int) (Math.random() * 11) - 5;
		blue += (int) (Math.random() * 11) - 5;
		green += (int) (Math.random() * 11) - 5;
		if (red < 30)
			red = 200;
		else if (red > 220)
			red = 50;
		if (green < 30)
			green = 200;
		else if (green > 220)
			green = 50;
		if (blue < 30)
			blue = 200;
		else if (blue > 220)
			blue = 50;
		c = new Color(red, green, blue);
		int e = (int) (Math.random() * 7);
		switch (e) {
		case 0:
			simpleWay(x, pattern);
			break;
		case 1:
			if (lvl < GameWindow.height - size)
				stepDowneasy(x, pattern);
			break;
		case 2:
			if (lvl > 280)
				stepUpeasy(x, pattern);
			break;
		case 3:
			gap(x, pattern);
			break;
		case 4:
			if(lvl > 380)
				stepUpHeavy(x, pattern);
			break;
		case 5:
			if(lvl > 1080-size)
			gapDownEasy(x,pattern);
			break;
		case 6:
			gapDownComplete(x,pattern);
		}
		return pattern;

	}

	private static void simpleWay(int x, ArrayList<Blocks> pattern) {
		Blocks b = new Square(x, lvl, size, size, c);
		pattern.add(b);
		GameWindow.getInstance().panel.add(b);
	}

	private static void stepUpeasy(int x, ArrayList<Blocks> pattern) {
		Blocks b = new Square(x, lvl -= size, size, size, c);
		pattern.add(b);
		simpleWay(x += size, pattern);
		simpleWay(x += size, pattern);
		GameWindow.getInstance().panel.add(b);
	}

	private static void stepUpHeavy(int x, ArrayList<Blocks> pattern) {
		simpleWay(x, pattern);
		simpleWay(x+=size,pattern);
		Blocks b = new Square(x, lvl - size, size, size, c);
		pattern.add(b);
		GameWindow.getInstance().panel.add(b);
		b = new Square(x, lvl - 2*size, size, size, c);
		pattern.add(b);
		GameWindow.getInstance().panel.add(b);
	}

	private static void stepDowneasy(int x, ArrayList<Blocks> pattern) {
		Blocks b = new Square(x, lvl += size, size, size, c);
		pattern.add(b);
		simpleWay(x += size, pattern);
		simpleWay(x += size, pattern);
			GameWindow.getInstance().panel.add(b);
	}

	private static void gap(int x, ArrayList<Blocks> pattern) {
		simpleWay(x, pattern);
		simpleWay(x += size, pattern);
		simpleWay(x += size, pattern);
		}
	
	private static void gapDownEasy(int x, ArrayList<Blocks> pattern) {
		simpleWay(x += size, pattern);
		Blocks b = new Square(x += 4*size, lvl - size, size, size, c);
		pattern.add(b);
		GameWindow.getInstance().panel.add(b);
	}
	private static void gapDownComplete(int x, ArrayList<Blocks> pattern) {
		simpleWay(x += size, pattern);
		Blocks b = new Square(x += 4 + (1080-lvl)/size * size, lvl  =1080 - size, size, size, c);
		pattern.add(b);
		GameWindow.getInstance().panel.add(b);
	}

}
