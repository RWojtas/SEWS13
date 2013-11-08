package main;

import javax.swing.Icon;
import javax.swing.JLabel;

public class PSELabel extends JLabel {

	public PSELabel() {
		// TODO Auto-generated constructor stub
	}

	public PSELabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public PSELabel(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	public PSELabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public PSELabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public PSELabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setBounds(int x, int y, int w, int h) {
    	setBounds(BufferedImageLoader.scaleToScreenX(x),BufferedImageLoader.scaleToScreenY(y),w,h);
    }
	
	@Override
	public void setLocation(int x, int y) {
    	setLocation(BufferedImageLoader.scaleToScreenX(x),BufferedImageLoader.scaleToScreenY(y));
    }

}
