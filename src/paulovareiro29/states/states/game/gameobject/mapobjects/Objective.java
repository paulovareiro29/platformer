package paulovareiro29.states.states.game.gameobject.mapobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;
import paulovareiro29.states.states.game.gameobject.pickup.Pickup;
import paulovareiro29.states.states.game.sprites.Animation;
import paulovareiro29.states.states.game.sprites.Image;
import paulovareiro29.states.states.game.sprites.Sprite;

public class Objective extends Pickup {

	private Animation objective = new Animation(new Sprite(new Image("/pickup.png").getImage(),128).getSubCropsByRow(1, 1), 10);

	public Objective(int x, int y, boolean rigid, GameState game) {
		super(ObjectType.Objective,0, x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(),  game.getTileSize(), rigid, game);
		currentAnimation = objective;
	}

	@Override
	public void onClicked() {
		
	}

	@Override
	public void render(Graphics g, double xOffset, double yOffset) {
		
		if(currentAnimation != null){
			g.drawImage(currentAnimation.getCurrentFrame(), (int) x - game.getTileSize()/2,(int) y - game.getTileSize(),game.getTileSize()*2,game.getTileSize()*2,null);
		}
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.blue);
//		g2d.fill(getBoundsHorizontal());
//		
//		g2d.setColor(Color.GREEN);
//		g2d.fill(getBoundsVertical());
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(currentAnimation != null){
			currentAnimation.tick();
		}
	}

	@Override
	public void collidedWith(GameObject object) {
		
	}


}
