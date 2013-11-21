/**
 * @author Philip, Nicolas
 */

package main;

import java.awt.image.BufferedImage;

public class GraphicManager {
	public int levels;

	// Mouse
	public BufferedImageLoader mouse;
	
	// DiscoObjects
	public BufferedImageLoader background;
	public BufferedImageLoader bar;
	public BufferedImageLoader dj;
	public BufferedImageLoader dancefloor;
	public BufferedImageLoader table;
	public BufferedImageLoader wc;
	public BufferedImageLoader status;
	public BufferedImageLoader bench;
	public BufferedImageLoader carpet;

	// Human
	public BufferedImageLoader man01;
	public BufferedImageLoader woman01;
	public BufferedImageLoader man02;
	public BufferedImageLoader woman02;

	// Startmenu
	public BufferedImageLoader startMenueBG;
	public BufferedImageLoader startMenueButtons;
	public BufferedImageLoader startImpressum;

	public BufferedImageLoader closeButtons;
	public BufferedImageLoader startPopup;
	public BufferedImageLoader speaker;

	public BufferedImageLoader popup1000x600;

	public BufferedImageLoader drinkButtons;
	public BufferedImageLoader barkeeper;

	public BufferedImageLoader dj_overlay;
	public BufferedImageLoader benchOverlay;
	public BufferedImageLoader dancefloorOverlay;
	public BufferedImageLoader flirtOverlay;

	public BufferedImageLoader statusBG;
	public BufferedImageLoader statusUhr;
	public BufferedImageLoader statusBeenden;
	public BufferedImageLoader statusMusicMute;

	public BufferedImageLoader musicButtons;

	// Progress
	public BufferedImageLoader progress0;
	public BufferedImageLoader progress1;
	public BufferedImageLoader progress2;
	public BufferedImageLoader progress3;
	public BufferedImageLoader progress4;

	// Flirten
	public BufferedImageLoader flirtButton;

	// Sitzen
	public BufferedImageLoader benchButton;

	// Toilette
	public BufferedImageLoader toiletButton;
	public BufferedImageLoader toiletOverlay;

	// Tanzen
	public BufferedImageLoader dancefloorButton;

	public BufferedImageLoader gameOverImage;

	public BufferedImageLoader statMaennlich;
	public BufferedImageLoader statWeiblich;

	public GraphicManager() {
		// Grafiken in Abhaengigkeit zur Bildschirmaufloesung laden
		BufferedImageLoader.setRelationToResolution(true);

		// Grafiken laden
		bar = new BufferedImageLoader("Data/Graphics/", "bar02.png");
		dj = new BufferedImageLoader("Data/Graphics/", "dj02.png");
		dancefloor = new BufferedImageLoader("Data/Graphics/", "tanzflaeche02.png");
		table = new BufferedImageLoader("Data/Graphics/", "tisch02.png");
		wc = new BufferedImageLoader("Data/Graphics/", "wc03.png");

		status = new BufferedImageLoader("Data/Graphics/", "status01.png");
		bench = new BufferedImageLoader("Data/Graphics/", "bank01.png");

		man01 = new BufferedImageLoader("Data/Graphics/", "human03.png");
		man02 = new BufferedImageLoader("Data/Graphics/", "man01.png");
		woman01 = new BufferedImageLoader("Data/Graphics/", "woman01.png");
		woman02 = new BufferedImageLoader("Data/Graphics/", "woman02.png");

		startMenueBG = new BufferedImageLoader("Data/Graphics/", "startbildschirm02.png");
		startMenueButtons = new BufferedImageLoader("Data/Graphics/", "menu_buttons02.png", 1, 8);
		startImpressum = new BufferedImageLoader("Data/Graphics/", "impressum01.png");

		mouse = new BufferedImageLoader("Data/Graphics/", "mouse04.png");

		closeButtons = new BufferedImageLoader("Data/Graphics/", "closebutton01.png", 1, 2);
		startPopup = new BufferedImageLoader("Data/Graphics/", "startpopup01.png");
		speaker = new BufferedImageLoader("Data/Graphics/", "speaker01.png", 1, 2);

		popup1000x600 = new BufferedImageLoader("Data/Graphics/", "popup1000x600_01.png");
		drinkButtons = new BufferedImageLoader("Data/Graphics/", "drinkbuttons02.png", 2, 8);

		statusBG = new BufferedImageLoader("Data/Graphics/", "statusbg04.png");
		statusUhr = new BufferedImageLoader("Data/Graphics/", "uhr01.png");
		statusBeenden = new BufferedImageLoader("Data/Graphics/", "exitButton01.png", 1, 2);

		musicButtons = new BufferedImageLoader("Data/Graphics/", "musicButton01.png", 1, 2);
		barkeeper = new BufferedImageLoader("Data/Graphics/", "barkeeper01.png");

		progress0 = new BufferedImageLoader("Data/Graphics/", "progress0_01.png");
		progress1 = new BufferedImageLoader("Data/Graphics/", "progress1_01.png");
		progress2 = new BufferedImageLoader("Data/Graphics/", "progress2_01.png");
		progress3 = new BufferedImageLoader("Data/Graphics/", "progress3_01.png");
		progress4 = new BufferedImageLoader("Data/Graphics/", "progress4_01.png");

		dj_overlay = new BufferedImageLoader("Data/Graphics/", "dj_overlay.png");

		benchButton = new BufferedImageLoader("Data/Graphics/", "SitztenButton02.png", 1, 2);

		toiletButton = new BufferedImageLoader("Data/Graphics/", "WCButton02.png", 1, 2);
		toiletOverlay = new BufferedImageLoader("Data/Graphics/", "wcoverlay_01.png");

		dancefloorButton = new BufferedImageLoader("Data/Graphics/", "TanzenButton02.png", 1, 2);

		carpet = new BufferedImageLoader("Data/Graphics/", "carpet_01.png");

		benchOverlay = new BufferedImageLoader("Data/Graphics/", "benchoverlay_01.png");
		flirtButton = new BufferedImageLoader("Data/Graphics/", "FlirtenButton01.png", 1, 2);
		flirtOverlay = new BufferedImageLoader("Data/Graphics/", "flirtenoverlay_01.png");

		gameOverImage = new BufferedImageLoader("Data/Graphics/", "gameOver01.png");

		dancefloorOverlay = new BufferedImageLoader("Data/Graphics/", "daceoverlay_01.png");

		statMaennlich = new BufferedImageLoader("Data/Graphics/", "maennlich01.png", 1, 2);
		statWeiblich = new BufferedImageLoader("Data/Graphics/", "weiblich01.png", 1, 2);
	}
}
