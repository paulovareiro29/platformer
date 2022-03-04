package paulovareiro29.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.EventListener;

import paulovareiro29.states.GameStateManager;


public class Main extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int YMAX = HEIGHT - 10;
	private GameStateManager gsm;
	private Listener listener;
	
	public Main(){
		myCanvas c = new myCanvas(800, 600, "Window", this);
		gsm = new GameStateManager();
		listener = new Listener(gsm);
		this.addKeyListener(listener);
		this.addMouseListener(listener);
	}
	
	public static void main(String[] args){
		new Main();
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while(delta >=1)
                            {
                                tick();
                                delta--;
                            }
                            if(running)
                                render();
                            frames++;
                            
                            if(System.currentTimeMillis() - timer > 1000)
                            {
                                timer += 1000;
                                System.out.println("FPS: "+ frames);
                                frames = 0;
                            }
        }
                stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gsm != null) gsm.render(g);
		
		g.dispose();
		bs.show();
	}

	private void tick() {	
		if(gsm != null) gsm.tick();	
	}
}
