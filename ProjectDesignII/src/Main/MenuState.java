/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GameObjects.Constant;
import Graphics.Asset;
import State.GameState;
import State.State;
import UI.Action;
import UI.Button;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author HOME
 */
public class MenuState extends State{
    private ArrayList<Button> buttons;
    
    public MenuState() {
		buttons = new ArrayList<Button>();
		
		buttons.add(new Button(
				Asset.greyBtn,
				Asset.blueBtn,
				Constant.WIDTH / 2 - Asset.greyBtn.getWidth()/2,
				Constant.HEIGHT / 2 - Asset.greyBtn.getHeight(),
				Constant.PLAY,
				new Action() {
					@Override
					public void doAction() {
						State.changeState(new GameState());
					}
				}
				));
	}
	
	
	@Override
	public void update() {
		for(Button b: buttons) {
			b.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		for(Button b: buttons) {
			b.draw(g);
		}
	}
     
}
