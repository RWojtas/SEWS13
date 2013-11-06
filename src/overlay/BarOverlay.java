package overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import objects.DiscoObject;
import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import main.Highscore;

public class BarOverlay extends Overlay {
	/*  11 - BlauHohn
	 *  12 - RotOchsen
	 *  13 - GelbVögln
	 *  14 - SchwarzKatzerl
	 *  15 - ZitronenLimonade
	 *  16 - Eistee
	 *  17 - Cocktail
	 *  18 - Shot
	 */
	
	public Player player;
	
	public BarOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		// Buttons
		for(int i=0;i<8;i++) {
            JLabel b = new JLabel();
            b.setIcon(new ImageIcon(graphicManager.drinkButtons.getImage(0,i)));
            b.setBounds(BufferedImageLoader.scaleToScreenX(700+i%3*95), BufferedImageLoader.scaleToScreenY(100+i/3*180), BufferedImageLoader.scaleToScreenX(90), BufferedImageLoader.scaleToScreenY(176));
            b.addMouseListener(new A(11+i, new ImageIcon(graphicManager.drinkButtons.getImage(0,i)), new ImageIcon(graphicManager.drinkButtons.getImage(1,i))));
            add(b,JLayeredPane.POPUP_LAYER);
		}
	}
	
	class A extends MouseAdapter {
		ImageIcon i;
		ImageIcon h;
		int action;
		public A(int action, ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
			this.action = action;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Energie:"+player.getEnergy());
			System.out.println("Highscore:"+Highscore.getInstance().getScore());
			DiscoObject.setStatusES(player, action);
			System.out.println("Energie:"+player.getEnergy());
			System.out.println("Highscore:"+Highscore.getInstance().getScore());
			((JLabel) e.getSource()).getParent().setVisible(false);
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
