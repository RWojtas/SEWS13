package player;


import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import main.*;

public class AS extends Human {

	Functions f = new Functions();
	
	public AS(char gender, BufferedImage image, int x, int y, int direction) {
		super("AS",gender,image,x,y,direction);
		this.type = type;
	}
	
	public void resetAS(char gender, BufferedImage image, int x, int y, int direction) {
		super.resetHuman("AS",gender,image,x,y,direction);
		this.target = new Position(0, 0, 0, 0, 0, 0, 0, 0);
		this.flirt = 0.5;
		this.fun = 0.5;
		this.alcLevel = 0;
		this.urine = 0.5;
		this.energy = 1;
		this.gender = gender;
		this.type = type;
		this.activity = 0;
		this.direction = direction;
		
		graphicState = 0;
//		this.height = image.getHeight();
//		this.width =  image.getWidth();
		this.height = BufferedImageLoader.scaleToScreenX(60,false);
		this.width =  BufferedImageLoader.scaleToScreenY(60,false);
		
		this.position = new Position(x, y, x+width, y, x, y+height, x+width, y+height);
		
		for(int i=0;i<8;i++) {
			images[i] = new ImageIcon(image.getSubimage(0,i*width,width,height));
			//System.out.println(i*width+"");
		};
		
		setIcon(images[0]);
		setBounds(x,y,width,height);
		setOpaque(false);
	}
	
	public void setActivityWithPos(int activity, DiscoObjectManager doManager){
		int dos=0;
		this.setActivity(activity);
		switch(activity){
			case 2:
				dos = 6;
				this.setActivityTimer(f.myRandom(2000, 6000));
				break;
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
				dos = 0;
				this.setActivityTimer(1000);
				break;
			case 4:
				dos = f.myRandom(7, 13);
				this.setActivityTimer(f.myRandom(2000,6000));
				break;
			case 5:
				dos = 1;
				if(this.getGender() == 'w'){
					this.setActivityTimer(12000);
				}
				else {
					this.setActivityTimer(2000);
				}
				break;
			case 9:
				dos = f.myRandom(7, 13);
				this.setActivityTimer(2000);
				break;
		}
		int x = f.myRandom(doManager.discoObject[dos].getX(), doManager.discoObject[dos].getX()+doManager.discoObject[dos].WIDTH);
		int y = f.myRandom(doManager.discoObject[dos].getY(), doManager.discoObject[dos].getY()+doManager.discoObject[dos].HEIGHT);
		this.setTarget(x,y);
	}
	
}
