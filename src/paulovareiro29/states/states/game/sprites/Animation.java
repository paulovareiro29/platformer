package paulovareiro29.states.states.game.sprites;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import paulovareiro29.states.states.game.gameobject.GameObject;

public class Animation {

	private ArrayList<BufferedImage> crops;
	private int speed;
	private int frames;
	private int index = 0;
	private int count = 0;
	
	private BufferedImage currentFrame;
	
	public Animation(ArrayList<BufferedImage> crops, int speed){
		this.crops = crops;
		this.speed = speed;
		currentFrame = crops.get(0);
		frames = crops.size();
	}
	
	

	
	public void tick(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame(){
		for(int i = 0; i < frames; i++){
			if(count == i){
				currentFrame = crops.get(i); 
			}
		}
		
		count++;
		
		if(count > frames){
			count = 0;
		}
	}


	public int getSpeed() {
		return speed;
	}




	public void setSpeed(int speed) {
		this.speed = speed;
	}




	public int getFrames() {
		return frames;
	}




	public void setFrames(int frames) {
		this.frames = frames;
	}




	public int getIndex() {
		return index;
	}




	public void setIndex(int index) {
		this.index = index;
	}




	public BufferedImage getCurrentFrame() {
		return currentFrame;
	}




	public void setCurrentFrame(BufferedImage currentFrame) {
		this.currentFrame = currentFrame;
	}
}
