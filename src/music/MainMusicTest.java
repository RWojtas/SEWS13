package music;

import javax.swing.JFrame;

public class MainMusicTest {
	public static void main(String[] args) {
		new MainMusicTest().start();
	}

	public void start() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 100);
		frame.setVisible(true);

		// neuer Musik-Manager
		MusicManager a = new MusicManager();

		frame.add(a.getPanel());
		
		a.play();
		//a.requestedCategory("Indie");
		
	}
}
