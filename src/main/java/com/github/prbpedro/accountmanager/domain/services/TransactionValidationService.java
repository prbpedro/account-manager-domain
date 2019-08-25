package com.github.prbpedro.accountmanager.domain.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.github.prbpedro.accountmanager.domain.enums.TransferTransactionStatusEnum;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionReturnDto;
import com.github.prbpedro.accountmanager.domain.services.dto.TransferTransactionalDataDto;
import com.github.prbpedro.accountmanager.domain.services.interfaces.ITransactionValidationService;
import com.github.prbpedro.accountmanager.domain.tables.Account;
import com.github.prbpedro.accountmanager.domain.tables.AccountBalance;
import com.github.prbpedro.accountmanager.domain.tables.Bank;
import com.github.prbpedro.accountmanager.domain.tables.Currency;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountBalanceRecord;
import com.github.prbpedro.accountmanager.domain.tables.records.AccountRecord;
import com.github.prbpedro.accountmanager.domain.util.Constants;

/**
 * Transaction validation service class.
 * 
 * @author Pedro Ribeiro Baptista
 */
public class TransactionValidationService extends BaseService implements ITransactionValidationService{

	/**
	 * Method responsible for returning a database account balance record.
	 * 
	 * @param context
	 * @param accountId
	 * @param currencyId
	 * @return AccountBalanceRecord
	 */
	private AccountBalanceRecord getAccountBalance(DSLContext context, String accountId, long currencyId) {
		return context.
				selectFrom(AccountBalance.ACCOUNT_BALANCE)
				.where(AccountBalance.ACCOUNT_BALANCE.ACCOUNT_ID.eq(accountId)
						.and(AccountBalance.ACCOUNT_BALANCE.CURRENCY_ID.eq(currencyId)))
				.fetchOne();
	}
	
	/**
	 * Method responsible for returning an active database account record.
	 * 
	 * @param context
	 * @param idAccount
	 * @return AccountRecord
	 */
	private AccountRecord getActiveAccount(DSLContext context, String idAccount) {
		return context.selectFrom(Account.ACCOUNT).where(Account.ACCOUNT.ID.eq(idAccount).and(Account.ACCOUNT.ACTIVE.eq(true))).fetchOne();
	}
	
	/**
	 * Method responsible for validating a transfer transaction.
	 * 
	 * @param idAccountSender
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param codeCurrency
	 * @param ammount
	 * @param context
	 * @return TransferTransactionalDataDto
	 * @throws SQLException
	 */
	public TransferTransactionalDataDto validateTransfer(
			String idAccountSender, 
			String idAccountBeneficiary, 
			String codeBankBeneficiary, 
			String codeCurrency, 
			BigDecimal ammount, 
			DSLContext context) throws SQLException {
		
		if(context==null) {
			try (Connection conn = databaseService.createConnection()){
				context = DSL.using(conn, SQLDialect.H2);
				return internalValidateTransfer(idAccountSender, idAccountBeneficiary, codeBankBeneficiary, codeCurrency, ammount, context);
			}
		}
		else{
			return internalValidateTransfer(idAccountSender, idAccountBeneficiary, codeBankBeneficiary, codeCurrency, ammount, context);
		}
	}
	
	/**
	 * Method created to facilitate tests
	 * 
	 * @param idAccountSender
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param codeCurrency
	 * @param ammount
	 * @param context
	 * @return TransferTransactionalDataDto
	 * @throws SQLException
	 */
	private TransferTransactionalDataDto internalValidateTransfer(
			String idAccountSender, 
			String idAccountBeneficiary, 
			String codeBankBeneficiary, 
			String codeCurrency, 
			BigDecimal ammount, 
			DSLContext context) throws SQLException {
		
			TransferTransactionalDataDto ret = new TransferTransactionalDataDto();
			
			boolean validation = validateCurrency(context, codeCurrency, ret);
			if(!validation) {
				return ret;
			}
			
			validation = validateSenderData(context, idAccountSender, ammount, ret);
			if(!validation) {
				return ret;
			}
			
			validation = validateBeneficiaryData(context, idAccountBeneficiary, codeBankBeneficiary, ret);
			if(!validation) {
				return ret;
			}
			
			return ret;
	}

	/**
	 * Method responsible for validating the sender's data.
	 * 
	 * @param context
	 * @param idAccountSender
	 * @param ammount
	 * @param ret
	 * @return boolean
	 */
	private boolean validateSenderData(DSLContext context, String idAccountSender, BigDecimal ammount, TransferTransactionalDataDto ret) {
		
		boolean validation = validateSenderAccountExistence(context, idAccountSender, ret);
		if(!validation) {
			return false;
		}
		
		return validateSenderAccountBalance(context, ammount, ret);
	}

