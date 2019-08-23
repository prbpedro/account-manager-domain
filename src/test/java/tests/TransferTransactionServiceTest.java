package tests;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.enums.TransferTransactionStatusEnum;
import com.github.prbpedro.accountmanager.domain.services.TransferTransactionService;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionReturnDto;
import com.github.prbpedro.accountmanager.domain.tables.AccountBalance;
import com.github.prbpedro.accountmanager.domain.tables.AccountTransaction;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountBalanceRecord;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountTransactionRecord;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.github.prbpedro.accountmanager.domain.util.Startup;

import tests.util.mock.ConcurrencyTestTransferTransactionService;

public class TransferTransactionServiceTest {

private static TransferTransactionService transferTransactionService;
	
	@BeforeClass
	public static void configure() throws SQLException
	{
		Startup.configure();
		transferTransactionService = Startup.getContainer().select(TransferTransactionService.class).get();
	}
	
	@Test
	public void testSucessTransfer() throws SQLException
	{	
		TransferTransactionReturnDto ret = transferTransactionService.doTransferTransaction("ACCOUNT_1", "ACCOUNT_2", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE);
		Assert.assertEquals(TransferTransactionStatusEnum.PROCESSED, ret.getStatus());
		
		try (Connection conn = transferTransactionService.databaseService.createConnection()){
			
			DSLContext context = DSL.using(conn, SQLDialect.H2);
			
			AccountTransactionRecord accountTransactionRecord = context.selectFrom(AccountTransaction.ACCOUNT_TRANSACTION)
			.where(
					AccountTransaction.ACCOUNT_TRANSACTION.SENDER_ID.eq("ACCOUNT_1")
					.and(AccountTransaction.ACCOUNT_TRANSACTION.BENEFICIARY_ID.eq("ACCOUNT_2")
			)).fetchOne();
			
			Assert.assertEquals((Double)BigDecimal.ONE.doubleValue(), (Double)accountTransactionRecord.getValue().doubleValue());
			
			AccountBalanceRecord senderAccountbalenceRecord = context.selectFrom(AccountBalance.ACCOUNT_BALANCE)
			.where(
					AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID.eq("ACCOUNT_1")
					.and(AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID.eq(accountTransactionRecord.getCurrencyId())
			)).fetchOne();
			
			Assert.assertEquals((Double)BigDecimal.valueOf(99).doubleValue(), (Double)senderAccountbalenceRecord.getValue().doubleValue());
			
			AccountBalanceRecord beneficiaryAccountbalenceRecord = context.selectFrom(AccountBalance.ACCOUNT_BALANCE)
			.where(
					AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID.eq("ACCOUNT_2")
					.and(AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID.eq(accountTransactionRecord.getCurrencyId())
			)).fetchOne();
			
			Assert.assertEquals((Double)BigDecimal.valueOf(1).doubleValue(), (Double)beneficiaryAccountbalenceRecord.getValue().doubleValue());
		}
	}
	
	@Test
	public void testConcurrentTransferTransactions() throws SQLException, InterruptedException
	{	
		TransferTransactionService instance1 = Startup.getContainer().select(TransferTransactionService.class).get();
		TransferTransactionService instance2 = new ConcurrencyTestTransferTransactionService(instance1, 8000);
		Assert.assertNotEquals(instance2, instance1);
		
		Thread t1 = new Thread(new Runnable() {
	        public void run() {
	        	TransferTransactionReturnDto ret = instance2.doTransferTransaction("ACCOUNT_1", "ACCOUNT_2", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE);
	        	Assert.assertEquals(TransferTransactionStatusEnum.PROCESSED, ret.getStatus());
	        }
	    });
		
		t1.start();
		Thread.sleep(500);

		Thread t2 = new Thread(new Runnable() {
	        public void run() {    
	        	TransferTransactionReturnDto ret = instance1.doTransferTransaction("ACCOUNT_1", "ACCOUNT_2", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE);
	        	Assert.assertEquals(TransferTransactionStatusEnum.ERROR_PROCESSING, ret.getStatus());
	        }
	    });
		
		t2.start();
		t2.join();
		t1.join();
		
		TransferTransactionReturnDto ret = transferTransactionService.doTransferTransaction("ACCOUNT_1", "ACCOUNT_2", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE);
		Assert.assertEquals(TransferTransactionStatusEnum.PROCESSED, ret.getStatus());
	}

	@Test
	public void testErrorTransfer() throws SQLException
	{	
		TransferTransactionReturnDto ret = transferTransactionService.doTransferTransaction("ACCOUNT_1", "ACCOUNT_9999", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getStatus());
		Assert.assertEquals("Beneficiary's account doesn't exists.", ret.getMessages().get(0));
	}
}