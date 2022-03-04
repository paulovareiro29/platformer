package paulovareiro29.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import paulovareiro29.states.GameStateManager;

public class Listener implements KeyListener,MouseListener {

	private GameStateManager gsm;
	
	public Listener(GameStateManager gsm){
		this.gsm = gsm;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			gsm.onClick(e.getX(), e.getY());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.onKeyPress(e.getKeyCode());
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		gsm.onKeyRealease(e.getKeyCode());
	}
	
	
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
