package paulovareiro29.states.states.menu.options.suboptions;

import paulovareiro29.states.GameStateManager;
import paulovareiro29.states.states.menu.options.Option;

public class QuitOption extends Option{

	public QuitOption( GameStateManager gsm) {
		super("Quit", gsm);
	}

	public void execute() {
		System.exit(0);
	}

}
