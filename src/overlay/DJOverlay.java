package overlay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

import overlay.BarOverlay.Act;
import objects.DiscoObject;
import player.Player;
import main.BufferedImageLoader;
import main.GraphicManager;
import music.MusicManager;


/**
 * @author Nicolas
 */
public class DJOverlay extends Overlay {
	MusicManager musicManager;
	
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<Act> actions = new ArrayList<Act>();
	
	JLabel progress;
	JLabel progressText;
	
	public Player player;
	
	public DJOverlay(GraphicManager graphicManager, String t, MusicManager m, Player p) {
		super(graphicManager, t);
		
		this.player = p;
		this.musicManager = m;
		
		JLayeredPane pan = new JLayeredPane();
		int i = 0;
		for(String cat : m.getSongCategories()) {
			JButton j = new JButton(cat);
			Act a = new Act(cat);
			j.setBounds(0, BufferedImageLoader.scaleToScreenY(0+i*130,false), 
					BufferedImageLoader.scaleToScreenX(275,false), BufferedImageLoader.scaleToScreenY(120,false));
			j.setBorder(null);
			pan.add(j);
			
			buttons.add(j);
			actions.add(a);
			i++;
		}
		pan.setPreferredSize(new Dimension(BufferedImageLoader.scaleToScreenX(100,false), BufferedImageLoader.scaleToScreenY(i*100,false)));
		
		JScrollPane listScrollPane = new JScrollPane(pan);
		listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		listScrollPane.setWheelScrollingEnabled(true);
		listScrollPane.setBounds(BufferedImageLoader.scaleToScreenX(700,false),BufferedImageLoader.scaleToScreenY(100,false),
				BufferedImageLoader.scaleToScreenX(275,false),BufferedImageLoader.scaleToScreenY(540,false));
		listScrollPane.setBorder(null);
		
		add(listScrollPane, JLayeredPane.POPUP_LAYER);
		
		
		// DJ
		JLabel dj = new JLabel();
		dj.setIcon(new ImageIcon(graphicManager.dj_overlay.getImage()));
		dj.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(100,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(540,false));
		add(dj,JLayeredPane.POPUP_LAYER);
		
		// Progress
		progress = new JLabel();
		progress.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(100,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(540,false));
		progress.setIcon(new ImageIcon(graphicManager.progress0.getImage()));
		progress.setVisible(false);
		add(progress,JLayeredPane.POPUP_LAYER);
		moveToFront(progress);
		
		progressText = new JLabel();
		progressText.setBounds(BufferedImageLoader.scaleToScreenX(15,false), BufferedImageLoader.scaleToScreenY(550,false), 
				BufferedImageLoader.scaleToScreenX(660,false), BufferedImageLoader.scaleToScreenY(150,false));
		progressText.setText("\"Klar, spiel ich nach diesem Song fuer dich!\"");
		progressText.setForeground(new Color(128,0,0));
		progressText.setFont(new Font("Aharoni", 0, BufferedImageLoader.scaleToScreenX(30,false)));
		progressText.setHorizontalTextPosition(JLabel.RIGHT);
		progressText.setVisible(false);
		add(progressText,JLayeredPane.POPUP_LAYER);
		moveToFront(progressText);
	}
	
	
	public void setVisible(boolean on) {
		super.setVisible(on);
		if (actions != null)
			if(on) enableActions();
			else disableActions();
	}
	
	private void enableActions() {
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).addMouseListener(actions.get(i));
		}
	}
	
	private void disableActions() {
		for(int i=0;i<buttons.size();i++) {
			buttons.get(i).removeMouseListener(actions.get(i));
		}
	}
	
	class Act extends MouseAdapter {
		String cat;
		MouseEvent e;
		public Act(final String cat) {
			this.cat = cat;
		}
		@Override
		public void mouseClicked(final MouseEvent e) {
			this.e = e;
			player.setActivityTimer(400);
			
			//if(player.getActivityTimer()==0){
			new Thread(new Runnable(){
				@Override
				public void run() {
					DJOverlay overlay = (DJOverlay)((JButton) e.getSource()).getParent().getParent().getParent().getParent();
					overlay.progress.setVisible(true);
					overlay.progressText.setVisible(true);
					overlay.close.setVisible(false);
					while(player.getActivityTimer()>0) {
						
						switch(player.getActivityTimer()*5/400) {
							case 3:
								overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress1.getImage()));
								break;
							case 2:
								overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress2.getImage()));
								break;
							case 1:
								overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress3.getImage()));
								break;
							case 0:
								overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress4.getImage()));
						}
						
						try {
							Thread.sleep(40);
						} catch (InterruptedException e1) {}
					}
					
					overlay.setVisible(false);
					overlay.setEnabled(false);
					overlay.close.setVisible(true);
					disableActions();
					

					// Music
					musicManager.requestedCategory(((JButton) e.getSource()).getText());
					
					// Reset
					overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress0.getImage()));
					overlay.progress.setVisible(false);
					overlay.progressText.setVisible(false);
					
					// Reset
					overlay.progress.setIcon(new ImageIcon(overlay.graphicManager.progress0.getImage()));
					overlay.progress.setVisible(false);
					overlay.progressText.setVisible(false);
					
					
				}
			}).start();
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}	
}
