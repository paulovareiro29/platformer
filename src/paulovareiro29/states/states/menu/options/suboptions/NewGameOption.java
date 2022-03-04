package paulovareiro29.states.states.menu.options.suboptions;

import paulovareiro29.states.GameStateManager;
import paulovareiro29.states.states.game.GameState;
import paulovareiro29.states.states.menu.options.Option;

public class NewGameOption extends Option{

	private GameState game;
	
	public NewGameOption(GameStateManager gsm) {
		super("Start",gsm);
		game = new GameState("/level1.png",gsm);
	}

	@Override
	public void execute() {
		gsm.setGameState(game);
	}

	
}
