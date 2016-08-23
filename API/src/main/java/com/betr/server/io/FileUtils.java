package com.betr.server.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	private static final String configFolder =  "C:/conf/";
	
	public static File getConfigFile(String fileName) {
		return new File(configFolder, fileName);
	}
	
	public static Properties loadProps(File configuration) {
	    try {
	    	FileReader reader = new FileReader(configuration);
	        Properties props = new Properties();
	        props.load(reader);
	        return props;
	    }
	    catch(IOException ioe) {
	    	
	    }
	    
	    return null;
	}
}
