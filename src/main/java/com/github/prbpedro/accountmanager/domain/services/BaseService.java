package com.github.prbpedro.accountmanager.domain.services;

import javax.inject.Inject;

import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;

/**
 * Base service class.
 * 
 * @author Pedro Ribeiro Baptista
 */
public abstract class BaseService {

	/**
	 * DatabaseService attribute.
	 */
	@Inject
	public IDatabaseService databaseService;
}
