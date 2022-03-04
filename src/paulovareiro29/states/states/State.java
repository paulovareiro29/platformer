package paulovareiro29.states.states;

import java.awt.Graphics;

import paulovareiro29.states.GameStateManager;

public abstract class State {

	protected GameStateManager gsm;
	
	public State(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	public abstract void onClick(int x, int y);
	public abstract void onKeyPress(int key);
	public abstract void onKeyRealease(int key);
}
