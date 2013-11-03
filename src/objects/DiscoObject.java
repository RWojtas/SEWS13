package objects;

import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * @author Anna
 * @author Silke
 */

public abstract class DiscoObject extends JLabel {

	protected Position position;
	protected boolean accessible;
	
	public DiscoObject(BufferedImage image, int x, int y) {
		position = new Position(x,y);
		setIcon(new ImageIcon(image.getSubimage(0,0,image.getWidth(),image.getHeight())));
		setBounds(position.getXPosition(),position.getYPosition(),image.getWidth(),image.getHeight());
		setOpaque(false);
	}
	
	public void moveObject(int x, int y) {
		position.setPosition(x, y);
		setLocation(position.getXPosition(),position.getYPosition());
	}

	public int getPositionX() {
		return position.getXPosition();
	}

	public int getPositionY() {
		return position.getYPosition();
	}
}
