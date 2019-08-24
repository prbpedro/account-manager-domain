package com.github.prbpedro.accountmanager.domain.cdi.producer;  

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.LoggerFactory;

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
    public org.slf4j.Logger produceLogger(InjectionPoint injectionPoint) {  
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());  
    }  
}  