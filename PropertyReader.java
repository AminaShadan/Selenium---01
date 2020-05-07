package com.project.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PropertyReader {
	
	public static Properties prop;
	public static FileInputStream inputStream; 
	
	public static String ReadProperty(String Propertyname) throws IOException{
		
		prop = new Properties();
		String projectpath = System.getProperty("user.dir");
		System.out.println("Project Path : " + projectpath);
		inputStream = new FileInputStream(projectpath + "/src/config.properties");
		prop.load(inputStream);
		String propertyValue = prop.getProperty(Propertyname);
		return propertyValue;
		
	}
	
	public static void main(String args[])throws IOException{
		
		System.out.println(PropertyReader.ReadProperty("browser"));
		System.out.println(PropertyReader.ReadProperty("appurl"));
	}

}
