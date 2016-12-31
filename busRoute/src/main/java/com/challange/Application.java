package com.challange;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class Application {

	private static final Logger logger = LogManager.getLogger(Application.class);
	
	public static String dataFilePath;

	public static void main(String[] args) {
		if( args.length ==0 || StringUtils.isEmpty(args[0])){			
			logger.error("ERROR! Missing argument: data file path");
			return;
		}
		dataFilePath =args[0]; 
		SpringApplication.run( Application.class, args);
	}

	
}