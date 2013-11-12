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
  private static double widthCorrection;
  private static double heightCorrection;
  
  public BufferedImageLoader(String adress, String name, int picsX, int picsY) {
	  loadImageTable(adress, name, picsX, picsY);
  }
  
  public BufferedImageLoader(String adress, String name) {
	  loadImage(adress, name);
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
        Image scaledImage = source.getSubimage(x*source.getWidth()/picsX, y*source.getHeight()/picsY, source.getWidth()/picsX, source.getHeight()/picsY);
        scaledImage = scaledImage.getScaledInstance((int)(resolutionFactor*source.getWidth()/picsX), (int)(resolutionFactor*source.getHeight()/picsY),BufferedImage.SCALE_SMOOTH);
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
  
  //Gibt das Image zurück
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
	    heightCorrection = (deskResolution.getHeight()-resolutionFactor*(standardResolution.getHeight()))/2;
	    
	  } else {
	    resolutionFactor = deskResolution.getHeight()/standardResolution.getHeight();
	    widthCorrection = (deskResolution.getWidth()-resolutionFactor*(standardResolution.getWidth()))/2;
	  }
    } else {
      resolutionFactor = 1;	
    }
  }
  
  /* Types:
   * true  - Zentrierungs-Korrektur erfolgt
   * false - Zentrierungs-Korrektur erfolgt nicht
   */ 
  public static int scaleToScreenX(int value, boolean type) {
	  if(type) {
		  return (int)(resolutionFactor*(double)(value)+widthCorrection); 
	  } else {
		  return (int)(resolutionFactor*(double)(value));
	  }
  }
  
  /* Types:
   * true  - Zentrierungs-Korrektur erfolgt
   * false - Zentrierungs-Korrektur erfolgt nicht
   */ 
  public static int scaleToScreenY(int value, boolean type) {
	  if(type) {
		  return (int)(resolutionFactor*(double)(value)+heightCorrection); 
	  } else {
		  return (int)(resolutionFactor*(double)(value));
	  } 
  }
  
  public void reScaleImageSize(int w, int h) {
	  BufferedImage scaledBufferedImage = null;
	  BufferedImage source = null;
	    
	  Image scaledImage = image.getScaledInstance(w, h,BufferedImage.SCALE_SMOOTH);
	  scaledBufferedImage = new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
	  scaledBufferedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

	  image = scaledBufferedImage;
	  imageWidth = scaledBufferedImage.getWidth();
	  imageHeight = scaledBufferedImage.getHeight();
  }
  
  public static double getWidthCorrection() {
	  return widthCorrection; 
  }
  
  public static double getHeightCorrection() {
	  return heightCorrection; 
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
