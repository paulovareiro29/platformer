package paulovareiro29.states.states.game.gameobject.mapobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import paulovareiro29.main.Main;
import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;

public class Ground extends GameObject{

	private int type;
	
	public Ground(int type, int tileX, int tileY, int size,boolean rigid,GameState game) {
		super(ObjectType.Ground,0,tileX * size, tileY * size, 0, 0, size, size, true, rigid,game);
		this.type = type;
	}

	@Override
	public void onClicked() {
	}

	@Override
	public void render(Graphics g, double xOffset, double yOffset) {
		g.drawImage(game.getMapCrops().get(type),(int) x ,(int) y ,width,height, null);
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.white);
//		g.drawString(""+ID,(int) x + width/2,(int) y+height/2);
		
//		g2d.draw(getBoundsHorizontal());
//		g2d.draw(getBoundsVertical());
		
	}

	@Override
	public Rectangle getBoundsVertical() {
		double bx = x + 2;
		double by = y + velY;
		double bw = width - 4;
		double bh = height + velY/2;
		
		if(isFalling && rigid){
			 bh -= 10;
		}
		
		return new Rectangle((int) bx,(int) by, (int) bw, (int) bh);
	}
	
	
	@Override
	public void tick() {

		
		

		x += velX;
		y += velY;
		
	}

	@Override
	public void collidedWith(GameObject object) {
		
	}




}
