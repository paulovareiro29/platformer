package paulovareiro29.states.states.game.gameobject.pickup.pickups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;
import paulovareiro29.states.states.game.gameobject.pickup.Pickup;
import paulovareiro29.states.states.game.sprites.Animation;
import paulovareiro29.states.states.game.sprites.Image;
import paulovareiro29.states.states.game.sprites.Sprite;

public class Coin extends Pickup{
	
	Animation coin = new Animation(new Sprite(new Image("/pickup.png").getImage(), 128).getSubCropsByRow(0, 10), 3);
	
	public Coin(int x, int y, boolean rigid, GameState game) {
		super(ObjectType.PickUp,0, x*game.getTileSize(), y*game.getTileSize(), game.getTileSize(), game.getTileSize(), rigid, game);
		currentAnimation = coin;
	}

	@Override
	public void onClicked() {
	}

	@Override
	public void render(Graphics g, double xOffset, double yOffset) {
		Graphics2D g2d = (Graphics2D) g;
		
//		g.setColor(Color.blue);
//		g2d.fill(getBoundsHorizontal());
		
		if(currentAnimation != null){
			g.drawImage(currentAnimation.getCurrentFrame(), (int) x - game.getTileSize()/2,(int) y - game.getTileSize(),game.getTileSize()*2,game.getTileSize()*2,null);
		}
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		
		currentAnimation.tick();
	}

	@Override
	public void collidedWith(GameObject object) {
		
	}

}
