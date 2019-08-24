package com.github.prbpedro.accountmanager.domain.guice;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.prbpedro.accountmanager.domain.services.ConfigurationService;
import com.github.prbpedro.accountmanager.domain.services.DatabaseService;
import com.github.prbpedro.accountmanager.domain.services.TransactionValidationService;
import com.github.prbpedro.accountmanager.domain.services.TransferTransactionService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.IConfigurationService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.IDatabaseService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.ITransactionValidationService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.ITransferTransactionService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AccountManagerModule extends AbstractModule {
	
	@Override
	protected void configure() {
		 bind(IConfigurationService.class).to(ConfigurationService.class).in(Singleton.class);;
		 bind(IDatabaseService.class).to(DatabaseService.class);
		 bind(ITransactionValidationService.class).to(TransactionValidationService.class);
		 bind(ITransferTransactionService.class).to(TransferTransactionService.class);
		 bind(Logger.class).toInstance(LoggerFactory.getLogger(AccountManagerModule.class));
	}
}
