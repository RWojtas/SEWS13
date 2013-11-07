package player;


import java.awt.image.BufferedImage;
import main.*;

public class AS extends Human {

	Functions f = new Functions();
	
	public AS(char gender, BufferedImage image, int x, int y, int direction) {
		super("AS",gender,image,x,y,direction);
		this.type = type;
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
