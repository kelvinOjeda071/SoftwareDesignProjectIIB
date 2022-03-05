/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GameObjects;

import Graphics.Text;
import Math.Vector2D;
import State.GameState;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author HOME
 */
public class Message {
    
    //RGB color values for transparency
    private float alpha;
    //text to display
    private String text;
    //message position
    private Vector2D position;
    //Color
    private Color color;
    //Check if it is in the center
    private boolean center;
    //fade effect
    private boolean fade;
    private Font font;
    //alpha behavior change
    private final float deltaAlpha = 0.01f;
    private boolean dead;
    
    public Message(Vector2D position, boolean fade, String text, Color color,
            boolean center, Font font) {
        this.font = font;
        
        this.text = text;
        this.position = position;
        this.fade = fade;
        this.color = color;
        this.center = center;
        this.dead = false;
        if (fade) {
            alpha = 1;
        } else {
            alpha = 0;
        }

    }

    public void draw(Graphics2D g2d) {
        //Transparency is applied to the text
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        //The text is draw
        Text.drawText(g2d, text, position, center, color, font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        position.setY(position.getY() - 1);
        //controls that the transparency of the images
        if (fade) {
            alpha -= deltaAlpha;
        } else {
            alpha += deltaAlpha;
        }

        if (fade && alpha < 0) {
            dead = true;
        }

        if (!fade && alpha > 1) {
            fade = true;
            alpha = 1;
        }

    }
    
    public boolean isDead() {return dead;}
}
