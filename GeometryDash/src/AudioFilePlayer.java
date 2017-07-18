import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class AudioFilePlayer implements Runnable {
	public AudioFilePlayer() {

	}

	public void run() {
		while (true) {
			try {
				FileInputStream in = new FileInputStream(new File("./Techno.mp3"));
				Player pl = new Player(in);
				pl.play();
			} catch (Exception e) {
			}
		}
	}
}