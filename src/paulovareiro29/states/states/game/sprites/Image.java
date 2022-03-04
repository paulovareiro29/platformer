package paulovareiro29.states.states.game.sprites;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private String path;
	private BufferedImage image;
	
	private int width,height;
	
	
	public Image(String path){
		this.path = path;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
	}

	public Color getPixelColor(int x, int y){
		int p = image.getRGB(x, y);
		return new Color((p>>16) & 0xff,(p>>8) & 0xff,p & 0xff);
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}



	public int getWidth() {
		return width;
	}



	public int getHeight() {
		return height;
	}
	
	
}
