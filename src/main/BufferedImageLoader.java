package main;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.net.*;


public class BufferedImageLoader {

  private BufferedImage[][] imageTable;
  private BufferedImage image;
  private int imageHeight;
  private int imageWidth;
  private static Dimension deskResolution;
  private static Dimension standardResolution = new Dimension(1366,768); //Beispiel Wert: (1920,1080)
  private static double resolutionFactor = 1;
  
  public BufferedImageLoader(String adress, String name, int picsX, int picsY) {
    imageTable = new BufferedImage[picsX][picsY];
    BufferedImage scaledBufferedImage = null;
    BufferedImage source = null;
    
    URL pic_url = getClass().getResource("../"+adress+""+name);

    try {
      source = ImageIO.read(pic_url);
      System.out.println("Erfolgreich geladen: "+pic_url);
    } catch (IOException e) {
      System.out.println("Fehler beim laden von: "+pic_url);
    }
    
    int x;
    for(int y=0;y<picsY;y++) {
      for(x=0;x<picsX;x++) {
    	
        Image scaledImage = source.getSubimage(x*source.getWidth()/picsX, y*source.getHeight()/picsY, source.getWidth()/picsX, source.getHeight()/picsY).getScaledInstance((int)(resolutionFactor*source.getWidth()/picsX), (int)(resolutionFactor*source.getHeight()/picsY),BufferedImage.SCALE_SMOOTH);
        scaledBufferedImage = new BufferedImage((int)(resolutionFactor*source.getWidth()/picsX), (int)(resolutionFactor*source.getHeight()/picsY),BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);  
    	imageTable[x][y] = scaledBufferedImage;
    	
      }
    }
    imageWidth = scaledBufferedImage.getWidth();
    imageHeight = scaledBufferedImage.getHeight();
  }
  
  public BufferedImageLoader(String adress, String name) {
    BufferedImage scaledBufferedImage = null;
    BufferedImage source = null;

    URL pic_url = getClass().getResource("../"+adress+""+name);
    
    try {
      source = ImageIO.read(pic_url);
      Image scaledImage = source.getScaledInstance((int)(resolutionFactor*source.getWidth()), (int)(resolutionFactor*source.getHeight()),BufferedImage.SCALE_SMOOTH);
      scaledBufferedImage = new BufferedImage((int)(resolutionFactor*source.getWidth()), (int)(resolutionFactor*source.getHeight()),BufferedImage.TYPE_INT_ARGB);
      scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
      System.out.println("Erfolgreich geladen: "+pic_url);
    } catch (IOException e) {
      System.out.println("Fehler beim laden von: "+pic_url);
    }

    image = scaledBufferedImage;
    imageWidth = scaledBufferedImage.getWidth();
    imageHeight = scaledBufferedImage.getHeight();
  }
  
  public void loadImageTable(String adress, String name, int picsX, int picsY) {
    imageTable = new BufferedImage[picsX][picsY];
    BufferedImage scaledBufferedImage = null;
    BufferedImage source = null;
    
    URL pic_url = getClass().getResource("../"+adress+""+name);

    try {
      source = ImageIO.read(pic_url);
      System.out.println("Erfolgreich geladen: "+pic_url);
    } catch (IOException e) {
      System.out.println("Fehler beim laden von: "+pic_url);
    }

    int x;
    for(int y=0;y<picsY;y++) {
      for(x=0;x<picsX;x++) {
        Image scaledImage = source.getSubimage(x*source.getWidth()/picsX, y*source.getHeight()/picsY, source.getWidth()/picsX, source.getHeight()/picsY).getScaledInstance((int)(resolutionFactor*source.getWidth()/picsX), (int)(resolutionFactor*source.getHeight()/picsY),BufferedImage.SCALE_SMOOTH);
        scaledBufferedImage = new BufferedImage((int)(resolutionFactor*source.getWidth()/picsX), (int)(resolutionFactor*source.getHeight()/picsY),BufferedImage.TYPE_INT_ARGB);
        scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);  
    	imageTable[x][y] = scaledBufferedImage;  	
      }
    }
    imageWidth = scaledBufferedImage.getWidth();
    imageHeight = scaledBufferedImage.getHeight();
  }
  
  public void loadImage(String adress, String name) {
    BufferedImage scaledBufferedImage = null;
    BufferedImage source = null;
    
    URL pic_url = getClass().getResource("../"+adress+""+name);
    
    try {
      source = ImageIO.read(pic_url);
      Image scaledImage = source.getScaledInstance((int)(resolutionFactor*source.getWidth()), (int)(resolutionFactor*source.getHeight()),BufferedImage.SCALE_SMOOTH);
      scaledBufferedImage = new BufferedImage((int)(resolutionFactor*source.getWidth()), (int)(resolutionFactor*source.getHeight()),BufferedImage.TYPE_INT_ARGB);
      scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);
      System.out.println("Erfolgreich geladen: "+pic_url);
    } catch (IOException e) {
      System.out.println("Fehler beim laden von: "+pic_url);
    }

    image = scaledBufferedImage;
    imageWidth = scaledBufferedImage.getWidth();
    imageHeight = scaledBufferedImage.getHeight();
  }
  
  //Gibt die das Image zurück
  public BufferedImage getImage() {
    return this.image;
  }

  //Gibt die ImageTable zurück
  public BufferedImage[][] getImageTable() {
    return this.imageTable;
  }

  //Gibt ein Element von ImageTable zurück
  public BufferedImage getImage(int x, int y) {
    return this.imageTable[x][y];
  }
  
  public int getImageHeight() {
    return this.imageHeight;
  }
  
  public int getImageWidth() {
    return this.imageWidth;
  }
  
  //Kann true gesetzt werden, um die grafischen Elemente in Relation zur Desktopauflösung zu erzeugen oder false um dies zu vermeiden
  public static void setRelationToResolution(boolean value) {
    if(value) {
	  deskResolution = Toolkit.getDefaultToolkit().getScreenSize();

	  if(deskResolution.getWidth()/standardResolution.getWidth() < deskResolution.getHeight()/standardResolution.getHeight()) {
	    resolutionFactor = deskResolution.getWidth()/standardResolution.getWidth();
	  } else {
	    resolutionFactor = deskResolution.getHeight()/standardResolution.getHeight();
	  }
    } else {
      resolutionFactor = 1;	
    }
  }
  
  public static int scaleToScreenX(int xPos) {
	  return (int)(resolutionFactor*(double)(xPos));
  }

  public static int scaleToScreenY(int yPos) {
	  return (int)(resolutionFactor*(double)(yPos));  
  }
  
  public static Dimension getStandardResolution() {
    return standardResolution;
  }
  
  public static Dimension getDeskResolution() {
	    return deskResolution;
  }
  
  public static void setStandardResolution(Dimension d) {
	standardResolution = d;  
  }
}