	/**
	 * Method responsible for validating the sender's account balance.
	 *  
	 * @param context
	 * @param ammount
	 * @param ret
	 * @return boolean
	 */
	private boolean validateSenderAccountBalance(DSLContext context, BigDecimal ammount, TransferTransactionalDataDto ret) {
		AccountBalanceRecord senderAccountBalance = getAccountBalance(context, ret.getSenderAccount().getId(), ret.getCurrency().getId());
		
		ret.setSenderAcountBalance(senderAccountBalance);
		if(ret.getSenderAcountBalance() == null ||
				ret.getSenderAcountBalance().getValue().compareTo(ammount) < 1 ) {
			ret.setReturnDto(new TransferTransactionReturnDto());
			ret.getReturnDto().setStatus(TransferTransactionStatusEnum.NOT_PROCESSED);
			ret.getReturnDto().addMessage(String.format(Constants.NO_FUNDS, ret.getCurrency().getCode()));
			return false;
		}
		
		return true;
	}

	/**
	 * Method responsible for validating the beneficiary's data.
	 * 
	 * @param context
	 * @param idAccountBeneficiary
	 * @param codeBankBeneficiary
	 * @param ret
	 * @return boolean
	 */
	private boolean validateBeneficiaryData(DSLContext context, String idAccountBeneficiary, String codeBankBeneficiary,
			TransferTransactionalDataDto ret) {
		boolean validation = validateBeneficiaryBank(context, codeBankBeneficiary, ret);
		if(!validation) {
			return false;
		}
		
		validation = validateBeneficiaryAccountExistence(context, idAccountBeneficiary, ret);
		if(!validation) {
			return false;
		}
		
		return validateBeneficiaryAccountBalance(context, ret);
	}

	/**
	 * Method responsible for validating the beneficiary's account balance.
	 * 
	 * @param context
	 * @param ret
	 * @return boolean
	 */
	private boolean validateBeneficiaryAccountBalance(DSLContext context, TransferTransactionalDataDto ret) {
		AccountBalanceRecord beneficiaryAccountBalance = getAccountBalance(context, ret.getBeneficiaryAccount().getId(), ret.getCurrency().getId());
		ret.setBeneficiaryAccountBalance(beneficiaryAccountBalance);
		return true;
	}

	/**
	 * Method responsible for validating the beneficiary's bank
	 * 
	 * @param context
	 * @param codeBankBeneficiary
	 * @param ret
	 * @return boolean
	 */
	private boolean validateBeneficiaryBank(DSLContext context, String codeBankBeneficiary,
			TransferTransactionalDataDto ret) {
		ret.setBeneficiaryBank(context.selectFrom(Bank.BANK).where(Bank.BANK.CODE.eq(codeBankBeneficiary)).fetchOne());
		if(ret.getBeneficiaryBank() == null || !ret.getBeneficiaryBank().getCode().equals(Constants.REVOLUT_BANK_CODE)){
			ret.setReturnDto(new TransferTransactionReturnDto());
			ret.getReturnDto().setStatus(TransferTransactionStatusEnum.NOT_PROCESSED);
			ret.getReturnDto().addMessage(Constants.OPERATION_NOT_SUPPORTED);
			return false;
		}
		
		return true;
	}

	/**
	 * Method responsible for validating a currency.
	 * 
	 * @param context
	 * @param codeCurrency
	 * @param ret
	 * @return boolean
	 */
	private boolean validateCurrency(DSLContext context, String codeCurrency, TransferTransactionalDataDto ret) {
		ret.setCurrency(context.selectFrom(Currency.CURRENCY).where(Currency.CURRENCY.CODE.eq(codeCurrency)).fetchOne());
		if(ret.getCurrency() == null) {
			ret.setReturnDto(new TransferTransactionReturnDto());
			ret.getReturnDto().setStatus(TransferTransactionStatusEnum.NOT_FOUND);
			ret.getReturnDto().addMessage(String.format(Constants.NOT_PROCESSED_NOT_EXISTS, Constants.TRANSACTIONS, Constants.CURRENCY));
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method responsible for validating the beneficiary's account existence.
	 * 
	 * @param context
	 * @param idBeneficiary
	 * @param ret
	 * @return boolean
	 */
	private boolean validateBeneficiaryAccountExistence(DSLContext context, String idBeneficiary,
			TransferTransactionalDataDto ret) {
		ret.setBeneficiaryAccount((getActiveAccount(context, idBeneficiary)));
		if(ret.getBeneficiaryAccount() == null) {
			ret.setReturnDto(new TransferTransactionReturnDto());
			ret.getReturnDto().setStatus(TransferTransactionStatusEnum.NOT_FOUND);
			ret.getReturnDto().addMessage(String.format(Constants.NOT_PROCESSED_NOT_EXISTS, Constants.BENEFICIARYS, Constants.ACCOUNT));
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method responsible for validating the sender's account existence.
	 * 
	 * @param context
	 * @param idAccountSender
	 * @param ret
	 * @return boolean
	 */
	private boolean validateSenderAccountExistence(DSLContext context, String idAccountSender,
			TransferTransactionalDataDto ret) {
		ret.setSenderAccount(getActiveAccount(context, idAccountSender));
		if(ret.getSenderAccount() == null) {
			ret.setReturnDto(new TransferTransactionReturnDto());
			ret.getReturnDto().setStatus(TransferTransactionStatusEnum.NOT_FOUND);
			ret.getReturnDto().addMessage(String.format(Constants.NOT_PROCESSED_NOT_EXISTS, Constants.SENDERS, Constants.ACCOUNT));
			return false;
		}
		
		return true;
	}
}
