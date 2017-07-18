import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class AudioFilePlayer implements Runnable {
	public AudioFilePlayer() {

	}

	public void run()
	{
		while(true)
		{
			try
			{
				String p = "C:\\Users\\Maximillian Holzvoig\\JavaMitFreunden\\GeometryDash\\src\\Techno.mp3";
				FileInputStream in = new FileInputStream(p);
				Player pl = new Player(in);
				pl.play();
			}catch(Exception e)
			{
				e.printStackTrace();			
			}	
		}
	}
}