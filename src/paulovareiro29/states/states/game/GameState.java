package paulovareiro29.states.states.game;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import paulovareiro29.main.Main;
import paulovareiro29.states.GameStateManager;
import paulovareiro29.states.states.State;
import paulovareiro29.states.states.game.gameobject.GameObject;
import paulovareiro29.states.states.game.gameobject.ObjectType;
import paulovareiro29.states.states.game.gameobject.creature.player.Player;
import paulovareiro29.states.states.game.gameobject.mapobjects.Ground;
import paulovareiro29.states.states.game.gameobject.mapobjects.Objective;
import paulovareiro29.states.states.game.sprites.Image;
import paulovareiro29.states.states.game.sprites.Sprite;

public class GameState extends State {

	
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	GameObject player = new Player(1*128,1*128,this);
	private double gravity = 0.5;
	
	private ArrayList<BufferedImage> mapCrops;
	private Image pickUpImage;
	
	private GameCamera camera;
	private GameObject cameraTarget;
	
	private HUD hud;
	
	private int tileSize = 32;
	private Map level;
	
	
	public GameState(String levelPath,GameStateManager gsm) {
		super(gsm);
		Sprite mapSprite = new Sprite(new Image("/mapSprites.png").getImage(),128);
		mapCrops = mapSprite.getCrops();
		level = new Map(levelPath);
		
		camera = new GameCamera(0,0,this);
		hud = new HUD();
		
		level.loadMap(this);

		addGameObject(new Objective(3,3,true,this));
	}


	public void addGameObject(GameObject object){
		object.setID(objects.size());
		objects.add(object);
	}
	
	public void removeGameObject(GameObject object){
		for(int i = 0; i < objects.size(); i++){
			if(objects.get(i).equals(object)){
				objects.remove(i);
				return;
			}
		}
	}

	public int getLevelWidth(){
		return level.getWidth();
	}
	
	public int getLevelHeight(){
		return level.getHeight();
	}
	
	public ArrayList<BufferedImage> getMapCrops(){
		return mapCrops;
	}
	
	@Override
	public void tick() {
		for(int i = 0; i < objects.size(); i++){
			if(objects.get(i).isRigid() && objects.get(i).isFalling()){
				objects.get(i).setVelY(objects.get(i).getVelY()+gravity);
			}
			objects.get(i).tick();
			objects.get(i).collision();
		}
		camera.tick(cameraTarget);
		hud.tick();
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.translate(camera.getxOffset(), camera.getyOffset());
		for(int i = 0; i < objects.size(); i++){
			if(isInsideCamera(objects.get(i))){
				objects.get(i).render(g,camera.getxOffset(),camera.getyOffset());
			}
		}
		g2d.translate(-camera.getxOffset(), -camera.getyOffset());
		
		hud.render(g);
		
	}

	public boolean isInsideCamera(GameObject object){
		if(isBetween(object.getX(),-(camera.getxOffset()),-(camera.getxOffset() - Main.WIDTH)) &&
				isBetween(object.getY(), -(camera.getyOffset()), -(camera.getyOffset() - Main.HEIGHT)) ||
				isBetween(object.getX() + object.getWidth(),-(camera.getxOffset()),-(camera.getxOffset() - Main.WIDTH)) &&
				isBetween(object.getY() + object.getHeight(), -(camera.getyOffset()), -(camera.getyOffset() - Main.HEIGHT))){
			return true;
		}
		return false;
	}
	
	public boolean isBetween(double var, double min, double max){
		if(var 	>= min && var <= max){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void onClick(int x, int y) {
		addGameObject(new Ground(3,(int)(x-camera.getxOffset())/getTileSize() ,(int)(y - camera.getyOffset())/getTileSize(),getTileSize(),false,this));
	}


	@Override
	public void onKeyPress(int key) {
		((Player) player).move(key,true);
		switch(key){
		case 27:
			gsm.setCurrentState("menu");
			break;
		case 80:
			level.loadMap(this);
			break;
		}
	}

	@Override
	public void onKeyRealease(int key){
		((Player) player).move(key,false);
	}


	public ArrayList<GameObject> getObjects() {
		return objects;
	}


	public double getGravity() {
		return gravity;
	}


	public void setGravity(double gravity) {
		this.gravity = gravity;
	}


	public GameCamera getCamera() {
		return camera;
	}


	public int getTileSize() {
		return tileSize;
	}


	public void setPlayer(GameObject player) {
		this.player = player;
	}


	public void setCameraTarget(GameObject cameraTarget) {
		this.cameraTarget = cameraTarget;
	}


	public Image getPickUpImage() {
		return pickUpImage;
	}


	public void win(){
		objects.clear();
	}
}
