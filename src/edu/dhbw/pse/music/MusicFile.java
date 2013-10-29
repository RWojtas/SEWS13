package edu.dhbw.pse.music;

import javafx.scene.media.Media;

/**
 * @author Nicolas
 */
public class MusicFile {
	public String category;
	public String title;
	public String path;
	private Media media;

	public MusicFile(String title, String category, String path) {
		this.title = title;
		this.category = category;
		this.path = path;

		this.media = new Media(path);
	}

	public Media getMedia() {
		return media;
	}
}
