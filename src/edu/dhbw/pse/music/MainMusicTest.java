package edu.dhbw.pse.music;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolTip;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.List;
import java.util.Random;

public class MainMusicTest {
	public static void main(String[] args) {
		new MainMusicTest().start();
	}

	public void start() {
		MusicFileLoader mfl = new MusicFileLoader();
		List<MusicFile> mf_list = mfl.getMusicList();
		MusicFile mf = mf_list.get(new Random().nextInt(mf_list.size()));
		String file = mf.path;
		String title = mf.title;
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,100);
        final JFXPanel jfxpanel = new JFXPanel();
        frame.add(jfxpanel);
        frame.setVisible(true);
        
        frame.add(new JLabel(title));
        
        
		Media hit = new Media(file);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	}
}
