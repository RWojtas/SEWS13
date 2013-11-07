package overlay;

import java.awt.Color;
import java.awt.Dimension;
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
import main.GraphicManager;
import music.MusicManager;

public class DJOverlay extends Overlay {
	MusicManager musicManager;
	
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	ArrayList<Act> actions = new ArrayList<Act>();
	
	public DJOverlay(GraphicManager graphicManager, String t, MusicManager m) {
		super(graphicManager, t);
		
		this.musicManager = m;
		
		JLayeredPane pan = new JLayeredPane();
		int i = 0;
		for(String cat : m.getSongCategories()) {
			JButton j = new JButton(cat);
			Act a = new Act(cat);
			j.setBounds(0, 0+i*130, 275, 120);
			j.setBorder(null);
			pan.add(j);
			
			buttons.add(j);
			actions.add(a);
			i++;
		}
		pan.setPreferredSize(new Dimension(100, i*100));
		
		JScrollPane listScrollPane = new JScrollPane(pan);
		listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		listScrollPane.setWheelScrollingEnabled(true);
		listScrollPane.setBounds(700,100,275,540);
		listScrollPane.setBorder(null);
		
		add(listScrollPane, JLayeredPane.POPUP_LAYER);
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
		public Act(String cat) {
			this.cat = cat;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			musicManager.requestedCategory(this.cat);
			((JButton) e.getSource()).getParent().getParent().getParent().getParent().setVisible(false);
			((JButton) e.getSource()).getParent().getParent().getParent().getParent().setEnabled(false);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}	
}
