/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IO;

import GameObjects.Constant;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author KelvinOjeda
 */
public class JSONParser {
    public static ArrayList<ScoreData> readField() throws FileNotFoundException{
        // ScoreData is the array where we can read the core from file
        ArrayList <ScoreData> dataList = new ArrayList<ScoreData>();
        
        File file = new File(Constant.SCORE_PATH);
        // If the file is empty or is not exist return the dataList
         if(!file.exists() || file.length() == 0){
             return dataList;
         }
         // Start to parser
         JSONTokener parser = new JSONTokener(new FileInputStream(file));
         //Initial the JSONArray
         JSONArray jsonList = new JSONArray(parser);
         
         for (int i = 0; i < jsonList.length(); i++){
             JSONObject jsonObject = (JSONObject) jsonList.get(i);
             ScoreData scoreData = new ScoreData();
             scoreData.setScore(jsonObject.getInt("score"));
             scoreData.setDate(jsonObject.getString("date"));
             dataList.add(scoreData);
         }
         
         return dataList;
         
         
    }
    
    public static void writeFile(ArrayList<ScoreData> dataList) throws IOException{
        File outputFile = new File(Constant.SCORE_PATH);
        // Create the parent directory
        outputFile.getParentFile().mkdir();
        // Create the file into the parent directory
        outputFile.createNewFile();
        
        JSONArray jsonList = new JSONArray();
         for (ScoreData scoreData : dataList){
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("score", scoreData.getScore());
             jsonObject.put("date", scoreData.getDate());
             
             jsonList.put(jsonObject);
         }
         // Create the buffer to write
         BufferedWriter bufferWriter = Files.newBufferedWriter(
                 Paths.get(outputFile.toURI()));
         
         jsonList.write(bufferWriter);
         bufferWriter.close();
         
         
                 
        
        
    }
    
    
}
