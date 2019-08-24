package com.github.prbpedro.accountmanager.domain.services;

import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;
import com.google.inject.Inject;

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
