package com.comcast.crm.generic.fileUtility;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.comcast.crm.generic.webDriverUtility.IconstantUtility;
/**
 * this class store reusable method for getDataFromJsonFile file
 */
public class JsonUtility {
	/**
	 * this method is a reusable method for getDataFromJsonFile file
	 * @param key
	 * @return
	 * @throws Exception
	 */
        public String getDataFromJsonFile(String key) throws Exception {
        	//FileReader fileR=new FileReader("./cofigAppData/Data.josn");
        	JSONParser parser=new JSONParser();
        	Object obj=parser.parse(new FileReader(IconstantUtility.jSonPath));
        	JSONObject map=(JSONObject)obj;
        	String data= (String) map.get(key);																											
        	return data;
        }
}
