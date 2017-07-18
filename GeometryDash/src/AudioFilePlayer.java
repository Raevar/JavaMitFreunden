import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class AudioFilePlayer implements Runnable {

	public void run() {
		while (true) {
			try {
				new Player(new FileInputStream(new File("./Techno.mp3"))).play();
			} catch (Exception e) {
			}
		}
	}
}