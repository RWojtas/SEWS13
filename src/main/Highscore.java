package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Nicolas
 */
public class Highscore {
	private static final String HIGHSCORE_FILE = "data\\highscore.txt";
	private static Highscore instance;
	private int score;
	private ArrayList<Integer> list;

	/**
	 * Konstruktor
	 */
	private Highscore() {
		score = 0;
		list = readHighscoreFile();
	}

	/**
	 * Singeltone
	 * 
	 * @return Instanz des Highscores
	 */
	public static Highscore getInstance() {
		instance = (instance == null) ? new Highscore() : instance;
		return instance;
	}

	/**
	 * gibt Punktestand zurück
	 * 
	 * @return Score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Punktestand setzen
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Punktestand speichern
	 */
	public void saveScore() {
		this.list.add(score);
		saveHighscoreFile();
	}

	/**
	 * Bonuspunkte hinzufügen
	 * 
	 * @param bonus
	 *            Pluspunkte
	 */
	public void setBonus(int bonus) {
		this.score += bonus;
	}

	/**
	 * Manuspunkte abziehen
	 * 
	 * @param manus
	 *            Minuspunkte
	 */
	public void setManus(int manus) {
		this.score -= manus;
	}

	/**
	 * Save highscores
	 */
	public void saveHighscoreFile() {
		FileWriter file_writer;
		StringBuffer buff = new StringBuffer();
		try {
			file_writer = new FileWriter(new File(HIGHSCORE_FILE), false);

			Collections.sort(list);

			for (Integer a : list) {
				buff.append(a.toString() + ";");
			}

			file_writer.write(buff.toString());
			file_writer.close();
		} catch (IOException e) {
		}

	}

	/**
	 * 
	 * @return Highscorelist
	 */
	public ArrayList<Integer> readHighscoreFile() {
		FileReader file_reader;
		int c;
		StringBuffer buff = new StringBuffer();
		ArrayList<Integer> l = new ArrayList<Integer>();

		try {
			file_reader = new FileReader(new File(HIGHSCORE_FILE));

			do {
				c = file_reader.read();
				if (c == ';' || c == -1 && buff.length() > 0) {
					l.add(Integer.parseInt(buff.toString()));
					buff = new StringBuffer();

				} else
					buff.append((char) c);
			} while (c != -1);

			file_reader.close();
		} catch (IOException e) {
		}

		Comparator<Integer> comparator = Collections.reverseOrder();

		Collections.sort(l, comparator);

		return l;
	}
}
