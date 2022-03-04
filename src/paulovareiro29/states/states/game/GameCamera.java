package paulovareiro29.states.states.game;

import paulovareiro29.main.Main;
import paulovareiro29.states.states.game.gameobject.GameObject;

public class GameCamera {

	private double xOffset,yOffset;
	private GameState game;
	
	public GameCamera(double xOffset, double yOffset,GameState game){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.game = game;
	}

	public void tick(GameObject target){
		if(target == null) return;
		xOffset = -target.getX() + Main.WIDTH/2 - target.getWidth()/2;
		yOffset = -target.getY() + Main.HEIGHT/2 - target.getHeight()/2;
		if(xOffset > 0) xOffset =0;
		if(xOffset < -(game.getTileSize() * game.getLevelWidth()) + Main.WIDTH) xOffset = -(game.getTileSize() * game.getLevelWidth()) + Main.WIDTH;
		
		if(yOffset > 0) yOffset = 0;
		if(yOffset < -(game.getTileSize() * game.getLevelHeight()) + Main.HEIGHT- game.getTileSize()*1.5) yOffset = -(game.getTileSize() * game.getLevelHeight()) + Main.HEIGHT - game.getTileSize()*1.5;
		
		
	}
	
	public void move(double x, double y){
		xOffset += x;
		yOffset += y;
	}
	
	public double getxOffset() {
		return xOffset;
	}


	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}


	public double getyOffset() {
		return yOffset;
	}


	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	
	

}
