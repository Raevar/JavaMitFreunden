
public class GeometryDash {
	public static void main(String[] args) {
		new Thread(new AudioFilePlayer()).start();
		GameWindow g = GameWindow.getInstance();
		new Timer(1, g);
	}
}
