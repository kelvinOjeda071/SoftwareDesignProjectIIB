/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author KelvinOjeda
 */
public class ScoreData {
    private String date;
    private int score;
    
    public ScoreData (int score){
        this.score= score;
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(today);
        
    }
    
    public ScoreData(){
        
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}