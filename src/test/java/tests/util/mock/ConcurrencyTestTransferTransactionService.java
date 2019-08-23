package tests.util.mock;

import com.github.prbpedro.accountmanager.domain.services.TransferTransactionService;

public class ConcurrencyTestTransferTransactionService extends TransferTransactionService {
	
	private long threadSleepMillisecs;
	public ConcurrencyTestTransferTransactionService(TransferTransactionService transactionService, int threadSleepMillisecs) {
		this.databaseService = transactionService.databaseService;
		this.transactionValidationService = transactionService.transactionValidationService;
		this.logger = transactionService.logger;
		this.threadSleepMillisecs = threadSleepMillisecs;
	}
	
	@Override
	protected void beforeCommit() {
		try {
			Thread.sleep(threadSleepMillisecs);
		} catch (InterruptedException e) {
			return;
		}
	}

}
