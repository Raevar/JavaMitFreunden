
public class GeometryDash
{
	public static void main(String[] args)
	{
		GameWindow g = GameWindow.getInstance();
		System.out.println("GEO");
		new Thread(new AudioFilePlayer()).start();
		g.run();
	}
}
