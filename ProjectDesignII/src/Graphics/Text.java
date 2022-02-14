/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Graphics;

import Math.Vector2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Kevin Toasa
 */
public class Text {
    /*draw the text*/
    public static void drawText(Graphics g, String text, Vector2D pos, boolean center, Color color, Font font) {		
        g.setColor(color);
        g.setFont(font);
        Vector2D position = new Vector2D(pos.getX(), pos.getY());
        if(center) {
            //Take the dimensions of the text
            FontMetrics fm = g.getFontMetrics();
            position.setX(position.getX() - fm.stringWidth(text) / 2);  
            position.setY(position.getY() - fm.getHeight() / 2);	
        }
        g.drawString(text, (int)position.getX(), (int)position.getY());	
	}
}
