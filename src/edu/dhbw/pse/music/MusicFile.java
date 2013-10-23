package edu.dhbw.pse.music;

/**
 * @author Nicolas
 */
public class MusicFile {
	public String category;
	public String title;
	public String path;

	public MusicFile(String title, String category, String path) {
		this.title = title;
		this.category = category;
		this.path = path;
	}
}
