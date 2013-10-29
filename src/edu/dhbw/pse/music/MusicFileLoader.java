package edu.dhbw.pse.music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

/**
 * 
 * @author Nicolas
 * 
 */
public class MusicFileLoader {
	final static String START_DIR = "C:\\Users\\Nicolas\\Desktop\\test";
	final static String[] MUSIC_TYPES = { "application/octet-stream" };
	static List<String> music_type = new ArrayList<String>();

	private List<MusicFile> music_list = new ArrayList<MusicFile>();
	private List<String> categories = new ArrayList<String>();
	static {
		for (String s : MUSIC_TYPES)
			music_type.add(s);
	}

	public MusicFileLoader() {
		File home = new File(START_DIR);
		for (File dir : home.listFiles()) {
			if (dir.isDirectory()) {
				if (!categories.contains(dir.getName()))
					categories.add(dir.getName());
				for (File music : dir.listFiles()) {
					if (music.isFile()
							&& music_type.contains(new MimetypesFileTypeMap()
									.getContentType(music))
							&& getRightPartFilename(music.getName()).equals("mp3"))
						music_list.add(new MusicFile(this
								.getLeftPartFilename(music.getName()), dir
								.getName(), music.toURI().toString()));
				}
			}
		}
	}

	/**
	 * get all files as music file
	 * 
	 * @return list of music files
	 */
	public List<MusicFile> getMusicList() {
		return music_list;
	}

	/**
	 * get all files as music file
	 * 
	 * @param category
	 *            filter for category
	 * @return list of music files
	 */
	public List<MusicFile> getMusicList(String category) {
		List<MusicFile> tmp = new ArrayList<MusicFile>();
		for (MusicFile mf : music_list)
			if (mf.category.equals(category))
				tmp.add(mf);

		return tmp;
	}

	/**
	 * 
	 * @return category list
	 */
	public List<String> getCategoryList() {
		return categories;
	}

	/**
	 * get filename without extension
	 * 
	 * @param name
	 *            full filename
	 * @return filename
	 */
	private String getLeftPartFilename(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(0, pos);
	}

	/**
	 * get extension
	 * 
	 * @param name
	 *            full filename
	 * @return file extension
	 */
	private String getRightPartFilename(String name) {
		int pos = name.lastIndexOf(".");
		return name.substring(pos + 1, name.length());
	}
}
