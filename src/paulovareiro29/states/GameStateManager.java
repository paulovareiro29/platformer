package paulovareiro29.states;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import paulovareiro29.states.states.State;
import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.menu.MenuState;

public class GameStateManager {

	public State menuState = new MenuState(this);
	public State gameState;
	
	
	public static State currentState;
	
	
	
	public GameStateManager(){
		currentState = menuState;
	}
	
	public void render(Graphics g){
		currentState.render(g);
	}
	
	public void setGameState(GameState game){
		gameState = game;
		setCurrentState("game");
	}
	
	public void setCurrentState(String name){
		switch(name.toUpperCase()){
		case "GAME":
			currentState = gameState;
			break;
		case "MENU":
			currentState = menuState;
			break;
		}
	}
	
	public void tick(){
		currentState.tick();
	}
	
	public void onClick(int x, int y) {
		currentState.onClick(x, y);
	}
	
	public void onKeyPress(int key){
		currentState.onKeyPress(key);
	}
	
	public void onKeyRealease(int key){
		currentState.onKeyRealease(key);
	}
}
