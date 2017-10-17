import java.util.ArrayList;

public class Pattern {

	ArrayList<Blocks> blocks;
	private Blocks[] simpleGround = new Blocks[3];

	public Pattern() {
		for (int i = 0; i < 3; i++) {
			blocks.add(simpleGround[i]);
		}
	}

}
