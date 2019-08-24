package com.github.prbpedro.accountmanager.domain.services;

import java.math.BigDecimal;
import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import org.slf4j.Logger;

import com.github.prbpedro.accountmanager.domain.enums.TransferTransactionStatusEnum;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionReturnDto;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionalDataDto;
import com.github.prbpedro.accountmanager.domain.services.interfaces.ITransactionValidationService;
import com.github.prbpedro.accountmanager.domain.services.interfaces.ITransferTransactionService;
import com.github.prbpedro.accountmanager.domain.tables.AccountBalance;
import com.github.prbpedro.accountmanager.domain.tables.AccountTransaction;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountBalanceRecord;
import com.github.prbpedro.accountmanager.domain.util.Constants;
import com.google.inject.Inject;

/**
 * Transfer transaction service class.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class TransferTransactionService extends BaseService implements ITransferTransactionService{

	/**
	 * transactionValidationService attribute.
	 */
	@Inject
	public ITransactionValidationService transactionValidationService;
	
	/**
	 * logger attribute.
	 */
	@Inject
	public Logger logger;
	
	/**
	 * Method responsible for executing a transfer.
	 * 
	 * @param idAccountSender
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param codeCurrency
	 * @param ammount
	 * @return TransferTransactionReturnDto
	 */
	public TransferTransactionReturnDto doTransferTransaction(String idAccountSender, String idAccountBeneficiary, String codeBankBeneficiary, String codeCurrency, BigDecimal ammount) {
		
		try (Connection conn = databaseService.createConnection()){
				
			DSLContext context = DSL.using(conn, SQLDialect.H2);
			
			TransferTransactionalDataDto dataDto = transactionValidationService.validateTransfer(idAccountSender, idAccountBeneficiary, codeBankBeneficiary, codeCurrency, ammount, context);
			
			if(dataDto.getReturnDto()!=null){
				return dataDto.getReturnDto();
			}
		
			TransferTransactionReturnDto ret = new TransferTransactionReturnDto();
			context.transaction(configuration -> {
				
				AccountBalanceRecord senderAccountBalance = dataDto.getSenderAcountBalance();
				senderAccountBalance.set(AccountBalance.ACCOUNT_BALANCE.VALUE, senderAccountBalance.getValue().add(ammount.negate()));
				((UpdatableRecord<?>)senderAccountBalance).store();
				
				if(dataDto.getBeneficiaryAccountBalance() == null) {
				
					dataDto.setBeneficiaryAccountBalance(context.insertInto(
						AccountBalance.ACCOUNT_BALANCE, 
						AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID, 
						AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID, 
						AccountBalance.ACCOUNT_BALANCE.VALUE)
					.values(
							dataDto.getBeneficiaryAccount().getId(),
							dataDto.getCurrency().getId(),
							ammount)
					.returning()
					.fetchOne());
				}else {
					AccountBalanceRecord beneficiaryAccountBalance = dataDto.getBeneficiaryAccountBalance();
					beneficiaryAccountBalance.setValue(beneficiaryAccountBalance.getValue().add(ammount));
					((UpdatableRecord<?>)beneficiaryAccountBalance).store();
				}
				
				ret.setTransferInformation(
						context.insertInto(AccountTransaction.ACCOUNT_TRANSACTION)
						.set(AccountTransaction.ACCOUNT_TRANSACTION.SENDER_ID,dataDto.getSenderAccount().getId())
						.set(AccountTransaction.ACCOUNT_TRANSACTION.BENEFICIARY_ID,dataDto.getBeneficiaryAccount().getId())
						.set(AccountTransaction.ACCOUNT_TRANSACTION.CURRENCY_ID,dataDto.getCurrency().getId())
						.set(AccountTransaction.ACCOUNT_TRANSACTION.STATUS,TransferTransactionStatusEnum.PROCESSED.name())
						.set(AccountTransaction.ACCOUNT_TRANSACTION.VALUE,ammount)
						.set(AccountTransaction.ACCOUNT_TRANSACTION.DATE_TIME,DSL.currentTimestamp())
						.returning()
						.fetchOne()
				);
				ret.setStatus(TransferTransactionStatusEnum.PROCESSED);
				beforeCommit();
			});
			
			return ret;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			
			TransferTransactionReturnDto ret = new TransferTransactionReturnDto();
			ret.setStatus(TransferTransactionStatusEnum.ERROR_PROCESSING);
			ret.addMessage(Constants.ERROR_PROCESSING_TRASACTION_TRANSFER);
			return ret;
		}
	}
	
	/**
	 * Method created to facilitate tests.
	 */
	protected void beforeCommit() {
	}
}
