package overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import main.BufferedImageLoader;
import main.GraphicManager;

public class BarOverlay extends Overlay {	
	private enum drinks {
		
	}
	public BarOverlay(final GraphicManager graphicManager, String t) {
		super(graphicManager, t);
		
		// Buttons
		for(int i=0;i<8;i++) {
            JLabel b = new JLabel();
            b.setIcon(new ImageIcon(graphicManager.drinkButtons.getImage(0,i)));
            b.setBounds(BufferedImageLoader.scaleToScreenX(700+i%3*95), BufferedImageLoader.scaleToScreenY(100+i/3*180), BufferedImageLoader.scaleToScreenX(90), BufferedImageLoader.scaleToScreenY(176));
            b.addMouseListener(new A(new ImageIcon(graphicManager.drinkButtons.getImage(0,i)), new ImageIcon(graphicManager.drinkButtons.getImage(1,i))));
            add(b,JLayeredPane.POPUP_LAYER);
		}
	}
	
	class A extends MouseAdapter {
		ImageIcon i;
		ImageIcon h;
		public A(ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(h);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			((JLabel) e.getSource()).setIcon(i);
		}
	}
}
