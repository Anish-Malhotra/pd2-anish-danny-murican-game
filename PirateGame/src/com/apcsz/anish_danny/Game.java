package com.apcsz.anish_danny;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas{
	
	private JFrame frame;
	private BufferStrategy strat;
	private boolean stillPlaying;
	
	
	public Game(){
		frame = new JFrame("Pirate Game!");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.getContentPane().add(this);
		setIgnoreRepaint(true);
		frame.setSize(800,600);
		setBounds(0,0,800,600);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		this.createBufferStrategy(2);
		strat = getBufferStrategy();
		stillPlaying = true;
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.runGameLoop();
	}

	private void runGameLoop() {
		long initLoop = System.currentTimeMillis();
		//while(stillPlaying){
			long change = initLoop - System.currentTimeMillis();
			initLoop = System.currentTimeMillis();
			Graphics2D gfx = (Graphics2D) strat.getDrawGraphics();
			BufferedImage sourceImage = ImageLoader.getImageLoader().getImage("resources/Untitled.jpg");
			gfx.drawImage(sourceImage,0,0,null);
		//}
	}

}
