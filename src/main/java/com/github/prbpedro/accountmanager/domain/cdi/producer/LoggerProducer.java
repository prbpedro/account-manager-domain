package com.github.prbpedro.accountmanager.domain.cdi.producer;  

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that contains the method to produce Logger whith CDI.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class LoggerProducer {   
	
	/**
	 * Method responsible for generating Loggers.
	 * 
	 * @param injectionPoint
	 * @return Logger
	 */
    @Produces  
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass());  
    }  
}  