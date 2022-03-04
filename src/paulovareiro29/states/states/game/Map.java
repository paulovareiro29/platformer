package paulovareiro29.states.states.game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.creature.player.Player;
import paulovareiro29.states.states.game.gameobject.mapobjects.Ground;
import paulovareiro29.states.states.game.gameobject.pickup.pickups.Coin;
import paulovareiro29.states.states.game.sprites.Image;

public class Map {

	private String path;
	private Image levelImage;
	
	private int width,height;
	
	private Color playerColor = new Color(0,0,0);
	
	
	public Map(String path){
		this.path = path;
		levelImage = new Image(path);
	}
	
	public void loadMap(GameState game){
		game.getObjects().clear();
		
		
		width = levelImage.getWidth();
		height = levelImage.getHeight();
	
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				Color pixelColor = levelImage.getPixelColor(x, y);
				
				int red = pixelColor.getRed();
				int green = pixelColor.getGreen();
				int blue = pixelColor.getBlue();
				
				if(green == 255 && blue == 255) continue;
				
				if(pixelColor.getRGB() == playerColor.getRGB()){
					GameObject player = new Player(x,y,game);
					game.addGameObject(player);
					game.setPlayer(player);
					game.setCameraTarget(player);
					System.out.println("Player loaded");
				}
				
				switch(red){
				case 255: //se for um GROUND
					if(blue == 1){
						game.addGameObject(new Ground(green,x,y,game.getTileSize(),true,game));
					}else{
						game.addGameObject(new Ground(green,x,y,game.getTileSize(),false,game));
					}
					break;
				case 124: //se for pickup
					if(blue == 1){
						game.addGameObject(new Coin(x,y,true,game));
					}else{
						game.addGameObject(new Coin(x,y,false,game));
					}
					break;
				}
				
//				System.out.println("Pixel: [" + x + "," + y + "]	R:" + pixelColor.getRed() + "  G:" + pixelColor.getGreen() + "  B:" + pixelColor.getBlue());
				
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
