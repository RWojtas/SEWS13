package main;
import java.awt.image.BufferedImage;

public class GraphicManager {
  public int levels;

  // public BufferedImageLoader objectGraphic;
  public BufferedImageLoader background;
  public BufferedImageLoader bar;
  public BufferedImageLoader dj;
  public BufferedImageLoader dancefloor;
  public BufferedImageLoader table;
  public BufferedImageLoader wc;
  public BufferedImageLoader status;
  
  // public BufferedImageLoader humanGraphic;
  public BufferedImageLoader human;
 
  // Startmenü
  public BufferedImageLoader startMenueBG;
  public BufferedImageLoader startMenueButtons;
  
  // Mouse
  public BufferedImageLoader mouse;
  
  public GraphicManager() {
    
	//Mit diesem Befehl wird jede im Folgenden geladene Grafik
	//in Abhaengigkeit zur Bildschirmaufloesung geladen.
    BufferedImageLoader.setRelationToResolution(true);
    
    //Mit diesem Befehl kann eine Grafiktabelle mit 5 x 2 Bildern geladen werden.
    //humanGraphic = new BufferedImageLoader("Data/Graphics/","area.png",5,2);
    
    //Mit diesem Befehl kann eine einzige Grafik geladen werden.
    //objectGraphic = new BufferedImageLoader("Data/Graphics/","Bar.png");
    
    //background = new BufferedImageLoader("Data/Graphics/","bg.png");
    bar = new BufferedImageLoader("Data/Graphics/","bar01.png");
    dj = new BufferedImageLoader("Data/Graphics/","dj01.png");
    dancefloor = new BufferedImageLoader("Data/Graphics/","tanzflaeche01.png");
    table = new BufferedImageLoader("Data/Graphics/","tisch01.png");
    wc = new BufferedImageLoader("Data/Graphics/","wc01.png");
    human = new BufferedImageLoader("Data/Graphics/","mensch01.png");
    status = new BufferedImageLoader("Data/Graphics/","status01.png");
    
    
    startMenueBG = new BufferedImageLoader("Data/Graphics/","startbildschirm01.png");
    startMenueButtons = new BufferedImageLoader("Data/Graphics/","menu_buttons01.png", 1, 8);
    
    mouse = new BufferedImageLoader("Data/Graphics/","mouse02.png");
  }
}
