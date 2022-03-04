package paulovareiro29.states.states.game.gameobject.pickup;

import java.awt.Graphics;

import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;

public abstract class Pickup extends GameObject{
	
	public Pickup(ObjectType type,int ID, int x, int y, int width, int height,
			boolean rigid, GameState game) {
		super(type, ID, x, y, 0, 0, width, height, false, rigid, game);
	}

}
