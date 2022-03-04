package paulovareiro29.states.states.menu.options;

import paulovareiro29.states.GameStateManager;

public abstract class Option {

	protected String name;
	protected GameStateManager gsm;
	
	public Option(String name,GameStateManager gsm){
		this.name = name;
		this.gsm = gsm;
	}
	
	public abstract void execute();
	
	public String getName(){
		return name;
	}
}
