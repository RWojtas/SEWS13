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
  public BufferedImageLoader bench;
  
  // public BufferedImageLoader humanGraphic;
  public BufferedImageLoader man01;
  public BufferedImageLoader woman01;
  public BufferedImageLoader man02;
  public BufferedImageLoader woman02;
 
  // Startmen�
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
  
  public BufferedImageLoader statusBG;
  public BufferedImageLoader statusUhr;
  public BufferedImageLoader statusBeenden;
  public BufferedImageLoader statusMusicMute;
  
  public BufferedImageLoader musicButtons;
  
  public BufferedImageLoader progress0;
  public BufferedImageLoader progress1;
  public BufferedImageLoader progress2;
  public BufferedImageLoader progress3;
  public BufferedImageLoader progress4;
  
  public BufferedImageLoader border;
  
  //Flirten
  public BufferedImageLoader ButtonFlirten;
  
  // Mouse
  public BufferedImageLoader mouse;
  
  //Sitzen
  public BufferedImageLoader buttonSitzen;
  
  //Toilette
  public BufferedImageLoader buttonWC;
  
  public GraphicManager() {
    
	//Mit diesem Befehl wird jede im Folgenden geladene Grafik
	//in Abhaengigkeit zur Bildschirmaufloesung geladen.
    BufferedImageLoader.setRelationToResolution(true);
    
    //Mit diesem Befehl kann eine Grafiktabelle mit 5 x 2 Bildern geladen werden.
    //humanGraphic = new BufferedImageLoader("Data/Graphics/","area.png",5,2);
    
    //Mit diesem Befehl kann eine einzige Grafik geladen werden.
    //objectGraphic = new BufferedImageLoader("Data/Graphics/","Bar.png");
    
    //background = new BufferedImageLoader("Data/Graphics/","bg.png");
    bar = new BufferedImageLoader("Data/Graphics/","bar02.png");
    dj = new BufferedImageLoader("Data/Graphics/","dj02.png");
    dancefloor = new BufferedImageLoader("Data/Graphics/","tanzflaeche02.png");
    table = new BufferedImageLoader("Data/Graphics/","tisch02.png");
    wc = new BufferedImageLoader("Data/Graphics/","wc01.png");
    
    status = new BufferedImageLoader("Data/Graphics/","status01.png");
    bench = new BufferedImageLoader("Data/Graphics/", "bank01.png");
    
    man01 = new BufferedImageLoader("Data/Graphics/","human03.png");
    man02 = new BufferedImageLoader("Data/Graphics/","man01.png");
    woman01 = new BufferedImageLoader("Data/Graphics/","woman01.png");
    woman02 = new BufferedImageLoader("Data/Graphics/","woman02.png");
    
    startMenueBG = new BufferedImageLoader("Data/Graphics/","startbildschirm02.png");
    startMenueButtons = new BufferedImageLoader("Data/Graphics/","menu_buttons02.png", 1, 8);
    startImpressum = new BufferedImageLoader("Data/Graphics/","impressum01.png");
    
    mouse = new BufferedImageLoader("Data/Graphics/","mouse04.png");
    
    closeButtons = new BufferedImageLoader("Data/Graphics/","closebutton01.png", 1, 2);
    startPopup = new BufferedImageLoader("Data/Graphics/","startpopup01.png");
    speaker = new BufferedImageLoader("Data/Graphics/","speaker01.png",1,2);
    
    popup1000x600 = new BufferedImageLoader("Data/Graphics/","popup1000x600_01.png");
    drinkButtons = new BufferedImageLoader("Data/Graphics/","drinkbuttons02.png", 2, 8);
    
    statusBG = new BufferedImageLoader("Data/Graphics/","statusbg04.png");
    statusUhr = new BufferedImageLoader("Data/Graphics/","uhr01.png");
    statusBeenden = new BufferedImageLoader("Data/Graphics/", "exitButton01.png", 1, 2);
    
    musicButtons = new BufferedImageLoader("Data/Graphics/", "musicButton01.png", 1, 2);
    barkeeper = new BufferedImageLoader("Data/Graphics/", "barkeeper01.png");
    
    progress0 = new BufferedImageLoader("Data/Graphics/", "progress0_01.png");
    progress1 = new BufferedImageLoader("Data/Graphics/", "progress1_01.png");
    progress2 = new BufferedImageLoader("Data/Graphics/", "progress2_01.png");
    progress3 = new BufferedImageLoader("Data/Graphics/", "progress3_01.png");
    progress4 = new BufferedImageLoader("Data/Graphics/", "progress4_01.png");
    
    dj_overlay = new BufferedImageLoader("Data/Graphics/", "dj_overlay.png");
    
    buttonSitzen = new BufferedImageLoader("Data/Graphics/", "ButtonSitzen.png");
    
    buttonWC = new BufferedImageLoader("Data/Graphics", "buttonWC.png");
  }
}
