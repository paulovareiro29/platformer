package paulovareiro29.states.states.game.sprites;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Sprite {

	private int size,rows,collumns;
	private BufferedImage sprite;
	private ArrayList<BufferedImage> crops = new ArrayList<BufferedImage>();
	
	
	public Sprite(BufferedImage sprite,int size){
		this.sprite = sprite;
		this.size = size;
		this.rows = rows;
		this.collumns = collumns;
		loadCrops();
	}

	private void loadCrops(){
		for(int i = 0; i < sprite.getHeight()/size;i++){ // Y : 4
			for(int j = 0; j < sprite.getWidth()/size;j++){ // X : 5
				crops.add(sprite.getSubimage(j * size, i * size, size, size));
			}
		}
	}
	
	
	public ArrayList<BufferedImage> getSubCropsByRow(int row,int maxCollumns){
		ArrayList<BufferedImage> subCrop = new ArrayList<BufferedImage>();
		if(maxCollumns*size > sprite.getWidth()) return null;
		for(int i = 0; i < maxCollumns;i++){
			subCrop.add(sprite.getSubimage(i * size, row * size, size, size));
		}
		return subCrop;
	}
	
	public ArrayList<BufferedImage> getCrops(){
		return crops;
		
	}
}

