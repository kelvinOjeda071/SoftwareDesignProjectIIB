/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package State;

import GameObjects.Constant;
import Graphics.Asset;
import Graphics.Text;
import IO.JSONParser;
import IO.ScoreData;
import Math.Vector2D;
import UI.Action;
import UI.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KelvinOjeda
 */
public class ScoreState extends State {

    private Button returnButton;
    private PriorityQueue <ScoreData> highScores;
    private Comparator <ScoreData> scoreComparator;
    private ScoreData[] auxArrayScoreData;
    
    public ScoreState() {
        returnButton = new Button(
                Asset.greyBtn, 
                Asset.blueBtn,
                Asset.greyBtn.getHeight(),
                Constant.HEIGHT - Asset.greyBtn.getHeight()*2, 
                Constant.RETURN, 
                new Action (){
                    @Override
                    public void doAction(){
                        State.changeState(new MenuState());
                    }
                }
        );
        scoreComparator = new Comparator<ScoreData>(){
            @Override
            public int compare(ScoreData score1, ScoreData score2) {
                return score1.getScore() < score2.getScore() ? -1 : 
                        score1.getScore() > score2.getScore() ? 1: 
                        0;
            }
        };
        
        highScores = new  PriorityQueue<ScoreData> (10, scoreComparator);
        
        
        try {
            ArrayList <ScoreData> dataList = JSONParser.readField();
            for(ScoreData scoreData: dataList){
                highScores.add(scoreData);
            }
             while (highScores.size() > 10){
                 highScores.poll();
             }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
                
        
    }

    
    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);
        
        auxArrayScoreData = highScores.toArray(new ScoreData[highScores.size()]);
        Arrays.sort(auxArrayScoreData,scoreComparator);
        
        
        Vector2D scorePos = new Vector2D(
                Constant.WIDTH/2 - 200,
                100 
        );
        Vector2D datePos = new Vector2D(
                Constant.WIDTH/2 + 200,
                100
        );
        Text.drawText(g,
                Constant.SCORE,
                scorePos,
                true,
                Color.WHITE,
                Asset.fontMed);
        Text.drawText(g,
                Constant.DATE,
                datePos,
                true,
                Color.WHITE,
                Asset.fontMed);
        scorePos.setY(scorePos.getY() + 40);
        datePos.setY(datePos.getY() +40);
        for ( int i = auxArrayScoreData.length-1; i > -1; i-- ){
            ScoreData auxScoreData = auxArrayScoreData[i];
            Text.drawText(g,
                Integer.toString(auxScoreData.getScore()),
                scorePos,
                true,
                Color.WHITE,
                Asset.fontMed);
            Text.drawText(g,
                auxScoreData.getDate(),
                datePos,
                true,
                Color.WHITE,
                Asset.fontMed);
            scorePos.setY(scorePos.getY() + 40);
            datePos.setY(datePos.getY() +40);
        }
    }
    
}
