package overlay;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import objects.DiscoObject;
import main.GraphicManager;
import music.MusicManager;

public class DJOverlay extends Overlay {
	MusicManager musicManager;
	
	public DJOverlay(GraphicManager graphicManager, String t, MusicManager m) {
		super(graphicManager, t);
		
		this.musicManager = m;
		
		JLayeredPane pan = new JLayeredPane();
		int i = 0;
		for(String cat : m.getSongCategories()) {
			JButton j = new JButton(cat);
			j.setBounds(0, 0+i*130, 275, 120);
			j.addMouseListener(new Act(cat));
			j.setBorder(null);
			pan.add(j);
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
	
	class Act extends MouseAdapter {
		String cat;
		public Act(String cat) {
			this.cat = cat;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			musicManager.requestedCategory(this.cat);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}	
}
