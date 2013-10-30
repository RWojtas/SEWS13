import java.awt.image.BufferedImage;

public class GraphicManager {
  public int levels;

  public BufferedImageLoader objectGraphic;
  public BufferedImageLoader humanGraphic;
  public BufferedImageLoader background;

  public GraphicManager() {
    
	//Mit diesem Befehl wird jede im Folgenden geladene Grafik
	//in Abhaengigkeit zur Bildschirmaufloesung geladen.
    BufferedImageLoader.setRelationToResolution(true);
    
    //Mit diesem Befehl kann eine Grafiktabelle mit 5 x 2 Bildern geladen werden.
    //humanGraphic = new BufferedImageLoader("Data/Graphics/","area.png",5,2);
    
    //Mit diesem Befehl kann eine einzige Grafik geladen werden.
    objectGraphic = new BufferedImageLoader("Data/Graphics/","Bar.png");
    
    background = new BufferedImageLoader("Data/Graphics/dark/","bg.png");
  }
}
