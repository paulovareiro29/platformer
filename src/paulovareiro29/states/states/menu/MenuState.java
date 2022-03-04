package paulovareiro29.states.states.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import paulovareiro29.main.Main;
import paulovareiro29.states.GameStateManager;
import paulovareiro29.states.states.State;
import paulovareiro29.states.states.menu.options.Option;
import paulovareiro29.states.states.menu.options.suboptions.CreateMapOption;
import paulovareiro29.states.states.menu.options.suboptions.NewGameOption;
import paulovareiro29.states.states.menu.options.suboptions.QuitOption;

public class MenuState extends State {

	private Background background;
	
	private List<Option> options = new ArrayList<Option>();
	private int selected = 0;
	
	private String title = "Catarina";
	private Font titleFont = new Font("Century Gothic",Font.BOLD,100);
	private Color titleColor = Color.PINK;
	
	private Font font = new Font("Century Gothic",Font.PLAIN,40);
	private Color selectedColor = Color.PINK;
	private Color nonSelectedColor = Color.WHITE;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
		background = new Background("/menu_background.png");
		background.setVector(-1, 0);
		
		options.add(new NewGameOption(gsm));
		options.add(new CreateMapOption(gsm));
		options.add(new QuitOption(gsm));
	}

	@Override
	public void render(Graphics g) {
		if(background != null){
			background.render(g);
		}
		g.setColor(titleColor);
		g.setFont(titleFont);
		FontMetrics fm = g.getFontMetrics();
		
		g.drawString(title,(Main.WIDTH - fm.stringWidth(title)) / 2,Main.HEIGHT/4 + fm.getAscent()/2);
		
		g.setFont(font);
		for(int i = 0; i < options.size(); i++){
			if(selected == i){
				g.setColor(selectedColor);
			}else{
				g.setColor(nonSelectedColor);
			}
			
			g.drawString(options.get(i).getName(), (Main.WIDTH - Main.WIDTH / 2) - (options.get(i).getName().length() / 2) * 23, Main.HEIGHT/2 + 30 + i * 45);
		}
	}

	@Override
	public void tick() {
		if(background != null){
			background.tick();
		}
	}

	@Override
	public void onClick(int x, int y) {
		
	}

	@Override
	public void onKeyPress(int key) {
		switch(key){
		case 38:
			selected--;
			if(selected < 0){
				selected = options.size()-1;
			}
			break;
		case 40:
			selected++;
			if(selected > options.size()-1){
				selected = 0;
			}
			break;
		case 10:
			options.get(selected).execute();
			break;
		}
	}

	@Override
	public void onKeyRealease(int key) {
		
	}

}
