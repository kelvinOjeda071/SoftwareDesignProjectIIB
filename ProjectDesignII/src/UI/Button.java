/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import Graphics.Asset;
import Graphics.Text;
import Input.MouseInput;
import Math.Vector2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author HOME
 */
public class Button {
/*Button images*/
    private BufferedImage mouseOutImg;
    private BufferedImage mouseInImg;
/*Events with mouse*/
    private boolean mouseIn;
    private Rectangle boundingBox;
    private Action action;
/*button name's*/
    private String text;
	/*Constructor*/
	public Button(
			BufferedImage mouseOutImg,
			BufferedImage mouseInImg,
			int x, int y,
			String text,
			Action action
			) {
		this.mouseInImg = mouseInImg;
		this.mouseOutImg = mouseOutImg;
		this.text = text;
                //button position and size
		boundingBox = new Rectangle(x, y, mouseInImg.getWidth(), mouseInImg.getHeight());
		this.action = action;
	}
	
	public void update() {
            /*the coordinates of the button are inside the mouse*/
            if(boundingBox.contains(MouseInput.X, MouseInput.Y)) {
    		mouseIn = true;
            }else{
		mouseIn = false;
            }
            /*if i press the button*/
            if(mouseIn && MouseInput.MLB) {
		action.doAction();
            }
	}
	
	public void draw(Graphics g) {
            //Draw the button
            if(mouseIn) {
		g.drawImage(mouseInImg, boundingBox.x, boundingBox.y, null);
            }else {
		g.drawImage(mouseOutImg, boundingBox.x, boundingBox.y, null);
            }	
	Text.drawText(g,text,new Vector2D(
					boundingBox.getX() + boundingBox.getWidth() / 2,
					boundingBox.getY() + boundingBox.getHeight()),
                                        true,Color.BLACK,Asset.fontMed);
	}
}
