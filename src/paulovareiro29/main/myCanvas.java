package paulovareiro29.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class myCanvas extends Canvas {

	myCanvas(int width,int height, String title, Main game){
		JFrame window = new JFrame(title);
		window.setSize(width,height);
		window.setResizable(false);
		window.setBackground(Color.gray);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setLocationRelativeTo(null);


		window.add(game);
		setVisible(true);

		setBackground(Color.LIGHT_GRAY);
		game.start();
	}
	
}
