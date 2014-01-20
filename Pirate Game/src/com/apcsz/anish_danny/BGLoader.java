package com.apcsz.anish_danny;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class BGLoader extends JPanel{
	public void drawComponent(Graphics g,BufferedImage image){
		g.drawImage(image,0,0,null);
	}
}
