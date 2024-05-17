package utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class GenericUtils {
	
	public static String getPropertyValue(String env, String key) {
		String returnValue=null;
		
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/"+env+".properties");
			Properties prop = new Properties();
			prop.load(fis);
			returnValue = prop.getProperty(key);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnValue;
	}
	
	public static String getRandomRepoName() {
		int max = 1000000;
		int min = 1;
		int rndNum = new Random().nextInt(max - min + 1) + min;
		String prefix = "repo_";
		return prefix+rndNum;
	}
	
	public static String getDownloadPath()
	{
		String property = System.getProperty("user.home");
		return property + "\\Downloads";
	}
	
	public static ArrayList<String> readCSV(String csvFile) {
        BufferedReader br = null;
        String line;
        ArrayList<String> data=new ArrayList<String>();
        try 
        {
            br = new BufferedReader(new FileReader(csvFile));
           
            while ((line = br.readLine()) != null)
            {
            	data.add(line);
                
            }
            
        }
        catch (Exception e)
        {
			e.printStackTrace();
		}
        finally 
        {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return data;
	}
}
