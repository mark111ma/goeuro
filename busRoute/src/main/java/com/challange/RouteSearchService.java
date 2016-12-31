package com.challange;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class RouteSearchService {
	
	private Logger logger = LogManager.getLogger( this.getClass());
	private Stream<String> stream;	
	private Boolean isDirectConnectionResult = false;		
	
	public boolean isDirectConnection( Integer depSid, Integer arrSid) throws Exception{	
		Path path= Paths.get( Application.dataFilePath);
	    stream = Files.lines( path, StandardCharsets.UTF_8 );
	    stream.parallel().forEach( 
	    		l-> isDirectConnectionForRoute(l, depSid, arrSid)	    				    								
	    	);		
	    logger.debug("finded: " + this.isDirectConnectionResult);
		return this.isDirectConnectionResult;
	}		
	
	private void isDirectConnectionForRoute(String route, Integer depSid, Integer arrSid){
		int stops[] = Pattern.compile("\\s").splitAsStream( route.replaceFirst("\\d+\\s", "")).mapToInt( Integer::parseInt).toArray();			
		boolean depSidExists = false;		
		for(int i=0 ; i < stops.length; i++){
			if( depSidExists){
				if( stops[i] == arrSid){
					this.isDirectConnectionResult = true;			
					return;
				}
			}else{
				if( stops[i] == depSid){
					depSidExists = true;
				}
			}
		}
	}
}