package music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.MediaPlayer;

import javax.swing.JComponent;

import main.GameLogic;

/**
 * @author Nicolas
 */
public class MusicManager {
	private MusicFileLoader mfl;
	private JFXPanel jfxpanel;
	private MediaPlayer mediaPlayer;
	private int mediaIndex = 0;
	private List<MusicFile> mfList;

	public MusicManager() {
		// new instances of MusicLoader and multi-media Panel
		mfl = new MusicFileLoader();
		jfxpanel = new JFXPanel();
		
		// random order of songs
		mfList = mfl.getMusicList();
		this.shuffle();
		
		// instance of MediaPlayer
		mediaPlayer = new MediaPlayer(mfList.get(mediaIndex).getMedia());
		
		// Define actions of MediaPlayer
		this.defineActions();
	}
	
	private void defineActions() {
		// next MusicFile
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				openNextSong();
			}
		});
	}
	
	private void openNextSong() {
		boolean before = isMute();
		mediaPlayer.stop();
		mediaIndex = (mediaIndex + 1) % mfList.size();	// next MusicFile
		mediaPlayer = new MediaPlayer(mfList.get(mediaIndex).getMedia());
		mute(before);
		mediaPlayer.play();
		defineActions();
		GameLogic.getInstance().updateMusic();
	}
	
	private void shuffle() {
		Collections.shuffle(mfList);
	}

	public JComponent getPanel() {
		return jfxpanel;
	}
	
	public void play() {
		mediaPlayer.play();
	}
	
	public void stop() {
		mediaPlayer.stop();
	}
	
	public void mute(boolean onOff) {
		mediaPlayer.setMute(onOff);
	}
	public boolean isMute() {
		return mediaPlayer.isMute();
	}
	
	public String getSongTitle() {
		return mfList.get(mediaIndex).title;
	}
	
	public String getSongCategory() {
		return mfList.get(mediaIndex).category;
	}
	
	public List<String> getSongCategories() {
		return mfl.getCategoryList();
	}
	
	public void next() {
		openNextSong();
	}
	
	public void requestedCategory(String category) {
		boolean before = mediaPlayer.isAutoPlay();
		if(before) mediaPlayer.stop();	// avoid async-effects...
		int size = mfList.size();
		int index;
		for(int i = 1;i < size;i++) {
			index = (mediaIndex+i)%size;
			if(category.equals(mfList.get(index).category)) {	// is requested category
				// switch next file and requested
				MusicFile next = mfList.get((mediaIndex + 1)%size);	
				MusicFile temp = mfList.get(index);
				mfList.set((mediaIndex + 1)%size, temp);
				mfList.set(index, next);
				break;
			}
		}
		if(before) mediaPlayer.play();	// avoid async-effects...
		
		System.out.println("nächser Song:"+mfList.get((mediaIndex + 1)%size).category+": "+mfList.get((mediaIndex + 1)%size).title);
		
		/*
		mediaPlayer.stop();
		int size = mfList.size();
		int index;
		for(int i = 1;i < size;i++) {
			index = (mediaIndex+i)%size;
			if(category.equals(mfList.get(index).category)) {
				mediaIndex = index;
				defineActions();
			}
		}
		mediaPlayer.play();
		*/
	}
}
