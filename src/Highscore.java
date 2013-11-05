/**
 * @author Nicolas
 */


public class Highscore {
	private static Highscore instance;
	private int score;
	/**
	 * Konstruktor
	 */
	private Highscore() {
		this.score = 0;
	}
	/**
	 * Singeltone
	 * @return Instanz des Highscores
	 */
	public static Highscore getInstance() {
		instance = (instance == null) ?  new Highscore() : instance;
		return instance;
	}
	/**
	 * gibt Punktestand zurück
	 * @return Score
	 */
	public int getScore() {
		return this.score;
	}
	/**
	 * Punktestand setzen
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Bonuspunkte hinzufügen
	 * @param bonus Pluspunkte
	 */
	public void setBonus(int bonus) {
		this.score += bonus;
	}
	/**
	 * Manuspunkte abziehen
	 * @param manus Minuspunkte
	 */
	public void setManus(int manus) {
		this.score += manus;
	}
}
