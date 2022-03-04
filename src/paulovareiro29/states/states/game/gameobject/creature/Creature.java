package paulovareiro29.states.states.game.gameobject.creature;

import java.awt.Graphics;

import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;


public abstract class Creature extends GameObject{

	protected double HP;
	
	public Creature(ObjectType type,int ID, int x, int y, int width, int height,boolean rigid, double HP,GameState game) {
		super(type,ID, x, y, 0, 0, width, height,true,rigid,game);
		this.HP = HP;	
	}

	public double getHP() {
		return HP;
	}

	public void setHP(double hP) {
		HP = hP;
	}

	
	
}
