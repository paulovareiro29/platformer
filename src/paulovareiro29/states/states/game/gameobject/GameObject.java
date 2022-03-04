package paulovareiro29.states.states.game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.sprites.Animation;
import paulovareiro29.states.states.game.sprites.Image;
import paulovareiro29.states.states.game.sprites.Sprite;

public abstract class GameObject {

	protected int ID;
	protected double x;
	protected double y;
	protected double velX,velY;
	protected int width, height;
	protected boolean solid,rigid;
	protected ObjectType type;
	protected GameState game;
	protected boolean isFalling;
	
	protected Animation currentAnimation;
	
	public GameObject(ObjectType type,int ID, int x, int y, int velX, int velY, int width, int height, boolean solid,boolean rigid,GameState game){
		this.type = type;
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.rigid = rigid;
		this.game = game;
		isFalling = true;
	}
	
	
	public abstract void onClicked();
	public abstract void render(Graphics g,double xOffset,double yOffset);
	public abstract void tick();
	
	public Rectangle getBoundsHorizontal() {
		double bx = x + velX;
		double by = y + 2;
		double bw = width + velX / 2;
		double bh = height - 4;
		
		return new Rectangle((int) bx,(int) by, (int) bw, (int) bh);
	}
	
	public Rectangle getBoundsVertical() {
		double bx = x + 4;
		double by = y + velY;
		double bw = width - 4;
		double bh = height + velY/2;
		
		return new Rectangle((int) bx,(int) by, (int) bw, (int) bh);
	}
	
	
	
	public abstract void collidedWith(GameObject object);
	
	public void collision() {

		ArrayList<GameObject> tempObjects = new ArrayList<GameObject>(game.getObjects());
		
		for(GameObject object : tempObjects){
			if(object == null) continue;
			if(object.getID() == ID) continue;
			
			boolean collided = false;
			
			if(getBoundsHorizontal().intersects(object.getBoundsHorizontal())){
				collided = true;
				if(object.isSolid()){
					if(velX > 0){
						velX = 0;
						x = object.getX() - width;
					}else if(velX < 0){
						velX = 0;
						x = object.getX() + object.getWidth();
					}
				}
			}
			if(getBoundsVertical().intersects(object.getBoundsVertical())){
				collided = true;
				if(object.isSolid()){
					if(velY > 0){
						isFalling = false;
						velY = 0;
						y = object.getY() - height;
					}else if(velY < 0){
						velY = 0;
						y = object.getY() + object.getHeight();
					}
				}
			}
			
			if(collided){
				collidedWith(object);
			}
		}
		
	}
	
	
	public double clamp(double var,double min, double max){
		if(var < min) var = min;
		if(var > max) var = max;
		
		return var;
	}
	
	
	public boolean isFalling(){
		return isFalling;
	}
	
	public boolean isSolid(){
		return solid;
	}
	
	public boolean isRigid(){
		return rigid;
	}


	public void setVelY(int velY) {
		this.velY = velY;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getVelX() {
		return velX;
	}


	public void setVelX(double velX) {
		this.velX = velX;
	}


	public double getVelY() {
		return velY;
	}


	public void setVelY(double velY) {
		this.velY = velY;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public void setSolid(boolean solid) {
		this.solid = solid;
	}


	public void setRigid(boolean rigid) {
		this.rigid = rigid;
	}


	public ObjectType getType() {
		return type;
	}

	
	
	
}
