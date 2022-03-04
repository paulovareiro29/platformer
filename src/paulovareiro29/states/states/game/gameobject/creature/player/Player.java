package paulovareiro29.states.states.game.gameobject.creature.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import paulovareiro29.main.Main;
import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;
import paulovareiro29.states.states.game.gameobject.creature.Creature;
import paulovareiro29.states.states.game.sprites.Animation;
import paulovareiro29.states.states.game.sprites.Image;
import paulovareiro29.states.states.game.sprites.Sprite;


public class Player extends Creature{

	public boolean movingRIGHT = false;
	public boolean movingLEFT = false;
	
	public boolean jumping = false;
	public boolean falling = false;
	
	public boolean rightPressed = false;
	public boolean leftPressed = false;
	
	public boolean lastPressed = false;
	
	public int jumpForce = 6;
	
	Animation idleRightAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(0, 10),3);
	Animation idleLeftAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(1, 10),3);
	
	Animation walkRightAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(2, 10),2);
	Animation walkLeftAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(3, 10),2);
	
	Animation jumpRightAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(4, 6),4);
	Animation jumpLeftAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(5, 6),4);
	
	Animation fallRightAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(6, 6),4);
	Animation fallLeftAnim = new Animation(new Sprite(new Image("/player.png").getImage(),128).getSubCropsByRow(7, 6),4);
	
	public Player(int x, int y,GameState game) {
		super(ObjectType.Player,0,x*game.getTileSize(), y*game.getTileSize(),game.getTileSize(),game.getTileSize(), true, 100,game);
		falling = true;
	}

	public void move(int key,boolean move){
		if(key == 27){
			leftPressed = false;
			rightPressed = false;
			movingLEFT = false;
			movingRIGHT = false;
		}

		if(move){
			switch(key){
			case 68:
				case 39:
				rightPressed = true;
				if(!movingLEFT){
					movingRIGHT = true;
					lastPressed = false;
				}
				break;
			case 65:
				case 37:
				leftPressed = true;
				if(!movingRIGHT){
					movingLEFT = true;
					lastPressed = true;
				}
				break;
			case 32:
				case 38:
					velY = -jumpForce * 2;
					jumping = true;
				break;
			}
		}else{
			switch(key){
			case 68:
				case 39:
				rightPressed = false;
				if(movingRIGHT){
					movingRIGHT = false;
					if(leftPressed){
						movingLEFT = true;
					}
				}
				break;
			case 65:
				case 37:
				leftPressed = false;
				if(movingLEFT){
					movingLEFT = false;
					if(rightPressed){
						movingRIGHT = true;
					}
				}
				break;
			}
		}
	}
	

	
	@Override
	public void onClicked() {
		
	}

	@Override
	public void render(Graphics g,double xOffset,double yOffset) {
		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(Color.blue);
//		g2d.fill(getBoundsHorizontal());
//		
//		g2d.setColor(Color.GREEN);
//		g2d.fill(getBoundsVertical());

		if(currentAnimation != null){
			g.drawImage(currentAnimation.getCurrentFrame(), (int) x - game.getTileSize()/2,(int) y - game.getTileSize(),game.getTileSize()*2,game.getTileSize()*2,null);
		}
	}

	@Override
	public Rectangle getBoundsVertical() {
		double bx = x + 4;
		double by = y + velY;
		double bw = width - 8;
		double bh = height + velY/2;
		
		return new Rectangle((int) bx,(int) by, (int) bw, (int) bh);
	}
	
	
	
	@Override
	public void tick() {

		isFalling = true;
		velX = 0;
		if(movingRIGHT){
			velX = +5;
			currentAnimation = walkRightAnim;
		}
		if(movingLEFT){
			velX = -5;
			currentAnimation = walkLeftAnim;
		}
		
		x += velX;
		y += velY;

		if(jumping && velY == 0){
			falling = true;
			jumping = false;
		}else if(falling && !jumping && velY == 0){
			falling = false;
			jumping = false;
		}
		
		if(jumping && !falling){
			
			if(movingRIGHT){
				currentAnimation = jumpRightAnim;
			}else if(movingLEFT){
				currentAnimation = jumpLeftAnim;
			}else if(!lastPressed) { currentAnimation = jumpRightAnim; } else { currentAnimation = jumpLeftAnim; }
			
		}else if(falling && !jumping){
			if(movingRIGHT){
				currentAnimation = fallRightAnim;
			}else if(movingLEFT){
				currentAnimation = fallLeftAnim;
			}else if(!lastPressed) { currentAnimation = fallRightAnim; } else { currentAnimation = fallLeftAnim; }
		}else if(jumping && falling){
			falling = false;
		}
		
		if(!movingLEFT && !movingRIGHT && !jumping && !falling){
			if(!lastPressed) {currentAnimation = idleRightAnim; } else { currentAnimation = idleLeftAnim; }}

		
		if(currentAnimation != null){
			currentAnimation.tick();
		}
	}

	
	@Override
	public void collidedWith(GameObject object) {
		switch(object.getType()){
		case PickUp:
			game.removeGameObject(object);
			break;
		case Objective:
			game.win();
		default:
			break;
		}
	}





	
	
}
