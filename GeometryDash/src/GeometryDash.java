
public class GeometryDash
{
	public static void main(String[] args)
	{
		GameWindow g = GameWindow.getInstance();
		new Thread(new AudioFilePlayer()).start();
		g.run();
	}
}
