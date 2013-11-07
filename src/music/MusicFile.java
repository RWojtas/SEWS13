package music;

import javafx.scene.media.Media;

/**
 * @author Nicolas
 */
public class MusicFile {
	String category;
	String title;
	String path;
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
