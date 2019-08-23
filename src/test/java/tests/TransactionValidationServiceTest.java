package tests;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.prbpedro.accountmanager.domain.enums.TransferTransactionStatusEnum;
import com.github.prbpedro.accountmanager.domain.services.TransactionValidationService;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionalDataDto;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.github.prbpedro.accountmanager.domain.util.Startup;

public class TransactionValidationServiceTest {

	private static TransactionValidationService transactionValidationService;
	
	@BeforeClass
	public static void configure() throws SQLException
	{
		Startup.configure();
		transactionValidationService = Startup.getContainer().select(TransactionValidationService.class).get();
	}

	@Test
	public void testInexistentCurrency() throws SQLException
	{	TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_999", "ACCOUNT_9999", "XXX", "XXX", BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Transactions's currency doesn't exists.", ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testInexistentSenderAccount() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_999", "ACCOUNT_9999", Constants.REVOLUT_BANK_CODE, Constants.BRL, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Sender's account doesn't exists.", ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testInexistentSenderAccountBalance() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", "XXX", Constants.BRL, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Sender has no founds (BRL).", ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testAmmountBiggerSenderAccountBalance() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", "XXX", Constants.USD, BigDecimal.valueOf(99999999999999.0), null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Sender has no founds (USD).", ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testInexistentBeneficiaryBank() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", "XXX", Constants.USD, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals(Constants.OPERATION_NOT_SUPPORTED, ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testOtherBeneficiaryBank() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", Constants.BORINGBANK, Constants.USD, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals(Constants.OPERATION_NOT_SUPPORTED, ret.getReturnDto().getMessages().get(0));
	}
	
	@Test
	public void testInexistentBeneficiaryAccount() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Beneficiary's account doesn't exists.", ret.getReturnDto().getMessages().get(0));
	}
	
	
	@Test
	public void testTransferInexxistentBeneficiaryAccountBallance() throws SQLException
	{	
		TransferTransactionalDataDto ret = transactionValidationService.validateTransfer("ACCOUNT_1", "ACCOUNT_9999", Constants.REVOLUT_BANK_CODE, Constants.USD, BigDecimal.ONE, null);
		Assert.assertEquals(TransferTransactionStatusEnum.NOT_PROCESSED, ret.getReturnDto().getStatus());
		Assert.assertEquals("Beneficiary's account doesn't exists.", ret.getReturnDto().getMessages().get(0));
	}
}
