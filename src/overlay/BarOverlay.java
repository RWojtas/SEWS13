package overlay;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import objects.Bar;
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
	static final int NUM_BUT = 8;
	public Player player;
	public Bar bar;
	JLabel buttons[] = new JLabel[NUM_BUT];
	Act actions[] = new Act[NUM_BUT];
	
	public BarOverlay(final GraphicManager graphicManager, Player player, String t) {
		super(graphicManager, t);
		this.player = player;
		
		// Buttons
		for(int i=0;i<NUM_BUT;i++) {
            buttons[i] = new JLabel();
            buttons[i].setIcon(new ImageIcon(graphicManager.drinkButtons.getImage(0,i)));
            buttons[i].setBounds(BufferedImageLoader.scaleToScreenX(700+i%3*95), BufferedImageLoader.scaleToScreenY(100+i/3*180), BufferedImageLoader.scaleToScreenX(90), BufferedImageLoader.scaleToScreenY(176));
            actions[i] = new Act(11+i, new ImageIcon(graphicManager.drinkButtons.getImage(0,i)), new ImageIcon(graphicManager.drinkButtons.getImage(1,i)));
            add(buttons[i],JLayeredPane.POPUP_LAYER);
		}
		enableActions();
	}
	
	public void setVisible(boolean on) {
		super.setVisible(on);
		if (actions != null)
			if(on) enableActions();
			else disableActions();
	}
	
	private void enableActions() {
		for(int i=0;i<NUM_BUT;i++) {
			buttons[i].addMouseListener(actions[i]);
		}
	}
	
	private void disableActions() {
		for(int i=0;i<NUM_BUT;i++) {
			buttons[i].removeMouseListener(actions[i]);
		}
	}
	
	class Act extends MouseAdapter {
		ImageIcon i;
		ImageIcon h;
		int action;
		MouseEvent e;
		public Act(int action, ImageIcon i, ImageIcon h) {
			this.i = i;
			this.h = h;
			this.action = action;
		}
		@Override
		public void mouseClicked(final MouseEvent e) {
			this.e = e;
			player.setActivityTimer(600);
			//if(player.getActivityTimer()==0){
			new Thread(new Runnable(){
				@Override
				public void run() {
					System.out.println("Event"+player.getActivityTimer());	
					while(player.getActivityTimer()>0) {
						System.out.println("Event"+player.getActivityTimer());
						try {
							Thread.sleep(20);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					DiscoObject.setStatusES(player, action);
					((JLabel) e.getSource()).getParent().setVisible(false);
					((JLabel) e.getSource()).getParent().setEnabled(false);
					disableActions();
					player.setActivity(0);
					System.out.println(player.getActivityTimer());
					bar.openOverlay=false;
					
				}
			}).start();;
				
			//}
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
