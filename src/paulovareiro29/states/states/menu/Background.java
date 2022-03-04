package paulovareiro29.states.states.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import paulovareiro29.main.Main;

public class Background {

	private BufferedImage image;
	
	private double x, y, dx, dy;
	private double width,height;
	
	
	public Background(String path){
		try{
			image = ImageIO.read(getClass().getResourceAsStream(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPostion(double x, double y){
		this.x = x % Main.WIDTH;
		this.y = y % Main.HEIGHT;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void tick(){
		x += dx;
		y += dy;
		
		if(x == -Main.WIDTH){
			x = Main.WIDTH;
		}
	}
	
	public void render(Graphics g){
		g.drawImage(image, (int) x, (int) y,null);
		if(x < 0){
			g.drawImage(image,  (int) x + Main.WIDTH, (int) y,  null);
		}
		if(x > 0){
			g.drawImage(image, (int) x - Main.WIDTH,(int) y, null);
		}
	}
}
