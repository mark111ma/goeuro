package com.challange;

import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/")
public class RouteController {

	private static final Logger logger = LogManager.getLogger( RouteController.class);
	
	@Autowired
    ApplicationContext ctx;

	@RequestMapping(value="direct")
	public @ResponseBody DirectRouteResponse direct(Model model, @RequestParam("dep_sid") Integer depSid, @RequestParam("arr_sid") Integer arrSid) throws Exception{
		logger.debug("direct: depSid:" + depSid + ", arrSid:" + arrSid);
		Date now = new Date();
		
		boolean connectionExist = ((RouteSearchService ) ctx.getBean( RouteSearchService.class)).isDirectConnection( depSid, arrSid);
		
		DirectRouteResponse ret = new DirectRouteResponse();
		ret.setArr_sid( arrSid);
		ret.setDep_sid( depSid);
		ret.setDirect_bus_route( connectionExist);
		logger.debug("total time : " + ( (new Date().getTime())-now.getTime() ) / 1000 );
		return ret;
	}
}